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

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.transition
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.AmbientLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.utils.*
import java.awt.BorderLayout
import java.awt.Rectangle
import java.awt.Window
import javax.swing.JWindow
import kotlin.math.max

object ComboBoxSizingConstants {
    val DefaultComboBoxArrowWidth = 10.dp
    val DefaultComboBoxArrowHeight = 7.dp
    val DefaultComboBoxContentArrowGap = 6.dp
}

// This will be initialized on first usage using the getSelectedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var SelectedTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getRolloverTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var RolloverTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getPressedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var PressedTransitionDefinition: TransitionDefinition<Boolean>

// This will be initialized on first usage using the getEnabledTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var EnabledTransitionDefinition: TransitionDefinition<Boolean>

@Immutable
private class ComboBoxDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false,
        ultraLight = Color.White,
        extraLight = Color.White,
        light = Color.White,
        mid = Color.White,
        dark = Color.White,
        ultraDark = Color.White,
        foreground = Color.Black
    )
)

private class ComboBoxLocator(val topLeftOffset: AuroraOffset, val size: AuroraSize) : OnGloballyPositionedModifier {
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
private fun Modifier.comboBoxLocator(topLeftOffset: AuroraOffset, size: AuroraSize) = this.then(
    ComboBoxLocator(topLeftOffset, size)
)

class AuroraPopupWindow : JWindow()

@Composable
fun <E> AuroraComboBox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
    popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
    items: List<E>,
    selectedItem: E,
    displayConverter: (E) -> String,
    onTriggerItemSelectedChange: (E) -> Unit
) {
    AuroraComboBox(
        modifier = modifier,
        enabled = enabled,
        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
        popupPlacementStrategy = popupPlacementStrategy,
        items = items,
        selectedItem = selectedItem,
        displayConverter = displayConverter,
        onTriggerItemSelectedChange = onTriggerItemSelectedChange,
        interactionState = remember { InteractionState() },
        stateTransitionFloat = AnimatedFloat(0.0f, AmbientAnimationClock.current.asDisposableClock())
    )
}

