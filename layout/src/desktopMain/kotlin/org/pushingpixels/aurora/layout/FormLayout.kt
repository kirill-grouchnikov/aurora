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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.*
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.layout.CellConstraints.Alignment
import kotlin.math.max
import kotlin.math.roundToInt

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

public typealias ComponentLambda = @Composable FormLayoutScope.(modifier: Modifier) -> Unit

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
 * [FormLayout] has been designed to work with non-visual builders that help you
 * specify the layout and fill the grid. For example, the
 * [org.pushingpixels.aurora.layout.builder.ButtonBarBuilder] assists you in building button
 * bars; it creates a standardized [FormLayout] and provides a minimal API that
 * specializes in adding buttons. Other builders can create
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
 * **Example 2** (Using [org.pushingpixels.aurora.layout.builder.PanelBuilder]):<br></br>
 * This example creates the same panel as above using the
 * [org.pushingpixels.aurora.layout.builder.PanelBuilder] to add components to the form.
 * ```kotlin
 * Panel(
 *    modifier = Modifier.fillMaxSize(),
 *    padding = Paddings.Dlu9,
 *    encodedColumnSpecs = "end:pref, 6dlu, 50dlu, 4dlu, default",
 *    encodedRowSpecs = "p, 3dlu, p, 3dlu, p",
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
 * **Example 3** (Using DefaultFormBuilder):<br></br>
 * This example utilizes the
 * [DefaultFormBuilder] that
 * ships with the source distribution.
 * <pre>
 * FormLayout layout = new FormLayout(
 * "right:pref, 6dlu, 50dlu, 4dlu, default"); // 5 columns; add rows later
 *
 * DefaultFormBuilder builder = new DefaultFormBuilder(layout);
 * builder.append("Label1", new JTextField(), 3);
 * builder.append("Label2", new JTextField());
 * builder.append("Label3", new JTextField());
 * builder.append(new JButton("/u2026"));
 * return builder.getPanel();
</pre> *
 *
 * @see ColumnSpec
 * @see RowSpec
 * @see CellConstraints
 * @see FormSpecs
 * @see Size
 * @see Sizes
 */
@Composable
public fun FormLayout(
    modifier: Modifier,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    content: @Composable FormLayoutScope.() -> Unit) {

    val textMeasurer = rememberTextMeasurer()
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    Sizes.textStyle = resolvedTextStyle
    Sizes.textMeasurer = textMeasurer
    Sizes.density = LocalDensity.current

    CompositionLocalProvider(
        LocalTextMeasurer provides textMeasurer,
        LocalResolvedTextStyle provides resolvedTextStyle,
    ) {
        Layout(
            content = { FormLayoutScopeImpl(colSpecs.size, rowSpecs.size).content() },
            measurePolicy = FormLayoutMeasurePolicy(colSpecs, rowSpecs, colGroupIndices, rowGroupIndices),
            modifier = modifier)
    }
}

@Composable
public fun FormLayout(
    modifier: Modifier,
    encodedColumnSpecs: String,
    layoutMap: LayoutMap,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    content: @Composable FormLayoutScope.() -> Unit) {

    val textMeasurer = rememberTextMeasurer()
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    Sizes.textStyle = resolvedTextStyle
    Sizes.textMeasurer = textMeasurer
    Sizes.density = LocalDensity.current

    CompositionLocalProvider(
        LocalTextMeasurer provides textMeasurer,
        LocalResolvedTextStyle provides resolvedTextStyle,
    ) {
        val colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap)
        Layout(
            content = { FormLayoutScopeImpl(colSpecs.size, 0).content() },
            measurePolicy = FormLayoutMeasurePolicy(
                colSpecs = colSpecs,
                rowSpecs = listOf(),
                colGroupIndices = colGroupIndices,
                rowGroupIndices = rowGroupIndices
            ),
            modifier = modifier
        )
    }
}

@Composable
public fun FormLayout(
    modifier: Modifier,
    encodedColumnSpecs: String,
    encodedRowSpecs: String,
    colGroupIndices: Array<IntArray> = arrayOf(),
    rowGroupIndices: Array<IntArray> = arrayOf(),
    content: @Composable FormLayoutScope.() -> Unit) {

    val textMeasurer = rememberTextMeasurer()
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    Sizes.textStyle = resolvedTextStyle
    Sizes.textMeasurer = textMeasurer
    Sizes.density = LocalDensity.current

    CompositionLocalProvider(
        LocalTextMeasurer provides textMeasurer,
        LocalResolvedTextStyle provides resolvedTextStyle,
    ) {
        val layoutMap: LayoutMap = LayoutMap.getRoot()
        val colSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap)
        val rowSpecs = RowSpec.decodeSpecs(encodedRowSpecs, layoutMap)
        Layout(
            content = { FormLayoutScopeImpl(colSpecs.size, rowSpecs.size).content() },
            measurePolicy = FormLayoutMeasurePolicy(
                colSpecs = colSpecs,
                rowSpecs = rowSpecs,
                colGroupIndices = colGroupIndices,
                rowGroupIndices = rowGroupIndices
            ),
            modifier = modifier
        )
    }
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

private class FormLayoutChildDataNode(var cellConstraints: CellConstraints) :
    ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?) = this@FormLayoutChildDataNode
}

@Composable
internal fun rememberFormLayoutMeasurePolicy(colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>, colGroupIndices: Array<IntArray>, rowGroupIndices: Array<IntArray>): MeasurePolicy =
    remember {
        FormLayoutMeasurePolicy(colSpecs, rowSpecs, colGroupIndices, rowGroupIndices)
    }

private class FormLayoutMeasurePolicy(
    private val colSpecs: List<ColumnSpec>,
    private val rowSpecs: List<RowSpec>,
    private val colGroupIndices: Array<IntArray>,
    private val rowGroupIndices: Array<IntArray>,
) : MeasurePolicy {
    private var colComponents: Array<MutableList<IntrinsicMeasurable>> = Array(colSpecs.size) { arrayListOf() }
    private var rowComponents: Array<MutableList<IntrinsicMeasurable>> = Array(rowSpecs.size) { arrayListOf() }
    private var colComponents2: Array<MutableList<Measurable>> = Array(colSpecs.size) { arrayListOf() }
    private var rowComponents2: Array<MutableList<Measurable>> = Array(rowSpecs.size) { arrayListOf() }
    private val minimumWidthMeasure: Measure = MinimumWidthMeasure()
    private val minimumHeightMeasure: Measure = MinimumHeightMeasure()
    private val preferredWidthMeasure: Measure = PreferredWidthMeasure()
    private val preferredHeightMeasure: Measure = PreferredHeightMeasure()

    /**
     * Measures a component by computing its minimum width.
     */
    private class MinimumWidthMeasure : Measure {
        override fun sizeOf(measurable: IntrinsicMeasurable): Int {
            return measurable.minIntrinsicWidth(Constraints.Infinity)
        }
    }

    /**
     * Measures a component by computing its minimum height.
     */
    private class MinimumHeightMeasure : Measure {
        override fun sizeOf(measurable: IntrinsicMeasurable): Int {
            return measurable.minIntrinsicHeight(Constraints.Infinity)
        }
    }

    /**
     * Measures a component by computing its preferred width.
     */
    private class PreferredWidthMeasure : Measure {
        override fun sizeOf(measurable: IntrinsicMeasurable): Int {
            return measurable.maxIntrinsicWidth(Constraints.Infinity)
        }
    }

    /**
     * Measures a component by computing its preferred height.
     */
    private class PreferredHeightMeasure : Measure {
        override fun sizeOf(measurable: IntrinsicMeasurable): Int {
            return measurable.maxIntrinsicHeight(Constraints.Infinity)
        }
    }

//    override fun IntrinsicMeasureScope.minIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int {
//        return computeLayoutSize(measurables, minimumWidthMeasure, minimumHeightMeasure).width.toInt()
//    }
//
//    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int {
//        return computeLayoutSize(measurables, minimumWidthMeasure, minimumHeightMeasure).height.toInt()
//    }
//
//    override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int {
//        return computeLayoutSize(measurables, preferredWidthMeasure, preferredHeightMeasure).width.toInt()
//    }
//
//    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int {
//        return computeLayoutSize(measurables, preferredWidthMeasure, preferredHeightMeasure).height.toInt()
//    }

    private fun initializeColAndRowComponentLists(
        measurables: List<IntrinsicMeasurable>,
        colSpecs: List<ColumnSpec>,
        rowSpecs: List<RowSpec>) {

        colComponents = arrayOf()
        for (i in colSpecs.indices) {
            colComponents[i] = ArrayList()
        }

        rowComponents = arrayOf()
        for (i in rowSpecs.indices) {
            rowComponents[i] = ArrayList()
        }

        for (element in measurables) {
            val elementDataNode = element.parentData as? FormLayoutChildDataNode
            if (elementDataNode != null) {
                val constraints = elementDataNode.cellConstraints
                if (constraints.gridWidth == 1) {
                    colComponents[constraints.gridX - 1].add(element)
                }

                if (constraints.gridHeight == 1) {
                    rowComponents[constraints.gridY - 1].add(element)
                }
            }
        }
    }

    private fun initializeColAndRowComponentLists2(
        measurables: List<Measurable>,
        colSpecs: List<ColumnSpec>,
        rowSpecs: List<RowSpec>) {

        colComponents2 = Array(colSpecs.size) { arrayListOf() }
        for (i in colSpecs.indices) {
            colComponents2[i] = ArrayList()
        }

        rowComponents2 = Array(rowSpecs.size) { arrayListOf() }
        for (i in rowSpecs.indices) {
            rowComponents2[i] = ArrayList()
        }

        for (element in measurables) {
            val elementDataNode = element.parentData as? FormLayoutChildDataNode
            if (elementDataNode != null) {
                val constraints = elementDataNode.cellConstraints
                if (constraints.gridWidth == 1) {
                    colComponents2[constraints.gridX - 1].add(element)
                }

                if (constraints.gridHeight == 1) {
                    rowComponents2[constraints.gridY - 1].add(element)
                }
            }
        }
    }

    /**
     * Computes and returns the grouped sizes.
     * Gives grouped columns and rows the same size.
     *
     * @param groups    the group specification
     * @param rawSizes    the raw sizes before the grouping
     * @return the grouped sizes
     */
    private fun groupedSizes(groups: Array<IntArray>?, rawSizes: IntArray): IntArray {
        // Return the compressed sizes if there are no groups.
        if (groups.isNullOrEmpty()) {
            return rawSizes
        }

        // Initialize the result with the given compressed sizes.
        val sizes = IntArray(rawSizes.size)
        for (i in sizes.indices) {
            sizes[i] = rawSizes[i]
        }

        // For each group equalize the sizes.
        for (groupIndices in groups) {
            var groupMaxSize = 0
            // Compute the group's maximum size.
            for (groupIndice in groupIndices) {
                val index = groupIndice - 1
                groupMaxSize = max(groupMaxSize, sizes[index])
            }
            // Set all sizes of this group to the group's maximum size.
            for (groupIndice in groupIndices) {
                val index = groupIndice - 1
                sizes[index] = groupMaxSize
            }
        }
        return sizes
    }

    /**
     * Computes origins from sizes taking the specified offset into account.
     *
     * @param sizes     the array of sizes
     * @param offset    an offset for the first origin
     * @return an array of origins
     */
    private fun computeOrigins(sizes: IntArray, offset: Int): IntArray {
        val count = sizes.size
        val origins = IntArray(count + 1)
        origins[0] = offset
        for (i in 1..count) {
            origins[i] = origins[i - 1] + sizes[i - 1]
        }
        return origins
    }

    /**
     * Computes and returns the sum of integers in the given array of ints.
     *
     * @param sizes    an array of ints to sum up
     * @return the sum of ints in the array
     */
    private fun sum(sizes: IntArray): Int {
        var sum = 0
        for (i in sizes.indices.reversed()) {
            sum += sizes[i]
        }
        return sum
    }

    /**
     * Computes and returns a table that maps a column/row index
     * to the maximum number of columns/rows that a component can span
     * without spanning a growing column.
     *
     * Iterates over the specs from right to left/bottom to top,
     * sets the table value to zero if a spec can grow,
     * otherwise increases the span by one.
     *
     * **Examples:**
     * ```text
     * "pref, 4dlu, pref, 2dlu, p:grow, 2dlu,      pref" ->
     * [4,    3,    2,    1,    0,      MAX_VALUE, MAX_VALUE]
     *
     * "p:grow, 4dlu, p:grow, 9dlu,      pref" ->
     * [0,      1,    0,      MAX_VALUE, MAX_VALUE]
     *
     * "p, 4dlu, p, 2dlu, 0:grow" ->
     * [4, 3,    2, 1,    0]
     * ```
     *
     * @param formSpecs  the column specs or row specs
     * @return a table that maps a spec index to the maximum span for
     * fixed size specs
     */
    private fun computeMaximumFixedSpanTable(formSpecs: List<FormSpec>): IntArray {
        val size = formSpecs.size
        val table = IntArray(size)
        var maximumFixedSpan = Int.MAX_VALUE // Could be 1
        for (i in size - 1 downTo 0) {
            val spec = formSpecs[i] // ArrayList access
            if (spec.canGrow()) {
                maximumFixedSpan = 0
            }
            table[i] = maximumFixedSpan
            if (maximumFixedSpan < Int.MAX_VALUE) {
                maximumFixedSpan++
            }
        }
        return table
    }


    /**
     * Computes and returns the sizes for the given form specs, component
     * lists and measures for minimum, preferred, and default size.
     *
     * @param formSpecs         the column or row specs, resp.
     * @param componentLists    the components list for each col/row
     * @param minMeasure        the measure used to determine min sizes
     * @param prefMeasure       the measure used to determine pre sizes
     * @param defaultMeasure    the measure used to determine default sizes
     * @return the column or row sizes
     */
    private fun maximumSizes(
        formSpecs: List<FormSpec>,
        componentLists: Array<List<IntrinsicMeasurable>>,
        minMeasure: Measure,
        prefMeasure: Measure,
        defaultMeasure: Measure
    ): IntArray {
        var formSpec: FormSpec
        val size = formSpecs.size
        val result = IntArray(size)
        for (i in 0..<size) {
            formSpec = formSpecs[i]
            result[i] = formSpec.maximumSize(
                componentLists[i],
                minMeasure,
                prefMeasure,
                defaultMeasure
            )
        }
        return result
    }

    /**
     * Computes and returns the compressed sizes. Compresses space for columns
     * and rows iff the available space is less than the total preferred size
     * but more than the total minimum size.
     *
     * Only columns and rows that are specified to be compressible will be
     * affected. You can specify a column and row as compressible by
     * giving it the component size <tt>default</tt>.
     *
     * @param formSpecs      the column or row specs to use
     * @param totalSize      the total available size
     * @param totalMinSize   the sum of all minimum sizes
     * @param totalPrefSize  the sum of all preferred sizes
     * @param minSizes       an int array of column/row minimum sizes
     * @param prefSizes      an int array of column/row preferred sizes
     * @return an int array of compressed column/row sizes
     */
    private fun compressedSizes(
        formSpecs: List<FormSpec>,
        totalSize: Int, totalMinSize: Int, totalPrefSize: Int,
        minSizes: IntArray, prefSizes: IntArray
    ): IntArray {
        // If we have less space than the total min size, answer the min sizes.

        if (totalSize < totalMinSize) {
            return minSizes
        }
        // If we have more space than the total pref size, answer the pref sizes.
        if (totalSize >= totalPrefSize) {
            return prefSizes
        }

        val count = formSpecs.size
        val sizes = IntArray(count)

        val totalCompressionSpace = (totalPrefSize - totalSize).toDouble()
        val maxCompressionSpace = (totalPrefSize - totalMinSize).toDouble()
        val compressionFactor = totalCompressionSpace / maxCompressionSpace

        //      System.out.println("Total compression space=" + totalCompressionSpace);
//      System.out.println("Max compression space  =" + maxCompressionSpace);
//      System.out.println("Compression factor     =" + compressionFactor);
        for (i in 0..<count) {
            val formSpec = formSpecs[i]
            sizes[i] = prefSizes[i]
            if (formSpec.size.compressible()) {
                sizes[i] -= ((prefSizes[i] - minSizes[i]) * compressionFactor).roundToInt()
            }
        }
        return sizes
    }



    /**
     * Distributes free space over columns and rows and
     * returns the sizes after this distribution process.
     *
     * @param formSpecs      the column/row specifications to work with
     * @param totalSize      the total available size
     * @param totalPrefSize  the sum of all preferred sizes
     * @param inputSizes     the input sizes
     * @return the distributed sizes
     */
    private fun distributedSizes(
        formSpecs: List<FormSpec>,
        totalSize: Int, totalPrefSize: Int,
        inputSizes: IntArray
    ): IntArray {
        val totalFreeSpace = (totalSize - totalPrefSize).toDouble()
        // Do nothing if there's no free space.
        if (totalFreeSpace < 0) {
            return inputSizes
        }

        // Compute the total weight.
        val count = formSpecs.size
        var totalWeight = 0.0
        for (i in 0..<count) {
            val formSpec = formSpecs[i]
            totalWeight += formSpec.resizeWeight
        }

        // Do nothing if there's no resizing column.
        if (totalWeight == 0.0) {
            return inputSizes
        }

        val sizes = IntArray(count)

        var restSpace = totalFreeSpace
        var roundedRestSpace = totalFreeSpace.toInt()
        for (i in 0..<count) {
            val formSpec = formSpecs[i]
            val weight = formSpec.resizeWeight
            if (weight == FormSpec.NoGrow) {
                sizes[i] = inputSizes[i]
            } else {
                val roundingCorrection = restSpace - roundedRestSpace
                val extraSpace = totalFreeSpace * weight / totalWeight
                val correctedExtraSpace = extraSpace - roundingCorrection
                val roundedExtraSpace = correctedExtraSpace.roundToInt()
                sizes[i] = inputSizes[i] + roundedExtraSpace
                restSpace -= extraSpace
                roundedRestSpace -= roundedExtraSpace
            }
        }
        return sizes
    }

    /**
     * Computes and returns the grid's origins.
     *
     * @param totalSize         the total size to assign
     * @param offset            the offset from left or top margin
     * @param formSpecs        the column or row specs, resp.
     * @param componentLists    the components list for each col/row
     * @param minMeasure        the measure used to determine min sizes
     * @param prefMeasure        the measure used to determine pre sizes
     * @param groupIndices        the group specification
     * @return an int array with the origins
     */
        private fun computeGridOrigins(
            totalSize: Int,
            offset: Int,
            formSpecs: List<FormSpec>,
            componentLists: Array<MutableList<Measurable>>,
            groupIndices: Array<IntArray>,
            minMeasure: Measure,
            prefMeasure: Measure
        ): IntArray {
            /* For each spec compute the minimum and preferred size that is
         * the maximum of all component minimum and preferred sizes resp.
         */
            // TODO: simplify
            val intrinsicComponentLists: Array<List<IntrinsicMeasurable>> =
                componentLists.map { it.map { m -> m as IntrinsicMeasurable } }.toTypedArray()
            val minSizes: IntArray = maximumSizes(
                formSpecs, intrinsicComponentLists,
                minMeasure, prefMeasure, minMeasure
            )
            val prefSizes: IntArray = maximumSizes(
                formSpecs, intrinsicComponentLists,
                minMeasure, prefMeasure, prefMeasure
            )

            val groupedMinSizes: IntArray = groupedSizes(groupIndices, minSizes)
            val groupedPrefSizes: IntArray = groupedSizes(groupIndices, prefSizes)
            val totalMinSize: Int = sum(groupedMinSizes)
            val totalPrefSize: Int = sum(groupedPrefSizes)
            val compressedSizes: IntArray = compressedSizes(
                formSpecs,
                totalSize,
                totalMinSize,
                totalPrefSize,
                groupedMinSizes,
                prefSizes
            )
            val groupedSizes: IntArray = groupedSizes(groupIndices, compressedSizes)
            val totalGroupedSize: Int = sum(groupedSizes)
            val sizes: IntArray = distributedSizes(
                formSpecs,
                totalSize,
                totalGroupedSize,
                groupedSizes
            )
            return computeOrigins(sizes, offset)
        }

    private fun measureComponents(
        measureScope: MeasureScope,
        layoutDirection: LayoutDirection,
        measurables: List<Measurable>,
        x: IntArray, y: IntArray): Map<Placeable, IntOffset> {

        val result = hashMapOf<Placeable, IntOffset>()

        for (element in measurables) {
            val elementDataNode = element.parentData as? FormLayoutChildDataNode
            if (elementDataNode != null) {
                val constraints = elementDataNode.cellConstraints

                val gridX = constraints.gridX - 1
                val gridY = constraints.gridY - 1
                val gridWidth = constraints.gridWidth
                val gridHeight = constraints.gridHeight

                val cellBounds = Rect(
                    left = x[gridX].toFloat(), top = y[gridY].toFloat(),
                    right = x[gridX + gridWidth].toFloat(), bottom = y[gridY + gridHeight].toFloat()
                )

                val rect = constraints.getBounds(measureScope, element,
                    colSpecs, rowSpecs, cellBounds, layoutDirection,
                    minimumWidthMeasure, minimumHeightMeasure,
                    preferredWidthMeasure, preferredHeightMeasure
                )

                val placeable = element.measure(Constraints.fixed(rect.width, rect.height))
                result[placeable] = IntOffset(rect.left, rect.top)
            }
        }

        return result
    }

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints,
    ): MeasureResult {
        initializeColAndRowComponentLists2(measurables, colSpecs, rowSpecs)
//            val size = parent.getSize()
//
//            val insets = parent.getInsets()
        val totalWidth = constraints.minWidth
        val totalHeight = constraints.minHeight

        val x: IntArray = computeGridOrigins(
            totalWidth,
            0,
            colSpecs,
            colComponents2,
            colGroupIndices,
            minimumWidthMeasure,
            preferredWidthMeasure
        )
        val y: IntArray = computeGridOrigins(
            totalHeight,
            0,
            rowSpecs,
            rowComponents2,
            rowGroupIndices,
            minimumHeightMeasure,
            preferredHeightMeasure
        )

        val placeables = measureComponents(this, this.layoutDirection, measurables, x, y)

        val boxWidth = constraints.minWidth
        val boxHeight = constraints.minHeight

        // Specify the size of the Box and position its children.
        return layout(boxWidth, boxHeight) {
            placeables.forEach { (placeable, offset) ->
                placeable.placeRelative(offset.x, offset.y)
            }
        }
    }
}
