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

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.layout.CellConstraints.Alignment

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

public typealias ComponentLambda = @Composable FormLayoutScope.() -> Unit

/**
 * FormLayout is a powerful, flexible and precise general purpose
 * layout manager. It aligns components vertically and horizontally in
 * a dynamic rectangular grid of cells, with each component occupying one or
 * more cells.
 * A [whitepaper](../../../../../whitepaper.pdf)
 * about the FormLayout ships with the product documentation and is available
 * [online](http://www.jgoodies.com/articles/forms.pdf).
 *
 * To use FormLayout you first define the grid by specifying the
 * columns and rows. In a second step you add components to the grid. You can
 * specify columns and rows via human-readable String descriptions or via
 * arrays of [ColumnSpec] and [RowSpec] instances.
 *
 * Each component managed by a [FormLayout] is associated with an instance of
 * [CellConstraints]. The constraints object specifies where a component
 * should be located on the form's grid and how the component should be
 * positioned. In addition to its constraints object the
 * [FormLayout] also considers each component's minimum and
 * preferred sizes in order to determine a component's size.
 *
 * [FormLayout] has been designed to work with non-visual DSLs that help you
 * specify the layout and fill the grid. For example, the
 * [org.pushingpixels.aurora.layout.builder.ButtonBar] assists you in building button
 * bars; it creates a standardized [FormLayout] and provides a minimal API that
 * specializes in adding buttons. Other APIs can create
 * frequently used panel designs, for example a form that consists of rows of
 * label-component pairs.
 *
 * [FormLayout] has been prepared to work with different types of sizes as
 * defined by the [Size] interface.
 *
 * **Example 1** (Plain [FormLayout]):<br></br>
 * The following example creates a panel with 3 data columns and 3 data rows;
 * the columns and rows are specified before components are added
 * to the form.
 * ```kotlin
 * FormLayout(
 *    modifier = Modifier.fillMaxSize().padding(Paddings.Dlu9),
 *    encodedColumnSpecs = "end:pref, 6dlu, 50dlu, 4dlu, default",
 *    encodedRowSpecs = "p, 3dlu, p, 3dlu, p"
 * ) {
 *    MyLabel(modifier = Modifier.xy(1, 1), ...)
 *    MyTextField(modifier = modifier.xywh(3, 1, 3, 1), ...)
 *    MyLabel(modifier = Modifier.xy(1, 3), ...)
 *    MyTextField(modifier = modifier.xy(3, 3), ...)
 *    MyLabel(modifier = Modifier.xy(1, 5), ...)
 *    MyTextField(modifier = modifier.xy(3, 5), ...)
 *    MyButton(modifier = modifier.xy(5, 5), ...)
 * }
 * ```
 *
 * **Example 2** (Using [org.pushingpixels.aurora.layout.builder.Panel]):<br></br>
 * This example creates the same panel as above using the
 * [org.pushingpixels.aurora.layout.builder.Panel] to add components to the form.
 * ```kotlin
 * Panel(
 *    modifier = Modifier.fillMaxSize(),
 *    padding = Paddings.Dlu9,
 *    encodedColumnSpecs = "end:pref, 6dlu, 50dlu, 4dlu, default",
 *    encodedRowSpecs = "p, 3dlu, p, 3dlu, p",
 * ) {
 *     label("Title:",                                      CellConstraints.xy  (1, 1))
 *     component({ MyTextField(...) },   CellConstraints.xywh(3, 1, 3, 1))
 *     label("Price:",                                      CellConstraints.xy  (1, 3))
 *     component({ MyTextField(...) },   CellConstraints.xy  (3, 3))
 *     label("Author:",                                     CellConstraints.xy  (1, 5))
 *     component({ MyTextField(...) },   CellConstraints.xy  (3, 5))
 *     component({ MyButton(...) },      CellConstraints.xy  (5, 5))
 * }
 * ```
 *
 * **Example 3** (Using [org.pushingpixels.aurora.layout.builder.DefaultForm]):<br></br>
 * This example utilizes the [org.pushingpixels.aurora.layout.builder.DefaultForm] that
 * ships with the source distribution.
 * ```kotlin
 * DefaultForm(
 *    modifier = Modifier.fillMaxSize(),
 *    padding = Paddings.Dlu9,
 *    encodedColumnSpecs = "end:pref, @lcgap, 60dlu, @rgap, max(40dlu;default)",
 *    encodedRowSpecs = ""
 * ) {
 *     append(resourceString(Res.string.title), component({ MyTextField(...) }, 3)
 *     append(resourceString(Res.string.price), component({ MyTextField(...) })
 *     nextLine()
 *     append(resourceString(Res.string.author), component({ MyTextField(...) })
 *     append({ MyButton(...) })
 * }
 * ```
 *
 * @see [ColumnSpec]
 * @see [RowSpec]
 * @see [CellConstraints]
 * @see [FormSpecs]
 * @see [Size]
 * @see [Sizes]
 */
