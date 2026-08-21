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
package org.pushingpixels.aurora.layout.util

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * This is the default implementation of the [UnitConverter] interface.
 * It converts horizontal and vertical dialog base units to pixels.
 *
 * The horizontal base unit is equal to the average width, in pixels,
 * of the characters in the system font; the vertical base unit is equal
 * to the height, in pixels, of the font.
 * Each horizontal base unit is equal to 4 horizontal dialog units;
 * each vertical base unit is equal to 8 vertical dialog units.
 *
 * The DefaultUnitConverter computes dialog base units using a default font
 * and a test string for the average character width. You can configure
 * the font and the test string via the bound Bean properties
 * *defaultDialogFont* and *averageCharacterWidthTestString*.
 * See also Microsoft's suggestion for a custom computation
 * [custom computation](http://support.microsoft.com/default.aspx?scid=kb;EN-US;125681).
 * More information how to use dialog units in screen design can be found
 * in Microsoft's
 * [Design Specifications and Guidelines](http://msdn2.microsoft.com/en-us/library/ms997619).
 *
 * Since the Forms 1.1 this converter logs font information at
 * the `CONFIG` level.
 * 
 * @see UnitConverter
 * @see Size
 * @see Sizes
 */
public class DefaultUnitConverter(
    private val textMeasurer: TextMeasurer,
    private val textStyle: TextStyle,
    private val density: Density)
/**
 * Constructs a DefaultUnitConverter and registers
 * a listener that handles changes in the look&amp;feel.
 */
    : AbstractUnitConverter() {

    // Cached *****************************************************************
    /**
     * Holds the lazily created cached global dialog base units that are used
     * if a component is not (yet) available - for example in a Border.
     */
    private var cachedGlobalDialogBaseUnits: DialogBaseUnits? = null

    // Implementing Abstract Superclass Behavior ******************************
    /**
     * Returns the cached or computed horizontal dialog base units.
     * 
     * @return the horizontal dialog base units
     */
    override fun getDialogBaseUnitsX(): Double {
        return getDialogBaseUnits().x
    }

    /**
     * Returns the cached or computed vertical dialog base units
     * for the given component.
     * 
     * @return the vertical dialog base units
     */
    override fun getDialogBaseUnitsY(): Double {
        return getDialogBaseUnits().y
    }


    // Compute and Cache Global and Components Dialog Base Units **************
    private val globalDialogBaseUnits: DialogBaseUnits
        /**
         * Lazily computes and answer the global dialog base units.
         * Should be re-computed if the l&amp;f, platform, or screen changes.
         * 
         * @return a cached DialogBaseUnits object used globally if no container is available
         */
        get() {
            if (cachedGlobalDialogBaseUnits == null) {
                cachedGlobalDialogBaseUnits = computeGlobalDialogBaseUnits()
            }
            return cachedGlobalDialogBaseUnits!!
        }

    /**
     * Looks up and returns the dialog base units for the given component.
     * In case the component is `null` the global dialog base units
     * are answered.
     *
     * Before we compute the dialog base units we check whether they
     * have been computed and cached before - for the same component
     * `FontMetrics`.
     * 
     * @return the DialogBaseUnits object for the given component
     */
    private fun getDialogBaseUnits(): DialogBaseUnits {
        return this.globalDialogBaseUnits
    }

    /**
     * Computes and returns the horizontal dialog base units.
     * Honors the font, font size and resolution.
     *
     * Implementation Note: 14dluY map to 22 pixel for 8pt Tahoma on 96 dpi.
     * I could not yet manage to compute the Microsoft compliant font height.
     * Therefore this method adds a correction value that seems to work
     * well with the vast majority of desktops.
     *
     * TODO: Revise the computation of vertical base units as soon as
     * there are more information about the original computation
     * in Microsoft environments.
     * 
     * @param metrics  the FontMetrics used to measure the dialog font
     * @return the horizontal and vertical dialog base units
     */
    private fun computeDialogBaseUnits(): DialogBaseUnits {
        val balancedString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        val textLayoutResult = textMeasurer.measure(
            text = balancedString,
            style = textStyle,
            maxLines = 1
        )

        val averageCharWidth = (textLayoutResult.size.width / balancedString.length) / density.density.toDouble()
        val ascent = (textLayoutResult.getLineBaseline(0) - textLayoutResult.getLineTop(0)) / density.density.toDouble()
        val height = if (ascent > 14.0) ascent else ascent + (15.0 - ascent) / 3.0

        val dialogBaseUnits = DialogBaseUnits(x = averageCharWidth, y = height)
        return dialogBaseUnits
    }

    /**
     * Computes the global dialog base units. The current implementation
     * assumes a fixed 8pt font and on 96 or 120 dpi. A better implementation
     * should ask for the main dialog font and should honor the current
     * screen resolution.
     *
     * Should be re-computed if the l&amp;f, platform, or screen changes.
     * 
     * @return a DialogBaseUnits object used globally if no container is available
     */
    private fun computeGlobalDialogBaseUnits(): DialogBaseUnits {
        val globalDialogBaseUnits = computeDialogBaseUnits()
        return globalDialogBaseUnits
    }

    /**
     * Invalidates the caches. Resets the global dialog base units,
     * clears the Map from `FontMetrics` to dialog base units,
     * and resets the fallback for the default dialog font.
     * This is invoked after a change of the look&amp;feel.
     */
    public fun clearCache() {
        cachedGlobalDialogBaseUnits = null
    }


    // Helper Code ************************************************************
    /**
     * Describes horizontal and vertical dialog base units.
     */
    private class DialogBaseUnits(val x: Double, val y: Double) {
        override fun toString(): String {
            return "DBU(x=$x; y=$y)"
        }
    }
}
