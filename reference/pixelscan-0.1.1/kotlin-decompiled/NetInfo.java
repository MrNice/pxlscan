package com.mrnice.pixelscan;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.RouteInfo;
import android.os.Build;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NetInfo.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001e\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000f\u001a\u00020\nH\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u0016"}, d2 = {"Lcom/mrnice/pixelscan/NetInfo;", "", "<init>", "()V", "current", "Lcom/mrnice/pixelscan/LanInfo;", "ctx", "Landroid/content/Context;", "ipv4Of", "Lkotlin/Pair;", "", "", "lp", "Landroid/net/LinkProperties;", "guessGateway", "ip", "bindTo", "", "lan", "unbind", "", "localNetworkPermission", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetInfo {
    public static final int $stable = 0;
    public static final NetInfo INSTANCE = new NetInfo();

    private NetInfo() {
    }

    public final LanInfo current(Context ctx) {
        String strGuessGateway;
        InetAddress gateway;
        String str;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Object systemService = ctx.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network[] allNetworks = connectivityManager.getAllNetworks();
        Intrinsics.checkNotNullExpressionValue(allNetworks, "getAllNetworks(...)");
        Object obj = null;
        boolean z = false;
        Triple triple = null;
        for (Network network : allNetworks) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(4)) {
                    z = true;
                } else {
                    if (networkCapabilities.hasTransport(1)) {
                        str = "wifi";
                    } else if (networkCapabilities.hasTransport(3)) {
                        str = "ethernet";
                    }
                    LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                    if (linkProperties != null && ipv4Of(linkProperties) != null && (triple == null || Intrinsics.areEqual(str, "ethernet"))) {
                        triple = new Triple(network, linkProperties, str);
                    }
                }
            }
        }
        if (triple == null) {
            return null;
        }
        Network network2 = (Network) triple.component1();
        LinkProperties linkProperties2 = (LinkProperties) triple.component2();
        String str2 = (String) triple.component3();
        Pair<String, Integer> pairIpv4Of = ipv4Of(linkProperties2);
        if (pairIpv4Of == null) {
            return null;
        }
        String first = pairIpv4Of.getFirst();
        List<RouteInfo> routes = linkProperties2.getRoutes();
        Intrinsics.checkNotNullExpressionValue(routes, "getRoutes(...)");
        Iterator<T> it = routes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            RouteInfo routeInfo = (RouteInfo) next;
            if (routeInfo.isDefaultRoute() && (routeInfo.getGateway() instanceof Inet4Address)) {
                obj = next;
                break;
            }
        }
        RouteInfo routeInfo2 = (RouteInfo) obj;
        if (routeInfo2 == null || (gateway = routeInfo2.getGateway()) == null || (strGuessGateway = gateway.getHostAddress()) == null) {
            strGuessGateway = guessGateway(first);
        }
        String str3 = strGuessGateway;
        List<InetAddress> dnsServers = linkProperties2.getDnsServers();
        Intrinsics.checkNotNullExpressionValue(dnsServers, "getDnsServers(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : dnsServers) {
            if (obj2 instanceof Inet4Address) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String hostAddress = ((Inet4Address) it2.next()).getHostAddress();
            if (hostAddress != null) {
                arrayList2.add(hostAddress);
            }
        }
        return new LanInfo(first, pairIpv4Of.getSecond().intValue(), str3, arrayList2, linkProperties2.getInterfaceName(), str2, z, network2);
    }

    private final Pair<String, Integer> ipv4Of(LinkProperties lp) {
        String hostAddress;
        for (LinkAddress linkAddress : lp.getLinkAddresses()) {
            InetAddress address = linkAddress.getAddress();
            if (address instanceof Inet4Address) {
                Inet4Address inet4Address = (Inet4Address) address;
                if (!inet4Address.isLoopbackAddress() && !inet4Address.isLinkLocalAddress() && (hostAddress = inet4Address.getHostAddress()) != null) {
                    return TuplesKt.to(hostAddress, Integer.valueOf(linkAddress.getPrefixLength()));
                }
            }
        }
        return null;
    }

    private final String guessGateway(String ip) {
        List listSplit$default = StringsKt.split$default((CharSequence) ip, new char[]{'.'}, false, 0, 6, (Object) null);
        if (listSplit$default.size() != 4) {
            return null;
        }
        return listSplit$default.get(0) + "." + listSplit$default.get(1) + "." + listSplit$default.get(2) + ".1";
    }

    public final boolean bindTo(Context ctx, LanInfo lan) {
        Object objM6658constructorimpl;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(lan, "lan");
        Object systemService = ctx.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network network = lan.getNetwork();
        if (network == null) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM6658constructorimpl = Result.m6658constructorimpl(Boolean.valueOf(connectivityManager.bindProcessToNetwork(network)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6658constructorimpl = Result.m6658constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6664isFailureimpl(objM6658constructorimpl)) {
            objM6658constructorimpl = false;
        }
        return ((Boolean) objM6658constructorimpl).booleanValue();
    }

    public final void unbind(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Object systemService = ctx.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        try {
            Result.Companion companion = Result.INSTANCE;
            NetInfo netInfo = this;
            Result.m6658constructorimpl(Boolean.valueOf(connectivityManager.bindProcessToNetwork(null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6658constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final String localNetworkPermission(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return Build.VERSION.SDK_INT < 37 ? "n/a (implicit)" : ctx.checkSelfPermission("android.permission.ACCESS_LOCAL_NETWORK") == 0 ? "granted" : "denied";
    }
}
