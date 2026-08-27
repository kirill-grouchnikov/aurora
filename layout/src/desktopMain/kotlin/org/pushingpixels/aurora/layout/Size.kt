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

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An interface that describes sizes as used by the [FormLayout]:
 * component measuring sizes, constant sizes with value and unit,
 * and bounded sizes that provide lower and upper bounds for a size.
 *
 * You can find a motivation for the different `Size` types in
 * the Forms whitepaper that is part of the product documentation and that is
 * available online too, see
 * [
 * http://www.jgoodies.com/articles/forms.pdf](http://www.jgoodies.com/articles/forms.pdf).
 * 
 * @see Sizes
 * @see ConstantSize
 */
public interface Size {
    /**
     * Computes and returns this Size's maximum pixel size applied to
     * the given list of components using the specified measures.
     *
     * Invoked by [FormSpec] to determine
     * the size of a column or row. This method is not intended to be called
     * by API users, and it uses API invisible parameter types.
     * 
     * @param components      the list of components used to compute the size
     * @param minMeasure      the measure that determines the minimum sizes
     * @param prefMeasure     the measure that determines the preferred sizes
     * @param defaultMeasure  the measure that determines the default sizes
     * @return the maximum size in pixels for the given list of components
     */
    public fun maximumSize(
        components: List<IntrinsicMeasurable>,
        minMeasure: Measure,
        prefMeasure: Measure,
        defaultMeasure: Measure
    ): Int

    /**
     * Describes if this Size can be compressed, if container space gets scarce.
     * Used by the [FormLayout] size computations in `#compressedSizes`
     * to check whether a column or row can be compressed or not.
     *
     * The [Sizes.ComponentSize] *default* is compressible, as well as
     * [BoundedSize]s that are based on the *default* size.
     * 
     * @return `true` for compressible Sizes
     * 
     * @since 1.1
     */
    public fun compressible(): Boolean

    /**
     * Returns a String representation of this Size object that can
     * be parsed by the Forms parser.
     *
     * Implementors should return a non-verbose string.
     * 
     * @return a parseable String representation of this object.
     */
    public fun encode(): String
}
