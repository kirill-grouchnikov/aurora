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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.demo.svg.material.mode_edit_black_24dp
import org.pushingpixels.aurora.demo.svg.material.person_outline_black_24dp
import org.pushingpixels.aurora.demo.svg.material.refresh_black_24dp
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowScope

@Composable
fun AuroraWindowScope.ThreadListPanel(modifier: Modifier) {
    AuroraDecorationArea(decorationAreaType = VisorDecorations.Threads) {
        // Resolve the default text style
        val resolvedTextStyle = resolveAuroraDefaults()
        Column(modifier = modifier.fillMaxHeight().auroraBackground()) {
            // This row is part of the integrated title pane, so we need to wrap it in
            // a WindowDraggableArea
            WindowDraggableArea {
                // A row with the searchbox
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val fontSize = resolvedTextStyle.fontSize
                    // Compute a larger font size
                    val largerFontSize = TextUnit(fontSize.value + 1.0f, fontSize.type)
                    // And create our own text style with larger font size
                    val textStyle = resolvedTextStyle.copy(fontSize = largerFontSize)

                    TextFieldStringProjection(
                        contentModel = TextFieldStringContentModel(
                            value = "",
                            onValueChange = { },
                            placeholder = "Search"
                        ),
                        presentationModel = TextFieldPresentationModel(
                            singleLine = true,
                            contentPadding = PaddingValues(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp),
                            textStyle = textStyle
                        )
                    ).project(modifier = Modifier.weight(1.0f))

                    Spacer(modifier = Modifier.width(12.dp).weight(1.0f))

                    CommandButtonProjection(
                        contentModel = Command(text = "Search", icon = mode_edit_black_24dp(),
                            action = { println("Search!") }),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.SmallFitToIcon,
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                        )
                    ).project()
                }
            }

            ThreadListPanelHeaderSeparator()

            // Header
            Row(modifier = Modifier.fillMaxWidth()) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        icon = refresh_black_24dp(), text = "Inbox"
                    ), presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        textStyle = resolvedTextStyle.copy(fontWeight = FontWeight.Bold),
                        horizontalGapScaleFactor = 2.0f
                    )
                ).project()
            }

            ThreadListPanelHeaderSeparator()

            val lazyListState = rememberLazyListState()
            val stateSelection = mutableStateOf(ThreadsList[3])
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .padding(end = ScrollBarSizingConstants.DefaultScrollBarThickness + 8.dp),
                    state = lazyListState
                ) {
                    itemsIndexed(ThreadsList) { _, item ->
                        Column(modifier = Modifier.fillMaxWidth()) {
                            AuroraBoxWithHighlights(
                                modifier = Modifier.fillMaxWidth(),
                                selected = (stateSelection.value == item),
                                onClick = { stateSelection.value = item },
                                sides = Sides.ClosedRectangle,
                                content = {
                                    ThreadListEntry(item)
                                })
                            HorizontalSeparatorProjection().project(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
                AuroraVerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight()
                        .padding(end = 4.dp, top = 4.dp, bottom = 4.dp),
                    adapter = rememberScrollbarAdapter(scrollState = lazyListState)
                )
            }
        }
    }
}

