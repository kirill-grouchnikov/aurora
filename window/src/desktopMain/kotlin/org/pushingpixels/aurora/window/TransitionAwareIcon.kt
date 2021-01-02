/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.common.HashMapKey
import org.pushingpixels.aurora.component.utils.StateTransitionTracker
import org.pushingpixels.aurora.icon.AuroraIcon

/**
 * Icon with transition-aware capabilities. Has a delegate that does the actual painting based on
 * the transition color schemes.
 *
 * @author Kirill Grouchnikov
 */
internal class TransitionAwareIcon(
    val decorationAreaType: DecorationAreaType,
    val skinColors: AuroraSkinColors,
    val buttonBackgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    val stateTransitionTracker: StateTransitionTracker,
    val delegate: (AuroraColorScheme) -> ImageBitmap,
    val colorSchemeAssociationKindDelegate: ((ComponentState) -> ColorSchemeAssociationKind)?,
    val uniqueIconTypeId: String
) : AuroraIcon {

    private val markEnabledIcon = this.delegate.invoke(
        skinColors.getColorScheme(
            decorationAreaType,
            ColorSchemeAssociationKind.MARK, ComponentState.ENABLED
        )
    )

    val iconWidth = markEnabledIcon.width
    val iconHeight = markEnabledIcon.height

    /**
     * Returns the current icon to paint.
     *
     * @return Icon to paint.
     */
    private val iconToPaint: ImageBitmap
        get() {
            val modelStateInfo = stateTransitionTracker.modelStateInfo
            val activeStates = modelStateInfo.stateContributionMap
            var currState = modelStateInfo.currModelState
            val buttonNeverPainted = (buttonBackgroundAppearanceStrategy == BackgroundAppearanceStrategy.NEVER)
            if (buttonNeverPainted) {
                if (currState.isFacetActive(ComponentStateFacet.ENABLE)) currState = ComponentState.ENABLED
            }
            val baseAssociationKind =
                colorSchemeAssociationKindDelegate?.invoke(currState) ?: ColorSchemeAssociationKind.MARK
            val baseScheme = skinColors.getColorScheme(decorationAreaType, baseAssociationKind, currState)
            val baseAlpha: Float = skinColors.getAlpha(decorationAreaType, currState)
            val keyBase = HashMapKey(uniqueIconTypeId, baseScheme.displayName, baseAlpha)
            var layerBase = iconMap[keyBase]
            if (layerBase == null) {
                val baseFullOpacity = delegate.invoke(baseScheme)
                if (baseAlpha == 1.0f) {
                    layerBase = baseFullOpacity
                    iconMap[keyBase] = layerBase
                } else {
                    layerBase = ImageBitmap(
                        width = baseFullOpacity.width,
                        height = baseFullOpacity.height
                    )
                    val baseCanvas = Canvas(layerBase)
                    baseCanvas.drawImage(
                        image = baseFullOpacity,
                        topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                        paint = Paint().also { it.alpha = baseAlpha })

                    iconMap[keyBase] = layerBase
                }
            }
            if (currState.isDisabled || activeStates.size == 1 || buttonNeverPainted) {
                // Simple cases - disabled component, only one active state, or the button
                // never paints its background
                return layerBase
            }

            val result = ImageBitmap(width = layerBase.width, height = layerBase.height)
            val canvas = Canvas(result)

            // draw the base layer
            canvas.drawImage(image = layerBase, topLeftOffset = Offset(x = 0.0f, y = 0.0f), paint = Paint())

            // draw the other active layers
            for ((activeState, value) in activeStates) {
                if (activeState === currState) {
                    continue
                }
                val stateContribution = value.contribution
                if (stateContribution > 0.0f) {
                    val associationKind =
                        colorSchemeAssociationKindDelegate?.invoke(activeState) ?: ColorSchemeAssociationKind.MARK
                    val scheme = skinColors.getColorScheme(decorationAreaType, associationKind, activeState)
                    val alpha = skinColors.getAlpha(decorationAreaType, activeState)
                    val key = HashMapKey(uniqueIconTypeId, scheme.displayName, alpha)
                    var layer = iconMap.get(key)
                    if (layer == null) {
                        val fullOpacity = delegate.invoke(scheme)
                        if (alpha == 1.0f) {
                            layer = fullOpacity
                            iconMap[key] = layer
                        } else {
                            layer = ImageBitmap(
                                width = fullOpacity.width,
                                height = fullOpacity.height
                            )
                            val layerCanvas = Canvas(layer)
                            layerCanvas.drawImage(
                                image = fullOpacity,
                                topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                                paint = Paint().also { it.alpha = alpha })

                            iconMap[key] = layer
                        }
                    }
                    canvas.drawImage(
                        image = layer,
                        topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                        paint = Paint().also { it.alpha = stateContribution})
                }
            }
            return result
        }

    override fun paintIcon(drawScope: DrawScope) {
        drawScope.drawImage(iconToPaint)
    }

    override fun getWidth(): Int {
        return iconWidth
    }

    override fun getHeight(): Int {
        return iconHeight
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        // This icon only "pretends" to be resizable
        throw UnsupportedOperationException("This operation is not supported")
    }

    companion object {
        /**
         * Icon cache to speed up the subsequent icon painting. The basic assumption is that the
         * [.delegate] returns an icon that paints the same for the same parameters.
         */
        private val iconMap = hashMapOf<HashMapKey, ImageBitmap>()
    }
}