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
package org.pushingpixels.aurora.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.utils.AuroraText
import org.pushingpixels.aurora.component.utils.AuroraThemedIcon
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.component.utils.getTextColor
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraLabel(
    modifier: Modifier,
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel
) {
    val state =
        if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected

    val widthModifier =
        if ((presentationModel.textMaxLines > 1) || (presentationModel.singleLineDisplayPrototype == null)) {
            Modifier
        } else {
            val layoutDirection = LocalLayoutDirection.current
            val textStyle = presentationModel.textStyle ?: LocalTextStyle.current
            val resolvedTextStyle = resolveDefaults(textStyle, layoutDirection)
            val density = LocalDensity.current

            val prototypeDisplayWidth = getLabelPreferredSingleLineWidth(
                contentModel = LabelContentModel(text = presentationModel.singleLineDisplayPrototype),
                presentationModel = presentationModel,
                resolvedTextStyle = resolvedTextStyle,
                layoutDirection = layoutDirection,
                density = density,
                fontFamilyResolver = LocalFontFamilyResolver.current
            )

            var requiredWidth = (prototypeDisplayWidth / density.density).dp
            if (contentModel.icon != null) {
                requiredWidth += presentationModel.iconDimension.width
                requiredWidth += presentationModel.iconTextGap * presentationModel.horizontalGapScaleFactor
            }

            Modifier.requiredWidth(width = requiredWidth)
        }

    Row(
        modifier = modifier.padding(presentationModel.contentPadding).then(widthModifier),
        horizontalArrangement = presentationModel.horizontalAlignment.arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (contentModel.icon != null) {
            LabelIconContent(contentModel, presentationModel, state)
            Spacer(
                modifier = Modifier.requiredWidth(
                    presentationModel.iconTextGap * presentationModel.horizontalGapScaleFactor
                )
            )
        }
        LabelTextContent(contentModel, presentationModel, state)
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun LabelTextContent(
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel,
    state: ComponentState
) {
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = presentationModel.textStyle ?: LocalTextStyle.current
    val resolvedTextStyle = resolveDefaults(textStyle, layoutDirection)

    if (presentationModel.inheritStateFromParent) {
        // Take the resolved text style and unset its color before passing it to the text
        // composable. The text color will be determined based on the parent's state.
        AuroraText(
            text = contentModel.text,
            style = resolvedTextStyle.copy(color = Color.Unspecified),
            overflow = presentationModel.textOverflow,
            softWrap = presentationModel.textSoftWrap,
            maxLines = presentationModel.textMaxLines
        )
    } else {
        val decorationAreaType = AuroraSkin.decorationAreaType
        val skinColors = AuroraSkin.colors

        // If the presentation model specifies a text style with a color, use that. Otherwise
        // use the foreground color that matches the decoration area type of this label
        val presentationStyleHasColor = presentationModel.textStyle?.color?.isSpecified ?: false
        val textColor = if (presentationStyleHasColor) presentationModel.textStyle.color else
            getTextColor(
                modelStateInfo = null,
                currState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected,
                colors = skinColors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Default,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                skipFlatCheck = true,
                inactiveContainerType = ContainerType.Neutral,
                isTextInFilledArea = false,
        )

        // Pass our text color and synthesized model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides ModelStateInfoSnapshot(
                currModelState = state,
                stateContributionMap = mapOf(state to 1.0f),
                activeStrength = 1.0f
            )
        ) {
            // Since we're passing the resolved style that has the default color,
            // also explicitly pass our text color to override the one set in the style
            AuroraText(
                text = contentModel.text,
                color = textColor,
                style = resolvedTextStyle,
                overflow = presentationModel.textOverflow,
                softWrap = presentationModel.textSoftWrap,
                maxLines = presentationModel.textMaxLines
            )
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun LabelIconContent(
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel,
    currState: ComponentState
) {
    if (contentModel.icon == null) {
        return
    }

    if (presentationModel.inheritStateFromParent) {
        CompositionLocalProvider(
            LocalColorTokensOverlayProvider provides presentationModel.colorTokensOverlayProvider
        ) {
            AuroraThemedIcon(
                icon = contentModel.icon,
                size = presentationModel.iconDimension,
                disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                activeFilterStrategy = presentationModel.iconEnabledFilterStrategy
            )
        }
    } else {
        val decorationAreaType = AuroraSkin.decorationAreaType
        val skinColors = AuroraSkin.colors

        val textColor = getTextColor(
            modelStateInfo = null,
            currState = if (contentModel.enabled) ComponentState.Enabled else ComponentState.DisabledUnselected,
            colors = skinColors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
            skipFlatCheck = true,
            inactiveContainerType = ContainerType.Neutral,
            isTextInFilledArea = false,
        )

        // Pass our text color and synthesized model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides ModelStateInfoSnapshot(
                currModelState = currState,
                stateContributionMap = mapOf(currState to 1.0f),
                activeStrength = 1.0f
            ),
            LocalColorTokensOverlayProvider provides presentationModel.colorTokensOverlayProvider
        ) {
            AuroraThemedIcon(
                icon = contentModel.icon,
                size = presentationModel.iconDimension,
                disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                activeFilterStrategy = presentationModel.iconEnabledFilterStrategy
            )
        }
    }
}
