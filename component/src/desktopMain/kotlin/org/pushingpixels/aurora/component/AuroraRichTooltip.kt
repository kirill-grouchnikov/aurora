/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.model.RichTooltip
import org.pushingpixels.aurora.component.model.RichTooltipPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.LocalTextStyle
import org.pushingpixels.aurora.theming.LocalWindow
import org.pushingpixels.aurora.theming.PopupPlacementStrategy

// Rich tooltip tracking code based on code in TooltipArea.desktop.kt
private suspend fun PointerInputScope.detectDown(onDown: (Offset) -> Unit) {
    while (true) {
        awaitPointerEventScope {
            val event = awaitPointerEvent(PointerEventPass.Initial)
            val down = event.changes.find { it.changedToDown() }
            if (down != null) {
                onDown(down.position)
            }
        }
    }
}

private class Locator(val topLeftOffset: AuroraOffset, val size: AuroraSize) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.width = coordinates.size.width
        size.height = coordinates.size.height
    }
}

@Composable
private fun Modifier.locator(topLeftOffset: AuroraOffset, size: AuroraSize) = this.then(
    Locator(topLeftOffset, size)
)

@OptIn(AuroraInternalApi::class)
@Composable
fun Modifier.auroraRichTooltip(
    richTooltip: RichTooltip?,
    presentationModel: RichTooltipPresentationModel
): Modifier {
    if (richTooltip == null) {
        return this
    }

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val mergedTextStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters
    val decorationAreaType = AuroraSkin.decorationAreaType
    val window = LocalWindow.current
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)

    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }

    val topLeftOffset = AuroraOffset(0.0f, 0.0f)
    val size = AuroraSize(0, 0)

    val scope = rememberCoroutineScope()
    var job: Job? by remember { mutableStateOf(null) }

    fun startShowing() {
        job?.cancel()
        job = scope.launch {
            delay(500)
            displayRichTooltipContent(
                currentWindow = window,
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                resourceLoader = resourceLoader,
                skinColors = skinColors,
                skinPainters = painters,
                decorationAreaType = decorationAreaType,
                compositionLocalContext = compositionLocalContext,
                anchorBoundsInWindow = Rect(
                    offset = topLeftOffset.asOffset(density),
                    size = size.asSize(density)
                ),
                richTooltip = richTooltip,
                presentationModel = presentationModel,
                popupPlacementStrategy = PopupPlacementStrategy.Downward
            )
        }
    }

    fun hide() {
        job?.cancel()
        AuroraPopupManager.hidePopups(
            originator = window,
            popupKind = AuroraPopupManager.PopupKind.RICH_TOOLTIP
        )
    }

    return this.then(Modifier.locator(topLeftOffset, size)
        .pointerInput(layoutDirection) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent()
                    when (event.type) {
                        PointerEventType.Enter -> {
                            startShowing()
                        }
                        PointerEventType.Exit -> {
                            hide()
                        }
                    }
                }
            }
        }
        .pointerInput(layoutDirection) {
            detectDown {
                hide()
            }
        })
}
