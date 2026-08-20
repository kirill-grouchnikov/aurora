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
import java.util.regex.Pattern

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An abstract class that specifies columns and rows in FormLayout
 * by their default alignment, start size and resizing behavior.
 * API users will use the subclasses [ColumnSpec] and [RowSpec].
 *
 * Also implements the parser for encoded column and row specifications
 * and provides parser convenience behavior for its subclasses ColumnSpec
 * and RowSpec.
 *
 * TODO: Consider extracting the parser role to a separate class.
 * 
 * @see ColumnSpec
 * @see RowSpec
 * @see FormLayout
 * @see CellConstraints
 */
abstract class FormSpec protected constructor(
    defaultAlignment: DefaultAlignment,
    size: Size,
    resizeWeight: Double,
) {
    // Fields ***************************************************************
    /**
     * Holds the default alignment that will be used if a cell does not
     * override this default.
     */
    private var defaultAlignment: DefaultAlignment

    private lateinit var textMeasurer: TextMeasurer

    /**
     * Describes whether the default alignment has been explictly set.
     * 
     * @see .getDefaultAlignmentExplictlySet
     */
    var defaultAlignmentExplictlySet: Boolean = false
        private set

    /**
     * Holds the size that describes how to size this column or row.
     */
    var size: Size

    /**
     * Holds the resize weight; is 0 if not used.
     */
    var resizeWeight: Double

    // Instance Creation ****************************************************
    /**
     * Constructs a `FormSpec` for the given default alignment,
     * size, and resize weight. The resize weight must be a non-negative
     * double; you can use `NONE` as a convenience value for no
     * resize.
     * 
     * @param defaultAlignment the spec's default alignment
     * @param size             a constant, component or bounded size
     * @param resizeWeight     the spec resize weight
     * 
     * @throws NullPointerException  if the `size` is `null`
     * @throws IllegalArgumentException if the `resizeWeight` is negative
     */
    init {
        check(resizeWeight >= 0) { "The resize weight must be non-negative." }
        this.defaultAlignment = defaultAlignment
        this.size = size
        this.resizeWeight = resizeWeight
    }

    /**
     * Constructs a FormSpec from the specified encoded description.
     * The description will be parsed to set initial values.
     * 
     * @param defaultAlignment        the default alignment
     * @param encodedDescription    the encoded description
     */
    protected constructor(defaultAlignment: DefaultAlignment, encodedDescription: String, textMeasurer: TextMeasurer) :
        this(
            defaultAlignment,
            Sizes.ComponentSize.Default,
            NoGrow,
        ) {
            this.textMeasurer = textMeasurer
            parseAndInitValues(encodedDescription.lowercase())
        }

    // Public API ***********************************************************
    /**
     * Returns the default alignment.
     * 
     * @return the default alignment
     */
    fun getDefaultAlignment(): DefaultAlignment {
        return defaultAlignment
    }

    /**
     * Checks and answers whether this spec can grow or not.
     * That is the case if and only if the resize weight is
     * != `NO_GROW`.
     * 
     * @return true if it can grow, false if it can't grow
     */
    fun canGrow(): Boolean {
        return this.resizeWeight != NoGrow
    }

    // Abstract Behavior ****************************************************
    /**
     * Returns if this is a horizontal specification (vs. vertical).
     * Used to distinct between horizontal and vertical dialog units,
     * which have different conversion factors.
     * @return true for horizontal, false for vertical
     */
    abstract val isHorizontal: Boolean

    // Setting Values *********************************************************
    fun setDefaultAlignment(defaultAlignment: DefaultAlignment) {
        this.defaultAlignment = defaultAlignment
        this.defaultAlignmentExplictlySet = true
    }

    // Parsing **************************************************************
    /**
     * Parses an encoded form specification and initializes all required fields.
     * The encoded description must be in lower case.
     * 
     * @param encodedDescription   the FormSpec in an encoded format
     * 
     * @throws NullPointerException  if `encodedDescription` is `null`
     * @throws IllegalArgumentException if `encodedDescription`
     * is empty, whitespace, has no size, or is otherwise invalid
     */
    private fun parseAndInitValues(encodedDescription: String) {
        val token: Array<String> = TOKEN_SEPARATOR_PATTERN.split(encodedDescription)
        require(token.isNotEmpty()) { "The form spec must not be empty." }
        var nextIndex = 0
        var next = token[nextIndex++]

        // Check if the first token is an orientation.
        val alignment = DefaultAlignment.Companion.valueOf(next, this.isHorizontal)
        if (alignment != null) {
            setDefaultAlignment(alignment)
            require(token.size > 1) { "The form spec must provide a size." }
            next = token[nextIndex++]
        }
        this.size = parseSize(next)
        if (nextIndex < token.size) {
            this.resizeWeight = parseResizeWeight(token[nextIndex])
        }
    }

    /**
     * Parses an encoded size spec and returns the size.
     * 
     * @param token    a token that represents a size, either bounded or plain
     * @return the decoded Size
     */
    private fun parseSize(token: String): Size {
        if (token.startsWith("[") && token.endsWith("]")) {
            return parseBoundedSize(token)
        }
        if (token.startsWith("max(") && token.endsWith(")")) {
            return parseOldBoundedSize(token, false)
        }
        if (token.startsWith("min(") && token.endsWith(")")) {
            return parseOldBoundedSize(token, true)
        }
        return parseAtomicSize(token)
    }

    private fun parseBoundedSize(token: String): Size {
        val content = token.substring(1, token.length - 1)
        val subtoken: Array<String> = BOUNDS_SEPARATOR_PATTERN.split(content)
        var basis: Size? = null
        var lower: Size? = null
        var upper: Size? = null
        if (subtoken.size == 2) {
            val size1 = parseAtomicSize(subtoken[0])
            val size2 = parseAtomicSize(subtoken[1])
            if (isConstant(size1)) {
                if (isConstant(size2)) {
                    lower = size1
                    basis = size2
                    upper = size2
                } else {
                    lower = size1
                    basis = size2
                }
            } else {
                basis = size1
                upper = size2
            }
        } else if (subtoken.size == 3) {
            lower = parseAtomicSize(subtoken[0])
            basis = parseAtomicSize(subtoken[1])
            upper = parseAtomicSize(subtoken[2])
        }
        if ((lower == null || isConstant(lower))
            && (upper == null || isConstant(upper))
        ) {
            return BoundedSize(basis, lower, upper)
        }
        throw IllegalArgumentException(
            ("Illegal bounded size '" + token + "'. Must be one of:"
                + "\n[<constant size>,<logical size>]                 // lower bound"
                + "\n[<logical size>,<constant size>]                 // upper bound"
                + "\n[<constant size>,<logical size>,<constant size>] // lower and upper bound."
                + "\nExamples:"
                + "\n[50dlu,pref]                                     // lower bound"
                + "\n[pref,200dlu]                                    // upper bound"
                + "\n[50dlu,pref,200dlu]                              // lower and upper bound.")
        )
    }

    /**
     * Parses an encoded compound size and sets the size fields.
     * The compound size has format:
     * max(&lt;atomic size&gt;;&lt;atomic size2&gt;) | min(&lt;atomic size1&gt;;&lt;atomic size2&gt;)
     * One of the two atomic sizes must be a logical size, the other must
     * be a size constant.
     * 
     * @param token  a token for a bounded size, e.g. "max(50dlu; pref)"
     * @param setMax  if true we set a maximum size, otherwise a minimum size
     * @return a Size that represents the parse result
     */
    private fun parseOldBoundedSize(token: String, setMax: Boolean): Size {
        val semicolonIndex = token.indexOf(';')
        val sizeToken1 = token.substring(4, semicolonIndex)
        val sizeToken2 = token.substring(semicolonIndex + 1, token.length - 1)

        val size1 = parseAtomicSize(sizeToken1)
        val size2 = parseAtomicSize(sizeToken2)

        // Check valid combinations and set min or max.
        if (isConstant(size1)) {
            if (size2 is Sizes.ComponentSize) {
                return BoundedSize(
                    size2, if (setMax) null else size1,
                    if (setMax) size1 else null
                )
            }
            throw IllegalArgumentException(
                "Bounded sizes must not be both constants."
            )
        }
        if (isConstant(size2)) {
            return BoundedSize(
                size1, if (setMax) null else size2,
                if (setMax) size2 else null
            )
        }
        throw IllegalArgumentException(
            "Bounded sizes must not be both logical."
        )
    }

    /**
     * Decodes and returns an atomic size that is either a constant size or a
     * component size.
     * 
     * @param token    the encoded size
     * @return the decoded size either a constant or component size
     */
    private fun parseAtomicSize(token: String): Size {
        val trimmedToken = token.trim { it <= ' ' }
        if (trimmedToken.startsWith("'") && trimmedToken.endsWith("'")) {
            val length = trimmedToken.length
            require(length >= 2) { "Missing closing \"'\" for prototype." }
            require(::textMeasurer.isInitialized) { "Text measurer has not been initialized internally" }
            return PrototypeSize(textMeasurer, trimmedToken.substring(1, length - 1))
        }
        val componentSize: Sizes.ComponentSize? = Sizes.ComponentSize.parseValueOf(trimmedToken)
        if (componentSize != null) {
            return componentSize
        }
        return ConstantSize.valueOf(trimmedToken, this.isHorizontal)
    }

    // Misc *****************************************************************
    /**
     * Returns a string representation of this form specification.
     * The string representation consists of three elements separated by
     * a colon (<tt>":"</tt>), first the alignment, second the size,
     * and third the resize spec.
     *
     *
     * 
     * This method does *not* return an encoded version
     * of this object; the contrary is the case. Many instances
     * will return a string that cannot be parsed.
     *
     *
     * 
     * **Note:** The string representation may change at any time.
     * For parsing use [.encode] instead.
     * 
     * @return    a string representation of the form specification.
     */
    override fun toString(): String {
        val buffer = StringBuffer()
        buffer.append(defaultAlignment)

        buffer.append(":")
        buffer.append(size.toString())
        buffer.append(':')
        when (resizeWeight) {
            NoGrow -> {
                buffer.append("noGrow")
            }
            DefaultGrow -> {
                buffer.append("grow")
            }
            else -> {
                buffer.append("grow(")
                buffer.append(resizeWeight)
                buffer.append(')')
            }
        }
        return buffer.toString()
    }

    /**
     * Returns a string representation of this form specification.
     * The string representation consists of three elements separated by
     * a colon (<tt>":"</tt>), first the alignment, second the size,
     * and third the resize spec.
     *
     *
     * 
     * This method does *not* return an encoded version
     * of this object; the contrary is the case. Many instances
     * will return a string that cannot be parsed.
     *
     *
     * 
     * **Note:** The string representation may change at any time.
     * For parsing use [.encode] instead.
     * 
     * @return  a string representation of the form specification.
     */
    fun toShortString(): String {
        val buffer = StringBuffer()
        buffer.append(defaultAlignment.abbreviation())

        buffer.append(":")
        buffer.append(size.toString())
        buffer.append(':')
        when (resizeWeight) {
            NoGrow -> {
                buffer.append("n")
            }
            DefaultGrow -> {
                buffer.append("g")
            }
            else -> {
                buffer.append("g(")
                buffer.append(resizeWeight)
                buffer.append(')')
            }
        }
        return buffer.toString()
    }

    /**
     * Returns a short and parseable string representation of this
     * form specification. The string will omit the alignment and resize
     * specifications if these are the default values.
     *
     * @return  a string representation of the form specification.
     * 
     * @see .toShortString
     * @since 1.2
     */
    fun encode(): String {
        val buffer = StringBuffer()
        val alignmentDefault: DefaultAlignment = (if (this.isHorizontal)
            ColumnSpec.Default
        else
            RowSpec.Default)
        if (alignmentDefault != defaultAlignment) {
            buffer.append(defaultAlignment.abbreviation())
            buffer.append(":")
        }
        buffer.append(size.encode())
        when (resizeWeight) {
            NoGrow -> {
                // Omit the resize part
            }
            DefaultGrow -> {
                buffer.append(':')
                buffer.append("g")
            }
            else -> {
                buffer.append(':')
                buffer.append("g(")
                buffer.append(resizeWeight)
                buffer.append(')')
            }
        }
        return buffer.toString()
    }

    // Helper Code **********************************************************
    /**
     * Computes the maximum size for the given list of components, using
     * this form spec and the specified measure.
     *
     * Invoked by FormLayout to determine the size of one of my elements
     * 
     * @param container       the layout container
     * @param components      the list of components to measure
     * @param minMeasure      the measure used to determine the minimum size
     * @param prefMeasure     the measure used to determine the preferred size
     * @param defaultMeasure  the measure used to determine the default size
     * @return the maximum size in pixels
     */
    fun maximumSize(
        components: List<IntrinsicMeasurable>,
        minMeasure: Measure,
        prefMeasure: Measure,
        defaultMeasure: Measure
    ): Int {
        return size.maximumSize(
            components,
            minMeasure,
            prefMeasure,
            defaultMeasure
        )
    }

    /**
     * Enumeration for the column and row default alignment types.
     */
    enum class DefaultAlignment {
        /**
         * By default put components in the left.
         */
        LeftAlign,

        /**
         * By default put components in the right.
         */
        RightAlign,

        /**
         * By default put the components in the top.
         */
        TopAlign,

        /**
         * By default put the components in the bottom.
         */
        BottomAlign,

        /**
         * By default put the components in the center.
         */
        CenterAlign,

        /**
         * By default fill the column or row.
         */
        FillAlign,

        /**
         * A special alignment intended for table columns only,
         * where some cell renderers are not aligned.
         */
        NoAlign;

        /**
         * Returns the first character of this Alignment's name.
         * Used to identify it in short format strings.
         * 
         * @return the name's first character.
         */
        fun abbreviation(): Char {
            return name[0]
        }

        companion object {
            /**
             * Returns a DefaultAlignment that corresponds to the specified
             * string, null if no such alignment exists.
             * 
             * @param str    the encoded alignment
             * @param isHorizontal   indicates the values orientation
             * @return the corresponding DefaultAlignment or null
             */
            internal fun valueOf(str: String, isHorizontal: Boolean): DefaultAlignment? {
                if (str == "f" || str == "fill") {
                    return FillAlign
                } else if (str == "c" || str == "center") {
                    return CenterAlign
                } else if (isHorizontal) {
                    return when (str) {
                        "r", "right" -> {
                            RightAlign
                        }
                        "l", "left" -> {
                            LeftAlign
                        }
                        "none" -> {
                            NoAlign
                        }
                        else -> {
                            null
                        }
                    }
                } else {
                    return when (str) {
                        "t", "top" -> {
                            TopAlign
                        }
                        "b", "bottom" -> {
                            BottomAlign
                        }
                        else -> {
                            null
                        }
                    }
                }
            }

            // Serialization *****************************************************
            private var nextOrdinal = 0
        }
    }

    companion object {
        // Resizing Weights *****************************************************
        /**
         * Gives a column or row a fixed size.
         */
        const val NoGrow: Double = 0.0

        /**
         * The default resize weight.
         */
        const val DefaultGrow: Double = 1.0

        // Parser Patterns ******************************************************
        private val TOKEN_SEPARATOR_PATTERN: Pattern = Pattern.compile(":")

        private val BOUNDS_SEPARATOR_PATTERN: Pattern = Pattern.compile("\\s*,\\s*")

        /**
         * Decodes an encoded resize mode and resize weight and answers
         * the resize weight.
         * 
         * @param token    the encoded resize weight
         * @return the decoded resize weight
         * @throws IllegalArgumentException if the string description is an
         * invalid string representation
         */
        private fun parseResizeWeight(token: String): Double {
            if (token == "g" || token == "grow") {
                return DefaultGrow
            }
            if (token == "n" || token == "nogrow" || token == "none") {
                return NoGrow
            }
            // Must have format: grow(<double>)
            if ((token.startsWith("grow(") || token.startsWith("g("))
                && token.endsWith(")")
            ) {
                val leftParen = token.indexOf('(')
                val rightParen = token.indexOf(')')
                val substring = token.substring(leftParen + 1, rightParen)
                return substring.toDouble()
            }
            throw IllegalArgumentException(
                "The resize argument '" + token + "' is invalid. " +
                    " Must be one of: grow, g, none, n, grow(<double>), g(<double>)"
            )
        }

        private fun isConstant(aSize: Size?): Boolean {
            return aSize is ConstantSize
                || aSize is PrototypeSize
        }
    }
}
