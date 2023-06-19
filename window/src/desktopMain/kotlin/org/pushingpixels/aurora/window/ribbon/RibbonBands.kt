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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.ribbon.AbstractRibbonBand
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.LocalTextStyle
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonBands(ribbon: Ribbon) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val selectedTask = ribbon.selectedTask
    val bands = selectedTask.bands

    // The height of ribbon band control panel is computed based on the preferred height of a command
    // button in BIG state.
    val commandForSizing = Command(text = "Text", action = {})
    val presentationForSizing = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Big,
        forceAllocateSpaceForIcon = true
    )
    val sizingLayoutManager = presentationForSizing.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )
    val sizingPreLayoutInfo =
        sizingLayoutManager.getPreLayoutInfo(
            commandForSizing,
            presentationForSizing
        )
    val bandContentHeight = sizingLayoutManager.getPreferredSize(
        commandForSizing, presentationForSizing, sizingPreLayoutInfo
    ).height
    val bandTitleHeight = getLabelPreferredHeight(
        contentModel = LabelContentModel(text = "Title"),
        presentationModel = LabelPresentationModel(contentPadding = RibbonBandTitleLabelPadding, textMaxLines = 1),
        resolvedTextStyle = resolvedTextStyle,
        layoutDirection = layoutDirection,
        density = density,
        fontFamilyResolver = fontFamilyResolver,
        availableWidth = Float.MAX_VALUE
    )
    val fullHeightDp = ((bandContentHeight + bandTitleHeight) / density.density).dp

    AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
        Row(modifier = Modifier.fillMaxWidth().height(fullHeightDp).auroraBackground()) {
            for (band in bands) {
                RibbonBand(band = band)
            }
        }
    }
}

@Composable
private fun RibbonBand(band: AbstractRibbonBand) {
    Box(modifier = Modifier.width(60.dp).fillMaxHeight())
}

private val RibbonBandTitleLabelPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
