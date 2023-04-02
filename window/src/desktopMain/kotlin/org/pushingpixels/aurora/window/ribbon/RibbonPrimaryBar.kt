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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.AuroraHorizontallyScrollableBox
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonPrimaryBar(ribbon: Ribbon) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val taskButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Medium,
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
        sides = Sides(openSides = hashSetOf(Side.Bottom)),
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
    )
    val taskButtonLayoutManager = taskButtonPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val forSizing = Command(text = ribbon.tasks[0].title, action = {})
    val taskButtonBarHeight = taskButtonLayoutManager.getPreferredSize(
        command = forSizing,
        presentationModel = taskButtonPresentationModel,
        preLayoutInfo = taskButtonLayoutManager.getPreLayoutInfo(
            forSizing,
            taskButtonPresentationModel
        )
    ).height.toInt()

    val taskButtonRowScrollState: ScrollState = rememberScrollState(0)

    val ribbonTaskCommands = ribbon.tasks.map { task ->
        Command(text = task.title,
            icon = null,
            isActionToggle = true,
            isActionToggleSelected = (task == ribbon.selectedTask),
            onTriggerActionToggleSelectedChange = {
                ribbon.onTaskClick(task)
            }
        )
    }

    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
        Row(modifier = Modifier.auroraBackground().fillMaxWidth().padding(all = 4.dp)) {
            ribbon.applicationMenuCommandButtonProjection?.project()

            Spacer(modifier = Modifier.width(8.dp))

            AuroraHorizontallyScrollableBox(
                modifier = Modifier,
                height = (taskButtonBarHeight / density.density).dp,
                contentWidth = {
                    ribbonTaskCommands.sumOf { taskCommand ->
                        val commandPreLayoutInfo =
                            taskButtonLayoutManager.getPreLayoutInfo(
                                taskCommand,
                                taskButtonPresentationModel
                            )
                        val commandSize = taskButtonLayoutManager.getPreferredSize(
                            taskCommand, taskButtonPresentationModel, commandPreLayoutInfo
                        )
                        commandSize.width.toDouble()
                    }.toInt()
                },
                horizontalScrollState = taskButtonRowScrollState,
                scrollAmount = 12.dp,
                content = {
                    for (taskCommand in ribbonTaskCommands) {
                        CommandButtonProjection(
                            contentModel = taskCommand,
                            presentationModel = taskButtonPresentationModel
                        ).project()
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                for (anchored in ribbon.anchoredCommands) {
                    anchored.reproject(modifier = Modifier,
                        primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                            popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd
                        ),
                        actionInteractionSource = remember { MutableInteractionSource() },
                        popupInteractionSource = remember { MutableInteractionSource() })
                }
            }
        }
    }
}

