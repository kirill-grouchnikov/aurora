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

import androidx.compose.animation.core.*
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.layout.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.icon.AuroraThemedIcon
import java.awt.*
import javax.swing.JWindow
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
private class CommandButtonDrawingCache(
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
fun AuroraCommandButton(
    command: Command,
    presentationModel: CommandButtonPresentationModel
) {
    AuroraCommandButton(
        command = command,
        parentWindow = null,
        extraAction = null,
        presentationModel = presentationModel
    )
}

@Composable
private fun AuroraCommandButton(
    command: Command,
    parentWindow: JWindow? = null,
    extraAction: (() -> Unit)? = null,
    presentationModel: CommandButtonPresentationModel
) {
    val drawingCache = remember { CommandButtonDrawingCache() }

    val actionInteractionSource = remember { MutableInteractionSource() }
    val popupInteractionSource = remember { MutableInteractionSource() }

    var actionRollover by remember { mutableStateOf(false) }
    var popupRollover by remember { mutableStateOf(false) }
    val combinedRollover = actionRollover or popupRollover

    val isActionPressed by actionInteractionSource.collectIsPressedAsState()
    val isPopupPressed by popupInteractionSource.collectIsPressedAsState()

    val currentActionState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = command.isActionEnabled,
                isRollover = actionRollover,
                isSelected = command.isActionToggle and command.isActionToggleSelected,
                isPressed = isActionPressed
            )
        )
    }
    val currentPopupState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = command.isSecondaryEnabled,
                isRollover = popupRollover,
                isSelected = false,
                isPressed = isPopupPressed
            )
        )
    }

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = AuroraSkin.buttonShaper
    val painters = AuroraSkin.painters

    val auroraTopLeftOffset = remember { AuroraOffset(0.0f, 0.0f) }
    val auroraSize = AuroraSize(0, 0)
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val resourceLoader = LocalFontLoader.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    // Transition for the action selection state
    val actionSelectionTransition =
        updateTransition(command.isActionToggle and command.isActionToggleSelected)
    val actionSelectedFraction by actionSelectionTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the action rollover state
    val actionRolloverTransition = updateTransition(actionRollover)
    val actionRolloverFraction by actionRolloverTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the action pressed state
    val actionPressedTransition = updateTransition(isActionPressed)
    val actionPressedFraction by actionPressedTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the action enabled state
    val actionEnabledTransition = updateTransition(command.isActionEnabled)
    val actionEnabledFraction by actionEnabledTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // TODO - figure out why the animations are not running without looking
    //  at the result (and how it looks like in the new animation APIs)
    val actionTotalFraction =
        actionSelectedFraction + actionRolloverFraction + actionPressedFraction + actionEnabledFraction

    // Transition for the combined rollover state
    val combinedRolloverTransition = updateTransition(combinedRollover)
    val combinedRolloverFraction by combinedRolloverTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    val actionModelStateInfo = remember { ModelStateInfo(currentActionState.value) }
    val actionTransitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = actionModelStateInfo,
        currentState = currentActionState,
        transitionInfo = actionTransitionInfo,
        enabled = command.isActionEnabled,
        selected = command.isActionToggle and command.isActionToggleSelected,
        rollover = actionRollover,
        pressed = isActionPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (actionTransitionInfo.value != null) {
        LaunchedEffect(currentActionState.value) {
            val transitionFloat = Animatable(actionTransitionInfo.value!!.from)
            val result = transitionFloat.animateTo(
                targetValue = actionTransitionInfo.value!!.to,
                animationSpec = tween(durationMillis = actionTransitionInfo.value!!.duration)
            ) {
                actionModelStateInfo.updateActiveStates(value)
            }

            if (result.endReason == AnimationEndReason.Finished) {
                actionModelStateInfo.updateActiveStates(1.0f)
                actionModelStateInfo.clear(currentActionState.value)
            }
        }
    }

    // Transition for the popup selection state
    val popupSelectionTransition = updateTransition(false)
    val popupSelectedFraction by popupSelectionTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the popup rollover state
    val popupRolloverTransition = updateTransition(popupRollover)
    val popupRolloverFraction by popupRolloverTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the popup pressed state
    val popupPressedTransition = updateTransition(isPopupPressed)
    val popupPressedFraction by popupPressedTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the popup enabled state
    val popupEnabledTransition = updateTransition(command.isSecondaryEnabled)
    val popupEnabledFraction by popupEnabledTransition.animateFloat(transitionSpec = {
        tween(durationMillis = AuroraSkin.animationConfig.regular)
    }) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // TODO - figure out why the animations are not running without looking
    //  at the result (and how it looks like in the new animation APIs)
    val totalPopupFraction =
        popupSelectedFraction + popupRolloverFraction + popupPressedFraction + popupEnabledFraction

    val popupModelStateInfo = remember { ModelStateInfo(currentPopupState.value) }
    val popupTransitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = popupModelStateInfo,
        currentState = currentPopupState,
        transitionInfo = popupTransitionInfo,
        enabled = command.isSecondaryEnabled,
        selected = false,
        rollover = popupRollover,
        pressed = isPopupPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (popupTransitionInfo.value != null) {
        LaunchedEffect(currentPopupState.value) {
            val transitionFloat = Animatable(popupTransitionInfo.value!!.from)
            val result = transitionFloat.animateTo(
                targetValue = popupTransitionInfo.value!!.to,
                animationSpec = tween(durationMillis = popupTransitionInfo.value!!.duration)
            ) {
                popupModelStateInfo.updateActiveStates(value)
            }

            if (result.endReason == AnimationEndReason.Finished) {
                popupModelStateInfo.updateActiveStates(1.0f)
                popupModelStateInfo.clear(currentPopupState.value)
            }
        }
    }

    val layoutManager = remember {
        presentationModel.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            resourceLoader = resourceLoader
        )
    }

    val hasIcon = (command.iconFactory != null)
    val hasAction = (command.action != null)
    val isActionEnabled = command.isActionEnabled
    val hasPopup = (command.secondaryContentModel != null)
    val isTextInActionArea = hasAction && (presentationModel.textClick == TextClick.ACTION)
    Layout(
        modifier = Modifier.commandButtonLocator(auroraTopLeftOffset, auroraSize),
        content = {
            Box(
                modifier = Modifier
                    // TODO - this needs to be toggleable for toggleable action
                    .clickable(
                        enabled = isActionEnabled,
                        onClick = {
                            command.action?.invoke()
                            extraAction?.invoke()
                        },
                        interactionSource = actionInteractionSource,
                        indication = null
                    ).pointerMoveFilter(onEnter = {
                        val wasRollover = actionRollover
                        actionRollover = true
                        if (isActionEnabled && !wasRollover) {
                            command.actionPreview?.onCommandPreviewActivated(command)
                        }
                        false
                    }, onExit = {
                        val wasRollover = actionRollover
                        actionRollover = false
                        if (isActionEnabled && wasRollover) {
                            command.actionPreview?.onCommandPreviewCanceled(command)
                        }
                        false
                    }, onMove = {
                        false
                    })
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.NEVER) {
                    // Populate the cached color scheme for filling the button container
                    // based on the current model state info
                    populateColorScheme(
                        drawingCache.colorScheme,
                        actionModelStateInfo,
                        currentActionState.value,
                        decorationAreaType,
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
                        drawingCache.colorScheme,
                        actionModelStateInfo,
                        currentActionState.value,
                        decorationAreaType,
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

                    val actionAlpha = max(combinedRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.FLAT) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            actionModelStateInfo.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.ENABLED) }
                                .values.sumByDouble { it.contribution.toDouble() }.toFloat()
                        } else {
                            if (currentActionState.value.isDisabled)
                                skinColors.getAlpha(
                                    decorationAreaType,
                                    currentActionState.value
                                ) else 1.0f
                        }
                    )

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = this.size.width
                        val height = this.size.height

                        val openSides: Set<Side> = if (hasPopup) setOf(Side.END) else emptySet()
                        val straightSides =
                            if (presentationModel.isMenu) Side.values().toSet() else openSides
                        val sides =
                            ButtonSides(openSides = openSides, straightSides = straightSides)

                        val openDelta = 3
                        val deltaLeft = if (sides.openSides.contains(Side.START)) openDelta else 0
                        val deltaRight = if (sides.openSides.contains(Side.END)) openDelta else 0
                        val deltaTop = if (sides.openSides.contains(Side.TOP)) openDelta else 0
                        val deltaBottom =
                            if (sides.openSides.contains(Side.BOTTOM)) openDelta else 0

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = width,
                                bottom = height,
                                clipOp = ClipOp.Intersect
                            )
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
                            drawingCache.colorScheme.foreground = Color.Black
                            fillPainter.paintContourBackground(
                                this, this.size, outline, drawingCache.colorScheme, actionAlpha
                            )

                            // Populate the cached color scheme for drawing the button border
                            drawingCache.colorScheme.ultraLight = borderUltraLight
                            drawingCache.colorScheme.extraLight = borderExtraLight
                            drawingCache.colorScheme.light = borderLight
                            drawingCache.colorScheme.mid = borderMid
                            drawingCache.colorScheme.dark = borderDark
                            drawingCache.colorScheme.ultraDark = borderUltraDark
                            drawingCache.colorScheme.isDark = borderIsDark
                            drawingCache.colorScheme.foreground = Color.Black

                            val innerOutline =
                                if (borderPainter.isPaintingInnerOutline) buttonShaper.getButtonOutline(
                                    width = width + deltaLeft + deltaRight,
                                    height = height + deltaTop + deltaBottom,
                                    extraInsets = 1.0f,
                                    isInner = true,
                                    sides = sides,
                                    drawScope = this
                                ) else null

                            borderPainter.paintBorder(
                                this,
                                this.size,
                                outline,
                                innerOutline,
                                drawingCache.colorScheme,
                                actionAlpha
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.clickable(
                    enabled = command.isSecondaryEnabled,
                    onClick = {
                        // TODO - move off of JWindow when https://github.com/JetBrains/compose-jb/issues/195
                        //  is addressed
                        val popupContentWindow = AuroraPopupWindow()
                        popupContentWindow.focusableWindowState = false
                        popupContentWindow.type = Window.Type.POPUP
                        popupContentWindow.isAlwaysOnTop = true

                        // TODO - hopefully temporary. Mark the popup window as fully transparent
                        //  so that when it is globally positioned, we can size it to the actual
                        //  content and make it fully opaque
                        popupContentWindow.opacity = 0.0f

                        val locationOnScreen =
                            (parentWindow ?: AppManager.focusedWindow!!.window).locationOnScreen

                        // anchor the popup window to the bottom left corner of the component
                        // in screen coordinates
                        // TODO - figure out the sizing (see above)
                        val initialWidth = 1000
                        val initialHeight = 1000
                        popupContentWindow.setBounds(
                            (locationOnScreen.x + auroraTopLeftOffset.x / density.density).toInt(),
                            (locationOnScreen.y + auroraTopLeftOffset.y / density.density).toInt(),
                            initialWidth,
                            initialHeight
                        )

                        val popupContent = ComposePanel()
                        popupContent.preferredSize = Dimension(initialWidth, initialHeight)
                        popupContent.setContent {
                            CompositionLocalProvider(
                                LocalDecorationAreaType provides decorationAreaType,
                                LocalSkinColors provides skinColors,
                                LocalButtonShaper provides buttonShaper,
                                LocalPainters provides painters,
                                LocalAnimationConfig provides AuroraSkin.animationConfig
                            ) {
                                CommandButtonPopupContent(
                                    popupContentWindow = popupContentWindow,
                                    anchorSize = auroraSize,
                                    command = command,
                                    presentationModel = presentationModel
                                )
                            }
                        }
                        popupContentWindow.contentPane.layout = BorderLayout()
                        popupContentWindow.contentPane.add(popupContent, BorderLayout.CENTER)
                        popupContentWindow.invalidate()
                        popupContentWindow.validate()
                        popupContentWindow.isVisible = true
                        popupContentWindow.pack()
                    },
                    interactionSource = popupInteractionSource,
                    indication = null
                ).pointerMoveFilter(onEnter = {
                    popupRollover = true
                    false
                }, onExit = {
                    popupRollover = false
                    false
                }, onMove = {
                    false
                })
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.NEVER) {
                    // Populate the cached color scheme for filling the button container
                    // based on the current model state info
                    populateColorScheme(
                        drawingCache.colorScheme,
                        popupModelStateInfo,
                        currentPopupState.value,
                        decorationAreaType,
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
                        drawingCache.colorScheme,
                        popupModelStateInfo,
                        currentPopupState.value,
                        decorationAreaType,
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

                    val popupAlpha = max(combinedRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.FLAT) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            popupModelStateInfo.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.ENABLED) }
                                .values.sumByDouble { it.contribution.toDouble() }.toFloat()
                        } else {
                            if (currentPopupState.value.isDisabled)
                                skinColors.getAlpha(
                                    decorationAreaType,
                                    currentPopupState.value
                                ) else 1.0f
                        }
                    )

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = this.size.width
                        val height = this.size.height

                        val openSides: Set<Side> = if (hasAction) setOf(Side.START) else emptySet()
                        val straightSides =
                            if (presentationModel.isMenu) Side.values().toSet() else openSides
                        val sides =
                            ButtonSides(openSides = openSides, straightSides = straightSides)

                        val openDelta = 3
                        val deltaLeft = if (sides.openSides.contains(Side.START)) openDelta else 0
                        val deltaRight = if (sides.openSides.contains(Side.END)) openDelta else 0
                        val deltaTop = if (sides.openSides.contains(Side.TOP)) openDelta else 0
                        val deltaBottom =
                            if (sides.openSides.contains(Side.BOTTOM)) openDelta else 0

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = width,
                                bottom = height,
                                clipOp = ClipOp.Intersect
                            )
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
                            drawingCache.colorScheme.foreground = Color.Black
                            fillPainter.paintContourBackground(
                                this, this.size, outline, drawingCache.colorScheme, popupAlpha
                            )

                            // Populate the cached color scheme for drawing the button border
                            drawingCache.colorScheme.ultraLight = borderUltraLight
                            drawingCache.colorScheme.extraLight = borderExtraLight
                            drawingCache.colorScheme.light = borderLight
                            drawingCache.colorScheme.mid = borderMid
                            drawingCache.colorScheme.dark = borderDark
                            drawingCache.colorScheme.ultraDark = borderUltraDark
                            drawingCache.colorScheme.isDark = borderIsDark
                            drawingCache.colorScheme.foreground = Color.Black

                            val innerOutline =
                                if (borderPainter.isPaintingInnerOutline) buttonShaper.getButtonOutline(
                                    width = width + deltaLeft + deltaRight,
                                    height = height + deltaTop + deltaBottom,
                                    extraInsets = 1.0f,
                                    isInner = true,
                                    sides = sides,
                                    drawScope = this
                                ) else null

                            borderPainter.paintBorder(
                                this,
                                this.size,
                                outline,
                                innerOutline,
                                drawingCache.colorScheme,
                                popupAlpha
                            )
                        }
                    }
                }
            }

            if (hasIcon) {
                // Icon can be in action or popup area
                val modelStateInfoForIcon =
                    if (hasAction) actionModelStateInfo else popupModelStateInfo
                val currStateForIcon =
                    if (hasAction) currentActionState.value else currentPopupState.value
                CommandButtonIconContent(
                    command,
                    presentationModel,
                    layoutManager.getPreferredIconSize(),
                    modelStateInfoForIcon,
                    currStateForIcon
                )
            }

            // Text content can be in action or popup area. Use the matching model
            // to determine the text color
            val modelStateInfoForText =
                if (isTextInActionArea) actionModelStateInfo else popupModelStateInfo
            val currStateForText =
                if (isTextInActionArea) currentActionState.value else currentPopupState.value
            CommandButtonTextContent(command, modelStateInfoForText, currStateForText,
                resolvedTextStyle)

            // Popup action (arrow) if we need one
            if (hasPopup) {
                CommandButtonPopupIconContent(
                    presentationModel,
                    popupModelStateInfo,
                    currentPopupState.value
                )
            }

            // Separator between action and popup areas if we have both
            if (hasAction and hasPopup) {
                when (layoutManager.getSeparatorOrientation()) {
                    CommandButtonLayoutManager.CommandButtonSeparatorOrientation.VERTICAL ->
                        AuroraVerticalSeparator(
                            modifier = Modifier.alpha(combinedRolloverFraction),
                            startGradientAmount = 4.dp,
                            endGradientAmount = 4.dp
                        )
                    CommandButtonLayoutManager.CommandButtonSeparatorOrientation.HORIZONTAL ->
                        AuroraHorizontalSeparator(
                            modifier = Modifier.alpha(combinedRolloverFraction),
                            startGradientAmount = 4.dp,
                            endGradientAmount = 4.dp
                        )
                }
            }
        }) { measurables, constraints ->

        // Pass the constraints from the parent (which may or may not use fixed width
        // or height) so that the layout manager can decide what to do with available
        // space
        val layoutInfo = layoutManager.getLayoutInfo(
            constraints = constraints,
            command = command,
            presentationModel = presentationModel,
            paddingValues = ButtonSizingConstants.DefaultButtonContentPadding
        )

        // Measure the action and popup boxes
        var childIndex = 0
        val actionMeasurable = measurables[childIndex++]
        val actionPlaceable = actionMeasurable.measure(
            Constraints.fixed(
                width = layoutInfo.actionClickArea.width.roundToInt(),
                height = layoutInfo.actionClickArea.height.roundToInt()
            )
        )
        val popupMeasurable = measurables[childIndex++]
        val popupPlaceable = popupMeasurable.measure(
            Constraints.fixed(
                width = layoutInfo.popupClickArea.width.roundToInt(),
                height = layoutInfo.popupClickArea.height.roundToInt()
            )
        )
        var iconPlaceable: Placeable? = null
        if (hasIcon) {
            val iconMeasurable = measurables[childIndex++]
            iconPlaceable = iconMeasurable.measure(
                Constraints.fixed(
                    width = layoutInfo.iconRect.width.roundToInt(),
                    height = layoutInfo.iconRect.height.roundToInt()
                )
            )
        }
        val textMeasurable = measurables[childIndex++]
        val textPlaceable = textMeasurable.measure(
            Constraints.fixed(
                width = layoutInfo.textLayoutInfoList[0].textRect.width.roundToInt(),
                height = layoutInfo.textLayoutInfoList[0].textRect.height.roundToInt()
            )
        )
        var popupIconPlaceable: Placeable? = null
        if (hasPopup) {
            val popupIconMeasurable = measurables[childIndex++]
            popupIconPlaceable = popupIconMeasurable.measure(
                Constraints.fixed(
                    width = layoutInfo.popupActionRect.width.roundToInt(),
                    height = layoutInfo.popupActionRect.height.roundToInt()
                )
            )
        }
        var separatorPlaceable: Placeable? = null
        if (hasAction && hasPopup) {
            val separatorMeasurable = measurables[childIndex++]
            separatorPlaceable = separatorMeasurable.measure(
                Constraints.fixed(
                    width = layoutInfo.separatorArea!!.width.roundToInt(),
                    height = layoutInfo.separatorArea!!.height.roundToInt()
                )
            )
        }

        layout(
            width = layoutInfo.fullSize.width.toInt(), height = layoutInfo.fullSize.height.toInt()
        ) {
            actionPlaceable.placeRelative(
                x = layoutInfo.actionClickArea.left.roundToInt(),
                y = layoutInfo.actionClickArea.top.roundToInt()
            )
            popupPlaceable.placeRelative(
                x = layoutInfo.popupClickArea.left.roundToInt(),
                y = layoutInfo.popupClickArea.top.roundToInt()
            )
            iconPlaceable?.placeRelative(
                x = layoutInfo.iconRect.left.roundToInt(),
                y = layoutInfo.iconRect.top.roundToInt()
            )
            textPlaceable.placeRelative(
                x = layoutInfo.textLayoutInfoList[0].textRect.left.roundToInt(),
                y = layoutInfo.textLayoutInfoList[0].textRect.top.roundToInt()
            )
            popupIconPlaceable?.placeRelative(
                x = layoutInfo.popupActionRect.left.roundToInt(),
                y = layoutInfo.popupActionRect.top.roundToInt()
            )
            separatorPlaceable?.placeRelative(
                x = layoutInfo.separatorArea!!.left.roundToInt(),
                y = layoutInfo.separatorArea!!.top.roundToInt()
            )
        }
    }
}

