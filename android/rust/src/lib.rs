//! PxLScan core: JNI exports for `com.pixelicious.pxlscan.Native` plus the scan handle registry.
//! The interface is docs/CORE-CONTRACT.md. Nothing here may panic across the FFI boundary.

pub mod classify;
pub mod discover;
pub mod dns;
pub mod http;
pub mod icmp;
pub mod model;
pub mod monitor;
pub mod scan;

use std::collections::HashMap;
use std::panic::{catch_unwind, AssertUnwindSafe};
use std::sync::atomic::{AtomicI64, Ordering};
use std::sync::{Arc, Mutex, OnceLock};

use jni::objects::{JObject, JString};
use jni::sys::{jfloat, jlong, jstring};
use jni::JNIEnv;

use model::{ScanConfig, GONE_JSON};
use scan::Shared;

struct ScanState {
    shared: Arc<Shared>,
    task: Mutex<Option<tokio::task::JoinHandle<()>>>,
}

static RUNTIME: OnceLock<tokio::runtime::Runtime> = OnceLock::new();
static SCANS: OnceLock<Mutex<HashMap<i64, Arc<ScanState>>>> = OnceLock::new();
static NEXT_HANDLE: AtomicI64 = AtomicI64::new(1);

fn runtime() -> &'static tokio::runtime::Runtime {
    RUNTIME.get_or_init(|| {
        tokio::runtime::Builder::new_multi_thread()
            .worker_threads(4)
            .thread_name("pxlscan")
            .enable_all()
            .build()
            .expect("tokio runtime")
    })
}

fn scans() -> std::sync::MutexGuard<'static, HashMap<i64, Arc<ScanState>>> {
    SCANS
        .get_or_init(|| Mutex::new(HashMap::new()))
        .lock()
        .unwrap_or_else(|p| p.into_inner())
}

fn error_json(msg: &str) -> String {
    format!(
        "{{\"error\":{}}}",
        serde_json::Value::String(msg.to_string())
    )
}

/// Parse the config and launch the scan. Returns the handle, or -1 on bad input.
pub fn start_scan(config_json: &str) -> i64 {
    let cfg: ScanConfig = match serde_json::from_str(config_json) {
        Ok(c) => c,
        Err(_) => return -1,
    };
    let shared = match Shared::new(cfg) {
        Ok(s) => Arc::new(s),
        Err(_) => return -1,
    };
    // Scans nobody cancelled stay in the map; drop finished ones once a few pile up.
    {
        let mut map = scans();
        if map.len() > 8 {
            map.retain(|_, s| !s.shared.snapshot().done);
        }
    }
    let task = runtime().spawn(scan::run(shared.clone()));
    let handle = NEXT_HANDLE.fetch_add(1, Ordering::Relaxed);
    scans().insert(
        handle,
        Arc::new(ScanState {
            shared,
            task: Mutex::new(Some(task)),
        }),
    );
    handle
}

/// Current `ScanSnapshot` JSON, or the exact "gone" document for an unknown handle.
pub fn poll_json(handle: i64) -> String {
    let state = scans().get(&handle).cloned();
    match state {
        None => GONE_JSON.to_string(),
        Some(s) => serde_json::to_string(&s.shared.snapshot())
            .unwrap_or_else(|e| error_json(&e.to_string())),
    }
}

/// Stop the scan and forget the handle.
pub fn cancel_scan(handle: i64) {
    let removed = scans().remove(&handle);
    if let Some(s) = removed {
        s.shared.request_cancel();
        if let Some(t) = s.task.lock().unwrap_or_else(|p| p.into_inner()).take() {
            t.abort();
        }
    }
}

fn jstr(env: &mut JNIEnv, s: &JString) -> Option<String> {
    env.get_string(s).ok().map(|j| j.into())
}

fn to_jstring(env: &mut JNIEnv, s: &str) -> jstring {
    match env.new_string(s) {
        Ok(j) => j.into_raw(),
        Err(_) => std::ptr::null_mut(),
    }
}

#[no_mangle]
pub extern "system" fn Java_com_pixelicious_pxlscan_Native_startScan(
    mut env: JNIEnv,
    _this: JObject,
    config: JString,
) -> jlong {
    catch_unwind(AssertUnwindSafe(|| match jstr(&mut env, &config) {
        Some(s) => start_scan(&s),
        None => -1,
    }))
    .unwrap_or(-1)
}

#[no_mangle]
pub extern "system" fn Java_com_pixelicious_pxlscan_Native_poll(
    mut env: JNIEnv,
    _this: JObject,
    handle: jlong,
) -> jstring {
    let out = catch_unwind(AssertUnwindSafe(|| poll_json(handle)))
        .unwrap_or_else(|_| error_json("panic in poll"));
    to_jstring(&mut env, &out)
}

#[no_mangle]
pub extern "system" fn Java_com_pixelicious_pxlscan_Native_cancel(
    _env: JNIEnv,
    _this: JObject,
    handle: jlong,
) {
    let _ = catch_unwind(AssertUnwindSafe(|| cancel_scan(handle)));
}

#[no_mangle]
pub extern "system" fn Java_com_pixelicious_pxlscan_Native_monitorDiff(
    mut env: JNIEnv,
    _this: JObject,
    input: JString,
) -> jstring {
    let out = catch_unwind(AssertUnwindSafe(|| match jstr(&mut env, &input) {
        Some(s) => monitor::diff_json(&s),
        None => error_json("bad input string"),
    }))
    .unwrap_or_else(|_| error_json("panic in monitorDiff"));
    to_jstring(&mut env, &out)
}

#[no_mangle]
pub extern "system" fn Java_com_pixelicious_pxlscan_Native_ping(
    mut env: JNIEnv,
    _this: JObject,
    ip: JString,
    timeout_ms: jlong,
) -> jfloat {
    catch_unwind(AssertUnwindSafe(|| match jstr(&mut env, &ip) {
        Some(s) => icmp::ping_ms(&s, timeout_ms),
        None => -1.0,
    }))
    .unwrap_or(-1.0)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn unknown_handle_is_gone() {
        assert_eq!(poll_json(987_654), GONE_JSON);
        assert_eq!(poll_json(-1), GONE_JSON);
        cancel_scan(987_654); // must not panic
    }

    #[test]
    fn bad_config_returns_minus_one() {
        assert_eq!(start_scan("not json"), -1);
        assert_eq!(start_scan(r#"{"self_ip":"zzz","prefix_len":24}"#), -1);
    }

    #[test]
    fn scan_lifecycle_cancel_makes_it_gone() {
        // 127.0.0.x: nothing to find, but the machinery must start, poll and cancel cleanly.
        let h = start_scan(
            r#"{"self_ip":"127.0.0.1","prefix_len":30,"gateway":null,"dns_servers":[],"timeout_ms":200,"self_name":"Test"}"#,
        );
        assert!(h >= 0);
        let v: serde_json::Value = serde_json::from_str(&poll_json(h)).unwrap();
        assert!(v["progress"].as_f64().unwrap() <= 1.0);
        assert_eq!(v["hosts"][0]["name"], "Test");
        assert!(v["diag"]["errors"].is_array());
        cancel_scan(h);
        assert_eq!(poll_json(h), GONE_JSON);
    }
}
