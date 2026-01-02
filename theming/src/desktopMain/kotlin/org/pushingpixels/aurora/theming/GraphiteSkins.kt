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

import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.FlatDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.AuroraOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.overlay.BottomLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.overlay.TopLineOverlayPainter
import org.pushingpixels.aurora.theming.painter.surface.*
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

private fun getDefaultTokensBundle(accentContainerColorTokens: AccentContainerColorTokens): ContainerColorTokensBundle {
    val graphiteDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF636363u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = accentContainerColorTokens.defaultAreaPaletteColorResolver),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF424242u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = accentContainerColorTokens.defaultAreaPaletteColorResolver),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF424242u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = accentContainerColorTokens.defaultAreaPaletteColorResolver),
        isSystemDark = true)

    graphiteDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaSelectedTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverUnselected, ComponentState.Selected, ComponentState.RolloverSelected)
    // Highlights
    graphiteDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens!!,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates)
    // Tabs
    graphiteDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens,
        associationKind = ContainerColorTokensAssociationKind.Tab,
        ComponentState.Selected, ComponentState.RolloverSelected)
    // Text highlights
    graphiteDefaultBundle.registerActiveContainerTokens(
        colorTokens = accentContainerColorTokens.defaultAreaHighlightTokens,
        associationKind = ContainerColorTokensAssociationKind.HighlightText,
        ComponentState.Selected, ComponentState.RolloverSelected)

    return graphiteDefaultBundle
}