@Composable
private fun CommandButtonTextContent(
    command: Command, modelStateInfo: ModelStateInfo, currState: ComponentState,
    style: TextStyle
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors

    // Compute the text color based on the passed model state (which can be action
    // or popup)
    val textColor = getTextColor(
        modelStateInfo = modelStateInfo,
        currState = currState,
        skinColors = skinColors,
        decorationAreaType = decorationAreaType,
        isTextInFilledArea = true
    )

    // Pass our text color and model state snapshot to the children
    CompositionLocalProvider(
        LocalTextColor provides textColor,
        LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState)
    ) {
        AuroraText(text = command.text, style = style)
    }
}

@Composable
private fun CommandButtonIconContent(
    command: Command, presentationModel: CommandButtonPresentationModel,
    iconSize: Dp, modelStateInfo: ModelStateInfo, currState: ComponentState
) {
    if (command.iconFactory != null) {
        val icon = remember(iconSize) { command.iconFactory.createNewIcon() }
        // TODO - why does this need to be divided by density?
        icon.setSize(
            iconSize / LocalDensity.current.density,
            iconSize / LocalDensity.current.density
        )
        val decorationAreaType = AuroraSkin.decorationAreaType
        val skinColors = AuroraSkin.colors

        // Compute the text color based on the passed model state (which can be action
        // or popup)
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currState,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            isTextInFilledArea = true
        )

        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState)
        ) {
            AuroraThemedIcon(
                icon = icon,
                disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                activeFilterStrategy = presentationModel.iconActiveFilterStrategy
            )
        }
    }
}

