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
package org.pushingpixels.aurora.demo.titlepane.mail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.HorizontalAlignment
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.resolveAuroraDefaults
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneButton

@Composable
fun AuroraWindowScope.DestinationsPanel(modifier: Modifier) {
    // Resolve the default text style
    val resolvedTextStyle = resolveAuroraDefaults()
    AuroraDecorationArea(decorationAreaType = VisorDecorations.Destinations) {
        Column(modifier = modifier.fillMaxHeight().auroraBackground()) {
            // This row is part of the integrated title pane, so we need to wrap it in
            // a WindowDraggableArea
            WindowDraggableArea {
                Row(
                    modifier = Modifier.height(40.dp).fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Use Aurora-provided title pane button composable for consistent visuals
                    // of the refresh button with the title pane control buttons
                    AuroraWindowTitlePaneButton(
                        titlePaneCommand = Command(
                            icon = refresh_black_24dp(),
                            text = "",
                            action = { println("Refresh!") })
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "MAIL"),
                    presentationModel = LabelPresentationModel(
                        textStyle = resolvedTextStyle.copy(fontWeight = FontWeight.Bold))
                ).project()
            }

            val lazyListState = rememberLazyListState()
            val stateSelection = mutableStateOf(DestinationsList[0])
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                // Note that here we don't use a vertical scroll bar, assuming that all destinations fit
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 2.dp), state = lazyListState
                ) {
                    itemsIndexed(DestinationsList) { _, item ->
                        AuroraBoxWithHighlights(modifier = Modifier.fillMaxWidth().height(32.dp),
                            selected = (stateSelection.value == item),
                            onClick = { stateSelection.value = item },
                            sides = Sides.ClosedRectangle,
                            content = { colorTokens ->
                                Row(modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    LabelProjection(
                                        contentModel = LabelContentModel(
                                            text = item.title, icon = item.icon
                                        ),
                                        presentationModel = LabelPresentationModel(
                                            inheritStateFromParent = true,
                                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                                            horizontalGapScaleFactor = 2.0f,
                                            horizontalAlignment = HorizontalAlignment.Leading,
                                            contentPadding = PaddingValues.Zero
                                        )
                                    ).project(modifier = Modifier.weight(1.0f))
                                    if (item.unread > 0) {
                                        LabelProjection(
                                            contentModel = LabelContentModel(text = "${item.unread}"),
                                            presentationModel = LabelPresentationModel(inheritStateFromParent = true,
                                                contentPadding = PaddingValues.Zero)
                                        ).project(modifier =
                                            Modifier
                                                .border(
                                                    width = 0.dp,
                                                    color = colorTokens.containerOutlineVariant,
                                                    shape = CircleShape)
                                                .background(color = colorTokens.containerSurfaceLow,
                                                    shape = CircleShape)
                                                .padding(vertical = 2.dp, horizontal = 10.dp)
                                        )
                                    }
                                }
                            })
                    }
                }
            }
        }
    }
}

private data class DestinationInfo(
    val icon: Painter, val title: String, val unread: Int
)

private val DestinationsList = listOf(
    DestinationInfo(inbox_black_24dp(), "Inbox", 6),
    DestinationInfo(send_black_24dp(), "Sent", 3),
    DestinationInfo(watch_later_black_24dp(), "Send later", 5),
    DestinationInfo(drafts_black_24dp(), "Drafts", -1),
    DestinationInfo(star_border_black_24dp(), "Starred", -1),
    DestinationInfo(delete_black_24dp(), "Trash", -1)
)
