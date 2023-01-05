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
package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel

fun getLabelPreferredHeight(
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel,
    resolvedTextStyle: TextStyle,
    layoutDirection: LayoutDirection,
    density: Density,
    fontFamilyResolver: FontFamily.Resolver,
    availableWidth: Float
): Float {
    // Account for horizontal content padding
    var widthForText = availableWidth -
            (presentationModel.contentPadding.calculateLeftPadding(layoutDirection) +
                    presentationModel.contentPadding.calculateRightPadding(layoutDirection)).value *
            density.density
    if (contentModel.icon != null) {
        // Account for icon width
        widthForText -= presentationModel.iconDimension.width.value * density.density
        // and gap between icon and text
        widthForText -=
            (presentationModel.iconTextGap * presentationModel.horizontalGapScaleFactor).value *
                    density.density
    }

    val paragraph = Paragraph(
        text = contentModel.text, style = resolvedTextStyle,
        constraints = Constraints(maxWidth = widthForText.toInt()),
        density = density, maxLines = presentationModel.textMaxLines,
        fontFamilyResolver = fontFamilyResolver
    )

    // Account for vertical content padding
    return paragraph.height +
            (presentationModel.contentPadding.calculateTopPadding() + presentationModel.contentPadding.calculateBottomPadding()).value *
            density.density
}