package com.mrnice.pixelscan;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: RadarView.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.mrnice.pixelscan.RadarViewKt$RadarView$1$1", f = "RadarView.kt", i = {0}, l = {AccessibilityNodeInfoCompat.MAX_NUMBER_OF_PREFETCHED_NODES}, m = "invokeSuspend", n = {"$this$pointerInput"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class RadarViewKt$RadarView$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<String, Unit> $onOpen;
    final /* synthetic */ MutableState<List<Triple<Row, Offset, Float>>> $positions$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    RadarViewKt$RadarView$1$1(MutableState<List<Triple<Row, Offset, Float>>> mutableState, Function1<? super String, Unit> function1, Continuation<? super RadarViewKt$RadarView$1$1> continuation) {
        super(2, continuation);
        this.$positions$delegate = mutableState;
        this.$onOpen = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RadarViewKt$RadarView$1$1 radarViewKt$RadarView$1$1 = new RadarViewKt$RadarView$1$1(this.$positions$delegate, this.$onOpen, continuation);
        radarViewKt$RadarView$1$1.L$0 = obj;
        return radarViewKt$RadarView$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((RadarViewKt$RadarView$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final MutableState<List<Triple<Row, Offset, Float>>> mutableState = this.$positions$delegate;
            final Function1<String, Unit> function1 = this.$onOpen;
            this.L$0 = SpillingKt.nullOutSpilledVariable(pointerInputScope);
            this.label = 1;
            if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, new Function1() { // from class: com.mrnice.pixelscan.RadarViewKt$RadarView$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return RadarViewKt$RadarView$1$1.invokeSuspend$lambda$3(mutableState, function1, (Offset) obj2);
                }
            }, this, 7, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3(MutableState mutableState, Function1 function1, Offset offset) {
        Object next;
        Iterator it = RadarViewKt.RadarView$lambda$5(mutableState).iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                Triple triple = (Triple) next;
                float fHypot = (float) Math.hypot(Offset.m3599getXimpl(offset.getPackedValue()) - Offset.m3599getXimpl(((Offset) triple.getSecond()).getPackedValue()), Offset.m3600getYimpl(offset.getPackedValue()) - Offset.m3600getYimpl(((Offset) triple.getSecond()).getPackedValue()));
                do {
                    Object next2 = it.next();
                    Triple triple2 = (Triple) next2;
                    float fHypot2 = (float) Math.hypot(Offset.m3599getXimpl(offset.getPackedValue()) - Offset.m3599getXimpl(((Offset) triple2.getSecond()).getPackedValue()), Offset.m3600getYimpl(offset.getPackedValue()) - Offset.m3600getYimpl(((Offset) triple2.getSecond()).getPackedValue()));
                    if (Float.compare(fHypot, fHypot2) > 0) {
                        next = next2;
                        fHypot = fHypot2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Triple triple3 = (Triple) next;
        if (triple3 != null) {
            Triple triple4 = ((float) Math.hypot((double) (Offset.m3599getXimpl(offset.getPackedValue()) - Offset.m3599getXimpl(((Offset) triple3.getSecond()).getPackedValue())), (double) (Offset.m3600getYimpl(offset.getPackedValue()) - Offset.m3600getYimpl(((Offset) triple3.getSecond()).getPackedValue())))) < 60.0f ? triple3 : null;
            if (triple4 != null) {
                function1.invoke(((Row) triple4.getFirst()).getId());
            }
        }
        return Unit.INSTANCE;
    }
}
