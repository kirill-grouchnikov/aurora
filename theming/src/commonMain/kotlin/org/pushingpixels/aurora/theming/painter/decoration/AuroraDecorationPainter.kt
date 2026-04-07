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
package org.pushingpixels.aurora.theming.painter.decoration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.DecorationAreaType
import java.util.Collections

/**
 * Decoration painter for Aurora.
 *
 * @author Kirill Grouchnikov
 */
abstract class AuroraDecorationPainter : AuroraTrait {
    interface OverlayPainter : AuroraTrait {
        /**
         * Paints the overlay.
         *
         * @param drawScope Draw scope.
         * @param decorationAreaType Decoration area type.
         * @param width Width.
         * @param height Height.
         * @param colors Colors for painting the overlay.
         */
        fun paintOverlay(
            drawScope: DrawScope,
            decorationAreaType: DecorationAreaType,
            width: Float,
            height: Float,
            colors: AuroraSkinColors
        )
    }

    interface InlayPainter : AuroraTrait {
        /**
         * Paints the inlay.
         *
         * @param drawScope Draw scope.
         * @param decorationAreaType Decoration area type.
         * @param width Width.
         * @param height Height.
         * @param colors Colors for painting the overlay.
         */
        fun paintInlay(
            drawScope: DrawScope,
            decorationAreaType: DecorationAreaType,
            rootSize: Size,
            offsetFromRoot: Offset,
            width: Float,
            height: Float,
            colorTokens: ContainerColorTokens
        )
    }

    private val overlayPaintersMap: MutableMap<DecorationAreaType, MutableList<OverlayPainter>> = hashMapOf()

    public var inlayPainter: AuroraDecorationPainter.InlayPainter? = null

    /**
     * Adds the specified overlay painter to the end of the list of overlay
     * painters associated with the specified decoration area types.
     *
     * @param overlayPainter Overlay painter to add to the end of the list of overlay
     * painters associated with the specified decoration area types.
     * @param areaTypes      Decoration area types.
     */
    fun addOverlayPainter(
        overlayPainter: OverlayPainter,
        vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            if (!overlayPaintersMap.containsKey(areaType)) {
                overlayPaintersMap[areaType] = arrayListOf()
            }
            overlayPaintersMap[areaType]!!.add(overlayPainter)
        }
    }

    /**
     * Removes the specified overlay painter from the list of overlay painters
     * associated with the specified decoration area types.
     *
     * @param overlayPainter Overlay painter to remove from the list of overlay painters
     * associated with the specified decoration area types.
     * @param areaTypes      Decoration area types.
     */
    fun removeOverlayPainter(
        overlayPainter: OverlayPainter,
        vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            if (!overlayPaintersMap.containsKey(areaType)) {
                return
            }
            overlayPaintersMap[areaType]!!.remove(overlayPainter)
            if (overlayPaintersMap[areaType]!!.isEmpty()) {
                overlayPaintersMap.remove(areaType)
            }
        }
    }

    /**
     * Removes all overlay painters associated with the specified decoration area types.
     *
     * @param areaTypes Decoration area types.
     */
    fun clearOverlayPainters(vararg areaTypes: DecorationAreaType) {
        for (areaType in areaTypes) {
            if (!overlayPaintersMap.containsKey(areaType)) {
                return
            }
            overlayPaintersMap[areaType]!!.clear()
            overlayPaintersMap.remove(areaType)
        }
    }

    /**
     * Returns a non-null, non-modifiable list of overlay painters associated
     * with the specified decoration area type.
     *
     * @param decorationAreaType Decoration area type.
     * @return A non-null, non-modifiable list of overlay painters associated
     * with the specified decoration area type.
     */
    fun getOverlayPainters(decorationAreaType: DecorationAreaType): List<OverlayPainter> {
        return if (!overlayPaintersMap.containsKey(decorationAreaType)) {
            emptyList()
        } else Collections.unmodifiableList(overlayPaintersMap[decorationAreaType])
    }

    /**
     * Paints the decoration area as a specified shape.
     *
     * @param drawScope Draw scope.
     * @param decorationAreaType Decoration area type.
     * @param outline            Outline to fill.
     * @param colorTokens        Color tokens for painting the decoration area.
     */
    abstract fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    )
}