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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.IconProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.resolveAuroraDefaults
import org.pushingpixels.aurora.window.AuroraWindowScope

@Composable
fun AuroraWindowScope.ThreadPanel(modifier: Modifier) {
    Column(modifier = modifier.fillMaxHeight().auroraBackground()) {
        val fillTokens = AuroraSkin.colors.getMutedContainerTokens(AuroraSkin.decorationAreaType)
        val iconColorFilter = ColorFilter.tint(color = fillTokens.onContainerVariant)

        // Resolve the default text style
        val resolvedTextStyle = resolveAuroraDefaults()

        // This row is part of the integrated title pane, so we need to wrap it in
        // a WindowDraggableArea
        WindowDraggableArea() {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                IconProjection(
                    contentModel = IconContentModel(icon = close_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(22.dp, 22.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()
                IconProjection(
                    contentModel = IconContentModel(icon = view_list_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(22.dp, 22.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()
                IconProjection(
                    contentModel = IconContentModel(icon = alarm_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(22.dp, 22.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()
                IconProjection(
                    contentModel = IconContentModel(icon = archive_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(22.dp, 22.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()
                IconProjection(
                    contentModel = IconContentModel(icon = delete_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(22.dp, 22.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()
            }
        }

        // Header
        Row(modifier = Modifier.fillMaxWidth()) {
            val fontSize = resolvedTextStyle.fontSize
            // Compute a larger font size
            val largerFontSize = TextUnit(fontSize.value + 6.0f, fontSize.type)
            // And create our own text style with larger font size and bold weight
            val textStyle = resolvedTextStyle.copy(fontSize = largerFontSize, fontWeight = FontWeight.Bold)

            LabelProjection(
                contentModel = LabelContentModel(text = "Keys found"),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    textStyle = textStyle
                )
            ).project(modifier = Modifier.padding(vertical = 12.dp))
        }

        CollapsedMessagePanel(
            from = "Reception desk",
            title = "If you lost your keys stop by the reception desk",
            timestamp = "10:25am"
        )

        CollapsedMessagePanel(
            from = "Bryce Dunwood",
            title = "I think those might be Grayson's",
            timestamp = "10:28am"
        )

        ExpandedMessagePanel(
            from = "Reception desk",
            timestamp = "Today, 4:15pm",
            to = "Bryce Dunwood, Grayson Flay",
            message = """
                Thanks, Bryce.
                
                Grayson, can you check if you still have your keys?
                
                It's a silver keychain with five keys and a small elephant. If these are yours, please stop by. We'll be here until six today.
                
                Morgan from reception.
                """.trimIndent(),
            iconColorFilter = iconColorFilter
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            CommandButtonProjection(
                contentModel = Command(text = "Reply", icon = reply_black_24dp(), action = { println("Reply!") }),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                )
            ).project()

            CommandButtonProjection(
                contentModel = Command(text = "Forward", icon = forward_black_24dp(), action = { println("Forward!") }),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                )
            ).project()
        }
    }
}

@Composable
private fun CollapsedMessagePanel(from: String, title: String, timestamp: String) {
    // Resolve the default text style
    val resolvedTextStyle = resolveAuroraDefaults()

    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 2.dp)) {
        val colorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = colorTokens.containerSurfaceHighest,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 0.dp, color = colorTokens.containerOutlineVariant)
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LabelProjection(
                contentModel = LabelContentModel(text = from),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    textStyle = resolvedTextStyle.copy(fontWeight = FontWeight.Bold)
                )
            ).project()

            LabelProjection(
                contentModel = LabelContentModel(text = title),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(horizontal = 2.dp),
                    textOverflow = TextOverflow.Ellipsis,
                    textMaxLines = 1,
                    horizontalAlignment = HorizontalAlignment.Leading
                )
            ).project(modifier = Modifier.weight(1.0f))

            // Resolve the default text style to get the default font size
            val resolvedTextStyle = resolveAuroraDefaults()
            val fontSize = resolvedTextStyle.fontSize
            // Compute a smaller font size
            val smallerFontSize = TextUnit(fontSize.value - 2.0f, fontSize.type)
            LabelProjection(
                contentModel = LabelContentModel(text = timestamp),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    textStyle = resolvedTextStyle.copy(fontSize = smallerFontSize)
                )
            ).project()
        }
    }
}

@Composable
private fun ExpandedMessagePanel(
    from: String, timestamp: String, to: String, message: String,
    iconColorFilter: ColorFilter
) {
    // Resolve the default text style
    val resolvedTextStyle = resolveAuroraDefaults()

    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 2.dp)) {
        val colorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = colorTokens.containerSurfaceHighest,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(width = 0.dp, color = colorTokens.containerOutlineVariant)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                LabelProjection(
                    contentModel = LabelContentModel(text = from),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        textStyle = resolvedTextStyle.copy(fontWeight = FontWeight.Bold)
                    )
                ).project()

                Spacer(modifier = Modifier.weight(1.0f))

                // Resolve the default text style to get the default font size
                val resolvedTextStyle = resolveAuroraDefaults()
                val fontSize = resolvedTextStyle.fontSize
                // Compute a smaller font size
                val smallerFontSize = TextUnit(fontSize.value - 2.0f, fontSize.type)
                LabelProjection(
                    contentModel = LabelContentModel(text = timestamp),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        textStyle = resolvedTextStyle.copy(fontSize = smallerFontSize)
                    )
                ).project()

                IconProjection(
                    contentModel = IconContentModel(icon = reply_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(16.dp, 16.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()

                Spacer(modifier = Modifier.width(12.dp))

                IconProjection(
                    contentModel = IconContentModel(icon = more_horiz_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(16.dp, 16.dp),
                        iconColorFilter = iconColorFilter
                    )
                ).project()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "To: $to"),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                        horizontalAlignment = HorizontalAlignment.Leading
                    )
                ).project(modifier = Modifier.fillMaxWidth())
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(text = message),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 24.dp),
                        horizontalAlignment = HorizontalAlignment.Leading
                    )
                ).project(modifier = Modifier.fillMaxWidth())
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val historyColor = Color(red = 32, green = 96, blue = 148)

                IconProjection(
                    contentModel = IconContentModel(icon = refresh_black_24dp()),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(16.dp, 16.dp),
                        iconColorFilter = ColorFilter.tint(color = historyColor)
                    )
                ).project()

                LabelProjection(
                    contentModel = LabelContentModel(text = "Show History"),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        horizontalAlignment = HorizontalAlignment.Leading,
                        textStyle = resolvedTextStyle.copy(color = historyColor)
                    )
                ).project()
            }
        }
    }
}