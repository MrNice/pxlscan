package com.mrnice.pixelscan;

import android.net.Network;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NetInfo.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u000eHÆ\u0003Je\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0005HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0012¨\u0006-"}, d2 = {"Lcom/mrnice/pixelscan/LanInfo;", "", "ip", "", "prefixLen", "", "gateway", "dnsServers", "", "interfaceName", NotificationCompat.CATEGORY_TRANSPORT, "vpnActive", "", "network", "Landroid/net/Network;", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLandroid/net/Network;)V", "getIp", "()Ljava/lang/String;", "getPrefixLen", "()I", "getGateway", "getDnsServers", "()Ljava/util/List;", "getInterfaceName", "getTransport", "getVpnActive", "()Z", "getNetwork", "()Landroid/net/Network;", "subnetLabel", "getSubnetLabel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LanInfo {
    public static final int $stable = 8;
    private final List<String> dnsServers;
    private final String gateway;
    private final String interfaceName;
    private final String ip;
    private final Network network;
    private final int prefixLen;
    private final String transport;
    private final boolean vpnActive;

    public static /* synthetic */ LanInfo copy$default(LanInfo lanInfo, String str, int i, String str2, List list, String str3, String str4, boolean z, Network network, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = lanInfo.ip;
        }
        if ((i2 & 2) != 0) {
            i = lanInfo.prefixLen;
        }
        if ((i2 & 4) != 0) {
            str2 = lanInfo.gateway;
        }
        if ((i2 & 8) != 0) {
            list = lanInfo.dnsServers;
        }
        if ((i2 & 16) != 0) {
            str3 = lanInfo.interfaceName;
        }
        if ((i2 & 32) != 0) {
            str4 = lanInfo.transport;
        }
        if ((i2 & 64) != 0) {
            z = lanInfo.vpnActive;
        }
        if ((i2 & 128) != 0) {
            network = lanInfo.network;
        }
        boolean z2 = z;
        Network network2 = network;
        String str5 = str3;
        String str6 = str4;
        return lanInfo.copy(str, i, str2, list, str5, str6, z2, network2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getIp() {
        return this.ip;
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
    public final String getInterfaceName() {
        return this.interfaceName;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTransport() {
        return this.transport;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getVpnActive() {
        return this.vpnActive;
    }

    /* renamed from: component8, reason: from getter */
    public final Network getNetwork() {
        return this.network;
    }

    public final LanInfo copy(String ip, int prefixLen, String gateway, List<String> dnsServers, String interfaceName, String transport, boolean vpnActive, Network network) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(dnsServers, "dnsServers");
        Intrinsics.checkNotNullParameter(transport, "transport");
        return new LanInfo(ip, prefixLen, gateway, dnsServers, interfaceName, transport, vpnActive, network);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LanInfo)) {
            return false;
        }
        LanInfo lanInfo = (LanInfo) other;
        return Intrinsics.areEqual(this.ip, lanInfo.ip) && this.prefixLen == lanInfo.prefixLen && Intrinsics.areEqual(this.gateway, lanInfo.gateway) && Intrinsics.areEqual(this.dnsServers, lanInfo.dnsServers) && Intrinsics.areEqual(this.interfaceName, lanInfo.interfaceName) && Intrinsics.areEqual(this.transport, lanInfo.transport) && this.vpnActive == lanInfo.vpnActive && Intrinsics.areEqual(this.network, lanInfo.network);
    }

    public int hashCode() {
        int iHashCode = ((this.ip.hashCode() * 31) + Integer.hashCode(this.prefixLen)) * 31;
        String str = this.gateway;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.dnsServers.hashCode()) * 31;
        String str2 = this.interfaceName;
        int iHashCode3 = (((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.transport.hashCode()) * 31) + Boolean.hashCode(this.vpnActive)) * 31;
        Network network = this.network;
        return iHashCode3 + (network != null ? network.hashCode() : 0);
    }

    public String toString() {
        return "LanInfo(ip=" + this.ip + ", prefixLen=" + this.prefixLen + ", gateway=" + this.gateway + ", dnsServers=" + this.dnsServers + ", interfaceName=" + this.interfaceName + ", transport=" + this.transport + ", vpnActive=" + this.vpnActive + ", network=" + this.network + ")";
    }

    public LanInfo(String ip, int i, String str, List<String> dnsServers, String str2, String transport, boolean z, Network network) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(dnsServers, "dnsServers");
        Intrinsics.checkNotNullParameter(transport, "transport");
        this.ip = ip;
        this.prefixLen = i;
        this.gateway = str;
        this.dnsServers = dnsServers;
        this.interfaceName = str2;
        this.transport = transport;
        this.vpnActive = z;
        this.network = network;
    }

    public final String getIp() {
        return this.ip;
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

    public final String getInterfaceName() {
        return this.interfaceName;
    }

    public final String getTransport() {
        return this.transport;
    }

    public final boolean getVpnActive() {
        return this.vpnActive;
    }

    public final Network getNetwork() {
        return this.network;
    }

    public final String getSubnetLabel() {
        StringBuilder sbAppend;
        List listSplit$default = StringsKt.split$default((CharSequence) this.ip, new char[]{'.'}, false, 0, 6, (Object) null);
        if (listSplit$default.size() == 4) {
            Object obj = listSplit$default.get(0);
            Object obj2 = listSplit$default.get(1);
            sbAppend = new StringBuilder().append(obj).append(".").append(obj2).append(".").append(listSplit$default.get(2)).append(".0/").append(this.prefixLen);
        } else {
            sbAppend = new StringBuilder().append(this.ip).append("/").append(this.prefixLen);
        }
        return sbAppend.toString();
    }
}
