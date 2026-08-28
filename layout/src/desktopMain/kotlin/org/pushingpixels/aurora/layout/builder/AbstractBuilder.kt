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
package org.pushingpixels.aurora.layout.builder

import androidx.compose.runtime.Composable
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An abstract class that minimizes the effort required to implement
 * non-visual builders that use the [org.pushingpixels.aurora.layout.FormLayout].
 *
 * Builders hide details of the FormLayout and provide convenience behavior
 * that assists you in constructing a form, bar, stack.
 * This class provides a cell cursor that helps you traverse a form while
 * you add components. Also, it offers several methods to append custom
 * and logical columns and rows.
 * 
 * @see [ButtonBarBuilder]
 * @see [ButtonStackBuilder]
 * @see [PanelBuilder]
 * @see [DefaultFormBuilder]
 */
public abstract class AbstractBuilder protected constructor(public val componentFactory: ComponentFactory) {
    protected val colSpecs: MutableList<ColumnSpec> = arrayListOf()
    protected val rowSpecs: MutableList<RowSpec> = arrayListOf()

    protected var colGroupIndices: Array<IntArray> = arrayOf()
    protected var rowGroupIndices: Array<IntArray> = arrayOf()

    /**
     * Holds an instance of [CellConstraints] that will be used to
     * specify the location, extent and alignments of the component to be
     * added next.
     */
    protected var currentCellConstraints: CellConstraints = CellConstraints(gridX = 1, gridY = 1)

    protected val componentLambdas: MutableList<Pair<ComponentLambda, CellConstraints>> = arrayListOf()

    public var debugConfiguration: FormLayoutDebugConfiguration? = null

    // Accessors ************************************************************

    /**
     * Returns the number of columns in the form.
     * 
     * @return the number of columns
     */
    public val columnCount: Int
        get() = colSpecs.size

    /**
     * Returns the number of rows in the form.
     * 
     * @return the number of rows
     */
    public val rowCount: Int
        get() = rowSpecs.size

    /**
     * Appends the given column specification to this builder's spec.
     */
    protected fun appendColumn(columnSpec: ColumnSpec) {
        this.colSpecs.add(columnSpec)
    }

    /**
     * Appends a column specification to this builder's spec
     * that represents the given string encoding
     *
     * @param encodedColumnSpec  the column specification object to append
     */
    @Composable
    protected fun appendColumn(encodedColumnSpec: String) {
        this.colSpecs.add(ColumnSpec.decode(encodedColumnSpec))
    }

    /**
     * Appends the given row specification to this builder's spec.
     */
    protected fun appendRow(rowSpec: RowSpec) {
        this.rowSpecs.add(rowSpec)
    }

    /**
     * Appends a row specification to this builder's spec
     * that represents the given string encoding
     *
     * @param encodedRowSpec  the row specification object to append
     */
    @Composable
    protected fun appendRow(encodedRowSpec: String) {
        this.rowSpecs.add(RowSpec.decode(encodedRowSpec))
    }

    public fun getRowSpec(rowIndex: Int): RowSpec {
        return this.rowSpecs[rowIndex - 1]
    }

    /**
     * Sets the row groups, where each row in such a group gets the same group
     * wide height. Each group is described by an array of integers that are
     * interpreted as row indices. The parameter is an array of such group
     * descriptions.
     *
     * **Examples:**
     * ```kotlin
     * // Group rows 1 and 2.
     * setRowGroups(arrayOf(intArrayOf(1, 2)))
     *
     * // Group rows 1 and 2, and group rows 5, 7, and 9.
     * setRowGroups(arrayOf(intArrayOf(1, 2), intArrayOf(5, 7, 9)))
     * ```
     *
     * @param groupOfIndices a two-dimensional array of row group indices
     *
     * @throws IndexOutOfBoundsException if an index is outside the grid
     * @throws IllegalArgumentException if a column index is used twice,
     * or if a group of indices contains only a single element
     */
    public fun setRowGroups(groupOfIndices: Array<IntArray>) {
        setRowGroupsImpl(groupOfIndices, true)
    }

    private fun setRowGroupsImpl(groupOfIndices: Array<IntArray>, checkIndices: Boolean) {
        val rowCount: Int = this.rowCount
        val usedIndices = BooleanArray(rowCount + 1)
        for (group in groupOfIndices.indices) {
            val indices = groupOfIndices[group]
            if (checkIndices) {
                require(indices.size >= 2) {
                    "Each index group must contain at least two indices."
                }
            }
            for (index in indices) {
                if (index !in 1..rowCount) {
                    throw IndexOutOfBoundsException(
                        "Invalid row group index $index in group ${group + 1}"
                    )
                }
                require(!usedIndices[index]) { "Row index $index must not be used in multiple row groups." }
                usedIndices[index] = true
            }
        }
        this.rowGroupIndices = deepClone(groupOfIndices)
    }

    /**
     * Sets a single row group, where each row gets the same height.
     *
     * **Examples:**
     * ```kotlin
     * // Group rows 1 and 2.
     * setRowGroup(arrayOf(intArrayOf(1, 2)))
     * ```
     *
     * @param indices   the indices for a single row group
     * @throws  IndexOutOfBoundsException if an index is outside the grid
     * @throws IllegalArgumentException if a row index is used twice
     * or if there is only a single index
     *
     * @see [setRowGroups]
     */
    public fun setRowGroup(vararg indices: Int) {
        require(indices.size >= 2) {
            "You must specify at least two indices."
        }
        setRowGroups(arrayOf(indices))
    }

    /**
     * Adds the specified row index to the last row group.
     * In case there are no groups, a new group will be created.
     *
     * @param rowIndex   the index of the row that should be grouped
     */
    public fun addGroupedRow(rowIndex: Int) {
        var newRowGroups: Array<IntArray> = deepClone(this.rowGroupIndices)
        // Create a group if none exists.
        if (newRowGroups.isEmpty()) {
            newRowGroups = arrayOf(intArrayOf(rowIndex))
        } else {
            val lastGroupIndex = newRowGroups.size - 1
            val lastGroup = newRowGroups[lastGroupIndex]
            val groupSize = lastGroup.size
            val newLastGroup = IntArray(groupSize + 1)
            System.arraycopy(lastGroup, 0, newLastGroup, 0, groupSize)
            newLastGroup[groupSize] = rowIndex
            newRowGroups[lastGroupIndex] = newLastGroup
        }
        setRowGroupsImpl(newRowGroups, false)
    }

    // Helper Code **********************************************************
    private fun deepClone(array: Array<IntArray>): Array<IntArray> {
        return Array(array.size) {
            index -> array[index].copyOf()
        }
    }
}
