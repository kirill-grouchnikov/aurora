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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.resolveDefaults
import org.pushingpixels.aurora.layout.ColumnSpec
import org.pushingpixels.aurora.layout.ComponentLambda
import org.pushingpixels.aurora.layout.FormLayout
import org.pushingpixels.aurora.layout.FormSpecs
import org.pushingpixels.aurora.layout.FormsSetup
import org.pushingpixels.aurora.layout.LocalResolvedTextStyle
import org.pushingpixels.aurora.layout.LocalTextMeasurer
import org.pushingpixels.aurora.layout.LocalTextStyle
import org.pushingpixels.aurora.layout.RowSpec
import org.pushingpixels.aurora.layout.Sizes
import org.pushingpixels.aurora.layout.factories.ComponentFactory
import java.util.*

/**
 * Provides a means to build form-oriented panels quickly and consistently
 * using the [org.pushingpixels.aurora.layout.FormLayout]. This builder
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
 * This builder can map resource keys to internationalized (i15d) texts
 * when creating text labels, titles and titled separators. Therefore
 * you must specify a [ResourceBundle] in the constructor.
 *
 * You can configure the build process by setting a leading column,
 * enabling the row grouping and by modifying the gaps between normal
 * lines and between paragraphs. The leading column will be honored
 * if the cursor proceeds to the next row. All appended components
 * start in the specified lead column, except appended separators that
 * span all columns.
 *
 * It is tempting to use the [DefaultFormBuilder] all the time and
 * to let it add rows automatically. Use a simpler style if it increases
 * the code readability. Explicit row specifications and cell constraints
 * make your layout easier to understand - but harder to maintain.
 * See also the accompanying tutorial sources and the Tips &amp; Tricks
 * that are part of the Forms documentation.
 *
 * Sometimes a form consists of many standardized rows but has a few
 * rows that require a customization. The DefaultFormBuilder can do everything
 * that the superclasses [AbstractFormBuilder] and [PanelBuilder] can do;
 * among other things: appending new rows and moving the cursor.
 * Again, ask yourself if the [DefaultFormBuilder] is the appropriate builder.
 * As a rule of thumb you should have more components than builder commands.
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
 *     append("Identifier", { builderModifier -> MyIdentifierField(...) })
 *     nextLine()
 * 
 *     append("PTI [kW]",   { builderModifier -> MyTextField(...) })
 *     append("Power [kW]", { builderModifier -> MyTextField(...) })
 * 
 *     append("s [mm]",     { builderModifier -> MyTextField(...) })
 *     nextLine()
 * 
 *     appendSeparator("Diameters")
 * 
 *     append("da [mm]",    { builderModifier -> MyTextField(...) })
 *     append("di [mm]",    { builderModifier -> MyTextField(...) })
 * 
 *     append("da2 [mm]",   { builderModifier -> MyTextField(...) })
 *     append("di2 [mm]",   { builderModifier -> MyTextField(...) })
 * 
 *     append("R [mm]",     { builderModifier -> MyTextField(...) })
 *     append("D [mm]",     { builderModifier -> MyTextField(...) })
 * 
 *     appendSeparator("Criteria")
 * 
 *     append("Location",   { builderModifier -> MyLocationComboBox(...) })
 *     append("k-factor",   { builderModifier -> MyTextField(...) })
 * 
 *     appendSeparator("Bolts")
 * 
 *     append("Material",   { builderModifier -> MyMaterialComboBox(...) })
 *     nextLine()
 * 
 *     append("Numbers",    { builderModifier -> MyTextField(...) })
 *     nextLine()
 * 
 *     append("ds [mm]",    { builderModifier -> MyTextField(...) })
 * }
 * ```
 *
 * **Custom Row Example:**
 * <pre>
 * public JComponent buildPanel() {
 * initComponents();
 * 
 * FormLayout layout = new FormLayout(
 * "right:pref, 3dlu, default:grow",
 * "");
 * DefaultFormBuilder builder = new DefaultFormBuilder(layout)
 * .border(Borders.DIALOG)
 * .rowGroupingEnabled(true);
 * 
 * // In this approach, we add a gap and a custom row.
 * // The advantage of this approach is, that we can express
 * // the row spec and comment area cell constraints freely.
 * // The disadvantage is the misalignment of the leading label.
 * // Also the row's height may be inconsistent with other rows.
 *     appendSeparator("Single Custom Row");
 *     append("Name", name1Field);
 *     appendLineGapRow();
 *     appendRow(RowSpec.decode("top:31dlu")); // Assumes line is 14, gap is 3
 *     nextLine(2);
 *     append("Comment");
 *     add(new JScrollPane(comment1Area),
 * CC.xy(builder.getColumn(), builder.getRow(), "fill, fill"));
 *     nextLine();
 * 
 * // In this approach, we append a standard row with gap before it.
 * // The advantage is, that the leading label is aligned well.
 * // The disadvantage is that the comment area now spans
 * // multiple cells and is slightly less flexible.
 * // Also the row's height may be inconsistent with other rows.
 *     appendSeparator("Standard + Custom Row");
 *     append("Name", name2Field);
 *     append("Comment");
 *     appendRow(RowSpec.decode("17dlu")); // Assumes line is 14, gap is 3
 *     add(new JScrollPane(comment2Area),
 * CC.xywh(builder.getColumn(), builder.getRow(), 1, 2));
 *     nextLine(2);
 * 
 * // In this approach, we append two standard rows with associated gaps.
 * // The advantage is, that the leading label is aligned well,
 * // and the height is consistent with other rows.
 * // The disadvantage is that the comment area now spans
 * // multiple cells and is slightly less flexible.
 *     appendSeparator("Two Standard Rows");
 *     append("Name", name3Field);
 *     append("Comment");
 *     nextLine();
 *     append("");
 *     nextRow(-2);
 *     add(new JScrollPane(comment3Area),
 * CC.xywh(builder.getColumn(), builder.getRow(), 1, 3));
 * 
 * return     build();
 * }
</pre> * 
 *
 * TODO: Consider adding a method for appending a component that spans the
 * remaining columns in the current row. Method name candidates are
 * `#appendFullSpan` and `#appendRemaining`.
 * 
 * @see [AbstractFormBuilder]
 * @see [org.pushingpixels.aurora.layout.FormSpecs]
 * @see [org.pushingpixels.aurora.layout.FormLayout]
 */
