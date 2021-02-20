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
package org.pushingpixels.aurora.component.model

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.utils.AuroraOffset
import org.pushingpixels.aurora.component.utils.AuroraSize
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.icon.AuroraThemedIcon
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.Window
import javax.swing.JWindow

interface CommandActionPreview {
    /**
     * Invoked when a command preview has been activated.
     *
     * @param command Command for which the preview has been activated.
     */
    // TODO - remove nullability when buttons are only created from commands
    fun onCommandPreviewActivated(command: Command?)

    /**
     * Invoked when a command preview has been canceled.
     *
     * @param command Command for which the preview has been canceled.
     */
    // TODO - remove nullability when buttons are only created from commands
    fun onCommandPreviewCanceled(command: Command?)
}

data class Command(
    val text: String,
    val extraText: String? = null,
    val iconFactory: AuroraIcon.Factory?,
    val disabledIconFactory: AuroraIcon.Factory? = null,
    val action: () -> Unit = {},
    val actionPreview: CommandActionPreview? = null,
    var isActionEnabled: State<Boolean>,
    var isActionToggle: Boolean = false,
    val secondaryContentModel: CommandMenuContentModel? = null,
    var isSecondaryEnabled: State<Boolean>? = null
)

data class CommandGroup(
    val title: String? = null,
    val command: List<Command>
)

data class CommandMenuContentModel(
    val commands: List<CommandGroup>
) {
    constructor(group: CommandGroup) : this(listOf(group))
}

enum class TextClick {
    ACTION, POPUP
}

data class CommandPresentationModel(
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
    val textClick: TextClick = TextClick.ACTION
)

@Composable
fun AuroraCommandButton(
    command: Command,
    presentationModel: CommandPresentationModel
) {
    AuroraButton(
        enabled = command.isActionEnabled.value,
        onClick = command.action,
        rolloverTracker = command.actionPreview,
        content = {
            // TODO - content layout will depend on the presentation state
            if (command.iconFactory != null) {
                val icon = command.iconFactory.createNewIcon()
                icon.setSize(10.dp, 10.dp)
                AuroraThemedIcon(
                    icon = icon,
                    modifier = Modifier.auroraButtonIconPadding()
                )
            }
            AuroraText(command.text)
        }
    )
}

