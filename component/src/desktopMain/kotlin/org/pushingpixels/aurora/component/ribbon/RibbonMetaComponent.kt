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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.LocalTextStyle
import kotlin.math.max

data class MetaComponentPresentationModel<out P : PresentationModel>(
    val presentationModel: P,
    val ribbonComponentPresentationModel: RibbonComponentPresentationModel
) : PresentationModel

class RibbonMetaComponentProjection<out C : ContentModel, out P : PresentationModel>(
    val projection: Projection<C, P>,
    val ribbonComponentPresentationModel: RibbonComponentPresentationModel
) : Projection<C, MetaComponentPresentationModel<P>>() {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        RibbonMetaComponent(
            modifier = modifier,
            projection = projection,
            ribbonComponentPresentationModel = ribbonComponentPresentationModel
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        RibbonMetaComponent(
            modifier = modifier,
            projection = projection,
            ribbonComponentPresentationModel = ribbonComponentPresentationModel
        )
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun intrinsicWidth(height: Int): Int {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

        val hasIcon = (ribbonComponentPresentationModel.icon != null)
        val hasCaption = (ribbonComponentPresentationModel.caption != null)

        var result = 0.0f
        if (hasIcon) {
            result = 16 * density.density
        }
        if (hasIcon && hasCaption) {
            result += DefaultMetaComponentIconTextLayoutGap.value * density.density
        }
        if (hasCaption) {
            result += getLabelPreferredSingleLineWidth(
                contentModel = LabelContentModel(text = ribbonComponentPresentationModel.caption!!),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(0.dp),
                    textMaxLines = 1
                ),
                resolvedTextStyle = resolvedTextStyle,
                layoutDirection = layoutDirection,
                density = density,
                fontFamilyResolver = fontFamilyResolver
            )
        }
        if (hasIcon || hasCaption) {
            result += DefaultMetaComponentLayoutGap.value * density.density
        }
        result += projection.intrinsicWidth(height)

        return result.toInt()
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun intrinsicHeight(width: Int): Int {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

        var result = 0
        if (ribbonComponentPresentationModel.icon != null) {
            result = (16 * density.density).toInt()
        }
        if (ribbonComponentPresentationModel.caption != null) {
            result = max(
                result, getLabelPreferredHeight(
                    contentModel = LabelContentModel(text = ribbonComponentPresentationModel.caption),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        textMaxLines = 1
                    ),
                    resolvedTextStyle = resolvedTextStyle,
                    layoutDirection = layoutDirection,
                    density = density,
                    fontFamilyResolver = fontFamilyResolver,
                    availableWidth = width.toFloat()
                ).toInt()
            )
        }
        result = max(result, projection.intrinsicHeight(width))
        return result
    }
}

@Composable
internal fun <C : ContentModel, P : PresentationModel> RibbonMetaComponent(
    modifier: Modifier,
    projection: Projection<C, P>,
    ribbonComponentPresentationModel: RibbonComponentPresentationModel
) {
    val hasIcon = (ribbonComponentPresentationModel.icon != null)
    val hasCaption = (ribbonComponentPresentationModel.caption != null)
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (hasIcon) {
            Box(Modifier.size(DpSize(16.dp, 16.dp)).paint(painter = ribbonComponentPresentationModel.icon!!))
        }

        if (hasIcon && hasCaption) {
            Spacer(modifier = Modifier.width(DefaultMetaComponentIconTextLayoutGap))
        }

        if (hasCaption) {
            LabelProjection(
                contentModel = LabelContentModel(text = ribbonComponentPresentationModel.caption!!),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(0.dp),
                    textMaxLines = 1
                )
            ).project()
        }

        if (hasIcon || hasCaption) {
            Spacer(modifier = Modifier.width(DefaultMetaComponentLayoutGap))
        }

        projection.reproject(
            modifier = Modifier.then(
                if (ribbonComponentPresentationModel.horizontalAlignment == HorizontalAlignment.Fill)
                    Modifier.weight(1.0f) else Modifier
            )
        )
    }
}

private val DefaultMetaComponentIconTextLayoutGap = 4.dp
private val DefaultMetaComponentLayoutGap = 6.dp