@Composable
public fun FormLayout(
    modifier: Modifier,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    debugConfiguration: FormLayoutDebugConfiguration? = null,
    content: @Composable FormLayoutScope.() -> Unit) {

    FormLayout(
        modifier = modifier,
        colSpecs = colSpecs,
        rowSpecs = rowSpecs,
        colGroupIndices = colGroupIndices,
        rowGroupIndices = rowGroupIndices,
        debugConfiguration = debugConfiguration,
        constraintsMapping = null,
        content = content)
}

@Composable
internal fun FormLayout(
    modifier: Modifier,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    debugConfiguration: FormLayoutDebugConfiguration? = null,
    constraintsMapping: List<CellConstraints>?,
    content: @Composable FormLayoutScope.() -> Unit) {

    val measurePolicy = FormLayoutMeasurePolicy(
        LocalTextMeasurer.current, LocalTextStyle.current,
        colSpecs, rowSpecs, colGroupIndices, rowGroupIndices,
        constraintsMapping)
    val formModifier = if (debugConfiguration != null) modifier.debugOverlay(measurePolicy, debugConfiguration) else modifier
    Layout(
        content = { FormLayoutScopeImpl(colSpecs.size, rowSpecs.size).content() },
        measurePolicy = measurePolicy,
        modifier = formModifier)
}

@Composable
public fun FormLayout(
    modifier: Modifier,
    encodedColumnSpecs: String,
    layoutMap: LayoutMap,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    debugConfiguration: FormLayoutDebugConfiguration? = null,
    content: @Composable FormLayoutScope.() -> Unit) {

    FormLayout(
        modifier = modifier,
        encodedColumnSpecs = encodedColumnSpecs,
        layoutMap = layoutMap,
        colGroupIndices = colGroupIndices,
        rowGroupIndices = rowGroupIndices,
        debugConfiguration = debugConfiguration,
        constraintsMapping = null,
        content = content
    )
}

@Composable
internal fun FormLayout(
    modifier: Modifier,
    encodedColumnSpecs: String,
    layoutMap: LayoutMap,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    debugConfiguration: FormLayoutDebugConfiguration? = null,
    constraintsMapping: List<CellConstraints>?,
    content: @Composable FormLayoutScope.() -> Unit) {

    val colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap)
    val measurePolicy = FormLayoutMeasurePolicy(
        textMeasurer = LocalTextMeasurer.current,
        textStyle = LocalTextStyle.current,
        colSpecs = colSpecs,
        rowSpecs = listOf(),
        colGroupIndices = colGroupIndices,
        rowGroupIndices = rowGroupIndices,
        constraintsMapping = constraintsMapping
    )
    val formModifier = if (debugConfiguration != null) modifier.debugOverlay(measurePolicy, debugConfiguration) else modifier
    Layout(
        content = { FormLayoutScopeImpl(colSpecs.size, 0).content() },
        measurePolicy = measurePolicy,
        modifier = formModifier
    )
}

