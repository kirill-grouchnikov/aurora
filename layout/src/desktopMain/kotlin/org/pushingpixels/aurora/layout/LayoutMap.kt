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
import org.pushingpixels.aurora.layout.util.LayoutStyle

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Provides a hierarchical variable expansion useful to improve layout
 * consistency, style guide compliance, and layout readability.
 *
 *
 * 
 * A LayoutMap maps variable names to layout expression Strings. The FormLayout,
 * ColumnSpec, and RowSpec parsers expand variables before an encoded layout
 * specification is parsed and converted into ColumnSpec and RowSpec values.
 * Variables start with the '$' character. The variable name can be wrapped
 * by braces ('{' and '}'). For example, you can write:
 * `new FormLayout("pref, $lcg, pref")` or
 * `new FormLayout("pref, ${lcg}, pref")`.
 *
 *
 * 
 * LayoutMaps build a chain; each LayoutMap has an optional parent map.
 * The root is defined by [LayoutMap.getRoot]. Application-wide
 * variables should be defined in the root LayoutMap. If you want to override
 * application-wide variables locally, obtain a LayoutMap using `new LayoutMap()`, configure it, and provide it as argument to the
 * FormLayout, ColumnSpec, and RowSpec constructors/factory methods.
 *
 *
 * 
 * By default the root LayoutMap provides the following associations:
 * <table border="1">
 * <tr><td>**Variable Name**</td><td>**Abbreviations**</td><td>**Orientation**</td><td>**Description**</td></tr>
 * <tr><td>label-component-gap</td><td>lcg, lcgap</td><td>both</td><td>gap between a label and the labeled component</td></tr>
 * <tr><td>related-gap</td><td>rg, rgap</td><td>both</td><td>gap between two related components</td></tr>
 * <tr><td>unrelated-gap</td><td>ug, ugap</td><td>both</td><td>gap between two unrelated components</td></tr>
 * <tr><td>button</td><td>b</td><td>horizontal</td><td>button column with minimum width</td></tr>
 * <tr><td>line-gap</td><td>lg, lgap</td><td>vertical</td><td>gap between two lines</td></tr>
 * <tr><td>narrow-line-gap</td><td>nlg, nlgap</td><td>vertical</td><td>narrow gap between two lines</td></tr>
 * <tr><td>paragraph</td><td>pg, pgap</td><td>vertical</td><td>gap between two paragraphs/sections</td></tr>
</table> * 
 * 
 * 
 * **Examples:**
 * <pre>
 * // Predefined variables
 * new FormLayout(
 * "pref, $lcgap, pref, $rgap, pref",
 * "p, $lgap, p, $lgap, p");
 * 
 * // Custom variables
 * LayoutMap.getRoot().columnPut("half", "39dlu");
 * LayoutMap.getRoot().columnPut("full", "80dlu");
 * LayoutMap.getRoot().rowPut("table", "fill:0:grow");
 * LayoutMap.getRoot().rowPut("table50", "fill:50dlu:grow");
 * new FormLayout(
 * "pref, $lcgap, $half, 2dlu, $half",
 * "p, $lcgap, $table50");
 * new FormLayout(
 * "pref, $lcgap, $full",
 * "p, $lcgap, $table50");
 * 
 * // Nested variables
 * LayoutMap.getRoot().columnPut("c-gap-c", "$half, 2dlu, $half");
 * new FormLayout(
 * "pref, $lcgap, ${c-gap-c}", // -> "pref, $lcgap, $half, 2dlu, $half",
 * "p, $lcgap, $table");
</pre> * 
 * 
 * LayoutMap holds two internal Maps that associate key Strings with expression
 * Strings for the columns and rows respectively. Null values are not allowed.
 *
 *
 * 
 * **Tips:**
 *  * You should carefully override predefined variables,
 * because variable users may expect that these don't change.
 *  * Set custom variables in the root LayoutMap.
 *  * Avoid aliases for custom variables.
 * 
 * 
 * @see FormLayout
 * @see ColumnSpec
 * @see RowSpec
 *
 * @since 1.2
 */
