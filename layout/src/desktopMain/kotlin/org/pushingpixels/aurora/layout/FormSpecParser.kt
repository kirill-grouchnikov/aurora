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
import java.util.regex.Matcher
import java.util.regex.Pattern

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Parses encoded column and row specifications.
 * Returns ColumnSpec or RowSpec arrays if successful,
 * and aims to provide useful information in case of a syntax error.
 * 
 * @see ColumnSpec
 * @see RowSpec
 */
public class FormSpecParser private constructor(
    source: String,
    description: String?,
    private val layoutMap: LayoutMap,
    horizontal: Boolean
) {
    // Instance Fields ********************************************************
    private val source: String = this.layoutMap.expand(source, horizontal)
    //checkNotNull(source, "The %S must not be null.", description)
    //checkNotNull(layoutMap, "The LayoutMap must not be null.")

    // Instance Creation ******************************************************

    // Parser Implementation **************************************************
    @Composable
    private fun parseColumnSpecs(): List<ColumnSpec> {
        val encodedColumnSpecs: List<String> = split(source, 0)
        val columnCount = encodedColumnSpecs.size
        val columnSpecs = arrayListOf<ColumnSpec>()
        for (i in 0..<columnCount) {
            val encodedSpec = encodedColumnSpecs[i]
            columnSpecs.add(ColumnSpec.decodeExpanded(encodedSpec))
        }
        return columnSpecs
    }

    @Composable
    private fun parseRowSpecs(): List<RowSpec> {
        val encodedRowSpecs: List<String> = split(source, 0)
        val rowCount = encodedRowSpecs.size
        val rowSpecs = arrayListOf<RowSpec>()
        for (i in 0..<rowCount) {
            val encodedSpec = encodedRowSpecs[i]
            rowSpecs.add(RowSpec.decodeExpanded(encodedSpec))
        }
        return rowSpecs
    }

    // Parser Implementation **************************************************
    private fun split(expression: String, offset: Int): MutableList<String> {
        val encodedSpecs: MutableList<String> = ArrayList()
        var parenthesisLevel = 0 // number of open '('
        var bracketLevel = 0 // number of open '['
        var quoteLevel = 0 // number of open '\''
        val length = expression.length
        var specStart = 0
        var c: Char
        var lead = true
        for (i in 0..<length) {
            c = expression[i]
            if (lead && Character.isWhitespace(c)) {
                specStart++
                continue
            }
            lead = false
            if (c == ',' && parenthesisLevel == 0 && bracketLevel == 0 && quoteLevel == 0) {
                val token = expression.substring(specStart, i)
                addSpec(encodedSpecs, token, offset + specStart)
                specStart = i + 1
                lead = true
            } else if (c == '(') {
                if (bracketLevel > 0) {
                    fail(offset + i, "illegal '(' in [...]")
                }
                parenthesisLevel++
            } else if (c == ')') {
                if (bracketLevel > 0) {
                    fail(offset + i, "illegal ')' in [...]")
                }
                parenthesisLevel--
                if (parenthesisLevel < 0) {
                    fail(offset + i, "missing '('")
                }
            } else if (c == '[') {
                if (bracketLevel > 0) {
                    fail(offset + i, "too many '['")
                }
                bracketLevel++
            } else if (c == ']') {
                bracketLevel--
                if (bracketLevel < 0) {
                    fail(offset + i, "missing '['")
                }
            } else if (c == '\'') {
                if (quoteLevel == 0) {
                    quoteLevel++
                } else if (quoteLevel == 1) {
                    quoteLevel--
                }
            }
        }
        if (parenthesisLevel > 0) {
            fail(offset + length, "missing ')'")
        }
        if (bracketLevel > 0) {
            fail(offset + length, "missing ']")
        }
        if (specStart < length) {
            val token = expression.substring(specStart)
            addSpec(encodedSpecs, token, offset + specStart)
        }
        return encodedSpecs
    }

    private fun addSpec(encodedSpecs: MutableList<String>, expression: String, offset: Int) {
        val trimmedExpression = expression.trim { it <= ' ' }
        val multiplier = multiplier(trimmedExpression, offset)
        if (multiplier == null) {
            encodedSpecs.add(trimmedExpression)
            return
        }
        val subTokenList = split(multiplier.expression, offset + multiplier.offset)
        for (i in 0..<multiplier.multiplier) {
            encodedSpecs.addAll(subTokenList)
        }
    }

    private fun multiplier(expression: String, offset: Int): Multiplier? {
        val matcher: Matcher = MULTIPLIER_PREFIX_PATTERN.matcher(expression)
        if (!matcher.find()) {
            return null
        }
        if (matcher.start() > 0) {
            fail(offset + matcher.start(), "illegal multiplier position")
        }
        val digitMatcher: Matcher = DIGIT_PATTERN.matcher(expression)
        if (!digitMatcher.find()) {
            return null
        }
        val digitStr = expression.substring(0, digitMatcher.end())
        if (digitStr.startsWith("-")) {
            fail(offset, "illegal negative multiplier designation")
        }
        var number = 0
        try {
            number = digitStr.toInt()
        } catch (ex: NumberFormatException) {
            fail(offset, ex)
        }
        if (number < 0) {  // Due to integer overflow
            fail(offset, "illegal negative multiplier")
        }
        val subexpression = expression.substring(matcher.end(), expression.length - 1)
        return Multiplier(number, subexpression, matcher.end())
    }

    private fun fail(index: Int, description: String?) {
        throw FormLayoutParseException(
            message(source, index, description)
        )
    }

    private fun fail(index: Int, cause: NumberFormatException?) {
        throw FormLayoutParseException(
            message(source, index, "Invalid multiplier"),
            cause
        )
    }

    /**
     * Used by the parser for encoded column and row specifications.
     */
    public class FormLayoutParseException : RuntimeException {
        internal constructor(message: String?) : super(message)

        internal constructor(message: String?, cause: Throwable?) : super(message, cause)
    }

    // Helper Class ***********************************************************
    /**
     * Internal helper class that is returned by
     * [FormSpecParser.multiplier].
     */
    internal class Multiplier(val multiplier: Int, val expression: String, val offset: Int)

    internal companion object {
        // Parser Patterns ******************************************************
        private val MULTIPLIER_PREFIX_PATTERN: Pattern = Pattern.compile("-?\\d+\\s*\\*\\s*\\(")

        private val DIGIT_PATTERN: Pattern = Pattern.compile("-?\\d+")

        // Parser API *************************************************************
        @Composable
        internal fun parseColumnSpecs(
            encodedColumnSpecs: String,
            layoutMap: LayoutMap
        ): List<ColumnSpec> {
            val parser = FormSpecParser(
                encodedColumnSpecs,
                "encoded column specifications",
                layoutMap,
                true
            )
            return parser.parseColumnSpecs()
        }

        @Composable
        internal fun parseRowSpecs(
            encodedRowSpecs: String,
            layoutMap: LayoutMap
        ): List<RowSpec> {
            val parser = FormSpecParser(
                encodedRowSpecs,
                "encoded row specifications",
                layoutMap,
                false
            )
            return parser.parseRowSpecs()
        }

        // Exceptions *************************************************************
        internal fun fail(source: String?, index: Int, description: String?) {
            throw FormLayoutParseException(message(source, index, description))
        }

        private fun message(source: String?, index: Int, description: String?): String? {
            val buffer = StringBuffer('\n'.code)
            buffer.append('\n')
            buffer.append(source)
            buffer.append('\n')
            for (i in 0..<index) {
                buffer.append(' ')
            }
            buffer.append('^')
            buffer.append(description)
            val message = buffer.toString()
            throw FormLayoutParseException(message)
        }
    }
}