@Composable
public fun FormLayout(
    modifier: Modifier,
    encodedColumnSpecs: String,
    encodedRowSpecs: String,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    debugConfiguration: FormLayoutDebugConfiguration? = null,
    content: @Composable FormLayoutScope.() -> Unit) {

    FormLayout(
        modifier = modifier,
        encodedColumnSpecs = encodedColumnSpecs,
        encodedRowSpecs = encodedRowSpecs,
        colGroupIndices = colGroupIndices,
        rowGroupIndices = rowGroupIndices,
        debugConfiguration = debugConfiguration,
        constraintsMapping = null,
        content = content
    )
}

@Composable
internal fun FormLayout(
    modifier: Modifier,
    encodedColumnSpecs: String,
    encodedRowSpecs: String,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    debugConfiguration: FormLayoutDebugConfiguration? = null,
    constraintsMapping: List<CellConstraints>? = null,
    content: @Composable FormLayoutScope.() -> Unit) {

    val layoutMap: LayoutMap = LayoutMap.getRoot()
    val colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap)
    val rowSpecs = RowSpec.decodeSpecs(encodedRowSpecs, layoutMap)

    val measurePolicy = FormLayoutMeasurePolicy(
        textMeasurer = LocalTextMeasurer.current,
        textStyle = LocalTextStyle.current,
        colSpecs = colSpecs,
        rowSpecs = rowSpecs,
        colGroupIndices = colGroupIndices,
        rowGroupIndices = rowGroupIndices,
        constraintsMapping = constraintsMapping
    )
    val formModifier = if (debugConfiguration != null) modifier.debugOverlay(measurePolicy, debugConfiguration) else modifier
    Layout(
        content = { FormLayoutScopeImpl(colSpecs.size, rowSpecs.size).content() },
        measurePolicy = measurePolicy,
        modifier = formModifier
    )
}

/** A FormLayoutScope provides a scope for the children of [FormLayout]. */
@LayoutScopeMarker
@Immutable
public interface FormLayoutScope {
    public fun Modifier.xy(col: Int, row: Int,
        colAlign: Alignment = Alignment.Default,
        rowAlign: Alignment = Alignment.Default): Modifier

    public fun Modifier.xyw(col: Int, row: Int, colSpan: Int = 1,
        colAlign: Alignment = Alignment.Default,
        rowAlign: Alignment = Alignment.Default): Modifier

    public fun Modifier.xywh(col: Int, row: Int, colSpan: Int = 1, rowSpan: Int = 1,
        colAlign: Alignment = Alignment.Default,
        rowAlign: Alignment = Alignment.Default): Modifier

    public fun Modifier.rc(row: Int, col: Int,
        rowAlign: Alignment = Alignment.Default,
        colAlign: Alignment = Alignment.Default): Modifier

    public fun Modifier.rcw(row: Int, col: Int, colSpan: Int = 1,
        rowAlign: Alignment = Alignment.Default,
        colAlign: Alignment = Alignment.Default): Modifier

    public fun Modifier.rchw(row: Int, col: Int, rowSpan: Int = 1, colSpan: Int = 1,
        rowAlign: Alignment = Alignment.Default,
        colAlign: Alignment = Alignment.Default): Modifier

    public fun Modifier.cc(cellConstraints: CellConstraints): Modifier
}

private class FormLayoutScopeImpl(private val colCount: Int, private val rowCount: Int) : FormLayoutScope {
    override fun Modifier.xywh(
        col: Int,
        row: Int,
        colSpan: Int,
        rowSpan: Int,
        colAlign: Alignment,
        rowAlign: Alignment
    ): Modifier =
        this.then(
            this.then(
                FormLayoutChildDataElement(
                    cellConstraints = CellConstraints.xywh(col, row, colSpan, rowSpan, colAlign, rowAlign),
                    colCount = colCount,
                    rowCount = rowCount,
                    inspectorInfo = debugInspectorInfo { name = "xywh" },
                )
            )
        )

