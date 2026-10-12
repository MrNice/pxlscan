package com.mrnice.pixelscan;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Store.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00148F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R$\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013¨\u0006 "}, d2 = {"Lcom/mrnice/pixelscan/Settings;", "", "ctx", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "file", "Ljava/io/File;", "read", "Lorg/json/JSONObject;", "write", "", "o", "v", "", "monitorEnabled", "getMonitorEnabled", "()Z", "setMonitorEnabled", "(Z)V", "", "intervalMinutes", "getIntervalMinutes", "()I", "setIntervalMinutes", "(I)V", "alertAllNew", "getAlertAllNew", "setAlertAllNew", "alertWatchedMovement", "getAlertWatchedMovement", "setAlertWatchedMovement", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Settings {
    public static final int $stable = 8;
    private final File file;

    public Settings(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        this.file = new File(ctx.getFilesDir(), "settings.json");
    }

    private final JSONObject read() {
        String text$default;
        try {
            File file = this.file;
            if (!file.exists()) {
                file = null;
            }
            if (file == null || (text$default = FilesKt.readText$default(file, null, 1, null)) == null) {
                text$default = "{}";
            }
            return new JSONObject(text$default);
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    private final void write(JSONObject o) {
        File file = this.file;
        String string = o.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        FilesKt.writeText$default(file, string, null, 2, null);
    }

    public final boolean getMonitorEnabled() {
        return read().optBoolean("monitor", false);
    }

    public final void setMonitorEnabled(boolean z) throws JSONException {
        JSONObject jSONObjectPut = read().put("monitor", z);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        write(jSONObjectPut);
    }

    public final int getIntervalMinutes() {
        return read().optInt("interval", 30);
    }

    public final void setIntervalMinutes(int i) throws JSONException {
        JSONObject jSONObjectPut = read().put("interval", i);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        write(jSONObjectPut);
    }

    public final boolean getAlertAllNew() {
        return read().optBoolean("alert_all_new", true);
    }

    public final void setAlertAllNew(boolean z) throws JSONException {
        JSONObject jSONObjectPut = read().put("alert_all_new", z);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        write(jSONObjectPut);
    }

    public final boolean getAlertWatchedMovement() {
        return read().optBoolean("alert_watched", true);
    }

    public final void setAlertWatchedMovement(boolean z) throws JSONException {
        JSONObject jSONObjectPut = read().put("alert_watched", z);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        write(jSONObjectPut);
    }
}