public class LayoutMap constructor(
    /**
     * Refers to the parent map that is used to look up values
     * if this map contains no association for a given key.
     * The parent maps can build chains.
     */
    private val parent: LayoutMap? = root
) {
    // Instance Fields ********************************************************

    /**
     * Holds the raw associations from variable names to expressions.
     * The expression may contain variables that are not expanded.
     */
    private val columnMap: MutableMap<String?, String?> = HashMap()

    /**
     * Holds the cached associations from variable names to expressions.
     * The expression are fully expanded and contain no variables.
     */
    private val columnMapCache: MutableMap<String?, String?> = HashMap()

    /**
     * Holds the raw associations from variable names to expressions.
     * The expression may contain variables that are not expanded.
     */
    private val rowMap: MutableMap<String?, String?> = HashMap()

    /**
     * Holds the cached associations from variable names to expressions.
     * The expression are fully expanded and contain no variables.
     */
    private val rowMapCache: MutableMap<String?, String?> = HashMap()

    // Column Mapping *********************************************************
    /**
     * Returns `true` if this map or a parent map - if any - contains
     * a mapping for the specified key.
     * 
     * @param key key whose presence in this LayoutMap chain is to be tested.
     * @return `true` if this map contains a column mapping
     * for the specified key.
     * 
     * @throws NullPointerException if the key is `null`.
     * 
     * @see Map.containsKey
     */
    public fun columnContainsKey(key: String): Boolean {
        val resolvedKey: String = resolveColumnKey(key)
        return columnMap.containsKey(resolvedKey)
            || parent != null && parent.columnContainsKey(resolvedKey)
    }

    /**
     * Looks up and returns the String associated with the given key.
     * First looks for an association in this LayoutMap. If there's no
     * association, the lookup continues with the parent map - if any.
     * 
     * @param key key whose associated value is to be returned.
     * @return the column String associated with the `key`,
     * or `null` if no LayoutMap in the parent chain
     * contains an association.
     * 
     * @throws NullPointerException  if `key` is `null`
     * 
     * @see Map.get
     */
    public fun columnGet(key: String): String? {
        val resolvedKey: String = resolveColumnKey(key)
        val cachedValue = columnMapCache[resolvedKey]
        if (cachedValue != null) {
            return cachedValue
        }
        var value = columnMap[resolvedKey]
        if (value == null && parent != null) {
            value = parent.columnGet(resolvedKey)
        }
        if (value == null) {
            return null
        }
        val expandedString = expand(value, true)
        columnMapCache[resolvedKey] = expandedString
        return expandedString
    }

    /**
     * Associates the specified column String with the specified key
     * in this map.
     * If the map previously contained a mapping for this key, the old value
     * is replaced by the specified value. The value set in this map
     * overrides an association - if any - in the chain of parent LayoutMaps.
     *
     *
     * 
     * The `value` must not be `null`. To remove
     * an association from this map use [.columnRemove].
     * 
     * @param key key with which the specified value is to be associated.
     * @param value column expression value to be associated with the specified key.
     * @return previous String associated with specified key,
     * or `null` if there was no mapping for key.
     * 
     * @throws NullPointerException if the `key` or `value`
     * is `null`.
     * 
     * @see Map.put
     */
    public fun columnPut(key: String, value: String): String? {
        val resolvedKey: String = resolveColumnKey(key)
        columnMapCache.clear()
        return columnMap.put(
            resolvedKey,
            value.lowercase()
        )
    }

    public fun columnPut(key: String, value: ColumnSpec): String? {
        return columnPut(key, value.encode())
    }

    public fun columnPut(key: String, value: Size): String? {
        return columnPut(key, value.encode())
    }

    /**
     * Removes the column value mapping for this key from this map if it is
     * present.
     *
     *
     * 
     * Returns the value to which the map previously associated the key,
     * or `null` if the map contained no mapping for this key.
     * The map will not contain a String mapping for the specified key
     * once the call returns.
     * 
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or `null`
     * if there was no mapping for key.
     * 
     * @throws NullPointerException if `key` is `null`.
     * 
     * @see Map.remove
     */
    public fun columnRemove(key: String): String? {
        val resolvedKey: String = resolveColumnKey(key)
        columnMapCache.clear()
        return columnMap.remove(resolvedKey)
    }

    // Row Mapping ************************************************************
    /**
     * Returns `true` if this map or a parent map - if any - contains
     * a RowSpec mapping for the specified key.
     * 
     * @param key key whose presence in this LayoutMap chain is to be tested.
     * @return `true` if this map contains a row mapping
     * for the specified key.
     * 
     * @throws NullPointerException if the key is `null`.
     * 
     * @see Map.containsKey
     */
    public fun rowContainsKey(key: String): Boolean {
        val resolvedKey: String = resolveRowKey(key)
        return rowMap.containsKey(resolvedKey)
            || parent != null && parent.rowContainsKey(resolvedKey)
    }

    /**
     * Looks up and returns the RowSpec associated with the given key.
     * First looks for an association in this LayoutMap. If there's no
     * association, the lookup continues with the parent map - if any.
     * 
     * @param key key whose associated value is to be returned.
     * @return the row specification associated with the `key`,
     * or `null` if no LayoutMap in the parent chain
     * contains an association.
     * 
     * @throws NullPointerException  if `key` is `null`
     * 
     * @see Map.get
     */
    public fun rowGet(key: String): String? {
        val resolvedKey: String = resolveRowKey(key)
        val cachedValue = rowMapCache[resolvedKey]
        if (cachedValue != null) {
            return cachedValue
        }
        var value = rowMap[resolvedKey]
        if (value == null && parent != null) {
            value = parent.rowGet(resolvedKey)
        }
        if (value == null) {
            return null
        }
        val expandedString = expand(value, false)
        rowMapCache[resolvedKey] = expandedString
        return expandedString
    }

    public fun rowPut(key: String, value: String): String? {
        val resolvedKey: String = resolveRowKey(key)
        rowMapCache.clear()
        return rowMap.put(
            resolvedKey,
            value.lowercase()
        )
    }

    /**
     * Associates the specified ColumnSpec with the specified key in this map.
     * If the map previously contained a mapping for this key, the old value
     * is replaced by the specified value. The RowSpec set in this map
     * override an association - if any - in the chain of parent LayoutMaps.
     *
     *
     * 
     * The RowSpec must not be `null`. To remove an association
     * from this map use [.rowRemove].
     * 
     * @param key key with which the specified value is to be associated.
     * @param value ColumnSpec to be associated with the specified key.
     * @return previous ColumnSpec associated with specified key,
     * or `null` if there was no mapping for key.
     * 
     * @throws NullPointerException if the `key` or `value`
     * is `null`.
     * 
     * @see Map.put
     */
    public fun rowPut(key: String, value: RowSpec): String? {
        return rowPut(key, value.encode())
    }

    public fun rowPut(key: String, value: Size): String? {
        return rowPut(key, value.encode())
    }

    /**
     * Removes the row value mapping for this key from this map if it is
     * present.
     *
     *
     * 
     * Returns the value to which the map previously associated the key,
     * or `null` if the map contained no mapping for this key.
     * The map will not contain a String mapping for the specified key
     * once the call returns.
     * 
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or `null`
     * if there was no mapping for key.
     * 
     * @throws NullPointerException if `key` is `null`.
     * 
     * @see Map.remove
     */
    public fun rowRemove(key: String): String? {
        val resolvedKey: String = resolveRowKey(key)
        rowMapCache.clear()
        return rowMap.remove(resolvedKey)
    }

    // Overriding Object Behavior *********************************************
    /**
     * Returns a string representation of this LayoutMap that lists
     * the column and row associations.
     * 
     * @return a string representation
     */
    override fun toString(): String {
        val buffer = StringBuffer(super.toString())
        buffer.append("\n  Column associations:")
        for ((key, value) in columnMap) {
            buffer.append("\n    ")
            buffer.append(key)
            buffer.append("->")
            buffer.append(value)
        }
        buffer.append("\n  Row associations:")
        for ((key, value) in rowMap) {
            buffer.append("\n    ")
            buffer.append(key)
            buffer.append("->")
            buffer.append(value)
        }
        return buffer.toString()
    }

    // String Expansion *******************************************************
    public fun expand(expression: String, horizontal: Boolean): String {
        var cursor = 0
        var start: Int = expression.indexOf(VARIABLE_PREFIX_CHAR, cursor)
        if (start == -1) { // No variables
            return expression
        }
        val buffer = StringBuffer()
        do {
            buffer.append(expression.substring(cursor, start))
            val variableName: String = nextVariableName(expression, start)
            buffer.append(expansion(variableName, horizontal))
            cursor = start + variableName.length + 1
            start = expression.indexOf(VARIABLE_PREFIX_CHAR, cursor)
        } while (start != -1)
        buffer.append(expression.substring(cursor))
        return buffer.toString()
    }

    private fun expansion(variableName: String, horizontal: Boolean): String {
        val key: String = stripBraces(variableName)
        val expansion = if (horizontal) columnGet(key) else rowGet(key)
        if (expansion == null) {
            val orientation = if (horizontal) "column" else "row"
            throw IllegalArgumentException("Unknown $orientation layout variable \"$key\"")
        }
        return expansion
    }

    private fun columnPut(key: String, aliases: Array<String>, value: ColumnSpec) {
        ensureLowerCase(key)
        columnPut(key, value)
        for (alias in aliases) {
            ensureLowerCase(alias)
            COLUMN_ALIASES[alias] = key
        }
    }

    private fun rowPut(key: String, aliases: Array<String>, value: RowSpec) {
        ensureLowerCase(key)
        rowPut(key, value)
        for (alias in aliases) {
            ensureLowerCase(alias)
            ROW_ALIASES[alias] = key
        }
    }

    public companion object {
        /**
         * Marks a layout variable; used by the Forms parsers.
         */
        private const val VARIABLE_PREFIX_CHAR = '$'

        /**
         * Maps column aliases to their default name, for example
         * `"rgap"` -> `"related-gap"`.
         */
        private val COLUMN_ALIASES: MutableMap<String?, String?> = HashMap()

        /**
         * Maps row aliases to their default name, for example
         * `"rgap"` -> `"related-gap"`.
         */
        private val ROW_ALIASES: MutableMap<String?, String?> = HashMap()

        private lateinit var root: LayoutMap
        /**
         * Holds the lazily initialized root map.
         */
        @Composable
        public fun getRoot(): LayoutMap {
            if (!::root.isInitialized) {
                root = createRoot()
            }
            return root
        }

        // Default ****************************************************************

        private fun nextVariableName(expression: String, start: Int): String {
            val length = expression.length
            if (length <= start) {
                FormSpecParser.fail(expression, start, "Missing variable name after variable char '$'.")
            }
            if (expression[start + 1] == '{') {
                val end = expression.indexOf('}', start + 1)
                if (end == -1) {
                    FormSpecParser.fail(expression, start, "Missing closing brace '}' for variable.")
                }
                return expression.substring(start + 1, end + 1)
            }
            var end = start + 1
            while (end < length
                && Character.isUnicodeIdentifierPart(expression[end])
            ) {
                end++
            }
            return expression.substring(start + 1, end)
        }

        private fun stripBraces(variableName: String): String {
            return if (variableName[0] == '{')
                variableName.substring(1, variableName.length - 1)
            else
                variableName
        }

        // Helper Code ************************************************************
        private fun resolveColumnKey(key: String): String {
            //checkNotNull(key, "The column key must not be null.")
            val lowercaseKey = key.lowercase()
            val defaultKey: String? = COLUMN_ALIASES[lowercaseKey]
            return defaultKey ?: lowercaseKey
        }

        private fun resolveRowKey(key: String): String {
            //checkNotNull(key, "The row key must not be null.")
            val lowercaseKey = key.lowercase()
            val defaultKey: String? = ROW_ALIASES[lowercaseKey]
            return defaultKey ?: lowercaseKey
        }

        @Composable
        private fun createRoot(): LayoutMap {
            val map = LayoutMap(null)

            // Column variables
            map.columnPut(
                "label-component-gap",
                arrayOf<String>("lcg", "lcgap"),
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC
            )
            map.columnPut(
                "related-gap",
                arrayOf<String>("rg", "rgap"),
                FormSpecs.RELATED_GAP_COLSPEC
            )
            map.columnPut(
                "unrelated-gap",
                arrayOf<String>("ug", "ugap"),
                FormSpecs.UNRELATED_GAP_COLSPEC
            )
            map.columnPut(
                "button",
                arrayOf<String>("b"),
                FormSpecs.BUTTON_COLSPEC
            )
            map.columnPut(
                "growing-button",
                arrayOf<String>("gb"),
                FormSpecs.GROWING_BUTTON_COLSPEC
            )
            map.columnPut(
                "dialog-margin",
                arrayOf<String>("dm", "dmargin"),
                ColumnSpec.createGap(LayoutStyle.current.dialogMarginX)
            )
            map.columnPut(
                "tabbed-dialog-margin",
                arrayOf<String>("tdm", "tdmargin"),
                ColumnSpec.createGap(LayoutStyle.current.tabbedDialogMarginX)
            )
            map.columnPut(
                "glue",
                FormSpecs.GLUE_COLSPEC.toShortString()
            )

            // Row variables
            map.rowPut(
                "label-component-gap",
                arrayOf<String>("lcg", "lcgap"),
                FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC
            )
            map.rowPut(
                "related-gap",
                arrayOf<String>("rg", "rgap"),
                FormSpecs.RELATED_GAP_ROWSPEC
            )
            map.rowPut(
                "unrelated-gap",
                arrayOf<String>("ug", "ugap"),
                FormSpecs.UNRELATED_GAP_ROWSPEC
            )
            map.rowPut(
                "narrow-line-gap",
                arrayOf<String>("nlg", "nlgap"),
                FormSpecs.NARROW_LINE_GAP_ROWSPEC
            )
            map.rowPut(
                "line-gap",
                arrayOf<String>("lg", "lgap"),
                FormSpecs.LINE_GAP_ROWSPEC
            )
            map.rowPut(
                "paragraph-gap",
                arrayOf<String>("pg", "pgap"),
                FormSpecs.PARAGRAPH_GAP_ROWSPEC
            )
            map.rowPut(
                "dialog-margin",
                arrayOf<String>("dm", "dmargin"),
                RowSpec.createGap(LayoutStyle.current.dialogMarginY)
            )
            map.rowPut(
                "tabbed-dialog-margin",
                arrayOf<String>("tdm", "tdmargin"),
                RowSpec.createGap(LayoutStyle.current.tabbedDialogMarginY)
            )
            map.rowPut(
                "button",
                arrayOf<String>("b"),
                FormSpecs.BUTTON_ROWSPEC
            )
            map.rowPut(
                "glue",
                FormSpecs.GLUE_ROWSPEC
            )

            return map
        }

        private fun ensureLowerCase(str: String) {
            val lowerCase = str.lowercase()
            require(lowerCase == str) { "The string \"$str\" should be lower case." }
        }
    }
}
