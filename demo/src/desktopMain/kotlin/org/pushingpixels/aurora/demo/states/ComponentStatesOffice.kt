/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.painter.border.*
import org.pushingpixels.aurora.theming.painter.decoration.MatteDecorationPainter
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemes
import org.pushingpixels.aurora.window.*
import org.pushingpixels.aurora.window.auroraApplication

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
                minWidth = 72.dp
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
    val officeSchemes = getColorSchemes(
        AuroraSkin::class.java.getResourceAsStream(
            "/org/pushingpixels/aurora/demo/office2007.colorschemes"
        )
    )
    val activeScheme = officeSchemes["Office Silver Active"]
    val enabledScheme = officeSchemes["Office Silver Enabled"]

    val bundle = AuroraColorSchemeBundle(activeScheme, enabledScheme, enabledScheme)
    bundle.registerAlpha(
        0.5f, ComponentState.DisabledUnselected, ComponentState.DisabledSelected
    )
    bundle.registerColorScheme(
        enabledScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledUnselected
    )
    bundle.registerColorScheme(
        activeScheme,
        ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )
    val rolloverScheme = officeSchemes["Office Silver Rollover"]
    val rolloverSelectedScheme = officeSchemes["Office Silver Rollover Selected"]
    val selectedScheme = officeSchemes["Office Silver Selected"]
    val pressedScheme = officeSchemes["Office Silver Pressed"]
    val pressedSelectedScheme = officeSchemes["Office Silver Pressed Selected"]

    val borderEnabledScheme = officeSchemes["Office Silver Border Enabled"]
    val borderRolloverScheme = officeSchemes["Office Border Rollover"]
    val borderRolloverSelectedScheme = officeSchemes["Office Border Rollover Selected"]
    val borderSelectedScheme = officeSchemes["Office Border Selected"]
    val borderPressedScheme = officeSchemes["Office Border Pressed"]

    bundle.registerColorScheme(
        rolloverScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverUnselected
    )
    bundle.registerColorScheme(
        rolloverSelectedScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.RolloverSelected
    )
    bundle.registerColorScheme(
        selectedScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.Selected
    )
    bundle.registerColorScheme(
        pressedScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.PressedUnselected
    )
    bundle.registerColorScheme(
        pressedSelectedScheme, ColorSchemeAssociationKind.Fill,
        ComponentState.PressedSelected
    )
    bundle.registerColorScheme(
        selectedScheme.tone(0.2f), ColorSchemeAssociationKind.Fill,
        ComponentState.DisabledSelected
    )

    bundle.registerColorScheme(
        borderEnabledScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )
    bundle.registerColorScheme(
        borderEnabledScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.DisabledSelected,
        ComponentState.DisabledUnselected
    )
    bundle.registerColorScheme(
        borderRolloverScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.RolloverUnselected
    )
    bundle.registerColorScheme(
        borderRolloverSelectedScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.RolloverSelected
    )
    bundle.registerColorScheme(
        borderSelectedScheme,
        ColorSchemeAssociationKind.Border, ComponentState.Selected
    )
    bundle.registerColorScheme(
        borderPressedScheme,
        ColorSchemeAssociationKind.Border,
        ComponentState.PressedSelected,
        ComponentState.PressedUnselected
    )

    officeColors.registerDecorationAreaSchemeBundle(bundle, DecorationAreaType.None)

    val outerBorderPainter = FractionBasedBorderPainter(
        0.0f to { it.lightColor },
        0.5f to { it.ultraDarkColor },
        1.0f to { it.midColor },
        displayName = "Office Outer"
    )

    return AuroraSkinDefinition(
        displayName = "Office Silver",
        colors = officeColors,
        painters = AuroraPainters(
            fillPainter = FractionBasedFillPainter(
                0.0f to { it.ultraLightColor },
                0.499999f to { it.lightColor },
                0.5f to { it.ultraDarkColor },
                1.0f to { it.extraLightColor },
                displayName = "Office"
            ),
            borderPainter = CompositeBorderPainter(
                displayName = "Office",
                outer = outerBorderPainter,
                inner = DelegateFractionBasedBorderPainter(
                    displayName = "Office Inner",
                    delegate = outerBorderPainter,
                    masks = longArrayOf(0xFFFFFFFFL, 0xFFFFFFFFL, 0xFFFFFFFFL),
                ) { it.tint(0.5f) }),
            decorationPainter = MatteDecorationPainter()
        ),
        buttonShaper = ClassicButtonShaper()
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

