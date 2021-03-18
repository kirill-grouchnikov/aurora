package org.pushingpixels.aurora.component.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel

/**
 * Definition of a layout manager for command buttons.
 *
 * @author Kirill Grouchnikov
 */
interface CommandButtonLayoutManager: MeasureScope {
    /**
     * Enumerates the available values for separator orientations.
     */
    enum class CommandButtonSeparatorOrientation {
        /**
         * Vertical separator orientation.
         */
        VERTICAL,

        /**
         * Horizontal separator orientation.
         */
        HORIZONTAL
    }

    /**
     * Layout information on a single line of text.
     *
     * @param text Text itself
     * @param textRect The text rectangle.
     */
    data class TextLayoutInfo(val text: String, var textRect: Rect)

    /**
     * Layout information on different visual parts of a single command button.
     *
     * @param actionClickArea The action area. A mouse click in this area will trigger the action
     * lambda set as [Command.action].
     * @param popupClickArea The popup area. A mouse click in this area will show the popup content
     * associated with the command button.
     * @param separatorArea The separator area. If it's not empty, the command button will show a
     * separator between [.actionClickArea] and
     * [.popupClickArea] on mouse rollover.
     * @param iconRect Rectangle for the command button icon.
     * @param textLayoutInfoList Layout information for the command button text (that can span
     * multiple lines).
     * @param extraTextLayoutInfoList Layout information for the command button extra text
     * (that can span multiple lines).
     * @param popupActionRect Rectangle for the icon associated with the [.popupClickArea].
     * This icon is an arrow indicating that the command button has a popup area.
     * @param isTextInActionArea Indication whether the command button text (rectangles in
     * [.textLayoutInfoList]) belongs in the action area.
     */
    data class CommandButtonLayoutInfo(
        val fullSize: Size,
        val actionClickArea: Rect,
        val popupClickArea: Rect,
        val separatorArea: Rect?,
        val separatorOrientation: CommandButtonSeparatorOrientation?,
        val iconRect: Rect,
        val textLayoutInfoList: List<TextLayoutInfo>,
        val extraTextLayoutInfoList: List<TextLayoutInfo>?,
        val popupActionRect: Rect,
        val isTextInActionArea: Boolean
    )

    /**
     * Returns the preferred icon size of the specified command button when it uses
     * this layout manager.
     *
     * @param commandButton Command button.
     * @return The preferred icon size of the specified command button when it uses
     * this layout manager.
     */
    fun getPreferredIconSize(): Dp

    /**
     * Returns the layout information for the specified command button.
     *
     * @param commandButton Command button.
     * @return The layout information for the specified command button.
     */
    fun getLayoutInfo(
        command: Command,
        presentationModel: CommandButtonPresentationModel,
        paddingValues: PaddingValues
    ): CommandButtonLayoutInfo
}