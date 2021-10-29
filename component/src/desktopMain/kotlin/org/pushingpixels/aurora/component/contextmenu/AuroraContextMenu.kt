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
package org.pushingpixels.aurora.component.contextmenu

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.component.model.CommandPopupMenuPresentationModel
import org.pushingpixels.aurora.component.utils.displayPopupContent
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.LocalTextStyle
import org.pushingpixels.aurora.theming.LocalWindow

@OptIn(ExperimentalComposeApi::class)
@Composable
fun Modifier.auroraContextMenu(
    enabled: Boolean = true,
    contentModel: CommandMenuContentModel,
    presentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay> = mapOf()
): Modifier {
    val contentModelState = rememberUpdatedState(contentModel)
    val enabledState = rememberUpdatedState(enabled)

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val mergedTextStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters
    val decorationAreaType = AuroraSkin.decorationAreaType
    val window = LocalWindow.current
    val currentLocals by rememberUpdatedState(currentCompositionLocalContext)

    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }

    return this.then(Modifier.pointerInput(Unit) {
        while (true) {
            val lastMouseEvent = awaitPointerEventScope { awaitPointerEvent() }.mouseEvent

            if (enabledState.value && (lastMouseEvent?.isPopupTrigger == true)) {
                displayPopupContent(
                    currentWindow = window,
                    layoutDirection = layoutDirection,
                    density = density,
                    textStyle = resolvedTextStyle,
                    resourceLoader = resourceLoader,
                    skinColors = skinColors,
                    skinPainters = painters,
                    decorationAreaType = decorationAreaType,
                    locals = currentLocals,
                    anchorBoundsInWindow = Rect(
                        offset = Offset(
                            x = lastMouseEvent.x.toFloat(),
                            y = lastMouseEvent.y.toFloat()
                        ),
                        size = Size.Zero
                    ),
                    contentModel = contentModelState,
                    presentationModel = presentationModel,
                    toDismissPopupsOnActivation = true,
                    toUseBackgroundStriping = false,
                    popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                    overlays = overlays
                )
            }
        }
    })
}