@Composable
private fun CommandButtonPopupIconContent(
    presentationModel: CommandButtonPresentationModel,
    modelStateInfo: ModelStateInfo, currState: ComponentState
) {
    val decorationAreaType = AuroraSkin.decorationAreaType

    val arrowColor = getStateAwareColor(
        modelStateInfo,
        currState,
        decorationAreaType,
        ColorSchemeAssociationKind.MARK
    ) { it.markColor }

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            val arrowWidth = if (presentationModel.popupPlacementStrategy.isHorizontal)
                ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx() else
                ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx()
            val arrowHeight =
                if (presentationModel.popupPlacementStrategy.isHorizontal)
                    ComboBoxSizingConstants.DefaultComboBoxArrowWidth.toPx() else
                    ComboBoxSizingConstants.DefaultComboBoxArrowHeight.toPx()
            translate(
                left = (size.width - arrowWidth) / 2.0f,
                top = (size.height - arrowHeight) / 2.0f
            ) {
                drawArrow(
                    drawScope = this,
                    width = arrowWidth,
                    height = arrowHeight,
                    strokeWidth = 2.0.dp.toPx(),
                    direction = presentationModel.popupPlacementStrategy,
                    layoutDirection = layoutDirection,
                    color = arrowColor
                )
            }
        }
    }
}

