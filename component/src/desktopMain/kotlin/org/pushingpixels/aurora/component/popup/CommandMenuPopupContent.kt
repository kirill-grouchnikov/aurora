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
package org.pushingpixels.aurora.component.popup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import java.awt.Component
import java.awt.Rectangle
import java.awt.Window

internal val Color.awtColor: java.awt.Color
    get() = java.awt.Color(
        this.red, this.green, this.blue, this.alpha
    )

interface BaseCommandMenuHandler<in M : BaseCommandMenuContentModel,
        in P : BaseCommandPopupMenuPresentationModel> {
    fun showPopupContent(
        popupOriginator: Component,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        skinColors: AuroraSkinColors,
        skinPainters: AuroraPainters,
        buttonShaper: AuroraButtonShaper,
        decorationAreaType: DecorationAreaType,
        compositionLocalContext: CompositionLocalContext,
        anchorBoundsInWindow: Rect,
        popupTriggerAreaInWindow: Rect,
        contentModel: State<M?>,
        presentationModel: P,
        displayPrototypeCommand: BaseCommand?,
        toDismissPopupsOnActivation: Boolean,
        popupPlacementStrategy: PopupPlacementStrategy,
        popupAnchorBoundsProvider: (() -> Rect)?,
        popupOriginatorKeyTip: String?,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupKind: AuroraPopupManager.PopupKind
    ): Window?

    companion object {
        fun getPlacementAwarePopupShift(
            layoutDirection: LayoutDirection,
            anchorDimension: IntSize, popupDimension: IntSize,
            popupPlacementStrategy: PopupPlacementStrategy
        ): IntSize {
            var dx = 0
            var dy = 0
            val anchorWidth = anchorDimension.width
            val anchorHeight = anchorDimension.height
            val popupWidth = popupDimension.width
            val popupHeight = popupDimension.height

            val ltr = (layoutDirection == LayoutDirection.Ltr)

            // Compute horizontal / vertical offsets relative to the placement of the popup
            // under the default PopupPlacementStrategy.Downward.HAlignStart
            when (popupPlacementStrategy) {
                PopupPlacementStrategy.Upward.HAlignStart -> {
                    // Popup above the component, horizontally aligned to the start edge
                    dy = -popupHeight - anchorHeight
                }

                PopupPlacementStrategy.Upward.HAlignEnd -> {
                    // Popup above the component, horizontally aligned to the end edge
                    dy = -popupHeight - anchorHeight
                    dx = if (ltr) {
                        anchorWidth - popupWidth
                    } else {
                        popupWidth - anchorWidth
                    }

                }

                PopupPlacementStrategy.Downward.HAlignStart -> {
                    // Popup below the component, horizontally aligned to the start edge
                }

                PopupPlacementStrategy.Downward.HAlignEnd -> {
                    // Popup below the component, horizontally aligned to the end edge
                    dx = if (ltr) {
                        anchorWidth - popupWidth
                    } else {
                        popupWidth - anchorWidth
                    }
                }

                PopupPlacementStrategy.CenteredVertically.HAlignStart -> {
                    // Popup centered vertically, horizontally aligned to the start edge
                    dy = -popupHeight / 2 - anchorHeight / 2
                }

                PopupPlacementStrategy.CenteredVertically.HAlignEnd -> {
                    // Popup centered vertically, horizontally aligned to the end edge
                    dy = -popupHeight / 2 - anchorHeight / 2
                    dx = if (ltr) {
                        anchorWidth - popupWidth
                    } else {
                        popupWidth - anchorWidth
                    }
                }

                PopupPlacementStrategy.Startward.VAlignTop -> {
                    // Popup next to the start edge of the component, vertically aligned to the top edge
                    dx = if (ltr) -popupWidth else popupWidth
                    dy = -anchorHeight
                }

                PopupPlacementStrategy.Startward.VAlignBottom -> {
                    // Popup next to the start edge of the component, vertically aligned to the bottom edge
                    dx = if (ltr) -popupWidth else popupWidth
                    dy = -popupHeight
                }

                PopupPlacementStrategy.Endward.VAlignTop -> {
                    // Popup next to the end edge of the component, vertically aligned to the top edge
                    dx = if (ltr) anchorWidth else -anchorWidth
                    dy = -anchorHeight
                }

                PopupPlacementStrategy.Endward.VAlignBottom -> {
                    // Popup next to the end edge of the component, vertically aligned to the bottom edge
                    dx = if (ltr) anchorWidth else -anchorWidth
                    dy = -popupHeight
                }
            }
            return IntSize(dx, dy)
        }

        fun getPopupRectangleOnScreen(
            popupOriginator: Component,
            layoutDirection: LayoutDirection,
            anchorBoundsInWindow: Rect,
            popupPlacementStrategy: PopupPlacementStrategy,
            fullPopupSize: IntSize
        ): Rectangle {
            // PHASE 1 - screen-based coordinates of the popup anchor
            val popupOriginatorLocationOnScreen = popupOriginator.locationOnScreen
            val currentScreenBounds = popupOriginator.graphicsConfiguration.bounds
            popupOriginatorLocationOnScreen.translate(-currentScreenBounds.x, -currentScreenBounds.y)

            val initialAnchorX = if (layoutDirection == LayoutDirection.Ltr)
                (popupOriginatorLocationOnScreen.x + anchorBoundsInWindow.left).toInt() else
                (popupOriginatorLocationOnScreen.x + anchorBoundsInWindow.left + anchorBoundsInWindow.width).toInt() - fullPopupSize.width
            // Initial anchor corresponds to the on-screen location of the top-left corner of the
            // popup window under the default PopupPlacementStrategy.Downward.HAlignStart placement
            // strategy
            val initialAnchor = IntOffset(
                x = initialAnchorX,
                y = (popupOriginatorLocationOnScreen.y + anchorBoundsInWindow.top).toInt()
            )

            // PHASE 2 - shift to account for popup placement strategy
            val popupShift = getPlacementAwarePopupShift(
                layoutDirection = layoutDirection,
                anchorDimension = IntSize(
                    width = anchorBoundsInWindow.width.toInt(),
                    height = anchorBoundsInWindow.height.toInt()
                ),
                popupDimension = fullPopupSize,
                popupPlacementStrategy = popupPlacementStrategy
            )
            val popupRect = Rectangle(
                initialAnchor.x + popupShift.width,
                initialAnchor.y + anchorBoundsInWindow.height.toInt() + popupShift.height,
                fullPopupSize.width,
                fullPopupSize.height
            )

            // PHASE 3 - make sure the popup stays in screen bounds
            val screenBounds = popupOriginator.graphicsConfiguration.bounds
            if (popupRect.x < 0) {
                popupRect.translate(-popupRect.x, 0)
            }
            if ((popupRect.x + popupRect.width) > screenBounds.width) {
                popupRect.translate(
                    screenBounds.width - popupRect.x - popupRect.width,
                    0
                )
            }
            if (popupRect.y < 0) {
                popupRect.translate(0, -popupRect.y)
            }
            if ((popupRect.y + popupRect.height) > screenBounds.height) {
                popupRect.translate(
                    0,
                    screenBounds.height - popupRect.y - popupRect.height
                )
            }
            return popupRect
        }
    }
}

