package com.mrnice.pixelscan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.mrnice.pixelscan.Store;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Monitor.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u000b\u001a\u00020\fJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\u0018\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/mrnice/pixelscan/Monitor;", "", "<init>", "()V", "JOB_ID", "", "CH_NEW", "", "CH_WATCH", "schedule", "", "ctx", "Landroid/content/Context;", "ensureChannels", "runOnce", "", "Lcom/mrnice/pixelscan/NetEvent;", "isWatched", "", "store", "Lcom/mrnice/pixelscan/Store;", "devicesJson", "id", "openAppIntent", "Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "deviceToJson", "Lorg/json/JSONObject;", "d", "Lcom/mrnice/pixelscan/Device;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Monitor {
    public static final int $stable = 0;
    private static final String CH_NEW = "new_devices";
    private static final String CH_WATCH = "watched_movement";
    public static final Monitor INSTANCE = new Monitor();
    public static final int JOB_ID = 4711;

    private Monitor() {
    }

    public final void schedule(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Settings settings = new Settings(ctx);
        Object systemService = ctx.getSystemService("jobscheduler");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
        JobScheduler jobScheduler = (JobScheduler) systemService;
        jobScheduler.cancel(JOB_ID);
        if (settings.getMonitorEnabled()) {
            jobScheduler.schedule(new JobInfo.Builder(JOB_ID, new ComponentName(ctx, (Class<?>) MonitorJobService.class)).setRequiredNetworkType(2).setPersisted(true).setPeriodic(RangesKt.coerceIn(settings.getIntervalMinutes(), 15, 720) * 60000).build());
        }
    }

    public final void ensureChannels(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Object systemService = ctx.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        NotificationChannel notificationChannel = new NotificationChannel(CH_NEW, "New devices", 4);
        notificationChannel.setDescription("A device you haven't seen before joined the network");
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationChannel notificationChannel2 = new NotificationChannel(CH_WATCH, "Watched devices", 3);
        notificationChannel2.setDescription("A starred device joined or left the network");
        notificationManager.createNotificationChannel(notificationChannel2);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01dd A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<NetEvent> runOnce(Context ctx) throws JSONException {
        Pair pair;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        LanInfo lanInfoCurrent = NetInfo.INSTANCE.current(ctx);
        if (lanInfoCurrent != null && Native.INSTANCE.ensureLoaded()) {
            Store store = new Store(ctx, Store.INSTANCE.networkKey(lanInfoCurrent));
            Settings settings = new Settings(ctx);
            ScanConfig scanConfig = new ScanConfig(lanInfoCurrent.getIp(), lanInfoCurrent.getPrefixLen(), lanInfoCurrent.getGateway(), lanInfoCurrent.getDnsServers(), 700L, Build.MODEL);
            NetInfo.INSTANCE.bindTo(ctx, lanInfoCurrent);
            try {
                long jStart = Native.INSTANCE.start(scanConfig);
                if (jStart < 0) {
                    return CollectionsKt.emptyList();
                }
                ScanSnapshot scanSnapshotSnapshot = Native.INSTANCE.snapshot(jStart);
                long jCurrentTimeMillis = System.currentTimeMillis() + AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH;
                while (!scanSnapshotSnapshot.getDone() && System.currentTimeMillis() < jCurrentTimeMillis) {
                    Thread.sleep(300L);
                    scanSnapshotSnapshot = Native.INSTANCE.snapshot(jStart);
                }
                Native.INSTANCE.stop(jStart);
                NetInfo.INSTANCE.unbind(ctx);
                if (scanSnapshotSnapshot.getDiag().getLanLooksBlocked()) {
                    return CollectionsKt.emptyList();
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("known", new JSONArray(store.knownDevicesJson()));
                List<Device> devices = scanSnapshotSnapshot.getDevices();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(devices, 10));
                Iterator<T> it = devices.iterator();
                while (it.hasNext()) {
                    arrayList.add(INSTANCE.deviceToJson((Device) it.next()));
                }
                jSONObject.put("scan", new JSONArray((Collection) arrayList));
                jSONObject.put("leave_after", 2);
                jSONObject.put("alert_all_new", settings.getAlertAllNew());
                Native r0 = Native.INSTANCE;
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                MonitorOutput monitorOutputDiff = r0.diff(string);
                store.saveKnownDevices(monitorOutputDiff.getDevicesJson());
                Store.appendEvents$default(store, monitorOutputDiff.getEvents(), 0L, 2, null);
                Store.recordPresence$default(store, monitorOutputDiff.getOnlineIds(), 0L, 2, null);
                INSTANCE.ensureChannels(ctx);
                NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(ctx);
                Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
                if (Build.VERSION.SDK_INT >= 33 && ctx.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
                    return monitorOutputDiff.getEvents();
                }
                for (NetEvent netEvent : monitorOutputDiff.getEvents()) {
                    String type = netEvent.getType();
                    int iHashCode = type.hashCode();
                    if (iHashCode != -1154529463) {
                        if (iHashCode != 108960) {
                            if (iHashCode == 3317767 && type.equals("left") && settings.getAlertWatchedMovement()) {
                                pair = TuplesKt.to(CH_WATCH, "Device left");
                                String str = (String) pair.component1();
                                String str2 = (String) pair.component2();
                                if (Intrinsics.areEqual(netEvent.getType(), "new") || isWatched(store, monitorOutputDiff.getDevicesJson(), netEvent.getId())) {
                                    Notification notificationBuild = new NotificationCompat.Builder(ctx, str).setSmallIcon(android.R.drawable.stat_notify_more).setContentTitle(str2).setContentText(netEvent.getName() + " · " + netEvent.getIp()).setColor(SupportMenu.CATEGORY_MASK).setAutoCancel(true).setContentIntent(openAppIntent(ctx)).build();
                                    Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
                                    try {
                                        Result.Companion companion = Result.INSTANCE;
                                        Monitor monitor = this;
                                        notificationManagerCompatFrom.notify(netEvent.getId().hashCode(), notificationBuild);
                                        Result.m6658constructorimpl(Unit.INSTANCE);
                                    } catch (Throwable th) {
                                        Result.Companion companion2 = Result.INSTANCE;
                                        Result.m6658constructorimpl(ResultKt.createFailure(th));
                                    }
                                }
                            }
                        } else if (type.equals("new")) {
                            pair = TuplesKt.to(CH_NEW, "New device on your network");
                            String str3 = (String) pair.component1();
                            String str22 = (String) pair.component2();
                            if (Intrinsics.areEqual(netEvent.getType(), "new")) {
                            }
                            Notification notificationBuild2 = new NotificationCompat.Builder(ctx, str3).setSmallIcon(android.R.drawable.stat_notify_more).setContentTitle(str22).setContentText(netEvent.getName() + " · " + netEvent.getIp()).setColor(SupportMenu.CATEGORY_MASK).setAutoCancel(true).setContentIntent(openAppIntent(ctx)).build();
                            Intrinsics.checkNotNullExpressionValue(notificationBuild2, "build(...)");
                            Result.Companion companion3 = Result.INSTANCE;
                            Monitor monitor2 = this;
                            notificationManagerCompatFrom.notify(netEvent.getId().hashCode(), notificationBuild2);
                            Result.m6658constructorimpl(Unit.INSTANCE);
                        }
                    } else if (type.equals("joined") && settings.getAlertWatchedMovement()) {
                        pair = TuplesKt.to(CH_WATCH, "Device joined");
                        String str32 = (String) pair.component1();
                        String str222 = (String) pair.component2();
                        if (Intrinsics.areEqual(netEvent.getType(), "new")) {
                        }
                        Notification notificationBuild22 = new NotificationCompat.Builder(ctx, str32).setSmallIcon(android.R.drawable.stat_notify_more).setContentTitle(str222).setContentText(netEvent.getName() + " · " + netEvent.getIp()).setColor(SupportMenu.CATEGORY_MASK).setAutoCancel(true).setContentIntent(openAppIntent(ctx)).build();
                        Intrinsics.checkNotNullExpressionValue(notificationBuild22, "build(...)");
                        Result.Companion companion32 = Result.INSTANCE;
                        Monitor monitor22 = this;
                        notificationManagerCompatFrom.notify(netEvent.getId().hashCode(), notificationBuild22);
                        Result.m6658constructorimpl(Unit.INSTANCE);
                    }
                }
                return monitorOutputDiff.getEvents();
            } finally {
                NetInfo.INSTANCE.unbind(ctx);
            }
        }
        return CollectionsKt.emptyList();
    }

    private final boolean isWatched(Store store, String devicesJson, String id) {
        Object objM6658constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Monitor monitor = this;
            objM6658constructorimpl = Result.m6658constructorimpl(new JSONArray(devicesJson));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6658constructorimpl = Result.m6658constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6664isFailureimpl(objM6658constructorimpl)) {
            objM6658constructorimpl = null;
        }
        JSONArray jSONArray = (JSONArray) objM6658constructorimpl;
        if (jSONArray == null) {
            return false;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && Intrinsics.areEqual(jSONObjectOptJSONObject.optString("id"), id)) {
                Store.Companion companion3 = Store.INSTANCE;
                String strOptStringOrNull = NativeKt.optStringOrNull(jSONObjectOptJSONObject, "mac");
                String strOptStringOrNull2 = NativeKt.optStringOrNull(jSONObjectOptJSONObject, "ssdp_uuid");
                String strOptStringOrNull3 = NativeKt.optStringOrNull(jSONObjectOptJSONObject, "mdns_name");
                String strOptStringOrNull4 = NativeKt.optStringOrNull(jSONObjectOptJSONObject, "netbios_name");
                String strOptStringOrNull5 = NativeKt.optStringOrNull(jSONObjectOptJSONObject, "dns_name");
                String strOptString = jSONObjectOptJSONObject.optString("last_ip");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                JSONObject jSONObjectOptJSONObject2 = store.overrides().optJSONObject(companion3.keyOf(strOptStringOrNull, strOptStringOrNull2, strOptStringOrNull3, strOptStringOrNull4, strOptStringOrNull5, strOptString));
                return jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.optBoolean("watched");
            }
        }
        return false;
    }

    private final PendingIntent openAppIntent(Context ctx) {
        return PendingIntent.getActivity(ctx, 0, new Intent(ctx, (Class<?>) MainActivity.class).addFlags(536870912), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
    }

    private final JSONObject deviceToJson(Device d) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ip", d.getIp());
        jSONObject.put(HintConstants.AUTOFILL_HINT_NAME, d.getName());
        jSONObject.put("kind", d.getKind());
        jSONObject.put("seen_by", new JSONArray((Collection) d.getSeenBy()));
        String mac = d.getMac();
        if (mac != null) {
            jSONObject.put("mac", mac);
        }
        String mdnsName = d.getMdnsName();
        if (mdnsName != null) {
            jSONObject.put("mdns_name", mdnsName);
        }
        String netbiosName = d.getNetbiosName();
        if (netbiosName != null) {
            jSONObject.put("netbios_name", netbiosName);
        }
        String dnsName = d.getDnsName();
        if (dnsName != null) {
            jSONObject.put("dns_name", dnsName);
        }
        String ssdpUuid = d.getSsdpUuid();
        if (ssdpUuid != null) {
            jSONObject.put("ssdp_uuid", ssdpUuid);
        }
        jSONObject.put("is_self", d.isSelf());
        jSONObject.put("is_gateway", d.isGateway());
        Object vendor = d.getVendor();
        if (vendor == null) {
            vendor = JSONObject.NULL;
        }
        jSONObject.put("vendor", vendor);
        jSONObject.put("open_ports", new JSONArray((Collection) d.getOpenPorts()));
        jSONObject.put("services", new JSONArray((Collection) d.getServices()));
        return jSONObject;
    }
}
