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
package org.pushingpixels.aurora.component.contextmenu

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.component.model.CommandPopupMenuPresentationModel
import org.pushingpixels.aurora.component.utils.popup.GeneralCommandMenuPopupHandler
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.LocalPopupMenu
import org.pushingpixels.aurora.theming.LocalTextStyle
import org.pushingpixels.aurora.theming.LocalWindow

@OptIn(AuroraInternalApi::class)
@Composable
fun Modifier.auroraContextMenu(
    enabled: Boolean = true,
    contentModel: CommandMenuContentModel,
    presentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel(),
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay> = mapOf()
): Modifier {
    val contentModelState = rememberUpdatedState(contentModel)
    val enabledState = rememberUpdatedState(enabled)

    val density = LocalDensity.current
    // This needs to use rememberUpdatedState. Otherwise switching locale to RTL will
    // not properly propagate in here.
    val layoutDirection by rememberUpdatedState(LocalLayoutDirection.current)
    val mergedTextStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters
    val buttonShaper = AuroraSkin.buttonShaper
    val decorationAreaType = AuroraSkin.decorationAreaType
    val popupOriginator = LocalPopupMenu.current ?: LocalWindow.current.rootPane
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)

    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }

    val coroutineScope = rememberCoroutineScope()

    return this.then(Modifier.pointerInput(Unit) {
        while (true) {
            val lastMouseEvent = awaitPointerEventScope { awaitPointerEvent() }.awtEventOrNull

            if (enabledState.value && (lastMouseEvent?.isPopupTrigger == true)) {
                val popupWindow = GeneralCommandMenuPopupHandler.showPopupContent(
                    popupOriginator = popupOriginator,
                    layoutDirection = layoutDirection,
                    density = density,
                    textStyle = resolvedTextStyle,
                    fontFamilyResolver = fontFamilyResolver,
                    skinColors = skinColors,
                    skinPainters = painters,
                    buttonShaper = buttonShaper,
                    decorationAreaType = decorationAreaType,
                    compositionLocalContext = compositionLocalContext,
                    anchorBoundsInWindow = Rect(
                        offset = Offset(
                            x = lastMouseEvent.x.toFloat(),
                            y = lastMouseEvent.y.toFloat()
                        ),
                        size = Size.Zero
                    ),
                    popupTriggerAreaInWindow = Rect.Zero,
                    contentModel = contentModelState,
                    presentationModel = presentationModel,
                    displayPrototypeCommand = null,
                    toDismissPopupsOnActivation = presentationModel.toDismissOnCommandActivation,
                    popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                    popupAnchorBoundsProvider = null,
                    popupOriginatorKeyTip = null,
                    overlays = overlays,
                    popupKind = AuroraPopupManager.PopupKind.Popup
                )
                coroutineScope.launch {
                    popupWindow?.opacity = 1.0f
                }
            }
        }
    })
}
