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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.layout.FormSpec.DefaultAlignment
import java.util.*

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Defines constraints for components that are laid out with the FormLayout.
 * Defines the components display area: grid&nbsp;x, grid&nbsp;y,
 * grid width (column span), grid height (row span), horizontal alignment
 * and vertical alignment.
 *
 * You can set optional insets in a constructor. This is useful if you
 * need to use a pixel-size insets to align perceived component bounds
 * with pixel data, for example an icon. Anyway, this is rarely used.
 * The insets don't affect the size computation for columns and rows.
 * I consider renaming the insets to offsets to better indicate the
 * motivation for this option.
 *
 * **Examples**:<br></br>
 * The following cell constraints locate a component in the third
 * column of the fifth row; column and row span are 1; the component
 * will be aligned with the column's right-hand side and the row's
 * bottom.
 * <pre>
 * CellConstraints.xy(3, 5)
 * CellConstraints.xy(3, 5, CellConstraints.Right, CellConstraints.Bottom)
 * CellConstraints.xy(3, 5, "right, bottom")
 * 
 * CellConstraints.xyw (3, 5, 1)
 * CellConstraints.xyw (3, 5, 1, CellConstraints.Right, CellConstraints.Bottom)
 * CellConstraints.xyw (3, 5, 1, "right, bottom")
 * 
 * CellConstraints.xywh(3, 5, 1, 1)
 * CellConstraints.xywh(3, 5, 1, 1, CellConstraints.Right, CellConstraints.Bottom)
 * CellConstraints.xywh(3, 5, 1, 1, "right, bottom")
 * </pre>
 * See also the examples in the [FormLayout] class comment.
 *
 * TODO: Explain in the JavaDocs that the insets are actually offsets.
 * And describe that these offsets are not taken into account when
 * FormLayout computes the column and row sizes.
 *
 * TODO: Rename the inset to offsets.
 */