    override fun Modifier.xy(
        col: Int,
        row: Int,
        colAlign: Alignment,
        rowAlign: Alignment
    ): Modifier =
        this.then(
            this.then(
                FormLayoutChildDataElement(
                    cellConstraints = CellConstraints.xywh(col, row, 1, 1, colAlign, rowAlign),
                    colCount = colCount,
                    rowCount = rowCount,
                    inspectorInfo = debugInspectorInfo { name = "xy" },
                )
            )
        )

    override fun Modifier.xyw(
        col: Int,
        row: Int,
        colSpan: Int,
        colAlign: Alignment,
        rowAlign: Alignment
    ): Modifier =
        this.then(
            this.then(
                FormLayoutChildDataElement(
                    cellConstraints = CellConstraints.xywh(col, row, colSpan, 1, colAlign, rowAlign),
                    colCount = colCount,
                    rowCount = rowCount,
                    inspectorInfo = debugInspectorInfo { name = "xyw" },
                )
            )
        )

    override fun Modifier.rc(
        row: Int,
        col: Int,
        rowAlign: Alignment,
        colAlign: Alignment
    ): Modifier =
        this.then(
            FormLayoutChildDataElement(
                cellConstraints = CellConstraints.rchw(row, col, 1, 1, rowAlign, colAlign),
                colCount = colCount,
                rowCount = rowCount,
                inspectorInfo = debugInspectorInfo { name = "rc" },
            )
        )

    override fun Modifier.rcw(
        row: Int,
        col: Int,
        colSpan: Int,
        rowAlign: Alignment,
        colAlign: Alignment
    ): Modifier =
        this.then(
            FormLayoutChildDataElement(
                cellConstraints = CellConstraints.rchw(row, col, 1, colSpan, rowAlign, colAlign),
                colCount = colCount,
                rowCount = rowCount,
                inspectorInfo = debugInspectorInfo { name = "rcw" },
            )
        )

    override fun Modifier.rchw(
        row: Int,
        col: Int,
        rowSpan: Int,
        colSpan: Int,
        rowAlign: Alignment,
        colAlign: Alignment
    ): Modifier =
        this.then(
            FormLayoutChildDataElement(
                cellConstraints = CellConstraints.rchw(row, col, rowSpan, colSpan, rowAlign, colAlign),
                colCount = colCount,
                rowCount = rowCount,
                inspectorInfo = debugInspectorInfo { name = "rchw" },
            )
        )

    override fun Modifier.cc(cellConstraints: CellConstraints): Modifier =
        this.then(
            FormLayoutChildDataElement(
                cellConstraints = cellConstraints.copy(),
                colCount = colCount,
                rowCount = rowCount,
                inspectorInfo = debugInspectorInfo { name = "cc" },
            )
        )
}

private val Measurable.formLayoutChildDataNode: FormLayoutChildDataNode?
    get() = parentData as? FormLayoutChildDataNode

private class FormLayoutChildDataElement(
    val cellConstraints: CellConstraints,
    val colCount: Int,
    val rowCount: Int,
    val inspectorInfo: InspectorInfo.() -> Unit,
) : ModifierNodeElement<FormLayoutChildDataNode>() {
    override fun create(): FormLayoutChildDataNode {
        cellConstraints.ensureValidGridBounds(colCount, rowCount)
        return FormLayoutChildDataNode(cellConstraints)
    }

    override fun update(node: FormLayoutChildDataNode) {
        node.cellConstraints = cellConstraints
    }

    override fun InspectorInfo.inspectableProperties() {
        inspectorInfo()
    }

    override fun hashCode(): Int {
        return cellConstraints.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? FormLayoutChildDataElement ?: return false
        return cellConstraints == otherModifier.cellConstraints
    }
}

internal class FormLayoutChildDataNode(internal var cellConstraints: CellConstraints) :
    ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?) = this@FormLayoutChildDataNode
}

public data class FormLayoutDebugConfiguration(
    public val gridColor: Color = Color.Red,
    public val paintUnderContent: Boolean = false,
    public val paintDiagonals: Boolean = false)

