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
import kotlin.math.roundToInt

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An implementation of the [Size] interface that represents constant
 * sizes described by a value and unit, for example:
 * 10&nbsp;pixel, 15&nbsp;point or 4&nbsp;dialog units.
 * You can get instances of `ConstantSize` using
 * the factory methods and constants in the [Sizes] class.
 * Logical constant sizes that vary with the current layout style
 * are delivered by the [LayoutStyle] class.
 *
 *
 * 
 * This class supports different size units:
 * <table>
 * <tr><td>**Unit**&nbsp;
</td> * <td>&nbsp;**Abbreviation**&nbsp;</td><td>&nbsp;
 * **Size**</td></tr>
 * <tr><td>Millimeter</td><td>mm</td><td>0.1 cm</td></tr>
 * <tr><td>Centimeter</td><td>cm</td><td>10.0 mm</td></tr>
 * <tr><td>Inch</td><td>in</td><td>25.4 mm</td></tr>
 * <tr><td>DTP Point</td><td>pt</td><td>1/72 in</td></tr>
 * <tr><td>Pixel</td><td>px</td><td>1/(resolution in dpi) in</td></tr>
 * <tr><td>Dialog Unit</td><td>dlu</td><td>honors l&amp;f, resolution, and
 * dialog font size</td></tr>
</table> * 
 * 
 * 
 * **Examples:**<pre>
 * Sizes.ZERO;
 * Sizes.DLUX9;
 * Sizes.dluX(42);
 * Sizes.pixel(99);
</pre> * 
 * 
 * @see Size
 * @see Sizes
 */
public data class ConstantSize(public val value: Double, public val unit: MeasurementUnit) : Size {
    // Instance Creation ****************************************************
    /**
     * Constructs a ConstantSize for the given size and unit.
     * 
     * @param value     the size value interpreted in the given units
     * @param unit        the size's unit
     * 
     * @since 1.1
     */
    public constructor(value: Int, unit: MeasurementUnit) : this(value.toDouble(), unit)
    // Accessors ************************************************************

    // Accessing the Value **************************************************
    /**
     * Converts the size if necessary and returns the value in pixels.
     * 
     * @param component  the associated component
     * @return the size in pixels
     */
    public fun getPixelSize(): Int {
        return when (unit) {
            MeasurementUnit.Pixel -> intValue()
            MeasurementUnit.Point -> Sizes.pointAsPixel(intValue())
            MeasurementUnit.Inch -> Sizes.inchAsPixel(value)
            MeasurementUnit.Millimeter -> Sizes.millimeterAsPixel(value)
            MeasurementUnit.Centimeter -> Sizes.centimeterAsPixel(value)
            MeasurementUnit.DialogUnitsX -> Sizes.dialogUnitXAsPixel(intValue())
            MeasurementUnit.DialogUnitsY -> Sizes.dialogUnitYAsPixel(intValue())
        }
    }

    // Implementing the Size Interface **************************************
    /**
     * Returns this size as pixel size. Neither requires the component
     * list nor the specified measures.
     *
     *
     *
     * Invoked by [FormSpec] to determine
     * the size of a column or row.
     *
     * @param container       the layout container
     * @param components      the list of components used to compute the size
     * @param minMeasure      the measure that determines the minimum sizes
     * @param prefMeasure     the measure that determines the preferred sizes
     * @param defaultMeasure  the measure that determines the default sizes
     * @return the computed maximum size in pixel
     */
    override fun maximumSize(
        components: List<IntrinsicMeasurable>,
        minMeasure: Measure,
        prefMeasure: Measure,
        defaultMeasure: Measure
    ): Int {
        return getPixelSize()
    }

    /**
     * Describes if this Size can be compressed, if container space gets scarce.
     * Used by the FormLayout size computations in `#compressedSizes`
     * to check whether a column or row can be compressed or not.
     *
     *
     * 
     * ConstantSizes are incompressible.
     * 
     * @return `false`
     * 
     * @since 1.1
     */
    override fun compressible(): Boolean {
        return false
    }

    // Overriding Object Behavior *******************************************
    /**
     * Indicates whether some other ConstantSize is "equal to" this one.
     * 
     * @param other   the Object with which to compare
     * @return `true` if this object is the same as the obj
     * argument; `false` otherwise.
     * 
     * @see Object.hashCode
     * @see java.util.Hashtable
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is ConstantSize) {
            return false
        }
        return this.value == other.value
            && this.unit == other.unit
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hashtables such as those provided by
     * `java.util.Hashtable`.
     * 
     * @return  a hash code value for this object.
     * 
     * @see Object.equals
     * @see java.util.Hashtable
     */
    override fun hashCode(): Int {
        return value.hashCode() + 37 * unit.hashCode()
    }

    /**
     * Returns a string representation of this size object.
     * 
     * **Note:** This string representation may change
     * at any time. It is intended for debugging purposes. For parsing,
     * use [.encode] instead.
     * 
     * @return  a string representation of the constant size
     */
    override fun toString(): String {
        return if (value == intValue().toDouble())
            intValue().toString() + unit.abbreviation()
        else
            value.toString() + unit.abbreviation()
    }

