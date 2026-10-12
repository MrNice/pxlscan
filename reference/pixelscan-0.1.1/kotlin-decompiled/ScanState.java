package com.mrnice.pixelscan;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScanViewModel.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u000fHÆ\u0003J\t\u0010.\u001a\u00020\u000fHÆ\u0003J\t\u0010/\u001a\u00020\u0012HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003Jw\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0003HÆ\u0001J\u0013\u00102\u001a\u00020\u00032\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017¨\u00067"}, d2 = {"Lcom/mrnice/pixelscan/ScanState;", "", "running", "", "phase", "", NotificationCompat.CATEGORY_PROGRESS, "", "rows", "", "Lcom/mrnice/pixelscan/Row;", "lan", "Lcom/mrnice/pixelscan/LanInfo;", "error", "elapsedMs", "", "lastScanAt", "diag", "Lcom/mrnice/pixelscan/ScanDiag;", "boundToLan", "<init>", "(ZLjava/lang/String;FLjava/util/List;Lcom/mrnice/pixelscan/LanInfo;Ljava/lang/String;JJLcom/mrnice/pixelscan/ScanDiag;Z)V", "getRunning", "()Z", "getPhase", "()Ljava/lang/String;", "getProgress", "()F", "getRows", "()Ljava/util/List;", "getLan", "()Lcom/mrnice/pixelscan/LanInfo;", "getError", "getElapsedMs", "()J", "getLastScanAt", "getDiag", "()Lcom/mrnice/pixelscan/ScanDiag;", "getBoundToLan", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ScanState {
    public static final int $stable = 8;
    private final boolean boundToLan;
    private final ScanDiag diag;
    private final long elapsedMs;
    private final String error;
    private final LanInfo lan;
    private final long lastScanAt;
    private final String phase;
    private final float progress;
    private final List<Row> rows;
    private final boolean running;

    public ScanState() {
        this(false, null, 0.0f, null, null, null, 0L, 0L, null, false, 1023, null);
    }

    public static /* synthetic */ ScanState copy$default(ScanState scanState, boolean z, String str, float f, List list, LanInfo lanInfo, String str2, long j, long j2, ScanDiag scanDiag, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = scanState.running;
        }
        if ((i & 2) != 0) {
            str = scanState.phase;
        }
        if ((i & 4) != 0) {
            f = scanState.progress;
        }
        if ((i & 8) != 0) {
            list = scanState.rows;
        }
        if ((i & 16) != 0) {
            lanInfo = scanState.lan;
        }
        if ((i & 32) != 0) {
            str2 = scanState.error;
        }
        if ((i & 64) != 0) {
            j = scanState.elapsedMs;
        }
        if ((i & 128) != 0) {
            j2 = scanState.lastScanAt;
        }
        if ((i & 256) != 0) {
            scanDiag = scanState.diag;
        }
        if ((i & 512) != 0) {
            z2 = scanState.boundToLan;
        }
        long j3 = j2;
        long j4 = j;
        LanInfo lanInfo2 = lanInfo;
        String str3 = str2;
        float f2 = f;
        List list2 = list;
        return scanState.copy(z, str, f2, list2, lanInfo2, str3, j4, j3, scanDiag, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getRunning() {
        return this.running;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getBoundToLan() {
        return this.boundToLan;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPhase() {
        return this.phase;
    }

    /* renamed from: component3, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    public final List<Row> component4() {
        return this.rows;
    }

    /* renamed from: component5, reason: from getter */
    public final LanInfo getLan() {
        return this.lan;
    }

    /* renamed from: component6, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* renamed from: component7, reason: from getter */
    public final long getElapsedMs() {
        return this.elapsedMs;
    }

    /* renamed from: component8, reason: from getter */
    public final long getLastScanAt() {
        return this.lastScanAt;
    }

    /* renamed from: component9, reason: from getter */
    public final ScanDiag getDiag() {
        return this.diag;
    }

    public final ScanState copy(boolean running, String phase, float progress, List<Row> rows, LanInfo lan, String error, long elapsedMs, long lastScanAt, ScanDiag diag, boolean boundToLan) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(diag, "diag");
        return new ScanState(running, phase, progress, rows, lan, error, elapsedMs, lastScanAt, diag, boundToLan);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScanState)) {
            return false;
        }
        ScanState scanState = (ScanState) other;
        return this.running == scanState.running && Intrinsics.areEqual(this.phase, scanState.phase) && Float.compare(this.progress, scanState.progress) == 0 && Intrinsics.areEqual(this.rows, scanState.rows) && Intrinsics.areEqual(this.lan, scanState.lan) && Intrinsics.areEqual(this.error, scanState.error) && this.elapsedMs == scanState.elapsedMs && this.lastScanAt == scanState.lastScanAt && Intrinsics.areEqual(this.diag, scanState.diag) && this.boundToLan == scanState.boundToLan;
    }

    public int hashCode() {
        int iHashCode = ((((((Boolean.hashCode(this.running) * 31) + this.phase.hashCode()) * 31) + Float.hashCode(this.progress)) * 31) + this.rows.hashCode()) * 31;
        LanInfo lanInfo = this.lan;
        int iHashCode2 = (iHashCode + (lanInfo == null ? 0 : lanInfo.hashCode())) * 31;
        String str = this.error;
        return ((((((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Long.hashCode(this.elapsedMs)) * 31) + Long.hashCode(this.lastScanAt)) * 31) + this.diag.hashCode()) * 31) + Boolean.hashCode(this.boundToLan);
    }

    public String toString() {
        return "ScanState(running=" + this.running + ", phase=" + this.phase + ", progress=" + this.progress + ", rows=" + this.rows + ", lan=" + this.lan + ", error=" + this.error + ", elapsedMs=" + this.elapsedMs + ", lastScanAt=" + this.lastScanAt + ", diag=" + this.diag + ", boundToLan=" + this.boundToLan + ")";
    }

    public ScanState(boolean z, String phase, float f, List<Row> rows, LanInfo lanInfo, String str, long j, long j2, ScanDiag diag, boolean z2) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(diag, "diag");
        this.running = z;
        this.phase = phase;
        this.progress = f;
        this.rows = rows;
        this.lan = lanInfo;
        this.error = str;
        this.elapsedMs = j;
        this.lastScanAt = j2;
        this.diag = diag;
        this.boundToLan = z2;
    }

    public final boolean getRunning() {
        return this.running;
    }

    public /* synthetic */ ScanState(boolean z, String str, float f, List list, LanInfo lanInfo, String str2, long j, long j2, ScanDiag scanDiag, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "idle" : str, (i & 4) != 0 ? 0.0f : f, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? null : lanInfo, (i & 32) == 0 ? str2 : null, (i & 64) != 0 ? 0L : j, (i & 128) == 0 ? j2 : 0L, (i & 256) != 0 ? new ScanDiag(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, SupportMenu.USER_MASK, null) : scanDiag, (i & 512) != 0 ? false : z2);
    }

    public final String getPhase() {
        return this.phase;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final List<Row> getRows() {
        return this.rows;
    }

    public final LanInfo getLan() {
        return this.lan;
    }

    public final String getError() {
        return this.error;
    }

    public final long getElapsedMs() {
        return this.elapsedMs;
    }

    public final long getLastScanAt() {
        return this.lastScanAt;
    }

    public final ScanDiag getDiag() {
        return this.diag;
    }

    public final boolean getBoundToLan() {
        return this.boundToLan;
    }
}
