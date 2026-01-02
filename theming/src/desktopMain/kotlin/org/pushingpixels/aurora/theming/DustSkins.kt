/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.theming

import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.MatteSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.SpecularRectangularSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun dustBaseSkinColors(accentContainerColorTokens: AccentContainerColorTokens): AuroraSkinColors {
    val result = AuroraSkinColors()

    val dustDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = accentContainerColorTokens.defaultAreaActiveTokens!!,
        mutedContainerTokens = accentContainerColorTokens.defaultAreaMutedTokens!!,
        neutralContainerTokens = accentContainerColorTokens.defaultAreaNeutralTokens!!,
        isSystemDark = false)
    dustDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaSelectedTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    dustDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected, ComponentState.Selected,
        ComponentState.RolloverSelected)
    dustDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected)
    result.registerDecorationAreaTokensBundle(dustDefaultBundle, DecorationAreaType.None)

    val dustHeaderBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF5E3D2Bu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3C3B37u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF2B2A28u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.2)),
        isSystemDark = true)
    dustHeaderBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.headerAreaHighlightTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected, ComponentState.Selected,
        ComponentState.RolloverSelected)
    result.registerDecorationAreaTokensBundle(dustHeaderBundle,
        DecorationAreaType.TitlePane, DecorationAreaType.Header, DecorationAreaType.Footer)

    val dustToolbarBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF5E3D2Bu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3C3B37u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.4)),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3A3935u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        isSystemDark = true)
    dustToolbarBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.headerAreaHighlightTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected, ComponentState.Selected,
        ComponentState.RolloverSelected)
    result.registerDecorationAreaTokensBundle(dustToolbarBundle, DecorationAreaType.Toolbar)

    return result
}

private fun dustBasePainters(): AuroraPainters {
    val outlinePainter = InlayOutlinePainter(
        displayName = "Dust",
        outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
        inner = OutlineSpec(
            ColorStop(fraction = 0.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            ColorStop(fraction = 1.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
        )
    )
    val painters = AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = SpecularRectangularSurfacePainter(MatteSurfacePainter(), 0.3f),
        outlinePainter = outlinePainter,
        highlightSurfacePainter = MatteSurfacePainter(),
        highlightOutlinePainter = outlinePainter,
    )

    // add two overlay painters to create a bezel line between menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter( { it.containerOutline } ),
        DecorationAreaType.Header
    )
    painters.addOverlayPainter(
        TopLineOverlayPainter( { it.inverseContainerOutline.withAlpha(0.375f) } ),
        DecorationAreaType.Toolbar)

    return painters
}

fun dustSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFADA59Au.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFE5E2DBu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEAE7E2u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFAE9B7Au.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFB7A78Eu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.3)),
        headerAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF5E4436u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.3)),
    )

    return AuroraSkinDefinition(
        displayName = "Dust",
        colors = dustBaseSkinColors(accentContainerColorTokens),
        painters = dustBasePainters(),
        buttonShaper = ClassicButtonShaper(),
    )
}

fun dustCoffeeSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDDC49Cu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFDBCFADu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE9D9B8u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFDEBD7Du.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD0B18Bu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.3)),
        headerAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF5E4436u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ true,
                /* contrastLevel */ 0.3)),
    )

    return AuroraSkinDefinition(
        displayName = "Dust Coffee",
        colors = dustBaseSkinColors(accentContainerColorTokens),
        painters = dustBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

