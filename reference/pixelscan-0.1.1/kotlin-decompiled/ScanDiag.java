package com.mrnice.pixelscan;

import androidx.core.internal.view.SupportMenu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0087\b\u0018\u0000 C2\u00020\u0001:\u0001CB\u00ad\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0003J¯\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0001J\u0013\u0010?\u001a\u00020*2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020\u0003HÖ\u0001J\t\u0010B\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b+\u0010,¨\u0006D"}, d2 = {"Lcom/mrnice/pixelscan/ScanDiag;", "", "icmpSent", "", "icmpReplies", "icmpErrors", "tcpAttempts", "tcpOpen", "tcpRefused", "tcpTimeout", "tcpDenied", "tcpUnreachable", "udpSent", "udpErrors", "mdns", "netbios", "ssdp", "dns", "errors", "", "", "<init>", "(IIIIIIIIIIIIIIILjava/util/List;)V", "getIcmpSent", "()I", "getIcmpReplies", "getIcmpErrors", "getTcpAttempts", "getTcpOpen", "getTcpRefused", "getTcpTimeout", "getTcpDenied", "getTcpUnreachable", "getUdpSent", "getUdpErrors", "getMdns", "getNetbios", "getSsdp", "getDns", "getErrors", "()Ljava/util/List;", "lanLooksBlocked", "", "getLanLooksBlocked", "()Z", "lines", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "equals", "other", "hashCode", "toString", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ScanDiag {
    private final int dns;
    private final List<String> errors;
    private final int icmpErrors;
    private final int icmpReplies;
    private final int icmpSent;
    private final int mdns;
    private final int netbios;
    private final int ssdp;
    private final int tcpAttempts;
    private final int tcpDenied;
    private final int tcpOpen;
    private final int tcpRefused;
    private final int tcpTimeout;
    private final int tcpUnreachable;
    private final int udpErrors;
    private final int udpSent;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ScanDiag() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, SupportMenu.USER_MASK, null);
    }

    public static /* synthetic */ ScanDiag copy$default(ScanDiag scanDiag, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, List list, int i16, Object obj) {
        int i17 = (i16 & 1) != 0 ? scanDiag.icmpSent : i;
        return scanDiag.copy(i17, (i16 & 2) != 0 ? scanDiag.icmpReplies : i2, (i16 & 4) != 0 ? scanDiag.icmpErrors : i3, (i16 & 8) != 0 ? scanDiag.tcpAttempts : i4, (i16 & 16) != 0 ? scanDiag.tcpOpen : i5, (i16 & 32) != 0 ? scanDiag.tcpRefused : i6, (i16 & 64) != 0 ? scanDiag.tcpTimeout : i7, (i16 & 128) != 0 ? scanDiag.tcpDenied : i8, (i16 & 256) != 0 ? scanDiag.tcpUnreachable : i9, (i16 & 512) != 0 ? scanDiag.udpSent : i10, (i16 & 1024) != 0 ? scanDiag.udpErrors : i11, (i16 & 2048) != 0 ? scanDiag.mdns : i12, (i16 & 4096) != 0 ? scanDiag.netbios : i13, (i16 & 8192) != 0 ? scanDiag.ssdp : i14, (i16 & 16384) != 0 ? scanDiag.dns : i15, (i16 & 32768) != 0 ? scanDiag.errors : list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIcmpSent() {
        return this.icmpSent;
    }

    /* renamed from: component10, reason: from getter */
    public final int getUdpSent() {
        return this.udpSent;
    }

    /* renamed from: component11, reason: from getter */
    public final int getUdpErrors() {
        return this.udpErrors;
    }

    /* renamed from: component12, reason: from getter */
    public final int getMdns() {
        return this.mdns;
    }

    /* renamed from: component13, reason: from getter */
    public final int getNetbios() {
        return this.netbios;
    }

    /* renamed from: component14, reason: from getter */
    public final int getSsdp() {
        return this.ssdp;
    }

    /* renamed from: component15, reason: from getter */
    public final int getDns() {
        return this.dns;
    }

    public final List<String> component16() {
        return this.errors;
    }

    /* renamed from: component2, reason: from getter */
    public final int getIcmpReplies() {
        return this.icmpReplies;
    }

    /* renamed from: component3, reason: from getter */
    public final int getIcmpErrors() {
        return this.icmpErrors;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTcpAttempts() {
        return this.tcpAttempts;
    }

    /* renamed from: component5, reason: from getter */
    public final int getTcpOpen() {
        return this.tcpOpen;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTcpRefused() {
        return this.tcpRefused;
    }

    /* renamed from: component7, reason: from getter */
    public final int getTcpTimeout() {
        return this.tcpTimeout;
    }

    /* renamed from: component8, reason: from getter */
    public final int getTcpDenied() {
        return this.tcpDenied;
    }

    /* renamed from: component9, reason: from getter */
    public final int getTcpUnreachable() {
        return this.tcpUnreachable;
    }

    public final ScanDiag copy(int icmpSent, int icmpReplies, int icmpErrors, int tcpAttempts, int tcpOpen, int tcpRefused, int tcpTimeout, int tcpDenied, int tcpUnreachable, int udpSent, int udpErrors, int mdns, int netbios, int ssdp, int dns, List<String> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        return new ScanDiag(icmpSent, icmpReplies, icmpErrors, tcpAttempts, tcpOpen, tcpRefused, tcpTimeout, tcpDenied, tcpUnreachable, udpSent, udpErrors, mdns, netbios, ssdp, dns, errors);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScanDiag)) {
            return false;
        }
        ScanDiag scanDiag = (ScanDiag) other;
        return this.icmpSent == scanDiag.icmpSent && this.icmpReplies == scanDiag.icmpReplies && this.icmpErrors == scanDiag.icmpErrors && this.tcpAttempts == scanDiag.tcpAttempts && this.tcpOpen == scanDiag.tcpOpen && this.tcpRefused == scanDiag.tcpRefused && this.tcpTimeout == scanDiag.tcpTimeout && this.tcpDenied == scanDiag.tcpDenied && this.tcpUnreachable == scanDiag.tcpUnreachable && this.udpSent == scanDiag.udpSent && this.udpErrors == scanDiag.udpErrors && this.mdns == scanDiag.mdns && this.netbios == scanDiag.netbios && this.ssdp == scanDiag.ssdp && this.dns == scanDiag.dns && Intrinsics.areEqual(this.errors, scanDiag.errors);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((Integer.hashCode(this.icmpSent) * 31) + Integer.hashCode(this.icmpReplies)) * 31) + Integer.hashCode(this.icmpErrors)) * 31) + Integer.hashCode(this.tcpAttempts)) * 31) + Integer.hashCode(this.tcpOpen)) * 31) + Integer.hashCode(this.tcpRefused)) * 31) + Integer.hashCode(this.tcpTimeout)) * 31) + Integer.hashCode(this.tcpDenied)) * 31) + Integer.hashCode(this.tcpUnreachable)) * 31) + Integer.hashCode(this.udpSent)) * 31) + Integer.hashCode(this.udpErrors)) * 31) + Integer.hashCode(this.mdns)) * 31) + Integer.hashCode(this.netbios)) * 31) + Integer.hashCode(this.ssdp)) * 31) + Integer.hashCode(this.dns)) * 31) + this.errors.hashCode();
    }

    public String toString() {
        return "ScanDiag(icmpSent=" + this.icmpSent + ", icmpReplies=" + this.icmpReplies + ", icmpErrors=" + this.icmpErrors + ", tcpAttempts=" + this.tcpAttempts + ", tcpOpen=" + this.tcpOpen + ", tcpRefused=" + this.tcpRefused + ", tcpTimeout=" + this.tcpTimeout + ", tcpDenied=" + this.tcpDenied + ", tcpUnreachable=" + this.tcpUnreachable + ", udpSent=" + this.udpSent + ", udpErrors=" + this.udpErrors + ", mdns=" + this.mdns + ", netbios=" + this.netbios + ", ssdp=" + this.ssdp + ", dns=" + this.dns + ", errors=" + this.errors + ")";
    }

    public ScanDiag(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, List<String> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        this.icmpSent = i;
        this.icmpReplies = i2;
        this.icmpErrors = i3;
        this.tcpAttempts = i4;
        this.tcpOpen = i5;
        this.tcpRefused = i6;
        this.tcpTimeout = i7;
        this.tcpDenied = i8;
        this.tcpUnreachable = i9;
        this.udpSent = i10;
        this.udpErrors = i11;
        this.mdns = i12;
        this.netbios = i13;
        this.ssdp = i14;
        this.dns = i15;
        this.errors = errors;
    }

    public final int getIcmpErrors() {
        return this.icmpErrors;
    }

    public final int getIcmpReplies() {
        return this.icmpReplies;
    }

    public final int getIcmpSent() {
        return this.icmpSent;
    }

    public final int getTcpAttempts() {
        return this.tcpAttempts;
    }

    public final int getTcpOpen() {
        return this.tcpOpen;
    }

    public final int getTcpRefused() {
        return this.tcpRefused;
    }

    public final int getTcpTimeout() {
        return this.tcpTimeout;
    }

    public final int getTcpDenied() {
        return this.tcpDenied;
    }

    public final int getTcpUnreachable() {
        return this.tcpUnreachable;
    }

    public final int getUdpErrors() {
        return this.udpErrors;
    }

    public final int getUdpSent() {
        return this.udpSent;
    }

    public final int getDns() {
        return this.dns;
    }

    public final int getMdns() {
        return this.mdns;
    }

    public final int getNetbios() {
        return this.netbios;
    }

    public final int getSsdp() {
        return this.ssdp;
    }

    public /* synthetic */ ScanDiag(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, List list, int i16, DefaultConstructorMarker defaultConstructorMarker) {
        this((i16 & 1) != 0 ? 0 : i, (i16 & 2) != 0 ? 0 : i2, (i16 & 4) != 0 ? 0 : i3, (i16 & 8) != 0 ? 0 : i4, (i16 & 16) != 0 ? 0 : i5, (i16 & 32) != 0 ? 0 : i6, (i16 & 64) != 0 ? 0 : i7, (i16 & 128) != 0 ? 0 : i8, (i16 & 256) != 0 ? 0 : i9, (i16 & 512) != 0 ? 0 : i10, (i16 & 1024) != 0 ? 0 : i11, (i16 & 2048) != 0 ? 0 : i12, (i16 & 4096) != 0 ? 0 : i13, (i16 & 8192) != 0 ? 0 : i14, (i16 & 16384) != 0 ? 0 : i15, (i16 & 32768) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<String> getErrors() {
        return this.errors;
    }

    public final boolean getLanLooksBlocked() {
        return this.tcpAttempts > 0 && this.tcpOpen == 0 && this.tcpRefused == 0 && this.icmpReplies <= 1 && this.mdns == 0 && this.netbios == 0 && this.ssdp == 0;
    }

    public final List<String> lines() {
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"ping: " + this.icmpSent + " sent, " + this.icmpReplies + " replies, " + this.icmpErrors + " send errors", "tcp: " + this.tcpAttempts + " tries, " + this.tcpOpen + " open, " + this.tcpRefused + " refused, " + this.tcpTimeout + " timed out, " + this.tcpDenied + " denied, " + this.tcpUnreachable + " unreachable", "udp: " + this.udpSent + " sent, " + this.udpErrors + " errors", "replies: mdns " + this.mdns + " · netbios " + this.netbios + " · ssdp " + this.ssdp + " · dns " + this.dns});
        List<String> list = this.errors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add("error: " + ((String) it.next()));
        }
        return CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
    }

    /* compiled from: Native.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/mrnice/pixelscan/ScanDiag$Companion;", "", "<init>", "()V", "parse", "Lcom/mrnice/pixelscan/ScanDiag;", "o", "Lorg/json/JSONObject;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScanDiag parse(JSONObject o) {
            if (o == null) {
                return new ScanDiag(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, SupportMenu.USER_MASK, null);
            }
            return new ScanDiag(o.optInt("icmp_sent"), o.optInt("icmp_replies"), o.optInt("icmp_send_errors"), o.optInt("tcp_attempts"), o.optInt("tcp_open"), o.optInt("tcp_refused"), o.optInt("tcp_timeout"), o.optInt("tcp_denied"), o.optInt("tcp_unreachable"), o.optInt("udp_sent"), o.optInt("udp_send_errors"), o.optInt("replies_mdns"), o.optInt("replies_netbios"), o.optInt("replies_ssdp"), o.optInt("replies_dns"), NativeKt.toStringList(o.optJSONArray("errors")));
        }
    }
}
