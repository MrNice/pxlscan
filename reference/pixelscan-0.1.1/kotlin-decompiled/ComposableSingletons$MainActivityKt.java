package com.mrnice.pixelscan;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.SearchKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ComposableSingletons$MainActivityKt {
    public static final ComposableSingletons$MainActivityKt INSTANCE = new ComposableSingletons$MainActivityKt();

    /* renamed from: lambda$-466468303, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f70lambda$466468303 = ComposableLambdaKt.composableLambdaInstance(-466468303, false, new Function2() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__466468303$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$176493694 = ComposableLambdaKt.composableLambdaInstance(176493694, false, new Function2() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_176493694$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1411406452 = ComposableLambdaKt.composableLambdaInstance(1411406452, false, new Function2() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1411406452$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1003382837 = ComposableLambdaKt.composableLambdaInstance(1003382837, false, new Function2() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1003382837$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: lambda$-941730050, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f71lambda$941730050 = ComposableLambdaKt.composableLambdaInstance(-941730050, false, new Function3() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda__941730050$lambda$5((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1638827871 = ComposableLambdaKt.composableLambdaInstance(1638827871, false, new Function3() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1638827871$lambda$6((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1244671432 = ComposableLambdaKt.composableLambdaInstance(1244671432, false, new Function3() { // from class: com.mrnice.pixelscan.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1244671432$lambda$7((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: getLambda$-466468303$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6610getLambda$466468303$app_release() {
        return f70lambda$466468303;
    }

    /* renamed from: getLambda$-941730050$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6611getLambda$941730050$app_release() {
        return f71lambda$941730050;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1003382837$app_release() {
        return lambda$1003382837;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1244671432$app_release() {
        return lambda$1244671432;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1411406452$app_release() {
        return lambda$1411406452;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1638827871$app_release() {
        return lambda$1638827871;
    }

    public final Function2<Composer, Integer, Unit> getLambda$176493694$app_release() {
        return lambda$176493694;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_176493694$lambda$1(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C39@1597L24:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(176493694, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$176493694.<anonymous> (MainActivity.kt:39)");
            }
            ThemeKt.PixelScanTheme(f70lambda$466468303, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__466468303$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C39@1614L5:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-466468303, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$-466468303.<anonymous> (MainActivity.kt:39)");
            }
            MainActivityKt.App(null, composer, 0, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1411406452$lambda$2(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C165@6493L54:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1411406452, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$1411406452.<anonymous> (MainActivity.kt:165)");
            }
            TextKt.m2373Text4IGK_g("Search name, IP, maker…", (Modifier) null, Ink.INSTANCE.m6638getTextFaint0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1003382837$lambda$3(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C166@6583L51:MainActivity.kt#woosis");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1003382837, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$1003382837.<anonymous> (MainActivity.kt:166)");
            }
            IconKt.m1830Iconww6aTOc(SearchKt.getSearch(Icons.Filled.INSTANCE), (String) null, (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__941730050$lambda$5(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C180@7294L111:MainActivity.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-941730050, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$-941730050.<anonymous> (MainActivity.kt:180)");
            }
            Modifier modifierM681padding3ABfNKs = PaddingKt.m681padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6299constructorimpl(40));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
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
            ComposerKt.sourceInformationMarkerStart(composer, 619040126, "C180@7358L45:MainActivity.kt#woosis");
            TextKt.m2373Text4IGK_g("No devices found", (Modifier) null, Ink.INSTANCE.m6637getTextDim0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131066);
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
    public static final Unit lambda_1638827871$lambda$6(RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C295@12924L33:MainActivity.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1638827871, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$1638827871.<anonymous> (MainActivity.kt:295)");
            }
            TextKt.m2373Text4IGK_g("App info", (Modifier) null, Ink.INSTANCE.m6632getRed0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1244671432$lambda$7(RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C299@13246L41:MainActivity.kt#woosis");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1244671432, i, -1, "com.mrnice.pixelscan.ComposableSingletons$MainActivityKt.lambda$1244671432.<anonymous> (MainActivity.kt:299)");
            }
            TextKt.m2373Text4IGK_g("Copy diagnostics", (Modifier) null, Ink.INSTANCE.m6632getRed0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
