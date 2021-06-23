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
package org.pushingpixels.aurora.component

import androidx.compose.animation.core.*
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.ComposeWindow
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.layout.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.icon.AuroraThemedIcon
import java.awt.*
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
    ),
    val markPath: Path = Path()
)

@Composable
internal fun AuroraCommandButton(
    modifier: Modifier = Modifier,
    command: Command,
    parentWindow: ComposeWindow? = null,
    extraAction: (() -> Unit)? = null,
    extraActionPreview: CommandActionPreview? = null,
    presentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
    buttonSides: Sides
) {
    val secondaryContentModel = rememberUpdatedState(command.secondaryContentModel)
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
    val currentActionNoSelectionState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = command.isActionEnabled,
                isRollover = actionRollover,
                isSelected = false,
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
    val mergedTextStyle = LocalTextStyle.current.merge(presentationModel.textStyle)
    val resourceLoader = LocalFontLoader.current

    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }

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

    val actionModelNoSelectionStateInfo =
        remember { ModelStateInfo(currentActionNoSelectionState.value) }
    val actionNoSelectionTransitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = actionModelNoSelectionStateInfo,
        currentState = currentActionNoSelectionState,
        transitionInfo = actionNoSelectionTransitionInfo,
        enabled = command.isActionEnabled,
        selected = false,
        rollover = actionRollover,
        pressed = isActionPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (actionNoSelectionTransitionInfo.value != null) {
        LaunchedEffect(currentActionNoSelectionState.value) {
            val transitionFloat = Animatable(actionNoSelectionTransitionInfo.value!!.from)
            val result = transitionFloat.animateTo(
                targetValue = actionNoSelectionTransitionInfo.value!!.to,
                animationSpec = tween(durationMillis = actionNoSelectionTransitionInfo.value!!.duration)
            ) {
                actionModelNoSelectionStateInfo.updateActiveStates(value)
            }

            if (result.endReason == AnimationEndReason.Finished) {
                actionModelNoSelectionStateInfo.updateActiveStates(1.0f)
                actionModelNoSelectionStateInfo.clear(currentActionNoSelectionState.value)
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

    val layoutManager =
        presentationModel.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            resourceLoader = resourceLoader
        )

    val hasAction = (command.action != null)
    val isActionEnabled = command.isActionEnabled
    val isPopupEnabled = command.isSecondaryEnabled
    val isToggle = command.isActionToggle
    val hasPopup = (command.secondaryContentModel != null)
    val isTextInActionArea =
        (hasAction or isToggle) && (presentationModel.textClick == TextClick.Action)

    // TODO - do we need more keys? Maybe from the presentation model
    val preLayoutInfo = remember(
        command.text, command.extraText,
        command.action == null, command.secondaryContentModel == null
    ) {
        layoutManager.getPreLayoutInfo(command, presentationModel)
    }

    val hasIcon = preLayoutInfo.showIcon
    val parentComposition = rememberCompositionContext()

    Layout(
        modifier = modifier.commandButtonLocator(auroraTopLeftOffset, auroraSize),
        content = {
            val modifierAction: Modifier
            if (isToggle) {
                modifierAction = Modifier.toggleable(
                    value = command.isActionToggleSelected,
                    enabled = isActionEnabled,
                    role = Role.Button,
                    interactionSource = actionInteractionSource,
                    indication = null,
                    onValueChange = {
                        command.onTriggerActionToggleSelectedChange?.invoke(it)
                    })
            } else {
                modifierAction = Modifier.clickable(
                    enabled = isActionEnabled,
                    onClick = {
                        command.action?.invoke()
                        extraAction?.invoke()
                    },
                    interactionSource = actionInteractionSource,
                    indication = null
                )
            }
            // These two track the offset of action and popup area relative in
            // the overall bounding box of the command button. To paint continuous
            // visuals of the command button across two separate Box composables,
            // we paint each as full-size area, along with clipping to the specific
            // area (action or popup) and offsetting during the Canvas paint pass.
            var actionAreaOffset = remember { Offset.Zero }
            var popupAreaOffset = remember { Offset.Zero }
            Box(
                modifier = modifierAction.pointerMoveFilter(onEnter = {
                    val wasRollover = actionRollover
                    actionRollover = true
                    if (isActionEnabled && !wasRollover) {
                        command.actionPreview?.onCommandPreviewActivated(command)
                        extraActionPreview?.onCommandPreviewActivated(command)
                    }
                    false
                }, onExit = {
                    val wasRollover = actionRollover
                    actionRollover = false
                    if (isActionEnabled && wasRollover) {
                        command.actionPreview?.onCommandPreviewCanceled(command)
                        extraActionPreview?.onCommandPreviewCanceled(command)
                    }
                    false
                }, onMove = {
                    false
                }).onGloballyPositioned {
                    if (it.parentCoordinates != null) {
                        val selfToRoot = it.localToRoot(Offset.Zero)
                        val parentToRoot = it.parentCoordinates!!.localToRoot(Offset.Zero)
                        actionAreaOffset = Offset(
                            x = selfToRoot.x - parentToRoot.x,
                            y = selfToRoot.y - parentToRoot.y
                        )
                    }
                }
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
                    // Ignore the selected bit on toggle menu commands
                    val isActionToggleMenu = command.isActionToggle && presentationModel.isMenu
                    val actionModelStateInfoToUse =
                        if (isActionToggleMenu) actionModelNoSelectionStateInfo
                        else actionModelStateInfo
                    val currentActionStateToUse =
                        if (isActionToggleMenu) currentActionNoSelectionState
                        else currentActionState

                    // Populate the cached color scheme for filling the action area
                    // based on the current model state info
                    populateColorScheme(
                        drawingCache.colorScheme,
                        actionModelStateInfoToUse,
                        currentActionStateToUse.value,
                        decorationAreaType,
                        ColorSchemeAssociationKind.Fill
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
                        actionModelStateInfoToUse,
                        currentActionStateToUse.value,
                        decorationAreaType,
                        ColorSchemeAssociationKind.Border
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
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            actionModelStateInfoToUse.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else {
                            if (currentActionStateToUse.value.isDisabled)
                                skinColors.getAlpha(
                                    decorationAreaType,
                                    currentActionStateToUse.value
                                ) else 1.0f
                        }
                    )

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = auroraSize.width.toFloat()
                        val height = auroraSize.height.toFloat()

                        val openDelta = 3
                        // TODO - add RTL support
                        val deltaLeft =
                            if (buttonSides.openSides.contains(Side.Start)) openDelta else 0
                        val deltaRight =
                            if (buttonSides.openSides.contains(Side.End)) openDelta else 0
                        val deltaTop =
                            if (buttonSides.openSides.contains(Side.Top)) openDelta else 0
                        val deltaBottom =
                            if (buttonSides.openSides.contains(Side.Bottom)) openDelta else 0

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = size.width,
                                bottom = size.height,
                                clipOp = ClipOp.Intersect
                            )
                            translate(
                                left = -actionAreaOffset.x - deltaLeft.toFloat(),
                                top = -actionAreaOffset.y - deltaTop.toFloat()
                            )
                        }) {
                            val outline = buttonShaper.getButtonOutline(
                                width = width + deltaLeft + deltaRight,
                                height = height + deltaTop + deltaBottom,
                                extraInsets = 0.5f,
                                isInner = false,
                                sides = buttonSides,
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
                                this,
                                auroraSize.asSize(deltaLeft + deltaRight, deltaTop + deltaBottom),
                                outline,
                                drawingCache.colorScheme,
                                actionAlpha
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
                                    sides = buttonSides,
                                    drawScope = this
                                ) else null

                            borderPainter.paintBorder(
                                this,
                                auroraSize.asSize(deltaLeft + deltaRight, deltaTop + deltaBottom),
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
                    enabled = isPopupEnabled,
                    onClick = {
                        // TODO - move off of JWindow when https://github.com/JetBrains/compose-jb/issues/195
                        //  is addressed
                        val popupContentWindow = ComposeWindow()
                        popupContentWindow.focusableWindowState = false
                        popupContentWindow.type = Window.Type.POPUP
                        popupContentWindow.isAlwaysOnTop = true
                        popupContentWindow.isUndecorated = true

                        // TODO - hopefully temporary. Mark the popup window as fully transparent
                        //  so that when it is globally positioned, we can size it to the actual
                        //  content and make it fully opaque
                        popupContentWindow.opacity = 0.0f

                        val currentWindow = parentWindow ?: AppManager.focusedWindow!!.window
                        val locationOnScreen = currentWindow.locationOnScreen

                        // anchor the popup window to the bottom left corner of the component
                        // in screen coordinates
                        // TODO - figure out the sizing (see above)
                        val initialWidth = 1000
                        val initialHeight = 1000
                        val initialWindowAnchor = IntOffset(
                            x = (locationOnScreen.x + auroraTopLeftOffset.x / density.density).toInt(),
                            y = (locationOnScreen.y + auroraTopLeftOffset.y / density.density).toInt()
                        )
                        popupContentWindow.setBounds(
                            (locationOnScreen.x + auroraTopLeftOffset.x / density.density).toInt(),
                            (locationOnScreen.y + auroraTopLeftOffset.y / density.density).toInt(),
                            initialWidth,
                            initialHeight
                        )

                        popupContentWindow.setContent(
                            parentComposition = parentComposition
                        ) {
                            CompositionLocalProvider(
                                LocalDecorationAreaType provides decorationAreaType,
                                LocalSkinColors provides skinColors,
                                LocalButtonShaper provides buttonShaper,
                                LocalPainters provides painters,
                                LocalAnimationConfig provides AuroraSkin.animationConfig
                            ) {
                                CommandButtonPopupContent(
                                    popupContentWindow = popupContentWindow,
                                    initialAnchor = initialWindowAnchor,
                                    anchorSize = auroraSize,
                                    menuContentModel = secondaryContentModel,
                                    menuPresentationModel = presentationModel.popupMenuPresentationModel,
                                    popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                                    toDismissPopupsOnActivation = presentationModel.toDismissPopupsOnActivation,
                                    overlays = overlays
                                )
                            }
                        }

                        popupContentWindow.invalidate()
                        popupContentWindow.validate()
                        popupContentWindow.isVisible = true
                        popupContentWindow.pack()

                        // Hide the popups that "start" from the current window
                        AuroraPopupManager.hidePopups(originator = currentWindow)
                        // And display our new popup content
                        AuroraPopupManager.addPopup(
                            originator = currentWindow,
                            popupWindow = popupContentWindow
                        )
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
                }).onGloballyPositioned {
                    if (it.parentCoordinates != null) {
                        val selfToRoot = it.localToRoot(Offset.Zero)
                        val parentToRoot = it.parentCoordinates!!.localToRoot(Offset.Zero)
                        popupAreaOffset = Offset(
                            x = selfToRoot.x - parentToRoot.x,
                            y = selfToRoot.y - parentToRoot.y
                        )
                    }
                }
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
                    // Populate the cached color scheme for filling the button container
                    // based on the current model state info
                    populateColorScheme(
                        drawingCache.colorScheme,
                        popupModelStateInfo,
                        currentPopupState.value,
                        decorationAreaType,
                        ColorSchemeAssociationKind.Fill
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
                        ColorSchemeAssociationKind.Border
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
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            popupModelStateInfo.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else {
                            if (currentPopupState.value.isDisabled)
                                skinColors.getAlpha(
                                    decorationAreaType,
                                    currentPopupState.value
                                ) else 1.0f
                        }
                    )

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = auroraSize.width.toFloat()
                        val height = auroraSize.height.toFloat()

                        val openDelta = 3
                        // TODO - add RTL support
                        val deltaLeft =
                            if (buttonSides.openSides.contains(Side.Start)) openDelta else 0
                        val deltaRight =
                            if (buttonSides.openSides.contains(Side.End)) openDelta else 0
                        val deltaTop =
                            if (buttonSides.openSides.contains(Side.Top)) openDelta else 0
                        val deltaBottom =
                            if (buttonSides.openSides.contains(Side.Bottom)) openDelta else 0

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = size.width,
                                bottom = size.height,
                                clipOp = ClipOp.Intersect
                            )
                            translate(
                                left = -popupAreaOffset.x - deltaLeft.toFloat(),
                                top = -popupAreaOffset.y - deltaTop.toFloat()
                            )
                        }) {
                            val outline = buttonShaper.getButtonOutline(
                                width = width + deltaLeft + deltaRight,
                                height = height + deltaTop + deltaBottom,
                                extraInsets = 0.5f,
                                isInner = false,
                                sides = buttonSides,
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
                                this,
                                auroraSize.asSize(deltaLeft + deltaRight, deltaTop + deltaBottom),
                                outline,
                                drawingCache.colorScheme,
                                popupAlpha
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
                                    sides = buttonSides,
                                    drawScope = this
                                ) else null

                            borderPainter.paintBorder(
                                this,
                                auroraSize.asSize(deltaLeft + deltaRight, deltaTop + deltaBottom),
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
                    if (hasAction or isToggle) actionModelStateInfo else popupModelStateInfo
                val currStateForIcon =
                    if (hasAction or isToggle) currentActionState.value else currentPopupState.value

                CommandButtonIconContent(
                    command,
                    presentationModel,
                    layoutManager.getPreferredIconSize(command, presentationModel),
                    modelStateInfoForIcon,
                    currStateForIcon,
                    drawingCache
                )
            }

            // Text content can be in action or popup area. Use the matching model
            // to determine the text color
            val modelStateInfoForText = if (isTextInActionArea) {
                if (command.isActionToggle && presentationModel.isMenu) actionModelNoSelectionStateInfo
                else actionModelStateInfo
            } else {
                popupModelStateInfo
            }
            val currStateForText = if (isTextInActionArea) {
                if (command.isActionToggle && presentationModel.isMenu) currentActionNoSelectionState.value
                else currentActionState.value
            } else {
                currentActionState.value
            }

            for (text in preLayoutInfo.texts) {
                CommandButtonTextContent(
                    text, modelStateInfoForText, currStateForText, resolvedTextStyle
                )
            }
            for (extraText in preLayoutInfo.extraTexts) {
                CommandButtonExtraTextContent(
                    extraText, modelStateInfoForText, currStateForText, resolvedTextStyle
                )
            }

            // Popup action (arrow) if we need one
            if (preLayoutInfo.showPopupIcon) {
                CommandButtonPopupIconContent(
                    presentationModel,
                    popupModelStateInfo,
                    currentPopupState.value
                )
            }

            // Separator between action and popup areas if we have both
            if (hasAction and hasPopup and isActionEnabled and isPopupEnabled) {
                when (preLayoutInfo.separatorOrientation) {
                    CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Vertical ->
                        VerticalSeparatorProjection(
                            presentationModel = SeparatorPresentationModel(
                                startGradientAmount = 4.dp,
                                endGradientAmount = 4.dp
                            )
                        ).project(modifier = Modifier.alpha(combinedRolloverFraction))
                    CommandButtonLayoutManager.CommandButtonSeparatorOrientation.Horizontal ->
                        HorizontalSeparatorProjection(
                            presentationModel = SeparatorPresentationModel(
                                startGradientAmount = 4.dp,
                                endGradientAmount = 4.dp
                            )
                        ).project(modifier = Modifier.alpha(combinedRolloverFraction))
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
            preLayoutInfo = preLayoutInfo
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

        val textPlaceables = arrayListOf<Placeable>()
        for (index in preLayoutInfo.texts.indices) {
            // Measure each text part
            textPlaceables.add(
                measurables[childIndex++].measure(
                    Constraints.fixed(
                        width = layoutInfo.textLayoutInfoList[index].textRect.width.roundToInt(),
                        height = layoutInfo.textLayoutInfoList[index].textRect.height.roundToInt()
                    )
                )
            )
        }
        val extraTextPlaceables = arrayListOf<Placeable>()
        for (index in preLayoutInfo.extraTexts.indices) {
            // Measure each extra text part
            extraTextPlaceables.add(
                measurables[childIndex++].measure(
                    Constraints.fixed(
                        width = layoutInfo.extraTextLayoutInfoList[index].textRect.width.roundToInt(),
                        height = layoutInfo.extraTextLayoutInfoList[index].textRect.height.roundToInt()
                    )
                )
            )
        }

        var popupIconPlaceable: Placeable? = null
        if (preLayoutInfo.showPopupIcon) {
            val popupIconMeasurable = measurables[childIndex++]
            popupIconPlaceable = popupIconMeasurable.measure(
                Constraints.fixed(
                    width = layoutInfo.popupActionRect.width.roundToInt(),
                    height = layoutInfo.popupActionRect.height.roundToInt()
                )
            )
        }
        var separatorPlaceable: Placeable? = null
        if (hasAction and hasPopup and isActionEnabled and isPopupEnabled) {
            val separatorMeasurable = measurables[childIndex++]
            separatorPlaceable = separatorMeasurable.measure(
                Constraints.fixed(
                    width = layoutInfo.separatorArea.width.roundToInt(),
                    height = layoutInfo.separatorArea.height.roundToInt()
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
            for ((index, textPlaceable) in textPlaceables.withIndex()) {
                textPlaceable.placeRelative(
                    x = layoutInfo.textLayoutInfoList[index].textRect.left.roundToInt(),
                    y = layoutInfo.textLayoutInfoList[index].textRect.top.roundToInt()
                )
            }
            for ((index, extraTextPlaceable) in extraTextPlaceables.withIndex()) {
                extraTextPlaceable.placeRelative(
                    x = layoutInfo.extraTextLayoutInfoList[index].textRect.left.roundToInt(),
                    y = layoutInfo.extraTextLayoutInfoList[index].textRect.top.roundToInt()
                )
            }
            popupIconPlaceable?.placeRelative(
                x = layoutInfo.popupActionRect.left.roundToInt(),
                y = layoutInfo.popupActionRect.top.roundToInt()
            )
            separatorPlaceable?.placeRelative(
                x = layoutInfo.separatorArea.left.roundToInt(),
                y = layoutInfo.separatorArea.top.roundToInt()
            )
        }
    }
}

@Composable
private fun CommandButtonTextContent(
    text: String, modelStateInfo: ModelStateInfo, currState: ComponentState,
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
        colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
        isTextInFilledArea = true
    )

    // Pass our text color and model state snapshot to the children
    CompositionLocalProvider(
        LocalTextColor provides textColor,
        LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState)
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        AuroraText(text = text, color = textColor, style = style)
    }
}

@Composable
private fun CommandButtonExtraTextContent(
    text: String, modelStateInfo: ModelStateInfo, currState: ComponentState,
    style: TextStyle
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors

    // Compute the regular text color based on the passed model state (which can be action
    // or popup)
    val textColor = getTextColor(
        modelStateInfo = modelStateInfo,
        currState = currState,
        skinColors = skinColors,
        decorationAreaType = decorationAreaType,
        colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
        isTextInFilledArea = true
    )

    // "Move" the regular text color towards the disabled state for more muted visuals
    // of the extra text
    val disabledColorScheme = skinColors.getColorScheme(
        decorationAreaType, ComponentState.DisabledUnselected
    )
    var disabledFgColor = disabledColorScheme.foregroundColor
    val buttonAlpha = skinColors.getAlpha(decorationAreaType, currState)

    val backgroundColorScheme = skinColors.getColorScheme(decorationAreaType, currState)
    val bgFillColor = backgroundColorScheme.backgroundFillColor
    if (buttonAlpha < 1.0f) {
        // Blend with the background fill
        disabledFgColor = disabledFgColor.interpolateTowards(bgFillColor, buttonAlpha)
    }
    if (currState.isDisabled) {
        disabledFgColor = disabledFgColor.interpolateTowards(bgFillColor, 0.5f)
    }
    disabledFgColor = disabledFgColor.interpolateTowards(textColor, 0.5f)

    // Pass our text color and model state snapshot to the children
    CompositionLocalProvider(
        LocalTextColor provides disabledFgColor,
        LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState)
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        AuroraText(text = text, color = disabledFgColor, style = style)
    }
}

