package com.mrnice.pixelscan;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: Store.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/mrnice/pixelscan/LogEntry;", "", "t", "", "type", "", HintConstants.AUTOFILL_HINT_NAME, "ip", "detail", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getT", "()J", "getType", "()Ljava/lang/String;", "getName", "getIp", "getDetail", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LogEntry {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String detail;
    private final String ip;
    private final String name;
    private final long t;
    private final String type;

    public static /* synthetic */ LogEntry copy$default(LogEntry logEntry, long j, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            j = logEntry.t;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = logEntry.type;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = logEntry.name;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = logEntry.ip;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = logEntry.detail;
        }
        return logEntry.copy(j2, str5, str6, str7, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final long getT() {
        return this.t;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getIp() {
        return this.ip;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    public final LogEntry copy(long t, String type, String name, String ip, String detail) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(detail, "detail");
        return new LogEntry(t, type, name, ip, detail);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogEntry)) {
            return false;
        }
        LogEntry logEntry = (LogEntry) other;
        return this.t == logEntry.t && Intrinsics.areEqual(this.type, logEntry.type) && Intrinsics.areEqual(this.name, logEntry.name) && Intrinsics.areEqual(this.ip, logEntry.ip) && Intrinsics.areEqual(this.detail, logEntry.detail);
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.t) * 31) + this.type.hashCode()) * 31) + this.name.hashCode()) * 31) + this.ip.hashCode()) * 31) + this.detail.hashCode();
    }

    public String toString() {
        return "LogEntry(t=" + this.t + ", type=" + this.type + ", name=" + this.name + ", ip=" + this.ip + ", detail=" + this.detail + ")";
    }

    /* compiled from: Store.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/mrnice/pixelscan/LogEntry$Companion;", "", "<init>", "()V", "parse", "Lcom/mrnice/pixelscan/LogEntry;", "o", "Lorg/json/JSONObject;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LogEntry parse(JSONObject o) {
            Intrinsics.checkNotNullParameter(o, "o");
            long jOptLong = o.optLong("t");
            String strOptString = o.optString("type");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String strOptString2 = o.optString(HintConstants.AUTOFILL_HINT_NAME);
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            String strOptString3 = o.optString("ip");
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
            String strOptString4 = o.optString("detail");
            Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
            return new LogEntry(jOptLong, strOptString, strOptString2, strOptString3, strOptString4);
        }
    }

    public LogEntry(long j, String type, String name, String ip, String detail) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.t = j;
        this.type = type;
        this.name = name;
        this.ip = ip;
        this.detail = detail;
    }

    public final String getDetail() {
        return this.detail;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getName() {
        return this.name;
    }

    public final long getT() {
        return this.t;
    }

    public final String getType() {
        return this.type;
    }
}
