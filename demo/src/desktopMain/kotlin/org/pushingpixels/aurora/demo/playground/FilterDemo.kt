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
package org.pushingpixels.aurora.demo.playground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.skija.*
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.skin.colorscheme.MetallicColorScheme
import org.pushingpixels.aurora.skin.utils.getBrushedMetalTile
import org.pushingpixels.aurora.skin.utils.getNoiseTile
import org.pushingpixels.aurora.window.AuroraWindow

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(800.dp, 300.dp)
    )
    val skin = mutableStateOf(businessSkin())

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        // Create a noise tile
        val tile = getNoiseTile(scheme = MetallicColorScheme(), width = 400, height = 400)
        // A Skia blur filter
        val blur = ImageFilter.makeBlur(15.0f, 0.0f, FilterTileMode.REPEAT, null, null)
        val paint = Paint()
        paint.imageFilter = blur
        // A bitmap to paint the noise tile + blur filter into
        val bitmap = Bitmap()
        bitmap.imageInfo = ImageInfo(360, 360, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
        bitmap.allocPixels()
        // A canvas to wrap the bitmap
        val canvas = Canvas(bitmap)
        // Draw the noise tile with blur filter into the canvas
        canvas.drawImage(Image.makeFromBitmap(tile.asDesktopBitmap()), -20.0f, -20.0f, paint)
        // A Compose image bitmap
        val texture = ImageBitmap(width = 360, height = 360)
        // Copy over the pixels from the noise tile + blur filter bitmap
        texture.asDesktopBitmap().installPixels(bitmap.readPixels())

        val texture2 = getBrushedMetalTile(scheme = MetallicColorScheme(), width = 360, height = 360)

        Box(modifier = Modifier.size(500.dp).paint(painter = object : Painter() {
            override val intrinsicSize: Size
                get() = Size.Unspecified

            override fun DrawScope.onDraw() {
                drawImage(
                    tile,
                    srcOffset = IntOffset(20, 20), srcSize = IntSize(360, 360),
                    dstOffset = IntOffset(20, 20), dstSize = IntSize(360, 360)
                )
                drawImage(texture, topLeft = Offset(440f, 20f))
                drawImage(texture2, topLeft = Offset(860f, 20f))
            }
        }))
    }
}