@Composable
fun AuroraSplitButton(
    command: Command,
    presentationModel: CommandPresentationModel
) {
    val actionInteractionSource = remember { MutableInteractionSource() }
    val popupInteractionSource = remember { MutableInteractionSource() }

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = AuroraSkin.buttonShaper
    val painters = AuroraSkin.painters

    val auroraTopLeftOffset = AuroraOffset(0.0f, 0.0f)
    val auroraSize = AuroraSize(0, 0)
    val density = LocalDensity.current.density
    val layoutDirection = LocalLayoutDirection.current

    Layout(modifier = Modifier.background(Color.Green),
        content = {
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 24.dp)
                    .clickable(
                        enabled = command.isActionEnabled.value,
                        onClick = command.action,
                        interactionSource = actionInteractionSource,
                        indication = null
                    )
                    .splitButtonLocator(auroraTopLeftOffset, auroraSize)
            ) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    drawOutline(
                        outline = Outline.Rectangle(
                            rect = Rect(
                                left = 0.0f, top = 0.0f,
                                right = size.width, bottom = size.height
                            )
                        ), color = Color.Red, style = Fill
                    )
                }
                // Pass our text color and model state snapshot to the children
                CompositionLocalProvider(
                    LocalTextColor provides Color.Black,
                    LocalModelStateInfoSnapshot provides ModelStateInfoSnapshot(
                        currModelState = ComponentState.ENABLED,
                        stateContributionMap = emptyMap(),
                        activeStrength = 1.0f
                    )
                ) {
                    AuroraSplitButtonAction(command, presentationModel)
                }
            }
            Box(
                modifier = Modifier.clickable(
                    enabled = command.isSecondaryEnabled?.value ?: false,
                    onClick = {
                        // TODO - move off of JWindow when https://github.com/JetBrains/compose-jb/issues/195
                        //  is addressed
                        val jwindow = AuroraPopupWindow()
                        jwindow.focusableWindowState = false
                        jwindow.type = Window.Type.POPUP
                        jwindow.isAlwaysOnTop = true

                        // TODO - hopefully temporary. Mark the popup window as fully transparent
                        //  so that when it is globally positioned, we can size it to the actual
                        //  content and make it fully opaque
                        jwindow.opacity = 0.0f

                        val auroraWindow = AppManager.focusedWindow!!.window
                        val locationOnScreen = auroraWindow.locationOnScreen

                        // anchor the popup window to the bottom left corner of the component
                        // in screen coordinates
                        // TODO - figure out the sizing (see above)
                        jwindow.setBounds(
                            (locationOnScreen.x + auroraTopLeftOffset.x / density).toInt(),
                            (locationOnScreen.y + auroraTopLeftOffset.y / density).toInt(),
                            1000,
                            1000
                        )

                        val popupContent = ComposePanel()
                        popupContent.setContent {
                            CompositionLocalProvider(
                                LocalDecorationAreaType provides decorationAreaType,
                                LocalSkinColors provides skinColors,
                                LocalButtonShaper provides buttonShaper,
                                LocalPainters provides painters,
                                LocalAnimationConfig provides AuroraSkin.animationConfig
                            ) {
                                SplitButtonPopupContent(
                                    window = jwindow,
                                    anchorSize = auroraSize,
                                    command = command,
                                    presentationModel = presentationModel
                                )
                            }
                        }
                        jwindow.contentPane.add(popupContent, BorderLayout.CENTER)
                        jwindow.invalidate()
                        jwindow.validate()
                        jwindow.isVisible = true
                        jwindow.pack()
                    },
                    interactionSource = popupInteractionSource,
                    indication = null
                ).size(width = 20.dp, height = 24.dp)
            ) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    drawOutline(
                        outline = Outline.Rectangle(
                            rect = Rect(
                                left = 0.0f, top = 0.0f,
                                right = size.width, bottom = size.height
                            )
                        ), color = Color.Blue, style = Fill
                    )
                }
            }
        }) { measurables, constraints ->
        // Measure each child so that we know how much space they need
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // The children are laid out in a row
        val contentTotalWidth = placeables.sumBy { it.width }
        // And the height of the row is determined by the height of the tallest child
        val contentMaxHeight = placeables.maxOf { it.height }

        layout(width = contentTotalWidth, height = contentMaxHeight) {
            var xPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(
                    x = xPosition,
                    y = (contentMaxHeight - placeable.height) / 2
                )
                xPosition += placeable.width
            }
        }
    }
}

@Composable
private fun AuroraSplitButtonAction(
    command: Command,
    presentationModel: CommandPresentationModel
) {
    Layout(
        content = {
            // TODO - content layout will depend on the presentation state
            if (command.iconFactory != null) {
                val icon = command.iconFactory.createNewIcon()
                icon.setSize(10.dp, 10.dp)
                AuroraThemedIcon(
                    icon = icon,
                    modifier = Modifier.auroraButtonIconPadding()
                )
            }
            AuroraText(command.text)
        }
    ) { measurables, constraints ->
        // Measure each child so that we know how much space they need
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // The children are laid out in a row
        val contentTotalWidth = placeables.sumBy { it.width }
        // And the height of the row is determined by the height of the tallest child
        val contentMaxHeight = placeables.maxOf { it.height }

        // Center children vertically within the vertical space
        layout(width = contentTotalWidth, height = contentMaxHeight) {
            // TODO - add RTL support
            var xPosition = 0

            placeables.forEach { placeable ->
                placeable.placeRelative(
                    x = xPosition,
                    y = (contentMaxHeight - placeable.height) / 2
                )
                xPosition += placeable.width
            }
        }
    }
}

