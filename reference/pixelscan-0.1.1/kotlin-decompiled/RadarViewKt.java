package com.mrnice.pixelscan;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.location.LocationRequestCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: RadarView.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u001a9\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n²\u0006\n\u0010\u000b\u001a\u00020\fX\u008a\u0084\u0002²\u0006\"\u0010\r\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e0\u0003X\u008a\u008e\u0002"}, d2 = {"RadarView", "", "rows", "", "Lcom/mrnice/pixelscan/Row;", "self", "onOpen", "Lkotlin/Function1;", "", "(Ljava/util/List;Lcom/mrnice/pixelscan/Row;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app_release", "sweep", "", "positions", "Lkotlin/Triple;", "Landroidx/compose/ui/geometry/Offset;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RadarViewKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RadarView$lambda$13(List list, Row row, Function1 function1, int i, Composer composer, int i2) {
        RadarView(list, row, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void RadarView(final List<Row> rows, final Row row, final Function1<? super String, Unit> onOpen, Composer composer, final int i) {
        State<Float> state;
        int i2;
        Object obj;
        Composer composer2;
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(onOpen, "onOpen");
        Composer composerStartRestartGroup = composer.startRestartGroup(396645113);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RadarView)P(1,2)33@1366L43,34@1438L191,40@1718L212,46@1952L74,48@2102L267,48@2032L3009:RadarView.kt#woosis");
        int i3 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(rows) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(row) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onOpen) ? 256 : 128;
        }
        if ((i3 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(396645113, i3, -1, "com.mrnice.pixelscan.RadarView (RadarView.kt:32)");
            }
            int i4 = i3;
            State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition("sweep", composerStartRestartGroup, 6, 0), 0.0f, 360.0f, AnimationSpecKt.m139infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(3600, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "angle", composerStartRestartGroup, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1076120947, "CC(remember):RadarView.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(rows);
            ArrayList arrayListRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || arrayListRememberedValue == Composer.INSTANCE.getEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : rows) {
                    if (!((Row) obj2).getDevice().isSelf()) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                int i5 = 0;
                for (Object obj3 : arrayList2) {
                    int i6 = i5 + 1;
                    if (i5 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    double d = 360;
                    arrayList3.add(TuplesKt.to((Row) obj3, Double.valueOf(((Store.INSTANCE.identityKey(r12.getDevice()).hashCode() % d) + d) % d)));
                    i5 = i6;
                    stateAnimateFloat = stateAnimateFloat;
                    i4 = i4;
                }
                state = stateAnimateFloat;
                i2 = i4;
                arrayListRememberedValue = arrayList3;
                composerStartRestartGroup.updateRememberedValue(arrayListRememberedValue);
            } else {
                state = stateAnimateFloat;
                i2 = i4;
            }
            final List list = (List) arrayListRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1076113597, "CC(remember):RadarView.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = null;
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                obj = null;
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM236backgroundbw27NRU$default = BackgroundKt.m236backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), Ink.INSTANCE.m6628getBg0d7_KjU(), null, 2, null);
            List<Triple<Row, Offset, Float>> listRadarView$lambda$5 = RadarView$lambda$5(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1076108604, "CC(remember):RadarView.kt#9igjgp");
            boolean z = (i2 & 896) == 256;
            RadarViewKt$RadarView$1$1 radarViewKt$RadarView$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || radarViewKt$RadarView$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                radarViewKt$RadarView$1$1RememberedValue = new RadarViewKt$RadarView$1$1(mutableState, onOpen, null);
                composerStartRestartGroup.updateRememberedValue(radarViewKt$RadarView$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierM236backgroundbw27NRU$default, listRadarView$lambda$5, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) radarViewKt$RadarView$1$1RememberedValue);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPointerInput);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -507559918, "C55@2412L2209,55@2381L2240,96@4630L204:RadarView.kt#woosis");
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1784742964, "CC(remember):RadarView.kt#9igjgp");
            final State<Float> state2 = state;
            boolean zChanged2 = composerStartRestartGroup.changed(state2) | composerStartRestartGroup.changedInstance(list);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mrnice.pixelscan.RadarViewKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return RadarViewKt.RadarView$lambda$12$lambda$10$lambda$9(list, state2, mutableState, (DrawScope) obj4);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) objRememberedValue2, composerStartRestartGroup, 6);
            TextKt.m2373Text4IGK_g(list.size() + " devices · distance ≈ latency", PaddingKt.m681padding3ABfNKs(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), Dp.m6299constructorimpl(16)), Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3456, 0, 131056);
            if (row == null) {
                composerStartRestartGroup.startReplaceGroup(-505188172);
                composerStartRestartGroup.endReplaceGroup();
                composer2 = composerStartRestartGroup;
            } else {
                composerStartRestartGroup.startReplaceGroup(-505188171);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*102@4867L158");
                composer2 = composerStartRestartGroup;
                TextKt.m2373Text4IGK_g("YOU", PaddingKt.m685paddingqDBjuR0$default(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), 0.0f, Dp.m6299constructorimpl(22), 0.0f, 0.0f, 13, null), Ink.INSTANCE.m6632getRed0d7_KjU(), TextUnitKt.getSp(10), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 200070, 0, 131024);
                composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.RadarViewKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return RadarViewKt.RadarView$lambda$13(rows, row, onOpen, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Triple<Row, Offset, Float>> RadarView$lambda$5(MutableState<List<Triple<Row, Offset, Float>>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RadarView$lambda$12$lambda$10$lambda$9(List list, State state, MutableState mutableState, DrawScope drawScope) throws Throwable {
        DrawContext drawContext;
        long j;
        DrawScope Canvas = drawScope;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float f = 2;
        float fM3668getWidthimpl = Size.m3668getWidthimpl(Canvas.mo4396getSizeNHjbRc()) / f;
        float fM3665getHeightimpl = Size.m3665getHeightimpl(Canvas.mo4396getSizeNHjbRc()) / f;
        float fMin = Math.min(fM3668getWidthimpl, fM3665getHeightimpl) * 0.86f;
        char c = CharCompanionObject.MIN_VALUE;
        char c2 = 3;
        Iterator it = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(0.25f), Float.valueOf(0.5f), Float.valueOf(0.75f), Float.valueOf(1.0f)}).iterator();
        while (it.hasNext()) {
            DrawScope.m4377drawCircleVaOC9Bg$default(Canvas, Ink.INSTANCE.m6629getHairline0d7_KjU(), fMin * ((Number) it.next()).floatValue(), OffsetKt.Offset(fM3668getWidthimpl, fM3665getHeightimpl), 0.0f, new Stroke(1.5f, 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
            Canvas = drawScope;
            c = CharCompanionObject.MIN_VALUE;
            c2 = 3;
        }
        long jM6629getHairline0d7_KjU = Ink.INSTANCE.m6629getHairline0d7_KjU();
        float f2 = fM3668getWidthimpl - fMin;
        long jOffset = OffsetKt.Offset(f2, fM3665getHeightimpl);
        long jOffset2 = OffsetKt.Offset(fM3668getWidthimpl + fMin, fM3665getHeightimpl);
        float f3 = fM3665getHeightimpl;
        DrawScope.m4382drawLineNGM6Ib0$default(drawScope, jM6629getHairline0d7_KjU, jOffset, jOffset2, 1.5f, 0, null, 0.0f, null, 0, 496, null);
        float f4 = f3 - fMin;
        DrawScope.m4382drawLineNGM6Ib0$default(drawScope, Ink.INSTANCE.m6629getHairline0d7_KjU(), OffsetKt.Offset(fM3668getWidthimpl, f4), OffsetKt.Offset(fM3668getWidthimpl, f3 + fMin), 1.5f, 0, null, 0.0f, null, 0, 496, null);
        float fRadarView$lambda$0 = RadarView$lambda$0(state);
        long jOffset3 = OffsetKt.Offset(fM3668getWidthimpl, f3);
        DrawContext drawContext2 = drawScope.getDrawContext();
        long jMo4317getSizeNHjbRc = drawContext2.mo4317getSizeNHjbRc();
        drawContext2.getCanvas().save();
        try {
            drawContext2.getTransform().mo4323rotateUv8p0NA(fRadarView$lambda$0, jOffset3);
            float f5 = fMin * f;
            try {
                DrawScope.m4374drawArcillE91I$default(drawScope, Brush.INSTANCE.m3806sweepGradientUv8p0NA(new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m3830boximpl(Color.INSTANCE.m3875getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.12f), Color.m3830boximpl(Color.m3839copywmQWz5c$default(Ink.INSTANCE.m6632getRed0d7_KjU(), 0.28f, 0.0f, 0.0f, 0.0f, 14, null))), TuplesKt.to(Float.valueOf(0.16f), Color.m3830boximpl(Color.INSTANCE.m3875getTransparent0d7_KjU()))}, OffsetKt.Offset(fM3668getWidthimpl, f3)), -90.0f, 60.0f, true, OffsetKt.Offset(f2, f4), androidx.compose.ui.geometry.SizeKt.Size(f5, f5), 0.0f, null, null, 0, 960, null);
                DrawScope.m4382drawLineNGM6Ib0$default(drawScope, Ink.INSTANCE.m6632getRed0d7_KjU(), OffsetKt.Offset(fM3668getWidthimpl, f3), OffsetKt.Offset(fM3668getWidthimpl, f4), 2.5f, 0, null, 0.0f, null, 0, 496, null);
                drawContext2.getCanvas().restore();
                drawContext2.mo4318setSizeuvyYCjk(jMo4317getSizeNHjbRc);
                float f6 = fM3668getWidthimpl;
                DrawScope.m4377drawCircleVaOC9Bg$default(drawScope, Ink.INSTANCE.m6632getRed0d7_KjU(), 7.0f, OffsetKt.Offset(f6, f3), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
                ArrayList arrayList = new ArrayList();
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    Pair pair = (Pair) it2.next();
                    Row row = (Row) pair.component1();
                    double dDoubleValue = ((Number) pair.component2()).doubleValue();
                    float fCoerceIn = RangesKt.coerceIn(((float) Math.log((row.getDevice().getRttMs() != null ? r1.floatValue() : 120.0f) + 1.0f)) / ((float) Math.log(201.0f)), 0.12f, 1.0f) * fMin;
                    double d = (dDoubleValue / 180.0d) * 3.141592653589793d;
                    double d2 = fCoerceIn;
                    float fCos = ((float) (Math.cos(d) * d2)) + f6;
                    float fSin = ((float) (d2 * Math.sin(d))) + f3;
                    boolean zIsGateway = row.getDevice().isGateway();
                    Ink ink = Ink.INSTANCE;
                    float f7 = f6;
                    float f8 = f3;
                    DrawScope.m4377drawCircleVaOC9Bg$default(drawScope, zIsGateway ? ink.m6632getRed0d7_KjU() : ink.m6636getText0d7_KjU(), zIsGateway ? 6.0f : 5.0f, OffsetKt.Offset(fCos, fSin), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
                    if (row.getWatched()) {
                        DrawScope.m4377drawCircleVaOC9Bg$default(drawScope, Ink.INSTANCE.m6632getRed0d7_KjU(), 9.0f, OffsetKt.Offset(fCos, fSin), 0.0f, new Stroke(1.5f, 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
                    }
                    arrayList.add(new Triple(row, Offset.m3588boximpl(OffsetKt.Offset(fCos, fSin)), Float.valueOf(fCoerceIn)));
                    f3 = f8;
                    f6 = f7;
                }
                mutableState.setValue(arrayList);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                drawContext = drawContext2;
                j = jMo4317getSizeNHjbRc;
                drawContext.getCanvas().restore();
                drawContext.mo4318setSizeuvyYCjk(j);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            drawContext = drawContext2;
            j = jMo4317getSizeNHjbRc;
        }
    }

    private static final float RadarView$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }
}