public data class CellConstraints(
    public val gridX: Int = 1, public val gridY: Int = 1,
    public val gridWidth: Int = 1, public val gridHeight: Int = 1,
    public val hAlign: Alignment = Alignment.Default, public val vAlign: Alignment = Alignment.Default,
    public val padding: PaddingValues = PaddingValues.Zero
) {
    init {
        require (gridX > 0) {
            "The grid x must be a positive number."
        }
        require (gridY > 0) {
            "The grid y must be a positive number."
        }
        require (gridWidth > 0) {
            "The grid width must be a positive number."
        }
        require (gridHeight > 0) {
            "The grid height must be a positive number."
        }
        ensureValidOrientations(hAlign, vAlign)
    }

    // Parsing and Decoding String Descriptions *****************************
    /**
     * Decodes and returns the grid bounds and alignments for this
     * constraints as an array of six integers. The string representation
     * is a comma separated sequence, one of
     * <pre>
     * "x, y"
     * "x, y, w, h"
     * "x, y, hAlign, vAlign"
     * "x, y, w, h, hAlign, vAlign"
    </pre> * 
     * 
     * @param encodedConstraints represents horizontal and vertical alignment
     * 
     * @throws IllegalArgumentException if the encoded constraints do not
     * follow the constraint syntax
     */

    /**
     * Checks and verifies that this constraints object has valid grid
     * index values, i. e. the display area cells are inside the form's grid.
     * 
     * @param colCount  number of columns in the grid
     * @param rowCount  number of rows in the grid
     * @throws IndexOutOfBoundsException if the display area described
     * by this constraints object is not inside the grid
     */
    public fun ensureValidGridBounds(colCount: Int, rowCount: Int) {
        if (gridX <= 0) {
            throw IndexOutOfBoundsException(
                "The column index $gridX must be positive."
            )
        }
        if (gridX > colCount) {
            throw IndexOutOfBoundsException(
                ("The column index $gridX must be less than or equal to $colCount.")
            )
        }
        if (gridX + gridWidth - 1 > colCount) {
            throw IndexOutOfBoundsException(
                ("The grid width $gridWidth must be less than or equal to ${colCount - gridX + 1}.")
            )
        }
        if (gridY <= 0) {
            throw IndexOutOfBoundsException(
                "The row index $gridY must be positive."
            )
        }
        if (gridY > rowCount) {
            throw IndexOutOfBoundsException(
                ("The row index $gridY must be less than or equal to $rowCount.")
            )
        }
        if (gridY + gridHeight - 1 > rowCount) {
            throw IndexOutOfBoundsException(
                ("The grid height $gridHeight must be less than or equal to ${rowCount - gridY + 1}.")
            )
        }
    }

    // Settings Component Bounds ********************************************
    /**
     * Sets the component's bounds using the given component and cell bounds.
     * 
     * @param c                  the component to set bounds
     * @param layout             the FormLayout instance that computes the bounds
     * @param cellBounds          the cell's bounds
     * @param minWidthMeasure      measures the minimum width
     * @param minHeightMeasure      measures the minimum height
     * @param prefWidthMeasure      measures the preferred width
     * @param prefHeightMeasure  measures the preferred height
     */
    public fun getBounds(
        measureScope: MeasureScope,
        c: IntrinsicMeasurable,
        colSpecs: List<ColumnSpec>,
        rowSpecs: List<RowSpec>,
        cellBounds: Rect,
        layoutDirection: LayoutDirection,
        minWidthMeasure: Measure,
        minHeightMeasure: Measure,
        prefWidthMeasure: Measure,
        prefHeightMeasure: Measure
    ): IntRect {
        with (measureScope) {
            val colSpec: ColumnSpec? = if (gridWidth == 1) colSpecs[gridX-1] else null
            val rowSpec: RowSpec? = if (gridHeight == 1) rowSpecs[gridY-1] else null
            val concreteHAlign: Alignment? = concreteAlignment(hAlign, colSpec)
            val concreteVAlign: Alignment? = concreteAlignment(vAlign, rowSpec)
            val cellX = cellBounds.left + padding.calculateLeftPadding(layoutDirection).toPx()
            val cellY = cellBounds.top + padding.calculateTopPadding().toPx()
            val cellW = cellBounds.width - padding.calculateLeftPadding(layoutDirection).toPx() -
                padding.calculateRightPadding(layoutDirection).toPx()
            val cellH = cellBounds.height - padding.calculateTopPadding().toPx() -
                padding.calculateBottomPadding().toPx()
            val compW: Int = componentSize(
                c, colSpec, cellW.toInt(), minWidthMeasure,
                prefWidthMeasure
            )
            val compH: Int = componentSize(
                c, rowSpec, cellH.toInt(), minHeightMeasure,
                prefHeightMeasure
            )
            val x: Int = origin(concreteHAlign, cellX.toInt(), cellW.toInt(), compW)
            val y: Int = origin(concreteVAlign, cellY.toInt(), cellH.toInt(), compH)
            val w: Int = extent(concreteHAlign, cellW.toInt(), compW)
            val h: Int = extent(concreteVAlign, cellH.toInt(), compH)

            return IntRect(left = x, top = y, right =  x + w, bottom = y + h)
        }
    }

    /**
     * Constructs and returns a string representation of this constraints object.
     * 
     * @return    string representation of this constraints object
     */
    override fun toString(): String {
        val buffer = StringBuffer("CellConstraints")
        buffer.append("[x=")
        buffer.append(gridX)
        buffer.append("; y=")
        buffer.append(gridY)
        buffer.append("; w=")
        buffer.append(gridWidth)
        buffer.append("; h=")
        buffer.append(gridHeight)
        buffer.append("; hAlign=")
        buffer.append(hAlign)
        buffer.append("; vAlign=")
        buffer.append(vAlign)
        if (PaddingValues.Zero != this@CellConstraints.padding) {
            buffer.append("; insets=")
            buffer.append(this@CellConstraints.padding)
        }
//        buffer.append("; honorsVisibility=")
//        buffer.append(honorsVisibility)

        buffer.append(']')
        return buffer.toString()
    }

    internal enum class Orientation {
        Horizontal, Vertical, Both
    }

    // Helper Class *********************************************************
    /**
     * Enumeration for component alignment types as used by the [FormLayout].
     */
    public enum class Alignment(private val orientation: Orientation) {
        /**
         * Use the column's or row's default alignment.
         */
        Default(Orientation.Both),

        /**
         * Fill the cell either horizontally or vertically.
         */
        Fill(Orientation.Both),

        /**
         * Put the component in the left.
         */
        Left(Orientation.Horizontal),

        /**
         * Put the component in the right.
         */
        Right(Orientation.Horizontal),

        /**
         * Put the component in the center.
         */
        Center(Orientation.Both),

        /**
         * Put the component in the top.
         */
        Top(Orientation.Vertical),

        /**
         * Put the component in the bottom.
         */
        Bottom(Orientation.Vertical);

        /**
         * Returns this Alignment's name.
         * 
         * @return this alignment's name.
         */
        override fun toString(): String {
            return name
        }

        /**
         * Returns the first character of this Alignment's name.
         * Used to identify it in short format strings.
         * 
         * @return the name's first character.
         */
        public fun abbreviation(): Char {
            return name[0]
        }

        internal val isHorizontal: Boolean
            get() = orientation != Orientation.Vertical

        internal val isVertical: Boolean
            get() = orientation != Orientation.Horizontal

        public companion object {
            public fun valueOf(nameOrAbbreviation: String): Alignment {
                val str = nameOrAbbreviation.lowercase()
                when (str) {
                    "d", "default" -> {
                        return Default
                    }
                    "f", "fill" -> {
                        return Fill
                    }
                    "c", "center" -> {
                        return Center
                    }
                    "l", "left" -> {
                        return Left
                    }
                    "r", "right" -> {
                        return Right
                    }
                    "t", "top" -> {
                        return Top
                    }
                    "b", "bottom" -> {
                        return Bottom
                    }
                    else -> {
                        throw IllegalArgumentException(
                            ("Invalid alignment " + nameOrAbbreviation
                                + ". Must be one of: left, center, right, top, bottom, "
                                + "fill, default, l, c, r, t, b, f, d.")
                        )
                    }
                }
            }

            // Serialization *********************************************************
            private var nextOrdinal = 0
        }
    }

    public companion object {
        // Alignment Constants *************************************************

        /**
         * Decodes an integer string representation and returns the
         * associated Integer or null in case of an invalid number format.
         * 
         * @param token        the encoded integer
         * @return the decoded Integer or null
         */
        private fun decodeInt(token: String): Int? {
            try {
                return Integer.decode(token)
            } catch (e: NumberFormatException) {
                return null
            }
        }

        /**
         * Parses an alignment string description and
         * returns the corresponding alignment value.
         * 
         * @param encodedAlignment    the encoded alignment
         * @return the associated `Alignment` instance
         */
        private fun decodeAlignment(encodedAlignment: String): Alignment {
            return Alignment.Companion.valueOf(encodedAlignment)
        }

        /**
         * Checks and verifies that the horizontal alignment is a horizontal
         * and the vertical alignment is vertical.
         * 
         * @param horizontalAlignment  the horizontal alignment
         * @param verticalAlignment    the vertical alignment
         * @throws IllegalArgumentException if an alignment is invalid
         */
        private fun ensureValidOrientations(horizontalAlignment: Alignment, verticalAlignment: Alignment) {
            require(horizontalAlignment.isHorizontal) {
                "The horizontal alignment must be one of: left, center, right, fill, default."
            }
            require(verticalAlignment.isVertical) {
                "The vertical alignment must be one of: top, center, bottom, fill, default."
            }
        }

        /**
         * Computes and returns the concrete alignment. Takes into account
         * the cell alignment and *the* `FormSpec` if applicable.
         *
         *
         * 
         * If this constraints object doesn't belong to a single column or row,
         * the `formSpec` parameter is `null`.
         * In this case the cell alignment is answered, but `DEFAULT`
         * is mapped to `FILL`.
         *
         *
         * 
         * If the cell belongs to a single column or row, we use the cell
         * alignment, unless it is `DEFAULT`, where the alignment
         * is inherited from the column or row resp.
         * 
         * @param cellAlignment   this cell's alignment
         * @param formSpec        the associated column or row specification
         * @return the concrete alignment
         */
        private fun concreteAlignment(cellAlignment: Alignment?, formSpec: FormSpec?): Alignment? {
            return if (formSpec == null)
                if (cellAlignment == Alignment.Default) Alignment.Fill else cellAlignment
            else
                usedAlignment(cellAlignment, formSpec)
        }

        /**
         * Returns the alignment used for a given form constraints object.
         * The cell alignment overrides the column or row default, unless
         * it is `DEFAULT`. In the latter case, we use the
         * column or row alignment.
         * 
         * @param cellAlignment   this cell constraint's alignment
         * @param formSpec        the associated column or row specification
         * @return the alignment used
         */
        private fun usedAlignment(cellAlignment: Alignment?, formSpec: FormSpec): Alignment? {
            if (cellAlignment != Alignment.Default) {
                // Cell alignments other than DEFAULT override col/row alignments
                return cellAlignment
            }
            val defaultAlignment: DefaultAlignment = formSpec.getDefaultAlignment()
            if (defaultAlignment == DefaultAlignment.FillAlign) {
                return Alignment.Fill
            }
            return when (defaultAlignment) {
                ColumnSpec.Left -> {
                    Alignment.Left
                }
                DefaultAlignment.CenterAlign -> {
                    Alignment.Center
                }
                ColumnSpec.Right -> {
                    Alignment.Right
                }
                RowSpec.Top -> {
                    Alignment.Top
                }
                else -> {
                    Alignment.Bottom
                }
            }
        }

        /**
         * Computes and returns the pixel size of the given component using the
         * given form specification, measures, and cell size.
         * 
         * @param component    the component to measure
         * @param formSpec        the specification of the component's column/row
         * @param minMeasure    the measure for the minimum size
         * @param prefMeasure    the measure for the preferred size
         * @param cellSize        the cell size
         * @return the component size as measured or a constant
         */
        private fun componentSize(
            component: IntrinsicMeasurable,
            formSpec: FormSpec?,
            cellSize: Int,
            minMeasure: Measure,
            prefMeasure: Measure
        ): Int {
            return if (formSpec == null) {
                prefMeasure.sizeOf(component)
            } else if (formSpec.size === Sizes.ComponentSize.Minimum) {
                minMeasure.sizeOf(component)
            } else if (formSpec.size === Sizes.ComponentSize.Preferred) {
                prefMeasure.sizeOf(component)
            } else {  // default mode
                cellSize.coerceAtMost(prefMeasure.sizeOf(component))
            }
        }

        /**
         * Computes and returns the component's pixel origin.
         * 
         * @param alignment            the component's alignment
         * @param cellOrigin        the origin of the display area
         * @param cellSize            the extent of the display area
         * @param componentSize     the component's size
         * @return the component's pixel origin
         */
        private fun origin(
            alignment: Alignment?,
            cellOrigin: Int,
            cellSize: Int,
            componentSize: Int
        ): Int {
            return when (alignment) {
                Alignment.Right, Alignment.Bottom -> {
                    cellOrigin + cellSize - componentSize
                }
                Alignment.Center -> {
                    cellOrigin + (cellSize - componentSize) / 2
                }
                else -> {  // left, top, fill
                    cellOrigin
                }
            }
        }

        /**
         * Returns the component's pixel extent.
         * 
         * @param alignment        the component's alignment
         * @param cellSize            the size of the display area
         * @param componentSize    the component's size
         * @return the component's pixel extent
         */
        private fun extent(alignment: Alignment?, cellSize: Int, componentSize: Int): Int {
            return if (alignment == Alignment.Fill)
                cellSize
            else
                componentSize
        }

        /**
         * Returns an integer that has a minimum of two characters.
         * 
         * @param number   the number to format
         * @return a string representation for a number with a minimum of two chars
         */
        private fun formatInt(number: Int): String {
            val str = number.toString()
            return if (number < 10) " $str" else str
        }

        /**
         * Decodes a string description for the horizontal and vertical alignment
         * and sets this CellConstraints' alignment values. If the boolean is
         * `true` the horizontal alignment is the first token,
         * and the vertical alignment is the second token. if the boolean is
         * `false` the vertical alignment comes first.
         *
         * Valid horizontal alignments are: left, center, right, default, and fill.
         * Valid vertical alignments are: top, center, bottom, default, and fill.
         * The anchor's string representation abbreviates the alignment:
         * l, c, r, d, f, t, and b.
         *
         * Anchor examples:
         * "c, c" is centered, "l, t" is northwest, "c, t" is north, "r, c" east.
         * "c, d" is horizontally centered and uses the row's default alignment.
         * "d, t" is on top of the cell and uses the column's default alignment.
         *
         * @param encodedAlignments represents horizontal and vertical alignment
         * @throws IllegalArgumentException if an alignment orientation is invalid
         */
        private fun parseAlignments(encodedAlignments: String, horizontalThenVertical: Boolean): Pair<Alignment, Alignment> {
            val tokenizer = StringTokenizer(encodedAlignments, " ,")
            val first: Alignment = decodeAlignment(tokenizer.nextToken())
            val second: Alignment = decodeAlignment(tokenizer.nextToken())
            val hAlign = if (horizontalThenVertical) first else second
            val vAlign = if (horizontalThenVertical) second else first
            ensureValidOrientations(hAlign, vAlign)
            return Pair(hAlign, vAlign)
        }

        /**
         * Creates a [CellConstraints] from the column, row, width, and height; sets the horizontal
         * and vertical alignment using the specified alignment objects.
         *
         * **Examples:**
         * CellConstraints.xywh(1, 3, 2, 1, CellConstraints.Left,   CellConstraints.Bottom)
         * CellConstraints.xywh(1, 3, 7, 3, CellConstraints.Center, CellConstraints.Fill)
         *
         * @param col       the new column index
         * @param row       the new row index
         * @param colSpan   the column span or grid width
         * @param rowSpan   the row span or grid height
         * @param colAlign  horizontal component alignment
         * @param rowAlign  vertical component alignment
         *
         * @throws IllegalArgumentException if an alignment orientation is invalid
         */
        public fun xywh(
            col: Int, row: Int, colSpan: Int, rowSpan: Int,
            colAlign: Alignment = Alignment.Default, rowAlign: Alignment = Alignment.Default
        ): CellConstraints {
            ensureValidOrientations(horizontalAlignment = colAlign, verticalAlignment = rowAlign)
            return CellConstraints(gridX = col, gridY = row, gridWidth = colSpan, gridHeight = rowSpan,
                hAlign = colAlign, vAlign = rowAlign)
        }

        /**
         * Creates a [CellConstraints] from the column, row, width, and height; decodes the horizontal
         * and vertical alignments from the given string.
         *
         * **Examples:**
         * CellConstraints.xywh(1, 3, 2, 1, "left, bottom")
         * CellConstraints.xywh(1, 3, 2, 1, "l, b")
         * CellConstraints.xywh(1, 3, 7, 3, "center, fill")
         * CellConstraints.xywh(1, 3, 7, 3, "c, f")
         *
         * @param col                the new column index
         * @param row                the new row index
         * @param colSpan            the column span or grid width
         * @param rowSpan            the row span or grid height
         * @param encodedAlignments  describes the horizontal and vertical alignments
         *
         * @throws IllegalArgumentException if an alignment orientation is invalid
         */
        public fun xywh(col: Int, row: Int, colSpan: Int, rowSpan: Int, encodedAlignments: String): CellConstraints {
            val alignments = parseAlignments(encodedAlignments, true)
            return xywh(col, row, colSpan, rowSpan, alignments.first, alignments.second)
        }

        /**
         * Creates a [CellConstraints] from the row, column, height, and width; sets the vertical and
         * horizontal alignment using the specified alignment objects.
         *
         * **Examples:**
         * CellConstraints.rchw(3, 1, 1, 2, CellConstraints.Bottom, CellConstraints.Left)
         * CellConstraints.rchw(3, 1, 3, 7, CellConstraints.Fill,   CellConstraints.Center)
         *
         *
         * @param row       the new row index
         * @param col       the new column index
         * @param rowSpan   the row span or grid height
         * @param colSpan   the column span or grid width
         * @param rowAlign  vertical component alignment
         * @param colAlign  horizontal component alignment
         *
         * @throws IllegalArgumentException if an alignment orientation is invalid
         */
        public fun rchw(
            row: Int, col: Int, rowSpan: Int, colSpan: Int,
            rowAlign: Alignment = Alignment.Default, colAlign: Alignment = Alignment.Default
        ): CellConstraints {
            return xywh(col, row, colSpan, rowSpan, colAlign, rowAlign)
        }

        /**
         * Creates a [CellConstraints] from the row, column, height, and width; decodes the horizontal
         * and vertical alignments from the given string.
         *
         * **Examples:**
         * CellConstraints.rchw(3, 1, 1, 2, "bottom, left")
         * CellConstraints.rchw(3, 1, 1, 2, "b, l")
         * CellConstraints.rchw(3, 1, 3, 7, "fill, center")
         * CellConstraints.rchw(3, 1, 3, 7, "f, c")
         *
         * @param row                the new row index
         * @param col                the new column index
         * @param rowSpan            the row span or grid height
         * @param colSpan            the column span or grid width
         * @param encodedAlignments  describes the horizontal and vertical alignments
         *
         * @throws IllegalArgumentException if an alignment orientation is invalid
         */
        public fun rchw(row: Int, col: Int, rowSpan: Int, colSpan: Int, encodedAlignments: String): CellConstraints {
            val alignments = parseAlignments(encodedAlignments, true)
            return rchw(row, col, rowSpan, colSpan, alignments.first, alignments.second)
        }

        /**
         * Constructs an instance of [CellConstraints] from
         * the given encoded string properties.<p>
         *
         * <strong>Examples:</strong><pre>
         * CellConstraints("1, 3")
         * CellConstraints("1, 3, left, bottom")
         * CellConstraints("1, 3, 2, 1, left, bottom")
         * CellConstraints("1, 3, 2, 1, l, b")
         * </pre>
         *
         * @param encodedConstraints	the constraints encoded as string
         */
        public fun fromConstraints(encodedConstraints: String): CellConstraints {
            val tokenizer = StringTokenizer(encodedConstraints, " ,")
            val argCount = tokenizer.countTokens()
            require(argCount == 2 || argCount == 4 || argCount == 6) {
                "You must provide 2, 4 or 6 arguments."
            }
            var nextInt: Int? = decodeInt(tokenizer.nextToken())
            require(nextInt != null) {
                "First cell constraint element must be a number."
            }
            val gridX = nextInt
            require(gridX > 0) { "The grid x must be a positive number." }
            nextInt = decodeInt(tokenizer.nextToken())
            require(nextInt != null) {
                "Second cell constraint element must be a number."
            }
            val gridY = nextInt
            require(gridY > 0) { "The grid y must be a positive number." }
            if (!tokenizer.hasMoreTokens()) {
                return CellConstraints(gridX, gridY)
            }

            var token = tokenizer.nextToken()
            nextInt = decodeInt(token)
            var gridWidth: Int = 0
            var gridHeight: Int = 0
            if (nextInt != null) {
                // Case: "x, y, w, h" or
                //       "x, y, w, h, hAlign, vAlign"
                gridWidth = nextInt
                if (gridWidth <= 0) {
                    throw IndexOutOfBoundsException(
                        "The grid width must be a positive number."
                    )
                }
                nextInt = decodeInt(tokenizer.nextToken())
                requireNotNull(nextInt) { "Fourth cell constraint element must be like third." }
                gridHeight = nextInt
                if (gridHeight <= 0) {
                    throw IndexOutOfBoundsException(
                        "The grid height must be a positive number."
                    )
                }

                if (!tokenizer.hasMoreTokens()) {
                    return CellConstraints(gridX, gridY, gridWidth, gridHeight)
                }
                token = tokenizer.nextToken()
            }

            val hAlign = decodeAlignment(token)
            val vAlign = decodeAlignment(tokenizer.nextToken())
            ensureValidOrientations(hAlign, vAlign)
            return CellConstraints(gridX, gridY, gridWidth, gridHeight, hAlign, vAlign)
        }
    }
}
