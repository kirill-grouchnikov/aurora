/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.ColorSelectorCommandButtonProjection
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

@ExperimentalUnitApi
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

@ExperimentalUnitApi
@Composable
fun ColorSelectorButton(
    colorData: ColorData,
    onColorDataChanged: (ColorData) -> Unit,
    resourceBundle: ResourceBundle
) {
    val selectorModel = ColorSelectorMenuContentModel(
        menuGroups = listOf(),
        onColorActivated = {
            colorData.copy(
                isInPreview = false,
                permanentColor = it
            )
        },
        onColorPreviewActivated = object: ColorPreviewListener {
            override fun onColorPreviewActivated(color: Color) {
                onColorDataChanged.invoke(
                    colorData.copy(
                        isInPreview = true,
                        previewColor = color
                    )
                )
            }

            override fun onColorPreviewCanceled() {
                onColorDataChanged.invoke(colorData.copy(isInPreview = false))
            }
        }
    )

    val colorSelectorCommand = ColorSelectorCommand(
        text = "",
        icon = ColorSolidIcon(colorData.permanentColor),
        secondaryContentModel = selectorModel
    )

    ColorSelectorCommandButtonProjection(
        contentModel = colorSelectorCommand,
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium
        )
    ).project()
}

@ExperimentalUnitApi
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

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
            AuroraSkinSwitcher(onSkinChange)

            Spacer(modifier = Modifier.width(8.dp))

            AuroraLocaleSwitcher(resourceBundle)

            Spacer(modifier = Modifier.width(8.dp))

            ColorSelectorButton(
                colorData = colorData,
                onColorDataChanged = { colorData = it },
                resourceBundle = resourceBundle
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Animate color to preview / permanent based on the preview state
        val color = animateColorAsState(
            targetValue = if (colorData.isInPreview) colorData.previewColor else colorData.permanentColor
        )

        DemoStyleCanvas(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            topColor = color.value,
            bottomColor = color.value
        )
    }
}



