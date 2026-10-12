package com.mrnice.pixelscan;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material.icons.filled.SensorsKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material.icons.filled.StopKt;
import androidx.compose.material.icons.filled.ViewListKt;
import androidx.compose.material.icons.filled.WifiOffKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.NavigationBarItemDefaults;
import androidx.compose.material3.NavigationBarKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
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
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a)\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0002\u0010\t\u001a)\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0003¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\bH\u0003¢\u0006\u0002\u0010\u0010\u001a\r\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001d\u001a\u0015\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020 H\u0003¢\u0006\u0002\u0010!\u001a\u0016\u0010\"\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020$¨\u0006%²\u0006\n\u0010&\u001a\u00020'X\u008a\u008e\u0002²\u0006\f\u0010(\u001a\u0004\u0018\u00010\bX\u008a\u008e\u0002²\u0006\n\u0010)\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020\bX\u008a\u008e\u0002"}, d2 = {"App", "", "vm", "Lcom/mrnice/pixelscan/ScanViewModel;", "(Lcom/mrnice/pixelscan/ScanViewModel;Landroidx/compose/runtime/Composer;II)V", "DevicesScreen", "onOpen", "Lkotlin/Function1;", "", "(Lcom/mrnice/pixelscan/ScanViewModel;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "DeviceRow", "row", "Lcom/mrnice/pixelscan/Row;", "(Lcom/mrnice/pixelscan/Row;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "Tag", "text", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "ScanButton", "running", "", NotificationCompat.CATEGORY_PROGRESS, "", "onClick", "Lkotlin/Function0;", "(ZFLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "EmptyState", NotificationCompat.CATEGORY_MESSAGE, "fieldColors", "Landroidx/compose/material3/TextFieldColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "ScanProblemCard", "s", "Lcom/mrnice/pixelscan/ScanState;", "(Lcom/mrnice/pixelscan/ScanState;Landroidx/compose/runtime/Composer;I)V", "diagText", "ctx", "Landroid/content/Context;", "app_release", "tab", "Lcom/mrnice/pixelscan/Tab;", "detailId", "radar", "query"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MainActivityKt {

    /* compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Tab.values().length];
            try {
                iArr[Tab.Devices.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Tab.Monitor.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Tab.Settings.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$11(ScanViewModel scanViewModel, int i, int i2, Composer composer, int i3) {
        App(scanViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$23(ScanViewModel scanViewModel, int i, int i2, Composer composer, int i3) {
        App(scanViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceRow$lambda$62(Row row, Function1 function1, int i, Composer composer, int i2) {
        DeviceRow(row, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$52$lambda$43(ScanViewModel scanViewModel, Function1 function1, int i, Composer composer, int i2) {
        DevicesScreen(scanViewModel, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$53(ScanViewModel scanViewModel, Function1 function1, int i, Composer composer, int i2) {
        DevicesScreen(scanViewModel, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EmptyState$lambda$69(String str, int i, Composer composer, int i2) {
        EmptyState(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScanButton$lambda$66(boolean z, float f, Function0 function0, int i, Composer composer, int i2) {
        ScanButton(z, f, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScanProblemCard$lambda$80(ScanState scanState, int i, Composer composer, int i2) {
        ScanProblemCard(scanState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Tag$lambda$64(String str, int i, Composer composer, int i2) {
        Tag(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void App(ScanViewModel scanViewModel, Composer composer, final int i, final int i2) {
        final ScanViewModel scanViewModel2;
        int i3;
        CreationExtras.Empty defaultViewModelCreationExtras;
        Object objRememberedValue;
        Object objRememberedValue2;
        Object objRememberedValue3;
        ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult;
        boolean zChangedInstance;
        MainActivityKt$App$1$1 mainActivityKt$App$1$1RememberedValue;
        String strApp$lambda$4;
        final ScanViewModel scanViewModel3;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-722054508);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(App)51@1953L40,52@2014L42,56@2197L2,54@2104L95,58@2226L385,58@2205L406,77@2823L838,96@3669L310,75@2760L1219:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                scanViewModel2 = scanViewModel;
                if (composerStartRestartGroup.changedInstance(scanViewModel2)) {
                    i4 = 4;
                }
                i3 = i4 | i;
            } else {
                scanViewModel2 = scanViewModel;
            }
            i4 = 2;
            i3 = i4 | i;
        } else {
            scanViewModel2 = scanViewModel;
            i3 = i;
        }
        if ((i3 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            scanViewModel3 = scanViewModel2;
        } else {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "50@1923L11");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    composerStartRestartGroup.startReplaceableGroup(1729797275);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                    ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
                    if (current == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    if (current instanceof HasDefaultViewModelProviderFactory) {
                        defaultViewModelCreationExtras = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
                    } else {
                        defaultViewModelCreationExtras = CreationExtras.Empty.INSTANCE;
                    }
                    ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(ScanViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, defaultViewModelCreationExtras, composerStartRestartGroup, 0, 0);
                    composerStartRestartGroup.endReplaceableGroup();
                    scanViewModel2 = (ScanViewModel) viewModel;
                    i3 &= -15;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-722054508, i3, -1, "com.mrnice.pixelscan.App (MainActivity.kt:50)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890322044, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Tab.Devices, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MutableState mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890323998, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final MutableState mutableState2 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ActivityResultContracts.RequestPermission requestPermission = new ActivityResultContracts.RequestPermission();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890329814, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.App$lambda$7$lambda$6(((Boolean) obj).booleanValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) objRememberedValue3, composerStartRestartGroup, 48);
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890331125, "CC(remember):MainActivity.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(scanViewModel2) | composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
                mainActivityKt$App$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance || mainActivityKt$App$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    mainActivityKt$App$1$1RememberedValue = new MainActivityKt$App$1$1(scanViewModel2, managedActivityResultLauncherRememberLauncherForActivityResult, null);
                    composerStartRestartGroup.updateRememberedValue(mainActivityKt$App$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) mainActivityKt$App$1$1RememberedValue, composerStartRestartGroup, 6);
                strApp$lambda$4 = App$lambda$4(mutableState2);
                if (strApp$lambda$4 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1528852877);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "71@2713L19,71@2673L60");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890346343, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda23
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.App$lambda$10$lambda$9(mutableState2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    DeviceDetailKt.DeviceDetailScreen(scanViewModel2, strApp$lambda$4, (Function0) objRememberedValue4, composerStartRestartGroup, (i3 & 14) | 384);
                    composerStartRestartGroup.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda24
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return MainActivityKt.App$lambda$11(scanViewModel2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                        return;
                    }
                    return;
                }
                composerStartRestartGroup.startReplaceGroup(-1531498386);
                composerStartRestartGroup.endReplaceGroup();
                long jM6628getBg0d7_KjU = Ink.INSTANCE.m6628getBg0d7_KjU();
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-2053451729, true, new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.App$lambda$18(mutableState, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1267652645, true, new Function3() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.App$lambda$22(scanViewModel2, mutableState, mutableState2, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54);
                scanViewModel3 = scanViewModel2;
                ScaffoldKt.m2088ScaffoldTvnljyQ(null, null, composableLambdaRememberComposableLambda, null, null, 0, jM6628getBg0d7_KjU, 0L, null, composableLambdaRememberComposableLambda2, composerStartRestartGroup, 805306752, 443);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890322044, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                final MutableState mutableState3 = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890323998, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                final MutableState mutableState22 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ActivityResultContracts.RequestPermission requestPermission2 = new ActivityResultContracts.RequestPermission();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890329814, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission2, (Function1) objRememberedValue3, composerStartRestartGroup, 48);
                Unit unit2 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1890331125, "CC(remember):MainActivity.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(scanViewModel2) | composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
                mainActivityKt$App$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    mainActivityKt$App$1$1RememberedValue = new MainActivityKt$App$1$1(scanViewModel2, managedActivityResultLauncherRememberLauncherForActivityResult, null);
                    composerStartRestartGroup.updateRememberedValue(mainActivityKt$App$1$1RememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.LaunchedEffect(unit2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) mainActivityKt$App$1$1RememberedValue, composerStartRestartGroup, 6);
                    strApp$lambda$4 = App$lambda$4(mutableState22);
                    if (strApp$lambda$4 == null) {
                    }
                }
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.App$lambda$23(scanViewModel3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Tab App$lambda$1(MutableState<Tab> mutableState) {
        return mutableState.getValue();
    }

    private static final String App$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$7$lambda$6(boolean z) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$10$lambda$9(MutableState mutableState) {
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$18(final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C78@2904L747,78@2837L814:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2053451729, i, -1, "com.mrnice.pixelscan.App.<anonymous> (MainActivity.kt:78)");
            }
            NavigationBarKt.m1953NavigationBarHsRjFd4(null, Ink.INSTANCE.m6634getSurface0d7_KjU(), 0L, Dp.m6299constructorimpl(0), null, ComposableLambdaKt.rememberComposableLambda(-848449944, true, new Function3() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MainActivityKt.App$lambda$18$lambda$17(mutableState, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 199680, 21);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$18$lambda$17(final MutableState mutableState, RowScope rowScope, Composer composer, int i) {
        int i2;
        RowScope NavigationBar = rowScope;
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(NavigationBar, "$this$NavigationBar");
        ComposerKt.sourceInformation(composer2, "C*82@3067L11,83@3111L25,84@3170L35,85@3266L330,80@2969L650:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            i2 = i | (composer2.changed(NavigationBar) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i2 & 19) == 18 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-848449944, i2, -1, "com.mrnice.pixelscan.App.<anonymous>.<anonymous> (MainActivity.kt:79)");
            }
            Iterator<Tab> it = Tab.getEntries().iterator();
            while (it.hasNext()) {
                final Tab next = it.next();
                boolean z = App$lambda$1(mutableState) == next;
                ComposerKt.sourceInformationMarkerStart(composer2, 1299658261, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = composer2.changed(next.ordinal());
                Object objRememberedValue = composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.App$lambda$18$lambda$17$lambda$16$lambda$13$lambda$12(next, mutableState);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1866653861, true, new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.App$lambda$18$lambda$17$lambda$16$lambda$14(next, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54);
                ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1899328664, true, new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.App$lambda$18$lambda$17$lambda$16$lambda$15(next, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54);
                int i3 = i2;
                composer2 = composer;
                NavigationBarKt.NavigationBarItem(NavigationBar, z, (Function0) objRememberedValue, composableLambdaRememberComposableLambda, null, false, composableLambdaRememberComposableLambda2, false, NavigationBarItemDefaults.INSTANCE.m1951colors69fazGs(Ink.INSTANCE.m6632getRed0d7_KjU(), Ink.INSTANCE.m6632getRed0d7_KjU(), Ink.INSTANCE.m6633getRedDim0d7_KjU(), Ink.INSTANCE.m6637getTextDim0d7_KjU(), Ink.INSTANCE.m6637getTextDim0d7_KjU(), 0L, 0L, composer, NavigationBarItemDefaults.$stable << 21, 96), null, composer2, (i3 & 14) | 1575936, 344);
                NavigationBar = rowScope;
                i2 = i3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$18$lambda$17$lambda$16$lambda$13$lambda$12(Tab tab, MutableState mutableState) {
        mutableState.setValue(tab);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$18$lambda$17$lambda$16$lambda$14(Tab tab, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C83@3113L21:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1866653861, i, -1, "com.mrnice.pixelscan.App.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:83)");
            }
            IconKt.m1830Iconww6aTOc(tab.getIcon(), tab.getLabel(), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$18$lambda$17$lambda$16$lambda$15(Tab tab, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C84@3172L31:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1899328664, i, -1, "com.mrnice.pixelscan.App.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:84)");
            }
            TextKt.m2373Text4IGK_g(tab.getLabel(), (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit App$lambda$22(ScanViewModel scanViewModel, MutableState mutableState, final MutableState mutableState2, PaddingValues pad, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(pad, "pad");
        ComposerKt.sourceInformation(composer, "C97@3686L287:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            i |= composer.changed(pad) ? 4 : 2;
        }
        if ((i & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1267652645, i, -1, "com.mrnice.pixelscan.App.<anonymous> (MainActivity.kt:97)");
            }
            Modifier modifierM236backgroundbw27NRU$default = BackgroundKt.m236backgroundbw27NRU$default(SizeKt.fillMaxSize$default(PaddingKt.padding(Modifier.INSTANCE, pad), 0.0f, 1, null), Ink.INSTANCE.m6628getBg0d7_KjU(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM236backgroundbw27NRU$default);
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
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1252305485, "C:MainActivity.kt#woosis");
            int i2 = WhenMappings.$EnumSwitchMapping$0[App$lambda$1(mutableState).ordinal()];
            if (i2 == 1) {
                composer.startReplaceGroup(-1067980456);
                ComposerKt.sourceInformation(composer, "99@3831L17,99@3804L45");
                ComposerKt.sourceInformationMarkerStart(composer, -1067979620, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.App$lambda$22$lambda$21$lambda$20$lambda$19(mutableState2, (String) obj);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                DevicesScreen(scanViewModel, (Function1) objRememberedValue, composer, 48);
                composer.endReplaceGroup();
            } else if (i2 == 2) {
                composer.startReplaceGroup(-1067978020);
                ComposerKt.sourceInformation(composer, "100@3881L17");
                MonitorScreenKt.MonitorScreen(scanViewModel, composer, 0);
                composer.endReplaceGroup();
            } else {
                if (i2 != 3) {
                    composer.startReplaceGroup(-1067981706);
                    composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
                }
                composer.startReplaceGroup(-1067976419);
                ComposerKt.sourceInformation(composer, "101@3931L18");
                SettingsScreenKt.SettingsScreen(scanViewModel, composer, 0);
                composer.endReplaceGroup();
            }
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
    public static final Unit App$lambda$22$lambda$21$lambda$20$lambda$19(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0775  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0784  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x07c3  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x08f2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0169  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void DevicesScreen(final ScanViewModel scanViewModel, final Function1<? super String, Unit> function1, Composer composer, final int i) {
        int i2;
        int i3;
        String str;
        MutableState mutableState;
        final ScanViewModel scanViewModel2;
        float f;
        final Function1<? super String, Unit> function12;
        Object next;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2127701153);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DevicesScreen)P(1)110@4106L34,111@4158L31,113@4210L343,123@4588L2867:MainActivity.kt#woosis");
        int currentMarker = composerStartRestartGroup.getCurrentMarker();
        char c = 2;
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changedInstance(scanViewModel) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            scanViewModel2 = scanViewModel;
            function12 = function1;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2127701153, i2, -1, "com.mrnice.pixelscan.DevicesScreen (MainActivity.kt:108)");
            }
            final ScanState state = scanViewModel.getState();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -316371167, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -316369506, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            int i5 = i2;
            String str2 = "";
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            List<Row> rows = state.getRows();
            String strDevicesScreen$lambda$28 = DevicesScreen$lambda$28(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -316367530, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(rows) | composerStartRestartGroup.changed(strDevicesScreen$lambda$28);
            ArrayList arrayListRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || arrayListRememberedValue == Composer.INSTANCE.getEmpty()) {
                if (StringsKt.isBlank(DevicesScreen$lambda$28(mutableState3))) {
                    i3 = currentMarker;
                    str = "";
                    arrayListRememberedValue = state.getRows();
                } else {
                    List<Row> rows2 = state.getRows();
                    ArrayList arrayList = new ArrayList();
                    Iterator it = rows2.iterator();
                    while (it.hasNext()) {
                        Object next2 = it.next();
                        Row row = (Row) next2;
                        String lowerCase = DevicesScreen$lambda$28(mutableState3).toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        int i6 = currentMarker;
                        String lowerCase2 = row.getDisplayName().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                        String str3 = lowerCase;
                        String str4 = str2;
                        Iterator it2 = it;
                        char c2 = 2;
                        if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) str3, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) row.getDevice().getIp(), (CharSequence) str3, false, 2, (Object) null)) {
                            arrayList.add(next2);
                        } else {
                            String vendor = row.getDevice().getVendor();
                            if (vendor != null) {
                                String lowerCase3 = vendor.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                                if (lowerCase3 == null || !StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) str3, false, 2, (Object) null)) {
                                    String lowerCase4 = Kinds.INSTANCE.label(row.getKind()).toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                                    c2 = 2;
                                    if (StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) str3, false, 2, (Object) null)) {
                                    }
                                } else {
                                    c2 = 2;
                                }
                                arrayList.add(next2);
                            }
                        }
                        c = c2;
                        currentMarker = i6;
                        str2 = str4;
                        it = it2;
                    }
                    i3 = currentMarker;
                    str = str2;
                    arrayListRememberedValue = arrayList;
                }
                composerStartRestartGroup.updateRememberedValue(arrayListRememberedValue);
            } else {
                i3 = currentMarker;
                str = "";
            }
            final List list = (List) arrayListRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int size = state.getRows().size();
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxSize$default);
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
            composerStartRestartGroup.startReplaceGroup(-261118176);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C125@4671L1289:MainActivity.kt#woosis");
            float f2 = 16;
            float f3 = 12;
            float f4 = 8;
            Modifier modifierM684paddingqDBjuR0 = PaddingKt.m684paddingqDBjuR0(Modifier.INSTANCE, Dp.m6299constructorimpl(f2), Dp.m6299constructorimpl(f3), Dp.m6299constructorimpl(f2), Dp.m6299constructorimpl(f4));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM684paddingqDBjuR0);
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
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1132134483, "C126@4737L868:MainActivity.kt#woosis");
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 885044548, "C127@4807L427,134@5272L18,134@5292L139,134@5251L180,137@5503L88,137@5448L143:MainActivity.kt#woosis");
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierWeight$default);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -628322877, "C128@4857L83,129@4961L255:MainActivity.kt#woosis");
            String str5 = str;
            int i7 = i3;
            TextKt.m2373Text4IGK_g("PixelScan", (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(22), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 199686, 0, 131026);
            LanInfo lan = state.getLan();
            if (lan != null) {
                String error = lan.getSubnetLabel() + " · " + size + " online" + (lan.getVpnActive() ? " · VPN on" : str5);
                if (error == null) {
                    error = state.getError();
                    if (error == null) {
                        error = "No network";
                    }
                }
                TextKt.m2373Text4IGK_g(error, (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3072, 0, 130994);
                composerStartRestartGroup = composerStartRestartGroup;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1079814719, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.DevicesScreen$lambda$52$lambda$42$lambda$39$lambda$35$lambda$34(mutableState2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                IconButtonKt.IconButton((Function0) objRememberedValue3, null, false, null, null, ComposableLambdaKt.rememberComposableLambda(1913748050, true, new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.DevicesScreen$lambda$52$lambda$42$lambda$39$lambda$36(mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 30);
                boolean running = state.getRunning();
                float progress = state.getProgress();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1079807257, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(state) | composerStartRestartGroup.changedInstance(scanViewModel);
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.DevicesScreen$lambda$52$lambda$42$lambda$39$lambda$38$lambda$37(state, scanViewModel);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ScanButton(running, progress, (Function0) objRememberedValue4, composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (state.getRunning()) {
                    composerStartRestartGroup.startReplaceGroup(1132995538);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "142@5651L29,144@5753L31,143@5697L239");
                    SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f4)), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1006383122, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(state);
                    Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Float.valueOf(MainActivityKt.DevicesScreen$lambda$52$lambda$42$lambda$41$lambda$40(state));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableState = mutableState2;
                    f = 0.0f;
                    scanViewModel2 = scanViewModel;
                    ProgressIndicatorKt.m2051LinearProgressIndicatorGJbTh5U((Function0) objRememberedValue5, SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6299constructorimpl(2)), Ink.INSTANCE.m6632getRed0d7_KjU(), Ink.INSTANCE.m6629getHairline0d7_KjU(), 0, 0.0f, null, composerStartRestartGroup, 48, 112);
                } else {
                    mutableState = mutableState2;
                    scanViewModel2 = scanViewModel;
                    f = 0.0f;
                    composerStartRestartGroup.startReplaceGroup(1127396783);
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (state.getError() != null && state.getRows().isEmpty()) {
                    composerStartRestartGroup.startReplaceGroup(-259873093);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "152@6025L19");
                    EmptyState(state.getError(), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endToMarker(i7);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda16
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return MainActivityKt.DevicesScreen$lambda$52$lambda$43(scanViewModel2, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                        return;
                    }
                    return;
                }
                composerStartRestartGroup.startReplaceGroup(-265838919);
                composerStartRestartGroup.endReplaceGroup();
                if (state.getRunning() || state.getLastScanAt() <= 0) {
                    composerStartRestartGroup.startReplaceGroup(-265838919);
                    composerStartRestartGroup.endReplaceGroup();
                    if (DevicesScreen$lambda$25(mutableState)) {
                        composerStartRestartGroup.startReplaceGroup(-259516190);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "169@6771L13,164@6445L14,163@6379L501,173@6935L29,174@7012L427,174@6977L462");
                        String strDevicesScreen$lambda$282 = DevicesScreen$lambda$28(mutableState3);
                        Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f, 1, null), Dp.m6299constructorimpl(f3), Dp.m6299constructorimpl(0));
                        TextFieldColors textFieldColorsFieldColors = fieldColors(composerStartRestartGroup, 0);
                        KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m5968getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2086579145, "CC(remember):MainActivity.kt#9igjgp");
                        Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function1() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda17
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return MainActivityKt.DevicesScreen$lambda$52$lambda$47$lambda$46(mutableState3, (String) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        OutlinedTextFieldKt.OutlinedTextField(strDevicesScreen$lambda$282, (Function1<? super String, Unit>) objRememberedValue6, modifierM682paddingVpY3zN4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1411406452$app_release(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1003382837$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, keyboardOptions, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsFieldColors, composerStartRestartGroup, 113246640, 12779520, 0, 4030072);
                        composerStartRestartGroup = composerStartRestartGroup;
                        SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(4)), composerStartRestartGroup, 6);
                        Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2086560588, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(list) | ((i5 & 112) == 32) | composerStartRestartGroup.changedInstance(state);
                        Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                            function12 = function1;
                            objRememberedValue7 = new Function1() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda18
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return MainActivityKt.DevicesScreen$lambda$52$lambda$51$lambda$50(list, state, function12, (LazyListScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                        } else {
                            function12 = function1;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        LazyDslKt.LazyColumn(modifierFillMaxSize$default2, null, null, false, null, null, null, false, (Function1) objRememberedValue7, composerStartRestartGroup, 6, 254);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-259660371);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "160@6238L84");
                        Iterator<T> it3 = state.getRows().iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                next = null;
                                break;
                            } else {
                                next = it3.next();
                                if (((Row) next).getDevice().isSelf()) {
                                    break;
                                }
                            }
                        }
                        RadarViewKt.RadarView(list, (Row) next, function1, composerStartRestartGroup, (i5 << 3) & 896);
                        composerStartRestartGroup.endReplaceGroup();
                        function12 = function1;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    List<Row> rows3 = state.getRows();
                    if ((rows3 instanceof Collection) && rows3.isEmpty()) {
                        i4 = 0;
                    } else {
                        Iterator<T> it4 = rows3.iterator();
                        i4 = 0;
                        while (it4.hasNext()) {
                            if (!((Row) it4.next()).getDevice().isSelf() && (i4 = i4 + 1) < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                    if (i4 == 0) {
                        composerStartRestartGroup.startReplaceGroup(-259724913);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "156@6175L18");
                        ScanProblemCard(state, composerStartRestartGroup, 0);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (DevicesScreen$lambda$25(mutableState)) {
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                }
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.DevicesScreen$lambda$53(scanViewModel2, function12, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean DevicesScreen$lambda$25(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DevicesScreen$lambda$26(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String DevicesScreen$lambda$28(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$52$lambda$42$lambda$39$lambda$35$lambda$34(MutableState mutableState) {
        DevicesScreen$lambda$26(mutableState, !DevicesScreen$lambda$25(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$52$lambda$42$lambda$39$lambda$36(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C135@5314L99:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1913748050, i, -1, "com.mrnice.pixelscan.DevicesScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:135)");
            }
            IconKt.m1830Iconww6aTOc(DevicesScreen$lambda$25(mutableState) ? ViewListKt.getViewList(Icons.Filled.INSTANCE) : SensorsKt.getSensors(Icons.Filled.INSTANCE), "toggle view", (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$52$lambda$42$lambda$39$lambda$38$lambda$37(ScanState scanState, ScanViewModel scanViewModel) {
        if (scanState.getRunning()) {
            scanViewModel.stopScan();
        } else {
            scanViewModel.startScan();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DevicesScreen$lambda$52$lambda$42$lambda$41$lambda$40(ScanState scanState) {
        return RangesKt.coerceIn(scanState.getProgress(), 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$52$lambda$47$lambda$46(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DevicesScreen$lambda$52$lambda$51$lambda$50(final List list, ScanState scanState, final Function1 function1, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final Function1 function12 = new Function1() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivityKt.DevicesScreen$lambda$52$lambda$51$lambda$50$lambda$48((Row) obj);
            }
        };
        final MainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$1 mainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$1 = new Function1() { // from class: com.mrnice.pixelscan.MainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Row row) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((Row) obj);
            }
        };
        LazyColumn.items(list.size(), new Function1<Integer, Object>() { // from class: com.mrnice.pixelscan.MainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return function12.invoke(list.get(i));
            }
        }, new Function1<Integer, Object>() { // from class: com.mrnice.pixelscan.MainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return mainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$1.invoke(list.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.mrnice.pixelscan.MainActivityKt$DevicesScreen$lambda$52$lambda$51$lambda$50$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                int i3;
                ComposerKt.sourceInformation(composer, "C152@7074L22:LazyDsl.kt#428nma");
                if ((i2 & 6) == 0) {
                    i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i2 & 48) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if ((i3 & 147) == 146 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                Row row = (Row) list.get(i);
                composer.startReplaceGroup(1951209562);
                ComposerKt.sourceInformation(composer, "C*176@7092L22,177@7135L57:MainActivity.kt#woosis");
                MainActivityKt.DeviceRow(row, function1, composer, 0);
                DividerKt.m1752HorizontalDivider9IZ8Weo(null, Dp.m6299constructorimpl(1), Ink.INSTANCE.m6629getHairline0d7_KjU(), composer, 48, 1);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        if (list.isEmpty() && !scanState.getRunning()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m6611getLambda$941730050$app_release(), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DevicesScreen$lambda$52$lambda$51$lambda$50$lambda$48(Row it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DeviceRow(final Row row, final Function1<? super String, Unit> function1, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(970997393);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DeviceRow)P(1)191@7605L18,190@7558L1710:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(row) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(970997393, i2, -1, "com.mrnice.pixelscan.DeviceRow (MainActivity.kt:188)");
            }
            Device device = row.getDevice();
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1120596131, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(row) | ((i2 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.DeviceRow$lambda$55$lambda$54(function1, row);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            float f = 12;
            Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(modifierFillMaxWidth$default, false, null, null, (Function0) objRememberedValue, 7, null), Dp.m6299constructorimpl(16), Dp.m6299constructorimpl(f));
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1520204239, "C194@7719L244,197@7972L29,198@8010L950,212@8969L28,216@9160L28,217@9197L65:MainActivity.kt#woosis");
            float f2 = 8;
            Modifier modifierM236backgroundbw27NRU$default = BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(40)), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f2))), Ink.INSTANCE.m6635getSurfaceHi0d7_KjU(), null, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM236backgroundbw27NRU$default);
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
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1522899611, "C195@7834L119:MainActivity.kt#woosis");
            IconKt.m1830Iconww6aTOc(Kinds.INSTANCE.icon(row.getKind()), row.getKind(), SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), device.isGateway() ? Ink.INSTANCE.m6632getRed0d7_KjU() : Ink.INSTANCE.m6637getTextDim0d7_KjU(), composerStartRestartGroup, 384, 0);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composerStartRestartGroup, 6);
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierWeight$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 848100394, "C199@8052L574,210@8835L115:MainActivity.kt#woosis");
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1035317850, "C200@8122L176:MainActivity.kt#woosis");
            TextKt.m2373Text4IGK_g(row.getDisplayName(), rowScopeInstance2.weight(Modifier.INSTANCE, 1.0f, false), Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 199680, 3120, 120784);
            if (row.getWatched()) {
                composerStartRestartGroup.startReplaceGroup(-1035121094);
                ComposerKt.sourceInformation(composerStartRestartGroup, "201@8334L28,201@8364L83");
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(6)), composerStartRestartGroup, 6);
                IconKt.m1830Iconww6aTOc(StarKt.getStar(Icons.Filled.INSTANCE), "watched", SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(14)), Ink.INSTANCE.m6632getRed0d7_KjU(), composerStartRestartGroup, 432, 0);
            } else {
                composerStartRestartGroup.startReplaceGroup(-1043391057);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (device.isSelf()) {
                composerStartRestartGroup.startReplaceGroup(-1034976324);
                ComposerKt.sourceInformation(composerStartRestartGroup, "202@8482L28,202@8512L17");
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(6)), composerStartRestartGroup, 6);
                Tag("THIS PHONE", composerStartRestartGroup, 6);
            } else {
                composerStartRestartGroup.startReplaceGroup(-1043391057);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (device.isGateway()) {
                composerStartRestartGroup.startReplaceGroup(-1034892128);
                ComposerKt.sourceInformation(composerStartRestartGroup, "203@8567L28,203@8597L13");
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(6)), composerStartRestartGroup, 6);
                Tag("ROUTER", composerStartRestartGroup, 6);
            } else {
                composerStartRestartGroup.startReplaceGroup(-1043391057);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            StringBuilder sb = new StringBuilder();
            sb.append(device.getIp());
            String vendor = row.getDevice().getVendor();
            if (vendor == null) {
                vendor = Kinds.INSTANCE.label(row.getKind());
            }
            sb.append("  ·  ").append(vendor);
            TextKt.m2373Text4IGK_g(sb.toString(), (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3072, 3120, 120754);
            composerStartRestartGroup = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f2)), composerStartRestartGroup, 6);
            Float rttMs = device.getRttMs();
            if (rttMs == null) {
                composerStartRestartGroup.startReplaceGroup(1521446252);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1521446253);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*214@9033L108");
                float fFloatValue = rttMs.floatValue();
                TextKt.m2373Text4IGK_g(fFloatValue < 1.0f ? "<1ms" : ((int) fFloatValue) + "ms", (Modifier) null, Ink.INSTANCE.m6638getTextFaint0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3072, 0, 130994);
                composerStartRestartGroup = composerStartRestartGroup;
                Unit unit = Unit.INSTANCE;
                composerStartRestartGroup.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f2)), composerStartRestartGroup, 6);
            BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f2)), RoundedCornerShapeKt.getCircleShape()), Ink.INSTANCE.m6631getOnline0d7_KjU(), null, 2, null), composerStartRestartGroup, 0);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.DeviceRow$lambda$62(row, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DeviceRow$lambda$55$lambda$54(Function1 function1, Row row) {
        function1.invoke(row.getId());
        return Unit.INSTANCE;
    }

    public static final void Tag(final String text, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer composerStartRestartGroup = composer.startRestartGroup(1979107769);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Tag)223@9312L178:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changed(text) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1979107769, i2, -1, "com.mrnice.pixelscan.Tag (MainActivity.kt:222)");
            }
            Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(4))), Ink.INSTANCE.m6633getRedDim0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(5), Dp.m6299constructorimpl(2));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1733528218, "C224@9410L74:MainActivity.kt#woosis");
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g(text, (Modifier) null, Ink.INSTANCE.m6632getRed0d7_KjU(), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, (i2 & 14) | 199680, 0, 131026);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.Tag$lambda$64(text, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ScanButton(final boolean z, final float f, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-22124115);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScanButton)P(2,1)230@9591L478:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i2 & 131) == 130 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-22124115, i2, -1, "com.mrnice.pixelscan.ScanButton (MainActivity.kt:229)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(48)), RoundedCornerShapeKt.getCircleShape());
            Ink ink = Ink.INSTANCE;
            Modifier modifierM269clickableXHw0xAI$default = ClickableKt.m269clickableXHw0xAI$default(BackgroundKt.m236backgroundbw27NRU$default(modifierClip, z ? ink.m6635getSurfaceHi0d7_KjU() : ink.m6632getRed0d7_KjU(), null, 2, null), false, null, null, function0, 7, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM269clickableXHw0xAI$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1817379592, "C:MainActivity.kt#woosis");
            if (z) {
                composerStartRestartGroup.startReplaceGroup(1817389573);
                ComposerKt.sourceInformation(composerStartRestartGroup, "235@9794L84,236@9891L81");
                ProgressIndicatorKt.m2047CircularProgressIndicatorLxG7B9w(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(24)), Ink.INSTANCE.m6632getRed0d7_KjU(), Dp.m6299constructorimpl(2), 0L, 0, composerStartRestartGroup, 390, 24);
                composerStartRestartGroup = composerStartRestartGroup;
                IconKt.m1830Iconww6aTOc(StopKt.getStop(Icons.Filled.INSTANCE), "stop", SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(16)), Ink.INSTANCE.m6636getText0d7_KjU(), composerStartRestartGroup, 432, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1817591972);
                ComposerKt.sourceInformation(composerStartRestartGroup, "238@10002L51");
                IconKt.m1830Iconww6aTOc(RefreshKt.getRefresh(Icons.Filled.INSTANCE), "scan", (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), composerStartRestartGroup, 48, 4);
                composerStartRestartGroup.endReplaceGroup();
            }
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScanButton$lambda$66(z, f, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void EmptyState(final String str, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1371139969);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(EmptyState)245@10127L318:MainActivity.kt#woosis");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1371139969, i2, -1, "com.mrnice.pixelscan.EmptyState (MainActivity.kt:244)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxSize$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 56692842, "C246@10183L256:MainActivity.kt#woosis");
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -883210413, "C247@10256L87,248@10356L30,249@10399L30:MainActivity.kt#woosis");
            IconKt.m1830Iconww6aTOc(WifiOffKt.getWifiOff(Icons.Filled.INSTANCE), (String) null, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(48)), Ink.INSTANCE.m6638getTextFaint0d7_KjU(), composerStartRestartGroup, 432, 0);
            SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(12)), composerStartRestartGroup, 6);
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g(str, (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, i2 & 14, 0, 131066);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.EmptyState$lambda$69(str, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TextFieldColors fieldColors(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1989417753, "C(fieldColors)255@10507L247:MainActivity.kt#woosis");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1989417753, i, -1, "com.mrnice.pixelscan.fieldColors (MainActivity.kt:255)");
        }
        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
        long jM6632getRed0d7_KjU = Ink.INSTANCE.m6632getRed0d7_KjU();
        long jM6629getHairline0d7_KjU = Ink.INSTANCE.m6629getHairline0d7_KjU();
        TextFieldColors textFieldColorsM2023colors0hiis_0 = outlinedTextFieldDefaults.m2023colors0hiis_0(Ink.INSTANCE.m6636getText0d7_KjU(), Ink.INSTANCE.m6636getText0d7_KjU(), 0L, 0L, Ink.INSTANCE.m6634getSurface0d7_KjU(), Ink.INSTANCE.m6634getSurface0d7_KjU(), 0L, 0L, Ink.INSTANCE.m6632getRed0d7_KjU(), 0L, null, jM6632getRed0d7_KjU, jM6629getHairline0d7_KjU, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 0, 0, 0, 0, 3072, 2147477196, 4095);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return textFieldColorsM2023colors0hiis_0;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0380 A[LOOP:1: B:66:0x037a->B:68:0x0380, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x051c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void ScanProblemCard(ScanState scanState, Composer composer, final int i) {
        String str;
        int currentCompositeKeyHash;
        Composer composerM3333constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        LanInfo lan;
        Composer composer2;
        Composer composer3;
        Iterator<T> it;
        int currentCompositeKeyHash2;
        Composer composerM3333constructorimpl2;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2;
        final RowScopeInstance rowScopeInstance;
        boolean zChangedInstance;
        Object objRememberedValue;
        final ScanState scanState2;
        boolean zChangedInstance2;
        Object objRememberedValue2;
        Composer composer4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-15274858);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScanProblemCard)266@10967L7,275@11667L1638:MainActivity.kt#woosis");
        int i2 = (i & 6) == 0 ? i | (composerStartRestartGroup.changedInstance(scanState) ? 4 : 2) : i;
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            scanState2 = scanState;
            composer4 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-15274858, i2, -1, "com.mrnice.pixelscan.ScanProblemCard (MainActivity.kt:265)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ScanDiag diag = scanState.getDiag();
            if (diag.getTcpDenied() > 0) {
                str = "Android is blocking this app's local-network traffic. Open App info → Permissions and allow Nearby devices / local network.";
                String str2 = str;
                float f = 12;
                float f2 = 4;
                Modifier modifierM681padding3ABfNKs = PaddingKt.m681padding3ABfNKs(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(PaddingKt.m682paddingVpY3zN4(Modifier.INSTANCE, Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(f2)), 0.0f, 1, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f))), Ink.INSTANCE.m6633getRedDim0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM681padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.useNode();
                } else {
                    composerStartRestartGroup.createNode(constructor);
                }
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1359869975, "C278@11815L82,279@11906L29,280@11944L48,281@12001L29,287@12372L130,289@12608L691:MainActivity.kt#woosis");
                TextKt.m2373Text4IGK_g("Scan found only this phone", (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 196614, 0, 131034);
                SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f2)), composerStartRestartGroup, 6);
                TextKt.m2373Text4IGK_g(str2, (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3072, 0, 131058);
                SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(8)), composerStartRestartGroup, 6);
                lan = scanState.getLan();
                if (lan != null) {
                    composerStartRestartGroup.startReplaceGroup(1360062948);
                    composerStartRestartGroup.endReplaceGroup();
                    composer2 = composerStartRestartGroup;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1360062949);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*283@12064L289");
                    composer2 = composerStartRestartGroup;
                    TextKt.m2373Text4IGK_g(lan.getTransport() + " " + lan.getIp() + "/" + lan.getPrefixLen() + " via " + lan.getGateway() + " · dns " + CollectionsKt.joinToString$default(lan.getDnsServers(), null, null, null, 0, null, null, 63, null) + (lan.getVpnActive() ? " · VPN up" : "") + (scanState.getBoundToLan() ? " · bound" : " · not bound"), (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 3072, 0, 130994);
                    Unit unit = Unit.INSTANCE;
                    composer2.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                }
                TextKt.m2373Text4IGK_g("local-network permission: " + NetInfo.INSTANCE.localNetworkPermission(context), (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 3072, 0, 130994);
                composer3 = composer2;
                composer3.startReplaceGroup(-1341585362);
                ComposerKt.sourceInformation(composer3, "*288@12531L66");
                it = diag.lines().iterator();
                while (it.hasNext()) {
                    Composer composer5 = composer3;
                    TextKt.m2373Text4IGK_g((String) it.next(), (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer5, 3072, 0, 130994);
                    composer3 = composer5;
                }
                composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, companion);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                    composer3.useNode();
                } else {
                    composer3.createNode(constructor2);
                }
                composerM3333constructorimpl2 = Updater.m3333constructorimpl(composer3);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, -1211064944, "C290@12647L273,290@12626L333,296@12993L249,296@12972L317:MainActivity.kt#woosis");
                ComposerKt.sourceInformationMarkerStart(composer3, -1978728979, "CC(remember):MainActivity.kt#9igjgp");
                zChangedInstance = composer3.changedInstance(context);
                objRememberedValue = composer3.rememberedValue();
                if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScanProblemCard$lambda$79$lambda$78$lambda$75$lambda$74(rowScopeInstance, context);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1638827871$app_release(), composer3, 805306368, 510);
                ComposerKt.sourceInformationMarkerStart(composer3, -1978717931, "CC(remember):MainActivity.kt#9igjgp");
                scanState2 = scanState;
                zChangedInstance2 = composer3.changedInstance(context) | composer3.changedInstance(scanState2);
                objRememberedValue2 = composer3.rememberedValue();
                if (!zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScanProblemCard$lambda$79$lambda$78$lambda$77$lambda$76(context, scanState2);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1244671432$app_release(), composer3, 805306368, 510);
                composer4 = composer3;
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (diag.getUdpErrors() > 0) {
                    List<String> errors = diag.getErrors();
                    if (!(errors instanceof Collection) || !errors.isEmpty()) {
                        for (String str3 : errors) {
                            if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "ermission", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "not permitted", false, 2, (Object) null)) {
                                str = "Android is blocking this app's local-network traffic. Open App info → Permissions and allow Nearby devices / local network.";
                                break;
                            }
                        }
                    }
                }
                str = diag.getLanLooksBlocked() ? "Nothing on the LAN answered at all — every connection timed out. That's what Android does when local-network access is blocked (packets are dropped silently). Check App info → Permissions → Nearby devices, and any VPN's \"allow LAN\" setting." : "Only this phone answered. Pull diagnostics below and send them to Claude.";
                String str22 = str;
                float f3 = 12;
                float f22 = 4;
                Modifier modifierM681padding3ABfNKs2 = PaddingKt.m681padding3ABfNKs(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(PaddingKt.m682paddingVpY3zN4(Modifier.INSTANCE, Dp.m6299constructorimpl(f3), Dp.m6299constructorimpl(f22)), 0.0f, 1, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f3))), Ink.INSTANCE.m6633getRedDim0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM681padding3ABfNKs2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                }
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting()) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1359869975, "C278@11815L82,279@11906L29,280@11944L48,281@12001L29,287@12372L130,289@12608L691:MainActivity.kt#woosis");
                    TextKt.m2373Text4IGK_g("Scan found only this phone", (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 196614, 0, 131034);
                    SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f22)), composerStartRestartGroup, 6);
                    TextKt.m2373Text4IGK_g(str22, (Modifier) null, Ink.INSTANCE.m6636getText0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3072, 0, 131058);
                    SpacerKt.Spacer(SizeKt.m712height3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(8)), composerStartRestartGroup, 6);
                    lan = scanState.getLan();
                    if (lan != null) {
                    }
                    TextKt.m2373Text4IGK_g("local-network permission: " + NetInfo.INSTANCE.localNetworkPermission(context), (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) ThemeKt.getMono(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 3072, 0, 130994);
                    composer3 = composer2;
                    composer3.startReplaceGroup(-1341585362);
                    ComposerKt.sourceInformation(composer3, "*288@12531L66");
                    it = diag.lines().iterator();
                    while (it.hasNext()) {
                    }
                    composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap22 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier22 = ComposedModifierKt.materializeModifier(composer3, companion2);
                    Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                    }
                    composerM3333constructorimpl2 = Updater.m3333constructorimpl(composer3);
                    Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl2.getInserting()) {
                        composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        rowScopeInstance = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -1211064944, "C290@12647L273,290@12626L333,296@12993L249,296@12972L317:MainActivity.kt#woosis");
                        ComposerKt.sourceInformationMarkerStart(composer3, -1978728979, "CC(remember):MainActivity.kt#9igjgp");
                        zChangedInstance = composer3.changedInstance(context);
                        objRememberedValue = composer3.rememberedValue();
                        if (!zChangedInstance) {
                            objRememberedValue = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda6
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.ScanProblemCard$lambda$79$lambda$78$lambda$75$lambda$74(rowScopeInstance, context);
                                }
                            };
                            composer3.updateRememberedValue(objRememberedValue);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1638827871$app_release(), composer3, 805306368, 510);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1978717931, "CC(remember):MainActivity.kt#9igjgp");
                            scanState2 = scanState;
                            zChangedInstance2 = composer3.changedInstance(context) | composer3.changedInstance(scanState2);
                            objRememberedValue2 = composer3.rememberedValue();
                            if (!zChangedInstance2) {
                                objRememberedValue2 = new Function0() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda7
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return MainActivityKt.ScanProblemCard$lambda$79$lambda$78$lambda$77$lambda$76(context, scanState2);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue2);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1244671432$app_release(), composer3, 805306368, 510);
                                composer4 = composer3;
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                            }
                        }
                    }
                }
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer4.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.MainActivityKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScanProblemCard$lambda$80(scanState2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScanProblemCard$lambda$79$lambda$78$lambda$75$lambda$74(RowScope rowScope, Context context) {
        try {
            Result.Companion companion = Result.INSTANCE;
            context.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", context.getPackageName(), null)));
            Result.m6658constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6658constructorimpl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScanProblemCard$lambda$79$lambda$78$lambda$77$lambda$76(Context context, ScanState scanState) {
        Object systemService = context.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("pixelscan diag", diagText(scanState, context)));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String diagText(ScanState s, Context ctx) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        StringBuilder sb = new StringBuilder();
        sb.append("PixelScan 0.1.1 on Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ") " + Build.MODEL).append('\n');
        LanInfo lan = s.getLan();
        if (lan != null) {
            if (sb.append("net: " + lan.getTransport() + " " + lan.getIp() + "/" + lan.getPrefixLen() + " gw=" + lan.getGateway() + " dns=" + lan.getDnsServers() + " if=" + lan.getInterfaceName() + " vpn=" + lan.getVpnActive() + " bound=" + s.getBoundToLan()).append('\n') == null) {
                sb.append("net: none\n");
            }
        }
        sb.append("local-network permission: " + NetInfo.INSTANCE.localNetworkPermission(ctx)).append('\n');
        sb.append("devices: " + s.getRows().size() + ", scan " + s.getElapsedMs() + " ms").append('\n');
        Iterator<T> it = s.getDiag().lines().iterator();
        while (it.hasNext()) {
            sb.append((String) it.next()).append('\n');
        }
        return sb.toString();
    }
}
