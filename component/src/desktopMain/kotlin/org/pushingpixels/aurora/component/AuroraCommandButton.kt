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

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.component.ribbon.impl.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.theming.utils.*
import java.awt.event.KeyEvent
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
@OptIn(AuroraInternalApi::class)
private class CommandButtonDrawingCache(
    val actionColorTokens: MutableContainerColorTokens = MutableContainerColorTokens(),
    val popupColorTokens: MutableContainerColorTokens = MutableContainerColorTokens(),
    val markPath: Path = Path()
)

private fun Modifier.commandButtonActionHoverable(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    onActivateActionState: State<() -> Unit>,
    presentationModel: BaseCommandButtonPresentationModel
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "hoverable"
        properties["interactionSource"] = interactionSource
        properties["enabled"] = enabled
    }
) {
    var hoverInteraction by remember { mutableStateOf<HoverInteraction.Enter?>(null) }
    val scope = rememberCoroutineScope()
    var clickJob: Job? by remember { mutableStateOf(null) }

    suspend fun emitEnter() {
        if (hoverInteraction == null) {
            val interaction = HoverInteraction.Enter()
            interactionSource.emit(interaction)
            hoverInteraction = interaction

            if (presentationModel.autoRepeatAction) {
                clickJob?.cancel()
                clickJob = scope.launch {
                    delay(presentationModel.autoRepeatInitialInterval)
                    while (isActive) {
                        onActivateActionState.value.invoke()
                        delay(presentationModel.autoRepeatSubsequentInterval)
                    }
                }
            } else {
                onActivateActionState.value.invoke()
            }
        }
    }

    suspend fun emitExit() {
        hoverInteraction?.let { oldValue ->
            val interaction = HoverInteraction.Exit(oldValue)
            interactionSource.emit(interaction)
            hoverInteraction = null
            clickJob?.cancel()
        }
    }

    fun tryEmitExit() {
        hoverInteraction?.let { oldValue ->
            val interaction = HoverInteraction.Exit(oldValue)
            interactionSource.tryEmit(interaction)
            hoverInteraction = null
            clickJob?.cancel()
        }
    }

    DisposableEffect(interactionSource) {
        onDispose { tryEmitExit() }
    }
    LaunchedEffect(enabled) {
        if (!enabled) {
            emitExit()
        }
    }

    if (enabled) {
        Modifier
            .pointerInput(interactionSource) {
                coroutineScope {
                    val currentContext = currentCoroutineContext()
                    val outerScope = this
                    awaitPointerEventScope {
                        while (currentContext.isActive) {
                            val event = awaitPointerEvent()
                            when (event.type) {
                                PointerEventType.Enter -> outerScope.launch { emitEnter() }
                                PointerEventType.Exit -> outerScope.launch { emitExit() }
                            }
                        }
                    }
                }
            }
    } else {
        Modifier
    }
}

private fun Modifier.commandButtonPopupHoverable(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    onActivatePopupState: State<() -> Unit>,
    onDeactivatePopupState: State<() -> Unit>,
    presentationModel: BaseCommandButtonPresentationModel
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "hoverable"
        properties["interactionSource"] = interactionSource
        properties["enabled"] = enabled
    }
) {
    var hoverInteraction by remember { mutableStateOf<HoverInteraction.Enter?>(null) }

    suspend fun emitEnter() {
        if (hoverInteraction == null) {
            val interaction = HoverInteraction.Enter()
            interactionSource.emit(interaction)
            hoverInteraction = interaction

            if (presentationModel.popupFireTrigger == PopupFireTrigger.OnRollover) {
                onActivatePopupState.value.invoke()
            }
        }
    }

    suspend fun emitExit() {
        hoverInteraction?.let { oldValue ->
            val interaction = HoverInteraction.Exit(oldValue)
            interactionSource.emit(interaction)
            hoverInteraction = null

            if (presentationModel.popupFireTrigger == PopupFireTrigger.OnRollover) {
                onDeactivatePopupState.value.invoke()
            }
        }
    }

    fun tryEmitExit() {
        hoverInteraction?.let { oldValue ->
            val interaction = HoverInteraction.Exit(oldValue)
            interactionSource.tryEmit(interaction)
            hoverInteraction = null
        }
    }

    DisposableEffect(interactionSource) {
        onDispose { tryEmitExit() }
    }
    LaunchedEffect(enabled) {
        if (!enabled) {
            emitExit()
        }
    }

    if (enabled) {
        Modifier
            .pointerInput(interactionSource) {
                coroutineScope {
                    val currentContext = currentCoroutineContext()
                    val outerScope = this
                    awaitPointerEventScope {
                        while (currentContext.isActive) {
                            val event = awaitPointerEvent()
                            when (event.type) {
                                PointerEventType.Enter -> outerScope.launch { emitEnter() }
                                PointerEventType.Exit -> outerScope.launch { emitExit() }
                            }
                        }
                    }
                }
            }
    } else {
        Modifier
    }
}

internal suspend fun PressGestureScope.auroraHandleActionPressInteraction(
    pressPoint: Offset,
    interactionSource: MutableInteractionSource,
    pressedInteraction: MutableState<PressInteraction.Press?>,
    onActivateActionState: State<() -> Unit>,
    invokeOnActivateActionOnPress: Boolean,
    presentationModel: BaseCommandButtonPresentationModel,
    scope: CoroutineScope,
    clickJob: MutableState<Job?>
) {
    coroutineScope {
        val delayJob = launch {
            delay(0L)
            val pressInteraction = PressInteraction.Press(pressPoint)
            interactionSource.emit(pressInteraction)
            pressedInteraction.value = pressInteraction
            if (invokeOnActivateActionOnPress) {
                if (presentationModel.autoRepeatAction) {
                    clickJob.value?.cancel()
                    clickJob.value = scope.launch {
                        delay(presentationModel.autoRepeatInitialInterval)
                        while (isActive) {
                            onActivateActionState.value.invoke()
                            delay(presentationModel.autoRepeatSubsequentInterval)
                        }
                    }
                } else {
                    onActivateActionState.value.invoke()
                }
            }
        }
        val success = tryAwaitRelease()
        if (delayJob.isActive) {
            delayJob.cancelAndJoin()
            // The press released successfully, before the timeout duration - emit the press
            // interaction instantly. No else branch - if the press was cancelled before the
            // timeout, we don't want to emit a press interaction.
            if (success) {
                val pressInteraction = PressInteraction.Press(pressPoint)
                val releaseInteraction = PressInteraction.Release(pressInteraction)
                interactionSource.emit(pressInteraction)
                interactionSource.emit(releaseInteraction)
                clickJob.value?.cancel()
            }
        } else {
            pressedInteraction.value?.let { pressInteraction ->
                val endInteraction = if (success) {
                    PressInteraction.Release(pressInteraction)
                } else {
                    PressInteraction.Cancel(pressInteraction)
                }
                interactionSource.emit(endInteraction)
                clickJob.value?.cancel()
            }
        }
        pressedInteraction.value = null
    }
}

