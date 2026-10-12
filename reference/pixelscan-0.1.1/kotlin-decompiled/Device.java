package com.mrnice.pixelscan;

import androidx.autofill.HintConstants;
import androidx.core.os.EnvironmentCompat;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\t\n\u0002\b\u001d\b\u0087\b\u0018\u0000 Q2\u00020\u0001:\u0001QBÇ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010>\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010&J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014HÆ\u0003J\u000f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014HÆ\u0003J\u000f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014HÆ\u0003J\t\u0010I\u001a\u00020\u0018HÆ\u0003J\t\u0010J\u001a\u00020\u0018HÆ\u0003Jô\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018HÆ\u0001¢\u0006\u0002\u0010LJ\u0013\u0010M\u001a\u00020\u00182\b\u0010N\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010O\u001a\u00020\u000bHÖ\u0001J\t\u0010P\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001dR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\b\n\u0000\u001a\u0004\b1\u00100R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\b\n\u0000\u001a\u0004\b2\u00100R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u00103R\u0011\u0010\u0019\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u00103R\u0011\u00104\u001a\u0002058F¢\u0006\u0006\u001a\u0004\b6\u00107¨\u0006R"}, d2 = {"Lcom/mrnice/pixelscan/Device;", "", "ip", "", HintConstants.AUTOFILL_HINT_NAME, "kind", "vendor", "model", "rttMs", "", "ttl", "", "mac", "mdnsName", "netbiosName", "dnsName", "ssdpUuid", "httpServer", "httpTitle", "openPorts", "", "services", "seenBy", "isSelf", "", "isGateway", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZZ)V", "getIp", "()Ljava/lang/String;", "getName", "getKind", "getVendor", "getModel", "getRttMs", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getTtl", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMac", "getMdnsName", "getNetbiosName", "getDnsName", "getSsdpUuid", "getHttpServer", "getHttpTitle", "getOpenPorts", "()Ljava/util/List;", "getServices", "getSeenBy", "()Z", "ipSortKey", "", "getIpSortKey", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZZ)Lcom/mrnice/pixelscan/Device;", "equals", "other", "hashCode", "toString", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Device {
    private final String dnsName;
    private final String httpServer;
    private final String httpTitle;
    private final String ip;
    private final boolean isGateway;
    private final boolean isSelf;
    private final String kind;
    private final String mac;
    private final String mdnsName;
    private final String model;
    private final String name;
    private final String netbiosName;
    private final List<Integer> openPorts;
    private final Float rttMs;
    private final List<String> seenBy;
    private final List<String> services;
    private final String ssdpUuid;
    private final Integer ttl;
    private final String vendor;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ Device copy$default(Device device, String str, String str2, String str3, String str4, String str5, Float f, Integer num, String str6, String str7, String str8, String str9, String str10, String str11, String str12, List list, List list2, List list3, boolean z, boolean z2, int i, Object obj) {
        boolean z3;
        boolean z4;
        String str13 = (i & 1) != 0 ? device.ip : str;
        String str14 = (i & 2) != 0 ? device.name : str2;
        String str15 = (i & 4) != 0 ? device.kind : str3;
        String str16 = (i & 8) != 0 ? device.vendor : str4;
        String str17 = (i & 16) != 0 ? device.model : str5;
        Float f2 = (i & 32) != 0 ? device.rttMs : f;
        Integer num2 = (i & 64) != 0 ? device.ttl : num;
        String str18 = (i & 128) != 0 ? device.mac : str6;
        String str19 = (i & 256) != 0 ? device.mdnsName : str7;
        String str20 = (i & 512) != 0 ? device.netbiosName : str8;
        String str21 = (i & 1024) != 0 ? device.dnsName : str9;
        String str22 = (i & 2048) != 0 ? device.ssdpUuid : str10;
        String str23 = (i & 4096) != 0 ? device.httpServer : str11;
        String str24 = (i & 8192) != 0 ? device.httpTitle : str12;
        String str25 = str13;
        List list4 = (i & 16384) != 0 ? device.openPorts : list;
        List list5 = (i & 32768) != 0 ? device.services : list2;
        List list6 = (i & 65536) != 0 ? device.seenBy : list3;
        boolean z5 = (i & 131072) != 0 ? device.isSelf : z;
        if ((i & 262144) != 0) {
            z4 = z5;
            z3 = device.isGateway;
        } else {
            z3 = z2;
            z4 = z5;
        }
        return device.copy(str25, str14, str15, str16, str17, f2, num2, str18, str19, str20, str21, str22, str23, str24, list4, list5, list6, z4, z3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getIp() {
        return this.ip;
    }

    /* renamed from: component10, reason: from getter */
    public final String getNetbiosName() {
        return this.netbiosName;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDnsName() {
        return this.dnsName;
    }

    /* renamed from: component12, reason: from getter */
    public final String getSsdpUuid() {
        return this.ssdpUuid;
    }

    /* renamed from: component13, reason: from getter */
    public final String getHttpServer() {
        return this.httpServer;
    }

    /* renamed from: component14, reason: from getter */
    public final String getHttpTitle() {
        return this.httpTitle;
    }

    public final List<Integer> component15() {
        return this.openPorts;
    }

    public final List<String> component16() {
        return this.services;
    }

    public final List<String> component17() {
        return this.seenBy;
    }

    /* renamed from: component18, reason: from getter */
    public final boolean getIsSelf() {
        return this.isSelf;
    }

    /* renamed from: component19, reason: from getter */
    public final boolean getIsGateway() {
        return this.isGateway;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getKind() {
        return this.kind;
    }

    /* renamed from: component4, reason: from getter */
    public final String getVendor() {
        return this.vendor;
    }

    /* renamed from: component5, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* renamed from: component6, reason: from getter */
    public final Float getRttMs() {
        return this.rttMs;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getTtl() {
        return this.ttl;
    }

    /* renamed from: component8, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component9, reason: from getter */
    public final String getMdnsName() {
        return this.mdnsName;
    }

    public final Device copy(String ip, String name, String kind, String vendor, String model, Float rttMs, Integer ttl, String mac, String mdnsName, String netbiosName, String dnsName, String ssdpUuid, String httpServer, String httpTitle, List<Integer> openPorts, List<String> services, List<String> seenBy, boolean isSelf, boolean isGateway) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(openPorts, "openPorts");
        Intrinsics.checkNotNullParameter(services, "services");
        Intrinsics.checkNotNullParameter(seenBy, "seenBy");
        return new Device(ip, name, kind, vendor, model, rttMs, ttl, mac, mdnsName, netbiosName, dnsName, ssdpUuid, httpServer, httpTitle, openPorts, services, seenBy, isSelf, isGateway);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Device)) {
            return false;
        }
        Device device = (Device) other;
        return Intrinsics.areEqual(this.ip, device.ip) && Intrinsics.areEqual(this.name, device.name) && Intrinsics.areEqual(this.kind, device.kind) && Intrinsics.areEqual(this.vendor, device.vendor) && Intrinsics.areEqual(this.model, device.model) && Intrinsics.areEqual((Object) this.rttMs, (Object) device.rttMs) && Intrinsics.areEqual(this.ttl, device.ttl) && Intrinsics.areEqual(this.mac, device.mac) && Intrinsics.areEqual(this.mdnsName, device.mdnsName) && Intrinsics.areEqual(this.netbiosName, device.netbiosName) && Intrinsics.areEqual(this.dnsName, device.dnsName) && Intrinsics.areEqual(this.ssdpUuid, device.ssdpUuid) && Intrinsics.areEqual(this.httpServer, device.httpServer) && Intrinsics.areEqual(this.httpTitle, device.httpTitle) && Intrinsics.areEqual(this.openPorts, device.openPorts) && Intrinsics.areEqual(this.services, device.services) && Intrinsics.areEqual(this.seenBy, device.seenBy) && this.isSelf == device.isSelf && this.isGateway == device.isGateway;
    }

    public int hashCode() {
        int iHashCode = ((((this.ip.hashCode() * 31) + this.name.hashCode()) * 31) + this.kind.hashCode()) * 31;
        String str = this.vendor;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.model;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Float f = this.rttMs;
        int iHashCode4 = (iHashCode3 + (f == null ? 0 : f.hashCode())) * 31;
        Integer num = this.ttl;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.mac;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.mdnsName;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.netbiosName;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.dnsName;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.ssdpUuid;
        int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.httpServer;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.httpTitle;
        return ((((((((((iHashCode11 + (str9 != null ? str9.hashCode() : 0)) * 31) + this.openPorts.hashCode()) * 31) + this.services.hashCode()) * 31) + this.seenBy.hashCode()) * 31) + Boolean.hashCode(this.isSelf)) * 31) + Boolean.hashCode(this.isGateway);
    }

    public String toString() {
        return "Device(ip=" + this.ip + ", name=" + this.name + ", kind=" + this.kind + ", vendor=" + this.vendor + ", model=" + this.model + ", rttMs=" + this.rttMs + ", ttl=" + this.ttl + ", mac=" + this.mac + ", mdnsName=" + this.mdnsName + ", netbiosName=" + this.netbiosName + ", dnsName=" + this.dnsName + ", ssdpUuid=" + this.ssdpUuid + ", httpServer=" + this.httpServer + ", httpTitle=" + this.httpTitle + ", openPorts=" + this.openPorts + ", services=" + this.services + ", seenBy=" + this.seenBy + ", isSelf=" + this.isSelf + ", isGateway=" + this.isGateway + ")";
    }

    public Device(String ip, String name, String kind, String str, String str2, Float f, Integer num, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List<Integer> openPorts, List<String> services, List<String> seenBy, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(openPorts, "openPorts");
        Intrinsics.checkNotNullParameter(services, "services");
        Intrinsics.checkNotNullParameter(seenBy, "seenBy");
        this.ip = ip;
        this.name = name;
        this.kind = kind;
        this.vendor = str;
        this.model = str2;
        this.rttMs = f;
        this.ttl = num;
        this.mac = str3;
        this.mdnsName = str4;
        this.netbiosName = str5;
        this.dnsName = str6;
        this.ssdpUuid = str7;
        this.httpServer = str8;
        this.httpTitle = str9;
        this.openPorts = openPorts;
        this.services = services;
        this.seenBy = seenBy;
        this.isSelf = z;
        this.isGateway = z2;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getName() {
        return this.name;
    }

    public final String getKind() {
        return this.kind;
    }

    public final String getVendor() {
        return this.vendor;
    }

    public final String getModel() {
        return this.model;
    }

    public final Float getRttMs() {
        return this.rttMs;
    }

    public final Integer getTtl() {
        return this.ttl;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getMdnsName() {
        return this.mdnsName;
    }

    public final String getNetbiosName() {
        return this.netbiosName;
    }

    public final String getDnsName() {
        return this.dnsName;
    }

    public final String getSsdpUuid() {
        return this.ssdpUuid;
    }

    public final String getHttpServer() {
        return this.httpServer;
    }

    public final String getHttpTitle() {
        return this.httpTitle;
    }

    public final List<Integer> getOpenPorts() {
        return this.openPorts;
    }

    public final List<String> getServices() {
        return this.services;
    }

    public final List<String> getSeenBy() {
        return this.seenBy;
    }

    public final boolean isSelf() {
        return this.isSelf;
    }

    public final boolean isGateway() {
        return this.isGateway;
    }

    public final long getIpSortKey() {
        Iterator it = StringsKt.split$default((CharSequence) this.ip, new char[]{'.'}, false, 0, 6, (Object) null).iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            long j = jLongValue * 256;
            Long longOrNull = StringsKt.toLongOrNull((String) it.next());
            jLongValue = j + (longOrNull != null ? longOrNull.longValue() : 0L);
        }
        return jLongValue;
    }

    /* compiled from: Native.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/mrnice/pixelscan/Device$Companion;", "", "<init>", "()V", "parse", "Lcom/mrnice/pixelscan/Device;", "o", "Lorg/json/JSONObject;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Device parse(JSONObject o) {
            Intrinsics.checkNotNullParameter(o, "o");
            String strOptString = o.optString("ip");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String strOptString2 = o.optString(HintConstants.AUTOFILL_HINT_NAME);
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            String strOptString3 = o.optString("kind", EnvironmentCompat.MEDIA_UNKNOWN);
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
            return new Device(strOptString, strOptString2, strOptString3, NativeKt.optStringOrNull(o, "vendor"), NativeKt.optStringOrNull(o, "model"), o.isNull("rtt_ms") ? null : Float.valueOf((float) o.optDouble("rtt_ms")), o.isNull("ttl") ? null : Integer.valueOf(o.optInt("ttl")), NativeKt.optStringOrNull(o, "mac"), NativeKt.optStringOrNull(o, "mdns_name"), NativeKt.optStringOrNull(o, "netbios_name"), NativeKt.optStringOrNull(o, "dns_name"), NativeKt.optStringOrNull(o, "ssdp_uuid"), NativeKt.optStringOrNull(o, "http_server"), NativeKt.optStringOrNull(o, "http_title"), NativeKt.toIntList(o.optJSONArray("open_ports")), NativeKt.toStringList(o.optJSONArray("services")), NativeKt.toStringList(o.optJSONArray("seen_by")), o.optBoolean("is_self"), o.optBoolean("is_gateway"));
        }
    }
}