@Composable
private fun CommandButtonIconContent(
    command: Command, presentationModel: CommandButtonPresentationModel,
    iconSize: Dp, modelStateInfo: ModelStateInfo, currState: ComponentState,
    drawingCache: CommandButtonDrawingCache
) {
    // Compute the combined strength of all the
    // states that have the selection bit turned on
    val selectionAlpha = modelStateInfo.stateContributionMap
        .filter { it.key.isFacetActive(ComponentStateFacet.Selection) }
        .map { it.value }
        .sumOf { it.contribution.toDouble() }
        .toFloat()
    val isSelectedMenu = presentationModel.isMenu && (selectionAlpha > 0.0f)

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val borderPainter = AuroraSkin.painters.borderPainter
    val fillPainter = AuroraSkin.painters.fillPainter

    Box {
        if (isSelectedMenu) {
            Canvas(modifier = Modifier.matchParentSize()) {
                // Background fill / border for selected toggle menu commands
                val stateForBackground = if (currState.isDisabled) ComponentState.DisabledSelected
                else ComponentState.Selected

                val alphaForBackground = skinColors.getAlpha(
                    decorationAreaType = decorationAreaType,
                    componentState = stateForBackground
                ) * selectionAlpha
                val outline = Outline.Rectangle(
                    rect = Rect(
                        left = 0.5f,
                        top = 0.5f,
                        right = size.width - 0.5f,
                        bottom = size.height - 0.5f
                    )
                )

                val fillScheme = skinColors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.Highlight,
                    componentState = stateForBackground
                )

                fillPainter.paintContourBackground(
                    drawScope = this,
                    size = size,
                    outline = outline,
                    fillScheme = fillScheme,
                    alpha = alphaForBackground
                )

                val borderScheme = skinColors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.HighlightBorder,
                    componentState = stateForBackground
                )

                borderPainter.paintBorder(
                    drawScope = this,
                    size = size,
                    outline = outline,
                    outlineInner = null,
                    borderScheme = borderScheme,
                    alpha = alphaForBackground
                )
            }
        }
        if (command.iconFactory == null) {
            // If we get to this function, we are being asked to display the icon. If the icon
            // factory is null, we display a checkmark if the button is in selected
            // state (full or partial)

            // Checkmark color
            val markColor = getStateAwareColor(
                modelStateInfo, currState,
                decorationAreaType, ColorSchemeAssociationKind.Mark
            ) { it.markColor }

            val stateForMark = if (currState.isDisabled) ComponentState.DisabledSelected
            else ComponentState.Selected
            val alphaForMark = skinColors.getAlpha(
                decorationAreaType = decorationAreaType,
                componentState = stateForMark
            )

            Canvas(modifier = Modifier.matchParentSize()) {
                val width = this.size.width
                val height = this.size.height

                // Draw the checkbox mark with the alpha that corresponds to the current
                // selection and potential transition
                val markStroke = 0.12f * width

                with(drawingCache) {
                    markPath.reset()
                    markPath.moveTo(0.25f * width, 0.48f * height)
                    markPath.lineTo(0.48f * width, 0.73f * height)
                    markPath.lineTo(0.76f * width, 0.28f * height)

                    // Note that we apply alpha twice - once for the selected / checked
                    // state or transition, and the second time based on the enabled bit
                    drawPath(
                        path = markPath,
                        color = markColor.withAlpha(selectionAlpha),
                        style = Stroke(
                            width = markStroke,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        ),
                        alpha = alphaForMark
                    )
                }
            }
        } else {
            val icon = if (command.iconFactory is TransitionAwareIcon.TransitionAwareIconFactory)
                command.iconFactory.createNewIcon(modelStateInfo.getSnapshot(currState))
            else
                remember(iconSize) { command.iconFactory.createNewIcon() }
            icon.setSize(width = iconSize, height = iconSize)

            // Compute the text color based on the passed model state (which can be action
            // or popup)
            val textColor = getTextColor(
                modelStateInfo = modelStateInfo,
                currState = currState,
                skinColors = skinColors,
                decorationAreaType = decorationAreaType,
                colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
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
        ColorSchemeAssociationKind.Mark
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
internal fun CommandButtonPopupContent(
    popupContentWindow: ComposeWindow,
    initialAnchor: IntOffset,
    anchorSize: AuroraSize,
    menuContentModel: State<CommandMenuContentModel?>,
    menuPresentationModel: CommandPopupMenuPresentationModel,
    popupPlacementStrategy: PopupPlacementStrategy,
    toDismissPopupsOnActivation: Boolean,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>
) {
    val borderScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = DecorationAreaType.None,
        associationKind = ColorSchemeAssociationKind.Border,
        componentState = ComponentState.Enabled
    )
    val popupBorderColor = AuroraSkin.painters.borderPainter.getRepresentativeColor(borderScheme)
    val density = LocalDensity.current
    val contentSize = AuroraSize(0, 0)
    Box(modifier = Modifier.onGloballyPositioned {
        // Get the size of the content and update the popup window bounds
        val popupWidth = (contentSize.width / density.density).toInt()
        val popupHeight = (contentSize.height / density.density).toInt()

        // TODO - support RTL for startward and endward
        val popupRect = when (popupPlacementStrategy) {
            PopupPlacementStrategy.Downward -> Rectangle(
                initialAnchor.x,
                initialAnchor.y + (anchorSize.height / density.density).toInt(),
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.Upward -> Rectangle(
                initialAnchor.x,
                initialAnchor.y - popupHeight,
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.Startward -> Rectangle(
                initialAnchor.x - popupWidth,
                initialAnchor.y,
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.Endward -> Rectangle(
                initialAnchor.x + (anchorSize.width / density.density).toInt(),
                initialAnchor.y,
                popupWidth,
                popupHeight
            )
            PopupPlacementStrategy.CenteredVertically -> Rectangle(
                initialAnchor.x,
                initialAnchor.y + (anchorSize.height / (2 * density.density)).toInt() - popupHeight / 2,
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
    }) {
        val hasPanel = (menuContentModel.value!!.panelContentModel != null)
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val resourceLoader = LocalFontLoader.current
        val panelPreferredSize = if (hasPanel) getPreferredCommandButtonPanelSize(
            contentModel = menuContentModel.value!!.panelContentModel!!,
            presentationModel = menuPresentationModel.panelPresentationModel!!,
            buttonLayoutManager = menuPresentationModel.panelPresentationModel.commandPresentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                resourceLoader = resourceLoader
            ),
            layoutDirection = layoutDirection,
            density = density
        ) else Size(0.0f, 0.0f)
        CommandButtonPopupColumn(
            contentSize = contentSize,
            hasPanel = hasPanel,
            panelPreferredSize = panelPreferredSize
        ) {
            // This canvas paints the background fill of the popup and the outer hairline border
            Canvas(modifier = Modifier.auroraBackground()) {
                val outline = Outline.Rectangle(
                    rect = Rect(
                        left = 0.5f,
                        top = 0.5f,
                        right = size.width - 0.5f,
                        bottom = size.height - 0.5f
                    )
                )
                drawOutline(
                    outline = outline, color = popupBorderColor, style = Stroke(width = 1.0f)
                )
            }
            if (hasPanel) {
                AuroraCommandButtonPanel(
                    contentModel = menuContentModel.value!!.panelContentModel!!,
                    presentationModel = menuPresentationModel.panelPresentationModel!!,
                    extraAction = {
                        if (toDismissPopupsOnActivation) {
                            AuroraPopupManager.hidePopups(null)
                        }
                    }
                )
                HorizontalSeparatorProjection(
                    presentationModel = SeparatorPresentationModel(
                        startGradientAmount = 0.dp,
                        endGradientAmount = 0.dp
                    )
                ).project()
            }

            // Command presentation for menu content, taking some of the values from
            // the popup menu presentation model configured on the top-level presentation model
            val menuButtonPresentationModel = CommandButtonPresentationModel(
                presentationState = menuPresentationModel.menuPresentationState,
                popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                horizontalAlignment = HorizontalAlignment.Leading,
                isMenu = true
            )

            var atLeastOneButtonHasIcon = false
            for (commandGroup in menuContentModel.value!!.groups) {
                for (secondaryCommand in commandGroup.commands) {
                    if (secondaryCommand.iconFactory != null) {
                        atLeastOneButtonHasIcon = true
                    }
                    if (secondaryCommand.isActionToggle) {
                        atLeastOneButtonHasIcon = true
                    }
                }
            }

            CompositionLocalProvider(
                LocalCommandForceIcon provides atLeastOneButtonHasIcon
            ) {
                for ((commandGroupIndex, commandGroup) in menuContentModel.value!!.groups.withIndex()) {
                    for (secondaryCommand in commandGroup.commands) {
                        // Check if we have a presentation overlay for this secondary command
                        val hasOverlay = overlays.containsKey(secondaryCommand)
                        var currSecondaryPresentationModel = if (hasOverlay)
                            menuButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
                        else menuButtonPresentationModel
                        if (secondaryCommand == menuContentModel.value!!.highlightedCommand) {
                            // If our secondary content model has a highlighted command, pass bold
                            // font weight to the text style of the matching command button.
                            currSecondaryPresentationModel =
                                currSecondaryPresentationModel.overlayWith(
                                    CommandButtonPresentationModel.Overlay(
                                        textStyle = TextStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                )
                        }

                        // Create a command button for each secondary command, passing the same
                        // overlays into it.
                        AuroraCommandButton(
                            command = secondaryCommand,
                            parentWindow = popupContentWindow,
                            extraAction = {
                                if (toDismissPopupsOnActivation and
                                    currSecondaryPresentationModel.toDismissPopupsOnActivation
                                ) {
                                    AuroraPopupManager.hidePopups(null)
                                }
                            },
                            presentationModel = currSecondaryPresentationModel,
                            overlays = overlays,
                            buttonSides = Sides(straightSides = Side.values().toSet())
                        )
                    }
                    if (commandGroupIndex < (menuContentModel.value!!.groups.size - 1)) {
                        HorizontalSeparatorProjection(
                            presentationModel = SeparatorPresentationModel(
                                startGradientAmount = 0.dp,
                                endGradientAmount = 0.dp
                            )
                        ).project()
                    }
                }
            }
        }
    }
}

@Composable
private fun CommandButtonPopupColumn(
    contentSize: AuroraSize,
    hasPanel: Boolean,
    panelPreferredSize: Size,
    content: @Composable () -> Unit
) {
    Layout(content = content) { measurables, _ ->
        val contentTotalWidth: Int
        var panelPlaceable: Placeable? = null
        var panelSeparatorPlaceable: Placeable? = null
        if (hasPanel) {
            // The column width is determined by the panel
            panelPlaceable = measurables[1].measure(
                Constraints.fixed(
                    width = panelPreferredSize.width.toInt(),
                    height = panelPreferredSize.height.toInt()
                )
            )
            contentTotalWidth = panelPlaceable.measuredWidth
            panelSeparatorPlaceable = measurables[2].measure(
                Constraints.fixed(
                    width = contentTotalWidth,
                    height = SeparatorSizingConstants.Thickness.roundToPx()
                )
            )
        } else {
            // The column width is determined by the widest child
            contentTotalWidth = measurables.maxOf { it.maxIntrinsicWidth(Integer.MAX_VALUE) }
        }

        val buttonPlaceables = arrayListOf<Placeable>()
        val startIndex = if (hasPanel) 3 else 1
        for (buttonIndex in startIndex until measurables.size) {
            // Measure each button with fixed (widest) width
            buttonPlaceables.add(
                measurables[buttonIndex]
                    .measure(Constraints.fixedWidth(contentTotalWidth))
            )
        }

        // The children are laid out in a column
        val contentMaxHeight = panelPreferredSize.height.toInt() +
                (if (hasPanel) SeparatorSizingConstants.Thickness.roundToPx() else 0) +
                buttonPlaceables.sumOf { it.height }

        // Add one pixel on each side for the popup border
        contentSize.width = contentTotalWidth + 2
        contentSize.height = contentMaxHeight + 2

        val canvasPlaceable = measurables[0].measure(
            constraints = Constraints.fixed(
                width = contentSize.width, height = contentSize.height
            )
        )

        layout(width = contentTotalWidth, height = contentMaxHeight) {
            // TODO - support RTL
            canvasPlaceable.placeRelative(0, 0)

            // Offset everything else by 1,1 for border insets
            var yPosition = 1
            if (panelPlaceable != null) {
                panelPlaceable.placeRelative(1, 1)
                yPosition += panelPlaceable.height
                panelSeparatorPlaceable!!.placeRelative(1, yPosition)
                yPosition += panelSeparatorPlaceable.height
            }

            buttonPlaceables.forEach { placeable ->
                placeable.placeRelative(x = 1, y = yPosition)
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

internal val LocalCommandForceIcon = staticCompositionLocalOf { false }
