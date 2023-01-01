/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

internal data class AuroraOffset(var x: Float, var y: Float)
internal data class AuroraRect(var x: Float, var y: Float, var width: Float, var height: Float)

internal fun AuroraRect.contains(x: Float, y: Float): Boolean {
    return (x >= this.x) && (x < (this.x + this.width)) && (y >= this.y) &&
            (y < (this.y + this.height))
}

internal fun AuroraOffset.asOffset(density: Density): Offset {
    return Offset(x / density.density, y / density.density)
}

internal fun IntSize.asSize(density: Density): Size {
    return Size(width / density.density, height / density.density)
}

internal fun IntSize.asSize(extraWidth: Int = 0, extraHeight: Int = 0) =
    Size((width + extraWidth).toFloat(), (height + extraHeight).toFloat())

fun getPlacementAwarePopupShift(
    ltr: Boolean,
    anchorDimension: IntSize, popupDimension: IntSize,
    popupPlacementStrategy: PopupPlacementStrategy
): IntSize {
    var dx = 0
    var dy = 0
    val anchorWidth = anchorDimension.width
    val anchorHeight = anchorDimension.height
    val popupWidth = popupDimension.width
    val popupHeight = popupDimension.height

    // Compute horizontal / vertical offsets relative to the placement of the popup
    // under the default PopupPlacementStrategy.Downward.HAlignStart
    when (popupPlacementStrategy) {
        PopupPlacementStrategy.Upward.HAlignStart -> {
            // Popup above the component, horizontally aligned to the start edge
            dy = -popupHeight - anchorHeight
        }

        PopupPlacementStrategy.Upward.HAlignEnd -> {
            // Popup above the component, horizontally aligned to the end edge
            dy = -popupHeight - anchorHeight
            dx = if (ltr) {
                anchorWidth - popupWidth
            } else {
                popupWidth - anchorWidth
            }

        }

        PopupPlacementStrategy.Downward.HAlignStart -> {
            // Popup below the component, horizontally aligned to the start edge
        }

        PopupPlacementStrategy.Downward.HAlignEnd -> {
            // Popup below the component, horizontally aligned to the end edge
            dx = if (ltr) {
                anchorWidth - popupWidth
            } else {
                popupWidth - anchorWidth
            }
        }

        PopupPlacementStrategy.CenteredVertically.HAlignStart -> {
            // Popup centered vertically, horizontally aligned to the start edge
            dy = -popupHeight / 2 - anchorHeight / 2
        }

        PopupPlacementStrategy.CenteredVertically.HAlignEnd -> {
            // Popup centered vertically, horizontally aligned to the end edge
            dy = -popupHeight / 2 - anchorHeight / 2
            dx = if (ltr) {
                anchorWidth - popupWidth
            } else {
                popupWidth - anchorWidth
            }
        }

        PopupPlacementStrategy.Startward.VAlignTop -> {
            // Popup next to the start edge of the component, vertically aligned to the top edge
            dx = if (ltr) -popupWidth else popupWidth
            dy = -anchorHeight
        }

        PopupPlacementStrategy.Startward.VAlignBottom -> {
            // Popup next to the start edge of the component, vertically aligned to the bottom edge
            dx = if (ltr) -popupWidth else popupWidth
            dy = -popupHeight
        }

        PopupPlacementStrategy.Endward.VAlignTop -> {
            // Popup next to the end edge of the component, vertically aligned to the top edge
            dx = if (ltr) anchorWidth else -anchorWidth
            dy = -anchorHeight
        }

        PopupPlacementStrategy.Endward.VAlignBottom -> {
            // Popup next to the end edge of the component, vertically aligned to the bottom edge
            dx = if (ltr) anchorWidth else -anchorWidth
            dy = -popupHeight
        }
    }
    return IntSize(dx, dy)
}
