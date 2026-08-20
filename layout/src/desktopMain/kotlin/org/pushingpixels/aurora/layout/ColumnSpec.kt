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

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Specifies columns in FormLayout by their default orientation,
 * start size and resizing behavior.
 *
 *
 * 
 * **Examples:**<br></br>
 * The following examples specify a column with FILL alignment, a size of
 * 10&nbsp;dlu that won't grow.
 * <pre>
 * new ColumnSpec(Sizes.dluX(10));
 * new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(10), 0.0);
 * new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(10), ColumnSpec.NO_GROW);
 * ColumnSpec.parse("10dlu");
 * ColumnSpec.parse("10dlu:0");
 * ColumnSpec.parse("fill:10dlu:0");
</pre> * 
 * 
 * 
 * The [FormSpecs] provides
 * predefined frequently used ColumnSpec instances.
 * 
 * @see FormSpecs
 */
class ColumnSpec : FormSpec {
    // Instance Creation ****************************************************
    /**
     * Constructs a ColumnSpec for the given default alignment,
     * size and resize weight.
     *
     *
     * 
     * The resize weight must be a non-negative double; you can use
     * `NO_GROW` as a convenience value for no resize.
     * 
     * @param defaultAlignment the column's default alignment
     * @param size             constant, component size or bounded size
     * @param resizeWeight     the column's non-negative resize weight
     * 
     * @throws NullPointerException if the `size` is `null`
     * @throws IllegalArgumentException if the size is invalid or
     * the `resizeWeight` is negative
     */
    constructor(
        defaultAlignment: DefaultAlignment,
        size: Size,
        resizeWeight: Double,
        textMeasurer: TextMeasurer
    ) : super(defaultAlignment, size, resizeWeight, textMeasurer)


    /**
     * Constructs a ColumnSpec for the given size using the
     * default alignment, and no resizing.
     * 
     * @param size             constant size, component size, or bounded size
     * @throws IllegalArgumentException if the size is invalid
     */
    constructor(size: Size,
        textMeasurer: TextMeasurer) : super(DEFAULT, size, NO_GROW, textMeasurer)


    /**
     * Constructs a ColumnSpec from the specified encoded description.
     * The description will be parsed to set initial values.
     *
     *
     * 
     * Unlike the factory method [.decode], this constructor
     * does not expand layout variables, and it cannot vend cached objects.
     * 
     * @param encodedDescription    the encoded description
     */
    private constructor(encodedDescription: String,
        textMeasurer: TextMeasurer) : super(DEFAULT, encodedDescription, textMeasurer)


    // Implementing Abstract Behavior ***************************************
    override val isHorizontal: Boolean
        /**
         * Returns if this is a horizontal specification (vs. vertical).
         * Used to distinct between horizontal and vertical dialog units,
         * which have different conversion factors.
         * 
         * @return  always `true` (for horizontal)
         */
        get() = true


