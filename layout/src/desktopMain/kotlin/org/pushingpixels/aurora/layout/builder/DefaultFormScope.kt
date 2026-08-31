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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

/**
 * Provides a means to build form-oriented panels quickly and consistently
 * using the [org.pushingpixels.aurora.layout.FormLayout]. This scope
 * combines frequently used panel building steps: add a new row, add a label,
 * proceed to the next data column, then add a component.
 *
 * The extra value lies in the [append] methods that
 * append gap rows and component rows if necessary and then add
 * the given components. They are built upon the superclass behavior
 * [appendRow] and the set of `add` methods.
 * A set of component appenders allows to add a textual label and
 * associated component in a single step.
 *
 * The choice for localizing text content is left to the application side. For
 * interoperability with Swing, you can load your strings from
 * [java.util.ResourceBundle]s, and extend the [DefaultFormScope] class
 * to provide the various `append` methods that do so. For Compose resources,
 * you can load your strings with `stringResource(Res.string.xyz)`,
 * and extend the [DefaultFormScope] class to provide the various `append`
 * methods that do so.
 *
 * You can configure the build process by setting a leading column,
 * enabling the row grouping and by modifying the gaps between normal
 * lines and between paragraphs. The leading column will be honored
 * if the cursor proceeds to the next row. All appended components
 * start in the specified lead column, except appended separators that
 * span all columns.
 *
 * It is tempting to use the [DefaultFormScope] all the time and
 * to let it add rows automatically. Use a simpler style if it increases
 * the code readability. Explicit row specifications and cell constraints
 * make your layout easier to understand - but harder to maintain.
 * See also the accompanying tutorial sources and the Tips &amp; Tricks
 * that are part of the Forms documentation.
 *
 * Sometimes a form consists of many standardized rows but has a few
 * rows that require a customization. The [DefaultFormScope] can do everything
 * that the superclasses [AbstractFormScope] and [PanelScope] can do;
 * among other things: appending new rows and moving the cursor.
 * Again, ask yourself if the [DefaultFormScope] is the appropriate scope.
 * As a rule of thumb you should have more components than scope commands.
 * There are different ways to add custom rows. Find below example code
 * that presents and compares the pros and cons of three approaches.
 *
 * **Example:**
 * ```kotlin
 * DefaultForm(
 *    modifier = modifier,
 *    padding = Paddings.Dialog,
 *    encodedColumnSpecs = "end:max(40dlu;pref), 3dlu, 80dlu, 7dlu", // 1st major column
 *     + "right:max(40dlu;pref), 3dlu, 80dlu",                       // 2nd major column
 *    encodedRowSpecs = "",                                          // add rows dynamically
 *    bundle = ...
 * ) {
 *     appendSeparator("Flange")
 * 
 *     append("Identifier", { MyIdentifierField(...) })
 *     nextLine()
 * 
 *     append("PTI [kW]",   { MyTextField(...) })
 *     append("Power [kW]", { MyTextField(...) })
 * 
 *     append("s [mm]",     { MyTextField(...) })
 *     nextLine()
 * 
 *     appendSeparator("Diameters")
 * 
 *     append("da [mm]",    { MyTextField(...) })
 *     append("di [mm]",    { MyTextField(...) })
 * 
 *     append("da2 [mm]",   { MyTextField(...) })
 *     append("di2 [mm]",   { MyTextField(...) })
 * 
 *     append("R [mm]",     { MyTextField(...) })
 *     append("D [mm]",     { MyTextField(...) })
 * 
 *     appendSeparator("Criteria")
 * 
 *     append("Location",   { MyLocationComboBox(...) })
 *     append("k-factor",   { MyTextField(...) })
 * 
 *     appendSeparator("Bolts")
 * 
 *     append("Material",   { MyMaterialComboBox(...) })
 *     nextLine()
 * 
 *     append("Numbers",    { MyTextField(...) })
 *     nextLine()
 * 
 *     append("ds [mm]",    { MyTextField(...) })
 * }
 * ```
 *
 * **Custom Row Example:**
 * ````kotlin
 * DefaultForm(
 *    modifier = Modifier.fillMaxSize(),
 *    padding = Paddings.Dialog,
 *    encodedColumnSpecs = "end:pref, 3dlu, default:grow",
 *    encodedRowSpecs = ""
 * ) {
 *     rowGroupingEnabled = true
 *
 *     // In this approach, we add a gap and a custom row.
 *     // The advantage of this approach is, that we can express
 *     // the row spec and comment area cell constraints freely.
 *     // The disadvantage is the misalignment of the leading label.
 *     // Also the row's height may be inconsistent with other rows.
 *     appendSeparator("Single Custom Row")
 *     append("Name", component({ MyTextField(...) }))
 *     appendLineGapRow()
 *     appendRow(RowSpec.decode("top:31dlu")) // Assumes line is 14, gap is 3
 *     nextLine(2)
 *     append("Comment")
 *     component({ MyCommentsScrollPane(...) },
 *         CellConstraints.xywh(col = column, row = row, colSpan = 1, rowSpan = 1, encodedAlignments = "fill, fill"))
 *     nextLine()
 * 
 *     // In this approach, we append a standard row with gap before it.
 *     // The advantage is, that the leading label is aligned well.
 *     // The disadvantage is that the comment area now spans
 *     // multiple cells and is slightly less flexible.
 *     // Also the row's height may be inconsistent with other rows.
 *     appendSeparator("Standard + Custom Row")
 *     append("Name", component({ MyTextField(...) }))
 *     append("Comment")
 *     appendRow(RowSpec.decode("17dlu")) // Assumes line is 14, gap is 3
 *     component({ MyCommentsScrollPane(...) },
 *         CellConstraints.xywh(col = column, row = row, colSpan = 1, rowSpan = 2))
 *     nextLine(2)
 * 
 *    // In this approach, we append two standard rows with associated gaps.
 *    // The advantage is, that the leading label is aligned well,
 *    // and the height is consistent with other rows.
 *    // The disadvantage is that the comment area now spans
 *    // multiple cells and is slightly less flexible.
 *     appendSeparator("Two Standard Rows")
 *     append("Name", component({ MyTextField(...) }))
 *     append("Comment")
 *     nextLine()
 *     append("")
 *     nextRow(-2)
 *     component({ MyCommentsScrollPane(...) },
 *         CellConstraints.xywh(col = column, row = row, colSpan = 1, rowSpan = 3))
 * ```
 *
 * TODO: Consider adding a method for appending a component that spans the
 * remaining columns in the current row. Method name candidates are
 * `#appendFullSpan` and `#appendRemaining`.
 * 
 * @see [AbstractFormScope]
 * @see [org.pushingpixels.aurora.layout.FormSpecs]
 * @see [org.pushingpixels.aurora.layout.FormLayout]
 */
