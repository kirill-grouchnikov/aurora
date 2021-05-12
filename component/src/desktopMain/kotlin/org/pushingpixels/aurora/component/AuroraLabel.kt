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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.utils.AuroraText
import org.pushingpixels.aurora.icon.AuroraThemedIcon

@Composable
fun AuroraLabel(
    modifier: Modifier = Modifier,
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel = LabelPresentationModel()
) {
    val state =
        if (contentModel.enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED

    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    Row(
        modifier = modifier.padding(presentationModel.contentPadding),
        horizontalArrangement = presentationModel.horizontalAlignment.arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (contentModel.iconFactory != null) {
            LabelIconContent(contentModel, presentationModel, state)
            Spacer(modifier = Modifier.requiredWidth(4.dp * presentationModel.horizontalGapScaleFactor))
        }
        LabelTextContent(contentModel, presentationModel, state, resolvedTextStyle)
    }
}

@Composable
private fun LabelTextContent(
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel,
    state: ComponentState,
    style: TextStyle
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors

    val textColor = skinColors.getColorScheme(decorationAreaType, state).foregroundColor

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
            style = style,
            overflow = presentationModel.textOverflow,
            softWrap = presentationModel.textSoftWrap,
            maxLines = presentationModel.textMaxLines
        )
    }
}

@Composable
private fun LabelIconContent(
    contentModel: LabelContentModel, presentationModel: LabelPresentationModel,
    currState: ComponentState
) {
    if (contentModel.iconFactory != null) {
        val iconSize = presentationModel.iconDimension
        val icon = remember(iconSize) { contentModel.iconFactory.createNewIcon() }
        icon.setSize(width = iconSize, height = iconSize)

        val decorationAreaType = AuroraSkin.decorationAreaType
        val skinColors = AuroraSkin.colors

        val textColor = skinColors.getColorScheme(decorationAreaType, currState).foregroundColor

        // Pass our text color and synthesized model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides ModelStateInfoSnapshot(
                currModelState = currState,
                stateContributionMap = mapOf(currState to 1.0f),
                activeStrength = 1.0f
            )
        ) {
            AuroraThemedIcon(
                icon = icon,
                disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                activeFilterStrategy = presentationModel.iconEnabledFilterStrategy
            )
        }
    }
}
