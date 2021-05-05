/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
