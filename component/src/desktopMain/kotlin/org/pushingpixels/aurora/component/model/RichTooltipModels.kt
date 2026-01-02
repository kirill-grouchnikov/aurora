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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object RichTooltipSizingConstants {
    val MaxWidth = 264.dp
    val SmallIconSize = DpSize(16.dp, 16.dp)
    val LargeIconSize = DpSize(32.dp, 32.dp)
    val DefaultContentPadding = PaddingValues(6.dp)
    val HorizontalContentLayoutGap = 6.dp
    val VerticalContentLayoutGap = 6.dp
}

/**
 * Rich tooltip. In its most basic form, the rich tooltip has a title and one
 * (possible multiline) description text:
 *
 * <pre>
 * +--------------------------------+
 * | Title                          |
 * |        Some description text   |
 * +--------------------------------+
 * </pre>
 *
 *
 * The [.descriptionSections] can be used to add multiple
 * sections to the description:
 *
 * <pre>
 * +--------------------------------+
 * | Title                          |
 * |        First multiline         |
 * |        description section     |
 * |                                |
 * |        Second multiline        |
 * |        description section     |
 * |                                |
 * |        Third multiline         |
 * |        description section     |
 * +--------------------------------+
 * </pre>
 *
 * The [.mainIcon] can be used to place an image below
 * the title and to the left of the description sections:
 *
 * <pre>
 * +--------------------------------+
 * | Title                          |
 * | *******  First multiline       |
 * | *image*  description section   |
 * | *******                        |
 * |          Second multiline      |
 * |          description section   |
 * +--------------------------------+
 * </pre>
 *
 * The [.footerSections] can be used to add (possibly) multiple
 * footer sections that will be shown below a horizontal separator:
 *
 * <pre>
 * +--------------------------------+
 * | Title                          |
 * |        First multiline         |
 * |        description section     |
 * |                                |
 * |        Second multiline        |
 * |        description section     |
 * |--------------------------------|
 * | A multiline footer section     |
 * | placed below a separator       |
 * +--------------------------------+
 * </pre>
 *
 * The [.footerIcon]  can be used to place an image to
 * the left of the footer sections:
 *
 * <pre>
 * +--------------------------------+
 * | Title                          |
 * |        First multiline         |
 * |        description section     |
 * |                                |
 * |        Second multiline        |
 * |        description section     |
 * |--------------------------------|
 * | *******  A multiline           |
 * | *image*  footer section        |
 * | *******                        |
 * +--------------------------------+
 * </pre>
 *
 * Here is a fully fledged rich tooltip that shows all these parts present:
 *
 * <pre>
 * +--------------------------------+
 * | Title                          |
 * | *******  First multiline       |
 * | *image*  description section   |
 * | *******                        |
 * |          Second multiline      |
 * |          description section   |
 * |--------------------------------|
 * | *******  First multiline       |
 * | *image*  footer section        |
 * | *******                        |
 * |          Second multiline      |
 * |          footer section        |
 * +--------------------------------+
 * </pre>
 */
data class RichTooltip(
    val title: String,
    val mainIcon: Painter? = null,
    val descriptionSections: List<String>? = null,
    val footerIcon: Painter? = null,
    val footerSections: List<String>? = null
)

data class RichTooltipPresentationModel(
    val mainIconSize: DpSize = RichTooltipSizingConstants.LargeIconSize,
    val footerIconSize: DpSize = RichTooltipSizingConstants.SmallIconSize,
    val contentPadding: PaddingValues = RichTooltipSizingConstants.DefaultContentPadding
) : PresentationModel