internal suspend fun PressGestureScope.auroraHandlePopupPressInteraction(
    pressPoint: Offset,
    interactionSource: MutableInteractionSource,
    pressedInteraction: MutableState<PressInteraction.Press?>,
    onActivatePopupState: State<() -> Unit>,
    presentationModel: BaseCommandButtonPresentationModel,
    scope: CoroutineScope,
    clickJob: MutableState<Job?>
) {
    coroutineScope {
        val delayJob = launch {
            delay(0L)
            val pressInteraction = PressInteraction.Press(pressPoint)
            interactionSource.emit(pressInteraction)
            pressedInteraction.value = pressInteraction
            if (presentationModel.popupFireTrigger == PopupFireTrigger.OnPressed) {
                onActivatePopupState.value.invoke()
            }
        }
        val success = tryAwaitRelease()
        if (delayJob.isActive) {
            delayJob.cancelAndJoin()
            // The press released successfully, before the timeout duration - emit the press
            // interaction instantly. No else branch - if the press was cancelled before the
            // timeout, we don't want to emit a press interaction.
            if (success) {
                val pressInteraction = PressInteraction.Press(pressPoint)
                val releaseInteraction = PressInteraction.Release(pressInteraction)
                interactionSource.emit(pressInteraction)
                interactionSource.emit(releaseInteraction)
                clickJob.value?.cancel()
            }
        } else {
            pressedInteraction.value?.let { pressInteraction ->
                val endInteraction = if (success) {
                    PressInteraction.Release(pressInteraction)
                } else {
                    PressInteraction.Cancel(pressInteraction)
                }
                interactionSource.emit(endInteraction)
                clickJob.value?.cancel()
            }
        }
        pressedInteraction.value = null
    }
}

private fun Modifier.commandButtonActionModifier(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    presentationModel: BaseCommandButtonPresentationModel,
    onActivateAction: () -> Unit
) = composed(
    factory = {
        // Start building the chain. First the semantics role
        var result = this.semantics(mergeDescendants = true) {
            this.role = Role.Button
        }
        // Then treating "Enter" key up event to fire the action
        result = result.then(onKeyEvent {
            if (enabled && (it.type == KeyEventType.KeyUp) && (it.key.nativeKeyCode == KeyEvent.VK_ENTER)) {
                onActivateAction()
                true
            } else {
                false
            }
        })

        val onActivateActionState = rememberUpdatedState(onActivateAction)
        val pressedInteraction = remember { mutableStateOf<PressInteraction.Press?>(null) }
        val scope = rememberCoroutineScope()
        val clickJob: MutableState<Job?> = mutableStateOf(null)

        // Now for the mouse interaction part
        if (presentationModel.actionFireTrigger == ActionFireTrigger.OnRollover) {
            // Our button is configured to fire action on rollover

            // Start with the hover
            result = result.then(
                Modifier.commandButtonActionHoverable(
                    interactionSource,
                    enabled,
                    onActivateActionState,
                    presentationModel
                )
            )

            // And add press detector, but without invoking onClick in onPress or onTap,
            // since we are invoking onClick on PointerEventType.Enter
            result = result.then(Modifier.pointerInput(interactionSource, enabled) {
                detectTapAndPress(
                    onPress = { offset ->
                        if (enabled) {
                            auroraHandleActionPressInteraction(
                                offset, interactionSource, pressedInteraction,
                                onActivateActionState, false, presentationModel,
                                scope, clickJob
                            )
                        }
                    },
                    onTap = {}
                )
            })
        } else {
            // Otherwise track hover state
            result = result.hoverable(enabled = enabled, interactionSource = interactionSource)

            // And finally add our custom tap-and-press detector
            DisposableEffect(interactionSource) {
                onDispose {
                    pressedInteraction.value?.let { oldValue ->
                        val interaction = PressInteraction.Cancel(oldValue)
                        interactionSource.tryEmit(interaction)
                        pressedInteraction.value = null
                    }
                }
            }
            result = result.then(Modifier.pointerInput(interactionSource, enabled) {
                detectTapAndPress(
                    onPress = { offset ->
                        if (enabled) {
                            auroraHandleActionPressInteraction(
                                offset, interactionSource, pressedInteraction,
                                onActivateActionState,
                                presentationModel.actionFireTrigger == ActionFireTrigger.OnPressed,
                                presentationModel,
                                scope,
                                clickJob
                            )
                        }
                    },
                    onTap = {
                        if (enabled && (presentationModel.actionFireTrigger == ActionFireTrigger.OnPressReleased)) {
                            onActivateActionState.value.invoke()
                        }
                    }
                )
            })
        }
        result
    },
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = null
        properties["role"] = Role.Button
        properties["onClick"] = onActivateAction
        properties["indication"] = null
        properties["interactionSource"] = interactionSource
    }
)

