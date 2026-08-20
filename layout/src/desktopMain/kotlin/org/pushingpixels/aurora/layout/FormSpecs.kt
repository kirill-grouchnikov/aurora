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

import org.pushingpixels.aurora.layout.util.LayoutStyle

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Provides frequently used column and row specifications.
 * 
 * @see FormLayout
 * @see ColumnSpec
 *
 * @since 1.6  This class was the FormFactory before.
 */
object FormSpecs {
    // Frequently used Column Specifications ********************************
    /**
     * An unmodifiable `ColumnSpec` that determines its width by
     * computing the maximum of all column component minimum widths.
     *
     * @see .PREF_COLSPEC
     *
     * @see .DEFAULT_COLSPEC
     */
    val MIN_COLSPEC: ColumnSpec by lazy { ColumnSpec(Sizes.ComponentSize.Minimum) }


    /**
     * An unmodifiable `ColumnSpec` that determines its width by
     * computing the maximum of all column component preferred widths.
     *
     * @see .MIN_COLSPEC
     *
     * @see .DEFAULT_COLSPEC
     */
    val PREF_COLSPEC: ColumnSpec by lazy {ColumnSpec(Sizes.ComponentSize.Preferred) }


    /**
     * An unmodifiable `ColumnSpec` that determines its preferred
     * width by computing the maximum of all column component preferred widths
     * and its minimum width by computing all column component minimum widths.
     *
     *
     *
     * Useful to let a column shrink from preferred width to minimum width
     * if the container space gets scarce.
     *
     * @see .MIN_COLSPEC
     *
     * @see .PREF_COLSPEC
     */
    val DEFAULT_COLSPEC: ColumnSpec by lazy { ColumnSpec(Sizes.ComponentSize.Default) }


    /**
     * An unmodifiable `ColumnSpec` that has an initial width
     * of 0 pixels and that grows. Useful to describe *glue* columns
     * that fill the space between other columns.
     *
     * @see .GLUE_ROWSPEC
     */
    val GLUE_COLSPEC: ColumnSpec by lazy {ColumnSpec(ColumnSpec.DEFAULT, Sizes.ZERO, FormSpec.DEFAULT_GROW) }


    // Layout Style Dependent Column Specs ***********************************
    /**
     * Describes a logical horizontal gap between a label and an associated
     * component. Useful for builders that automatically fill a grid with labels
     * and components.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @since 1.0.3
     */
    val LABEL_COMPONENT_GAP_COLSPEC: ColumnSpec by lazy {
        ColumnSpec.createGap(LayoutStyle.current.labelComponentPadX)
    }


    /**
     * Describes a logical horizontal gap between two related components.
     * For example the *OK* and *Cancel* buttons are considered
     * related.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .UNRELATED_GAP_COLSPEC
     */
    val RELATED_GAP_COLSPEC: ColumnSpec by lazy {ColumnSpec.createGap(LayoutStyle.current.relatedComponentsPadX) }


    /**
     * Describes a logical horizontal gap between two unrelated components.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .RELATED_GAP_COLSPEC
     */
    val UNRELATED_GAP_COLSPEC: ColumnSpec by lazy {ColumnSpec.createGap(LayoutStyle.current.unrelatedComponentsPadX) }


    /**
     * Describes a logical horizontal column for a fixed size button. This spec
     * honors the current layout style's default button minimum width.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .GROWING_BUTTON_COLSPEC
     */
    val BUTTON_COLSPEC: ColumnSpec by lazy {
        ColumnSpec(
            Sizes.bounded(
                Sizes.ComponentSize.Preferred,
                LayoutStyle.current.defaultButtonWidth,
                null
            )
        )
    }


    /**
     * Describes a logical horizontal column for a growing button. This spec
     * does *not* use the layout style's default button minimum width.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .BUTTON_COLSPEC
     */
    val GROWING_BUTTON_COLSPEC: ColumnSpec by lazy {
        ColumnSpec(
            ColumnSpec.DEFAULT,
            BUTTON_COLSPEC.size,
            FormSpec.DEFAULT_GROW
        )
    }


    // Frequently used Row Specifications ***********************************
    /**
     * An unmodifiable `RowSpec` that determines its height by
     * computing the maximum of all column component minimum heights.
     *
     * @see .PREF_ROWSPEC
     *
     * @see .DEFAULT_ROWSPEC
     */
    val MIN_ROWSPEC: RowSpec by lazy {RowSpec(Sizes.ComponentSize.Minimum) }