@Composable
private fun <E> AuroraComboBox(
    modifier: Modifier,
    enabled: Boolean,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    popupPlacementStrategy: PopupPlacementStrategy,
    items: List<E>,
    selectedItem: E,
    displayConverter: (E) -> String,
    onTriggerItemSelectedChange: (E) -> Unit,
    interactionState: InteractionState,
    stateTransitionFloat: AnimatedFloat
) {
    val drawingCache = remember { ComboBoxDrawingCache() }

    var rollover by remember { mutableStateOf(false) }

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = enabled,
                isRollover = rollover,
                isSelected = false,
                isPressed = Interaction.Pressed in interactionState
            )
        )
    }

    val modelStateInfo = remember {
        ModelStateInfo(
            ComponentState.getState(
                isEnabled = enabled,
                isRollover = rollover,
                isSelected = false,
                isPressed = Interaction.Pressed in interactionState
            )
        )
    }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        enabled = enabled,
        selected = false,
        rollover = rollover,
        pressed = Interaction.Pressed in interactionState,
        stateTransitionFloat = stateTransitionFloat,
        duration = AuroraSkin.animationConfig.regular
    )

    // Transition for the selection state
    if (!::SelectedTransitionDefinition.isInitialized) {
        SelectedTransitionDefinition =
            getSelectedTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val selectionTransitionState = transition(
        definition = SelectedTransitionDefinition,
        initState = false,
        toState = false
    )
    // Transition for the rollover state
    if (!::RolloverTransitionDefinition.isInitialized) {
        RolloverTransitionDefinition =
            getRolloverTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val rolloverTransitionState = transition(
        definition = RolloverTransitionDefinition,
        initState = rollover,
        toState = rollover
    )
    // Transition for the pressed state
    if (!::PressedTransitionDefinition.isInitialized) {
        PressedTransitionDefinition =
            getPressedTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val pressedTransitionState = transition(
        definition = PressedTransitionDefinition,
        initState = Interaction.Pressed in interactionState,
        toState = Interaction.Pressed in interactionState
    )
    // Transition for the enabled state
    if (!::EnabledTransitionDefinition.isInitialized) {
        EnabledTransitionDefinition =
            getEnabledTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val enabledTransitionState = transition(
        definition = EnabledTransitionDefinition,
        initState = enabled,
        toState = enabled
    )

    // TODO - how to trigger the state transition animation without these transitions
    //  that track the changes in different states?
    selectionTransitionState[SelectionTransitionFraction]
    rolloverTransitionState[RolloverTransitionFraction]
    pressedTransitionState[PressedTransitionFraction]
    enabledTransitionState[EnabledTransitionFraction]

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = AuroraSkin.buttonShaper
    val painters = AuroraSkin.painters

    val auroraTopLeftOffset = AuroraOffset(0.0f, 0.0f)
    val auroraSize = AuroraSize(0, 0)
    val density = AmbientDensity.current.density
    val layoutDirection = AmbientLayoutDirection.current

    Box(
        modifier = modifier
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
            .clickable(
                enabled = enabled,
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

                    val composePopupContent = ComposePanel()
                    composePopupContent.setContent {
                        Providers(
                            AmbientDecorationAreaType provides decorationAreaType,
                            AmbientSkinColors provides skinColors,
                            AmbientButtonShaper provides buttonShaper,
                            AmbientPainters provides painters,
                            AmbientAnimationConfig provides AuroraSkin.animationConfig
                        ) {
                            ComboBoxPopupContent(
                                window = jwindow,
                                anchorSize = auroraSize,
                                popupPlacementStrategy = popupPlacementStrategy,
                                items = items,
                                displayConverter = displayConverter,
                                onItemSelected = {
                                    onTriggerItemSelectedChange.invoke(it)
                                    jwindow.dispose()
                                }
                            )
                        }
                    }
                    jwindow.contentPane.add(composePopupContent, BorderLayout.CENTER)
                    jwindow.invalidate()
                    jwindow.validate()
                    jwindow.isVisible = true
                    jwindow.pack()
                },
                interactionState = interactionState,
                indication = null
            )
            .comboBoxLocator(auroraTopLeftOffset, auroraSize),
        contentAlignment = Alignment.TopStart
    ) {
        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            isTextInFilledArea = true
        )
        // And the arrow color
        val arrowColor = getStateAwareColor(
            modelStateInfo,
            decorationAreaType, ColorSchemeAssociationKind.MARK
        ) { it.markColor }


        if (backgroundAppearanceStrategy != BackgroundAppearanceStrategy.NEVER) {
            // Populate the cached color scheme for filling the button container
            // based on the current model state info
            populateColorScheme(
                drawingCache.colorScheme, modelStateInfo, decorationAreaType,
                ColorSchemeAssociationKind.FILL
            )
            // And retrieve the container fill colors
            val fillUltraLight = drawingCache.colorScheme.ultraLightColor
            val fillExtraLight = drawingCache.colorScheme.extraLightColor
            val fillLight = drawingCache.colorScheme.lightColor
            val fillMid = drawingCache.colorScheme.midColor
            val fillDark = drawingCache.colorScheme.darkColor
            val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
            val fillIsDark = drawingCache.colorScheme.isDark

            // Populate the cached color scheme for drawing the button border
            // based on the current model state info
            populateColorScheme(
                drawingCache.colorScheme, modelStateInfo, decorationAreaType,
                ColorSchemeAssociationKind.BORDER
            )
            // And retrieve the border colors
            val borderUltraLight = drawingCache.colorScheme.ultraLightColor
            val borderExtraLight = drawingCache.colorScheme.extraLightColor
            val borderLight = drawingCache.colorScheme.lightColor
            val borderMid = drawingCache.colorScheme.midColor
            val borderDark = drawingCache.colorScheme.darkColor
            val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
            val borderIsDark = drawingCache.colorScheme.isDark

            val fillPainter = AuroraSkin.painters.fillPainter
            val borderPainter = AuroraSkin.painters.borderPainter

            val alpha: Float
            if (backgroundAppearanceStrategy == BackgroundAppearanceStrategy.FLAT) {
                // For flat buttons, compute the combined contribution of all
                // non-disabled states - ignoring ComponentState.ENABLED
                alpha = modelStateInfo.stateContributionMap
                    .filter { !it.key.isDisabled && (it.key != ComponentState.ENABLED) }
                    .values.sumByDouble { it.contribution.toDouble() }.toFloat()
            } else {
                alpha = if (currentState.value.isDisabled)
                    AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f
            }

            Canvas(modifier.matchParentSize()) {
                val width = this.size.width
                val height = this.size.height

                withTransform({
                    clipRect(left = 0.0f, top = 0.0f, right = width, bottom = height, clipOp = ClipOp.Intersect)
                }) {
                    val outline = buttonShaper.getButtonOutline(
                        width = width,
                        height = height,
                        extraInsets = 0.5f,
                        isInner = false,
                        sides = ButtonSides(),
                        drawScope = this
                    )

                    val outlineBoundingRect = outline.bounds
                    if (outlineBoundingRect.isEmpty) {
                        return@withTransform
                    }

                    // Populate the cached color scheme for filling the button container
                    drawingCache.colorScheme.ultraLight = fillUltraLight
                    drawingCache.colorScheme.extraLight = fillExtraLight
                    drawingCache.colorScheme.light = fillLight
                    drawingCache.colorScheme.mid = fillMid
                    drawingCache.colorScheme.dark = fillDark
                    drawingCache.colorScheme.ultraDark = fillUltraDark
                    drawingCache.colorScheme.isDark = fillIsDark
                    drawingCache.colorScheme.foreground = textColor
                    fillPainter.paintContourBackground(
                        this, this.size, outline, drawingCache.colorScheme, alpha
                    )

                    // Populate the cached color scheme for drawing the button border
                    drawingCache.colorScheme.ultraLight = borderUltraLight
                    drawingCache.colorScheme.extraLight = borderExtraLight
                    drawingCache.colorScheme.light = borderLight
                    drawingCache.colorScheme.mid = borderMid
                    drawingCache.colorScheme.dark = borderDark
                    drawingCache.colorScheme.ultraDark = borderUltraDark
                    drawingCache.colorScheme.isDark = borderIsDark
                    drawingCache.colorScheme.foreground = textColor

                    val innerOutline = if (borderPainter.isPaintingInnerOutline)
                        buttonShaper.getButtonOutline(
                            width = width,
                            height = height,
                            extraInsets = 1.0f,
                            isInner = true,
                            sides = ButtonSides(),
                            drawScope = this
                        ) else null

                    borderPainter.paintBorder(
                        this, this.size, outline, innerOutline, drawingCache.colorScheme, alpha
                    )

                    val arrowWidth = if (popupPlacementStrategy.isHorizontal)
                        ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx() else
                        ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx()
                    val arrowHeight =
                        if (popupPlacementStrategy.isHorizontal)
                            ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx() else
                            ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx()
                    // TODO - support RTL
                    translate(
                        left = width
                                - ButtonSizingConstants.DefaultButtonContentPadding.end.toPx()
                                - arrowWidth,
                        top = (height - arrowHeight) / 2.0f
                    ) {
                        drawArrow(
                            drawScope = this,
                            width = arrowWidth,
                            height = arrowHeight,
                            strokeWidth = 2.0.dp.toPx(),
                            direction = popupPlacementStrategy,
                            layoutDirection = layoutDirection,
                            color = arrowColor
                        )
                    }
                }
            }
        }

        // Pass our text color and model state snapshot to the children
        Providers(
            AmbientTextColor provides textColor,
            AmbientModelStateInfoSnapshot provides modelStateInfo.getSnapshot()
        ) {
            Layout(
                // TODO - revisit this maybe
                modifier = Modifier.padding(
                    PaddingValues(
                        start = ButtonSizingConstants.DefaultButtonContentPadding.start,
                        end = ButtonSizingConstants.DefaultButtonContentPadding.end +
                                ComboBoxSizingConstants.DefaultComboBoxContentArrowGap +
                                ComboBoxSizingConstants.DefaultComboBoxArrowWidth,
                        top = ButtonSizingConstants.DefaultButtonContentPadding.top,
                        bottom = ButtonSizingConstants.DefaultButtonContentPadding.bottom
                    )
                ),
                content = {
                    AuroraText(displayConverter.invoke(selectedItem))
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

                // Get the preferred size
                var uiPreferredWidth = contentTotalWidth
                var uiPreferredHeight = contentMaxHeight

                // Bump up to default minimums if necessary
                uiPreferredWidth = max(uiPreferredWidth, ButtonSizingConstants.DefaultButtonContentWidth.toIntPx())
                uiPreferredHeight = max(uiPreferredHeight, ButtonSizingConstants.DefaultButtonContentHeight.toIntPx())

                // And ask the button shaper for the final sizing
                val finalSize = buttonShaper.getPreferredSize(
                    uiPreferredWidth.toFloat(), uiPreferredHeight.toFloat()
                )

                // Center children vertically within the vertical space
                layout(width = finalSize.width.toInt(), height = finalSize.height.toInt()) {
                    // TODO - add RTL support
                    var xPosition = 0

                    placeables.forEach { placeable ->
                        placeable.placeRelative(
                            x = xPosition,
                            y = (finalSize.height.toInt() - placeable.height) / 2
                        )
                        xPosition += placeable.width
                    }
                }
            }
        }
    }
}

@Composable
private fun <E> ComboBoxPopupContent(
    window: JWindow,
    anchorSize: AuroraSize,
    popupPlacementStrategy: PopupPlacementStrategy,
    items: List<E>,
    displayConverter: (E) -> String,
    onItemSelected: (E) -> Unit,
) {
    val density = AmbientDensity.current.density
    Box(
        modifier = Modifier.auroraBackground(window = window).onGloballyPositioned {
            // Get the size of the content and update the popup window bounds
            val popupWidth = (it.size.width / density).toInt()
            val popupHeight = (it.size.height / density).toInt()

            // TODO - support RTL for startward and endward
            // TODO - figure out the extra factor
            val popupRect = when (popupPlacementStrategy) {
                PopupPlacementStrategy.DOWNWARD -> Rectangle(
                    window.x,
                    window.y + (anchorSize.height / (2 * density)).toInt(),
                    popupWidth,
                    popupHeight
                )
                PopupPlacementStrategy.UPWARD -> Rectangle(
                    window.x,
                    window.y - popupHeight / 2,
                    popupWidth,
                    popupHeight
                )
                PopupPlacementStrategy.STARTWARD -> Rectangle(
                    window.x - popupWidth / 2,
                    window.y,
                    popupWidth,
                    popupHeight
                )
                PopupPlacementStrategy.ENDWARD -> Rectangle(
                    window.x + (anchorSize.width / (2 * density)).toInt(),
                    window.y,
                    popupWidth,
                    popupHeight
                )
                PopupPlacementStrategy.CENTERED_VERTICALLY -> Rectangle(
                    window.x,
                    window.y + (anchorSize.height / (4 * density)).toInt() - popupHeight / 4,
                    popupWidth,
                    popupHeight
                )
            }

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
            window.invalidate()
            window.validate()
            window.pack()
        }
    ) {
        ComboBoxPopupColumn {
            for (item in items) {
                AuroraButton(
                    enabled = true,
                    onClick = { onItemSelected.invoke(item) },
                    sides = ButtonSides(straightSides = Side.values().toSet()),
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                    sizingStrategy = ButtonSizingStrategy.EXTENDED,
                ) {
                    AuroraText(text = displayConverter.invoke(item), maxLines = 1)
                }
            }
        }
    }
}

@Composable
private fun ComboBoxPopupColumn(content: @Composable () -> Unit) {
    Layout(content = content) { measurables, _ ->
        // The column width is determined by the widest child
        val contentTotalWidth = measurables.maxOf { it.maxIntrinsicWidth(Int.MAX_VALUE) }

        val placeables = measurables.map { measurable ->
            // Measure each child with fixed (widest) width
            measurable.measure(Constraints.fixedWidth(contentTotalWidth))
        }

        // The children are laid out in a column
        val contentMaxHeight = placeables.sumBy { it.height }

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

