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
package org.pushingpixels.aurora.layout.factories

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.util.LayoutStyle

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Provides constants and factory methods for [PaddingValues] that use
 * instances of [ConstantSize] to define the margins.
 * 
 * **Examples:**
 * ```kotlin
 * Paddings.Dlu2
 * Paddings.createPaddingValues(Sizes.DluY4, Sizes.DluX2, Sizes.DluY4, Sizes.DluX2)
 * Paddings.createPaddingValues("4dlu, 2dlu, 4dlu, 2dlu")
 * ```
 * 
 * @see [PaddingValues]
 * @see [Sizes]
 */
public object Paddings {
    // Constant Borders *****************************************************
    /**
     * A prepared and reusable PaddingValues without gaps.
     */
    public val Empty: PaddingValues = PaddingValues.Zero

    /**
     * PaddingValues with 2dlu on all sides.
     */
    public val Dlu2: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = Sizes.DluY2,
            start = Sizes.DluX2,
            bottom = Sizes.DluY2,
            end = Sizes.DluX2
        )

    /**
     * PaddingValues with 4dlu on all sides.
     */
    public val Dlu4: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = Sizes.DluY4,
            start = Sizes.DluX4,
            bottom = Sizes.DluY4,
            end = Sizes.DluX4
        )

    /**
     * PaddingValues with 7dlu on all sides.
     */
    public val Dlu7: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = Sizes.DluY7,
            start = Sizes.DluX7,
            bottom = Sizes.DluY7,
            end = Sizes.DluX7
        )

    /**
     * PaddingValues with 9dlu on all sides.
     */
    public val Dlu9: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = Sizes.DluY9,
            start = Sizes.DluX9,
            bottom = Sizes.DluY9,
            end = Sizes.DluX9
        )

    /**
     * PaddingValues with 14dlu on all sides.
     */
    public val Dlu14: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = Sizes.DluY14,
            start = Sizes.DluX14,
            bottom = Sizes.DluY14,
            end = Sizes.DluX14
        )

    /**
     * PaddingValues with 21dlu on all sides.
     */
    public val Dlu21: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = Sizes.DluY21,
            start = Sizes.DluX21,
            bottom = Sizes.DluY21,
            end = Sizes.DluX21
        )

    /**
     * A standardized PaddingValues that describes the gap between a component
     * and a button bar in its bottom.
     */
    public val ButtonBarPad: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = LayoutStyle.current.buttonBarPad,
            start = Sizes.dluX(0),
            bottom = Sizes.dluY(0),
            end = Sizes.dluX(0)
        )

    /**
     * A standardized PaddingValues that describes the border around
     * a dialog content that has no tabs.
     * 
     * @see [TabbedDialog]
     */
    public val Dialog: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = LayoutStyle.current.dialogMarginY,
            start = LayoutStyle.current.dialogMarginX,
            bottom = LayoutStyle.current.dialogMarginY,
            end = LayoutStyle.current.dialogMarginX
        )

    /**
     * A standardized PaddingValues that describes the border around
     * a dialog content that uses tabs.
     * 
     * @see [Dialog]
     */
    public val TabbedDialog: PaddingValues
        @Composable
        get() = createPaddingValues(
            top = LayoutStyle.current.tabbedDialogMarginY,
            start = LayoutStyle.current.tabbedDialogMarginX,
            bottom = LayoutStyle.current.tabbedDialogMarginY,
            end = LayoutStyle.current.tabbedDialogMarginX
        )

    // Factory Methods ******************************************************
    /**
     * Creates and returns a [PaddingValues] with the specified gaps.
     * 
     * @param top        the top gap
     * @param start        the start-hand side gap
     * @param bottom    the bottom gap
     * @param end    the end-hand side gap
     * @return a [PaddingValues] with the specified gaps
     * 
     * @see [createPaddingValues]
     */
    @Composable
    public fun createPaddingValues(
        top: ConstantSize, start: ConstantSize,
        bottom: ConstantSize, end: ConstantSize
    ): PaddingValues {
        require(LocalFormLayoutInitialized.current) {
            "Initialize the FormLayout parameters via `FormCortex` first"
        }
        val textMeasurer = LocalTextMeasurer.current
        val textStyle = LocalTextStyle.current
        val density = LocalDensity.current
        return FormPaddingValues(textMeasurer, textStyle, density, top, start, bottom, end)
    }

    /**
     * Creates and returns a [PaddingValues] using sizes as specified by
     * the given string. This string is a comma-separated encoding of
     * 4 [ConstantSize]s.
     * 
     * @param encodedSizes     top, start, bottom, end gap encoded as String
     * @return a [PaddingValues] with the specified gaps
     * 
     * @see [createPaddingValues]
     */
    @Composable
    public fun createPaddingValues(encodedSizes: String): PaddingValues {
        val token: Array<String> =
            encodedSizes.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val tokenCount = token.size
        require(token.size == 4) {
            "The border requires 4 sizes, but \"$encodedSizes\" has $tokenCount."
        }

        val top: ConstantSize = Sizes.constant(token[0], false)
        val start: ConstantSize = Sizes.constant(token[1], true)
        val bottom: ConstantSize = Sizes.constant(token[2], false)
        val end: ConstantSize = Sizes.constant(token[3], true)
        return createPaddingValues(top, start, bottom, end)
    }

    /**
     * An empty padding values that uses 4 instances of [ConstantSize]
     * to define the top, start, bottom and end gap.
     */
    public class FormPaddingValues(
        private val textMeasurer: TextMeasurer,
        private val textStyle: TextStyle,
        private val density: Density,
        private val top: ConstantSize,
        private val start: ConstantSize,
        private val bottom: ConstantSize,
        private val end: ConstantSize
    ) : PaddingValues {

        override fun calculateTopPadding(): Dp {
            with(density) {
                return top.getPixelSize(textMeasurer, textStyle).toDp()
            }
        }

        override fun calculateBottomPadding(): Dp {
            with(density) {
                return bottom.getPixelSize(textMeasurer, textStyle).toDp()
            }
        }

        override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp {
            val left = if (layoutDirection == LayoutDirection.Ltr) start else end
            with(density) {
                return left.getPixelSize(textMeasurer, textStyle).toDp()
            }
        }

        override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp {
            val right = if (layoutDirection == LayoutDirection.Ltr) end else start
            with(density) {
                return right.getPixelSize(textMeasurer, textStyle).toDp()
            }
        }
    }
}

