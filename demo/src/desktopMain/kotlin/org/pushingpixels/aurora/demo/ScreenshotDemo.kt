package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
fun main() = auroraApplication {

    val size = DpSize(200.dp, 200.dp)
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = size
    )

    val density = LocalDensity.current
    val skin = twilightSkin()
    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        undecorated = true,
        icon = radiance_menu(),
        onCloseRequest = ::exitApplication
    ) {
        val scene = ImageComposeScene(width = (size.width.value / density.density).toInt(),
            height = (size.height.value / density.density).toInt()) {
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

        val image = scene.render()
        val bytes = image.encodeToData()!!.bytes
        val file = File("/Users/kirillg/mine2.png")
        file.writeBytes(bytes)
        scene.close()
        exitApplication()
    }
}
