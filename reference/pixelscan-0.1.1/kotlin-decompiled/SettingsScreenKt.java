package com.mrnice.pixelscan;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.SwitchDefaults;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a+\u0010\u0005\u001a\u00020\u00012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0003¢\u0006\u0002\u0010\u000b\u001a9\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0002\u0010\u0013\u001a3\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u001aH\u0003¢\u0006\u0002\u0010\u001b¨\u0006\u001c²\u0006\n\u0010\u001d\u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\u001fX\u008a\u008e\u0002²\u0006\n\u0010 \u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\u0011X\u008a\u008e\u0002"}, d2 = {"SettingsScreen", "", "vm", "Lcom/mrnice/pixelscan/ScanViewModel;", "(Lcom/mrnice/pixelscan/ScanViewModel;Landroidx/compose/runtime/Composer;I)V", "Card", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "ToggleRow", "title", "", "subtitle", "checked", "", "onChange", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "Chip", "label", "selected", "mod", "Landroidx/compose/ui/Modifier;", "onClick", "Lkotlin/Function0;", "(Ljava/lang/String;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release", "monitor", "interval", "", "alertAll", "alertWatched"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SettingsScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Card$lambda$33(Function3 function3, int i, Composer composer, int i2) {
        Card(function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Chip$lambda$38(String str, boolean z, Modifier modifier, Function0 function0, int i, Composer composer, int i2) {
        Chip(str, z, modifier, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$32(ScanViewModel scanViewModel, int i, Composer composer, int i2) {
        SettingsScreen(scanViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ToggleRow$lambda$36(String str, String str2, boolean z, Function1 function1, int i, Composer composer, int i2) {
        ToggleRow(str, str2, z, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void SettingsScreen(ScanViewModel vm, Composer composer, final int i) {
        int i2;
        final ScanViewModel scanViewModel;
        Intrinsics.checkNotNullParameter(vm, "vm");
        Composer composerStartRestartGroup = composer.startRestartGroup(416700739);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SettingsScreen)20@757L7,21@784L26,22@830L52,23@903L53,24@977L49,25@1051L58,27@1160L21,27@1115L3787:SettingsScreen.kt#woosis");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changedInstance(vm) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            scanViewModel = vm;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(416700739, i2, -1, "com.mrnice.pixelscan.SettingsScreen (SettingsScreen.kt:19)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1017455069, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Settings(context);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final Settings settings = (Settings) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1017456567, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(settings.getMonitorEnabled()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1017458904, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf(settings.getIntervalMinutes()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1017461268, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(settings.getAlertAllNew()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1017463645, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(settings.getAlertWatchedMovement()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM681padding3ABfNKs = PaddingKt.m681padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null), Dp.m6299constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM681padding3ABfNKs);
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
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1256206109, "C28@1209L129,30@1353L885,30@1348L890,47@2247L30,48@2291L357,48@2286L362,53@2657L30,54@2696L660,66@3365L30,67@3409L1448,67@3404L1453,85@4866L30:SettingsScreen.kt#woosis");
            TextKt.m2373Text4IGK_g("Settings", PaddingKt.m683paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m6299constructorimpl(10), 1, null), Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(22), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 199734, 0, 131024);
            composerStartRestartGroup = composerStartRestartGroup;
            Card(ComposableLambdaKt.rememberComposableLambda(141064614, true, new Function3() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$31$lambda$19(settings, context, mutableState, mutableState2, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 6);
            float f = 12;
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composerStartRestartGroup, 6);
            Card(ComposableLambdaKt.rememberComposableLambda(1740560669, true, new Function3() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$31$lambda$24(settings, mutableState3, mutableState4, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 6);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composerStartRestartGroup, 6);
            Card(ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$846625758$app_release(), composerStartRestartGroup, 6);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composerStartRestartGroup, 6);
            scanViewModel = vm;
            Card(ComposableLambdaKt.rememberComposableLambda(-47309153, true, new Function3() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$31$lambda$30(scanViewModel, context, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 6);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(30)), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$32(scanViewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean SettingsScreen$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final int SettingsScreen$lambda$5(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    private static final void SettingsScreen$lambda$6(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    private static final boolean SettingsScreen$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$11(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$12(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$19(final Settings settings, final Context context, MutableState mutableState, final MutableState mutableState2, ColumnScope Card, Composer composer, int i) {
        final MutableState mutableState3;
        String str;
        boolean z;
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer2, "C31@1449L141,31@1367L223:SettingsScreen.kt#woosis");
        if ((i & 17) == 16 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(141064614, i, -1, "com.mrnice.pixelscan.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:31)");
            }
            boolean zSettingsScreen$lambda$2 = SettingsScreen$lambda$2(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer2, -1875856205, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance = composer2.changedInstance(settings) | composer2.changedInstance(context);
            Object objRememberedValue = composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                mutableState3 = mutableState;
                objRememberedValue = new Function1() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$31$lambda$19$lambda$14$lambda$13(settings, context, mutableState3, ((Boolean) obj).booleanValue());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            } else {
                mutableState3 = mutableState;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ToggleRow("Monitor mode", "Scan in the background and alert on changes", zSettingsScreen$lambda$2, (Function1) objRememberedValue, composer2, 54);
            if (SettingsScreen$lambda$2(mutableState3)) {
                composer2.startReplaceGroup(1978180056);
                ComposerKt.sourceInformation(composer2, "36@1634L39,37@1690L114,38@1821L393");
                DividerKt.m1752HorizontalDivider9IZ8Weo(null, 0.0f, Ink.INSTANCE.m6629getHairline0d7_KjU(), composer2, 0, 3);
                float f = 16;
                float f2 = 12;
                TextKt.m2373Text4IGK_g("Check every", PaddingKt.m684paddingqDBjuR0(Modifier.INSTANCE, Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(f2), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(4)), Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3126, 0, 131056);
                composer2 = composer;
                Modifier modifierM684paddingqDBjuR0 = PaddingKt.m684paddingqDBjuR0(Modifier.INSTANCE, Dp.m6299constructorimpl(f2), Dp.m6299constructorimpl(0), Dp.m6299constructorimpl(f2), Dp.m6299constructorimpl(f2));
                Arrangement.HorizontalOrVertical horizontalOrVerticalM561spacedBy0680j_4 = Arrangement.INSTANCE.m561spacedBy0680j_4(Dp.m6299constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM561spacedBy0680j_4, Alignment.INSTANCE.getTop(), composer2, 6);
                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM684paddingqDBjuR0);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor);
                } else {
                    composer2.useNode();
                }
                Composer composerM3333constructorimpl = Updater.m3333constructorimpl(composer2);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, -1883631230, "C:SettingsScreen.kt#woosis");
                composer2.startReplaceGroup(-1307687698);
                ComposerKt.sourceInformation(composer2, "*40@2053L121,40@2002L172");
                Iterator it = CollectionsKt.listOf((Object[]) new Integer[]{15, 30, 60}).iterator();
                while (it.hasNext()) {
                    final int iIntValue = ((Number) it.next()).intValue();
                    String str2 = iIntValue + " min";
                    if (SettingsScreen$lambda$5(mutableState2) == iIntValue) {
                        str = str2;
                        z = true;
                    } else {
                        str = str2;
                        z = false;
                    }
                    Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composer2, 1258224822, "CC(remember):SettingsScreen.kt#9igjgp");
                    boolean zChanged = composer2.changed(iIntValue) | composer2.changedInstance(settings) | composer2.changedInstance(context);
                    Object objRememberedValue2 = composer2.rememberedValue();
                    if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return SettingsScreenKt.SettingsScreen$lambda$31$lambda$19$lambda$18$lambda$17$lambda$16$lambda$15(iIntValue, settings, context, mutableState2);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Chip(str, z, modifierWeight$default, (Function0) objRememberedValue2, composer2, 0);
                }
                composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
            } else {
                composer2.startReplaceGroup(1976557020);
            }
            composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$19$lambda$14$lambda$13(Settings settings, Context context, MutableState mutableState, boolean z) throws JSONException {
        SettingsScreen$lambda$3(mutableState, z);
        settings.setMonitorEnabled(z);
        Monitor.INSTANCE.ensureChannels(context);
        Monitor.INSTANCE.schedule(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$19$lambda$18$lambda$17$lambda$16$lambda$15(int i, Settings settings, Context context, MutableState mutableState) throws JSONException {
        SettingsScreen$lambda$6(mutableState, i);
        settings.setIntervalMinutes(i);
        Monitor.INSTANCE.schedule(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$24(final Settings settings, final MutableState mutableState, final MutableState mutableState2, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C49@2379L44,49@2305L118,50@2436L39,51@2581L57,51@2488L150:SettingsScreen.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1740560669, i, -1, "com.mrnice.pixelscan.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:49)");
            }
            boolean zSettingsScreen$lambda$8 = SettingsScreen$lambda$8(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, 1936527625, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(settings);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$31$lambda$24$lambda$21$lambda$20(settings, mutableState, ((Boolean) obj).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ToggleRow("Alert on any new device", "Not just untrusted ones", zSettingsScreen$lambda$8, (Function1) objRememberedValue, composer, 54);
            DividerKt.m1752HorizontalDivider9IZ8Weo(null, 0.0f, Ink.INSTANCE.m6629getHairline0d7_KjU(), composer, 0, 3);
            boolean zSettingsScreen$lambda$11 = SettingsScreen$lambda$11(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, 1936534102, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance2 = composer.changedInstance(settings);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$31$lambda$24$lambda$23$lambda$22(settings, mutableState2, ((Boolean) obj).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ToggleRow("Alert on watched devices", "When a starred device joins or leaves", zSettingsScreen$lambda$11, (Function1) objRememberedValue2, composer, 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$24$lambda$21$lambda$20(Settings settings, MutableState mutableState, boolean z) throws JSONException {
        SettingsScreen$lambda$9(mutableState, z);
        settings.setAlertAllNew(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$24$lambda$23$lambda$22(Settings settings, MutableState mutableState, boolean z) throws JSONException {
        SettingsScreen$lambda$12(mutableState, z);
        settings.setAlertWatchedMovement(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$30(final ScanViewModel scanViewModel, final Context context, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C68@3423L1424:SettingsScreen.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-47309153, i, -1, "com.mrnice.pixelscan.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:68)");
            }
            Modifier modifierM681padding3ABfNKs = PaddingKt.m681padding3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM681padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM3333constructorimpl = Updater.m3333constructorimpl(composer);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 802316606, "C69@3473L64,70@3554L29,71@3600L150,76@4124L109,77@4250L87,78@4375L268,78@4354L336,82@4707L126:SettingsScreen.kt#woosis");
            TextKt.m2373Text4IGK_g("Engine", (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 196614, 0, 131034);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(4)), composer, 6);
            TextKt.m2373Text4IGK_g(Native.INSTANCE.getAvailable() ? "Rust core loaded ✓" : "Rust core not loaded", (Modifier) null, Native.INSTANCE.getAvailable() ? Ink.INSTANCE.m6637getTextDim0d7_KjU() : Ink.INSTANCE.m6632getRed0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 131058);
            LanInfo lan = scanViewModel.getState().getLan();
            if (lan == null) {
                composer.startReplaceGroup(802590086);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(802590087);
                ComposerKt.sourceInformation(composer, "*73@3807L160");
                TextKt.m2373Text4IGK_g("This device: " + lan.getIp() + "/" + lan.getPrefixLen() + " (" + lan.getTransport() + (lan.getVpnActive() ? ", VPN up" : "") + ")", (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 130994);
                String gateway = lan.getGateway();
                if (gateway == null) {
                    composer.startReplaceGroup(-1477997007);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-1477997006);
                    ComposerKt.sourceInformation(composer, "*74@4011L76");
                    TextKt.m2373Text4IGK_g("Router: " + gateway, (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 130994);
                    composer.endReplaceGroup();
                    Unit unit = Unit.INSTANCE;
                }
                composer.endReplaceGroup();
            }
            TextKt.m2373Text4IGK_g("Last scan", PaddingKt.m685paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m6299constructorimpl(10), 0.0f, 0.0f, 13, null), Ink.INSTANCE.m6636getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 196662, 0, 131032);
            TextKt.m2373Text4IGK_g(MainActivityKt.diagText(scanViewModel.getState(), context), (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 130994);
            ComposerKt.sourceInformationMarkerStart(composer, 303003617, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(context) | composer.changedInstance(scanViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsScreenKt.SettingsScreen$lambda$31$lambda$30$lambda$29$lambda$28$lambda$27(context, scanViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$1422058706$app_release(), composer, 805306368, 510);
            TextKt.m2373Text4IGK_g("PixelScan 0.1.1", PaddingKt.m685paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m6299constructorimpl(6), 0.0f, 0.0f, 13, null), Ink.INSTANCE.m6638getTextFaint0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3120, 0, 131056);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$31$lambda$30$lambda$29$lambda$28$lambda$27(Context context, ScanViewModel scanViewModel) {
        Object systemService = context.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("pixelscan diag", MainActivityKt.diagText(scanViewModel.getState(), context)));
        return Unit.INSTANCE;
    }

    private static final void Card(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-228843043);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Card)91@4986L106:SettingsScreen.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-228843043, i2, -1, "com.mrnice.pixelscan.Card (SettingsScreen.kt:90)");
            }
            Modifier modifierM236backgroundbw27NRU$default = BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(14))), Ink.INSTANCE.m6634getSurface0d7_KjU(), null, 2, null);
            int i3 = (i2 << 9) & 7168;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM236backgroundbw27NRU$default);
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
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            function3.invoke(ColumnScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i3 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.Card$lambda$33(function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ToggleRow(final String str, final String str2, final boolean z, final Function1<? super Boolean, Unit> function1, Composer composer, final int i) {
        String str3;
        int i2;
        String str4;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1953146822);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ToggleRow)P(3,2)96@5216L607:SettingsScreen.kt#woosis");
        if ((i & 6) == 0) {
            str3 = str;
            i2 = (composerStartRestartGroup.changed(str3) ? 4 : 2) | i;
        } else {
            str3 = str;
            i2 = i;
        }
        if ((i & 48) == 0) {
            str4 = str2;
            i2 |= composerStartRestartGroup.changed(str4) ? 32 : 16;
        } else {
            str4 = str2;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i2 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1953146822, i2, -1, "com.mrnice.pixelscan.ToggleRow (SettingsScreen.kt:95)");
            }
            Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6299constructorimpl(16), Dp.m6299constructorimpl(12));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN4);
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
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 69401153, "C97@5325L165,101@5577L239,101@5499L318:SettingsScreen.kt#woosis");
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1462732242, "C98@5367L47,99@5427L53:SettingsScreen.kt#woosis");
            TextKt.m2373Text4IGK_g(str3, (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(16), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, (i2 & 14) | 3072, 0, 131058);
            TextKt.m2373Text4IGK_g(str4, (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, ((i2 >> 3) & 14) | 3072, 0, 131058);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            SwitchKt.Switch(z, function1, null, null, false, SwitchDefaults.INSTANCE.m2255colorsV1nXRL4(Ink.INSTANCE.m6636getText0d7_KjU(), Ink.INSTANCE.m6632getRed0d7_KjU(), Ink.INSTANCE.m6632getRed0d7_KjU(), 0L, Ink.INSTANCE.m6637getTextDim0d7_KjU(), Ink.INSTANCE.m6635getSurfaceHi0d7_KjU(), Ink.INSTANCE.m6629getHairline0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, SwitchDefaults.$stable << 18, 65416), null, composer2, (i2 >> 6) & 126, 92);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.ToggleRow$lambda$36(str, str2, z, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void Chip(final String str, final boolean z, final Modifier modifier, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1053526219);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Chip)P(!1,3)110@5932L328:SettingsScreen.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 2048 : 1024;
        }
        if ((i2 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1053526219, i2, -1, "com.mrnice.pixelscan.Chip (SettingsScreen.kt:109)");
            }
            Modifier modifierClip = ClipKt.clip(modifier, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(10)));
            Ink ink = Ink.INSTANCE;
            Modifier modifierM683paddingVpY3zN4$default = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(BackgroundKt.m236backgroundbw27NRU$default(modifierClip, z ? ink.m6632getRed0d7_KjU() : ink.m6635getSurfaceHi0d7_KjU(), null, 2, null), false, null, null, function0, 7, null), 0.0f, Dp.m6299constructorimpl(12), 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 349880673, "C111@6109L145:SettingsScreen.kt#woosis");
            Ink ink2 = Ink.INSTANCE;
            long jM6636getText0d7_KjU = z ? ink2.m6636getText0d7_KjU() : ink2.m6637getTextDim0d7_KjU();
            long sp = TextUnitKt.getSp(14);
            FontWeight.Companion companion = FontWeight.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g(str, (Modifier) null, jM6636getText0d7_KjU, sp, (FontStyle) null, z ? companion.getBold() : companion.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, (14 & i2) | 3072, 0, 131026);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.SettingsScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.Chip$lambda$38(str, z, modifier, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
