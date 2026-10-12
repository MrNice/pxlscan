package com.mrnice.pixelscan;

import androidx.compose.runtime.MutableState;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: DeviceDetail.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.mrnice.pixelscan.DeviceDetailKt$PingCard$1$1$2$1$1", f = "DeviceDetail.kt", i = {1}, l = {145, 147}, m = "invokeSuspend", n = {"ms"}, s = {"F$0"})
/* loaded from: classes2.dex */
final class DeviceDetailKt$PingCard$1$1$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $ip;
    final /* synthetic */ MutableState<Boolean> $running$delegate;
    final /* synthetic */ MutableState<List<Float>> $samples$delegate;
    float F$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DeviceDetailKt$PingCard$1$1$2$1$1(MutableState<Boolean> mutableState, MutableState<List<Float>> mutableState2, String str, Continuation<? super DeviceDetailKt$PingCard$1$1$2$1$1> continuation) {
        super(2, continuation);
        this.$running$delegate = mutableState;
        this.$samples$delegate = mutableState2;
        this.$ip = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DeviceDetailKt$PingCard$1$1$2$1$1(this.$running$delegate, this.$samples$delegate, this.$ip, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeviceDetailKt$PingCard$1$1$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0073 -> B:11:0x001e). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
                float fFloatValue = ((Number) obj).floatValue();
                MutableState<List<Float>> mutableState = this.$samples$delegate;
                mutableState.setValue(CollectionsKt.takeLast(CollectionsKt.plus((Collection<? extends Float>) DeviceDetailKt.PingCard$lambda$54(mutableState), Boxing.boxFloat(fFloatValue)), 40));
                this.F$0 = fFloatValue;
                this.label = 2;
                if (DelayKt.delay(900L, this) != coroutine_suspended) {
                    if (DeviceDetailKt.PingCard$lambda$57(this.$running$delegate) || DeviceDetailKt.PingCard$lambda$54(this.$samples$delegate).size() >= 60) {
                        DeviceDetailKt.PingCard$lambda$58(this.$running$delegate, false);
                        return Unit.INSTANCE;
                    }
                    final String str = this.$ip;
                    this.label = 1;
                    obj = DeviceDetailKt.withContextIO(new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$PingCard$1$1$2$1$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(DeviceDetailKt$PingCard$1$1$2$1$1.invokeSuspend$lambda$0(str));
                        }
                    }, this);
                    if (obj != coroutine_suspended) {
                        float fFloatValue2 = ((Number) obj).floatValue();
                        MutableState<List<Float>> mutableState2 = this.$samples$delegate;
                        mutableState2.setValue(CollectionsKt.takeLast(CollectionsKt.plus((Collection<? extends Float>) DeviceDetailKt.PingCard$lambda$54(mutableState2), Boxing.boxFloat(fFloatValue2)), 40));
                        this.F$0 = fFloatValue2;
                        this.label = 2;
                        if (DelayKt.delay(900L, this) != coroutine_suspended) {
                        }
                    }
                }
                return coroutine_suspended;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
        ResultKt.throwOnFailure(obj);
        if (DeviceDetailKt.PingCard$lambda$57(this.$running$delegate)) {
        }
        DeviceDetailKt.PingCard$lambda$58(this.$running$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invokeSuspend$lambda$0(String str) {
        return Native.INSTANCE.pingMs(str, 1000L);
    }
}
