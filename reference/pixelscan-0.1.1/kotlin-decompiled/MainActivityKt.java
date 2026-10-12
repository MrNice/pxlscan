package com.mrnice.pixelscan;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
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
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material.icons.filled.SensorsKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material.icons.filled.StopKt;
import androidx.compose.material.icons.filled.ViewListKt;
import androidx.compose.material.icons.filled.WifiOffKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.NavigationBarItemDefaults;
import androidx.compose.material3.NavigationBarKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.app.NotificationCompat;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

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
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void App(com.mrnice.pixelscan.ScanViewModel r18, androidx.compose.runtime.Composer r19, final int r20, final int r21) {
        /*
            Method dump skipped, instructions count: 511
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.MainActivityKt.App(com.mrnice.pixelscan.ScanViewModel, androidx.compose.runtime.Composer, int, int):void");
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
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DevicesScreen(final com.mrnice.pixelscan.ScanViewModel r49, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r50, androidx.compose.runtime.Composer r51, final int r52) {
        /*
            Method dump skipped, instructions count: 2310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.MainActivityKt.DevicesScreen(com.mrnice.pixelscan.ScanViewModel, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):void");
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
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void ScanProblemCard(com.mrnice.pixelscan.ScanState r38, androidx.compose.runtime.Composer r39, final int r40) {
        /*
            Method dump skipped, instructions count: 1328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.MainActivityKt.ScanProblemCard(com.mrnice.pixelscan.ScanState, androidx.compose.runtime.Composer, int):void");
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
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String diagText(com.mrnice.pixelscan.ScanState r12, android.content.Context r13) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrnice.pixelscan.MainActivityKt.diagText(com.mrnice.pixelscan.ScanState, android.content.Context):java.lang.String");
    }
}
