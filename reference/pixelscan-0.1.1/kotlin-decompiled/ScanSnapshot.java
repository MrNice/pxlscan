package com.mrnice.pixelscan;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.mrnice.pixelscan.ScanSnapshot;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: Native.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0001-BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003J\t\u0010&\u001a\u00020\u000eHÆ\u0003J\t\u0010'\u001a\u00020\u0010HÆ\u0003JU\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001J\u0013\u0010)\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u000eHÖ\u0001J\t\u0010,\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006."}, d2 = {"Lcom/mrnice/pixelscan/ScanSnapshot;", "", NotificationCompat.CATEGORY_PROGRESS, "", "phase", "", "done", "", "devices", "", "Lcom/mrnice/pixelscan/Device;", "elapsedMs", "", "totalAddresses", "", "diag", "Lcom/mrnice/pixelscan/ScanDiag;", "<init>", "(FLjava/lang/String;ZLjava/util/List;JILcom/mrnice/pixelscan/ScanDiag;)V", "getProgress", "()F", "getPhase", "()Ljava/lang/String;", "getDone", "()Z", "getDevices", "()Ljava/util/List;", "getElapsedMs", "()J", "getTotalAddresses", "()I", "getDiag", "()Lcom/mrnice/pixelscan/ScanDiag;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ScanSnapshot {
    private final List<Device> devices;
    private final ScanDiag diag;
    private final boolean done;
    private final long elapsedMs;
    private final String phase;
    private final float progress;
    private final int totalAddresses;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ ScanSnapshot copy$default(ScanSnapshot scanSnapshot, float f, String str, boolean z, List list, long j, int i, ScanDiag scanDiag, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f = scanSnapshot.progress;
        }
        if ((i2 & 2) != 0) {
            str = scanSnapshot.phase;
        }
        if ((i2 & 4) != 0) {
            z = scanSnapshot.done;
        }
        if ((i2 & 8) != 0) {
            list = scanSnapshot.devices;
        }
        if ((i2 & 16) != 0) {
            j = scanSnapshot.elapsedMs;
        }
        if ((i2 & 32) != 0) {
            i = scanSnapshot.totalAddresses;
        }
        if ((i2 & 64) != 0) {
            scanDiag = scanSnapshot.diag;
        }
        long j2 = j;
        boolean z2 = z;
        List list2 = list;
        return scanSnapshot.copy(f, str, z2, list2, j2, i, scanDiag);
    }

    /* renamed from: component1, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPhase() {
        return this.phase;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getDone() {
        return this.done;
    }

    public final List<Device> component4() {
        return this.devices;
    }

    /* renamed from: component5, reason: from getter */
    public final long getElapsedMs() {
        return this.elapsedMs;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTotalAddresses() {
        return this.totalAddresses;
    }

    /* renamed from: component7, reason: from getter */
    public final ScanDiag getDiag() {
        return this.diag;
    }

    public final ScanSnapshot copy(float progress, String phase, boolean done, List<Device> devices, long elapsedMs, int totalAddresses, ScanDiag diag) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(devices, "devices");
        Intrinsics.checkNotNullParameter(diag, "diag");
        return new ScanSnapshot(progress, phase, done, devices, elapsedMs, totalAddresses, diag);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScanSnapshot)) {
            return false;
        }
        ScanSnapshot scanSnapshot = (ScanSnapshot) other;
        return Float.compare(this.progress, scanSnapshot.progress) == 0 && Intrinsics.areEqual(this.phase, scanSnapshot.phase) && this.done == scanSnapshot.done && Intrinsics.areEqual(this.devices, scanSnapshot.devices) && this.elapsedMs == scanSnapshot.elapsedMs && this.totalAddresses == scanSnapshot.totalAddresses && Intrinsics.areEqual(this.diag, scanSnapshot.diag);
    }

    public int hashCode() {
        return (((((((((((Float.hashCode(this.progress) * 31) + this.phase.hashCode()) * 31) + Boolean.hashCode(this.done)) * 31) + this.devices.hashCode()) * 31) + Long.hashCode(this.elapsedMs)) * 31) + Integer.hashCode(this.totalAddresses)) * 31) + this.diag.hashCode();
    }

    public String toString() {
        return "ScanSnapshot(progress=" + this.progress + ", phase=" + this.phase + ", done=" + this.done + ", devices=" + this.devices + ", elapsedMs=" + this.elapsedMs + ", totalAddresses=" + this.totalAddresses + ", diag=" + this.diag + ")";
    }

    public ScanSnapshot(float f, String phase, boolean z, List<Device> devices, long j, int i, ScanDiag diag) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(devices, "devices");
        Intrinsics.checkNotNullParameter(diag, "diag");
        this.progress = f;
        this.phase = phase;
        this.done = z;
        this.devices = devices;
        this.elapsedMs = j;
        this.totalAddresses = i;
        this.diag = diag;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final String getPhase() {
        return this.phase;
    }

    public final boolean getDone() {
        return this.done;
    }

    public final List<Device> getDevices() {
        return this.devices;
    }

    public final long getElapsedMs() {
        return this.elapsedMs;
    }

    public final int getTotalAddresses() {
        return this.totalAddresses;
    }

    public /* synthetic */ ScanSnapshot(float f, String str, boolean z, List list, long j, int i, ScanDiag scanDiag, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        ScanDiag scanDiag2;
        if ((i2 & 64) != 0) {
            scanDiag2 = new ScanDiag(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, SupportMenu.USER_MASK, null);
        } else {
            scanDiag2 = scanDiag;
        }
        this(f, str, z, list, j, i, scanDiag2);
    }

    public final ScanDiag getDiag() {
        return this.diag;
    }

    /* compiled from: Native.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/mrnice/pixelscan/ScanSnapshot$Companion;", "", "<init>", "()V", "empty", "Lcom/mrnice/pixelscan/ScanSnapshot;", "parse", "json", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScanSnapshot empty() {
            return new ScanSnapshot(0.0f, "idle", false, CollectionsKt.emptyList(), 0L, 0, null, 64, null);
        }

        public final ScanSnapshot parse(String json) {
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                JSONObject jSONObject = new JSONObject(json);
                float fOptDouble = (float) jSONObject.optDouble(NotificationCompat.CATEGORY_PROGRESS);
                String strOptString = jSONObject.optString("phase", "scanning");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                return new ScanSnapshot(fOptDouble, strOptString, jSONObject.optBoolean("done"), NativeKt.mapObjects(jSONObject.optJSONArray("hosts"), new Function1() { // from class: com.mrnice.pixelscan.ScanSnapshot$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ScanSnapshot.Companion.parse$lambda$0((JSONObject) obj);
                    }
                }), jSONObject.optLong("elapsed_ms"), jSONObject.optInt("total_addresses"), ScanDiag.INSTANCE.parse(jSONObject.optJSONObject("diag")));
            } catch (Exception unused) {
                return empty();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Device parse$lambda$0(JSONObject it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Device.INSTANCE.parse(it);
        }
    }
}
