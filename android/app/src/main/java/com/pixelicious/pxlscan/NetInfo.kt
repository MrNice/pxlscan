package com.pixelicious.pxlscan

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import java.net.Inet4Address

/** The Wi-Fi or Ethernet network the phone is on. */
data class LanInfo(
    val ip: String,
    val prefixLen: Int,
    val gateway: String?,
    val dnsServers: List<String>,
    val interfaceName: String?,
    val transport: String,
    val vpnActive: Boolean,
    val network: Network?,
) {
    /** "a.b.c.0/24" style label (the host octet is zeroed even for non-/24 prefixes). */
    val subnetLabel: String
        get() {
            val parts = ip.split('.')
            return if (parts.size == 4) "${parts[0]}.${parts[1]}.${parts[2]}.0/$prefixLen" else "$ip/$prefixLen"
        }
}

object NetInfo {
    /** Finds the active Wi-Fi/Ethernet network with an IPv4 address; null when there is none. */
    fun current(ctx: Context): LanInfo? {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var vpn = false
        var best: Triple<Network, LinkProperties, String>? = null
        for (network in cm.allNetworks) {
            val caps = cm.getNetworkCapabilities(network) ?: continue
            val transport = when {
                caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                    vpn = true
                    continue
                }
                caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "wifi"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "ethernet"
                else -> continue
            }
            val lp = cm.getLinkProperties(network) ?: continue
            if (ipv4Of(lp) != null && (best == null || transport == "ethernet")) {
                best = Triple(network, lp, transport)
            }
        }
        val (network, lp, transport) = best ?: return null
        val (ip, prefix) = ipv4Of(lp) ?: return null
        val gateway = lp.routes
            .firstOrNull { it.isDefaultRoute && it.gateway is Inet4Address }
            ?.gateway?.hostAddress
            ?: guessGateway(ip)
        val dns = lp.dnsServers.filterIsInstance<Inet4Address>().mapNotNull { it.hostAddress }
        return LanInfo(ip, prefix, gateway, dns, lp.interfaceName, transport, vpn, network)
    }

    /** First usable IPv4 address (not loopback, not link-local) and its prefix length. */
    private fun ipv4Of(lp: LinkProperties): Pair<String, Int>? {
        for (la in lp.linkAddresses) {
            val a = la.address
            if (a is Inet4Address && !a.isLoopbackAddress && !a.isLinkLocalAddress) {
                a.hostAddress?.let { return it to la.prefixLength }
            }
        }
        return null
    }

    private fun guessGateway(ip: String): String? {
        val p = ip.split('.')
        return if (p.size == 4) "${p[0]}.${p[1]}.${p[2]}.1" else null
    }

    /** Routes this process's traffic over the LAN network so scans do not leak onto a VPN. */
    fun bindTo(ctx: Context, lan: LanInfo): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = lan.network ?: return false
        return runCatching { cm.bindProcessToNetwork(network) }.getOrDefault(false)
    }

    fun unbind(ctx: Context) {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        runCatching { cm.bindProcessToNetwork(null) }
    }

    /** State of the Android 17 local-network permission; implicit on older versions. */
    fun localNetworkPermission(ctx: Context): String = when {
        Build.VERSION.SDK_INT < 37 -> "n/a (implicit)"
        ctx.checkSelfPermission("android.permission.ACCESS_LOCAL_NETWORK") == 0 -> "granted"
        else -> "denied"
    }
}
