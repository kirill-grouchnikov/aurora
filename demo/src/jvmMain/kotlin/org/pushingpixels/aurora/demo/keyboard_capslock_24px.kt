package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.icon.AuroraIcon

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class keyboard_capslock_24px private constructor(var _width: Int, var _height: Int) : AuroraIcon {
    @Suppress("UNUSED_VARIABLE")
    private var shape: Shape? = null

    @Suppress("UNUSED_VARIABLE")
    private var generalPath: Path? = null

    @Suppress("UNUSED_VARIABLE")
    private var paint: Paint? = null

    @Suppress("UNUSED_VARIABLE")
    private var stroke: Stroke? = null

    @Suppress("UNUSED_VARIABLE")
    private var clip: Shape? = null


    private fun _paint0(drawScope: DrawScope) {
        with(drawScope) {
            // _0
            withTransform({
                transform(
                    Matrix(
                        values = floatArrayOf(
                            1.0f, 0.0f, 0.0f, 0.0f,
                            0.0f, 1.0f, 0.0f, 0.0f,
                            0.0f, 0.0f, 1.0f, 0.0f,
                            0.0f, 0.0f, 0.0f, 1.0f,
                        )
                    )
                )
            }) {
                // _0_0
                withTransform({
                    transform(
                        Matrix(
                            values = floatArrayOf(
                                1.0f, 0.0f, 0.0f, 0.0f,
                                0.0f, 1.0f, 0.0f, 0.0f,
                                0.0f, 0.0f, 1.0f, 0.0f,
                                0.0f, 0.0f, 0.0f, 1.0f,
                            )
                        )
                    )
                }) {
                    // _0_0_0
                    withTransform({
                        transform(
                            Matrix(
                                values = floatArrayOf(
                                    1.0f, 0.0f, 0.0f, 0.0f,
                                    0.0f, 1.0f, 0.0f, 0.0f,
                                    0.0f, 0.0f, 1.0f, 0.0f,
                                    0.0f, 0.0f, 0.0f, 1.0f,
                                )
                            )
                        )
                    }) {
                        if (generalPath == null) {
                            generalPath = Path()
                        } else {
                            generalPath!!.reset()
                        }
                        generalPath!!.moveTo(12.0f, 8.41f)
                        generalPath!!.lineTo(16.59f, 13.0f)
                        generalPath!!.lineTo(18.0f, 11.59f)
                        generalPath!!.lineTo(12.0f, 5.59f)
                        generalPath!!.lineTo(6.0f, 11.59f)
                        generalPath!!.lineTo(7.41f, 13.0f)
                        generalPath!!.lineTo(12.0f, 8.41f)
                        generalPath!!.close()
                        generalPath!!.moveTo(6.0f, 18.0f)
                        generalPath!!.lineTo(18.0f, 18.0f)
                        generalPath!!.lineTo(18.0f, 16.0f)
                        generalPath!!.lineTo(6.0f, 16.0f)
                        generalPath!!.lineTo(6.0f, 18.0f)
                        generalPath!!.close()

                        drawOutline(
                            outline = Outline.Generic(generalPath!!),
                            style = Fill,
                            color = Color(0, 0, 0, 255),
                            alpha = 1.0f
                        )
                    }
                }
            }
        }
    }


    private fun innerPaint(drawScope: DrawScope) {
        _paint0(drawScope)

        shape = null
        generalPath = null
        paint = null
        stroke = null
        clip = null
    }

    companion object {
        /**
         * Returns the X of the bounding box of the original SVG image.
         *
         * @return The X of the bounding box of the original SVG image.
         */
        fun getOrigX(): Double {
            return 6.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 5.590000152587891
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 12.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 12.40999984741211
        }

        /**
         * Returns a new instance of this icon with specified dimensions.
         *
         * @param width Required width of the icon
         * @param height Required height of the icon
         * @return A new instance of this icon with specified dimensions.
         */
        @Composable
        fun of(width: Dp, height: Dp): AuroraIcon {
            return keyboard_capslock_24px(
                _width = (width.value * AmbientDensity.current.density).toInt(),
                _height = (height.value * AmbientDensity.current.density).toInt()
            )
        }

        /**
         * Returns a factory that returns instances of this icon on demand.
         *
         * @return Factory that returns instances of this icon on demand.
         */
        fun factory(): AuroraIcon.Factory {
            return object : AuroraIcon.Factory {
                override fun createNewIcon(): AuroraIcon {
                    return keyboard_capslock_24px(getOrigWidth().toInt(), getOrigHeight().toInt())
                }
            }
        }
    }

    override fun getWidth(): Int {
        return _width
    }

    override fun getHeight(): Int {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = (width.value * AmbientDensity.current.density).toInt()
        _height = (height.value * AmbientDensity.current.density).toInt()
    }

    override fun paintIcon(drawScope: DrawScope) {
        with(drawScope) {
            val coef1 = _width / getOrigWidth()
            val coef2 = _height / getOrigHeight()
            val coef = Math.min(coef1, coef2)

            var translateX = -getOrigX().toFloat()
            var translateY = -getOrigY().toFloat()
            if (coef1 != coef2) {
                if (coef1 < coef2) {
                    val extraDy = ((getOrigWidth() - getOrigHeight()) / 2.0f).toInt().toFloat()
                    translateY += extraDy
                } else {
                    val extraDx = ((getOrigHeight() - getOrigWidth()) / 2.0f).toInt().toFloat()
                    translateX += extraDx
                }
            }

            withTransform({
                translate(left = translateX / coef.toFloat(), top = translateY / coef.toFloat())
                scale(scaleX = coef.toFloat(), scaleY = coef.toFloat(), pivot = Offset(0.0f, 0.0f))
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = _width.toFloat(),
                    bottom = _height.toFloat(),
                    clipOp = ClipOp.Intersect
                )
            }) {
                innerPaint(this)
            }
        }
    }
}

