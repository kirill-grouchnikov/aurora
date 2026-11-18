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
package org.pushingpixels.aurora.demo.filters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.demo.resources.Res
import org.pushingpixels.aurora.filters.lut.cubeTextToHaldImageBitmap
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.businessSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

private val CUBE_FILES = listOf(
    "khronos-pbrNeutral.cube",
    "resolve-DCI-P3 Kodak 2383 D65.cube",
    "tinyglade-Bluecine_75.cube",
    "tinyglade-Cold_Ice.cube",
    "tinyglade-LUNA_COLOR.cube",
    "tinyglade-Sam_Kolder.cube"
)

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 640.dp)
    )

    AuroraWindow(
        skin = businessSkin(),
        title = "Cube LUT → Hald CLUT",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication
    ) {
        var selectedCube by remember { mutableStateOf(CUBE_FILES[0]) }
        var haldBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        LaunchedEffect(selectedCube) {
            try {
                val cubeBytes = Res.readBytes("files/raw/$selectedCube")
                val cubeText = cubeBytes.decodeToString()
                haldBitmap = cubeTextToHaldImageBitmap(cubeText)
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.message ?: e.toString()
                haldBitmap = null
            }
        }

        Box(modifier = Modifier.fillMaxSize().auroraBackground(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
                // ComboBox row
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center) {
                    ComboBoxProjection(
                        contentModel = ComboBoxContentModel(
                            items = CUBE_FILES,
                            selectedItem = selectedCube,
                            onTriggerItemSelectedChange = { newSelection ->
                                selectedCube = newSelection
                            }
                        ),
                        presentationModel = ComboBoxPresentationModel(
                            displayConverter = { it }
                        )
                    ).project(modifier = Modifier.width(350.dp))
                }

                // Image display
                when {
                    haldBitmap != null -> {
                        Image(
                            painter = BitmapPainter(haldBitmap!!),
                            contentDescription = "Hald CLUT from $selectedCube",
                            modifier = Modifier.padding(8.dp),
                            contentScale = ContentScale.Fit
                        )
                        BasicText(
                            text = "512×512 Hald CLUT — $selectedCube",
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                        )
                    }
                    errorMessage != null -> {
                        BasicText(text = "Error: $errorMessage")
                    }
                    else -> {
                        BasicText(text = "Loading...")
                    }
                }
            }
        }
    }
}
