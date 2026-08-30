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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * A general purpose panel builder that uses the [org.pushingpixels.aurora.layout.FormLayout]
 * to lay out panels. It provides convenience methods to add labels, titles and titled separators.
 *
 * The PanelBuilder is the working horse for layouts when more specialized
 * builders like the [ButtonBarBuilder] or [DefaultFormBuilder]
 * are inappropriate.
 *
 * The Forms tutorial includes several examples that present and compare
 * different style to build with the PanelBuilder: static row numbers
 * vs. row variable, explicit [CellConstraints] vs. builder cursor,
 * static rows vs. dynamically added rows. Also, you may check out the
 * Tips &amp; Tricks section of the Forms HTML documentation.
 *
 * **Example:**<br></br>
 * This example creates a panel with 3 columns and 3 rows.
 * ```kotlin
 * Panel(
 *    modifier = modifier,
 *    padding = Paddings.Dlu4,
 *    encodedColumnSpecs = "pref, @lcgap, 50dlu, @rgap, default",
 *    encodedRowSpecs = "pref, @lg, pref, @lg, pref",
 * ) {
 *     label("Title:",                                      CellConstraints.xy  (1, 1))
 *     component({ builderModifier -> MyTextField(...) },   CellConstraints.xywh(3, 1, 3, 1))
 *     label("Price:",                                      CellConstraints.xy  (1, 3))
 *     component({ builderModifier -> MyTextField(...) },   CellConstraints.xy  (3, 3))
 *     label("Author:",                                     CellConstraints.xy  (1, 5))
 *     component({ builderModifier -> MyTextField(...) },   CellConstraints.xy  (3, 5))
 *     component({ builderModifier -> MyButton(...) },      CellConstraints.xy  (5, 5))
 * }
 * ```
 *
 * @see [org.pushingpixels.aurora.layout.factories.ComponentFactory]
 * @see [DefaultFormBuilder]
 */
