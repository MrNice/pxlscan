package com.mrnice.pixelscan;

import android.os.Build;
import androidx.activity.compose.ManagedActivityResultLauncher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.mrnice.pixelscan.MainActivityKt$App$1$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class MainActivityKt$App$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ManagedActivityResultLauncher<String, Boolean> $notifPerm;
    final /* synthetic */ ScanViewModel $vm;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivityKt$App$1$1(ScanViewModel scanViewModel, ManagedActivityResultLauncher<String, Boolean> managedActivityResultLauncher, Continuation<? super MainActivityKt$App$1$1> continuation) {
        super(2, continuation);
        this.$vm = scanViewModel;
        this.$notifPerm = managedActivityResultLauncher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$App$1$1(this.$vm, this.$notifPerm, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$App$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$vm.refreshNetwork();
        this.$vm.startScan();
        if (Build.VERSION.SDK_INT >= 33 && this.$vm.getApplication().checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
            this.$notifPerm.launch("android.permission.POST_NOTIFICATIONS");
        }
        return Unit.INSTANCE;
    }
}
