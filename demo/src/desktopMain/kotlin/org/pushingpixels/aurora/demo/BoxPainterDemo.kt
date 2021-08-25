package org.pushingpixels.aurora.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

class MyIcon : Painter() {
    private fun innerPaint(drawScope: DrawScope) {
        with(drawScope) {
            withTransform({
                transform(
                    Matrix(
                        values = floatArrayOf(
                            1.0f, 0.0f, 0.0f, -0.0f,
                            0.0f, 1.0f, 0.0f, -0.0f,
                            0.0f, 0.0f, 1.0f, 0.0f,
                            0.0f, 0.0f, 0.0f, 1.0f
                        )
                    )
                )
            }) {
                withTransform({
                    transform(
                        Matrix(
                            values = floatArrayOf(
                                1.0f, 0.0f, 0.0f, 0.0f,
                                0.0f, 1.0f, 0.0f, 0.0f,
                                0.0f, 0.0f, 1.0f, 0.0f,
                                2.0f, 2.0f, 0.0f, 1.0f
                            )
                        )
                    )
                }) {
                    val generalPath = Path()
                    generalPath.reset()
                    generalPath.moveTo(10.0f, 0.0f)
                    generalPath.lineTo(20.0f, 10.0f)
                    generalPath.lineTo(10.0f, 20.0f)
                    generalPath.lineTo(0.0f, 10.0f)
                    generalPath.lineTo(10.0f, 0.0f)
                    generalPath.close()
                    generalPath.moveTo(5.70703f, 7.12131f)
                    generalPath.lineTo(2.82861f, 10.0f)
                    generalPath.lineTo(5.70703f, 12.8787f)
                    generalPath.lineTo(7.17188f, 11.4141f)
                    generalPath.lineTo(8.58594f, 12.8282f)
                    generalPath.lineTo(7.12109f, 14.2928f)
                    generalPath.lineTo(10.0f, 17.1716f)
                    generalPath.lineTo(12.8789f, 14.2928f)
                    generalPath.lineTo(11.4141f, 12.8282f)
                    generalPath.lineTo(12.8281f, 11.4141f)
                    generalPath.lineTo(14.293f, 12.8787f)
                    generalPath.lineTo(17.1714f, 10.0f)
                    generalPath.lineTo(14.293f, 7.12131f)
                    generalPath.lineTo(10.0f, 11.4141f)
                    generalPath.lineTo(5.70703f, 7.12131f)
                    generalPath.close()
                    generalPath.moveTo(7.12158f, 5.70715f)
                    generalPath.lineTo(10.0f, 8.58591f)
                    generalPath.lineTo(12.8789f, 5.70718f)
                    generalPath.lineTo(10.0f, 2.82843f)
                    generalPath.lineTo(7.12158f, 5.70715f)
                    generalPath.close()

                    drawOutline(
                        outline = Outline.Generic(generalPath),
                        style = Fill,
                        brush = SolidColor(Color(0, 0, 0, 255)),
                        alpha = 1.0f
                    )
                }
            }
        }
    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        drawRect(color = Color.Red)
        clipRect {
            withTransform({
                scale(scaleX = 8.0f, scaleY = 8.0f, pivot = Offset.Zero)
            }) {
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(10.0f, 10.0f),
                    size = Size(10.0f, 10.0f)
                )

                innerPaint(this)
            }
        }
    }
}

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(400.dp, 320.dp)
    )
    Window(
        title = "Aurora Demo",
        state = state,
        undecorated = false,
        onCloseRequest = ::exitApplication,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.size(200.dp, 150.dp).background(color = Color.Red)
                    .paint(painter = MyIcon(), sizeToIntrinsics = false)
            )
        }
    }
}