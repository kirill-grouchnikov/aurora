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
package org.pushingpixels.aurora.layout

import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import org.pushingpixels.aurora.layout.ConstantSize.MeasurementUnit
import org.pushingpixels.aurora.layout.util.DefaultUnitConverter
import org.pushingpixels.aurora.layout.util.UnitConverter

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Consists only of static methods that create and convert sizes
 * as required by the [FormLayout]. The conversion of sizes
 * that are not based on pixel is delegated to an implementation
 * of [UnitConverter]. The conversion methods require the
 * layout container as parameter to read its current font and resolution.
 * 
 * @see [Size]
 * @see [UnitConverter]
 * @see [DefaultUnitConverter]
 */
public object Sizes {
    // Common Constant Sizes ************************************************
    public val Zero: ConstantSize = pixel(0)

    public val DluX1: ConstantSize = dluX(1)
    public val DluX2: ConstantSize = dluX(2)
    public val DluX3: ConstantSize = dluX(3)
    public val DluX4: ConstantSize = dluX(4)
    public val DluX5: ConstantSize = dluX(5)
    public val DluX6: ConstantSize = dluX(6)
    public val DluX7: ConstantSize = dluX(7)
    public val DluX8: ConstantSize = dluX(8)
    public val DluX9: ConstantSize = dluX(9)
    public val DluX11: ConstantSize = dluX(11)
    public val DluX14: ConstantSize = dluX(14)
    public val DluX21: ConstantSize = dluX(21)

    public val DluY1: ConstantSize = dluY(1)
    public val DluY2: ConstantSize = dluY(2)
    public val DluY3: ConstantSize = dluY(3)
    public val DluY4: ConstantSize = dluY(4)
    public val DluY5: ConstantSize = dluY(5)
    public val DluY6: ConstantSize = dluY(6)
    public val DluY7: ConstantSize = dluY(7)
    public val DluY8: ConstantSize = dluY(8)
    public val DluY9: ConstantSize = dluY(9)
    public val DluY11: ConstantSize = dluY(11)
    public val DluY14: ConstantSize = dluY(14)
    public val DluY21: ConstantSize = dluY(21)

    // Static Component Sizes ***********************************************

    // Singleton State *******************************************************
    /**
     * Holds the current converter that maps non-pixel sizes to pixels.
     * 
     * @see .setUnitConverter
     */
    private lateinit var unitConverter: UnitConverter

    /**
     * Returns the Unit that is used if an encoded ConstantSize contains
     * no unit string.
     * 
     * @return the Unit if no unit string is provided
     * 
     * @since 1.2
     */
    /**
     * Holds the Unit that is used if no Unit is provided in encoded
     * ConstantSizes.
     * 
     * @see .setDefaultUnit
     */
    public var defaultUnit: MeasurementUnit = MeasurementUnit.Pixel
        /**
         * Sets the Unit that shall be used if an encoded ConstantSize
         * provides no unit string.
         * 
         * @throws IllegalArgumentException if `unit` is
         * [MeasurementUnit.DialogUnitsX] or [MeasurementUnit.DialogUnitsY].
         */
        set(unit) {
            require(!((unit === MeasurementUnit.DialogUnitsX) || (unit === MeasurementUnit.DialogUnitsY))) {
                ("The unit must not be DialogUnitsX or DialogUnitsY. "
                    + "To use DLU as default unit, invoke this method with null.")
            }
            field = unit
        }

    // Creation of Size Instances *********************************************
    /**
     * Creates and returns an instance of `ConstantSize` from the
     * given encoded size and unit description.
     * 
     * @param encodedValueAndUnit  value and unit in string representation
     * @param horizontal            true for horizontal, false for vertical
     * @return a `ConstantSize` for the given value and unit
     */
    public fun constant(
        encodedValueAndUnit: String,
        horizontal: Boolean
    ): ConstantSize {
        val lowerCase = encodedValueAndUnit.lowercase()
        val trimmed = lowerCase.trim { it <= ' ' }
        return ConstantSize.valueOf(trimmed, horizontal)
    }

