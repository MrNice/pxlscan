package com.mrnice.pixelscan;

import android.app.Application;
import android.os.Build;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import org.json.JSONException;

/* compiled from: ScanViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001bH\u0002J \u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$J\u0010\u0010%\u001a\u0004\u0018\u00010\u001c2\u0006\u0010&\u001a\u00020\"J\b\u0010'\u001a\u0004\u0018\u00010\u0014J\b\u0010(\u001a\u00020\u0016H\u0014R+\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/mrnice/pixelscan/ScanViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "<set-?>", "Lcom/mrnice/pixelscan/ScanState;", "state", "getState", "()Lcom/mrnice/pixelscan/ScanState;", "setState", "(Lcom/mrnice/pixelscan/ScanState;)V", "state$delegate", "Landroidx/compose/runtime/MutableState;", "handle", "", "pollJob", "Lkotlinx/coroutines/Job;", "store", "Lcom/mrnice/pixelscan/Store;", "refreshNetwork", "", "startScan", "stopScan", "stopPolling", "merge", "", "Lcom/mrnice/pixelscan/Row;", "devices", "Lcom/mrnice/pixelscan/Device;", "setOverride", "row", "field", "", "value", "", "rowFor", "id", "currentStore", "onCleared", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ScanViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private long handle;
    private Job pollJob;

    /* renamed from: state$delegate, reason: from kotlin metadata */
    private final MutableState state;
    private Store store;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanViewModel(Application app) {
        super(app);
        Intrinsics.checkNotNullParameter(app, "app");
        this.state = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new ScanState(false, null, 0.0f, null, null, null, 0L, 0L, null, false, 1023, null), null, 2, null);
        this.handle = -1L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(ScanState scanState) {
        this.state.setValue(scanState);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ScanState getState() {
        return (ScanState) this.state.getValue();
    }

    public final void refreshNetwork() {
        LanInfo lanInfoCurrent = NetInfo.INSTANCE.current(getApplication());
        setState(ScanState.copy$default(getState(), false, null, 0.0f, null, lanInfoCurrent, lanInfoCurrent == null ? "Not on a Wi-Fi network" : null, 0L, 0L, null, false, 975, null));
        if (lanInfoCurrent != null) {
            this.store = new Store(getApplication(), Store.INSTANCE.networkKey(lanInfoCurrent));
        }
    }

    public final void startScan() {
        LanInfo lanInfoCurrent = NetInfo.INSTANCE.current(getApplication());
        if (lanInfoCurrent == null) {
            setState(ScanState.copy$default(getState(), false, null, 0.0f, null, null, "Join a Wi-Fi network to scan", 0L, 0L, null, false, 974, null));
            return;
        }
        this.store = new Store(getApplication(), Store.INSTANCE.networkKey(lanInfoCurrent));
        if (!Native.INSTANCE.ensureLoaded()) {
            setState(ScanState.copy$default(getState(), false, null, 0.0f, null, lanInfoCurrent, "Scan engine failed to load", 0L, 0L, null, false, 975, null));
            return;
        }
        stopPolling();
        boolean zBindTo = NetInfo.INSTANCE.bindTo(getApplication(), lanInfoCurrent);
        long jStart = Native.INSTANCE.start(new ScanConfig(lanInfoCurrent.getIp(), lanInfoCurrent.getPrefixLen(), lanInfoCurrent.getGateway(), lanInfoCurrent.getDnsServers(), 700L, Build.MODEL));
        this.handle = jStart;
        if (jStart < 0) {
            NetInfo.INSTANCE.unbind(getApplication());
            setState(ScanState.copy$default(getState(), false, null, 0.0f, null, lanInfoCurrent, "Could not start scan", 0L, 0L, null, false, 975, null));
        } else {
            setState(ScanState.copy$default(getState(), true, "starting", 0.0f, null, lanInfoCurrent, null, 0L, 0L, null, zBindTo, 456, null));
            this.pollJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
        }
    }

    /* compiled from: ScanViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.mrnice.pixelscan.ScanViewModel$startScan$1", f = "ScanViewModel.kt", i = {0}, l = {97}, m = "invokeSuspend", n = {"snap"}, s = {"L$0"})
    /* renamed from: com.mrnice.pixelscan.ScanViewModel$startScan$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ScanViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
            do {
                ScanSnapshot scanSnapshotSnapshot = Native.INSTANCE.snapshot(ScanViewModel.this.handle);
                ScanViewModel scanViewModel = ScanViewModel.this;
                scanViewModel.setState(ScanState.copy$default(scanViewModel.getState(), !scanSnapshotSnapshot.getDone(), scanSnapshotSnapshot.getPhase(), scanSnapshotSnapshot.getProgress(), ScanViewModel.this.merge(scanSnapshotSnapshot.getDevices()), null, null, scanSnapshotSnapshot.getElapsedMs(), 0L, scanSnapshotSnapshot.getDiag(), false, 688, null));
                if (scanSnapshotSnapshot.getDone()) {
                    NetInfo.INSTANCE.unbind(ScanViewModel.this.getApplication());
                    ScanViewModel scanViewModel2 = ScanViewModel.this;
                    scanViewModel2.setState(ScanState.copy$default(scanViewModel2.getState(), false, null, 0.0f, null, null, null, 0L, System.currentTimeMillis(), null, false, 895, null));
                    return Unit.INSTANCE;
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(scanSnapshotSnapshot);
                this.label = 1;
            } while (DelayKt.delay(250L, this) != coroutine_suspended);
            return coroutine_suspended;
        }
    }

    public final void stopScan() {
        stopPolling();
        NetInfo.INSTANCE.unbind(getApplication());
        if (this.handle >= 0) {
            Native.INSTANCE.stop(this.handle);
        }
        this.handle = -1L;
        setState(ScanState.copy$default(getState(), false, null, 0.0f, null, null, null, 0L, 0L, null, false, 1022, null));
    }

    private final void stopPolling() {
        Job job = this.pollJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pollJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Row> merge(List<Device> devices) {
        Store store = this.store;
        if (store != null) {
            List<Device> list = devices;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (Device device : list) {
                arrayList.add(new Row(device, store.overrideFor(device)));
            }
            return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.mrnice.pixelscan.ScanViewModel$merge$$inlined$compareBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((Row) t).getDevice().getIpSortKey()), Long.valueOf(((Row) t2).getDevice().getIpSortKey()));
                }
            });
        }
        List<Device> list2 = devices;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList2.add(new Row((Device) it.next(), new DeviceOverride(null, null, false, false, null, 31, null)));
        }
        return arrayList2;
    }

    public final void setOverride(Row row, String field, Object value) throws JSONException {
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(field, "field");
        Store store = this.store;
        if (store == null) {
            return;
        }
        store.setOverride(Store.INSTANCE.identityKey(row.getDevice()), field, value);
        ScanState state = getState();
        List<Row> rows = getState().getRows();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(rows, 10));
        for (Row rowCopy$default : rows) {
            if (Intrinsics.areEqual(Store.INSTANCE.identityKey(rowCopy$default.getDevice()), Store.INSTANCE.identityKey(row.getDevice()))) {
                rowCopy$default = Row.copy$default(rowCopy$default, null, store.overrideFor(rowCopy$default.getDevice()), 1, null);
            }
            arrayList.add(rowCopy$default);
        }
        setState(ScanState.copy$default(state, false, null, 0.0f, arrayList, null, null, 0L, 0L, null, false, PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, null));
    }

    public final Row rowFor(String id) {
        Object next;
        Intrinsics.checkNotNullParameter(id, "id");
        Iterator<T> it = getState().getRows().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Row) next).getId(), id)) {
                break;
            }
        }
        return (Row) next;
    }

    /* renamed from: currentStore, reason: from getter */
    public final Store getStore() {
        return this.store;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        stopScan();
        super.onCleared();
    }
}