private fun Modifier.commandButtonPopupModifier(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    presentationModel: BaseCommandButtonPresentationModel,
    onActivatePopup: () -> Unit,
    onDeactivatePopup: () -> Unit
) = composed(
    factory = {
        // Start building the chain. First the semantics role
        var result = this.semantics(mergeDescendants = true) {
            this.role = Role.DropdownList
        }
        // Then treating "Enter" key up event to fire the popup
        result = result.then(onKeyEvent {
            if (enabled && (it.type == KeyEventType.KeyUp) && (it.key.nativeKeyCode == KeyEvent.VK_ENTER)) {
                onActivatePopup()
                true
            } else {
                false
            }
        })

        val onActivatePopupState = rememberUpdatedState(onActivatePopup)
        val onDeactivatePopupState = rememberUpdatedState(onDeactivatePopup)
        val pressedInteraction = remember { mutableStateOf<PressInteraction.Press?>(null) }
        val scope = rememberCoroutineScope()
        val clickJob: MutableState<Job?> = mutableStateOf(null)

        // Now for the mouse interaction part
        if (presentationModel.popupFireTrigger == PopupFireTrigger.OnRollover) {
            // Activate popup on rollover

            // Start with the hover
            result = result.then(
                Modifier.commandButtonPopupHoverable(
                    interactionSource,
                    enabled,
                    onActivatePopupState,
                    onDeactivatePopupState,
                    presentationModel
                )
            )

            // And add press detector, but without invoking onClick in onPress or onTap,
            // since we are invoking onClick on PointerEventType.Enter
            result = result.then(Modifier.pointerInput(interactionSource, enabled) {
                detectTapAndPress(
                    onPress = { offset ->
                        if (enabled) {
                            auroraHandlePopupPressInteraction(
                                offset, interactionSource, pressedInteraction,
                                onActivatePopupState, presentationModel,
                                scope, clickJob
                            )
                        }
                    },
                    onTap = {}
                )
            })
        } else {
            // Otherwise track hover state
            result = result.hoverable(enabled = enabled, interactionSource = interactionSource)

            // And finally add our custom tap-and-press detector
            DisposableEffect(interactionSource) {
                onDispose {
                    pressedInteraction.value?.let { oldValue ->
                        val interaction = PressInteraction.Cancel(oldValue)
                        interactionSource.tryEmit(interaction)
                        pressedInteraction.value = null
                    }
                }
            }
            result = result.then(Modifier.pointerInput(interactionSource, enabled) {
                detectTapAndPress(
                    onPress = { offset ->
                        if (enabled) {
                            auroraHandlePopupPressInteraction(
                                offset, interactionSource, pressedInteraction,
                                onActivatePopupState,
                                presentationModel,
                                scope,
                                clickJob
                            )
                        }
                    }
                )
            })
        }
        result
    },
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = null
        properties["role"] = Role.Button
        properties["onClick"] = onActivatePopup
        properties["indication"] = null
        properties["interactionSource"] = interactionSource
    }
)