    /**
     * Creates and returns a ConstantSize for the specified value
     * in horizontal dialog units.
     * 
     * @param value    size value in horizontal dialog units
     * @return the associated `ConstantSize`
     */
    public fun dluX(value: Int): ConstantSize {
        return ConstantSize.dluX(value)
    }

    /**
     * Creates and returns a ConstantSize for the specified value
     * in vertical dialog units.
     * 
     * @param value    size value in vertical dialog units
     * @return the associated `ConstantSize`
     */
    public fun dluY(value: Int): ConstantSize {
        return ConstantSize.dluY(value)
    }

    /**
     * Creates and returns a ConstantSize
     * for the specified pixel value.
     * 
     * @param value  value in pixel
     * @return the associated `ConstantSize`
     */
    public fun pixel(value: Int): ConstantSize {
        return ConstantSize(value, MeasurementUnit.Pixel)
    }

    /**
     * Creates and returns a BoundedSize for the given basis
     * using the specified lower and upper bounds.
     * 
     * @param basis        the base size
     * @param lowerBound    the lower bound size
     * @param upperBound    the upper bound size
     * @return a `BoundedSize` for the given basis and bounds
     * @throws NullPointerException if `basis` is `null`,
     * or if both `lowerBound` and `upperBound` are `null`.
     */
    public fun bounded(basis: Size, lowerBound: Size?, upperBound: Size?): Size {
        return BoundedSize(basis, lowerBound, upperBound)
    }

    // Unit Conversion ******************************************************
    /**
     * Converts Inches and returns pixels using the specified resolution.
     *
     * @param inch           the Inches
     * @return the given Inches as pixels
     */
    public fun inchAsPixel(textMeasurer: TextMeasurer, textStyle: TextStyle, inch: Double): Int {
        return if (inch == 0.0)
            0
        else
            getUnitConverter(textMeasurer, textStyle).inchAsPixel(inch)
    }

    /**
     * Converts Millimeters and returns pixels using the resolution of the
     * given component's graphics object.
     *
     * @param mm            Millimeters
     * @return the given Millimeters as pixels
     */
    public fun millimeterAsPixel(textMeasurer: TextMeasurer, textStyle: TextStyle, mm: Double): Int {
        return if (mm == 0.0)
            0
        else
            getUnitConverter(textMeasurer, textStyle).millimeterAsPixel(mm)
    }

    /**
     * Converts Centimeters and returns pixels using the resolution of the
     * given component's graphics object.
     *
     * @param cm            Centimeters
     * @return the given Centimeters as pixels
     */
    public fun centimeterAsPixel(textMeasurer: TextMeasurer, textStyle: TextStyle, cm: Double): Int {
        return if (cm == 0.0)
            0
        else
            getUnitConverter(textMeasurer, textStyle).centimeterAsPixel(cm)
    }

    /**
     * Converts DTP Points and returns pixels using the resolution of the
     * given component's graphics object.
     *
     * @param pt            DTP Points
     * @return the given Points as pixels
     */
    public fun pointAsPixel(textMeasurer: TextMeasurer, textStyle: TextStyle, pt: Int): Int {
        return if (pt == 0)
            0
        else
            getUnitConverter(textMeasurer, textStyle).pointAsPixel(pt)
    }

    /**
     * Converts horizontal dialog units and returns pixels.
     * Honors the resolution, dialog font size, platform, and l&amp;f.
     *
     * @param dluX         the horizontal dialog units
     * @return the given horizontal dialog units as pixels
     */
    public fun dialogUnitXAsPixel(textMeasurer: TextMeasurer, textStyle: TextStyle, dluX: Int): Int {
        return if (dluX == 0)
            0
        else
            getUnitConverter(textMeasurer, textStyle).dialogUnitXAsPixel(dluX)
    }