public class DefaultFormBuilder(
    componentFactory: ComponentFactory,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    bundle: ResourceBundle
) : I15dPanelBuilder(componentFactory, colSpecs, rowSpecs, bundle) {
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
     * Appends a row with this builder's line gap size.
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

    // Appending internationalized labels with optional components ------------
    /**
     * Adds an internationalized (i15d) text label to the panel using
     * the given resource key and proceeds to the next column.
     * 
     * @param resourceKey      the resource key for the label's text
     */
    public fun appendI15d(resourceKey: String) {
        append(getResourceString(resourceKey))
    }

    /**
     * Adds an internationalized (i15d) text label and component
     * to the panel; then proceeds to the next data column
     * and adds a component with the given column span.
     *
     * @param resourceKey  the resource key for the text to add
     * @param component    the component to add
     * @param columnSpan   number of columns the component shall span
     */
    public fun appendI15d(resourceKey: String, component: ComponentLambda, columnSpan: Int = 1) {
        append(getResourceString(resourceKey), component, columnSpan)
    }

    /**
     * Adds an internationalized (i15d) text label and component
     * to the panel. Then proceeds to the next data column.
     * Goes to the next line if the boolean flag is set.
     * 
     * @param resourceKey  the resource key for the text to add
     * @param component    the component to add
     * @param nextLine     true forces a next line
     */
    public fun appendI15d(resourceKey: String, component: ComponentLambda, nextLine: Boolean) {
        append(getResourceString(resourceKey), component, nextLine)
    }

    /**
     * Adds an internationalized (i15d) text label and two components
     * to the panel; each component will span a single column.
     * Proceeds to the next data column.
     *
     * @param resourceKey  the resource key for the text to add
     * @param c1    the first component to add
     * @param c2    the second component to add
     */
    public fun appendI15d(resourceKey: String, c1: ComponentLambda, c2: ComponentLambda) {
        append(getResourceString(resourceKey), c1, c2)
    }

    /**
     * Adds an internationalized (i15d) text label and two components
     * to the panel; each component will span a single column.
     * Proceeds to the next data column.
     * 
     * @param resourceKey  the resource key for the text to add
     * @param c1      the first component to add
     * @param c2      the second component to add
     * @param colSpan the column span for the second component
     */
    public fun appendI15d(resourceKey: String, c1: ComponentLambda, c2: ComponentLambda, colSpan: Int) {
        append(getResourceString(resourceKey), c1, c2, colSpan)
    }

    /**
     * Adds an internationalized (i15d) text label and three components
     * to the panel; each component will span a single column.
     * Proceeds to the next data column.
     *
     * @param resourceKey  the resource key for the text to add
     * @param c1    the first component to add
     * @param c2    the second component to add
     * @param c3    the third component to add
     */
    public fun appendI15d(resourceKey: String, c1: ComponentLambda, c2: ComponentLambda, c3: ComponentLambda) {
        append(getResourceString(resourceKey), c1, c2, c3)
    }

    /**
     * Adds an internationalized (i15d) text label and four components
     * to the panel; each component will span a single column.
     * Proceeds to the next data column.
     *
     * @param resourceKey  the resource key for the text to add
     * @param c1    the first component to add
     * @param c2    the second component to add
     * @param c3    the third component to add
     * @param c4    the third component to add
     */
    public fun appendI15d(resourceKey: String, c1: ComponentLambda, c2: ComponentLambda, c3: ComponentLambda, c4: ComponentLambda) {
        append(getResourceString(resourceKey), c1, c2, c3, c4)
    }

    // Adding Titles ----------------------------------------------------------
    /**
     * Adds a title label to the panel and proceeds to the next column.
     * 
     * @param text  the label's text
     */
    public fun appendTitle(text: String) {
        append(componentFactory.createTitle(text))
    }

    /**
     * Adds an internationalized title label to the panel and
     * proceeds to the next column.
     * 
     * @param resourceKey   the resource key for the title's text
     */
    public fun appendI15dTitle(resourceKey: String) {
        appendTitle(getResourceString(resourceKey))
    }

    // Appending Separators ---------------------------------------------------
    /**
     * Adds a separator with the given text that spans all columns.
     * 
     * @param text      the separator title text
     */
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

    /**
     * Appends an internationalized titled separator for
     * the given resource key that spans all columns.
     * 
     * @param resourceKey   the resource key for the separator title's text
     */
    public fun appendI15dSeparator(resourceKey: String) {
        return appendSeparator(getResourceString(resourceKey))
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
        FormLayout(
            modifier = modifier,
            colSpecs = this.colSpecs,
            rowSpecs = this.rowSpecs,
            rowGroupIndices = this.rowGroupIndices,
            content = {
                for ((componentLambda, componentBuilderModifier) in componentLambdas) {
                    componentLambda.invoke(this, Modifier.cc(componentBuilderModifier))
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
    bundle: ResourceBundle,
    block: @Composable DefaultFormBuilder.() -> Unit) {

    require (FormsSetup.ComponentFactoryDefault != null) {
        "Configure `FormsSetup.ComponentFactoryDefault` with a non-null component factory before creating this builder"
    }

    val textMeasurer = rememberTextMeasurer()
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    Sizes.textStyle = resolvedTextStyle
    Sizes.textMeasurer = textMeasurer
    Sizes.density = LocalDensity.current

    CompositionLocalProvider(
        LocalTextMeasurer provides textMeasurer,
        LocalResolvedTextStyle provides resolvedTextStyle,
    ) {
        val builder = DefaultFormBuilder(
            componentFactory = FormsSetup.ComponentFactoryDefault!!,
            colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs),
            rowSpecs = RowSpec.decodeSpecs(encodedRowSpecs),
            bundle = bundle
        )
        builder.block()
        builder.build(modifier.padding(padding))
    }
}

@Composable
public fun DefaultForm(
    modifier: Modifier,
    padding: PaddingValues,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    bundle: ResourceBundle,
    block: @Composable DefaultFormBuilder.() -> Unit) {

    require (FormsSetup.ComponentFactoryDefault != null) {
        "Configure `FormsSetup.ComponentFactoryDefault` with a non-null component factory before creating this builder"
    }

    val textMeasurer = rememberTextMeasurer()
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    Sizes.textStyle = resolvedTextStyle
    Sizes.textMeasurer = textMeasurer
    Sizes.density = LocalDensity.current

    CompositionLocalProvider(
        LocalTextMeasurer provides textMeasurer,
        LocalResolvedTextStyle provides resolvedTextStyle,
    ) {
        val builder = DefaultFormBuilder(
            componentFactory = FormsSetup.ComponentFactoryDefault!!,
            colSpecs = colSpecs,
            rowSpecs = rowSpecs,
            bundle = bundle
        )
        builder.block()
        builder.build(modifier.padding(padding))
    }
}