@Composable
fun Modifier.auroraPopupMenuRowBackground(
    backgroundFillColorQuery: (Int, ContainerColorTokens) -> Color,
    iconGutterFillColorQuery: ((ContainerColorTokens) -> Color)? = null,
    gutterWidth: Float = 0.0f,
): Modifier {
    val colorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)

    val backgroundFill = backgroundFillColorQuery.invoke(0, colorTokens)
    val gutterFill = if (gutterWidth > 0) (iconGutterFillColorQuery?.invoke(colorTokens)
        ?: Color.Unspecified) else Color.Unspecified

    return this.then(
        PopupMenuRowBackground(
            backgroundFill = backgroundFill,
            gutterFill = gutterFill,
            gutterWidth = gutterWidth,
        )
    )
}

@Composable
fun Modifier.auroraPopupMenuRowBackground(
    backgroundFillColorQuery: (Int, ContainerColorTokens) -> Color,
    iconGutterFillColorQuery: ((ContainerColorTokens) -> Color)? = null,
    rowIndex: Int,
    gutterWidth: Float = 0.0f,
): Modifier {
    val colorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)

    val backgroundFill = backgroundFillColorQuery.invoke(rowIndex, colorTokens)
    val gutterFill = if (gutterWidth > 0) (iconGutterFillColorQuery?.invoke(colorTokens)
        ?: Color.Unspecified) else Color.Unspecified

    return this.then(
        PopupMenuRowBackground(
            backgroundFill = backgroundFill,
            gutterFill = gutterFill,
            gutterWidth = gutterWidth,
        )
    )
}

private class PopupMenuRowBackground(
    private val backgroundFill: Color,
    private val gutterFill: Color?,
    private val gutterWidth: Float,
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        drawRect(color = backgroundFill)

        // Have gutter?
        if (gutterWidth > 0) {
            if (layoutDirection == LayoutDirection.Ltr) {
                drawRect(
                    color = gutterFill!!,
                    topLeft = Offset.Zero,
                    size = Size(width = gutterWidth, height = size.height)
                )
            } else {
                drawRect(
                    color = gutterFill!!,
                    topLeft = Offset(x = size.width - gutterWidth, y = 0.0f),
                    size = Size(width = gutterWidth, height = size.height)
                )
            }
        }
        drawContent()
    }
}
