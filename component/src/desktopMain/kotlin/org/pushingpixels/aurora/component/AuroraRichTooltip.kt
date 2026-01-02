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

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.model.RichTooltip
import org.pushingpixels.aurora.component.model.RichTooltipPresentationModel
import org.pushingpixels.aurora.component.ribbon.impl.LocalRibbonBandRectOnScreen
import org.pushingpixels.aurora.component.utils.displayRichTooltipWindow
import org.pushingpixels.aurora.theming.*

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

@OptIn(AuroraInternalApi::class)
private class Locator(val topLeftOffset: AuroraOffset, val size: MutableState<IntSize>) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.value = coordinates.size
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun Modifier.locator(topLeftOffset: AuroraOffset, size: MutableState<IntSize>) = this.then(
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
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val popupOriginator = LocalPopupMenu.current ?: LocalWindow.current.rootPane
    val ribbonBandRectScreen = LocalRibbonBandRectOnScreen.current
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)

    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }

    val topLeftOffset = AuroraOffset(0.0f, 0.0f)
    val size = remember { mutableStateOf(IntSize(0, 0)) }

    val coroutineScope = rememberCoroutineScope()
    var job: Job? by remember { mutableStateOf(null) }

    fun startShowing() {
        job?.cancel()
        job = coroutineScope.launch {
            delay(750)
            val isShowingPopupFromHere = AuroraPopupManager.isShowingPopupFrom(
                originator = popupOriginator,
                pointInOriginator = AuroraOffset(
                    x = topLeftOffset.x + size.value.width / 2.0f,
                    y = topLeftOffset.y + size.value.height / 2.0f
                ).asOffset(density)
            )
            if (!isShowingPopupFromHere) {
                val anchorBoundsInWindow = Rect(
                    offset = topLeftOffset.asOffset(density),
                    size = size.value.asSize(density)
                )
                val extraVerticalOffset = if (ribbonBandRectScreen.isEmpty) {
                    0f
                } else {
                    ribbonBandRectScreen.bottom - anchorBoundsInWindow.bottom + 4.0f
                }
                val tooltipWindow = displayRichTooltipWindow(
                    popupOriginator = popupOriginator,
                    layoutDirection = layoutDirection,
                    density = density,
                    textStyle = resolvedTextStyle,
                    fontFamilyResolver = fontFamilyResolver,
                    skinColors = skinColors,
                    decorationAreaType = decorationAreaType,
                    compositionLocalContext = compositionLocalContext,
                    anchorBoundsInWindow = Rect(
                        offset = topLeftOffset.asOffset(density),
                        size = size.value.asSize(density)
                    ),
                    extraVerticalOffset = extraVerticalOffset,
                    richTooltip = richTooltip,
                    presentationModel = presentationModel,
                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
                )
                coroutineScope.launch {
                    tooltipWindow?.opacity = 1.0f
                }
            }
        }
    }

    fun hide() {
        job?.cancel()
        AuroraPopupManager.hidePopups(
            originator = popupOriginator,
            popupKind = AuroraPopupManager.PopupKind.RichTooltip
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
