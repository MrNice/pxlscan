package com.mrnice.pixelscan;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006&"}, d2 = {"Lcom/mrnice/pixelscan/ScanConfig;", "", "selfIp", "", "prefixLen", "", "gateway", "dnsServers", "", "timeoutMs", "", "selfName", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;JLjava/lang/String;)V", "getSelfIp", "()Ljava/lang/String;", "getPrefixLen", "()I", "getGateway", "getDnsServers", "()Ljava/util/List;", "getTimeoutMs", "()J", "getSelfName", "toJson", "Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ScanConfig {
    public static final int $stable = 8;
    private final List<String> dnsServers;
    private final String gateway;
    private final int prefixLen;
    private final String selfIp;
    private final String selfName;
    private final long timeoutMs;

    public static /* synthetic */ ScanConfig copy$default(ScanConfig scanConfig, String str, int i, String str2, List list, long j, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = scanConfig.selfIp;
        }
        if ((i2 & 2) != 0) {
            i = scanConfig.prefixLen;
        }
        if ((i2 & 4) != 0) {
            str2 = scanConfig.gateway;
        }
        if ((i2 & 8) != 0) {
            list = scanConfig.dnsServers;
        }
        if ((i2 & 16) != 0) {
            j = scanConfig.timeoutMs;
        }
        if ((i2 & 32) != 0) {
            str3 = scanConfig.selfName;
        }
        String str4 = str3;
        long j2 = j;
        return scanConfig.copy(str, i, str2, list, j2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSelfIp() {
        return this.selfIp;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPrefixLen() {
        return this.prefixLen;
    }

    /* renamed from: component3, reason: from getter */
    public final String getGateway() {
        return this.gateway;
    }

    public final List<String> component4() {
        return this.dnsServers;
    }

    /* renamed from: component5, reason: from getter */
    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSelfName() {
        return this.selfName;
    }

    public final ScanConfig copy(String selfIp, int prefixLen, String gateway, List<String> dnsServers, long timeoutMs, String selfName) {
        Intrinsics.checkNotNullParameter(selfIp, "selfIp");
        Intrinsics.checkNotNullParameter(dnsServers, "dnsServers");
        return new ScanConfig(selfIp, prefixLen, gateway, dnsServers, timeoutMs, selfName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScanConfig)) {
            return false;
        }
        ScanConfig scanConfig = (ScanConfig) other;
        return Intrinsics.areEqual(this.selfIp, scanConfig.selfIp) && this.prefixLen == scanConfig.prefixLen && Intrinsics.areEqual(this.gateway, scanConfig.gateway) && Intrinsics.areEqual(this.dnsServers, scanConfig.dnsServers) && this.timeoutMs == scanConfig.timeoutMs && Intrinsics.areEqual(this.selfName, scanConfig.selfName);
    }

    public int hashCode() {
        int iHashCode = ((this.selfIp.hashCode() * 31) + Integer.hashCode(this.prefixLen)) * 31;
        String str = this.gateway;
        int iHashCode2 = (((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.dnsServers.hashCode()) * 31) + Long.hashCode(this.timeoutMs)) * 31;
        String str2 = this.selfName;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ScanConfig(selfIp=" + this.selfIp + ", prefixLen=" + this.prefixLen + ", gateway=" + this.gateway + ", dnsServers=" + this.dnsServers + ", timeoutMs=" + this.timeoutMs + ", selfName=" + this.selfName + ")";
    }

    public ScanConfig(String selfIp, int i, String str, List<String> dnsServers, long j, String str2) {
        Intrinsics.checkNotNullParameter(selfIp, "selfIp");
        Intrinsics.checkNotNullParameter(dnsServers, "dnsServers");
        this.selfIp = selfIp;
        this.prefixLen = i;
        this.gateway = str;
        this.dnsServers = dnsServers;
        this.timeoutMs = j;
        this.selfName = str2;
    }

    public final String getSelfIp() {
        return this.selfIp;
    }

    public final int getPrefixLen() {
        return this.prefixLen;
    }

    public final String getGateway() {
        return this.gateway;
    }

    public final List<String> getDnsServers() {
        return this.dnsServers;
    }

    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    public final String getSelfName() {
        return this.selfName;
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("self_ip", this.selfIp);
        jSONObject.put("prefix_len", this.prefixLen);
        Object obj = this.gateway;
        if (obj == null) {
            obj = JSONObject.NULL;
        }
        jSONObject.put("gateway", obj);
        jSONObject.put("dns_servers", new JSONArray((Collection) this.dnsServers));
        jSONObject.put("timeout_ms", this.timeoutMs);
        Object obj2 = this.selfName;
        if (obj2 == null) {
            obj2 = JSONObject.NULL;
        }
        jSONObject.put("self_name", obj2);
        return jSONObject;
    }
}
