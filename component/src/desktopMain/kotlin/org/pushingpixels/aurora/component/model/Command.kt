/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.component.layout.*
import org.pushingpixels.aurora.icon.AuroraIcon

interface CommandActionPreview {
    /**
     * Invoked when a command preview has been activated.
     *
     * @param command Command for which the preview has been activated.
     */
    fun onCommandPreviewActivated(command: Command)

    /**
     * Invoked when a command preview has been canceled.
     *
     * @param command Command for which the preview has been canceled.
     */
    fun onCommandPreviewCanceled(command: Command)
}

data class Command(
    val text: String,
    val extraText: String? = null,
    val iconFactory: AuroraIcon.Factory? = null,
    val disabledIconFactory: AuroraIcon.Factory? = null,
    val action: (() -> Unit)? = null,
    val actionPreview: CommandActionPreview? = null,
    val isActionEnabled: Boolean = true,
    val isActionToggle: Boolean = false,
    val isActionToggleSelected: Boolean = false,
    val onTriggerActionToggleSelectedChange: ((Boolean) -> Unit)? = null,
    val secondaryContentModel: CommandMenuContentModel? = null,
    val isSecondaryEnabled: Boolean = true
)

data class CommandGroup(
    val title: String? = null,
    val commands: List<Command>
)

data class CommandMenuContentModel(val groups: List<CommandGroup>) {
    constructor(group: CommandGroup) : this(listOf(group))
}

enum class TextClick {
    ACTION, POPUP
}

// TODO - remove in favor of using Arrangement.Horizontal
enum class HorizontalAlignment(var arrangement: Arrangement.Horizontal) {
    LEADING(Arrangement.Start), CENTER(Arrangement.Center), TRAILING(Arrangement.End)
}

/**
 * Enumerates the available command button kinds.
 */
enum class CommandButtonKind(val hasAction: Boolean, val hasPopup: Boolean) {
    /**
     * Command button that has only action area.
     */
    ACTION_ONLY(true, false),

    /**
     * Command button that has only popup area.
     */
    POPUP_ONLY(false, true),

    /**
     * Command button that has both action and popup areas, with the main
     * text click activating the action.
     */
    ACTION_AND_POPUP_MAIN_ACTION(true, true),

    /**
     * Command button that has both action and popup areas, with the main
     * text click activating the popup.
     */
    ACTION_AND_POPUP_MAIN_POPUP(true, true);
}

/**
 * Presentation state for command buttons. This class provides a number of core
 * companion presentation states. In addition, it also allows creating additional
 * custom states by using the constructor and implementing the relevant abstract
 * methods.
 */
abstract class CommandButtonPresentationState(val displayName: String) {
    abstract fun createLayoutManager(
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        resourceLoader: Font.ResourceLoader
    ): CommandButtonLayoutManager

    override fun toString(): String {
        return displayName
    }

    companion object {
        /** Big state */
        val BIG: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Big") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    resourceLoader: Font.ResourceLoader
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerBig(
                        layoutDirection,
                        density,
                        textStyle,
                        resourceLoader
                    )
                }
            }

        /** Medium state */
        val MEDIUM: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Medium") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    resourceLoader: Font.ResourceLoader
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerMedium(
                        layoutDirection,
                        density,
                        textStyle,
                        resourceLoader
                    )
                }
            }

        /** Small state */
        val SMALL: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Small") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    resourceLoader: Font.ResourceLoader
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerSmall(
                        layoutDirection,
                        density,
                        textStyle,
                        resourceLoader
                    )
                }
            }

        /** Tile state */
        val TILE: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Tile") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    resourceLoader: Font.ResourceLoader
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerTile(
                        layoutDirection,
                        density,
                        textStyle,
                        resourceLoader
                    )
                }
            }
    }
}

