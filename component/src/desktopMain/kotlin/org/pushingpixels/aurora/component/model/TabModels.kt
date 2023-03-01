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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.TabContentSeparatorKind
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle

object TabConstants {
    val LeadingMargin = 2.dp
    val TrailingMargin = 2.dp
    val InterTabMargin = 2.dp
    val TopPadding = 4.dp
    val DoubleSeparatorGap = 2.dp
}

data class TabContentModel(
    val text: String,
    val icon: Painter? = null,
    val isEnabled: Boolean = true
) : ContentModel

data class TabsContentModel(
    val tabs: List<TabContentModel>,
    val selectedTabIndex: Int,
    val onTriggerTabSelected: (Int) -> Unit,
) : ContentModel

data class TabsPresentationModel(
    val colorSchemeBundle: AuroraColorSchemeBundle? = null,
    val leadingMargin: Dp = TabConstants.LeadingMargin,
    val trailingMargin: Dp = TabConstants.TrailingMargin,
    val interTabMargin: Dp = TabConstants.InterTabMargin,
    val topPadding: Dp = TabConstants.TopPadding,
    val contentSeparatorKind: TabContentSeparatorKind = TabContentSeparatorKind.Double,
    val tabPresentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium,
    val tabBackgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
    val tabIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val tabIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val tabIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val tabContentPadding: PaddingValues = CommandButtonSizingConstants.WideButtonContentPadding
) : PresentationModel
