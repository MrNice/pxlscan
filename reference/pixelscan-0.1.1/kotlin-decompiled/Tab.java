package com.mrnice.pixelscan;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.NotificationsKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material.icons.filled.WifiKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/mrnice/pixelscan/Tab;", "", "label", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "<init>", "(Ljava/lang/String;ILjava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;)V", "getLabel", "()Ljava/lang/String;", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "Devices", "Monitor", "Settings", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
final class Tab {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Tab[] $VALUES;
    public static final Tab Devices = new Tab("Devices", 0, "Devices", WifiKt.getWifi(Icons.Filled.INSTANCE));
    public static final Tab Monitor = new Tab("Monitor", 1, "Monitor", NotificationsKt.getNotifications(Icons.Filled.INSTANCE));
    public static final Tab Settings = new Tab("Settings", 2, "Settings", SettingsKt.getSettings(Icons.Filled.INSTANCE));
    private final ImageVector icon;
    private final String label;

    private static final /* synthetic */ Tab[] $values() {
        return new Tab[]{Devices, Monitor, Settings};
    }

    public static EnumEntries<Tab> getEntries() {
        return $ENTRIES;
    }

    public static Tab valueOf(String str) {
        return (Tab) Enum.valueOf(Tab.class, str);
    }

    public static Tab[] values() {
        return (Tab[]) $VALUES.clone();
    }

    private Tab(String str, int i, String str2, ImageVector imageVector) {
        this.label = str2;
        this.icon = imageVector;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final String getLabel() {
        return this.label;
    }

    static {
        Tab[] tabArr$values = $values();
        $VALUES = tabArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(tabArr$values);
    }
}
