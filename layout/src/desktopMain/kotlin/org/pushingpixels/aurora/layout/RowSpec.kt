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

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Specifies rows in FormLayout by their default orientation,
 * start size and resizing behavior.
 *
 *
 * 
 * **Examples:**<br></br>
 * The following examples specify a centered row with a size of 14&nbsp;dlu
 * that won't grow.
 * <pre>
 * new RowSpec(Sizes.dluX(14));
 * new RowSpec(RowSpec.CENTER, Sizes.dluX(14), 0.0);
 * new RowSpec(rowSpec.CENTER, Sizes.dluX(14), RowSpec.NO_GROW);
 * RowSpec.parse("14dlu");
 * RowSpec.parse("14dlu:0");
 * RowSpec.parse("center:14dlu:0");
</pre> * 
 * 
 * 
 * The [FormSpecs] provides
 * predefined frequently used RowSpec instances.
 * 
 * @see FormSpecs
 */
class RowSpec : FormSpec {
    // Instance Creation ****************************************************
    /**
     * Constructs a RowSpec from the given default orientation,
     * size, and resize weight.
     *
     *
     * 
     * The resize weight must be a non-negative double; you can use
     * `NO_FILL` as a convenience value for no resize.
     * 
     * @param defaultAlignment  the row's default alignment
     * @param size              constant size, component size, or bounded size
     * @param resizeWeight      the row's non-negative resize weight
     * @throws IllegalArgumentException if the size is invalid or
     * the resize weight is negative
     */
    constructor(
        defaultAlignment: DefaultAlignment,
        size: Size,
        resizeWeight: Double,
    ) : super(defaultAlignment, size, resizeWeight)

    /**
     * Constructs a RowSpec for the given size using the
     * default alignment, and no resizing.
     * 
     * @param size             constant size, component size, or bounded size
     * @throws IllegalArgumentException if the size is invalid
     */
    constructor(size: Size) : super(DEFAULT, size, NO_GROW)

    /**
     * Constructs a RowSpec from the specified encoded description.
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
         * @return always `false` (for vertical)
         */
        get() = false


    companion object {
        // Vertical Orientations ************************************************
        /**
         * By default put the components in the top.
         */
        val TOP: DefaultAlignment = DefaultAlignment.TopAlign

        /**
         * By default put the components in the center.
         */
        val CENTER: DefaultAlignment = DefaultAlignment.CenterAlign

        /**
         * By default put the components in the bottom.
         */
        val BOTTOM: DefaultAlignment = DefaultAlignment.BottomAlign

        /**
         * By default fill the component into the row.
         */
        val FILL: DefaultAlignment = DefaultAlignment.FillAlign

        /**
         * Unless overridden the default alignment for a row is CENTER.
         */
        val DEFAULT: DefaultAlignment = CENTER


        // Cache ******************************************************************
        /**
         * Maps encoded row specifications to RowSpec instances.
         */
        private val CACHE: MutableMap<String?, RowSpec?> = HashMap()


        // Factory Methods ********************************************************
        /**
         * Creates and returns a [RowSpec] that represents a gap with the
         * specified [ConstantSize].
         * 
         * @param gapHeight   specifies the gap height
         * @return a RowSpec that describes a vertical gap with the given height
         * 
         * @throws NullPointerException if `gapHeight` is `null`
         * 
         * @since 1.2
         */
        fun createGap(gapHeight: ConstantSize): RowSpec {
            return RowSpec(DEFAULT, gapHeight, FormSpec.NO_GROW)
        }


        /**
         * Parses the encoded row specifications and returns a RowSpec object
         * that represents the string. Variables are expanded using the given
         * LayoutMap.
         * 
         * @param encodedRowSpec    the encoded column specification
         * @param layoutMap         expands layout row variables
         * 
         * @return a RowSpec instance for the given specification
         * @throws NullPointerException if `encodedRowSpec` or
         * `layoutMap` is `null`
         * 
         * @see .decodeSpecs
         * @since 1.2
         */
        /**
         * Parses the encoded row specification and returns a RowSpec object
         * that represents the string. Variables are expanded using the default
         * LayoutMap.
         * 
         * @param encodedRowSpec    the encoded row specification
         * 
         * @return a RowSpec instance for the given specification
         * @throws NullPointerException if `encodedRowSpec` is `null`
         * 
         * @see .decode
         * @see LayoutMap.getRoot
         * @since 1.2
         */
        @JvmOverloads
        @Composable
        fun decode(encodedRowSpec: String, layoutMap: LayoutMap = LayoutMap.getRoot()): RowSpec {
            require (encodedRowSpec.isNotBlank()) {
                "The encoded row specification must not be null, empty or whitespace."
            }
            val trimmed = encodedRowSpec.trim { it <= ' ' }
            val lower = trimmed.lowercase()
            return decodeExpanded(layoutMap.expand(lower, false))
        }

        /**
         * Decodes an expanded, trimmed, lower case row spec.
         * Called by the public RowSpec factory methods.
         * Looks up and returns the RowSpec object from the cache - if any,
         * or constructs and returns a new RowSpec instance.
         * 
         * @param expandedTrimmedLowerCaseSpec  the encoded column specification
         * @return a RowSpec for the given encoded row spec
         */
        @Composable
        fun decodeExpanded(expandedTrimmedLowerCaseSpec: String): RowSpec {
            var spec: RowSpec? = CACHE[expandedTrimmedLowerCaseSpec]
            if (spec == null) {
                spec = RowSpec(expandedTrimmedLowerCaseSpec, LocalTextMeasurer.current)
                CACHE[expandedTrimmedLowerCaseSpec] = spec
            }
            return spec
        }


        /**
         * Parses and splits encoded row specifications using the given
         * [LayoutMap] and returns an array of RowSpec objects.
         * 
         * @param encodedRowSpecs     comma separated encoded row specifications
         * @param layoutMap           expands layout row variables
         * @return an array of decoded row specifications
         * 
         * @throws NullPointerException `encodedRowSpecs` or
         * `layoutMap` is `null`
         * 
         * @see RowSpec.RowSpec
         * @since 1.2
         */
        /**
         * Parses and splits encoded row specifications using the default
         * [LayoutMap] and returns an array of RowSpec objects.
         * 
         * @param encodedRowSpecs     comma separated encoded row specifications
         * @return an array of decoded row specifications
         * @throws NullPointerException if `encodedRowSpecs` is `null`
         * 
         * @see .decodeSpecs
         * @see .decode
         * @see LayoutMap.getRoot
         */
        @JvmOverloads
        @Composable
        fun decodeSpecs(encodedRowSpecs: String, layoutMap: LayoutMap = LayoutMap.getRoot()): List<RowSpec> {
            return FormSpecParser.parseRowSpecs(encodedRowSpecs, layoutMap)
        }
    }
}
