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
import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.ComponentLambda
import org.pushingpixels.aurora.layout.FormSpecs
import org.pushingpixels.aurora.layout.factories.ComponentFactory

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An abstract class that minimizes the effort required to implement
 * non-visual builders that use the [org.pushingpixels.aurora.layout.FormLayout].
 *
 * Builders hide details of the FormLayout and provide convenience behavior
 * that assists you in constructing a form.
 * This class provides a cell cursor that helps you traverse a form while
 * you add components. Also, it offers several methods to append custom
 * and logical columns and rows.
 * 
 * @see [ButtonBarBuilder]
 * @see [ButtonStackBuilder]
 * @see PanelBuilder
 * @see [DefaultFormBuilder]
 */
public abstract class AbstractFormBuilder(componentFactory: ComponentFactory) : AbstractBuilder(componentFactory) {
    // Accessing the Cursor Location and Extent *****************************
    /**
     * The cursor's column.
     */
    public var column: Int
        get() = currentCellConstraints.gridX
        set(value) {
            currentCellConstraints = currentCellConstraints.copy(gridX = value)
        }

    /**
     * The cursor's row.
     */
    public var row: Int
        get() = currentCellConstraints.gridY
        set(value) {
            currentCellConstraints = currentCellConstraints.copy(gridY = value)
        }

    /**
     * The cursor's column span.
     */
    public var columnSpan: Int
        get() = currentCellConstraints.gridWidth
        set(value) {
            currentCellConstraints = currentCellConstraints.copy(gridWidth = value)
        }

    /**
     * The cursor's row span.
     */
    public var rowSpan: Int
        get() = currentCellConstraints.gridHeight
        set(value) {
            currentCellConstraints = currentCellConstraints.copy(gridHeight = value)
        }

    /**
     * Sets the cursor's origin to the given column and row.
     * 
     * @param column    the new column index
     * @param row       the new row index
     */
    public fun setOrigin(column: Int, row: Int) {
        currentCellConstraints = currentCellConstraints.copy(gridX = column, gridY = row)
    }

    /**
     * Sets the cursor's extent to the given column span and row span.
     * 
     * @param columnSpan    the new column span (grid width)
     * @param rowSpan       the new row span (grid height)
     */
    public fun setExtent(columnSpan: Int, rowSpan: Int) {
        currentCellConstraints = currentCellConstraints.copy(gridWidth = columnSpan, gridHeight = rowSpan)
    }

    /**
     * Sets the cell bounds (location and extent) to the given column, row,
     * column span and row span.
     * 
     * @param column       the new column index (grid x)
     * @param row          the new row index     (grid y)
     * @param columnSpan   the new column span  (grid width)
     * @param rowSpan      the new row span     (grid height)
     */
    public fun setBounds(column: Int, row: Int, columnSpan: Int, rowSpan: Int) {
        currentCellConstraints = currentCellConstraints.copy(gridX = column, gridY = row,
            gridWidth = columnSpan, gridHeight = rowSpan)
    }

    // Accessing the Cursor Location and Extent *****************************
    /**
     * Sets the horizontal alignment.
     * 
     * @param alignment the new horizontal alignment
     */
    public fun setHAlignment(alignment: CellConstraints.Alignment) {
        currentCellConstraints = currentCellConstraints.copy(hAlign = alignment)
    }

    /**
     * Sets the vertical alignment.
     * 
     * @param alignment the new vertical alignment
     */
    public fun setVAlignment(alignment: CellConstraints.Alignment) {
        currentCellConstraints = currentCellConstraints.copy(vAlign = alignment)
    }

    /**
     * Sets the horizontal and vertical alignment.
     * 
     * @param hAlign the new horizontal alignment
     * @param vAlign the new vertical alignment
     */
    public fun setAlignment(hAlign: CellConstraints.Alignment, vAlign: CellConstraints.Alignment) {
        currentCellConstraints = currentCellConstraints.copy(hAlign = hAlign, vAlign = vAlign)
    }

    /**
     * Moves to the next column.
     * 
     * @param columns    number of columns to move
     */
    public fun nextColumn(columns: Int = 1) {
        val currGridX = currentCellConstraints.gridX
        currentCellConstraints = currentCellConstraints.copy(gridX = currGridX + columns)
    }

    /**
     * Increases the row by the specified rows.
     * 
     * @param rows   number of rows to move
     */
    public fun nextRow(rows: Int = 1) {
        val currGridY = currentCellConstraints.gridY
        currentCellConstraints = currentCellConstraints.copy(gridY = currGridY + rows)
    }

