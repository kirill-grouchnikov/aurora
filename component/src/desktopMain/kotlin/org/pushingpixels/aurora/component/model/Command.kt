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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.layout.*

interface CommandActionPreview {
    /**
     * Invoked when a command preview has been activated.
     *
     * @param command Command for which the preview has been activated.
     */
    fun onCommandPreviewActivated(command: BaseCommand)

    /**
     * Invoked when a command preview has been canceled.
     *
     * @param command Command for which the preview has been canceled.
     */
    fun onCommandPreviewCanceled(command: BaseCommand)
}

interface BaseCommand: ContentModel {
    val text: String
    val extraText: String?
    val icon: Painter?
    val action: (() -> Unit)?
    val actionPreview: CommandActionPreview?
    val isActionEnabled: Boolean
    val isActionToggle: Boolean
    val isActionToggleSelected: Boolean
    val actionRichTooltip: RichTooltip?
    val onTriggerActionToggleSelectedChange: ((Boolean) -> Unit)?
    val secondaryContentModel: BaseCommandMenuContentModel?
    val isSecondaryEnabled: Boolean
    val secondaryRichTooltip: RichTooltip?
    val tag: Any?
}

data class Command(
    override val text: String,
    override val extraText: String? = null,
    override val icon: Painter? = null,
    override val action: (() -> Unit)? = null,
    override val actionPreview: CommandActionPreview? = null,
    override val isActionEnabled: Boolean = true,
    override val isActionToggle: Boolean = false,
    override val isActionToggleSelected: Boolean = false,
    override val actionRichTooltip: RichTooltip? = null,
    override val onTriggerActionToggleSelectedChange: ((Boolean) -> Unit)? = null,
    override val secondaryContentModel: CommandMenuContentModel? = null,
    override val isSecondaryEnabled: Boolean = true,
    override val secondaryRichTooltip: RichTooltip? = null,
    override val tag: Any? = null,
) : BaseCommand

data class CommandGroup(
    val title: String? = null,
    val commands: List<Command>
) : ContentModel

interface BaseCommandMenuContentModel {
    val onActivatePopup: (() -> Unit)?
    val onDeactivatePopup: (() -> Unit)?
}

data class CommandMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val groups: List<CommandGroup>,
    val panelContentModel: CommandPanelContentModel? = null,
    val highlightedCommand: Command? = null
): BaseCommandMenuContentModel {
    constructor(
        group: CommandGroup,
        panelContentModel: CommandPanelContentModel? = null,
        highlightedCommand: Command? = null
    ) : this(
        groups = listOf(group),
        panelContentModel = panelContentModel,
        highlightedCommand = highlightedCommand
    )
}

enum class TextClick {
    /** Clicking command text will activate the command action. */
    Action,

    /** Clicking command text will activate the secondary content of the command. */
    Popup
}

enum class HorizontalAlignment {
    Leading {
        override val arrangement = Arrangement.Start
    },

    Trailing {
        override val arrangement = Arrangement.End
    },

    Center {
        override val arrangement: Arrangement.Horizontal = Arrangement.Center
    },

    Fill {
        override val arrangement: Arrangement.Horizontal by lazy {
            throw UnsupportedOperationException()
        }
    };

    internal abstract val arrangement: Arrangement.Horizontal
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
        fontFamilyResolver: FontFamily.Resolver
    ): CommandButtonLayoutManager

    override fun toString(): String {
        return displayName
    }

    companion object {
        /** Big state */
        val Big: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Big") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerBig(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Fit-to-icon + big state */
        val BigFitToIcon: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Big fit to icon") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerBigFitToIcon(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Medium state */
        @OptIn(AuroraInternalApi::class)
        val Medium: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Medium") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerMedium(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Fit-to-icon + medium state */
        @OptIn(AuroraInternalApi::class)
        val MediumFitToIcon: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Medium fit to icon") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerMediumFitToIcon(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Small state */
        val Small: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Small") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerSmall(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Fit-to-icon + small state */
        val SmallFitToIcon: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Small fit to icon") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerSmallFitToIcon(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Tile state */
        val Tile: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Tile") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerTile(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }

        /** Fit-to-icon + tile state */
        val TileFitToIcon: CommandButtonPresentationState =
            object : CommandButtonPresentationState("Tile fit to icon") {
                override fun createLayoutManager(
                    layoutDirection: LayoutDirection,
                    density: Density,
                    textStyle: TextStyle,
                    fontFamilyResolver: FontFamily.Resolver
                ): CommandButtonLayoutManager {
                    return CommandButtonLayoutManagerTileFitToIcon(
                        layoutDirection,
                        density,
                        textStyle,
                        fontFamilyResolver
                    )
                }
            }
    }
}