public open class PanelBuilder(
    componentFactory: ComponentFactory,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>
) : AbstractFormBuilder(componentFactory) {
    // Instance Creation ******************************************************
    init {
        this.colSpecs.addAll(colSpecs)
        this.rowSpecs.addAll(rowSpecs)
    }

    // Adding Labels **********************************************************
    /**
     * Adds a textual label to the form.
     *
     * @param text   the label's text
     * @param constraints       the label's cell constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun label(text: String, constraints: CellConstraints = currentCellConstraints) {
        componentLambdas.add(Pair(componentFactory.createLabel(text), constraints))
    }

    /**
     * Adds a textual label to the form using the specified constraints.
     *
     * @param text   the label's text
     * @param encodedConstraints  a string representation for the constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun label(text: String, encodedConstraints: String) {
        componentLambdas.add(Pair(componentFactory.createLabel(text), CellConstraints.fromConstraints(encodedConstraints)))
    }

    /**
     * Adds a label and component to the panel using the given cell constraints.
     *
     * @param text                  the label's text
     * @param labelConstraints      the label's cell constraints
     * @param component             the component to add
     * @param componentConstraints  the component's cell constraints
     *
     * @see [ComponentFactory]
     * @see [DefaultFormBuilder]
     */
    @Composable
    public fun label(
        text: String, labelConstraints: CellConstraints,
        component: ComponentLambda, componentConstraints: CellConstraints
    ) {
        label(text, labelConstraints)
        component(component, componentConstraints)
    }

    // Adding Labels for Read-Only Components ---------------------------------

    /**
     * Adds a textual label intended for labeling read-only components to the form.
     *
     * @param text   the label's text
     * @param constraints       the label's cell constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun readOnlyLabel(text: String, constraints: CellConstraints = currentCellConstraints) {
        componentLambdas.add(Pair(componentFactory.createReadOnlyLabel(text), constraints))
    }

    /**
     * Adds a textual label intended for labeling read-only components to the form using the specified constraints.
     *
     * @param text   the label's text
     * @param encodedConstraints  a string representation for the constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun readOnlyLabel(text: String, encodedConstraints: String) {
        componentLambdas.add(Pair(componentFactory.createReadOnlyLabel(text), CellConstraints.fromConstraints(encodedConstraints)))
    }

    /**
     * Adds a textual label intended for labeling read-only components and component to the panel using the given cell constraints.
     *
     * @param text                  the label's text
     * @param labelConstraints      the label's cell constraints
     * @param component             the component to add
     * @param componentConstraints  the component's cell constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun readOnlyLabel(
        text: String, labelConstraints: CellConstraints,
        component: ComponentLambda, componentConstraints: CellConstraints
    ) {
        readOnlyLabel(text, labelConstraints)
        component(component, componentConstraints)
    }

    // Adding Titles ----------------------------------------------------------

    /**
     * Adds a title label to the form.
     *
     * @param text   the title label's text
     * @param constraints       the label's cell constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun title(text: String, constraints: CellConstraints = currentCellConstraints) {
        componentLambdas.add(Pair(componentFactory.createTitle(text), constraints))
    }

    /**
     * Adds a title label to the form using the specified constraints.
     *
     * @param text   the title label's text
     * @param encodedConstraints  a string representation for the constraints
     *
     * @see [ComponentFactory]
     */
    @Composable
    public fun title(text: String, encodedConstraints: String) {
        componentLambdas.add(Pair(componentFactory.createTitle(text), CellConstraints.fromConstraints(encodedConstraints)))
    }

    // Adding Separators ------------------------------------------------------
    /**
     * Adds a titled separator to the form that spans all columns.
     *
     * @param text   the separator label's text
     */
    @Composable
    public fun separator(text: String) {
        return separator(text, this.columnCount)
    }

    /**
     * Adds a titled separator to the form using the specified constraints.
     *
     * @param text   the separator label's text
     * @param constraints  the separator's cell constraints
     */
    @Composable
    public fun separator(text: String, constraints: CellConstraints) {
        component(componentFactory.createSeparator(text, Arrangement.Start), constraints)
    }

    /**
     * Adds a titled separator to the form using the specified constraints.
     *
     * @param text   the separator label's text
     * @param encodedConstraints  a string representation for the constraints
     */
    @Composable
    public fun separator(text: String, encodedConstraints: String) {
        return separator(text, CellConstraints.fromConstraints(encodedConstraints))
    }

    /**
     * Adds a titled separator to the form that spans the specified columns.
     *
     * @param text   the separator label's text
     * @param columnSpan    the number of columns the separator spans
     */
    @Composable
    public fun separator(text: String, columnSpan: Int) {
        return separator(text, createAdjustedConstraints(columnSpan))
    }

    @Composable
    public open fun build(modifier: Modifier) {
        FormLayout(
            modifier = modifier,
            colSpecs = this.colSpecs,
            rowSpecs = this.rowSpecs,
            debugConfiguration = this.debugConfiguration,
            content = {
                for ((componentLambda, componentBuilderModifier) in componentLambdas) {
                    componentLambda.invoke(this, Modifier.cc(componentBuilderModifier))
                }
            }
        )
    }
}

@Composable
public fun Panel(
    modifier: Modifier,
    padding: PaddingValues,
    encodedColumnSpecs: String,
    encodedRowSpecs: String,
    block: @Composable PanelBuilder.() -> Unit) {

    require(LocalFormLayoutInitialized.current) {
        "Initialize the FormLayout parameters via `FormCortex` first"
    }

    val builder = PanelBuilder(
        componentFactory = LocalComponentFactory.current,
        colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs),
        rowSpecs = RowSpec.decodeSpecs(encodedRowSpecs)
    )
    builder.block()
    builder.build(modifier.padding(padding))
}

@Composable
public fun Panel(
    modifier: Modifier,
    padding: PaddingValues,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    block: @Composable PanelBuilder.() -> Unit) {

    require(LocalFormLayoutInitialized.current) {
        "Initialize the FormLayout parameters via `FormCortex` first"
    }

    val builder = PanelBuilder(
        componentFactory = LocalComponentFactory.current,
        colSpecs = colSpecs,
        rowSpecs = rowSpecs
    )
    builder.block()
    builder.build(modifier.padding(padding))
}