public open class DefaultFormScope(
    componentFactory: ComponentFactory,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
) : PanelScope(componentFactory, colSpecs, rowSpecs) {
    /**
     * Holds the row specification that is reused to describe rows
     * that are intended for labels and components.
     */
    public var defaultRowSpec: RowSpec = FormSpecs.PrefRowSpec

    /**
     * Holds the row specification that is reused to describe
     * the constant gaps between component lines.
     */
    public var lineGapSpec: RowSpec = FormSpecs.LineGapRowSpec

    /**
     * Holds the row specification that describes the constant gaps
     * between paragraphs.
     */
    public var paragraphGapSpec: RowSpec = FormSpecs.ParagraphGapRowSpec

    /**
     * Holds the offset of the leading column - often 0 or 1.
     */
    public var leadingColumnOffset: Int = 0

    /**
     * Determines whether new data rows are being grouped or not.
     */
    public var rowGroupingEnabled: Boolean = false

    // Appending Rows ********************************************************
    /**
     * Appends a row with this scope's line gap size.
     * 
     * @see [lineGapSpec]
     * @see [appendRow]
     */
    public fun appendLineGapRow() {
        appendRow(lineGapSpec)
    }

    // Filling Columns ******************************************************
    /**
     * Adds a component to the panel using the default constraints with
     * the given column span. Proceeds to the next data column.
     *
     * @param component the component to append
     * @param columnSpan    the column span used to add
     */
    @Composable
    public fun append(component: ComponentLambda, columnSpan: Int = 1) {
        ensureCursorColumnInGrid()
        ensureHasGapRow(lineGapSpec)
        ensureHasComponentLine()

        component(component, createAdjustedConstraints(columnSpan))
        nextColumn(columnSpan + 1)
    }

    /**
     * Adds two components to the panel; each component will span a single
     * data column. Proceeds to the next data column.
     * 
     * @param c1    the first component to add
     * @param c2    the second component to add
     */
    @Composable
    public fun append(c1: ComponentLambda, c2: ComponentLambda) {
        append(c1)
        append(c2)
    }

    /**
     * Adds three components to the panel; each component will span a single
     * data column. Proceeds to the next data column.
     * 
     * @param c1    the first component to add
     * @param c2    the second component to add
     * @param c3    the third component to add
     */
    @Composable
    public fun append(c1: ComponentLambda, c2: ComponentLambda, c3: ComponentLambda) {
        append(c1)
        append(c2)
        append(c3)
    }

    // Appending Labels with optional components ------------------------------
    /**
     * Adds a text label to the panel and proceeds to the next column.
     * 
     * @param text  the label's text
     * @return the added label
     */
    @Composable
    public fun append(text: String) {
        append(componentFactory.createLabel(text))
    }

    /**
     * Adds a text label and component to the panel; the component will span
     * the specified number of columns. Proceeds to the next data column.
     * 
     * @param text              the label's text
     * @param component         the component to add
     * @param columnSpan        number of columns the component shall span
     */
    @Composable
    public fun append(text: String, component: ComponentLambda, columnSpan: Int = 1) {
        append(text)
        append(component, columnSpan)
    }

    /**
     * Adds a text label and component to the panel; the component will span
     * the specified number columns. Proceeds to the next data column,
     * and goes to the next line if the boolean flag is set.
     *
     * @param text              the label's text
     * @param component         the component to add
     * @param nextLine          true forces a next line
     */
    @Composable
    public fun append(text: String, component: ComponentLambda, nextLine: Boolean) {
        append(text, component)
        if (nextLine) {
            nextLine()
        }
    }

    /**
     * Adds a text label and two components to the panel; the first component
     * will span a single column, the second component will span the specified 
     * number of columns. Proceeds to the next data column.
     * 
     * @param text              the label's text
     * @param component1        the first component to add
     * @param component2        the second component to add
     * @param colSpan           the column span for the second component
     */
    @Composable
    public fun append(text: String, component1: ComponentLambda, component2: ComponentLambda, colSpan: Int = 1) {
        append(text, component1)
        append(component2, colSpan)
    }

    /**
     * Adds a text label and three components to the panel; each component
     * will span a single column. Proceeds to the next data column.
     *
     * @param text  the label's text 
     * @param c1    the first component to add
     * @param c2    the second component to add
     * @param c3    the third component to add
     */
    @Composable
    public fun append(text: String, c1: ComponentLambda, c2: ComponentLambda, c3: ComponentLambda) {
        append(text, c1, c2)
        append(c3)
    }

    /**
     * Adds a text label and four components to the panel; each component
     * will span a single column. Proceeds to the next data column.
     *
     * @param text  the label's text
     * @param c1    the first component to add
     * @param c2    the second component to add
     * @param c3    the third component to add
     * @param c4    the fourth component to add
     */
    @Composable
    public fun append(
        text: String,
        c1: ComponentLambda,
        c2: ComponentLambda,
        c3: ComponentLambda,
        c4: ComponentLambda
    ) {
        append(text, c1, c2, c3)
        append(c4)
    }

    // Adding Titles ----------------------------------------------------------
    /**
     * Adds a title label to the panel and proceeds to the next column.
     * 
     * @param text  the label's text
     */
    @Composable
    public fun appendTitle(text: String) {
        append(componentFactory.createTitle(text))
    }

    // Appending Separators ---------------------------------------------------
    /**
     * Adds a separator with the given text that spans all columns.
     * 
     * @param text      the separator title text
     */
    @Composable
    public fun appendSeparator(text: String = "") {
        ensureCursorColumnInGrid()
        ensureHasGapRow(paragraphGapSpec)
        ensureHasComponentLine()

        column = 1
        val columnSpan = this.columnCount
        this.columnSpan = this.columnCount
        separator(text)
        this.columnSpan = 1
        nextColumn(columnSpan)
    }

    // Adding Rows **********************************************************
    /**
     * Ensures that the cursor is in the grid. In case it's beyond the
     * form's end side, the cursor is moved to the leading column
     * of the next line.
     */
    private fun ensureCursorColumnInGrid() {
        if (column > columnCount) {
            nextLine()
        }
    }

    /**
     * Ensures that we have a gap row before the next component row.
     * Checks if the current row is the given `RowSpec`
     * and appends this row spec if necessary.
     * 
     * @param gapRowSpec  the row specification to check for
     */
    private fun ensureHasGapRow(gapRowSpec: RowSpec) {
        if (row == 1 || row <= rowCount) {
            return
        }

        if (row <= rowCount) {
            val rowSpec: RowSpec = getCursorRowSpec()
            if (rowSpec === gapRowSpec) {
                return
            }
        }
        appendRow(gapRowSpec)
        nextLine()
    }

    /**
     * Ensures that the form has a component row. Adds a component row
     * if the cursor is beyond the form's bottom.
     */
    private fun ensureHasComponentLine() {
        if (row <= rowCount) {
            return
        }
        appendRow(defaultRowSpec)
        if (rowGroupingEnabled) {
            addGroupedRow(row)
        }
    }

    /**
     * Looks up and returns the row specification of the current row.
     * 
     * @return the row specification of the current row
     */
    private fun getCursorRowSpec(): RowSpec {
        return getRowSpec(row)
    }


    @Composable
    public override fun build(modifier: Modifier) {
        // TODO: is there a more elegant way to pass cell constraints down to the measure policy?
        val constraintsMapping = componentLambdas.map { it.second }
        FormLayout(
            modifier = modifier,
            colSpecs = this.colSpecs,
            rowSpecs = this.rowSpecs,
            rowGroupIndices = this.rowGroupIndices,
            debugConfiguration = this.debugConfiguration,
            constraintsMapping = constraintsMapping,
            content = {
                for ((componentLambda, _) in componentLambdas) {
                    componentLambda.invoke(this)
                }
            }
        )
    }
}

@Composable
public fun DefaultForm(
    modifier: Modifier,
    padding: PaddingValues,
    encodedColumnSpecs: String,
    encodedRowSpecs: String,
    block: @Composable DefaultFormScope.() -> Unit) {

    require(LocalFormLayoutInitialized.current) {
        "Initialize the FormLayout parameters via `FormCortex` first"
    }

    val scope = DefaultFormScope(
        componentFactory = LocalComponentFactory.current,
        colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs),
        rowSpecs = RowSpec.decodeSpecs(encodedRowSpecs),
    )
    scope.block()
    scope.build(modifier.padding(padding))
}

@Composable
public fun DefaultForm(
    modifier: Modifier,
    padding: PaddingValues,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    block: @Composable DefaultFormScope.() -> Unit) {

    require(LocalFormLayoutInitialized.current) {
        "Initialize the FormLayout parameters via `FormCortex` first"
    }

    val scope = DefaultFormScope(
        componentFactory = LocalComponentFactory.current,
        colSpecs = colSpecs,
        rowSpecs = rowSpecs,
    )
    scope.block()
    scope.build(modifier.padding(padding))
}

