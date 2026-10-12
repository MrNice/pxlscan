package com.mrnice.pixelscan;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScanViewModel.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00152\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017¨\u0006\""}, d2 = {"Lcom/mrnice/pixelscan/Row;", "", "device", "Lcom/mrnice/pixelscan/Device;", "override", "Lcom/mrnice/pixelscan/DeviceOverride;", "<init>", "(Lcom/mrnice/pixelscan/Device;Lcom/mrnice/pixelscan/DeviceOverride;)V", "getDevice", "()Lcom/mrnice/pixelscan/Device;", "getOverride", "()Lcom/mrnice/pixelscan/DeviceOverride;", "id", "", "getId", "()Ljava/lang/String;", "displayName", "getDisplayName", "kind", "getKind", "watched", "", "getWatched", "()Z", "known", "getKnown", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Row {
    public static final int $stable = 8;
    private final Device device;
    private final DeviceOverride override;

    public static /* synthetic */ Row copy$default(Row row, Device device, DeviceOverride deviceOverride, int i, Object obj) {
        if ((i & 1) != 0) {
            device = row.device;
        }
        if ((i & 2) != 0) {
            deviceOverride = row.override;
        }
        return row.copy(device, deviceOverride);
    }

    /* renamed from: component1, reason: from getter */
    public final Device getDevice() {
        return this.device;
    }

    /* renamed from: component2, reason: from getter */
    public final DeviceOverride getOverride() {
        return this.override;
    }

    public final Row copy(Device device, DeviceOverride override) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(override, "override");
        return new Row(device, override);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Row)) {
            return false;
        }
        Row row = (Row) other;
        return Intrinsics.areEqual(this.device, row.device) && Intrinsics.areEqual(this.override, row.override);
    }

    public int hashCode() {
        return (this.device.hashCode() * 31) + this.override.hashCode();
    }

    public String toString() {
        return "Row(device=" + this.device + ", override=" + this.override + ")";
    }

    public Row(Device device, DeviceOverride override) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(override, "override");
        this.device = device;
        this.override = override;
    }

    public final Device getDevice() {
        return this.device;
    }

    public final DeviceOverride getOverride() {
        return this.override;
    }

    public final String getId() {
        return this.device.getIp();
    }

    public final String getDisplayName() {
        String vendor;
        String nickname = this.override.getNickname();
        if (nickname != null) {
            return nickname;
        }
        String name = this.device.getName();
        if (name.length() == 0 && ((vendor = this.device.getVendor()) == null || (name = vendor + " device") == null)) {
            name = "Device " + this.device.getIp();
        }
        return name;
    }

    public final String getKind() {
        String typeOverride = this.override.getTypeOverride();
        return typeOverride == null ? this.device.getKind() : typeOverride;
    }

    public final boolean getWatched() {
        return this.override.getWatched();
    }

    public final boolean getKnown() {
        return this.override.getKnown();
    }
}
