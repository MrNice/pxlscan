package com.mrnice.pixelscan;

import androidx.autofill.HintConstants;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CastKt;
import androidx.compose.material.icons.filled.DeviceUnknownKt;
import androidx.compose.material.icons.filled.PrintKt;
import androidx.compose.material.icons.filled.RouterKt;
import androidx.compose.material.icons.filled.SportsEsportsKt;
import androidx.compose.material.icons.filled.StorageKt;
import androidx.compose.material.icons.filled.VideocamKt;
import androidx.compose.material.icons.outlined.LaptopKt;
import androidx.compose.material.icons.outlined.LightbulbKt;
import androidx.compose.material.icons.outlined.SmartphoneKt;
import androidx.compose.material.icons.outlined.SpeakerKt;
import androidx.compose.material.icons.outlined.TabletKt;
import androidx.compose.material.icons.outlined.TvKt;
import androidx.compose.material.icons.outlined.WatchKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.core.os.EnvironmentCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Kinds.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0005R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/mrnice/pixelscan/Kinds;", "", "<init>", "()V", "label", "", "kind", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "order", "", "getOrder", "()Ljava/util/List;", "rank", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Kinds {
    public static final Kinds INSTANCE = new Kinds();
    private static final List<String> order = CollectionsKt.listOf((Object[]) new String[]{"router", "computer", "server", HintConstants.AUTOFILL_HINT_PHONE, "tablet", "tv", "speaker", "console", "printer", "camera", "nas", "iot", "watch", "network", EnvironmentCompat.MEDIA_UNKNOWN});
    public static final int $stable = 8;

    private Kinds() {
    }

    public final String label(String kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        switch (kind.hashCode()) {
            case -2008522753:
                return !kind.equals("speaker") ? "Unknown" : "Speaker";
            case -1367751899:
                return !kind.equals("camera") ? "Unknown" : "Camera";
            case -925132983:
                return !kind.equals("router") ? "Unknown" : "Router";
            case -905826493:
                return !kind.equals("server") ? "Unknown" : "Server / Pi";
            case -881377690:
                return !kind.equals("tablet") ? "Unknown" : "Tablet";
            case -599163109:
                return !kind.equals("computer") ? "Unknown" : "Computer";
            case -314718182:
                return !kind.equals("printer") ? "Unknown" : "Printer";
            case 3714:
                return !kind.equals("tv") ? "Unknown" : "TV / Streamer";
            case 104462:
                return !kind.equals("iot") ? "Unknown" : "Smart home";
            case 108832:
                return !kind.equals("nas") ? "Unknown" : "NAS / Storage";
            case 106642798:
                return !kind.equals(HintConstants.AUTOFILL_HINT_PHONE) ? "Unknown" : "Phone";
            case 112903375:
                return !kind.equals("watch") ? "Unknown" : "Watch";
            case 951510359:
                return !kind.equals("console") ? "Unknown" : "Game console";
            case 1843485230:
                return !kind.equals("network") ? "Unknown" : "Network gear";
            default:
                return "Unknown";
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    public final ImageVector icon(String kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        switch (kind.hashCode()) {
            case -2008522753:
                if (kind.equals("speaker")) {
                    return SpeakerKt.getSpeaker(Icons.Outlined.INSTANCE);
                }
                break;
            case -1367751899:
                if (kind.equals("camera")) {
                    return VideocamKt.getVideocam(Icons.Filled.INSTANCE);
                }
                break;
            case -925132983:
                if (kind.equals("router")) {
                    return RouterKt.getRouter(Icons.Filled.INSTANCE);
                }
                break;
            case -905826493:
                if (kind.equals("server")) {
                    return StorageKt.getStorage(Icons.Filled.INSTANCE);
                }
                break;
            case -881377690:
                if (kind.equals("tablet")) {
                    return TabletKt.getTablet(Icons.Outlined.INSTANCE);
                }
                break;
            case -599163109:
                if (kind.equals("computer")) {
                    return LaptopKt.getLaptop(Icons.Outlined.INSTANCE);
                }
                break;
            case -314718182:
                if (kind.equals("printer")) {
                    return PrintKt.getPrint(Icons.Filled.INSTANCE);
                }
                break;
            case 3714:
                if (kind.equals("tv")) {
                    return TvKt.getTv(Icons.Outlined.INSTANCE);
                }
                break;
            case 104462:
                if (kind.equals("iot")) {
                    return LightbulbKt.getLightbulb(Icons.Outlined.INSTANCE);
                }
                break;
            case 108832:
                if (kind.equals("nas")) {
                    return StorageKt.getStorage(Icons.Filled.INSTANCE);
                }
                break;
            case 35543321:
                if (kind.equals("castable")) {
                    return CastKt.getCast(Icons.Filled.INSTANCE);
                }
                break;
            case 106642798:
                if (kind.equals(HintConstants.AUTOFILL_HINT_PHONE)) {
                    return SmartphoneKt.getSmartphone(Icons.Outlined.INSTANCE);
                }
                break;
            case 112903375:
                if (kind.equals("watch")) {
                    return WatchKt.getWatch(Icons.Outlined.INSTANCE);
                }
                break;
            case 951510359:
                if (kind.equals("console")) {
                    return SportsEsportsKt.getSportsEsports(Icons.Filled.INSTANCE);
                }
                break;
            case 1843485230:
                if (kind.equals("network")) {
                    return RouterKt.getRouter(Icons.Filled.INSTANCE);
                }
                break;
        }
        return DeviceUnknownKt.getDeviceUnknown(Icons.Filled.INSTANCE);
    }

    public final List<String> getOrder() {
        return order;
    }

    public final int rank(String kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        List<String> list = order;
        int iIndexOf = list.indexOf(kind);
        return iIndexOf < 0 ? list.size() : iIndexOf;
    }
}
