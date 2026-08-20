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

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An abstract class that describes a layout and design style guide.
 * It provides constants used to lay out panels consistently.
 *
 *
 * 
 * **Note:** This class is work in progress and
 * the API may change without notice. Therefore it is recommended
 * to not write custom subclasses for production code.
 * A future version of this class may collaborate with a class
 * `LogicalSize` or `StyledSize`.
 * 
 * @see MacLayoutStyle
 * @see WindowsLayoutStyle
 * @see FormSpecs
 * @see Borders
 */
abstract class LayoutStyle {
    // Layout Sizes *********************************************************
    /**
     * Returns this style's default button width.
     * 
     * @return the default button width
     * 
     * @see .getDefaultButtonHeight
     */
    abstract val defaultButtonWidth: Size


    /**
     * Returns this style's default button height.
     * 
     * @return the default button height
     * 
     * @see .getDefaultButtonWidth
     */
    abstract val defaultButtonHeight: Size


    /**
     * Returns this style's horizontal margin for general dialogs.
     * 
     * @return the horizontal margin for general dialogs
     * 
     * @see .getDialogMarginY
     * @see .getTabbedDialogMarginX
     */
    abstract val dialogMarginX: ConstantSize


    /**
     * Returns this style's vertical margin for general dialogs.
     * 
     * @return the vertical margin for general dialogs
     * 
     * @see .getDialogMarginX
     * @see .getTabbedDialogMarginY
     */
    abstract val dialogMarginY: ConstantSize


    /**
     * Returns this style's horizontal margin for dialogs that consist of
     * a tabbed pane.
     * 
     * @return the horizontal margin for dialogs that consist of a tabbed pane
     * 
     * @see .getTabbedDialogMarginY
     * @see .getDialogMarginX
     * @since 1.0.3
     */
    abstract val tabbedDialogMarginX: ConstantSize


    /**
     * Returns this style's vertical margin for dialogs that consist of
     * a tabbed pane.
     * 
     * @return the vertical margin for dialogs that consist of a tabbed pane
     * 
     * @see .getTabbedDialogMarginX
     * @see .getDialogMarginY
     * @since 1.0.3
     */
    abstract val tabbedDialogMarginY: ConstantSize


    /**
     * Returns a gap used to separate a label and associated control.
     * 
     * @return a gap between label and associated control
     * 
     * @see .getRelatedComponentsPadX
     * @see .getUnrelatedComponentsPadX
     */
    abstract val labelComponentPadX: ConstantSize


    /**
     * Returns a gap used to separate a label and associated control.
     * 
     * @return a gap between label and associated control
     * 
     * @see .getRelatedComponentsPadY
     * @see .getUnrelatedComponentsPadY
     * @since 1.4
     */
    abstract val labelComponentPadY: ConstantSize


    /**
     * Returns a horizontal gap used to separate related controls.
     * 
     * @return a horizontal gap between related controls
     * 
     * @see .getLabelComponentPadX
     * @see .getRelatedComponentsPadY
     * @see .getUnrelatedComponentsPadX
     */
    abstract val relatedComponentsPadX: ConstantSize


    /**
     * Returns a vertical gap used to separate related controls.
     * 
     * @return a vertical gap between related controls
     * 
     * @see .getRelatedComponentsPadX
     * @see .getUnrelatedComponentsPadY
     */
    abstract val relatedComponentsPadY: ConstantSize


    /**
     * Returns a horizontal gap used to separate unrelated controls.
     * 
     * @return a horizontal gap between unrelated controls
     * 
     * @see .getLabelComponentPadX
     * @see .getUnrelatedComponentsPadY
     * @see .getRelatedComponentsPadX
     */
    abstract val unrelatedComponentsPadX: ConstantSize


    /**
     * Returns a vertical gap used to separate unrelated controls.
     * 
     * @return a vertical gap between unrelated controls
     * 
     * @see .getUnrelatedComponentsPadX
     * @see .getRelatedComponentsPadY
     */
    abstract val unrelatedComponentsPadY: ConstantSize


    /**
     * Returns a narrow vertical pad used to separate lines.
     * 
     * @return a narrow vertical pad used to separate lines
     * 
     * @see .getLinePad
     * @see .getParagraphPad
     */
    abstract val narrowLinePad: ConstantSize


    /**
     * Returns a narrow vertical pad used to separate lines.
     * 
     * @return a vertical pad used to separate lines
     * 
     * @see .getNarrowLinePad
     * @see .getParagraphPad
     */
    abstract val linePad: ConstantSize


    /**
     * Returns a pad used to separate paragraphs.
     * 
     * @return a vertical pad used to separate paragraphs
     * 
     * @see .getNarrowLinePad
     * @see .getLinePad
     */
    abstract val paragraphPad: ConstantSize


    /**
     * Returns a pad used to separate a button bar from a component.
     * 
     * @return a vertical pad used to separate paragraphs
     * 
     * @see .getRelatedComponentsPadY
     * @see .getUnrelatedComponentsPadY
     * @since 1.0.3
     */
    abstract val buttonBarPad: ConstantSize


    companion object {
        /**
         * Returns the current `LayoutStyle`.
         * 
         * @return the current `LayoutStyle`
         */
        /**
         * Set a new `LayoutStyle`.
         * 
         * @param newLayoutStyle   the style to be set
         */
        /**
         * Holds the current layout style.
         */
        var current: LayoutStyle = initialLayoutStyle()


        // Computing the initial layout style *************************************
        /**
         * Computes and returns the initial `LayoutStyle`.
         * Checks the OS name and returns `MacLayoutStyle`
         * on Mac OS X and `WindowLayoutStyle` on all other platforms.
         * 
         * @return MacLayoutStyle on Mac, WindowsLayoutStyle on all other platforms
         */
        private fun initialLayoutStyle(): LayoutStyle {
            val name = System.getProperty("os.name")
            return if (name?.startsWith("Mac") == true) {
                MacLayoutStyle.INSTANCE
            } else {
                WindowsLayoutStyle.INSTANCE
            }
        }
        // Accessing the current style ******************************************
    }
}
