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
import kotlin.math.max
import kotlin.math.min

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Describes sizes that provide lower and upper bounds
 * as used by the [FormLayout].
 * 
 * @see [Sizes]
 * @see [ConstantSize]
 */
public class BoundedSize(public val basis: Size, public val lowerBound: Size?, public val upperBound: Size?) : Size {
    // Instance Creation ****************************************************
    init {
        require(!(lowerBound == null && upperBound == null)) {
            "A bounded size must have a non-null lower or upper bound."
        }
    }

    // Accessors ************************************************************

    // Implementation of the Size Interface *********************************
    /**
     * Returns this size as pixel size. Neither requires the component
     * list nor the specified measures. Honors the lower and upper bound.
     *
     * Invoked by [FormSpec] to determine the size of a column or
     * row.
     *
     * @param components      the list of components to measure
     * @param minMeasure      the measure used to determine the minimum size
     * @param prefMeasure     the measure used to determine the preferred size
     * @param defaultMeasure  the measure used to determine the default size
     * @return the maximum size in pixels
     * @see FormSpec.maximumSize
     */
    override fun maximumSize(
        components: List<IntrinsicMeasurable>,
        minMeasure: Measure,
        prefMeasure: Measure,
        defaultMeasure: Measure
    ): Int {
        var size = basis.maximumSize(
            components,
            minMeasure,
            prefMeasure,
            defaultMeasure
        )
        if (lowerBound != null) {
            size = max(
                size, lowerBound.maximumSize(
                    components,
                    minMeasure,
                    prefMeasure,
                    defaultMeasure
                )
            )
        }
        if (upperBound != null) {
            size = min(
                size, upperBound.maximumSize(
                    components,
                    minMeasure,
                    prefMeasure,
                    defaultMeasure
                )
            )
        }
        return size
    }

    /**
     * Describes if this Size can be compressed, if container space gets scarce.
     * Used by the FormLayout size computations in `#compressedSizes`
     * to check whether a column or row can be compressed or not.
     *
     * BoundedSizes are compressible if the base Size is compressible.
     * 
     * @return `true` if and only if the basis is compressible
     * 
     * @since 1.1
     */
    override fun compressible(): Boolean {
        return this.basis.compressible()
    }

    // Overriding Object Behavior *******************************************
    /**
     * Indicates whether some other BoundedSize is "equal to" this one.
     * 
     * @param other   the object with which to compare
     * @return `true` if this object is the same as the object
     * argument, `false` otherwise.
     * @see Object.hashCode
     * @see java.util.Hashtable
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is BoundedSize) {
            return false
        }
        return basis == other.basis
            && (lowerBound == null && other.lowerBound == null
            || lowerBound != null && lowerBound == other.lowerBound)
            && (upperBound == null && other.upperBound == null
            || upperBound != null && upperBound == other.upperBound)
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hashtables such as those provided by
     * `java.util.Hashtable`.
     * 
     * @return  a hash code value for this object.
     * @see Object.equals
     * @see java.util.Hashtable
     */
    override fun hashCode(): Int {
        var hashValue = basis.hashCode()
        if (lowerBound != null) {
            hashValue = hashValue * 37 + lowerBound.hashCode()
        }
        if (upperBound != null) {
            hashValue = hashValue * 37 + upperBound.hashCode()
        }
        return hashValue
    }

    /**
     * Returns a string representation of this size object.
     *
     * **Note:** This string representation may change
     * at any time. It is intended for debugging purposes. For parsing,
     * use [.encode] instead.
     * 
     * @return  a string representation of this bounded size
     */
    override fun toString(): String {
        return encode()
    }

    /**
     * Returns a parseable string representation of this bounded size.
     * 
     * @return a String that can be parsed by the Forms parser
     * 
     * @since 1.2
     */
    override fun encode(): String {
        val buffer = StringBuffer("[")
        if (lowerBound != null) {
            buffer.append(lowerBound.encode())
            buffer.append(',')
        }
        buffer.append(basis.encode())
        if (upperBound != null) {
            buffer.append(',')
            buffer.append(upperBound.encode())
        }
        buffer.append(']')
        return buffer.toString()
    }
}