    companion object {
        // Horizontal Orientations *********************************************
        /**
         * By default put components in the left.
         */
        val LEFT: DefaultAlignment = LEFT_ALIGN

        /**
         * By default put the components in the center.
         */
        val CENTER: DefaultAlignment = CENTER_ALIGN

        /**
         * By default put components in the right.
         */
        val RIGHT: DefaultAlignment = RIGHT_ALIGN

        /**
         * By default fill the component into the column.
         */
        val FILL: DefaultAlignment = FILL_ALIGN

        /**
         * A special alignment value for table column alignment specifications.
         * Some cell renderers shall not be aligned during the renderer
         * preparation.
         * 
         * @since 1.8
         */
        val NONE: DefaultAlignment = NO_ALIGN

        /**
         * Unless overridden the default alignment for a column is FILL.
         */
        val DEFAULT: DefaultAlignment = FILL


        // Cache ******************************************************************
        /**
         * Maps encoded column specifications to ColumnSpec instances.
         */
        private val CACHE: MutableMap<String?, ColumnSpec?> = HashMap()


        // Factory Methods ********************************************************
        /**
         * Creates and returns a [ColumnSpec] that represents a gap with the
         * specified [ConstantSize].
         * 
         * @param gapWidth   specifies the gap width
         * @return a ColumnSpec that describes a horizontal gap
         * 
         * @throws NullPointerException if `gapWidth` is `null`
         * 
         * @since 1.2
         */
        fun createGap(gapWidth: ConstantSize,
            textMeasurer: TextMeasurer): ColumnSpec {
            return ColumnSpec(DEFAULT, gapWidth, NO_GROW, textMeasurer)
        }


        /**
         * Parses the encoded column specifications and returns a ColumnSpec object
         * that represents the string. Variables are expanded using the given
         * LayoutMap.
         * 
         * @param encodedColumnSpec    the encoded column specification
         * @param layoutMap            expands layout column variables
         * 
         * @return a ColumnSpec instance for the given specification
         * @throws NullPointerException if `encodedColumnSpec` or
         * `layoutMap` is `null`
         * @throws IllegalArgumentException if `encodedColumnSpec` is empty
         * or whitespace
         * 
         * @see .decodeSpecs
         * @since 1.2
         */
        /**
         * Parses the encoded column specification and returns a ColumnSpec object
         * that represents the string. Variables are expanded using the default
         * LayoutMap.
         * 
         * @param encodedColumnSpec    the encoded column specification
         * 
         * @return a ColumnSpec instance for the given specification
         * @throws NullPointerException if `encodedColumnSpec` is `null`
         * 
         * @see .decode
         * @see LayoutMap.getRoot
         * @since 1.2
         */
        @JvmOverloads
        @Composable
        fun decode(encodedColumnSpec: String, layoutMap: LayoutMap = LayoutMap.getRoot()): ColumnSpec {
            require(encodedColumnSpec.isNotBlank()) {
                "The encoded column specification must not be null, empty or whitespace."
            }
//            checkNotNull(layoutMap, "The LayoutMap must not be null.")
            val trimmed = encodedColumnSpec.trim { it <= ' ' }
            val lower = trimmed.lowercase()
            return decodeExpanded(layoutMap.expand(lower, true))
        }


        /**
         * Decodes an expanded, trimmed, lower case column spec.
         * Called by the public ColumnSpec factory methods.
         * Looks up and returns the ColumnSpec object from the cache - if any,
         * or constructs and returns a new ColumnSpec instance.
         * 
         * @param expandedTrimmedLowerCaseSpec  the encoded column specification
         * @return a ColumnSpec for the given encoded column spec
         */
        @Composable
        fun decodeExpanded(expandedTrimmedLowerCaseSpec: String): ColumnSpec {
            var spec: ColumnSpec? = CACHE[expandedTrimmedLowerCaseSpec]
            if (spec == null) {
                spec = ColumnSpec(expandedTrimmedLowerCaseSpec, rememberTextMeasurer())
                CACHE[expandedTrimmedLowerCaseSpec] = spec
            }
            return spec
        }


        /**
         * Splits and parses the encoded column specifications using the given
         * [LayoutMap] and returns an array of ColumnSpec objects.
         * 
         * @param encodedColumnSpecs  comma separated encoded column specifications
         * @param layoutMap           expands layout column variables
         * @return an array of decoded column specifications
         * @throws NullPointerException if `encodedColumnSpecs` or
         * `layoutMap` is `null`
         * 
         * @see .decodeSpecs
         * @see .decode
         * @since 1.2
         */
        /**
         * Parses and splits encoded column specifications using the default
         * [LayoutMap] and returns an array of ColumnSpec objects.
         * 
         * @param encodedColumnSpecs  comma separated encoded column specifications
         * @return an array of decoded column specifications
         * @throws NullPointerException if `encodedColumnSpecs` is `null`
         * 
         * @see .decodeSpecs
         * @see .decode
         * @see LayoutMap.getRoot
         */
        @JvmOverloads
        @Composable
        fun decodeSpecs(encodedColumnSpecs: String, layoutMap: LayoutMap = LayoutMap.getRoot()): List<ColumnSpec> {
            return FormSpecParser.parseColumnSpecs(encodedColumnSpecs, layoutMap)
        }
    }
}
