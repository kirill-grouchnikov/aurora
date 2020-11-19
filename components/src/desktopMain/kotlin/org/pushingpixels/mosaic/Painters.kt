/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticAmbientOf
import org.pushingpixels.mosaic.painter.border.MosaicBorderPainter
import org.pushingpixels.mosaic.painter.border.SimpleBorderPainter
import org.pushingpixels.mosaic.painter.decoration.FlatDecorationPainter
import org.pushingpixels.mosaic.painter.decoration.MosaicDecorationPainter
import org.pushingpixels.mosaic.painter.fill.MosaicFillPainter
import org.pushingpixels.mosaic.painter.fill.SimpleFillPainter
import org.pushingpixels.mosaic.painter.overlay.MosaicOverlayPainter
import java.util.*

data class Painters(
    val fillPainter: MosaicFillPainter = SimpleFillPainter(),
    val borderPainter: MosaicBorderPainter = SimpleBorderPainter(),
    val decorationPainter: MosaicDecorationPainter = FlatDecorationPainter(),
    val overlayPaintersMap: MutableMap<DecorationAreaType, MutableList<MosaicOverlayPainter>> = hashMapOf()
) {
    /**
     * Adds the specified overlay painter to the end of the list of overlay
     * painters associated with the specified decoration area types.
     *
     * @param overlayPainter Overlay painter to add to the end of the list of overlay
     * painters associated with the specified decoration area types.
     * @param areaTypes      Decoration area types.
     */
    fun addOverlayPainter(
        overlayPainter: MosaicOverlayPainter,
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
        overlayPainter: MosaicOverlayPainter,
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
    fun getOverlayPainters(decorationAreaType: DecorationAreaType): List<MosaicOverlayPainter> {
        return if (!overlayPaintersMap.containsKey(decorationAreaType)) {
            emptyList()
        } else Collections.unmodifiableList(overlayPaintersMap[decorationAreaType])
    }
}

internal val AmbientPainters = staticAmbientOf { Painters() }