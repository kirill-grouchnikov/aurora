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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.layout.ConstantSize
import org.pushingpixels.aurora.layout.Sizes
import org.pushingpixels.aurora.layout.util.LayoutStyle

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Provides constants and factory methods for [PaddingValues] that use
 * instances of [ConstantSize] to define the margins.
 * 
 * **Examples:**<br></br>
 * <pre>
 * Paddings.Dlu2
 * Paddings.createPaddingValues(Sizes.DLUY4, Sizes.DLUX2, Sizes.DLUY4, Sizes.DLUX2);
 * Paddings.createPaddingValues("4dlu, 2dlu, 4dlu, 2dlu");
</pre> * 
 * 
 * @see [PaddingValues]
 * @see [Sizes]
 */
public object Paddings {
    internal lateinit var density: Density

    // Constant Borders *****************************************************
    /**
     * A prepared and reusable PaddingValues without gaps.
     */
    public val Empty: PaddingValues = PaddingValues.Zero

    /**
     * A prepared and reusable PaddingValues with 2dlu on all sides.
     */
    public val Dlu2: PaddingValues = createPaddingValues(
        Sizes.DLUY2,
        Sizes.DLUX2,
        Sizes.DLUY2,
        Sizes.DLUX2
    )

    /**
     * A prepared and reusable PaddingValues with 4dlu on all sides.
     */
    public val Dlu4: PaddingValues = createPaddingValues(
        Sizes.DLUY4,
        Sizes.DLUX4,
        Sizes.DLUY4,
        Sizes.DLUX4
    )

    /**
     * A prepared and reusable PaddingValues with 7dlu on all sides.
     */
    public val Dlu7: PaddingValues = createPaddingValues(
        Sizes.DLUY7,
        Sizes.DLUX7,
        Sizes.DLUY7,
        Sizes.DLUX7
    )

    /**
     * A prepared and reusable PaddingValues with 9dlu on all sides.
     */
    public val Dlu9: PaddingValues = createPaddingValues(
        Sizes.DLUY9,
        Sizes.DLUX9,
        Sizes.DLUY9,
        Sizes.DLUX9
    )

    /**
     * A prepared Border with PaddingValues on all sides.
     */
    public val Dlu14: PaddingValues = createPaddingValues(
        Sizes.DLUY14,
        Sizes.DLUX14,
        Sizes.DLUY14,
        Sizes.DLUX14
    )

    /**
     * A prepared Border with PaddingValues on all sides.
     */
    public val Dlu21: PaddingValues = createPaddingValues(
        Sizes.DLUY21,
        Sizes.DLUX21,
        Sizes.DLUY21,
        Sizes.DLUX21
    )

    /**
     * A standardized PaddingValues that describes the gap between a component
     * and a button bar in its bottom.
     */
    public val ButtonBarPad: PaddingValues = createPaddingValues(
        LayoutStyle.current.buttonBarPad,
        Sizes.dluX(0),
        Sizes.dluY(0),
        Sizes.dluX(0)
    )

    /**
     * A standardized PaddingValues that describes the border around
     * a dialog content that has no tabs.
     * 
     * @see [TabbedDialog]
     */
    public val Dialog: PaddingValues = createPaddingValues(
        LayoutStyle.current.dialogMarginY,
        LayoutStyle.current.dialogMarginX,
        LayoutStyle.current.dialogMarginY,
        LayoutStyle.current.dialogMarginX
    )

    /**
     * A standardized PaddingValues that describes the border around
     * a dialog content that uses tabs.
     * 
     * @see [Dialog]
     */
    public val TabbedDialog: PaddingValues = createPaddingValues(
        LayoutStyle.current.tabbedDialogMarginY,
        LayoutStyle.current.tabbedDialogMarginX,
        LayoutStyle.current.tabbedDialogMarginY,
        LayoutStyle.current.tabbedDialogMarginX
    )

    // Factory Methods ******************************************************
    /**
     * Creates and returns a [PaddingValues] with the specified gaps.
     * 
     * @param top        the top gap
     * @param left        the left-hand side gap
     * @param bottom    the bottom gap
     * @param right    the right-hand side gap
     * @return a [PaddingValues] with the specified gaps
     * 
     * @see [createPaddingValues]
     */
    public fun createPaddingValues(
        top: ConstantSize, left: ConstantSize,
        bottom: ConstantSize, right: ConstantSize
    ): PaddingValues {
        return FormPaddingValues(top, left, bottom, right)
    }

    /**
     * Creates and returns a [PaddingValues] using sizes as specified by
     * the given string. This string is a comma-separated encoding of
     * 4 [ConstantSize]s.
     * 
     * @param encodedSizes     top, left, bottom, right gap encoded as String
     * @return a [PaddingValues] with the specified gaps
     * 
     * @see [createPaddingValues]
     */
    public fun createPaddingValues(encodedSizes: String): PaddingValues {
        val token: Array<String> =
            encodedSizes.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val tokenCount = token.size
        require(token.size == 4) {
            "The border requires 4 sizes, but \"$encodedSizes\" has $tokenCount."
        }

        val top: ConstantSize = Sizes.constant(token[0], false)
        val left: ConstantSize = Sizes.constant(token[1], true)
        val bottom: ConstantSize = Sizes.constant(token[2], false)
        val right: ConstantSize = Sizes.constant(token[3], true)
        return createPaddingValues(top, left, bottom, right)
    }

    /**
     * An empty padding values that uses 4 instances of [ConstantSize]
     * to define the top, start, bottom and end gap.
     */
    public class FormPaddingValues(
        private val top: ConstantSize,
        private val start: ConstantSize,
        private val bottom: ConstantSize,
        private val end: ConstantSize
    ) : PaddingValues {

        override fun calculateTopPadding(): Dp {
            return (top.getPixelSize() / density.density).dp
        }

        override fun calculateBottomPadding(): Dp {
            return (bottom.getPixelSize() / density.density).dp
        }

        override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp {
            val left = if (layoutDirection == LayoutDirection.Ltr) start else end
            return (left.getPixelSize() / density.density).dp
        }

        override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp {
            val right = if (layoutDirection == LayoutDirection.Ltr) end else start
            return (right.getPixelSize() / density.density).dp
        }
    }
}