    /**
     * An unmodifiable `RowSpec` that determines its height by
     * computing the maximum of all column component preferred heights.
     *
     * @see .MIN_ROWSPEC
     *
     * @see .DEFAULT_ROWSPEC
     */
    val PREF_ROWSPEC: RowSpec by lazy {RowSpec(Sizes.ComponentSize.Preferred) }


    /**
     * An unmodifiable `RowSpec` that determines its preferred
     * height by computing the maximum of all column component preferred heights
     * and its minimum height by computing all column component minimum heights.
     *
     *
     *
     * Useful to let a column shrink from preferred height to minimum height
     * if the container space gets scarce.
     *
     * @see .MIN_COLSPEC
     *
     * @see .PREF_COLSPEC
     */
    val DEFAULT_ROWSPEC: RowSpec by lazy {RowSpec(Sizes.ComponentSize.Default) }


    /**
     * An unmodifiable `RowSpec` that has an initial height
     * of 0 pixels and that grows. Useful to describe *glue* rows
     * that fill the space between other rows.
     *
     * @see .GLUE_COLSPEC
     */
    val GLUE_ROWSPEC: RowSpec by lazy {RowSpec(RowSpec.DEFAULT, Sizes.ZERO, FormSpec.DEFAULT_GROW) }


    // Layout Style Dependent Row Specs *************************************
    /**
     * Describes a logical horizontal gap between a label and an associated
     * component. Useful for builders that automatically fill a grid with labels
     * and components.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @since 1.4
     */
    val LABEL_COMPONENT_GAP_ROWSPEC: RowSpec by lazy {RowSpec.createGap(LayoutStyle.current.labelComponentPadY) }


    /**
     * Describes a logical vertical gap between two related components.
     * For example the *OK* and *Cancel* buttons are considered
     * related.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .UNRELATED_GAP_ROWSPEC
     */
    val RELATED_GAP_ROWSPEC: RowSpec by lazy {RowSpec.createGap(LayoutStyle.current.relatedComponentsPadY) }


    /**
     * Describes a logical vertical gap between two unrelated components.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .RELATED_GAP_ROWSPEC
     */
    val UNRELATED_GAP_ROWSPEC: RowSpec by lazy {RowSpec.createGap(LayoutStyle.current.unrelatedComponentsPadY) }


    /**
     * Describes a logical vertical narrow gap between two rows in the grid.
     * Useful if the vertical space is scarce or if an individual vertical gap
     * shall be small than the default line gap.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .LINE_GAP_ROWSPEC
     *
     * @see .PARAGRAPH_GAP_ROWSPEC
     */
    val NARROW_LINE_GAP_ROWSPEC: RowSpec by lazy {RowSpec.createGap(LayoutStyle.current.narrowLinePad) }


    /**
     * Describes the logical vertical default gap between two rows in the grid.
     * A little bit larger than the narrow line gap.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .NARROW_LINE_GAP_ROWSPEC
     *
     * @see .PARAGRAPH_GAP_ROWSPEC
     */
    val LINE_GAP_ROWSPEC: RowSpec by lazy {RowSpec.createGap(LayoutStyle.current.linePad) }


    /**
     * Describes the logical vertical default gap between two paragraphs in
     * the layout grid. This gap is larger than the default line gap.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @see .NARROW_LINE_GAP_ROWSPEC
     *
     * @see .LINE_GAP_ROWSPEC
     */
    val PARAGRAPH_GAP_ROWSPEC: RowSpec by lazy {RowSpec.createGap(LayoutStyle.current.paragraphPad) }


    /**
     * Describes a logical row for a fixed size button. This spec
     * honors the current layout style's default button minimum height.
     *
     *
     *
     * **Note:** In a future version this constant will likely
     * be moved to a class `LogicalSize` or `StyledSize`.
     *
     * @since 1.2
     */
    val BUTTON_ROWSPEC: RowSpec by lazy {
        RowSpec(
            Sizes.bounded(
                Sizes.ComponentSize.Preferred,
                LayoutStyle.current.defaultButtonHeight,
                null
            )
        )
    }
}
