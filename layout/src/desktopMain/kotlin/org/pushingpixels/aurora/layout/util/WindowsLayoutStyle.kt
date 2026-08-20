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
package org.pushingpixels.aurora.layout.util

import org.pushingpixels.aurora.layout.ConstantSize
import org.pushingpixels.aurora.layout.Size
import org.pushingpixels.aurora.layout.Sizes

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * A [LayoutStyle] that aims to provide layout constants as defined by
 * Microsoft's *User Experience Guidelines*.
 */
internal class WindowsLayoutStyle private constructor() : LayoutStyle() {
    override val defaultButtonWidth: Size
        // Layout Sizes *********************************************************
        get() = BUTTON_WIDTH


    override val defaultButtonHeight: Size
        get() = BUTTON_HEIGHT


    override val dialogMarginX: ConstantSize
        get() = DIALOG_MARGIN_X


    override val dialogMarginY: ConstantSize
        get() = DIALOG_MARGIN_Y


    override val tabbedDialogMarginX: ConstantSize
        get() = TABBED_DIALOG_MARGIN_X


    override val tabbedDialogMarginY: ConstantSize
        get() = TABBED_DIALOG_MARGIN_Y


    override val labelComponentPadX: ConstantSize
        get() = LABEL_COMPONENT_PADX


    override val labelComponentPadY: ConstantSize
        get() = LABEL_COMPONENT_PADY


    override val relatedComponentsPadX: ConstantSize
        get() = RELATED_COMPONENTS_PADX


    override val relatedComponentsPadY: ConstantSize
        get() = RELATED_COMPONENTS_PADY


    override val unrelatedComponentsPadX: ConstantSize
        get() = UNRELATED_COMPONENTS_PADX


    override val unrelatedComponentsPadY: ConstantSize
        get() = UNRELATED_COMPONENTS_PADY


    override val narrowLinePad: ConstantSize
        get() = NARROW_LINE_PAD


    override val linePad: ConstantSize
        get() = LINE_PAD


    override val paragraphPad: ConstantSize
        get() = PARAGRAPH_PAD


    override val buttonBarPad: ConstantSize
        get() = BUTTON_BAR_PAD


    companion object {
        val INSTANCE: WindowsLayoutStyle = WindowsLayoutStyle()

        // Component Sizes ******************************************************
        private val BUTTON_WIDTH: Size = Sizes.dluX(50)
        private val BUTTON_HEIGHT: Size = Sizes.dluY(14)


        // Gaps ******************************************************************
        private val DIALOG_MARGIN_X: ConstantSize = Sizes.DLUX7
        private val DIALOG_MARGIN_Y: ConstantSize = Sizes.DLUY7

        private val TABBED_DIALOG_MARGIN_X: ConstantSize = Sizes.DLUX4
        private val TABBED_DIALOG_MARGIN_Y: ConstantSize = Sizes.DLUY4

        private val LABEL_COMPONENT_PADX: ConstantSize = Sizes.DLUX3
        private val RELATED_COMPONENTS_PADX: ConstantSize = Sizes.DLUX4
        private val UNRELATED_COMPONENTS_PADX: ConstantSize = Sizes.DLUX7

        private val LABEL_COMPONENT_PADY: ConstantSize = Sizes.DLUY2
        private val RELATED_COMPONENTS_PADY: ConstantSize = Sizes.DLUY4
        private val UNRELATED_COMPONENTS_PADY: ConstantSize = Sizes.DLUY7
        private val NARROW_LINE_PAD: ConstantSize = Sizes.DLUY2
        private val LINE_PAD: ConstantSize = Sizes.DLUY3
        private val PARAGRAPH_PAD: ConstantSize = Sizes.DLUY9
        private val BUTTON_BAR_PAD: ConstantSize = Sizes.DLUY5
    }
}
