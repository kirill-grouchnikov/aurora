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
package org.pushingpixels.aurora.component.renderer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*

@Composable
fun AuroraListRowRenderer(
    modifier: Modifier = Modifier,
    onSelect: () -> Unit,
    content: @Composable () -> Unit
) {
    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType

    var rollover by remember { mutableStateOf(false) }
    val state = if (rollover) ComponentState.ROLLOVER_UNSELECTED else ComponentState.ENABLED

    val colorBackground = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.FILL,
        componentState = state
    ).backgroundFillColor
    val textColor = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.FILL,
        componentState = state
    ).foregroundColor

    Box(
        modifier = modifier
            .background(color = colorBackground)
            .clickable(onClick = onSelect)
            .pointerMoveFilter(
                onEnter = {
                    rollover = true
                    false
                },
                onExit = {
                    rollover = false
                    false
                },
                onMove = {
                    false
                })
            .padding(start = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides ModelStateInfoSnapshot(
                currModelState = state,
                stateContributionMap = mapOf(state to 1.0f),
                activeStrength = 1.0f
            )
        ) {
            content()
        }
    }
}