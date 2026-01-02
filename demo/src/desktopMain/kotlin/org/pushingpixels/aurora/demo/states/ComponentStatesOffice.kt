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
package org.pushingpixels.aurora.demo.states

import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.ColorStop
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.InlayOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSpec
import org.pushingpixels.aurora.theming.painter.surface.ClassicSurfacePainter
import org.pushingpixels.aurora.theming.painter.surface.FractionBasedSurfacePainter
import org.pushingpixels.aurora.theming.palette.*
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.dynamiccolor.DynamicBimodalPalette
import org.pushingpixels.ephemeral.chroma.hct.Hct

@Composable
private fun StateRow(
    label: String,
    rollover: Boolean = false,
    selected: Boolean = false,
    pressed: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth(0.6f).padding(end = 8.dp)) {
            LabelProjection(contentModel = LabelContentModel(text = label)).project(
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        val actionInteractionSource = remember { MutableInteractionSource() }
        CommandButtonProjection(
            contentModel = Command(
                text = "sample",
                action = {},
                isActionToggle = selected,
                isActionToggleSelected = selected
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.Medium,
                contentPadding = PaddingValues(start = 20.dp, top = 3.dp, end = 20.dp, bottom = 4.dp)
            )
        ).project(
            modifier = Modifier.fillMaxWidth(1.0f),
            actionInteractionSource = actionInteractionSource
        )
        LaunchedEffect(Unit) {
            if (rollover) {
                actionInteractionSource.emit(HoverInteraction.Enter())
            }
            if (pressed) {
                actionInteractionSource.emit(PressInteraction.Press(Offset(5.0f, 5.0f)))
            }
        }
    }
}

private fun officeSkin(): AuroraSkinDefinition {
    val officeColors = AuroraSkinColors()
    
    val officeSilverDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFC6CACFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        mutedContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFE6EAEEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        neutralContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFF2F5F5u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        isSystemDark = false)

    val rolloverContainerTokens =
        getContainerTokens(
            seed = Hct.fromInt(0xFFFFD111u.toInt()),
            containerConfiguration = ContainerConfiguration(
                /* isDark */ false,
                /* contrastLevel */ 0.6),
            colorResolver = DefaultPaletteColorResolver.overlayWith(
                TokenPaletteColorResolverOverlay(
                    containerOutline = { it.containerOutlineVariant },
                    containerOutlineVariant = { it.containerOutlineVariant },
                )
            ))
    val selectedContainerTokens = getBimodalContainerTokens(
        seedOne = Hct.fromInt(0xFFFFA300u.toInt()),
        seedTwo = Hct.fromInt(0xFFFFD007u.toInt()),
        transitionRange = DynamicBimodalPalette.TransitionRange.TONAL_CONTAINER_SURFACES,
        fidelityTone = 83.0,
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.2,
            /* surfaceRangeAmplitudeFactor */ 1.0),
        colorResolver = DefaultPaletteColorResolver
    )
    val rolloverSelectedContainerTokens = getBimodalContainerTokens(
        seedOne = Hct.fromInt(0xFFFFA300u.toInt()),
        seedTwo = Hct.fromInt(0xFFFFD007u.toInt()),
        transitionRange = DynamicBimodalPalette.TransitionRange.TONAL_CONTAINER_SURFACES,
        fidelityTone = 79.0,
        containerConfiguration = ContainerConfiguration(
            /* isDark */ false,
            /* contrastLevel */ 0.2,
            /* surfaceRangeAmplitudeFactor */ 1.0),
        colorResolver = DefaultPaletteColorResolver
    )
    val pressedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFF8C18u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())
    val pressedSelectedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFF991Cu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight())

    // register state-specific color tokens on rollovers, presses and selections
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = rolloverContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverUnselected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = rolloverSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.RolloverSelected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = selectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.Selected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = pressedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.PressedUnselected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = pressedSelectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Default,
        ComponentState.PressedSelected)

    // register state-specific highlight color tokens on rollover and selections
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = rolloverContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverUnselected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = selectedContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        ComponentState.Selected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = rolloverSelectedContainerTokens,
        ContainerColorTokensAssociationKind.Highlight,
        ComponentState.RolloverSelected)

    val activeMarksColorResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            onContainer = { it.containerOutline },
        )
    )

    val rolloverMarkContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFD111u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = activeMarksColorResolver)
    val selectedMarkContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFBD51u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = activeMarksColorResolver)
    val rolloverSelectedMarkContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFA400u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = activeMarksColorResolver)
    val pressedMarkContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFF8C18u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = activeMarksColorResolver)
    val pressedSelectedMarkContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFF991Cu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = activeMarksColorResolver)

    // register state-specific color tokens on mark rollovers, presses and selections
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = rolloverMarkContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Mark,
        ComponentState.RolloverUnselected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = rolloverSelectedMarkContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Mark,
        ComponentState.RolloverSelected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = selectedMarkContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Mark,
        ComponentState.Selected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = pressedMarkContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Mark,
        ComponentState.PressedUnselected)
    officeSilverDefaultBundle.registerActiveContainerTokens(
        colorTokens = pressedSelectedMarkContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Mark,
        ComponentState.PressedSelected)

    officeColors.registerDecorationAreaTokensBundle(officeSilverDefaultBundle, DecorationAreaType.None)

    officeColors.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFCFD4DEu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.Header, DecorationAreaType.Toolbar, DecorationAreaType.Footer)

    officeColors.registerAsDecorationArea(
        getContainerTokens(
            seed = Hct.fromInt(0xFFCFCFD0u.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight()),
        DecorationAreaType.TitlePane, DecorationAreaType.ControlPane)

    return AuroraSkinDefinition(
        displayName = "Office Silver",
        colors = officeColors,
        painters = AuroraPainters(
            decorationPainter = MatteDecorationPainter(),
            surfacePainter = FractionBasedSurfacePainter(
                ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerSurfaceLow),
                ColorStop(fraction = 0.49999f, colorQuery = {
                    it.containerSurfaceLow.interpolateTowards(it.containerSurfaceLowest, 0.7f)
                }),
                ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
                ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerSurfaceLow),
                displayName = "Office Silver"
            ),
            highlightSurfacePainter = ClassicSurfacePainter(),
            outlinePainter = InlayOutlinePainter(
                displayName = "Office Silver",
                outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
                inner = OutlineSpec(
                    ColorStop(fraction = 0.0f, alpha = 0.9375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                    ColorStop(fraction = 1.0f, alpha = 0.9375f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
                )
            ),
            highlightOutlinePainter = FlatOutlinePainter(),
        ),
        buttonShaper = ClassicButtonShaper(),
    )
}

@ExperimentalComposeUiApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(260.dp, 240.dp)
    )

    AuroraWindow(
        skin = officeSkin(),
        title = "States",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StateRow("Regular")
            StateRow("Rollover", rollover = true)
            StateRow("Selected", selected = true)
            StateRow("Rollover selected", rollover = true, selected = true)
            StateRow("Pressed", pressed = true)
            StateRow("Pressed selected", pressed = true, selected = true)
        }
    }
}

