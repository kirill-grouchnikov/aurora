/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.*
import java.io.File
import java.util.*

private class ScreenshotScope(
    private val applicationScope: AuroraApplicationScope,
    original: WindowScope
) : AuroraWindowScope {
    override var applicationLocale: Locale
        get() = applicationScope.applicationLocale
        set(value) {
            applicationScope.applicationLocale = value
        }

    override val window = original.window
}

@Composable
fun AuroraApplicationScope.ScreenshotWindow(
    windowScope: WindowScope,
    skin: AuroraSkinDefinition,
    state: WindowState,
    title: String,
    icon: Painter,
    content: @Composable AuroraWindowScope.() -> Unit
) {
    val density = LocalDensity.current
    val screenshotScope = ScreenshotScope(this@ScreenshotWindow, windowScope)
    CompositionLocalProvider(
        LocalWindowSize provides state.size,
        LocalDensity provides density,
        LocalDecorationAreaType provides DecorationAreaType.None,
        LocalDisplayName provides skin.displayName,
        LocalSkinColors provides skin.colors,
        LocalButtonShaper provides skin.buttonShaper,
        LocalPainters provides skin.painters,
        LocalAnimationConfig provides AnimationConfig(),
    ) {
        screenshotScope.AuroraWindowContent(
            title = title,
            icon = icon,
            iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
            undecorated = true,
            menuCommands = null,
            content = content
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    screenshot(twilightSkin(), "/Users/kirillg/twilight.png")
    screenshot(marinerSkin(), "/Users/kirillg/mariner.png")
}

@OptIn(ExperimentalComposeUiApi::class)
fun screenshot(
    skin: AuroraSkinDefinition,
    filename: String
) = auroraApplication {
    val title = "Aurora"
    val icon = radiance_menu()
    val size = DpSize(220.dp, 200.dp)
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = size
    )

    val density = LocalDensity.current
    AuroraWindow(
        skin = skin,
        title = title,
        state = state,
        undecorated = true,
        icon = icon,
        onCloseRequest = ::exitApplication
    ) {
        val scene = ImageComposeScene(
            width = (size.width.value * density.density).toInt(),
            height = (size.height.value * density.density).toInt()
        ) {
            CompositionLocalProvider(
                LocalWindow provides (window as ComposeWindow),
                LocalWindowSize provides size,
                LocalDensity provides density,
                LocalDecorationAreaType provides DecorationAreaType.None,
                LocalDisplayName provides skin.displayName,
                LocalSkinColors provides skin.colors,
                LocalButtonShaper provides skin.buttonShaper,
                LocalPainters provides skin.painters,
                LocalAnimationConfig provides AnimationConfig(),
            ) {
                ScreenshotWindow(
                    windowScope = this,
                    skin = skin,
                    state = state,
                    title = title,
                    icon = icon
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.fillMaxSize().auroraBackground().padding(12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CommandButtonProjection(
                                contentModel = Command(
                                    text = "Hello screenshot!!!",
                                    action = {}
                                )
                            ).project()
                        }
                    }
                }
            }
        }

        val image = scene.render()
        val bytes = image.encodeToData()!!.bytes
        val file = File(filename)
        file.writeBytes(bytes)
        scene.close()
        exitApplication()
    }
}
