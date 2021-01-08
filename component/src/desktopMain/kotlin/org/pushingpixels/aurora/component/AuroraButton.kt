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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.utils.*
import kotlin.math.max

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

object ButtonSizingConstants {
    val DefaultButtonContentPadding = PaddingValues(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
    val CompactButtonContentPadding = PaddingValues(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
    val DefaultButtonIconTextGap = 6.dp
    val DefaultButtonContentWidth = 60.dp
    val DefaultButtonContentHeight = 16.dp
}

@Immutable
private class ButtonDrawingCache(
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

@Composable
fun AuroraToggleButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    onTriggerSelectedChange: (Boolean) -> Unit = {},
    sides: ButtonSides = ButtonSides(),
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
    sizingStrategy: ButtonSizingStrategy = ButtonSizingStrategy.EXTENDED,
    contentPadding: PaddingValues = ButtonSizingConstants.DefaultButtonContentPadding,
    content: @Composable () -> Unit
) {
    AuroraToggleButton(
        modifier = modifier,
        enabled = enabled,
        selected = selected,
        onTriggerSelectedChange = onTriggerSelectedChange,
        sides = sides,
        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
        sizingStrategy = sizingStrategy,
        contentPadding = contentPadding,
        interactionState = remember { InteractionState() },
        stateTransitionFloat = AnimatedFloat(0.0f, AmbientAnimationClock.current.asDisposableClock()),
        content = content
    )
}

@Composable
private fun AuroraToggleButton(
    modifier: Modifier,
    enabled: Boolean,
    selected: Boolean,
    onTriggerSelectedChange: (Boolean) -> Unit,
    sides: ButtonSides,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    sizingStrategy: ButtonSizingStrategy,
    contentPadding: PaddingValues,
    interactionState: InteractionState,
    stateTransitionFloat: AnimatedFloat,
    content: @Composable () -> Unit
) {
    val drawingCache = remember { ButtonDrawingCache() }
    var rollover by remember { mutableStateOf(false) }

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = enabled,
                isRollover = rollover,
                isSelected = selected,
                isPressed = Interaction.Pressed in interactionState
            )
        )
    }

    val modelStateInfo = remember {
        ModelStateInfo(
            ComponentState.getState(
                isEnabled = enabled,
                isRollover = rollover,
                isSelected = selected,
                isPressed = Interaction.Pressed in interactionState
            )
        )
    }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        enabled = enabled,
        selected = selected,
        rollover = rollover,
        pressed = Interaction.Pressed in interactionState,
        stateTransitionFloat = stateTransitionFloat,
        duration = AuroraSkin.animationConfig.regular
    )

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val painters = AuroraSkin.painters
    val buttonShaper = AuroraSkin.buttonShaper

    // Transition for the selection state
    if (!::SelectedTransitionDefinition.isInitialized) {
        SelectedTransitionDefinition =
            getSelectedTransitionDefinition(AuroraSkin.animationConfig.regular)
    }
    val selectionTransitionState = transition(
        definition = SelectedTransitionDefinition,
        initState = selected,
        toState = selected
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
            .toggleable(
                value = selected,
                enabled = enabled,
                interactionState = interactionState,
                indication = null,
                onValueChange = {
                    onTriggerSelectedChange.invoke(it)
                }),
        contentAlignment = Alignment.TopStart
    ) {
        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            isTextInFilledArea = true
        )

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

            val fillPainter = painters.fillPainter
            val borderPainter = painters.borderPainter

            val alpha: Float
            if (backgroundAppearanceStrategy == BackgroundAppearanceStrategy.FLAT) {
                // For flat buttons, compute the combined contribution of all
                // non-disabled states - ignoring ComponentState.ENABLED
                alpha = modelStateInfo.stateContributionMap
                    .filter { !it.key.isDisabled && (it.key != ComponentState.ENABLED) }
                    .values.sumByDouble { it.contribution.toDouble() }.toFloat()
            } else {
                alpha = if (currentState.value.isDisabled)
                    skinColors.getAlpha(decorationAreaType, currentState.value) else 1.0f
            }

            Canvas(modifier.matchParentSize()) {
                val width = this.size.width
                val height = this.size.height

                val openDelta = 3
                // TODO - add RTL support
                val deltaLeft = if (sides.openSides.contains(Side.START)) openDelta else 0
                val deltaRight = if (sides.openSides.contains(Side.END)) openDelta else 0
                val deltaTop = if (sides.openSides.contains(Side.TOP)) openDelta else 0
                val deltaBottom = if (sides.openSides.contains(Side.BOTTOM)) openDelta else 0

                withTransform({
                    clipRect(left = 0.0f, top = 0.0f, right = width, bottom = height, clipOp = ClipOp.Intersect)
                    translate(left = -deltaLeft.toFloat(), top = -deltaTop.toFloat())
                }) {
                    val outline = buttonShaper.getButtonOutline(
                        width = width + deltaLeft + deltaRight,
                        height = height + deltaTop + deltaBottom,
                        extraInsets = 0.5f,
                        isInner = false,
                        sides = sides,
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
                            width = width + deltaLeft + deltaRight,
                            height = height + deltaTop + deltaBottom,
                            extraInsets = 1.0f,
                            isInner = true,
                            sides = sides,
                            drawScope = this
                        ) else null

                    borderPainter.paintBorder(
                        this, this.size, outline, innerOutline, drawingCache.colorScheme, alpha
                    )
                }
            }
        }

        // Pass our text color and model state snapshot to the children
        Providers(
            AmbientTextColor provides textColor,
            AmbientModelStateInfoSnapshot provides modelStateInfo.getSnapshot()
        ) {
            Layout(
                modifier = Modifier.padding(contentPadding),
                content = content
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

                if (sizingStrategy == ButtonSizingStrategy.EXTENDED) {
                    // Bump up to default minimums if necessary
                    uiPreferredWidth = max(uiPreferredWidth, ButtonSizingConstants.DefaultButtonContentWidth.toIntPx())
                    uiPreferredHeight =
                        max(uiPreferredHeight, ButtonSizingConstants.DefaultButtonContentHeight.toIntPx())
                }

                // And ask the button shaper for the final sizing
                val finalSize = buttonShaper.getPreferredSize(
                    uiPreferredWidth.toFloat(), uiPreferredHeight.toFloat()
                )

                // Center children vertically within the vertical space
                layout(width = finalSize.width.toInt(), height = finalSize.height.toInt()) {
                    // TODO - add RTL support
                    var xPosition = (finalSize.width.toInt() - contentTotalWidth) / 2

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
fun AuroraButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    sides: ButtonSides = ButtonSides(),
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
    sizingStrategy: ButtonSizingStrategy = ButtonSizingStrategy.EXTENDED,
    contentPadding: PaddingValues = ButtonSizingConstants.DefaultButtonContentPadding,
    content: @Composable () -> Unit
) {
    AuroraButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        sides = sides,
        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
        sizingStrategy = sizingStrategy,
        contentPadding = contentPadding,
        interactionState = remember { InteractionState() },
        stateTransitionFloat = AnimatedFloat(0.0f, AmbientAnimationClock.current.asDisposableClock()),
        content = content
    )
}

@Composable
private fun AuroraButton(
    modifier: Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    sides: ButtonSides,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    sizingStrategy: ButtonSizingStrategy,
    contentPadding: PaddingValues,
    interactionState: InteractionState,
    stateTransitionFloat: AnimatedFloat,
    content: @Composable () -> Unit
) {
    val drawingCache = remember { ButtonDrawingCache() }

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

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val painters = AuroraSkin.painters
    val buttonShaper = AuroraSkin.buttonShaper

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
                onClick = onClick,
                interactionState = interactionState,
                indication = null
            ),
        contentAlignment = Alignment.TopStart
    ) {
        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            isTextInFilledArea = true
        )

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

            val fillPainter = painters.fillPainter
            val borderPainter = painters.borderPainter

            val alpha: Float
            if (backgroundAppearanceStrategy == BackgroundAppearanceStrategy.FLAT) {
                // For flat buttons, compute the combined contribution of all
                // non-disabled states - ignoring ComponentState.ENABLED
                alpha = modelStateInfo.stateContributionMap
                    .filter { !it.key.isDisabled && (it.key != ComponentState.ENABLED) }
                    .values.sumByDouble { it.contribution.toDouble() }.toFloat()
            } else {
                alpha = if (currentState.value.isDisabled)
                    skinColors.getAlpha(decorationAreaType, currentState.value) else 1.0f
            }

            Canvas(modifier.matchParentSize()) {
                val width = this.size.width
                val height = this.size.height

                val openDelta = 3
                // TODO - add RTL support
                val deltaLeft = if (sides.openSides.contains(Side.START)) openDelta else 0
                val deltaRight = if (sides.openSides.contains(Side.END)) openDelta else 0
                val deltaTop = if (sides.openSides.contains(Side.TOP)) openDelta else 0
                val deltaBottom = if (sides.openSides.contains(Side.BOTTOM)) openDelta else 0

                withTransform({
                    clipRect(left = 0.0f, top = 0.0f, right = width, bottom = height, clipOp = ClipOp.Intersect)
                    translate(left = -deltaLeft.toFloat(), top = -deltaTop.toFloat())
                }) {
                    val outline = buttonShaper.getButtonOutline(
                        width = width + deltaLeft + deltaRight,
                        height = height + deltaTop + deltaBottom,
                        extraInsets = 0.5f,
                        isInner = false,
                        sides = sides,
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
                            width = width + deltaLeft + deltaRight,
                            height = height + deltaTop + deltaBottom,
                            extraInsets = 1.0f,
                            isInner = true,
                            sides = sides,
                            drawScope = this
                        ) else null

                    borderPainter.paintBorder(
                        this, this.size, outline, innerOutline, drawingCache.colorScheme, alpha
                    )
                }
            }
        }

        // Pass our text color and model state snapshot to the children
        Providers(
            AmbientTextColor provides textColor,
            AmbientModelStateInfoSnapshot provides modelStateInfo.getSnapshot()
        ) {
            Layout(
                modifier = Modifier.padding(contentPadding),
                content = content
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

                if (sizingStrategy == ButtonSizingStrategy.EXTENDED) {
                    // Bump up to default minimums if necessary
                    uiPreferredWidth = max(uiPreferredWidth, ButtonSizingConstants.DefaultButtonContentWidth.toIntPx())
                    uiPreferredHeight =
                        max(uiPreferredHeight, ButtonSizingConstants.DefaultButtonContentHeight.toIntPx())
                }

                // And ask the button shaper for the final sizing
                val finalSize = buttonShaper.getPreferredSize(
                    uiPreferredWidth.toFloat(), uiPreferredHeight.toFloat()
                )

                // Center children vertically within the vertical space
                layout(width = finalSize.width.toInt(), height = finalSize.height.toInt()) {
                    // TODO - add RTL support
                    var xPosition = (finalSize.width.toInt() - contentTotalWidth) / 2

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

fun Modifier.auroraButtonIconPadding() =
    this.then(padding(end = ButtonSizingConstants.DefaultButtonIconTextGap))
