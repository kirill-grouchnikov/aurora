/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.LuminousOutlinePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.LuminousSurfacePainter
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.shaper.PillButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun mistBaseSkinColors(accentContainerColorTokens: AccentContainerColorTokens): AuroraSkinColors {
    val result = AuroraSkinColors()
    
    val mistDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = accentContainerColorTokens.defaultAreaActiveTokens!!,
        mutedContainerTokens = accentContainerColorTokens.defaultAreaMutedTokens!!,
        neutralContainerTokens = accentContainerColorTokens.defaultAreaNeutralTokens!!,
        isSystemDark = false)
    mistDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected, ComponentState.Selected,
        ComponentState.RolloverSelected)
    mistDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected)
    result.registerDecorationAreaTokensBundle(mistDefaultBundle, DecorationAreaType.None)

    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFB9C0C8u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.4
            )),
        DecorationAreaType.TitlePane, DecorationAreaType.Header,
        DecorationAreaType.Toolbar, DecorationAreaType.Footer)

    result.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFCDD8E0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.ControlPane)

    return result
}

private fun mistBasePainters(): AuroraPainters {
    return AuroraPainters(
        decorationPainter = MatteDecorationPainter(),
        surfacePainter = LuminousSurfacePainter(),
        outlinePainter = LuminousOutlinePainter(),
        highlightSurfacePainter = LuminousSurfacePainter(
            base = FractionBasedSurfacePainter(
                ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurface),
                ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
                displayName = "Mist Highlight Surface Base"
            ),
            baseAlpha = 1.0f,
            query = { tokens ->
                if (tokens.isDark) {
                    tokens.containerSurfaceHighest
                } else {
                    tokens.containerSurfaceLow
                }
            }
        ),
        highlightOutlinePainter = FlatOutlinePainter(),
    )
}

fun mistSilverSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD4DEE5u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaMutedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD6D9DDu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBF0F4u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFD0B18Bu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    return AuroraSkinDefinition(
        displayName = "Mist Silver",
        colors = mistBaseSkinColors(accentContainerColorTokens),
        painters = mistBasePainters(),
        buttonShaper = PillButtonShaper()
    )
}

fun mistAquaSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaActiveTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF8ACBE9u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaMutedTokens= getContainerTokens(
            seed = Hct.fromInt(0xFFD6D9DDu.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaNeutralTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBF0F4u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6)),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF8CC7E1u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    return AuroraSkinDefinition(
        displayName = "Mist Aqua",
        colors = mistBaseSkinColors(accentContainerColorTokens),
        painters = mistBasePainters(),
        buttonShaper = PillButtonShaper()
    )
}

