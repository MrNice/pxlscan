package com.mrnice.pixelscan;

import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Typography;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Theme.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"scheme", "Landroidx/compose/material3/ColorScheme;", "Mono", "Landroidx/compose/ui/text/font/GenericFontFamily;", "getMono", "()Landroidx/compose/ui/text/font/GenericFontFamily;", "typography", "Landroidx/compose/material3/Typography;", "PixelScanTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ThemeKt {
    private static final ColorScheme scheme = ColorSchemeKt.m1611darkColorSchemeCXl9yA$default(Ink.INSTANCE.m6632getRed0d7_KjU(), Ink.INSTANCE.m6636getText0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Ink.INSTANCE.m6628getBg0d7_KjU(), Ink.INSTANCE.m6636getText0d7_KjU(), Ink.INSTANCE.m6634getSurface0d7_KjU(), Ink.INSTANCE.m6636getText0d7_KjU(), Ink.INSTANCE.m6635getSurfaceHi0d7_KjU(), Ink.INSTANCE.m6637getTextDim0d7_KjU(), 0, 0, 0, Ink.INSTANCE.m6632getRed0d7_KjU(), 0, 0, 0, Ink.INSTANCE.m6629getHairline0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, -71819268, 15, null);
    private static final GenericFontFamily Mono = FontFamily.INSTANCE.getMonospace();
    private static final Typography typography = new Typography(null, null, null, null, null, null, null, null, null, new TextStyle(0, TextUnitKt.getSp(16), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777213, (DefaultConstructorMarker) null), new TextStyle(0, TextUnitKt.getSp(14), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777213, (DefaultConstructorMarker) null), null, null, null, new TextStyle(0, TextUnitKt.getSp(11), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777213, (DefaultConstructorMarker) null), 14847, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PixelScanTheme$lambda$0(Function2 function2, int i, Composer composer, int i2) {
        PixelScanTheme(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final GenericFontFamily getMono() {
        return Mono;
    }

    public static final void PixelScanTheme(Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1591525938);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PixelScanTheme)52@1634L21,53@1660L79:Theme.kt#woosis");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(content) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            function2 = content;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1591525938, i2, -1, "com.mrnice.pixelscan.PixelScanTheme (Theme.kt:49)");
            }
            DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
            function2 = content;
            MaterialThemeKt.MaterialTheme(scheme, null, typography, function2, composerStartRestartGroup, ((i2 << 9) & 7168) | 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mrnice.pixelscan.ThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.PixelScanTheme$lambda$0(function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
