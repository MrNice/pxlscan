package com.mrnice.pixelscan;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.StarBorderKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
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
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONException;

/* compiled from: DeviceDetail.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\f\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0003¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u0016\u001a3\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0002\u0010\u001e\u001aE\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010$H\u0003¢\u0006\u0002\u0010%\u001a\u0010\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(H\u0002\u001a\u0010\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020(H\u0002\u001a\"\u0010+\u001a\u0002H,\"\u0004\b\u0000\u0010,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0\u0007H\u0086@¢\u0006\u0002\u0010.¨\u0006/²\u0006\n\u00100\u001a\u000201X\u008a\u008e\u0002²\u0006\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u008a\u008e\u0002²\u0006\n\u00102\u001a\u000201X\u008a\u008e\u0002²\u0006\n\u00103\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u00104\u001a\u000201X\u008a\u008e\u0002"}, d2 = {"DeviceDetailScreen", "", "vm", "Lcom/mrnice/pixelscan/ScanViewModel;", "id", "", "onBack", "Lkotlin/Function0;", "(Lcom/mrnice/pixelscan/ScanViewModel;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "PingCard", "ip", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "Sparkline", "samples", "", "", "(Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "SectionTitle", "t", "Fact", "k", "v", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "ToolButton", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "mod", "Landroidx/compose/ui/Modifier;", "onClick", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "RenameDialog", HintConstants.AUTOFILL_HINT_NAME, "kind", "onDismiss", "onSave", "Lkotlin/Function2;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "portLabel", "p", "", "ttlHint", "ttl", "withContextIO", "T", "block", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release", "editing", "", "running", "text", "expanded"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceDetailKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$0(ScanViewModel scanViewModel, String str, Function0 function0, int i, Composer composer, int i2) {
        DeviceDetailScreen(scanViewModel, str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$52(ScanViewModel scanViewModel, String str, Function0 function0, int i, Composer composer, int i2) {
        DeviceDetailScreen(scanViewModel, str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Fact$lambda$74(String str, String str2, int i, Composer composer, int i2) {
        Fact(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PingCard$lambda$65(String str, int i, Composer composer, int i2) {
        PingCard(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$109(String str, String str2, Function0 function0, Function2 function2, int i, Composer composer, int i2) {
        RenameDialog(str, str2, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SectionTitle$lambda$72(String str, int i, Composer composer, int i2) {
        SectionTitle(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Sparkline$lambda$71(List list, int i, Composer composer, int i2) {
        Sparkline(list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ToolButton$lambda$76(String str, ImageVector imageVector, Modifier modifier, Function0 function0, int i, Composer composer, int i2) {
        ToolButton(str, imageVector, modifier, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void DeviceDetailScreen(final ScanViewModel vm, final String id, final Function0<Unit> onBack, Composer composer, final int i) {
        int i2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function2<? super Composer, ? super Integer, Unit> function2;
        Intrinsics.checkNotNullParameter(vm, "vm");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-600607298);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DeviceDetailScreen)P(2)32@1276L7,33@1303L34,37@1403L674,49@2085L3898,35@1343L4640:DeviceDetail.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(vm) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(id) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBack) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-600607298, i2, -1, "com.mrnice.pixelscan.DeviceDetailScreen (DeviceDetail.kt:28)");
            }
            final Row rowRowFor = vm.rowFor(id);
            if (rowRowFor == null) {
                onBack.invoke();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    function2 = new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DeviceDetailKt.DeviceDetailScreen$lambda$0(vm, id, onBack, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    };
                    scopeUpdateScopeEndRestartGroup.updateScope(function2);
                }
                return;
            }
            final Device device = rowRowFor.getDevice();
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1657986880, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ScaffoldKt.m2088ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(634580090, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$10(rowRowFor, onBack, vm, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, null, null, 0, Ink.INSTANCE.m6628getBg0d7_KjU(), 0L, null, ComposableLambdaKt.rememberComposableLambda(1319728975, true, new Function3() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$46(device, rowRowFor, mutableState, context, vm, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 806879280, 445);
            composerStartRestartGroup = composerStartRestartGroup;
            if (!DeviceDetailScreen$lambda$2(mutableState)) {
                composerStartRestartGroup.startReplaceGroup(140719652);
            } else {
                composerStartRestartGroup.startReplaceGroup(146685013);
                ComposerKt.sourceInformation(composerStartRestartGroup, "119@6076L19,120@6118L212,118@6012L319");
                String displayName = rowRowFor.getDisplayName();
                String kind = rowRowFor.getKind();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1657834159, "CC(remember):DeviceDetail.kt#9igjgp");
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DeviceDetailKt.DeviceDetailScreen$lambda$48$lambda$47(mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function0 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1657832622, "CC(remember):DeviceDetail.kt#9igjgp");
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(vm) | composerStartRestartGroup.changedInstance(rowRowFor) | composerStartRestartGroup.changedInstance(device);
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DeviceDetailKt.DeviceDetailScreen$lambda$51$lambda$50(vm, rowRowFor, device, mutableState, (String) obj, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                RenameDialog(displayName, kind, function0, (Function2) objRememberedValue3, composerStartRestartGroup, 384);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            function2 = new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$52(vm, id, onBack, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
    }

    private static final boolean DeviceDetailScreen$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DeviceDetailScreen$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$10(final Row row, final Function0 function0, final ScanViewModel scanViewModel, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C39@1452L57,40@1544L103,41@1675L287,46@2007L45,38@1417L650:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(634580090, i, -1, "com.mrnice.pixelscan.DeviceDetailScreen.<anonymous> (DeviceDetail.kt:38)");
            }
            AppBarKt.m1452TopAppBarGHTll3U(ComposableLambdaKt.rememberComposableLambda(536978238, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$10$lambda$4(row, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), null, ComposableLambdaKt.rememberComposableLambda(1035535040, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$10$lambda$5(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), ComposableLambdaKt.rememberComposableLambda(845251625, true, new Function3() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$10$lambda$9(scanViewModel, row, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), 0.0f, null, TopAppBarDefaults.INSTANCE.m2544topAppBarColorszjMxDiM(Ink.INSTANCE.m6634getSurface0d7_KjU(), 0L, 0L, 0L, 0L, composer, (TopAppBarDefaults.$stable << 15) | 6, 30), null, composer, 3462, 178);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$10$lambda$4(Row row, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C39@1454L53:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(536978238, i, -1, "com.mrnice.pixelscan.DeviceDetailScreen.<anonymous>.<anonymous> (DeviceDetail.kt:39)");
            }
            TextKt.m2373Text4IGK_g(row.getDisplayName(), (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 384, 3072, 122874);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$10$lambda$5(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C40@1546L99:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1035535040, i, -1, "com.mrnice.pixelscan.DeviceDetailScreen.<anonymous>.<anonymous> (DeviceDetail.kt:40)");
            }
            IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$DeviceDetailKt.INSTANCE.m6608getLambda$357516899$app_release(), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$10$lambda$9(final ScanViewModel scanViewModel, final Row row, RowScope TopAppBar, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
        ComposerKt.sourceInformation(composer, "C42@1718L48,42@1768L176,42@1697L247:DeviceDetail.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(845251625, i, -1, "com.mrnice.pixelscan.DeviceDetailScreen.<anonymous>.<anonymous> (DeviceDetail.kt:42)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 537994105, "CC(remember):DeviceDetail.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(scanViewModel) | composer.changedInstance(row);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DeviceDetailKt.DeviceDetailScreen$lambda$10$lambda$9$lambda$7$lambda$6(scanViewModel, row);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, ComposableLambdaKt.rememberComposableLambda(1159542252, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.DeviceDetailScreen$lambda$10$lambda$9$lambda$8(row, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$10$lambda$9$lambda$7$lambda$6(ScanViewModel scanViewModel, Row row) throws JSONException {
        scanViewModel.setOverride(row, "watched", Boolean.valueOf(!row.getWatched()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$10$lambda$9$lambda$8(Row row, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C43@1794L128:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1159542252, i, -1, "com.mrnice.pixelscan.DeviceDetailScreen.<anonymous>.<anonymous>.<anonymous> (DeviceDetail.kt:43)");
            }
            IconKt.m1830Iconww6aTOc(row.getWatched() ? StarKt.getStar(Icons.Filled.INSTANCE) : StarBorderKt.getStarBorder(Icons.Filled.INSTANCE), "watch", (Modifier) null, row.getWatched() ? Ink.INSTANCE.m6632getRed0d7_KjU() : Ink.INSTANCE.m6637getTextDim0d7_KjU(), composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:114:0x069c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x06bf  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x06c2  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x073c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0759  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0763  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0780  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x078a  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x07a7  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x07b1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x07ce  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x07d8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x07f5  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x07ff  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x081c  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0826  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0843  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x086a  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x089a  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0949  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0969  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x098b  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0a2d  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0a50  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0a82  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0a91  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0a9b  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0ae8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0b50  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit DeviceDetailScreen$lambda$46(com.mrnice.pixelscan.Device r48, final com.mrnice.pixelscan.Row r49, final androidx.compose.runtime.MutableState r50, android.content.Context r51, final com.mrnice.pixelscan.ScanViewModel r52, androidx.compose.foundation.layout.PaddingValues r53, androidx.compose.runtime.Composer r54, int r55) {
        /*
            Method dump skipped, instructions count: 2902
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.DeviceDetailKt.DeviceDetailScreen$lambda$46(com.mrnice.pixelscan.Device, com.mrnice.pixelscan.Row, androidx.compose.runtime.MutableState, android.content.Context, com.mrnice.pixelscan.ScanViewModel, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$46$lambda$45$lambda$16$lambda$15$lambda$14(MutableState mutableState) {
        DeviceDetailScreen$lambda$3(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$46$lambda$45$lambda$27$lambda$22$lambda$21(Device device, RowScope rowScope, Context context) {
        Object next;
        int iIntValue;
        Iterator<T> it = device.getOpenPorts().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((Number) next).intValue() == 443) {
                break;
            }
        }
        Integer num = (Integer) next;
        if (num == null) {
            for (Number number : device.getOpenPorts()) {
                int iIntValue2 = number.intValue();
                if (iIntValue2 == 80 || iIntValue2 == 8080) {
                    iIntValue = number.intValue();
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        iIntValue = num.intValue();
        String str = iIntValue == 443 ? "https" : "http";
        try {
            Result.Companion companion = Result.INSTANCE;
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str + "://" + device.getIp() + ":" + iIntValue)));
            Result.m6658constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6658constructorimpl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$46$lambda$45$lambda$27$lambda$24$lambda$23(Context context, Device device) {
        Object systemService = context.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("ip", device.getIp()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$46$lambda$45$lambda$27$lambda$26$lambda$25(ScanViewModel scanViewModel, Row row) throws JSONException {
        scanViewModel.setOverride(row, "known", Boolean.valueOf(!row.getKnown()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence DeviceDetailScreen$lambda$46$lambda$45$lambda$38$lambda$37(int i) {
        return portLabel(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$48$lambda$47(MutableState mutableState) {
        DeviceDetailScreen$lambda$3(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceDetailScreen$lambda$51$lambda$50(ScanViewModel scanViewModel, Row row, Device device, MutableState mutableState, String name, String kind) throws JSONException {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(kind, "kind");
        String str = name;
        if (StringsKt.isBlank(str)) {
            str = null;
        }
        scanViewModel.setOverride(row, "nickname", str);
        if (Intrinsics.areEqual(kind, device.getKind())) {
            kind = null;
        }
        scanViewModel.setOverride(row, "type", kind);
        DeviceDetailScreen$lambda$3(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x030d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void PingCard(java.lang.String r39, androidx.compose.runtime.Composer r40, final int r41) {
        /*
            Method dump skipped, instructions count: 1063
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.DeviceDetailKt.PingCard(java.lang.String, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Float> PingCard$lambda$54(MutableState<List<Float>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PingCard$lambda$57(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PingCard$lambda$58(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PingCard$lambda$64$lambda$63$lambda$61$lambda$60(CoroutineScope coroutineScope, MutableState mutableState, MutableState mutableState2, String str) {
        if (PingCard$lambda$57(mutableState)) {
            PingCard$lambda$58(mutableState, false);
        } else {
            PingCard$lambda$58(mutableState, true);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DeviceDetailKt$PingCard$1$1$2$1$1(mutableState, mutableState2, str, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PingCard$lambda$64$lambda$63$lambda$62(MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C151@7640L55:DeviceDetail.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(721710631, i, -1, "com.mrnice.pixelscan.PingCard.<anonymous>.<anonymous>.<anonymous> (DeviceDetail.kt:151)");
            }
            TextKt.m2373Text4IGK_g(PingCard$lambda$57(mutableState) ? "Stop" : "Start", (Modifier) null, Ink.INSTANCE.m6632getRed0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 384, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void Sparkline(final List<Float> list, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1811937148);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Sparkline)160@7918L715,160@7872L761:DeviceDetail.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1811937148, i2, -1, "com.mrnice.pixelscan.Sparkline (DeviceDetail.kt:159)");
            }
            Modifier modifierM712height3ABfNKs = SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6299constructorimpl(48));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1809839281, "CC(remember):DeviceDetail.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(list);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DeviceDetailKt.Sparkline$lambda$70$lambda$69(list, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM712height3ABfNKs, (Function1) objRememberedValue, composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.Sparkline$lambda$71(list, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Sparkline$lambda$70$lambda$69(List list, DrawScope drawScope) {
        int i;
        long j;
        DrawScope Canvas = drawScope;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        if (list.isEmpty()) {
            return Unit.INSTANCE;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (true) {
            Float fValueOf = null;
            if (!it.hasNext()) {
                break;
            }
            float fFloatValue = ((Number) it.next()).floatValue();
            if (fFloatValue >= 0.0f) {
                fValueOf = Float.valueOf(fFloatValue);
            }
            arrayList.add(fValueOf);
        }
        Float fMaxOrNull = CollectionsKt.maxOrNull((Iterable<? extends Float>) CollectionsKt.filterNotNull(arrayList));
        float fCoerceAtLeast = RangesKt.coerceAtLeast(fMaxOrNull != null ? fMaxOrNull.floatValue() : 1.0f, 1.0f);
        int i2 = 2;
        float fM3668getWidthimpl = Size.m3668getWidthimpl(Canvas.mo4396getSizeNHjbRc()) / (RangesKt.coerceAtLeast(list.size(), 2) - 1);
        int i3 = 0;
        Offset offsetM3588boximpl = null;
        for (Object obj : list2) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            float fFloatValue2 = ((Number) obj).floatValue();
            float f = i3 * fM3668getWidthimpl;
            if (fFloatValue2 < 0.0f) {
                i = i2;
                offsetM3588boximpl = null;
            } else {
                long jOffset = OffsetKt.Offset(f, (Size.m3665getHeightimpl(Canvas.mo4396getSizeNHjbRc()) - (((fFloatValue2 / fCoerceAtLeast) * Size.m3665getHeightimpl(Canvas.mo4396getSizeNHjbRc())) * 0.9f)) - i2);
                if (offsetM3588boximpl != null) {
                    j = jOffset;
                    i = i2;
                    DrawScope.m4382drawLineNGM6Ib0$default(Canvas, Ink.INSTANCE.m6632getRed0d7_KjU(), offsetM3588boximpl.getPackedValue(), j, 2.0f, 0, null, 0.0f, null, 0, 496, null);
                } else {
                    i = i2;
                    j = jOffset;
                }
                long j2 = j;
                DrawScope.m4377drawCircleVaOC9Bg$default(drawScope, Ink.INSTANCE.m6632getRed0d7_KjU(), 2.5f, j2, 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
                offsetM3588boximpl = Offset.m3588boximpl(j2);
            }
            Canvas = drawScope;
            i3 = i4;
            i2 = i;
        }
        return Unit.INSTANCE;
    }

    private static final void SectionTitle(final String str, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2305873);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SectionTitle)179@8691L146:DeviceDetail.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2305873, i2, -1, "com.mrnice.pixelscan.SectionTitle (DeviceDetail.kt:179)");
            }
            String upperCase = str.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            float f = 16;
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g(upperCase, PaddingKt.m684paddingqDBjuR0(Modifier.INSTANCE, Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(4)), Ink.INSTANCE.m6638getTextFaint0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 200112, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.SectionTitle$lambda$72(str, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void Fact(final String str, String str2, Composer composer, final int i) {
        int i2;
        Composer composer2;
        final String str3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1113052809);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Fact)182@8896L317:DeviceDetail.kt#woosis");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            str3 = str2;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1113052809, i2, -1, "com.mrnice.pixelscan.Fact (DeviceDetail.kt:181)");
            }
            Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6299constructorimpl(16), Dp.m6299constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composerStartRestartGroup, 0);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1302652133, "C183@8956L81,184@9046L161:DeviceDetail.kt#woosis");
            int i3 = i2;
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g(str, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(130)), Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, (i3 & 14) | 3504, 0, 131056);
            String str4 = str;
            str3 = str2;
            TextKt.m2373Text4IGK_g(str3, (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) ((StringsKt.contains$default((CharSequence) str4, (CharSequence) "MAC", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str4, (CharSequence) "UUID", false, 2, (Object) null)) ? ThemeKt.getMono() : FontFamily.INSTANCE.getDefault()), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, ((i3 >> 3) & 14) | 3456, 0, 130994);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.Fact$lambda$74(str, str3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ToolButton(final String str, final ImageVector imageVector, final Modifier modifier, final Function0<Unit> function0, Composer composer, final int i) {
        String str2;
        int i2;
        ImageVector imageVector2;
        Function0<Unit> function02;
        Composer composerStartRestartGroup = composer.startRestartGroup(1612118187);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ToolButton)P(1)189@9364L365:DeviceDetail.kt#woosis");
        if ((i & 6) == 0) {
            str2 = str;
            i2 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i;
        } else {
            str2 = str;
            i2 = i;
        }
        if ((i & 48) == 0) {
            imageVector2 = imageVector;
            i2 |= composerStartRestartGroup.changed(imageVector2) ? 32 : 16;
        } else {
            imageVector2 = imageVector;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            function02 = function0;
            i2 |= composerStartRestartGroup.changedInstance(function02) ? 2048 : 1024;
        } else {
            function02 = function0;
        }
        if ((i2 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1612118187, i2, -1, "com.mrnice.pixelscan.ToolButton (DeviceDetail.kt:188)");
            }
            Modifier modifierM683paddingVpY3zN4$default = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(modifier, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(10))), Ink.INSTANCE.m6634getSurface0d7_KjU(), null, 2, null), false, null, null, function02, 7, null), 0.0f, Dp.m6299constructorimpl(12), 1, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composerStartRestartGroup, 48);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1893216108, "C190@9549L66,191@9624L29,192@9662L61:DeviceDetail.kt#woosis");
            IconKt.m1830Iconww6aTOc(imageVector2, str2, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(20)), Ink.INSTANCE.m6632getRed0d7_KjU(), composerStartRestartGroup, ((i2 >> 3) & 14) | 3456 | ((i2 << 3) & 112), 0);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(4)), composerStartRestartGroup, 6);
            TextKt.m2373Text4IGK_g(str, (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, (i2 & 14) | 3456, 3072, 122866);
            composerStartRestartGroup = composerStartRestartGroup;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.ToolButton$lambda$76(str, imageVector, modifier, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void RenameDialog(final String str, final String str2, final Function0<Unit> function0, final Function2<? super String, ? super String, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1099009973);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RenameDialog)P(1)198@9873L33,199@9920L33,200@9974L34,204@10126L79,205@10231L75,207@10382L1015,201@10013L1391:DeviceDetail.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i2 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1099009973, i2, -1, "com.mrnice.pixelscan.RenameDialog (DeviceDetail.kt:197)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -413366250, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -413364746, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str2, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -413363017, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m1440AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-2053230483, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.RenameDialog$lambda$88(function2, mutableState, mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(1149101611, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.RenameDialog$lambda$89(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableSingletons$DeviceDetailKt.INSTANCE.getLambda$56466409$app_release(), ComposableLambdaKt.rememberComposableLambda(1657632456, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.RenameDialog$lambda$108(mutableState, mutableState2, mutableState3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, Ink.INSTANCE.m6634getSurface0d7_KjU(), 0L, 0L, 0L, 0.0f, null, composer2, ((i2 >> 6) & 14) | 102435888, 0, 16020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.RenameDialog$lambda$109(str, str2, function0, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String RenameDialog$lambda$78(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RenameDialog$lambda$81(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean RenameDialog$lambda$84(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void RenameDialog$lambda$85(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$88(final Function2 function2, final MutableState mutableState, final MutableState mutableState2, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C204@10149L19,204@10128L75:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2053230483, i, -1, "com.mrnice.pixelscan.RenameDialog.<anonymous> (DeviceDetail.kt:204)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1764504544, "CC(remember):DeviceDetail.kt#9igjgp");
            boolean zChanged = composer.changed(function2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DeviceDetailKt.RenameDialog$lambda$88$lambda$87$lambda$86(function2, mutableState, mutableState2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$DeviceDetailKt.INSTANCE.getLambda$1782717520$app_release(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$88$lambda$87$lambda$86(Function2 function2, MutableState mutableState, MutableState mutableState2) {
        function2.invoke(RenameDialog$lambda$78(mutableState), RenameDialog$lambda$81(mutableState2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$89(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C205@10233L71:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1149101611, i, -1, "com.mrnice.pixelscan.RenameDialog.<anonymous> (DeviceDetail.kt:205)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$DeviceDetailKt.INSTANCE.getLambda$690082318$app_release(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108(final MutableState mutableState, final MutableState mutableState2, MutableState mutableState3, Composer composer, int i) {
        final MutableState mutableState4;
        final MutableState mutableState5;
        ComposerKt.sourceInformation(composer, "C208@10396L991:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1657632456, i, -1, "com.mrnice.pixelscan.RenameDialog.<anonymous> (DeviceDetail.kt:208)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
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
            ComposerKt.sourceInformationMarkerStart(composer, -2036567623, "C209@10518L13,209@10445L13,209@10421L147,210@10585L30,211@10632L741:DeviceDetail.kt#woosis");
            String strRenameDialog$lambda$78 = RenameDialog$lambda$78(mutableState);
            TextFieldColors textFieldColorsFieldColors = MainActivityKt.fieldColors(composer, 0);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, -204243233, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$91$lambda$90(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strRenameDialog$lambda$78, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DeviceDetailKt.INSTANCE.m6607getLambda$1803358664$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsFieldColors, composer, 1573296, 12582912, 0, 4063160);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(12)), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM3333constructorimpl2 = Updater.m3333constructorimpl(composer);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1201359710, "C212@10748L13,214@10981L19,212@10692L2,213@10802L108,212@10658L343,215@11045L20,215@11114L241,215@11022L333:DeviceDetail.kt#woosis");
            String strLabel = Kinds.INSTANCE.label(RenameDialog$lambda$81(mutableState2));
            TextFieldColors textFieldColorsFieldColors2 = MainActivityKt.fieldColors(composer, 0);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, -2039446785, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                mutableState4 = mutableState3;
                objRememberedValue2 = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$93$lambda$92(mutableState4);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            } else {
                mutableState4 = mutableState3;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            Modifier modifierM269clickableXHw0xAI$default = ClickableKt.m269clickableXHw0xAI$default(modifierFillMaxWidth$default2, false, null, null, (Function0) objRememberedValue2, 7, null);
            ComposerKt.sourceInformationMarkerStart(composer, -2039456050, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$95$lambda$94((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strLabel, (Function1<? super String, Unit>) objRememberedValue3, modifierM269clickableXHw0xAI$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DeviceDetailKt.INSTANCE.getLambda$1784004722$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1709732853, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$98(mutableState4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsFieldColors2, composer, 806903856, 0, 0, 4193704);
            boolean zRenameDialog$lambda$84 = RenameDialog$lambda$84(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composer, -2039444736, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                mutableState5 = mutableState3;
                objRememberedValue4 = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$100$lambda$99(mutableState5);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            } else {
                mutableState5 = mutableState3;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            AndroidMenu_androidKt.m1443DropdownMenuIlH_yew(zRenameDialog$lambda$84, (Function0) objRememberedValue4, BackgroundKt.m236backgroundbw27NRU$default(Modifier.INSTANCE, Ink.INSTANCE.m6635getSurfaceHi0d7_KjU(), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(632163463, true, new Function3() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$105(mutableState2, mutableState5, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 432, 48, 2040);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
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
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$91$lambda$90(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$95$lambda$94(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$98(final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C213@10825L19,213@10804L104:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1709732853, i, -1, "com.mrnice.pixelscan.RenameDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DeviceDetail.kt:213)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1919201880, "CC(remember):DeviceDetail.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$98$lambda$97$lambda$96(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, ComposableSingletons$DeviceDetailKt.INSTANCE.m6606getLambda$1690968430$app_release(), composer, 196614, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$98$lambda$97$lambda$96(MutableState mutableState) {
        RenameDialog$lambda$85(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$93$lambda$92(MutableState mutableState) {
        RenameDialog$lambda$85(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$100$lambda$99(MutableState mutableState) {
        RenameDialog$lambda$85(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$105(final MutableState mutableState, final MutableState mutableState2, ColumnScope DropdownMenu, Composer composer, int i) {
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        ComposerKt.sourceInformation(composer2, "C*217@11221L44,217@11277L29,217@11197L110:DeviceDetail.kt#woosis");
        if ((i & 17) == 16 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(632163463, i, -1, "com.mrnice.pixelscan.RenameDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DeviceDetail.kt:216)");
            }
            for (final String str : Kinds.INSTANCE.getOrder()) {
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1555927815, true, new Function2() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$105$lambda$104$lambda$101(str, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54);
                ComposerKt.sourceInformationMarkerStart(composer2, -1333484076, "CC(remember):DeviceDetail.kt#9igjgp");
                boolean zChanged = composer2.changed(str);
                Object objRememberedValue = composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.DeviceDetailKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DeviceDetailKt.RenameDialog$lambda$108$lambda$107$lambda$106$lambda$105$lambda$104$lambda$103$lambda$102(str, mutableState, mutableState2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, null, null, false, null, null, null, composer2, 6, 508);
                composer2 = composer;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$105$lambda$104$lambda$101(String str, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C217@11223L40:DeviceDetail.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1555927815, i, -1, "com.mrnice.pixelscan.RenameDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DeviceDetail.kt:217)");
            }
            TextKt.m2373Text4IGK_g(Kinds.INSTANCE.label(str), (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 384, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RenameDialog$lambda$108$lambda$107$lambda$106$lambda$105$lambda$104$lambda$103$lambda$102(String str, MutableState mutableState, MutableState mutableState2) {
        mutableState.setValue(str);
        RenameDialog$lambda$85(mutableState2, false);
        return Unit.INSTANCE;
    }

    private static final String portLabel(int i) {
        switch (i) {
            case 22:
                return "22·ssh";
            case 80:
                return "80·http";
            case 139:
            case 445:
                return i + "·smb";
            case 443:
                return "443·https";
            case 631:
                return "631·ipp";
            case 1883:
                return "1883·mqtt";
            case 3389:
                return "3389·rdp";
            case 5000:
            case 5001:
                return i + "·nas";
            case 8009:
                return "8009·cast";
            case 9100:
                return "9100·print";
            case 32400:
                return "32400·plex";
            case 62078:
                return "62078·ios";
            default:
                return String.valueOf(i);
        }
    }

    private static final String ttlHint(int i) {
        if (i > 128) {
            return "network gear";
        }
        if (i > 64) {
            return "Windows";
        }
        if (i > 32) {
            return "Linux/Apple/Android";
        }
        return "many hops";
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: DeviceDetail.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.mrnice.pixelscan.DeviceDetailKt$withContextIO$2", f = "DeviceDetail.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.mrnice.pixelscan.DeviceDetailKt$withContextIO$2, reason: invalid class name */
    static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function0<T> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function0<? extends T> function0, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.$block.invoke();
        }
    }

    public static final <T> Object withContextIO(Function0<? extends T> function0, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(function0, null), continuation);
    }
}
