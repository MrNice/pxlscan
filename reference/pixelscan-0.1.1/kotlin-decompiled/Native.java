package com.mrnice.pixelscan;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u0005J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0082 J\u0011\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0082 J\u0011\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\fH\u0082 J\u0011\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH\u0082 J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\fH\u0082 J\u000e\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\fJ\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000eJ\u0018\u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006#"}, d2 = {"Lcom/mrnice/pixelscan/Native;", "", "<init>", "()V", "loaded", "", "value", "available", "getAvailable", "()Z", "ensureLoaded", "startScan", "", "configJson", "", "poll", "handle", "cancel", "", "monitorDiff", "inputJson", "ping", "", "ip", "timeoutMs", "start", "config", "Lcom/mrnice/pixelscan/ScanConfig;", "snapshot", "Lcom/mrnice/pixelscan/ScanSnapshot;", "stop", "diff", "Lcom/mrnice/pixelscan/MonitorOutput;", "input", "pingMs", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Native {
    private static volatile boolean available;
    private static volatile boolean loaded;
    public static final Native INSTANCE = new Native();
    public static final int $stable = 8;

    private final native void cancel(long handle);

    private final native String monitorDiff(String inputJson);

    private final native float ping(String ip, long timeoutMs);

    private final native String poll(long handle);

    private final native long startScan(String configJson);

    private Native() {
    }

    public final boolean getAvailable() {
        return available;
    }

    public final boolean ensureLoaded() {
        boolean z;
        if (!loaded) {
            synchronized (this) {
                if (!loaded) {
                    try {
                        System.loadLibrary("pixelscan_core");
                        z = true;
                    } catch (Throwable unused) {
                        z = false;
                    }
                    available = z;
                    loaded = true;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return available;
    }

    public final long start(ScanConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        if (!ensureLoaded()) {
            return -1L;
        }
        String string = config.toJson().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return startScan(string);
    }

    public final ScanSnapshot snapshot(long handle) {
        return (handle < 0 || !available) ? ScanSnapshot.INSTANCE.empty() : ScanSnapshot.INSTANCE.parse(poll(handle));
    }

    public final void stop(long handle) {
        if (handle < 0 || !available) {
            return;
        }
        cancel(handle);
    }

    public final MonitorOutput diff(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return ensureLoaded() ? MonitorOutput.INSTANCE.parse(monitorDiff(input)) : new MonitorOutput(CollectionsKt.emptyList(), "[]", CollectionsKt.emptyList());
    }

    public static /* synthetic */ float pingMs$default(Native r0, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 1000;
        }
        return r0.pingMs(str, j);
    }

    public final float pingMs(String ip, long timeoutMs) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        if (ensureLoaded()) {
            return ping(ip, timeoutMs);
        }
        return -1.0f;
    }
}
