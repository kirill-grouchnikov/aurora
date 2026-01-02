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
package org.pushingpixels.aurora.demo

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.ColorSelectorCommandButtonProjection
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*
import javax.swing.JColorChooser

private val DefaultColor = Color.DarkGray

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(660.dp, 400.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        ColorSelectorDemoContent({ skin = it }, resourceBundle)
    }
}

@Stable
data class ColorData(
    val isInPreview: Boolean,
    val permanentColor: Color,
    val previewColor: Color,
)

@Composable
fun ColorSelectorButton(
    permanentColor: Color,
    colorActivationListener: (Color) -> Unit,
    colorPreviewListener: ColorPreviewListener,
    resourceBundle: ResourceBundle
) {

    val defaultColorCommand = Command(
        text = resourceBundle.getString("ColorSelector.textAutomatic"),
        icon = ColorSolidIcon(DefaultColor),
        action = {
            colorActivationListener.invoke(DefaultColor)
            RecentlyUsedColors.addToRecentlyUsed(DefaultColor)
        },
        actionPreview = object : CommandActionPreview {
            override fun onCommandPreviewActivated(command: BaseCommand) {
                colorPreviewListener.onColorPreviewActivated(DefaultColor)
            }

            override fun onCommandPreviewCanceled(command: BaseCommand) {
                colorPreviewListener.onColorPreviewCanceled(DefaultColor)
            }
        }
    )

    val selectorModel = ColorSelectorMenuContentModel(
        entries = listOf(
            ColorSelectorPopupMenuCommand(defaultColorCommand),
            ColorSelectorPopupMenuSectionWithDerived(
                title = resourceBundle.getString("ColorSelector.textThemeCaption"),
                colors = listOf(
                    Color(255, 255, 255), Color(0, 0, 0),
                    Color(160, 160, 160), Color(16, 64, 128),
                    Color(80, 128, 192), Color(180, 80, 80),
                    Color(160, 192, 80), Color(128, 92, 160),
                    Color(80, 160, 208), Color(255, 144, 64)
                ),
                derivedCount = 8
            ),
            ColorSelectorPopupMenuSection(
                title = resourceBundle.getString("ColorSelector.textStandardCaption"),
                colors = listOf(
                    Color(140, 0, 0), Color(253, 0, 0),
                    Color(255, 160, 0), Color(255, 255, 0),
                    Color(144, 240, 144), Color(0, 128, 0),
                    Color(160, 224, 224), Color(0, 0, 255),
                    Color(0, 0, 128), Color(128, 0, 128)
                )
            ),
            ColorSelectorPopupMenuRecentsSection(
                title = resourceBundle.getString("ColorSelector.textRecentCaption")
            ),
            ColorSelectorPopupMenuCommand(
                command = Command(
                    text = resourceBundle.getString("ColorSelector.textMoreColor"),
                    action = {
                        val awtColor = JColorChooser.showDialog(
                            null,
                            "Color chooser", java.awt.Color(DefaultColor.red, DefaultColor.green, DefaultColor.blue)
                        )
                        if (awtColor != null) {
                            val composeColor = Color(awtColor.red, awtColor.green, awtColor.blue, awtColor.alpha)
                            colorActivationListener.invoke(composeColor)
                            RecentlyUsedColors.addToRecentlyUsed(composeColor)
                        }
                    }
                )
            )
        ),
        onColorActivated = colorActivationListener,
        onColorPreviewActivated = colorPreviewListener
    )

    val colorSelectorCommand = ColorSelectorCommand(
        text = "",
        icon = ColorSolidIcon(permanentColor),
        secondaryContentModel = selectorModel
    )

    ColorSelectorCommandButtonProjection(
        contentModel = colorSelectorCommand,
        presentationModel = ColorSelectorCommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium
        )
    ).project()
}

@Composable
fun AuroraApplicationScope.ColorSelectorDemoContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
) {
    var colorData by remember {
        mutableStateOf(
            ColorData(
                isInPreview = false,
                permanentColor = Color.White,
                previewColor = Color.White
            )
        )
    }

    val colorActivationListener: (Color) -> Unit = {
        colorData = colorData.copy(
            isInPreview = false,
            permanentColor = it
        )
    }
    val colorPreviewListener = object : ColorPreviewListener {
        override fun onColorPreviewActivated(color: Color) {
            colorData = colorData.copy(
                isInPreview = true,
                previewColor = color
            )
        }

        override fun onColorPreviewCanceled(color: Color) {
            // Handle the case where the user moves the mouse between color cells,
            // and we get color preview cancel on the old cell after color preview
            // activation on the new cell. Detect this by looking at the color we are
            // getting in this cancellation and comparing it with the current preview
            // color in our data model. If they don't match, don't do anything.
            if (colorData.isInPreview && (colorData.previewColor == color)) {
                colorData = colorData.copy(isInPreview = false)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
            AuroraDecorationArea(
                decorationAreaType = DecorationAreaType.None,
                buttonShaper = ClassicButtonShaper.Instance
            ) {
                AuroraSkinSwitcher(onSkinChange)

                Spacer(modifier = Modifier.width(8.dp))

                AuroraLocaleSwitcher(resourceBundle)

                Spacer(modifier = Modifier.width(8.dp))

                ColorSelectorButton(
                    permanentColor = colorData.permanentColor,
                    colorActivationListener = colorActivationListener,
                    colorPreviewListener = colorPreviewListener,
                    resourceBundle = resourceBundle
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Animate color to preview / permanent based on the preview state
        val color = animateColorAsState(
            targetValue = if (colorData.isInPreview) colorData.previewColor else colorData.permanentColor
        )

        DemoStyleCanvas(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            color = color.value,
        )
    }
}