@Composable
private fun CommandButtonPopupContent(
    popupContentWindow: JWindow,
    anchorSize: AuroraSize,
    command: Command,
    presentationModel: CommandButtonPresentationModel
) {
    assert(command.secondaryContentModel != null) { "Secondary content model cannot be null here " }

    val borderScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = DecorationAreaType.NONE,
        associationKind = ColorSchemeAssociationKind.BORDER,
        componentState = ComponentState.ENABLED
    )
    val popupBorderColor = AuroraSkin.painters.borderPainter.getRepresentativeColor(borderScheme)
    val density = LocalDensity.current.density
    val contentSize = AuroraSize(0, 0)
    Box(modifier = Modifier.auroraBackground(window = popupContentWindow).onGloballyPositioned {
        // Get the size of the content and update the popup window bounds
        val popupWidth = (contentSize.width / density).toInt()
        val popupHeight = (contentSize.height / density).toInt()

        // TODO - support RTL for startward and endward
        // TODO - figure out the extra factor
        val popupRect = when (presentationModel.popupPlacementStrategy) {
            PopupPlacementStrategy.DOWNWARD -> Rectangle(
                popupContentWindow.x,
                popupContentWindow.y + (anchorSize.height / (2 * density)).toInt(),
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.UPWARD -> Rectangle(
                popupContentWindow.x,
                popupContentWindow.y - popupHeight / 2,
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.STARTWARD -> Rectangle(
                popupContentWindow.x - popupWidth / 2,
                popupContentWindow.y,
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.ENDWARD -> Rectangle(
                popupContentWindow.x + (anchorSize.width / (2 * density)).toInt(),
                popupContentWindow.y,
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.CENTERED_VERTICALLY -> Rectangle(
                popupContentWindow.x,
                popupContentWindow.y + (anchorSize.height / (4 * density)).toInt() - popupHeight / 4,
                popupWidth,
                popupHeight
            )
        }

        // Make sure the popup stays in screen bounds
        val screenBounds = popupContentWindow.graphicsConfiguration.bounds
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

        popupContentWindow.bounds = popupRect
        popupContentWindow.opacity = 1.0f
        popupContentWindow.preferredSize = Dimension(popupRect.width, popupRect.height)
        popupContentWindow.size = Dimension(popupRect.width, popupRect.height)
        popupContentWindow.invalidate()
        popupContentWindow.validate()
        popupContentWindow.contentPane.revalidate()
    }) {
        Canvas(Modifier.matchParentSize()) {
            val outline = Outline.Rectangle(
                rect = Rect(
                    left = 0.5f, top = 0.5f, right = size.width - 0.5f, bottom = size.height - 0.5f
                )
            )
            drawOutline(
                outline = outline, color = popupBorderColor, style = Stroke(width = 1.0f)
            )
        }
        CommandButtonPopupColumn(contentSize = contentSize) {
            // Command presentation for menu content
            // TODO - some of this should come from the popup presentation model
            val presentation = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.MEDIUM,
                popupPlacementStrategy = PopupPlacementStrategy.ENDWARD,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                horizontalAlignment = HorizontalAlignment.LEADING,
                isMenu = true
            )

            for ((commandGroupIndex, commandGroup) in command.secondaryContentModel!!.groups.withIndex()) {
                for (secondaryCommand in commandGroup.commands) {
                    // TODO - support highlighted command (with bold text)
                    AuroraCommandButton(
                        command = secondaryCommand,
                        parentWindow = popupContentWindow,
                        extraAction = {
                            // TODO - this needs to be revisited for multi-selection popups
                            for (window in Window.getWindows()) {
                                if (window.isDisplayable && window is AuroraPopupWindow) {
                                    window.dispose()
                                }
                            }
                        },
                        presentationModel = presentation
                    )
                }
                if (commandGroupIndex < (command.secondaryContentModel.groups.size - 1)) {
                    AuroraHorizontalSeparator()
                }
            }
        }
    }
}

@Composable
private fun CommandButtonPopupColumn(contentSize: AuroraSize, content: @Composable () -> Unit) {
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
                    x = 0, y = yPosition
                )
                yPosition += placeable.height
            }
        }
    }
}

private class CommandButtonBoxLocator(val topLeftOffset: AuroraOffset, val size: AuroraSize) :
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
private fun Modifier.commandButtonLocator(topLeftOffset: AuroraOffset, size: AuroraSize) =
    this.then(
        CommandButtonBoxLocator(topLeftOffset, size)
    )