private class CommandButtonOutlineSuppler(
    val buttonShaper: AuroraButtonShaper,
    val presentationModel: BaseCommandButtonPresentationModel
): OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        return buttonShaper.getButtonOutline(
            width = size.width,
            height = size.height,
            insets = insets,
            sides = presentationModel.sides,
            radiusAdjustment = radiusAdjustment,
            outlineKind = outlineKind,
            layoutDirection = layoutDirection,
            density = density
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, AuroraInternalApi::class)
@Composable
internal fun <M : BaseCommandMenuContentModel,
        P : BaseCommandPopupMenuPresentationModel> AuroraCommandButton(
    modifier: Modifier,
    actionInteractionSource: MutableInteractionSource,
    popupInteractionSource: MutableInteractionSource,
    originalProjection: BaseCommandButtonProjection<BaseCommand,
            BaseCommandButtonPresentationModel, BaseCommandButtonProjection<BaseCommand, BaseCommandButtonPresentationModel, *>>,
    command: BaseCommand,
    popupHandler: BaseCommandMenuHandler<M, P>,
    presentationModel: BaseCommandButtonPresentationModel,
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>
) {
    val secondaryContentModel =
        rememberUpdatedState(command.secondaryContentModel as M?)
    val drawingCache = remember { CommandButtonDrawingCache() }

    var wasActionRollover by remember { mutableStateOf(false) }
    val actionRollover by actionInteractionSource.collectIsHoveredAsState()

    if (!wasActionRollover && actionRollover) {
        SideEffect {
            command.actionPreview?.onCommandPreviewActivated(command)
        }
    }
    if (wasActionRollover && !actionRollover) {
        SideEffect {
            command.actionPreview?.onCommandPreviewCanceled(command)
        }
    }
    wasActionRollover = actionRollover

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

    val buttonTopLeftOffset = remember { AuroraOffset(0.0f, 0.0f) }
    val buttonSize = remember { mutableStateOf(IntSize(0, 0)) }
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val mergedTextStyle = LocalTextStyle.current.merge(presentationModel.textStyle)
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val popupMenu = LocalPopupMenu.current
    val popupOriginator = popupMenu ?: LocalWindow.current.rootPane

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
    @Suppress("UNUSED_VARIABLE")
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
    @Suppress("UNUSED_VARIABLE")
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

    val layoutManager: CommandButtonLayoutManager =
        presentationModel.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
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
        command.action == null, command.secondaryContentModel == null,
        presentationModel.presentationState
    ) {
        layoutManager.getPreLayoutInfo(command, presentationModel)
    }

    val hasIcon = preLayoutInfo.showIcon
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)
    val coroutineScope = rememberCoroutineScope()

    val trackBounds = LocalRibbonTrackBounds.current
    val trackKeyTips = LocalRibbonTrackKeyTips.current
    val keyTipChainRoot = LocalRibbonKeyTipChainRoot.current
    val keyTipChainRootKeyTip = LocalRibbonKeyTipChainRootKeyTip.current
    val bandRowHeight = LocalRibbonBandRowHeight.current
    val bandRow = LocalRibbonBandRow.current

    // These two track the offset of action and popup area relative in
    // the overall bounding box of the command button. To paint continuous
    // visuals of the command button across two separate Box composables,
    // we paint each as full-size area, along with clipping to the specific
    // area (action or popup) and offsetting during the Canvas paint pass.
    var actionAreaOffset = remember { Offset.Zero }
    var popupAreaOffset = remember { Offset.Zero }
    val popupAreaSize = remember { mutableStateOf(IntSize(0, 0)) }
    val onActivatePopup: () -> Unit = {
        val isShowingPopupFromHere = AuroraPopupManager.isShowingPopupFrom(
            originator = popupOriginator,
            pointInOriginator = AuroraOffset(
                x = buttonTopLeftOffset.x + popupAreaOffset.x + popupAreaSize.value.width / 2.0f,
                y = buttonTopLeftOffset.y + popupAreaOffset.y + popupAreaSize.value.height / 2.0f
            ).asOffset(density)
        )
        if (!isShowingPopupFromHere) {
            // Display our popup content.
            val popupWindow = popupHandler.showPopupContent(
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
                    offset = buttonTopLeftOffset.asOffset(density),
                    size = buttonSize.value.asSize(density)
                ),
                popupTriggerAreaInWindow = Rect(
                    offset = AuroraOffset(
                        x = buttonTopLeftOffset.x + popupAreaOffset.x,
                        y = buttonTopLeftOffset.y + popupAreaOffset.y
                    ).asOffset(density),
                    size = popupAreaSize.value.asSize(density)
                ),
                contentModel = secondaryContentModel,
                presentationModel = presentationModel.popupMenuPresentationModel as P,
                displayPrototypeCommand = null,
                toDismissPopupsOnActivation = presentationModel.toDismissPopupsOnActivation,
                popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                popupAnchorBoundsProvider = presentationModel.popupAnchorBoundsProvider,
                popupOriginatorKeyTip = presentationModel.popupKeyTip,
                overlays = secondaryOverlays,
                popupKind = AuroraPopupManager.PopupKind.Popup
            )
            coroutineScope.launch {
                popupWindow?.opacity = 1.0f
            }
        } else {
            // Showing a popup that originates from the popup area of this command button.
            // Hide it.
            AuroraPopupManager.hidePopups(originator = popupOriginator)
        }
    }

    if (popupMenu != null) {
        if (presentationModel.actionKeyTip != null) {
            KeyTipTracker.trackKeyTipInPopup(
                originalProjection,
                presentationModel.actionKeyTip!!,
                command.isActionEnabled,
                { command.action?.invoke() },
                keyTipChainRoot,
                keyTipChainRootKeyTip,
                null,
            )
        }
        if (presentationModel.popupKeyTip != null) {
            KeyTipTracker.trackKeyTipInPopup(
                originalProjection,
                presentationModel.popupKeyTip!!,
                command.isSecondaryEnabled,
                { onActivatePopup.invoke() },
                keyTipChainRoot,
                keyTipChainRootKeyTip,
                null,
            )
        }
    }

    val allKeyTips = KeyTipTracker.getKeyTips()
    val isDisplayingActionKeyTip =
        (presentationModel.actionKeyTip != null) &&
                KeyTipTracker.uiVisibleFlow.value &&
                allKeyTips.any { link ->
                    (link.chainRootKeyTip == keyTipChainRootKeyTip)
                }
    val isDisplayingPopupKeyTip =
        (presentationModel.popupKeyTip != null) &&
                KeyTipTracker.uiVisibleFlow.value &&
                allKeyTips.any { link ->
                    (link.chainRootKeyTip == keyTipChainRootKeyTip)
                }

    Layout(
        modifier = modifier.commandButtonLocator(
            originalProjection,
            command,
            presentationModel,
            buttonTopLeftOffset,
            buttonSize,
            trackBounds,
            trackKeyTips,
            keyTipChainRoot,
            keyTipChainRootKeyTip,
            command.secondaryContentModel,
            popupMenu != null,
        ),
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
                        val shouldDismissFromPopupLevel = popupMenu?.toDismissPopupsOnActivation ?: false
                        if (shouldDismissFromPopupLevel and presentationModel.toDismissPopupsOnActivation) {
                            AuroraPopupManager.hidePopups(null)
                        }
                    })
            } else {
                modifierAction = Modifier.commandButtonActionModifier(
                    enabled = isActionEnabled,
                    onActivateAction = {
                        command.action?.invoke()
                        val shouldDismissFromPopupLevel = popupMenu?.toDismissPopupsOnActivation ?: false
                        if (shouldDismissFromPopupLevel and presentationModel.toDismissPopupsOnActivation) {
                            AuroraPopupManager.hidePopups(null)
                        }
                    },
                    interactionSource = actionInteractionSource,
                    presentationModel = presentationModel
                )
            }
            Box(
                modifier = modifierAction.auroraRichTooltip(
                    richTooltip = command.actionRichTooltip,
                    presentationModel = presentationModel.actionRichTooltipPresentationModel
                ).onGloballyPositioned {
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
                    // Ignore the selected bit?
                    val ignoreSelectedState = command.isActionToggle &&
                            (presentationModel.selectedStateHighlight == SelectedStateHighlight.IconOnly)
                    val actionModelStateInfoToUse =
                        if (ignoreSelectedState) actionModelNoSelectionStateInfo
                        else actionModelStateInfo
                    val currentActionStateToUse =
                        if (ignoreSelectedState) currentActionNoSelectionState
                        else currentActionState

                    // Populate the cached color tokens for filling the action area
                    // based on the current model state info
                    populateColorTokens(
                        colorTokens = drawingCache.actionColorTokens,
                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                        colors = AuroraSkin.colors,
                        decorationAreaType = decorationAreaType,
                        modelStateInfo = actionModelStateInfoToUse,
                        currState = currentActionStateToUse.value,
                        associationKind = ContainerColorTokensAssociationKind.Default,
                        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                        treatEnabledAsActive = false,
                        skipFlatCheck = false,
                        inactiveContainerType = ContainerType.Muted)

                    val surfacePainter = AuroraSkin.painters.surfacePainter
                    val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
                    val outlinePainter = AuroraSkin.painters.outlinePainter
                    val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

                    var actionAlpha = max(combinedRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            actionModelStateInfoToUse.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else 1.0f
                    )
                    actionAlpha = actionAlpha.coerceIn(0.0f, 1.0f)

                    val outlineSupplier = CommandButtonOutlineSuppler(buttonShaper, presentationModel)

                    Canvas(modifier = Modifier.matchParentSize()) {
                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = size.width,
                                bottom = size.height,
                                clipOp = ClipOp.Intersect
                            )
                            translate(
                                left = -actionAreaOffset.x,
                                top = -actionAreaOffset.y
                            )
                        }) {
                            val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                            val outlineFill = outlineSupplier.getOutline(
                                layoutDirection = layoutDirection,
                                density = density,
                                size = buttonSize.value.asSize(),
                                insets = outlineInset,
                                radiusAdjustment = 0.0f,
                                outlineKind = OutlineKind.Surface)
                            val outlineBoundingRect = outlineFill.bounds
                            if (outlineBoundingRect.isEmpty) {
                                return@withTransform
                            }

                            paintSurface(
                                drawScope = this,
                                componentState = currentActionStateToUse.value,
                                surfacePainter = surfacePainter,
                                surfacePainterOverlay = surfacePainterOverlay,
                                size = buttonSize.value.asSize(),
                                alpha = actionAlpha,
                                outline = outlineFill,
                                colorTokens = drawingCache.actionColorTokens)

                            paintOutline(
                                drawScope = this,
                                componentState = currentActionStateToUse.value,
                                outlinePainter = outlinePainter,
                                outlinePainterOverlay = outlinePainterOverlay,
                                size = buttonSize.value.asSize(),
                                alpha = actionAlpha,
                                outlineSupplier = outlineSupplier,
                                colorTokens = drawingCache.actionColorTokens)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.commandButtonPopupModifier(
                    enabled = isPopupEnabled,
                    onActivatePopup = onActivatePopup,
                    onDeactivatePopup = {
                        val isShowingPopupFromHere = AuroraPopupManager.isShowingPopupFrom(
                            originator = popupOriginator,
                            pointInOriginator = AuroraOffset(
                                x = buttonTopLeftOffset.x + popupAreaOffset.x + popupAreaSize.value.width / 2.0f,
                                y = buttonTopLeftOffset.y + popupAreaOffset.y + popupAreaSize.value.height / 2.0f
                            ).asOffset(density)
                        )
                        if (!isShowingPopupFromHere) {
                            // We're not showing a popup that originates from the popup area of this
                            // command button. Hide all popups that originate from our originator.
                            AuroraPopupManager.hidePopups(originator = popupOriginator)
                        }
                    },
                    interactionSource = popupInteractionSource,
                    presentationModel = presentationModel
                ).onPointerEvent(PointerEventType.Enter) {
                    if (isPopupEnabled) {
                        popupRollover = true
                    }
                }.onPointerEvent(PointerEventType.Exit) {
                    if (isPopupEnabled) {
                        popupRollover = false
                    }
                }.auroraRichTooltip(
                    richTooltip = command.secondaryRichTooltip,
                    presentationModel = presentationModel.popupRichTooltipPresentationModel
                ).onGloballyPositioned {
                    if (it.parentCoordinates != null) {
                        val selfToRoot = it.localToRoot(Offset.Zero)
                        val parentToRoot = it.parentCoordinates!!.localToRoot(Offset.Zero)
                        popupAreaOffset = Offset(
                            x = selfToRoot.x - parentToRoot.x,
                            y = selfToRoot.y - parentToRoot.y
                        )
                    }
                    popupAreaSize.value = it.size
                }
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
                    // Populate the cached color tokens for filling the button container
                    // based on the current model state info
                    populateColorTokens(
                        colorTokens = drawingCache.popupColorTokens,
                        colors = AuroraSkin.colors,
                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                        decorationAreaType = decorationAreaType,
                        modelStateInfo = popupModelStateInfo,
                        currState = currentPopupState.value,
                        associationKind = ContainerColorTokensAssociationKind.Default,
                        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                        treatEnabledAsActive = false,
                        skipFlatCheck = false,
                        inactiveContainerType = ContainerType.Muted)

                    val surfacePainter = AuroraSkin.painters.surfacePainter
                    val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
                    val outlinePainter = AuroraSkin.painters.outlinePainter
                    val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

                    var popupAlpha = max(combinedRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            popupModelStateInfo.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else 1.0f
                    )
                    popupAlpha = popupAlpha.coerceIn(0.0f, 1.0f)

                    val outlineSupplier = CommandButtonOutlineSuppler(buttonShaper, presentationModel)

                    Canvas(modifier = Modifier.matchParentSize()) {
                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = size.width,
                                bottom = size.height,
                                clipOp = ClipOp.Intersect
                            )
                            translate(
                                left = -popupAreaOffset.x,
                                top = -popupAreaOffset.y
                            )
                        }) {
                            val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                            val outlineFill = outlineSupplier.getOutline(
                                layoutDirection = layoutDirection,
                                density = density,
                                size = buttonSize.value.asSize(),
                                insets = outlineInset,
                                radiusAdjustment = 0.0f,
                                outlineKind = OutlineKind.Surface)
                            val outlineBoundingRect = outlineFill.bounds
                            if (outlineBoundingRect.isEmpty) {
                                return@withTransform
                            }

                            paintSurface(
                                drawScope = this,
                                componentState = currentPopupState.value,
                                surfacePainter = surfacePainter,
                                surfacePainterOverlay = surfacePainterOverlay,
                                size = buttonSize.value.asSize(),
                                alpha = popupAlpha,
                                outline = outlineFill,
                                colorTokens = drawingCache.popupColorTokens)

                            paintOutline(
                                drawScope = this,
                                componentState = currentPopupState.value,
                                outlinePainter = outlinePainter,
                                outlinePainterOverlay = outlinePainterOverlay,
                                size = buttonSize.value.asSize(),
                                alpha = popupAlpha,
                                outlineSupplier = outlineSupplier,
                                colorTokens = drawingCache.popupColorTokens)
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

                // Compute the text / icon color based on the passed model state (which can be action
                // or popup)
                val textColor = getTextColor(
                    modelStateInfo = modelStateInfoForIcon,
                    currState = currStateForIcon,
                    colors = AuroraSkin.colors,
                    tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                    decorationAreaType = decorationAreaType,
                    associationKind = ContainerColorTokensAssociationKind.Default,
                    backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                    skipFlatCheck = false,
                    inactiveContainerType = ContainerType.Muted,
                    isTextInFilledArea = true
                )

                CommandButtonIconContent(
                    command,
                    presentationModel,
                    layoutManager.getPreferredIconSize(command, presentationModel),
                    modelStateInfoForIcon,
                    currStateForIcon,
                    textColor,
                    drawingCache
                )
            }

            // Text content can be in action or popup area. Use the matching model
            // to determine the text color
            val ignoreSelectedState = command.isActionToggle &&
                    (presentationModel.selectedStateHighlight == SelectedStateHighlight.IconOnly)
            val modelStateInfoForText = if (isTextInActionArea) {
                if (ignoreSelectedState) actionModelNoSelectionStateInfo
                else actionModelStateInfo
            } else {
                popupModelStateInfo
            }
            val currStateForText = if (isTextInActionArea) {
                if (ignoreSelectedState) currentActionNoSelectionState.value
                else currentActionState.value
            } else {
                currentPopupState.value
            }

            // Compute the text color based on the passed model state (which can be action
            // or popup)
            val textColor = getTextColor(
                modelStateInfo = modelStateInfoForText,
                currState = currStateForText,
                colors = AuroraSkin.colors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Default,
                backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                skipFlatCheck = false,
                inactiveContainerType = ContainerType.Muted,
                isTextInFilledArea = true
            )
            val textVariantColor = getTextVariantColor(
                modelStateInfo = modelStateInfoForText,
                currState = currStateForText,
                colors = AuroraSkin.colors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Default,
                backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                skipFlatCheck = false,
                inactiveContainerType = ContainerType.Muted,
                isTextInFilledArea = true
            )

            for (text in preLayoutInfo.texts) {
                CommandButtonTextContent(
                    presentationModel, text, modelStateInfoForText, currStateForText,
                    textColor, resolvedTextStyle
                )
            }
            for (extraText in preLayoutInfo.extraTexts) {
                CommandButtonExtraTextContent(
                    presentationModel, extraText, modelStateInfoForText, currStateForText,
                    textVariantColor, resolvedTextStyle, layoutManager.getExtraTextMaxLines()
                )
            }

            // Popup action (arrow) if we need one
            if (preLayoutInfo.showPopupIcon) {
                CommandButtonPopupIconContent(
                    presentationModel = presentationModel,
                    popupPlacementStrategy = presentationModel.popupPlacementStrategy,
                    modelStateInfo = popupModelStateInfo,
                    currState = currentPopupState.value
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

                    else -> {}
                }
            }

            if (popupMenu != null) {
                if (isDisplayingActionKeyTip) {
                    CommandButtonKeyTip(
                        originalProjection = originalProjection,
                        keyTip = presentationModel.actionKeyTip!!,
                        isEnabled = isActionEnabled,
                        buttonSize = buttonSize.value,
                    )
                }
                if (isDisplayingPopupKeyTip) {
                    CommandButtonKeyTip(
                        originalProjection = originalProjection,
                        keyTip = presentationModel.popupKeyTip!!,
                        isEnabled = isPopupEnabled,
                        buttonSize = buttonSize.value,
                    )
                }
            }

            SideEffect {
                if (actionRollover) {
                    val isShowingPopupFromHere = AuroraPopupManager.isShowingPopupFrom(
                        originator = popupOriginator,
                        pointInOriginator = AuroraOffset(
                            x = buttonTopLeftOffset.x + popupAreaOffset.x + popupAreaSize.value.width / 2.0f,
                            y = buttonTopLeftOffset.y + popupAreaOffset.y + popupAreaSize.value.height / 2.0f
                        ).asOffset(density)
                    )
                    if (!isShowingPopupFromHere) {
                        // We're not showing a popup that originates from the popup area of this
                        // command button. Hide all popups that originate from our originator.
                        AuroraPopupManager.hidePopups(originator = popupOriginator)
                    }
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
            preLayoutInfo = preLayoutInfo,
            buttonShaper = buttonShaper
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

        var actionKeyTipPlaceable: Placeable? = null
        if ((popupMenu != null) && isDisplayingActionKeyTip) {
            val actionKeyTipSizingInfo = getKeyTipSize(
                presentationModel.actionKeyTip!!, mergedTextStyle, density, fontFamilyResolver, layoutDirection
            )
            val actionKeyTipMeasurable = measurables[childIndex++]
            actionKeyTipPlaceable = actionKeyTipMeasurable.measure(
                Constraints.fixed(
                    width = actionKeyTipSizingInfo.first.width.roundToInt(),
                    height = actionKeyTipSizingInfo.first.height.roundToInt()
                )
            )
        }

        var popupKeyTipPlaceable: Placeable? = null
        if ((popupMenu != null) && isDisplayingPopupKeyTip) {
            val popupKeyTipSizingInfo = getKeyTipSize(
                presentationModel.popupKeyTip!!, mergedTextStyle, density, fontFamilyResolver, layoutDirection
            )
            val popupKeyTipMeasurable = measurables[childIndex]
            popupKeyTipPlaceable = popupKeyTipMeasurable.measure(
                Constraints.fixed(
                    width = popupKeyTipSizingInfo.first.width.roundToInt(),
                    height = popupKeyTipSizingInfo.first.height.roundToInt()
                )
            )
        }

        if (popupMenu == null) {
            if ((presentationModel.actionKeyTip != null) && !layoutInfo.actionClickArea.isEmpty) {
                KeyTipTracker.trackKeyTipOffset(
                    originalProjection,
                    presentationModel.actionKeyTip!!,
                    command.isActionEnabled,
                    false,
                    getAdjustedAnchor(
                        anchor = layoutManager.getActionKeyTipAnchorCenterPoint(command, presentationModel, layoutInfo),
                        row = bandRow,
                        rowHeight = bandRowHeight
                    ),
                    {
                        coroutineScope.launch {
                            command.action?.invoke()
                        }
                    },
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    null
                )
            }
            if ((presentationModel.popupKeyTip != null) && !layoutInfo.popupClickArea.isEmpty) {
                KeyTipTracker.trackKeyTipOffset(
                    originalProjection,
                    presentationModel.popupKeyTip!!,
                    command.isSecondaryEnabled,
                    false,
                    getAdjustedAnchor(
                        anchor = layoutManager.getPopupKeyTipAnchorCenterPoint(command, presentationModel, layoutInfo),
                        row = bandRow,
                        rowHeight = bandRowHeight
                    ),
                    {
                        coroutineScope.launch {
                            onActivatePopup.invoke()
                        }
                    },
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    command.secondaryContentModel
                )
            }
        }

        layout(
            width = layoutInfo.fullSize.width.toInt(),
            height = layoutInfo.fullSize.height.toInt()
        ) {
            actionPlaceable.place(
                x = layoutInfo.actionClickArea.left.roundToInt(),
                y = layoutInfo.actionClickArea.top.roundToInt()
            )
            popupPlaceable.place(
                x = layoutInfo.popupClickArea.left.roundToInt(),
                y = layoutInfo.popupClickArea.top.roundToInt()
            )
            iconPlaceable?.place(
                x = layoutInfo.iconRect.left.roundToInt(),
                y = layoutInfo.iconRect.top.roundToInt()
            )
            for ((index, textPlaceable) in textPlaceables.withIndex()) {
                textPlaceable.place(
                    x = layoutInfo.textLayoutInfoList[index].textRect.left.roundToInt(),
                    y = layoutInfo.textLayoutInfoList[index].textRect.top.roundToInt()
                )
            }
            for ((index, extraTextPlaceable) in extraTextPlaceables.withIndex()) {
                extraTextPlaceable.place(
                    x = layoutInfo.extraTextLayoutInfoList[index].textRect.left.roundToInt(),
                    y = layoutInfo.extraTextLayoutInfoList[index].textRect.top.roundToInt()
                )
            }
            popupIconPlaceable?.place(
                x = layoutInfo.popupActionRect.left.roundToInt(),
                y = layoutInfo.popupActionRect.top.roundToInt()
            )
            separatorPlaceable?.place(
                x = layoutInfo.separatorArea.left.roundToInt(),
                y = layoutInfo.separatorArea.top.roundToInt()
            )
            if (actionKeyTipPlaceable != null) {
                val actionKeyTipAnchor =
                    layoutManager.getActionKeyTipAnchorCenterPoint(command, presentationModel, layoutInfo)
                var x = actionKeyTipAnchor.x - actionKeyTipPlaceable.measuredWidth / 2
                var y = actionKeyTipAnchor.y - actionKeyTipPlaceable.measuredHeight / 2

                // Now fit it inside the bounds
                if (x < 0) {
                    x = 0.0f
                }
                if (y < 0) {
                    y = 0.0f
                }
                if ((x + actionKeyTipPlaceable.measuredWidth) > layoutInfo.fullSize.width) {
                    x = layoutInfo.fullSize.width - actionKeyTipPlaceable.measuredWidth
                }
                if ((y + actionKeyTipPlaceable.measuredHeight) > layoutInfo.fullSize.height) {
                    y = layoutInfo.fullSize.height - actionKeyTipPlaceable.measuredHeight
                }
                actionKeyTipPlaceable.place(x.toInt(), y.toInt())
            }
            if (popupKeyTipPlaceable != null) {
                val popupKeyTipAnchor =
                    layoutManager.getPopupKeyTipAnchorCenterPoint(command, presentationModel, layoutInfo)
                var x = popupKeyTipAnchor.x - popupKeyTipPlaceable.measuredWidth / 2
                var y = popupKeyTipAnchor.y - popupKeyTipPlaceable.measuredHeight / 2

                // Now fit it inside the bounds
                if (x < 0) {
                    x = 0.0f
                }
                if (y < 0) {
                    y = 0.0f
                }
                if ((x + popupKeyTipPlaceable.measuredWidth) > layoutInfo.fullSize.width) {
                    x = layoutInfo.fullSize.width - popupKeyTipPlaceable.measuredWidth
                }
                if ((y + popupKeyTipPlaceable.measuredHeight) > layoutInfo.fullSize.height) {
                    y = layoutInfo.fullSize.height - popupKeyTipPlaceable.measuredHeight
                }
                popupKeyTipPlaceable.place(x.toInt(), y.toInt())
            }
        }
    }

    DisposableEffect(originalProjection) {
        onDispose {
            BoundsTracker.untrackBounds(originalProjection)
            KeyTipTracker.untrackKeyTip(originalProjection)
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun CommandButtonTextContent(
    presentationModel: BaseCommandButtonPresentationModel,
    text: String, modelStateInfo: ModelStateInfo, currState: ComponentState,
    defaultTextColor: Color, style: TextStyle
) {
    // Pass our text color and model state snapshot to the children
    CompositionLocalProvider(
        LocalTextColor provides defaultTextColor,
        LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState)
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        AuroraText(
            text = text,
            color = defaultTextColor,
            style = style,
            maxLines = 1,
            overflow = presentationModel.textOverflow
        )
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun CommandButtonExtraTextContent(
    presentationModel: BaseCommandButtonPresentationModel,
    text: String, modelStateInfo: ModelStateInfo, currState: ComponentState,
    defaultTextColor: Color, style: TextStyle, maxLines: Int
) {
    // Pass our text color and model state snapshot to the children
    CompositionLocalProvider(
        LocalTextColor provides defaultTextColor,
        LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState)
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        AuroraText(
            text = text,
            color = defaultTextColor,
            style = style,
            maxLines = maxLines,
            overflow = presentationModel.textOverflow
        )
    }
}

private object CommandButtonSelectedIconOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        return Outline.Rectangle(
            Rect(
                left = insets, top = insets,
                right = size.width - insets, bottom = size.height - insets
            )
        )
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun CommandButtonIconContent(
    command: BaseCommand,
    presentationModel: BaseCommandButtonPresentationModel,
    iconSize: DpSize,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    defaultIconColor: Color,
    drawingCache: CommandButtonDrawingCache
) {
    // Compute the combined strength of all the
    // states that have the selection bit turned on
    val selectionAlpha = modelStateInfo.stateContributionMap
        .filter { it.key.isFacetActive(ComponentStateFacet.Selection) }
        .map { it.value }
        .sumOf { it.contribution.toDouble() }
        .toFloat()
    val showSelectionAroundIcon = (presentationModel.selectedStateHighlight == SelectedStateHighlight.IconOnly)
            && (selectionAlpha > 0.0f)

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val surfacePainter = AuroraSkin.painters.surfacePainter
    val outlinePainter = AuroraSkin.painters.outlinePainter

    Box {
        if (showSelectionAroundIcon) {
            Canvas(modifier = Modifier.matchParentSize()) {
                // Background fill / border for selected toggle menu commands
                val stateForBackground =
                    if (currState.isDisabled) ComponentState.DisabledSelected
                    else ComponentState.Selected

                val highlightColorTokens = getContainerTokens(
                    colors = skinColors,
                    tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                    decorationAreaType = decorationAreaType,
                    associationKind = ContainerColorTokensAssociationKind.Highlight,
                    componentState = stateForBackground,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                    inactiveContainerType = ContainerType.Neutral
                )

                val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                val outlineFill = CommandButtonSelectedIconOutlineSuppler.getOutline(
                    layoutDirection = layoutDirection,
                    density = this,
                    size = this.size,
                    insets = outlineInset,
                    radiusAdjustment = 0.0f,
                    outlineKind = OutlineKind.Surface)

                paintSurface(
                    drawScope = this,
                    componentState = stateForBackground,
                    surfacePainter = surfacePainter,
                    surfacePainterOverlay = null,
                    size = this.size,
                    alpha = selectionAlpha,
                    outline = outlineFill,
                    colorTokens = highlightColorTokens)

                paintOutline(
                    drawScope = this,
                    componentState = stateForBackground,
                    outlinePainter = outlinePainter,
                    outlinePainterOverlay = null,
                    size = this.size,
                    alpha = selectionAlpha,
                    outlineSupplier = CommandButtonSelectedIconOutlineSuppler,
                    colorTokens = highlightColorTokens)
            }
        }
        if (command.icon == null) {
            // If we get to this function, we are being asked to display the icon. If the icon
            // factory is null, we display a checkmark if the button is in selected
            // state (full or partial)

            // Checkmark color
            val markColor = getStateAwareColor(
                modelStateInfo = modelStateInfo,
                currState = currState,
                colors = AuroraSkin.colors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Mark,
                backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                skipFlatCheck = false,
                inactiveContainerType = ContainerType.Muted,
            ) { it.onContainer }

            val markColorTokens = getContainerTokens(
                colors = AuroraSkin.colors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Mark,
                componentState = currState,
                backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                inactiveContainerType = ContainerType.Muted,
                skipFlatCheck = false
            )
            val markAlpha = if (currState.isDisabled) markColorTokens.onContainerDisabledAlpha else 1.0f

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
                        alpha = markAlpha
                    )
                }
            }
        } else {
            val icon = if (command.icon is TransitionAwarePainterDelegate)
                (command.icon as TransitionAwarePainterDelegate).createNewIcon(modelStateInfo.getSnapshot(currState))
            else
                command.icon

            // Pass our text / icon color and model state snapshot to the children
            CompositionLocalProvider(
                LocalTextColor provides defaultIconColor,
                LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState),
                LocalColorTokensOverlayProvider provides presentationModel.colorTokensOverlayProvider
            ) {
                AuroraThemedIcon(
                    icon = icon!!,
                    size = iconSize,
                    disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                    enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                    activeFilterStrategy = presentationModel.iconActiveFilterStrategy
                )
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun CommandButtonPopupIconContent(
    presentationModel: BaseCommandButtonPresentationModel,
    popupPlacementStrategy: PopupPlacementStrategy,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState
) {
    val decorationAreaType = AuroraSkin.decorationAreaType

    val arrowColor = getStateAwareColor(
        modelStateInfo = modelStateInfo,
        currState = currState,
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Mark,
        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
        skipFlatCheck = false,
        inactiveContainerType = ContainerType.Muted,
    ) { it.onContainer }

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            val arrowWidth = if (popupPlacementStrategy.isHorizontal)
                ArrowSizingConstants.DefaultSingleArrowHeight.toPx() else
                ArrowSizingConstants.DefaultSingleArrowWidth.toPx()
            val arrowHeight =
                if (popupPlacementStrategy.isHorizontal)
                    ArrowSizingConstants.DefaultSingleArrowWidth.toPx() else
                    ArrowSizingConstants.DefaultSingleArrowHeight.toPx()
            translate(
                left = (size.width - arrowWidth) / 2.0f,
                top = (size.height - arrowHeight) / 2.0f
            ) {
                drawArrow(
                    drawScope = this,
                    width = arrowWidth,
                    height = arrowHeight,
                    strokeWidth = ArrowSizingConstants.DefaultArrowStroke.toPx(),
                    popupPlacementStrategy = popupPlacementStrategy,
                    layoutDirection = layoutDirection,
                    color = arrowColor
                )
            }
        }
    }
}


@OptIn(AuroraInternalApi::class)
@Composable
private fun CommandButtonKeyTip(
    originalProjection: BaseCommandButtonProjection<*, *, *>,
    keyTip: String,
    isEnabled: Boolean,
    buttonSize: IntSize,
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = resolveDefaults(LocalTextStyle.current, layoutDirection)
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val textMeasurer = rememberTextMeasurer(cacheSize = 10)

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawKeyTip(
                keyTipInfo = KeyTipTracker.KeyTipLink(
                    projection = originalProjection,
                    keyTip = keyTip,
                    isEnabled = isEnabled,
                    isInPopup = false,
                    screenRect = AuroraRect(0.0f, 0.0f, buttonSize.width.toFloat(), buttonSize.height.toFloat()),
                    anchor = Offset(size.width / 2.0f, size.height / 2.0f),
                    onActivated = null,
                    chainRoot = null,
                    chainRootKeyTip = null,
                    traversal = { null }
                ),
                textStyle = textStyle,
                density = density,
                textMeasurer = textMeasurer,
                fontFamilyResolver = fontFamilyResolver,
                layoutDirection = layoutDirection,
                insets = 0.dp,
                decorationAreaType = decorationAreaType,
                painters = painters,
                skinColors = skinColors,
                tokensOverlayProvider = originalProjection.presentationModel.colorTokensOverlayProvider,
            )
        }
    }
}