@Composable
private fun SplitButtonPopupContent(
    window: JWindow,
    anchorSize: AuroraSize,
    command: Command,
    presentationModel: CommandPresentationModel
) {
    val borderScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = DecorationAreaType.NONE,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = ComponentState.ENABLED
    )
    val popupBorderColor = AuroraSkin.painters.borderPainter.getRepresentativeColor(borderScheme)
    val density = LocalDensity.current.density
    val contentSize = AuroraSize(0, 0)
    Box(
        modifier = Modifier.auroraBackground(window = window).onGloballyPositioned {
            // Get the size of the content and update the popup window bounds
            val popupWidth = (contentSize.width / density).toInt()
            val popupHeight = (contentSize.height / density).toInt()

            val popupRect = Rectangle(
                window.x,
                window.y + (anchorSize.height / (2 * density)).toInt(),
                popupWidth,
                popupHeight
            )

            // Make sure the popup stays in screen bounds
            val screenBounds = window.graphicsConfiguration.bounds
            if (popupRect.x < 0) {
                popupRect.translate(-popupRect.x, 0)
            }
            if ((popupRect.x + popupRect.width) > screenBounds.width) {
                popupRect.translate(screenBounds.width - popupRect.x - popupRect.width, 0)
            }
            if (popupRect.y < 0) {
                popupRect.translate(0, -popupRect.y)
            }
            if ((popupRect.y + popupRect.height) > screenBounds.height) {
                popupRect.translate(0, screenBounds.height - popupRect.y - popupRect.height)
            }

            window.bounds = popupRect
            window.opacity = 1.0f
            window.preferredSize = Dimension(popupRect.width, popupRect.height)
            window.size = Dimension(popupRect.width, popupRect.height)
            window.invalidate()
            window.validate()
        }
    ) {
        Canvas(Modifier.matchParentSize()) {
            val outline = Outline.Rectangle(
                rect = Rect(
                    left = 0.5f, top = 0.5f,
                    right = size.width - 0.5f, bottom = size.height - 0.5f
                )
            )
            drawOutline(
                outline = outline,
                color = popupBorderColor,
                style = Stroke(width = 1.0f)
            )
        }
        SplitButtonPopupColumn(contentSize = contentSize) {
            for (item in command.secondaryContentModel!!.commands[0].command) {
                AuroraMenuButton(
                    enabled = true,
                    onClick = {
                        window.dispose()
                        item.action.invoke()
                    },
                    sides = ButtonSides(straightSides = Side.values().toSet()),
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                    sizingStrategy = ButtonSizingStrategy.EXTENDED,
                ) {
                    AuroraText(text = item.text, maxLines = 1)
                }
            }
        }
    }
}

@Composable
private fun SplitButtonPopupColumn(contentSize: AuroraSize, content: @Composable () -> Unit) {
    Layout(content = content) { measurables, _ ->
        // The column width is determined by the widest child
        val contentTotalWidth = measurables.maxOf { it.maxIntrinsicWidth(Int.MAX_VALUE) }

        val placeables = measurables.map { measurable ->
            // Measure each child with fixed (widest) width
            measurable.measure(Constraints.fixedWidth(contentTotalWidth))
        }

        // The children are laid out in a column
        val contentMaxHeight = placeables.sumBy { it.height }
        contentSize.width = contentTotalWidth
        contentSize.height = contentMaxHeight

        layout(width = contentTotalWidth, height = contentMaxHeight) {
            var yPosition = 0

            // TODO - support RTL
            placeables.forEach { placeable ->
                placeable.placeRelative(
                    x = 0,
                    y = yPosition
                )
                yPosition += placeable.height
            }
        }
    }
}

private class SplitButtonBoxLocator(val topLeftOffset: AuroraOffset, val size: AuroraSize) :
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
private fun Modifier.splitButtonLocator(topLeftOffset: AuroraOffset, size: AuroraSize) = this.then(
    SplitButtonBoxLocator(topLeftOffset, size)
)


