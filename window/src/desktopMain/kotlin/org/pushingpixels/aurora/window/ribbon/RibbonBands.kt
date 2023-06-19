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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.ribbon.AbstractRibbonBand
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
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

@OptIn(AuroraInternalApi::class)
@Composable
private fun RibbonBand(band: AbstractRibbonBand) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current

    Column(modifier = Modifier.width(120.dp).fillMaxHeight()) {
        Spacer(modifier = Modifier.weight(1.0f))

        Row(modifier = Modifier.fillMaxWidth().padding(RibbonBandTitleAreaPadding),
            verticalAlignment = Alignment.CenterVertically) {
            LabelProjection(
                contentModel = LabelContentModel(text = band.title),
                presentationModel = LabelPresentationModel(
                    contentPadding = RibbonBandTitleLabelPadding,
                    horizontalAlignment = HorizontalAlignment.Center,
                    textMaxLines = 1,
                )
            ).project(modifier = Modifier.weight(1.0f))

            if (band.expandCommand != null) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        icon = getEndwardDoubleArrowIcon(
                            decorationAreaType = decorationAreaType,
                            skinColors = colors,
                            colorSchemeBundle = null,
                            density = density
                        ),
                        action = { band.expandCommand?.action?.invoke() },
                        actionRichTooltip = band.expandCommand?.actionRichTooltip
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.SmallFitToIcon,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        contentPadding = RibbonBandExpandButtonContentPadding,
                        sides = Sides.ClosedRectangle,
                        actionKeyTip = band.expandCommandKeyTip
                    )
                ).project()
            }
        }
    }
}

private val RibbonBandTitleAreaPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
private val RibbonBandTitleLabelPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
private val RibbonBandExpandButtonContentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp)