@OptIn(AuroraInternalApi::class)
private class CommandButtonLocator(
    val originalProjection: BaseCommandButtonProjection<*, *, *>,
    val command: BaseCommand,
    val presentationModel: BaseCommandButtonPresentationModel,
    val topLeftOffset: AuroraOffset,
    val size: MutableState<IntSize>,
    val trackBounds: Boolean,
    val trackKeyTips: Boolean,
    val keyTipChainRoot: Any?,
    val keyTipChainRootKeyTip: String?,
    val popupKeyTipTraversal: Any?,
    val isInPopup: Boolean
) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.value = coordinates.size

        val bounds = AuroraRect(
            x = converted.x,
            y = converted.y,
            width = coordinates.size.width.toFloat(),
            height = coordinates.size.height.toFloat()
        )
        if (trackBounds) {
            BoundsTracker.trackBounds(originalProjection, bounds)
        }

        if (trackKeyTips) {
            if (presentationModel.actionKeyTip != null) {
                KeyTipTracker.trackKeyTipBase(
                    originalProjection,
                    presentationModel.actionKeyTip!!,
                    command.isActionEnabled,
                    isInPopup,
                    bounds,
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    null,
                )
            }
            if (presentationModel.popupKeyTip != null) {
                KeyTipTracker.trackKeyTipBase(
                    originalProjection,
                    presentationModel.popupKeyTip!!,
                    command.isSecondaryEnabled,
                    isInPopup,
                    bounds,
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    popupKeyTipTraversal
                )
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun Modifier.commandButtonLocator(
    originalProjection: BaseCommandButtonProjection<*, *, *>,
    command: BaseCommand,
    presentationModel: BaseCommandButtonPresentationModel,
    topLeftOffset: AuroraOffset,
    size: MutableState<IntSize>,
    trackBounds: Boolean,
    trackKeyTips: Boolean,
    keyTipChainRoot: Any?,
    keyTipChainRootKeyTip: String?,
    popupKeyTipTraversal: Any?,
    isInPopup: Boolean,
) = this.then(
    CommandButtonLocator(
        originalProjection,
        command,
        presentationModel,
        topLeftOffset,
        size,
        trackBounds,
        trackKeyTips,
        keyTipChainRoot,
        keyTipChainRootKeyTip,
        popupKeyTipTraversal,
        isInPopup,
    )
)
