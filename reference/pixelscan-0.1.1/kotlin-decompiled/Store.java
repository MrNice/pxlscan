package com.mrnice.pixelscan;

import android.content.Context;
import androidx.autofill.HintConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Store.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000f\u001a\u00020\u0005J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\u001e\u0010\"\u001a\u00020\u00112\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0 2\b\b\u0002\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020\u0011J\u001e\u0010)\u001a\u00020\u00112\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050 2\b\b\u0002\u0010%\u001a\u00020&J\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020&0 2\u0006\u0010,\u001a\u00020\u0005R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/mrnice/pixelscan/Store;", "", "ctx", "Landroid/content/Context;", "networkKey", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "dir", "Ljava/io/File;", "safeKey", "devicesFile", "logFile", "overridesFile", "settingsFile", "knownDevicesJson", "saveKnownDevices", "", "json", "knownCount", "", "overrides", "Lorg/json/JSONObject;", "setOverride", "key", "field", "value", "overrideFor", "Lcom/mrnice/pixelscan/DeviceOverride;", "device", "Lcom/mrnice/pixelscan/Device;", "log", "", "Lcom/mrnice/pixelscan/LogEntry;", "appendEvents", "events", "Lcom/mrnice/pixelscan/NetEvent;", "at", "", "clearLog", "presenceFile", "recordPresence", "onlineIds", "presenceFor", "id", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Store {
    private final File devicesFile;
    private final File dir;
    private final File logFile;
    private final File overridesFile;
    private final File presenceFile;
    private final String safeKey;
    private final File settingsFile;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public Store(Context ctx, String networkKey) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(networkKey, "networkKey");
        File file = new File(ctx.getFilesDir(), "nets");
        file.mkdirs();
        this.dir = file;
        String strReplace = new Regex("[^A-Za-z0-9_.-]").replace(networkKey, "_");
        this.safeKey = strReplace;
        this.devicesFile = new File(file, strReplace + ".devices.json");
        this.logFile = new File(file, strReplace + ".log.json");
        this.overridesFile = new File(file, strReplace + ".overrides.json");
        this.settingsFile = new File(ctx.getFilesDir(), "settings.json");
        this.presenceFile = new File(file, strReplace + ".presence.json");
    }

    public final String knownDevicesJson() {
        String text$default;
        File file = this.devicesFile;
        if (!file.exists()) {
            file = null;
        }
        return (file == null || (text$default = FilesKt.readText$default(file, null, 1, null)) == null) ? "[]" : text$default;
    }

    public final void saveKnownDevices(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        FilesKt.writeText$default(this.devicesFile, json, null, 2, null);
    }

    public final int knownCount() {
        try {
            return new JSONArray(knownDevicesJson()).length();
        } catch (Exception unused) {
            return 0;
        }
    }

    public final JSONObject overrides() {
        String text$default;
        try {
            File file = this.overridesFile;
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

    public final void setOverride(String key, String field, Object value) throws JSONException {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(field, "field");
        JSONObject jSONObjectOverrides = overrides();
        JSONObject jSONObjectOptJSONObject = jSONObjectOverrides.optJSONObject(key);
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
        }
        if (value == null) {
            jSONObjectOptJSONObject.remove(field);
        } else {
            jSONObjectOptJSONObject.put(field, value);
        }
        jSONObjectOverrides.put(key, jSONObjectOptJSONObject);
        File file = this.overridesFile;
        String string = jSONObjectOverrides.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        FilesKt.writeText$default(file, string, null, 2, null);
    }

    public final DeviceOverride overrideFor(Device device) {
        Intrinsics.checkNotNullParameter(device, "device");
        JSONObject jSONObjectOptJSONObject = overrides().optJSONObject(INSTANCE.identityKey(device));
        return jSONObjectOptJSONObject == null ? new DeviceOverride(null, null, false, false, null, 31, null) : new DeviceOverride(NativeKt.optStringOrNull(jSONObjectOptJSONObject, "nickname"), NativeKt.optStringOrNull(jSONObjectOptJSONObject, "type"), jSONObjectOptJSONObject.optBoolean("watched"), jSONObjectOptJSONObject.optBoolean("known"), NativeKt.optStringOrNull(jSONObjectOptJSONObject, "note"));
    }

    public final List<LogEntry> log() {
        String text$default;
        try {
            File file = this.logFile;
            if (!file.exists()) {
                file = null;
            }
            if (file == null || (text$default = FilesKt.readText$default(file, null, 1, null)) == null) {
                text$default = "[]";
            }
            JSONArray jSONArray = new JSONArray(text$default);
            IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(((IntIterator) it).nextInt());
                LogEntry logEntry = jSONObjectOptJSONObject != null ? LogEntry.INSTANCE.parse(jSONObjectOptJSONObject) : null;
                if (logEntry != null) {
                    arrayList.add(logEntry);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }

    public static /* synthetic */ void appendEvents$default(Store store, List list, long j, int i, Object obj) throws JSONException {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        store.appendEvents(list, j);
    }

    public final void appendEvents(List<NetEvent> events, long at) throws JSONException {
        JSONArray jSONArray;
        String text$default;
        Intrinsics.checkNotNullParameter(events, "events");
        if (events.isEmpty()) {
            return;
        }
        try {
            File file = this.logFile;
            if (!file.exists()) {
                file = null;
            }
            if (file == null || (text$default = FilesKt.readText$default(file, null, 1, null)) == null) {
                text$default = "[]";
            }
            jSONArray = new JSONArray(text$default);
        } catch (Exception unused) {
            jSONArray = new JSONArray();
        }
        for (NetEvent netEvent : events) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("t", at);
            jSONObject.put("type", netEvent.getType());
            jSONObject.put(HintConstants.AUTOFILL_HINT_NAME, netEvent.getName());
            jSONObject.put("ip", netEvent.getIp());
            jSONObject.put("detail", netEvent.getDetail());
            jSONArray.put(jSONObject);
        }
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int iMax = Math.max(0, jSONArray.length() - 500); iMax < length; iMax++) {
            jSONArray2.put(jSONArray.get(iMax));
        }
        File file2 = this.logFile;
        String string = jSONArray2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        FilesKt.writeText$default(file2, string, null, 2, null);
    }

    public final void clearLog() {
        FilesKt.writeText$default(this.logFile, "[]", null, 2, null);
    }

    public static /* synthetic */ void recordPresence$default(Store store, List list, long j, int i, Object obj) throws JSONException {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        store.recordPresence(list, j);
    }

    public final void recordPresence(List<String> onlineIds, long at) throws JSONException {
        JSONObject jSONObject;
        String text$default;
        Intrinsics.checkNotNullParameter(onlineIds, "onlineIds");
        try {
            File file = this.presenceFile;
            if (!file.exists()) {
                file = null;
            }
            if (file == null || (text$default = FilesKt.readText$default(file, null, 1, null)) == null) {
                text$default = "{}";
            }
            jSONObject = new JSONObject(text$default);
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        long j = at - 93600000;
        for (String str : onlineIds) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
            }
            jSONArrayOptJSONArray.put(at);
            JSONArray jSONArray = new JSONArray();
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                long jOptLong = jSONArrayOptJSONArray.optLong(i);
                if (jOptLong >= j) {
                    jSONArray.put(jOptLong);
                }
            }
            jSONObject.put(str, jSONArray);
        }
        File file2 = this.presenceFile;
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        FilesKt.writeText$default(file2, string, null, 2, null);
    }

    public final List<Long> presenceFor(String id) {
        String text$default;
        Intrinsics.checkNotNullParameter(id, "id");
        try {
            File file = this.presenceFile;
            if (!file.exists()) {
                file = null;
            }
            if (file == null || (text$default = FilesKt.readText$default(file, null, 1, null)) == null) {
                text$default = "{}";
            }
            JSONArray jSONArrayOptJSONArray = new JSONObject(text$default).optJSONArray(id);
            if (jSONArrayOptJSONArray == null) {
                return CollectionsKt.emptyList();
            }
            IntRange intRangeUntil = RangesKt.until(0, jSONArrayOptJSONArray.length());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                arrayList.add(Long.valueOf(jSONArrayOptJSONArray.optLong(((IntIterator) it).nextInt())));
            }
            return arrayList;
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }

    /* compiled from: Store.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J@\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0012"}, d2 = {"Lcom/mrnice/pixelscan/Store$Companion;", "", "<init>", "()V", "identityKey", "", "d", "Lcom/mrnice/pixelscan/Device;", "keyOf", "mac", "uuid", "mdns", "netbios", "dns", "ip", "networkKey", "lan", "Lcom/mrnice/pixelscan/LanInfo;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String identityKey(Device d) {
            Intrinsics.checkNotNullParameter(d, "d");
            return keyOf(d.getMac(), d.getSsdpUuid(), d.getMdnsName(), d.getNetbiosName(), d.getDnsName(), d.getIp());
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0077  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.String keyOf(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
            /*
                r3 = this;
                java.lang.String r0 = "ip"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.String r0 = "toLowerCase(...)"
                if (r4 == 0) goto L27
                java.util.Locale r1 = java.util.Locale.ROOT
                java.lang.String r4 = r4.toLowerCase(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
                if (r4 == 0) goto L27
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "mac:"
                r1.<init>(r2)
                java.lang.StringBuilder r4 = r1.append(r4)
                java.lang.String r4 = r4.toString()
                if (r4 != 0) goto L26
                goto L27
            L26:
                return r4
            L27:
                if (r5 == 0) goto L39
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r6 = "upnp:"
                r4.<init>(r6)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                return r4
            L39:
                r4 = 0
                if (r6 == 0) goto L57
                java.util.Locale r5 = java.util.Locale.ROOT
                java.lang.String r5 = r6.toLowerCase(r5)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                if (r5 == 0) goto L57
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r1 = "mdns:"
                r6.<init>(r1)
                java.lang.StringBuilder r5 = r6.append(r5)
                java.lang.String r5 = r5.toString()
                goto L58
            L57:
                r5 = r4
            L58:
                if (r5 != 0) goto La8
                if (r7 == 0) goto L77
                java.util.Locale r5 = java.util.Locale.ROOT
                java.lang.String r5 = r7.toLowerCase(r5)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                if (r5 == 0) goto L77
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r7 = "nb:"
                r6.<init>(r7)
                java.lang.StringBuilder r5 = r6.append(r5)
                java.lang.String r5 = r5.toString()
                goto L78
            L77:
                r5 = r4
            L78:
                if (r5 != 0) goto La8
                if (r8 == 0) goto L96
                java.util.Locale r5 = java.util.Locale.ROOT
                java.lang.String r5 = r8.toLowerCase(r5)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                if (r5 == 0) goto L96
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r6 = "dns:"
                r4.<init>(r6)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
            L96:
                if (r4 != 0) goto La7
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r5 = "ip:"
                r4.<init>(r5)
                java.lang.StringBuilder r4 = r4.append(r9)
                java.lang.String r4 = r4.toString()
            La7:
                return r4
            La8:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.Store.Companion.keyOf(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
        }

        public final String networkKey(LanInfo lan) {
            Intrinsics.checkNotNullParameter(lan, "lan");
            String gateway = lan.getGateway();
            if (gateway == null) {
                gateway = "nogw";
            }
            return gateway + "_" + lan.getSubnetLabel();
        }
    }
}