    /**
     * Returns a parseable string representation of this constant size.
     * 
     * @return a String that can be parsed by the Forms parser
     * 
     * @since 1.2
     */
    override fun encode(): String {
        return if (value == intValue().toDouble())
            intValue().toString() + unit.encode()
        else
            value.toString() + unit.encode()
    }

    // Helper Code **********************************************************
    private fun intValue(): Int {
        return value.roundToInt()
    }

    // Helper Class *********************************************************
    /**
     * Enumeration for units as used in instances of [ConstantSize].
     */
    public enum class MeasurementUnit(
        private val abbreviation: String,
        private val parseAbbreviation: String?,
        internal val requiresIntegers: Boolean
    ) {
        Pixel("px", null, true),
        Point("pt", null, true),
        DialogUnitsX("dluX", "dlu", true),
        DialogUnitsY("dluY", "dlu", true),
        Millimeter("mm", null, false),
        Centimeter("cm", null, false),
        Inch("in", null, false);

        /**
         * Returns a parseable string representation of this unit.
         * 
         * @return a String that can be parsed by the Forms parser
         * 
         * @since 1.2
         */
        public fun encode(): String {
            return parseAbbreviation ?: abbreviation
        }

        /**
         * Returns the first character of this Unit's name.
         * Used to identify it in short format strings.
         * 
         * @return the first character of this Unit's name.
         */
        public fun abbreviation(): String {
            return abbreviation
        }

        public companion object {
            /**
             * Returns a Unit that corresponds to the specified string.
             * 
             * @param name   the encoded unit, trimmed and in lower case
             * @param horizontal  true for a horizontal unit, false for vertical
             * @return the corresponding Unit
             * @throws IllegalArgumentException if no Unit exists for the string
             */
            public fun valueOf(name: String, horizontal: Boolean): MeasurementUnit {
                if (name.isEmpty()) {
                    return Sizes.defaultUnit
                } else if (name == "px") {
                    return Pixel
                } else if (name == "dlu") {
                    return if (horizontal) DialogUnitsX else DialogUnitsY
                } else if (name == "pt") {
                    return Point
                } else if (name == "in") {
                    return Inch
                } else if (name == "mm") {
                    return Millimeter
                } else if (name == "cm") {
                    return Centimeter
                } else {
                    throw IllegalArgumentException(
                        "Invalid unit name '" + name + "'. Must be one of: " +
                            "px, dlu, pt, mm, cm, in"
                    )
                }
            }
        }
    }

    public companion object {
        /**
         * Creates and returns a ConstantSize from the given encoded size
         * and unit description.
         * 
         * @param encodedValueAndUnit  the size's value and unit as string,
         * trimmed and in lower case
         * @param horizontal           true for horizontal, false for vertical
         * @return a constant size for the given encoding and unit description
         * 
         * @throws IllegalArgumentException   if the unit requires integer
         * but the value is not an integer
         */
        public fun valueOf(encodedValueAndUnit: String, horizontal: Boolean): ConstantSize {
            val split: Array<String> = splitValueAndUnit(encodedValueAndUnit)
            val encodedValue = split[0]
            val encodedUnit = split[1]
            val unit: MeasurementUnit = MeasurementUnit.valueOf(encodedUnit, horizontal)
            val value = encodedValue.toDouble()
            if (unit.requiresIntegers) {
                require(
                    value == value.toInt().toDouble()) {
                    "$unit value $encodedValue must be an integer."
                }
            }
            return ConstantSize(value, unit)
        }

        /**
         * Creates and returns a ConstantSize for the specified size value
         * in horizontal dialog units.
         * 
         * @param value    size value in horizontal dialog units
         * @return the associated Size instance
         */
        public fun dluX(value: Int): ConstantSize {
            return ConstantSize(value, MeasurementUnit.DialogUnitsX)
        }

        /**
         * Creates and returns a ConstantSize for the specified size value
         * in vertical dialog units.
         * 
         * @param value    size value in vertical dialog units
         * @return the associated Size instance
         */
        public fun dluY(value: Int): ConstantSize {
            return ConstantSize(value, MeasurementUnit.DialogUnitsY)
        }

        /**
         * Splits a string that encodes size with unit into the size and unit
         * substrings. Returns an array of two strings.
         * 
         * @param encodedValueAndUnit  a strings that represents a size with unit,
         * trimmed and in lower case
         * @return the first element is size, the second is unit
         */
        private fun splitValueAndUnit(encodedValueAndUnit: String): Array<String> {
            val len = encodedValueAndUnit.length
            var firstLetterIndex = len
            while (firstLetterIndex > 0
                && Character.isLetter(encodedValueAndUnit[firstLetterIndex - 1])
            ) {
                firstLetterIndex--
            }
            val res0 = encodedValueAndUnit.substring(0, firstLetterIndex)
            val res1 = encodedValueAndUnit.substring(firstLetterIndex)
            return arrayOf(res0, res1)
        }
    }
}
