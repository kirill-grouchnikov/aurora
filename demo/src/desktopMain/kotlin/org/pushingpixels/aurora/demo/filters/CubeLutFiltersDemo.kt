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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.demo.resources.Res
import org.pushingpixels.aurora.filters.lut.LutFilter
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
        size = DpSize(800.dp, 750.dp)
    )

    AuroraWindow(
        skin = businessSkin(),
        title = "Cube LUT Filters Demo",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication
    ) {
        Box(modifier = Modifier.fillMaxSize().auroraBackground()) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            ) {
                item {
                    OriginalBox()
                }

                items(CUBE_FILES.size) { index ->
                    val cubeFile = CUBE_FILES[index]
                    LutFilterBox(
                        cubeFilePath = "files/raw/$cubeFile",
                        renderEffectTitle = cubeFile.removeSuffix(".cube")
                    )
                }
            }
        }
    }
}

@Composable
fun LutFilterBox(modifier: Modifier = Modifier, cubeFilePath: String, renderEffectTitle: String) {
    val haldBitmap = rememberHaldBitmap(cubeFilePath)
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        when (val result = haldBitmap) {
            is HaldResult.Success -> {
                ImageBox(modifier = LutFilter.effectModifier(result.bitmap))
                BasicText(text = renderEffectTitle, modifier = Modifier.padding(all = 8.dp))
            }
            is HaldResult.Error -> {
                BasicText(text = "Error: ${result.message}", modifier = Modifier.padding(all = 8.dp))
            }
            is HaldResult.Loading -> {
                BasicText(text = "Loading...", modifier = Modifier.padding(all = 8.dp))
            }
        }
    }
}

private sealed interface HaldResult {
    data class Success(val bitmap: ImageBitmap) : HaldResult
    data class Error(val message: String) : HaldResult
    data object Loading : HaldResult
}

@Composable
private fun rememberHaldBitmap(cubeFilePath: String): HaldResult {
    var result by remember { mutableStateOf<HaldResult>(HaldResult.Loading) }

    LaunchedEffect(cubeFilePath) {
        try {
            val cubeBytes = Res.readBytes(cubeFilePath)
            val cubeText = cubeBytes.decodeToString()
            val bitmap = cubeTextToHaldImageBitmap(cubeText)
            result = HaldResult.Success(bitmap)
        } catch (e: Exception) {
            result = HaldResult.Error(e.message ?: e.toString())
        }
    }

    return result
}