    /**
     * Moves the cursor down several lines: increases the row by the
     * specified number of lines and sets the cursor to the leading column.
     * 
     * @param lines  number of rows to move
     */
    public fun nextLine(lines: Int = 1) {
        nextRow(lines)
        column = 1
    }

    // Appending Columns ******************************************************

    /**
     * Appends a glue column.
     * 
     * @see [appendLabelComponentsGapColumn]
     * @see [appendRelatedComponentsGapColumn]
     * @see [appendUnrelatedComponentsGapColumn]
     */
    public fun appendGlueColumn() {
        appendColumn(FormSpecs.GlueColSpec)
    }

    /**
     * Appends a column that is the default gap between a label and
     * its associated component.
     * 
     * @see [appendGlueColumn]
     * @see [appendRelatedComponentsGapColumn]
     * @see [appendUnrelatedComponentsGapColumn]
     */
    public fun appendLabelComponentsGapColumn() {
        appendColumn(FormSpecs.LabelComponentGapColSpec)
    }

    /**
     * Appends a column that is the default gap for related components.
     * 
     * @see [appendGlueColumn]
     * @see [appendLabelComponentsGapColumn]
     * @see [appendUnrelatedComponentsGapColumn]
     */
    public fun appendRelatedComponentsGapColumn() {
        appendColumn(FormSpecs.RelatedGapColSpec)
    }

    /**
     * Appends a column that is the default gap for unrelated components.
     * 
     * @see [appendGlueColumn]
     * @see [appendLabelComponentsGapColumn]
     * @see [appendRelatedComponentsGapColumn]
     */
    public fun appendUnrelatedComponentsGapColumn() {
        appendColumn(FormSpecs.UnrelatedGapColSpec)
    }

    // Appending Rows ********************************************************

    /**
     * Appends a glue row.
     * 
     * @see [appendRelatedComponentsGapRow]
     * @see [appendUnrelatedComponentsGapRow]
     * @see [appendParagraphGapRow]
     */
    public fun appendGlueRow() {
        appendRow(FormSpecs.GlueRowSpec)
    }

    /**
     * Appends a row that is the default gap for related components.
     * 
     * @see [appendGlueRow]
     * @see [appendUnrelatedComponentsGapRow]
     * @see [appendParagraphGapRow]
     */
    public fun appendRelatedComponentsGapRow() {
        appendRow(FormSpecs.RelatedGapRowSpec)
    }

    /**
     * Appends a row that is the default gap for unrelated components.
     * 
     * @see [appendGlueRow]
     * @see [appendRelatedComponentsGapRow]
     * @see [appendParagraphGapRow]
     */
    public fun appendUnrelatedComponentsGapRow() {
        appendRow(FormSpecs.UnrelatedGapRowSpec)
    }

    /**
     * Appends a row that is the default gap for paragraphs.
     * 
     * @see [appendGlueRow]
     * @see [appendRelatedComponentsGapRow]
     * @see [appendUnrelatedComponentsGapRow]
     */
    public fun appendParagraphGapRow() {
        appendRow(FormSpecs.ParagraphGapRowSpec)
    }

    // Adding Components ****************************************************
    /**
     * Adds a component to the panel using the given cell constraints.
     * 
     * @param component        the component to add
     * @param cellConstraints  the component's cell constraints
     */
    @Composable
    public fun component(component: ComponentLambda, cellConstraints: CellConstraints) {
        this.componentLambdas.add(Pair(component, cellConstraints))
    }

    /**
     * Adds a component to the panel using the given encoded cell constraints.
     * 
     * @param component               the component to add
     * @param encodedCellConstraints  the component's encoded cell constraints
     */
    @Composable
    public fun component(component: ComponentLambda, encodedCellConstraints: String) {
        this.componentLambdas.add(Pair(component, CellConstraints.fromConstraints(encodedCellConstraints)))
    }

    /**
     * Adds a component to the container using the current cell constraints.
     *
     * @param component    the component to add
     *
     * @see [component]
     * @see [createAdjustedConstraints]
     */
    @Composable
    public fun component(component: ComponentLambda) {
        this.componentLambdas.add(Pair(component, currentCellConstraints))
    }

    // Misc *****************************************************************
    /**
     * Creates and returns a [CellConstraints] object at
     * the current cursor position that uses the given column span.
     * 
     * @param columnSpan   the column span to be used in the constraints
     * @return [CellConstraints] adjusted to the given column span
     */
    protected fun createAdjustedConstraints(columnSpan: Int): CellConstraints {
        return currentCellConstraints.copy(gridWidth = columnSpan)
    }
}
