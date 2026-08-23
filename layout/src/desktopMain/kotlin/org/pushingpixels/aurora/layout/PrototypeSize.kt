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

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * A [Size] implementation that computes its width and height
 * by a prototype String.
 *
 * **Examples:**
 * ```kotlin
 * PrototypeSize("123-456-789")
 * FormLayout("p, 2dlu, 'MMMM'") { ... }
 * ```
 * 
 * @see Size
 * @see Sizes
 *
 * @since 1.2
 */
public class PrototypeSize
/**
 * Constructs a PrototypeSize for the given String.
 * 
 * @param prototype    the String used to compute the width and height.
 * 
 * @throws NullPointerException if `prototype` is `null`.
 * 
 * @since 1.2
 */(
    private val textMeasurer: TextMeasurer,
    /**
     * Returns this size's prototype string.
     * 
     * @return the prototype string
     */
    // Fields ***************************************************************
    private val prototype: String
) : Size {
    // Instance Creation ****************************************************

    // Accessors ************************************************************

    // Implementing the Size Interface **************************************
    /**
     * Computes and returns the width of this Size's prototype in pixel.
     * Ignores the component list and measures. Obtains the FontMetrics
     * from the given layout `container` for the default dialog font
     * provided by [DefaultUnitConverter.getDefaultDialogFont].
     *
     * Invoked by [FormSpec] to determine
     * the size of a column or row.
     *
     * @param container       the layout container
     * @param components      the list of components used to compute the size
     * @param minMeasure      the measure that determines the minimum sizes
     * @param prefMeasure     the measure that determines the preferred sizes
     * @param defaultMeasure  the measure that determines the default sizes
     *
     * @return the `stringWidth` for this size's prototype string
     * computed by the `container`'s FontMetrics for the
     * `DefaultUnitConverter`'s default dialog font
     */
    override fun maximumSize(
        components: List<IntrinsicMeasurable>,
        minMeasure: Measure,
        prefMeasure: Measure,
        defaultMeasure: Measure
    ): Int {
        return this.textMeasurer.measure(this.prototype).size.width
    }

    /**
     * Describes if this Size can be compressed, if container space gets scarce.
     * Used by the FormLayout size computations in `#compressedSizes`
     * to check whether a column or row can be compressed or not.
     *
     * PrototypeSizes are incompressible.
     * 
     * @return `false`
     */
    override fun compressible(): Boolean {
        return false
    }

    /**
     * Returns a parseable string representation of this prototype size.
     * 
     * @return a String that can be parsed by the Forms parser
     */
    override fun encode(): String {
        return "'$prototype'"
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
        if (other !is PrototypeSize) {
            return false
        }
        return prototype == other.prototype
    }

    /**
     * Returns a hash code value for the object. This method is supported
     * for the benefit of hashtables such as those provided by
     * `java.util.Hashtable`.
     * 
     * @return  a hash code value for this object.
     * 
     * @see Object.equals
     * @see java.util.Hashtable
     */
    override fun hashCode(): Int {
        return prototype.hashCode()
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
        return encode()
    }
}
