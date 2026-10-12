package com.mrnice.pixelscan;

import androidx.autofill.HintConstants;
import androidx.core.os.EnvironmentCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/mrnice/pixelscan/NetEvent;", "", "type", "", "id", HintConstants.AUTOFILL_HINT_NAME, "ip", "detail", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getId", "getName", "getIp", "getDetail", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class NetEvent {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String detail;
    private final String id;
    private final String ip;
    private final String name;
    private final String type;

    public static /* synthetic */ NetEvent copy$default(NetEvent netEvent, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = netEvent.type;
        }
        if ((i & 2) != 0) {
            str2 = netEvent.id;
        }
        if ((i & 4) != 0) {
            str3 = netEvent.name;
        }
        if ((i & 8) != 0) {
            str4 = netEvent.ip;
        }
        if ((i & 16) != 0) {
            str5 = netEvent.detail;
        }
        String str6 = str5;
        String str7 = str3;
        return netEvent.copy(str, str2, str7, str4, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
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

    public final NetEvent copy(String type, String id, String name, String ip, String detail) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(detail, "detail");
        return new NetEvent(type, id, name, ip, detail);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NetEvent)) {
            return false;
        }
        NetEvent netEvent = (NetEvent) other;
        return Intrinsics.areEqual(this.type, netEvent.type) && Intrinsics.areEqual(this.id, netEvent.id) && Intrinsics.areEqual(this.name, netEvent.name) && Intrinsics.areEqual(this.ip, netEvent.ip) && Intrinsics.areEqual(this.detail, netEvent.detail);
    }

    public int hashCode() {
        return (((((((this.type.hashCode() * 31) + this.id.hashCode()) * 31) + this.name.hashCode()) * 31) + this.ip.hashCode()) * 31) + this.detail.hashCode();
    }

    public String toString() {
        return "NetEvent(type=" + this.type + ", id=" + this.id + ", name=" + this.name + ", ip=" + this.ip + ", detail=" + this.detail + ")";
    }

    /* compiled from: Native.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/mrnice/pixelscan/NetEvent$Companion;", "", "<init>", "()V", "parse", "Lcom/mrnice/pixelscan/NetEvent;", "o", "Lorg/json/JSONObject;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final NetEvent parse(JSONObject o) {
            Intrinsics.checkNotNullParameter(o, "o");
            String strOptString = o.optString("type");
            String strOptStringOrNull = NativeKt.optStringOrNull(o, "ip");
            String str = "";
            String str2 = (strOptStringOrNull == null && (strOptStringOrNull = NativeKt.optStringOrNull(o, "to")) == null && (strOptStringOrNull = NativeKt.optStringOrNull(o, "last_ip")) == null) ? "" : strOptStringOrNull;
            if (strOptString != null) {
                switch (strOptString.hashCode()) {
                    case -1154529463:
                        if (strOptString.equals("joined")) {
                            str = "Joined the network";
                            break;
                        }
                        break;
                    case -928317540:
                        if (strOptString.equals("ip_changed")) {
                            str = o.optString("from") + " → " + o.optString("to");
                            break;
                        }
                        break;
                    case 108960:
                        if (strOptString.equals("new")) {
                            if (!o.optBoolean(EnvironmentCompat.MEDIA_UNKNOWN)) {
                                str = "New device";
                                break;
                            } else {
                                str = "New unknown device";
                                break;
                            }
                        }
                        break;
                    case 3317767:
                        if (strOptString.equals("left")) {
                            str = "Left the network";
                            break;
                        }
                        break;
                }
            }
            Intrinsics.checkNotNull(strOptString);
            String strOptString2 = o.optString("id");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            String strOptString3 = o.optString(HintConstants.AUTOFILL_HINT_NAME);
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
            return new NetEvent(strOptString, strOptString2, strOptString3, str2, str);
        }
    }

    public NetEvent(String type, String id, String name, String ip, String detail) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.type = type;
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.detail = detail;
    }

    public final String getDetail() {
        return this.detail;
    }

    public final String getId() {
        return this.id;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }
}
