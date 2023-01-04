/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.component.layout

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*

object CommandButtonSizingConstants {
    val PopupIconWidth = 6.0.dp
    val PopupIconHeight = 4.0.dp
    val DefaultHorizontalContentLayoutGap = 2.0.dp
    val DefaultVerticalContentLayoutGap = 2.0.dp
}

/**
 * Definition of a layout manager for command buttons.
 *
 * @author Kirill Grouchnikov
 */
interface CommandButtonLayoutManager : MeasureScope {
    /**
     * Enumerates the available values for separator orientations.
     */
    enum class CommandButtonSeparatorOrientation {
        /**
         * Vertical separator orientation.
         */
        Vertical,

        /**
         * Horizontal separator orientation.
         */
        Horizontal
    }

    /**
     * Layout information on a single line of text.
     *
     * @param text Text itself
     * @param textRect The text rectangle.
     */
    data class TextLayoutInfo(val text: String, var textRect: Rect)

    /**
     * Pre-layout information on different visual parts of a single command button.
     *
     * @param commandButtonKind Layout kind
     * @param texts Command button texts (one per each line)
     * @param extraTexts Command button extra texts (one per each line)
     * @param isTextInActionArea Indication whether the command button text (texts in
     * [.texts]) belongs in the action area.
     * @param separatorOrientation Separator orientation
     * @param showPopupIcon Indicates whether this button shows the popup icon
     */
    data class CommandButtonPreLayoutInfo(
        val commandButtonKind: CommandButtonKind,
        val showIcon: Boolean,
        val texts: List<String>,
        val extraTexts: List<String>,
        val isTextInActionArea: Boolean,
        val separatorOrientation: CommandButtonSeparatorOrientation?,
        val showPopupIcon: Boolean
    )

    /**
     * Layout information on different visual parts of a single command button.
     *
     * @param actionClickArea The action area. A mouse click in this area will trigger the action
     * lambda set as [Command.action].
     * @param popupClickArea The popup area. A mouse click in this area will show the popup content
     * associated with the command button.
     * @param separatorArea The separator area. If it's not empty, the command button will show a
     * separator between [.actionClickArea] and [.popupClickArea] on mouse rollover.
     * @param iconRect Rectangle for the command button icon.
     * @param textLayoutInfoList Layout information for the command button text (that can span
     * multiple lines).
     * @param extraTextLayoutInfoList Layout information for the command button extra text
     * (that can span multiple lines).
     * @param popupActionRect Rectangle for the icon associated with the [.popupClickArea].
     * This icon is an arrow indicating that the command button has a popup area.
     */
    data class CommandButtonLayoutInfo(
        val fullSize: Size,
        val actionClickArea: Rect,
        val popupClickArea: Rect,
        val separatorArea: Rect,
        val iconRect: Rect,
        val textLayoutInfoList: List<TextLayoutInfo>,
        val extraTextLayoutInfoList: List<TextLayoutInfo>,
        val popupActionRect: Rect,
    )

    /**
     * Returns the preferred icon size under this layout manager. Note that some layout managers
     * may use fixed icon size, while some may respect the icon size set in [.presentationModel]
     */
    fun getPreferredIconSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): DpSize

    /**
     * Returns the pre-layout information for the specified parameters.
     */
    fun getPreLayoutInfo(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): CommandButtonPreLayoutInfo

    /**
     * Returns the preferred size of a projected button for the specified parameters.
     */
    fun getPreferredSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonPreLayoutInfo
    ): Size

    /**
     * Returns the layout information for the specified parameters.
     */
    fun getLayoutInfo(
        constraints: Constraints,
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonPreLayoutInfo
    ): CommandButtonLayoutInfo
}