    /**
     * Converts vertical dialog units and returns pixels.
     * Honors the resolution, dialog font size, platform, and l&amp;f.
     *
     * @param dluY         the vertical dialog units
     * @return the given vertical dialog units as pixels
     */
    public fun dialogUnitYAsPixel(textMeasurer: TextMeasurer, textStyle: TextStyle, dluY: Int): Int {
        return if (dluY == 0)
            0
        else
            getUnitConverter(textMeasurer, textStyle).dialogUnitYAsPixel(dluY)
    }

    // Accessing the Unit Converter *******************************************
    /**
     * Returns the current [UnitConverter]. If it has not been initialized
     * before it will get an instance of [DefaultUnitConverter].
     * 
     * @return the current [UnitConverter]
     */
    public fun getUnitConverter(textMeasurer: TextMeasurer, textStyle: TextStyle, ): UnitConverter {
        if (!::unitConverter.isInitialized) {
            unitConverter = DefaultUnitConverter(textMeasurer, textStyle)
        }
        return unitConverter
    }

    /**
     * Sets a new UnitConverter that will be used to convert
     * font-dependent sizes to pixel sizes.
     * 
     * @param newUnitConverter  the unit converter to be set
     */
    public fun setUnitConverter(newUnitConverter: UnitConverter) {
        unitConverter = newUnitConverter
    }

    // Default Unit ***********************************************************

    // Helper Class *********************************************************
    /**
     * Enumeration that implements the [Size] interface for the component sizes:
     * *min, pref, default*.
     */
    public enum class ComponentSize : Size {
        /**
         * Use the maximum of all component minimum sizes as column or row size.
         */
        Minimum,

        /**
         * Use the maximum of all component preferred sizes as column or row size.
         */
        Preferred,

        /**
         * Use the maximum of all component sizes as column or row size;
         * measures preferred sizes when asked for the preferred size
         * and minimum sizes when asked for the minimum size.
         */
        Default;

        /**
         * Computes the maximum size for the given list of components, using
         * this form spec and the specified measure.
         * 
         * Invoked by [FormLayout] to determine the size of one of my elements
         * 
         * @param components      the list of components to measure
         * @param minMeasure      the measure used to determine the minimum size
         * @param prefMeasure     the measure used to determine the preferred size
         * @param defaultMeasure  the measure used to determine the default size
         * @return the maximum size in pixels for the given list of components
         */
        public override fun maximumSize(
            textMeasurer: TextMeasurer,
            textStyle: TextStyle,
            components: List<IntrinsicMeasurable>,
            minMeasure: Measure,
            prefMeasure: Measure,
            defaultMeasure: Measure
        ): Int {
            val measure: Measure = if (this == Minimum)
                minMeasure
            else
                (if (this == Preferred) prefMeasure else defaultMeasure)
            var maximum = 0
            val i: Iterator<IntrinsicMeasurable> = components.iterator()
            while (i.hasNext()) {
                val c = i.next()
                maximum = maximum.coerceAtLeast(measure.sizeOf(c))
            }
            return maximum
        }

        /**
         * Describes if this Size can be compressed, if container space gets scarce.
         * Used by the [FormLayout] size computations in `#compressedSizes`
         * to check whether a column or row can be compressed or not.
         *
         * The [Default] [ComponentSize] is compressible, [Minimum] and [Preferred]
         * are incompressible.
         * 
         * @return `true` for the [Default] size,
         * `false` otherwise
         */
        override fun compressible(): Boolean {
            return this == Default
        }

        override fun toString(): String {
            return encode()
        }

        /**
         * Returns a parseable string representation of this ComponentSize.
         * 
         * @return a String that can be parsed by the Forms parser
         * 
         * @since 1.2
         */
        override fun encode(): String {
            return name.substring(0, 1)
        }

        public companion object {
            /**
             * Returns an instance of `ComponentSize` that corresponds
             * to the specified string.
             * @param str        the encoded component size
             * @return the corresponding ComponentSize or null if none matches
             */
            internal fun parseValueOf(str: String): ComponentSize? {
                if (str == "m" || str == "min") {
                    return Minimum
                }
                if (str == "p" || str == "pref") {
                    return Preferred
                }
                if (str == "d" || str == "default") {
                    return Default
                }
                return null
            }
        }
    }
}
