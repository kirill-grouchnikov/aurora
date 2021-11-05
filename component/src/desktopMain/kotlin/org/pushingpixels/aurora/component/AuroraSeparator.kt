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
package org.pushingpixels.aurora.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.SeparatorContentModel
import org.pushingpixels.aurora.component.model.SeparatorPresentationModel
import org.pushingpixels.aurora.component.model.SeparatorSizingConstants
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.ColorSchemeAssociationKind
import org.pushingpixels.aurora.theming.ComponentState

@Composable
internal fun AuroraVerticalSeparator(
    modifier: Modifier,
    contentModel: SeparatorContentModel,
    presentationModel: SeparatorPresentationModel
) {
    val separatorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Separator,
        componentState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    )

    Canvas(modifier.width(SeparatorSizingConstants.Thickness)) {
        val height = size.height

        if (height > 0.0f) {
            val primaryBrush = Brush.verticalGradient(
                0.0f to separatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                presentationModel.startGradientAmount.toPx() / height to separatorScheme.separatorPrimaryColor,
                (height - presentationModel.endGradientAmount.toPx()) / height to separatorScheme.separatorPrimaryColor,
                1.0f to separatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                startY = 0.0f,
                endY = height,
                tileMode = TileMode.Repeated
            )
            val secondaryBrush = Brush.verticalGradient(
                0.0f to separatorScheme.separatorSecondaryColor.withAlpha(0.0f),
                presentationModel.startGradientAmount.toPx() / height to separatorScheme.separatorSecondaryColor,
                (height - presentationModel.endGradientAmount.toPx()) / height to separatorScheme.separatorSecondaryColor,
                1.0f to separatorScheme.separatorSecondaryColor.withAlpha(0.0f),
                startY = 0.0f,
                endY = height,
                tileMode = TileMode.Repeated
            )

            drawLine(
                brush = primaryBrush,
                start = Offset(0.5f, 0.0f),
                end = Offset(0.5f, height),
                strokeWidth = 1.0f
            )
            drawLine(
                brush = secondaryBrush,
                start = Offset(1.5f, 0.0f),
                end = Offset(1.5f, height),
                strokeWidth = 1.0f
            )
        }
    }
}

@Composable
internal fun AuroraHorizontalSeparator(
    modifier: Modifier,
    contentModel: SeparatorContentModel,
    presentationModel: SeparatorPresentationModel
) {
    val separatorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Separator,
        componentState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    )

    Canvas(modifier.height(SeparatorSizingConstants.Thickness)) {
        val width = size.width

        if (width > 0.0f) {
            val primaryBrush = Brush.horizontalGradient(
                0.0f to separatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                presentationModel.startGradientAmount.toPx() / width to separatorScheme.separatorPrimaryColor,
                (width - presentationModel.endGradientAmount.toPx()) / width to separatorScheme.separatorPrimaryColor,
                1.0f to separatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                startX = 0.0f,
                endX = width,
                tileMode = TileMode.Repeated
            )
            val secondaryBrush = Brush.horizontalGradient(
                0.0f to separatorScheme.separatorSecondaryColor.withAlpha(0.0f),
                presentationModel.startGradientAmount.toPx() / width to separatorScheme.separatorSecondaryColor,
                (width - presentationModel.endGradientAmount.toPx()) / width to separatorScheme.separatorSecondaryColor,
                1.0f to separatorScheme.separatorSecondaryColor.withAlpha(0.0f),
                startX = 0.0f,
                endX = width,
                tileMode = TileMode.Repeated
            )

            drawLine(
                brush = primaryBrush,
                start = Offset(0.0f, 0.5f),
                end = Offset(width, 0.5f),
                strokeWidth = 1.0f
            )
            drawLine(
                brush = secondaryBrush,
                start = Offset(0.0f, 1.5f),
                end = Offset(width, 1.5f),
                strokeWidth = 1.0f
            )
        }
    }
}