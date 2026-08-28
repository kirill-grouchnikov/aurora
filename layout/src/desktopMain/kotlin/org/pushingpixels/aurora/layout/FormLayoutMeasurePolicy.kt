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

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import kotlin.collections.forEach
import kotlin.math.max
import kotlin.math.roundToInt

internal class FormLayoutMeasurePolicy(
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

    internal var gridXs: IntArray? = null
    internal var gridYs: IntArray? = null

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

        gridXs = x
        gridYs = y

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