private class DebugDrawNode(
    var measurePolicy: FormLayoutMeasurePolicy,
    var debugConfiguration: FormLayoutDebugConfiguration
) : DrawModifierNode, ParentDataModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        if (!debugConfiguration.paintUnderContent) {
            // Paint content first, then the grid
            drawContent()
        }

        val gridXs = measurePolicy.gridXs
        val gridYs = measurePolicy.gridYs
        val ltr = (layoutDirection == LayoutDirection.Ltr)

        if ((gridXs != null) && (gridYs != null)) {
            val gridLeft = gridXs[0]
            val gridTop = gridYs[0]
            val gridWidth = gridXs[gridXs.size - 1] - gridLeft
            val gridHeight = gridYs[gridYs.size - 1] - gridTop

            // Column bounds
            for ((colIndex, col) in gridXs.withIndex()) {
                val firstOrLast = (colIndex == 0) || (colIndex == gridXs.size - 1)
                val start = if (firstOrLast) 0 else gridTop
                val stop = if (firstOrLast) gridHeight else gridTop + gridHeight

                // Account for LTR vs RTL for the current vertical grid line
                val colX = if (ltr) col else size.width - col

                drawLine(
                    color = debugConfiguration.gridColor,
                    start = Offset(colX.toFloat(), start.toFloat()),
                    end = Offset(colX.toFloat(), stop.toFloat()),
                    strokeWidth = 1.0f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(3.0f, 2.0f),
                        phase = 0f
                    )
                )
            }

            // Row bounds
            for ((rowIndex, row) in gridYs.withIndex()) {
                val firstOrLast = (rowIndex == 0) || (rowIndex == gridYs.size - 1)
                val start = if (firstOrLast) 0 else gridLeft
                val stop = if (firstOrLast) gridWidth else (gridLeft + gridWidth)

                drawLine(
                    color = debugConfiguration.gridColor,
                    start = Offset(start.toFloat(), row.toFloat()),
                    end = Offset(stop.toFloat(), row.toFloat()),
                    strokeWidth = 1.0f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(3.0f, 2.0f),
                        phase = 0f
                    )
                )
            }

            if (debugConfiguration.paintDiagonals) {
                drawLine(
                    color = debugConfiguration.gridColor,
                    start = Offset(gridLeft.toFloat(), gridTop.toFloat()),
                    end = Offset((gridLeft + gridWidth).toFloat(), (gridTop + gridHeight).toFloat()),
                    strokeWidth = 1.0f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(3.0f, 2.0f),
                        phase = 0f
                    )
                )

                drawLine(
                    color = debugConfiguration.gridColor,
                    start = Offset((gridLeft + gridWidth).toFloat(), gridTop.toFloat()),
                    end = Offset(gridLeft.toFloat(), (gridTop + gridHeight).toFloat()),
                    strokeWidth = 1.0f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(3.0f, 2.0f),
                        phase = 0f
                    )
                )
            }
        }

        if (debugConfiguration.paintUnderContent) {
            // Paint grid first, then the content
            drawContent()
        }
    }

    override fun Density.modifyParentData(parentData: Any?) = this@DebugDrawNode
}

private data class DebugDrawElement(
    val measurePolicy: FormLayoutMeasurePolicy,
    val debugConfiguration: FormLayoutDebugConfiguration
) : ModifierNodeElement<DebugDrawNode>() {
    override fun create() = DebugDrawNode(measurePolicy, debugConfiguration)

    override fun update(node: DebugDrawNode) {
        node.measurePolicy = measurePolicy
        node.debugConfiguration = debugConfiguration
    }
}

// TODO: is there a more elegant way to share the grid structure from measure policy to this modifier?
private fun Modifier.debugOverlay(
    measurePolicy: FormLayoutMeasurePolicy,
    debugConfiguration: FormLayoutDebugConfiguration
) = this then DebugDrawElement(measurePolicy, debugConfiguration)