@Composable
private fun ThreadListEntry(threadInfo: ThreadInfo) {
    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveAuroraDefaults()
    val fontSize = resolvedTextStyle.fontSize
    // Compute a larger font size
    val largerFontSize = TextUnit(fontSize.value + 1.0f, fontSize.type)

    Column(modifier = Modifier.padding(12.dp)) {
        Row {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = threadInfo.from, icon = person_outline_black_24dp()
                ),
                presentationModel = LabelPresentationModel(
                    inheritStateFromParent = true,
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    horizontalGapScaleFactor = 2.0f,
                    horizontalAlignment = HorizontalAlignment.Leading,
                    textStyle = resolvedTextStyle.copy(fontSize = largerFontSize, fontWeight = FontWeight.Bold)
                )
            ).project(modifier = Modifier.weight(1.0f))
            LabelProjection(
                contentModel = LabelContentModel(text = "${threadInfo.time}"),
                presentationModel = LabelPresentationModel(
                    inheritStateFromParent = true,
                    contentPadding = PaddingValues(start = 4.dp, end = 0.dp, top = 2.dp, bottom = 2.dp)
                )
            ).project()
        }
        Row {
            LabelProjection(
                contentModel = LabelContentModel(text = threadInfo.title),
                presentationModel = LabelPresentationModel(
                    inheritStateFromParent = true,
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                    horizontalAlignment = HorizontalAlignment.Leading,
                    textStyle = resolvedTextStyle.copy(fontWeight = FontWeight.Bold)
                )
            ).project(modifier = Modifier.weight(1.0f))
        }
        Row {
            LabelProjection(
                contentModel = LabelContentModel(text = threadInfo.summary),
                presentationModel = LabelPresentationModel(
                    inheritStateFromParent = true,
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                    horizontalAlignment = HorizontalAlignment.Leading,
                    textMaxLines = 1,
                    textOverflow = TextOverflow.Ellipsis
                )
            ).project(modifier = Modifier.weight(1.0f))
            if (threadInfo.unread > 0) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "${threadInfo.unread}"),
                    presentationModel = LabelPresentationModel(
                        inheritStateFromParent = true,
                        contentPadding = PaddingValues(start = 4.dp, end = 0.dp, top = 2.dp, bottom = 2.dp)
                    )
                ).project()
            }
        }
    }
}

@Composable
private fun ThreadListPanelHeaderSeparator() {
    val separatorTokens = AuroraSkin.colors.getNeutralContainerTokens(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Separator,
    )

    Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
        drawLine(
            color = separatorTokens.containerOutline.withAlpha(0.5f),
            start = Offset(0.0f, 0.5f),
            end = Offset(size.width, 0.5f),
            strokeWidth = 1.0f
        )
    }
}

private data class ThreadInfo(
    val from: String,
    val time: String,
    val title: String,
    val summary: String,
    val unread: Int
)

private val ThreadsList = listOf(
    ThreadInfo(
        from = "Bob Macpearson", time = "5:50pm", title = "Welcome Natalie",
        summary = "Everybody please welcome our new team member", unread = 3
    ),
    ThreadInfo(
        from = "Eve Stephens", time = "5:35pm", title = "All hands rescheduled",
        summary = "We will send another update shortly", unread = -1
    ),
    ThreadInfo(
        from = "Randy White", time = "5:33pm", title = "Revenue target 'Q4",
        summary = "See the attached spreadsheet for the latest", unread = 5
    ),
    ThreadInfo(
        from = "Reception desk", time = "4:15pm", title = "Keys found",
        summary = "If you lost your keys stop by the reception desk", unread = 2
    ),
    ThreadInfo(
        from = "Amanda Pinewood", time = "4:00pm", title = "Samantha's baby!!!",
        summary = "It's a girl! And now the first photos", unread = 12
    ),
    ThreadInfo(
        from = "Jackson Grady", time = "11:58am", title = "Lunch meeting",
        summary = "Apologies for last minute reschedule", unread = 1
    ),
    ThreadInfo(
        from = "Robert Malone", time = "11:30am", title = "Project status update",
        summary = "Based on what we were talking about yesterday", unread = -1
    ),
    ThreadInfo(
        from = "Reception desk", time = "10:02am", title = "Car alarm",
        summary = "If you have a white Accord, its alarm is on", unread = 2
    ),
    ThreadInfo(
        from = "Jessica Fletcher", time = "10:01am", title = "Your flight",
        summary = "There has been a slight change to your flight", unread = 2
    ),
    ThreadInfo(
        from = "Josh Mandin", time = "9:29am", title = "Printer broken",
        summary = "We are aware of the issue with the main office printer", unread = -1
    )
)