package com.mrnice.pixelscan;

import androidx.compose.runtime.MutableState;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

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
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1b
            if (r1 == r3) goto L17
            if (r1 != r2) goto Lf
            goto L1b
        Lf:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L17:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L1b:
            kotlin.ResultKt.throwOnFailure(r7)
        L1e:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$running$delegate
            boolean r7 = com.mrnice.pixelscan.DeviceDetailKt.access$PingCard$lambda$57(r7)
            if (r7 == 0) goto L76
            androidx.compose.runtime.MutableState<java.util.List<java.lang.Float>> r7 = r6.$samples$delegate
            java.util.List r7 = com.mrnice.pixelscan.DeviceDetailKt.access$PingCard$lambda$54(r7)
            int r7 = r7.size()
            r1 = 60
            if (r7 >= r1) goto L76
            java.lang.String r7 = r6.$ip
            com.mrnice.pixelscan.DeviceDetailKt$PingCard$1$1$2$1$1$$ExternalSyntheticLambda0 r1 = new com.mrnice.pixelscan.DeviceDetailKt$PingCard$1$1$2$1$1$$ExternalSyntheticLambda0
            r1.<init>()
            r7 = r6
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r6.label = r3
            java.lang.Object r7 = com.mrnice.pixelscan.DeviceDetailKt.withContextIO(r1, r7)
            if (r7 != r0) goto L47
            goto L75
        L47:
            java.lang.Number r7 = (java.lang.Number) r7
            float r7 = r7.floatValue()
            androidx.compose.runtime.MutableState<java.util.List<java.lang.Float>> r1 = r6.$samples$delegate
            java.util.List r4 = com.mrnice.pixelscan.DeviceDetailKt.access$PingCard$lambda$54(r1)
            java.util.Collection r4 = (java.util.Collection) r4
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r7)
            java.util.List r4 = kotlin.collections.CollectionsKt.plus(r4, r5)
            r5 = 40
            java.util.List r4 = kotlin.collections.CollectionsKt.takeLast(r4, r5)
            com.mrnice.pixelscan.DeviceDetailKt.access$PingCard$lambda$55(r1, r4)
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r6.F$0 = r7
            r6.label = r2
            r4 = 900(0x384, double:4.447E-321)
            java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r4, r1)
            if (r7 != r0) goto L1e
        L75:
            return r0
        L76:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$running$delegate
            r0 = 0
            com.mrnice.pixelscan.DeviceDetailKt.access$PingCard$lambda$58(r7, r0)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.DeviceDetailKt$PingCard$1$1$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invokeSuspend$lambda$0(String str) {
        return Native.INSTANCE.pingMs(str, 1000L);
    }
}