private fun graphiteBasePainters(
    surfacePainter: AuroraSurfacePainter? = null,
    outlinePainter: AuroraOutlinePainter? = null,
    highlightSurfacePainter: AuroraSurfacePainter? = null
): AuroraPainters {
    return AuroraPainters(
        decorationPainter = FlatDecorationPainter(),
        surfacePainter = surfacePainter ?: FractionBasedSurfacePainter(
            ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurfaceHigh),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
            displayName = "Graphite"
        ),
        highlightSurfacePainter = highlightSurfacePainter ?: MatteSurfacePainter(),
        outlinePainter = outlinePainter ?: InlayOutlinePainter(
            displayName = "Graphite",
            outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
            inner = OutlineSpec(
                ColorStop(fraction = 0.0f, alpha = 0.359375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 0.5f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                ColorStop(fraction = 1.0f, alpha = 0.359375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
            )
        ),
        highlightOutlinePainter = FlatOutlinePainter(),
    )
}

fun graphiteSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF606060u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBECF0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens).also {
         it.registerActiveContainerTokens(
             colorTokens = getContainerTokens(
                 seed = Hct.fromInt(0xFFEBECF0u.toInt()),
                 containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ 0.6)),
             associationKind = ContainerColorTokensAssociationKind.Default,
            ComponentState.RolloverUnselected, ComponentState.RolloverSelected)
        it.registerActiveContainerTokens(
            colorTokens = getContainerTokens(
                seed = Hct.fromInt(0xFFACB2B9u.toInt()),
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ 0.6)),
            associationKind = ContainerColorTokensAssociationKind.Default,
            ComponentState.PressedUnselected, ComponentState.PressedSelected)
    }

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    return AuroraSkinDefinition(
        displayName = "Graphite",
        colors = colors,
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteAquaSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3E70FFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF3E70FFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens)

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    return AuroraSkinDefinition(
        displayName = "Graphite Aqua",
        colors = colors,
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteChalkSkin(): AuroraSkinDefinition {
    val paletteColorResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { it.complementaryContainerOutline and 0xA0FFFFFFu.toInt() },
            containerOutlineVariant = { it.complementaryContainerOutline and 0x80FFFFFFu.toInt() },
            complementaryContainerOutline = { it.containerOutline }
        )
    )
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaPaletteColorResolver = paletteColorResolver,
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF606060u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = paletteColorResolver),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBECF0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = paletteColorResolver),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens).also {
        it.registerActiveContainerTokens(
            colorTokens = getContainerTokens(
                seed = Hct.fromInt(0xFFEBECF0u.toInt()),
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ 0.6),
                colorResolver = paletteColorResolver),
            associationKind = ContainerColorTokensAssociationKind.Default,
            ComponentState.RolloverUnselected, ComponentState.RolloverSelected)
        it.registerActiveContainerTokens(
            colorTokens = getContainerTokens(
                seed = Hct.fromInt(0xFFACB2B9u.toInt()),
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ 0.6),
                colorResolver = paletteColorResolver),
            associationKind = ContainerColorTokensAssociationKind.Default,
            ComponentState.PressedUnselected, ComponentState.PressedSelected)
    }

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)
    return AuroraSkinDefinition(
        displayName = "Graphite Chalk",
        colors = colors,
        painters = graphiteBasePainters(outlinePainter = FlatOutlinePainter()),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteGlassSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF606060u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultDark()),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFEBECF0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens).also {
        it.registerActiveContainerTokens(
            colorTokens = getContainerTokens(
                seed = Hct.fromInt(0xFFEBECF0u.toInt()),
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ 0.6)),
            associationKind = ContainerColorTokensAssociationKind.Default,
            ComponentState.RolloverUnselected, ComponentState.RolloverSelected)
        it.registerActiveContainerTokens(
            colorTokens = getContainerTokens(
                seed = Hct.fromInt(0xFFACB2B9u.toInt()),
                containerConfiguration = ContainerConfiguration(
                    /* isDark */ false,
                    /* contrastLevel */ 0.6)),
            associationKind = ContainerColorTokensAssociationKind.Default,
            ComponentState.PressedUnselected, ComponentState.PressedSelected)
    }

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    // Headers
    colors.registerAsDecorationArea(
        getContainerTokens(
            /* seed */ Hct.fromInt(0xFF4F4F4Fu.toInt()),
            /* containerConfiguration */ ContainerConfiguration.defaultDark()),
        DecorationAreaType.TitlePane, DecorationAreaType.Header)

    return AuroraSkinDefinition(
        displayName = "Graphite Glass",
        colors = colors,
        painters = graphiteBasePainters(
            surfacePainter = SpecularRectangularSurfacePainter(
                base = FractionBasedSurfacePainter(
                    ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurfaceHigh),
                    ColorStop(fraction = 0.4999999f, colorQuery = {
                        it.containerSurfaceHigh.interpolateTowards(it.containerSurfaceHighest, 0.5f)
                    }),
                    ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
                    ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurface),
                    displayName = "Graphite Glass"
                )
            ),
            highlightSurfacePainter = GlassSurfacePainter()
        ).also {
            // add two overlay painters to create a bezel line between
            // menu bar and toolbars
            it.addOverlayPainter(BottomLineOverlayPainter(colorTokensQuery = { tokens -> tokens.containerOutline }),
                DecorationAreaType.Header)
            it.addOverlayPainter(TopLineOverlayPainter(colorTokensQuery = {
                tokens -> tokens.inverseContainerOutline.withAlpha(0.375f)
            }), DecorationAreaType.Toolbar)
        },
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteElectricSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF00FF9Cu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.4f },
                    onContainerDisabledAlpha = { 0.8f },
                    containerOutlineDisabledAlpha = { 0.4f },
                )
            )
        ),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFF00FF9Cu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens)

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    return AuroraSkinDefinition(
        displayName = "Graphite Electric",
        colors = colors,
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteGoldSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFFC900u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.4f },
                    onContainerDisabledAlpha = { 0.8f },
                    containerOutlineDisabledAlpha = { 0.4f },
                )
            )
        ),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFFC900u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens)

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    return AuroraSkinDefinition(
        displayName = "Graphite Gold",
        colors = colors,
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteSiennaSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFB27565u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.45f },
                    onContainerDisabledAlpha = { 0.5f },
                    containerOutlineDisabledAlpha = { 0.45f },
                )
            )
        ),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFB27565u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens)

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    return AuroraSkinDefinition(
        displayName = "Graphite Sienna",
        colors = colors,
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

fun graphiteSunsetSkin(): AuroraSkinDefinition {
    val accentContainerColorTokens = AccentContainerColorTokens(
        defaultAreaSelectedTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFF7B00u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerSurfaceDisabledAlpha = { 0.4f },
                    onContainerDisabledAlpha = { 0.8f },
                    containerOutlineDisabledAlpha = { 0.4f },
                )
            )
        ),
        defaultAreaHighlightTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFF7B00u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
    )

    val defaultTokensBundle = getDefaultTokensBundle(accentContainerColorTokens)

    val colors = AuroraSkinColors()
    colors.registerDecorationAreaTokensBundle(defaultTokensBundle, DecorationAreaType.None)

    return AuroraSkinDefinition(
        displayName = "Graphite Sunset",
        colors = colors,
        painters = graphiteBasePainters(),
        buttonShaper = ClassicButtonShaper()
    )
}

