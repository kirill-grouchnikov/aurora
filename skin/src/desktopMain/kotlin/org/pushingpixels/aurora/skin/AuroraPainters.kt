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
package org.pushingpixels.aurora.skin

import org.pushingpixels.aurora.skin.painter.border.AuroraBorderPainter
import org.pushingpixels.aurora.skin.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.skin.painter.fill.AuroraFillPainter
import org.pushingpixels.aurora.skin.painter.overlay.AuroraOverlayPainter
import java.util.*

data class AuroraPainters(
    val fillPainter: AuroraFillPainter,
    val borderPainter: AuroraBorderPainter,
    val decorationPainter: AuroraDecorationPainter,
    val overlayPaintersMap: MutableMap<DecorationAreaType, MutableList<AuroraOverlayPainter>> = hashMapOf()
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
        overlayPainter: AuroraOverlayPainter,
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
        overlayPainter: AuroraOverlayPainter,
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
    fun getOverlayPainters(decorationAreaType: DecorationAreaType): List<AuroraOverlayPainter> {
        return if (!overlayPaintersMap.containsKey(decorationAreaType)) {
            emptyList()
        } else Collections.unmodifiableList(overlayPaintersMap[decorationAreaType])
    }
}

