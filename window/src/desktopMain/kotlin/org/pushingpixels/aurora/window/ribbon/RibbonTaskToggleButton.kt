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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraOffset
import org.pushingpixels.aurora.common.AuroraRect
import org.pushingpixels.aurora.common.asOffset
import org.pushingpixels.aurora.component.auroraRichTooltip
import org.pushingpixels.aurora.component.model.BaseCommand
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.impl.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.*
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
@OptIn(AuroraInternalApi::class)
private class RibbonTaskToggleButtonDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

private class RibbonTaskToggleButtonOutlineSuppler(val presentationModel: CommandButtonPresentationModel): OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = density.getClassicCornerRadius()
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = cornerRadius - radiusAdjustment,
            sides = presentationModel.sides,
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonTaskToggleButton(
    modifier: Modifier,
    originalProjection: BaseCommandButtonProjection<BaseCommand,
            BaseCommandButtonPresentationModel, BaseCommandButtonProjection<BaseCommand, BaseCommandButtonPresentationModel, *>>,
    command: Command,
    presentationModel: CommandButtonPresentationModel,
    showSelectedTaskInPopup: Boolean,
    onUpdateShowSelectedTaskInPopup: (Boolean) -> Unit
) {
    val actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { RibbonTaskToggleButtonDrawingCache() }

    val isActionPressed by actionInteractionSource.collectIsPressedAsState()
    val actionRollover by actionInteractionSource.collectIsHoveredAsState()

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

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = ClassicButtonShaper.Instance

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val mergedTextStyle = LocalTextStyle.current.merge(presentationModel.textStyle)
    val fontFamilyResolver = LocalFontFamilyResolver.current

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

    val layoutManager =
        presentationModel.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
        )

    val isActionEnabled = command.isActionEnabled

    // TODO - do we need more keys? Maybe from the presentation model
    val preLayoutInfo = remember(
        command.text, command.extraText,
        command.action == null, command.secondaryContentModel == null,
        presentationModel.presentationState
    ) {
        layoutManager.getPreLayoutInfo(command, presentationModel)
    }

    val rootSize = Size(
        width = LocalTopWindowSize.current.width.value * LocalDensity.current.density,
        height = LocalTopWindowSize.current.height.value * LocalDensity.current.density
    )
    val buttonTopLeftOffset = remember { AuroraOffset(0.0f, 0.0f) }
    val buttonSize = remember { mutableStateOf(IntSize(0, 0)) }
    val coroutineScope = rememberCoroutineScope()

    val trackBounds = LocalRibbonTrackBounds.current
    val keyTipChainRoot = LocalRibbonKeyTipChainRoot.current
    val keyTipChainRootKeyTip = LocalRibbonKeyTipChainRootKeyTip.current
    val trackKeyTips = LocalRibbonTrackKeyTips.current

    Layout(
        modifier = modifier.ribbonTaskToggleButtonLocator(
            originalProjection,
            buttonTopLeftOffset,
            buttonSize,
            trackBounds,
            trackKeyTips,
            keyTipChainRoot,
            keyTipChainRootKeyTip,
            command.tag
        ),
        content = {
            // This button is a sort of in-between. It is toggleable in the sense that it can be
            // selected, but it does not lose selection when it's clicked again. Modifier.toggleable
            // has `onValueChanged` but it's not as good of an indicator that the button has been
            // clicked as `Modifier.clickable` and its `onClick`.
            val clickableModifier = Modifier.clickable(
                enabled = isActionEnabled,
                role = Role.Tab,
                interactionSource = actionInteractionSource,
                indication = null,
                onClick = {
                    command.action?.invoke()
                    onUpdateShowSelectedTaskInPopup.invoke(!showSelectedTaskInPopup)
                }
            )
            Box(
                modifier = clickableModifier.auroraRichTooltip(
                    richTooltip = command.actionRichTooltip,
                    presentationModel = presentationModel.actionRichTooltipPresentationModel
                )
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
                    // Populate the cached color tokens for filling the action area
                    // based on the current model state info
                    populateColorTokens(
                        colorTokens = drawingCache.colorTokens,
                        modelStateInfo = actionModelNoSelectionStateInfo,
                        currState = currentActionNoSelectionState.value,
                        colorTokensDelegate = object: ColorTokensDelegate {
                            override fun getContainerTokensForActiveState(state: ComponentState): ContainerColorTokens {
                                return getContainerTokens(
                                    colors = skinColors,
                                    tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                                    decorationAreaType = decorationAreaType,
                                    componentState = state,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                                    inactiveContainerType = ContainerType.Active
                                )
                            }

                            override fun getContainerTokensForCurrentState(state: ComponentState): ContainerColorTokens {
                                return if (state == ComponentState.Enabled) {
                                    getContainerTokens(
                                        colors = skinColors,
                                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                                        decorationAreaType = decorationAreaType,
                                        componentState = state,
                                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                                        inactiveContainerType = ContainerType.Neutral
                                    )
                                } else {
                                    getContainerTokens(
                                        colors = skinColors,
                                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                                        decorationAreaType = decorationAreaType,
                                        componentState = state,
                                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                                        inactiveContainerType = ContainerType.Active
                                    )
                                }
                            }
                        }
                    )

                    val outlinePainter = AuroraSkin.painters.outlinePainter
                    val decorationPainter = AuroraSkin.painters.decorationPainter

                    val actionAlpha = max(actionRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            actionModelStateInfo.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else 1.0f
                    )

                    val outlineSupplier = RibbonTaskToggleButtonOutlineSuppler(presentationModel)

                    Canvas(modifier = Modifier.matchParentSize().graphicsLayer(alpha = actionAlpha)) {
                        val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                        val outlineFill = outlineSupplier.getOutline(
                            layoutDirection = layoutDirection,
                            density = density,
                            size = this.size,
                            insets = outlineInset,
                            radiusAdjustment = 0.0f,
                            outlineKind = OutlineKind.Surface)

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = size.width,
                                bottom = size.height,
                                clipOp = ClipOp.Intersect
                            )
                        }) {
                            if (actionAlpha > 0.0f) {
                                if (skinColors.isRegisteredAsDecorationArea(decorationAreaType)) {
                                    // If the current skin has a decoration painter that provides custom visuals
                                    // for this decoration area, use it
                                    decorationPainter.paintDecorationArea(
                                        drawScope = this,
                                        decorationAreaType = decorationAreaType,
                                        componentSize = size,
                                        outline = outlineFill,
                                        rootSize = rootSize,
                                        offsetFromRoot = buttonTopLeftOffset.asOffset(density),
                                        colorTokens = drawingCache.colorTokens
                                    )
                                } else {
                                    // Otherwise use flat color fill
                                    drawOutline(
                                        color = drawingCache.colorTokens.containerSurface,
                                        outline = outlineFill
                                    )
                                }
                            }
                        }
                    }

                    Canvas(modifier = Modifier.matchParentSize()) {
                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = size.width,
                                bottom = size.height,
                                clipOp = ClipOp.Intersect
                            )
                        }) {
                            paintOutline(
                                drawScope = this,
                                componentState = currentActionState.value,
                                outlinePainter = outlinePainter,
                                outlinePainterOverlay = null,
                                size = this.size,
                                alpha = actionAlpha,
                                outlineSupplier = outlineSupplier,
                                colorTokens = drawingCache.colorTokens)
                        }
                    }
                }
            }

            for (text in preLayoutInfo.texts) {
                TaskToggleButtonTextContent(
                    text, presentationModel, actionModelNoSelectionStateInfo,
                    currentActionState.value, currentActionNoSelectionState.value,
                    resolvedTextStyle
                )
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

        // Measure the action box
        var childIndex = 0
        val actionMeasurable = measurables[childIndex++]
        val actionPlaceable = actionMeasurable.measure(
            Constraints.fixed(
                width = layoutInfo.actionClickArea.width.roundToInt(),
                height = layoutInfo.actionClickArea.height.roundToInt()
            )
        )

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

        if ((presentationModel.actionKeyTip != null) && !layoutInfo.actionClickArea.isEmpty) {
            KeyTipTracker.trackKeyTipOffset(
                originalProjection,
                presentationModel.actionKeyTip!!,
                command.isActionEnabled,
                false,
                layoutManager.getActionKeyTipAnchorCenterPoint(command, presentationModel, layoutInfo),
                {
                    coroutineScope.launch {
                        command.action?.invoke()
                    }
                },
                keyTipChainRoot,
                keyTipChainRootKeyTip,
                command.tag
            )
        }

        layout(
            width = layoutInfo.fullSize.width.toInt(),
            height = layoutInfo.fullSize.height.toInt()
        ) {
            actionPlaceable.place(
                x = layoutInfo.actionClickArea.left.roundToInt(),
                y = layoutInfo.actionClickArea.top.roundToInt()
            )
            for ((index, textPlaceable) in textPlaceables.withIndex()) {
                textPlaceable.place(
                    x = layoutInfo.textLayoutInfoList[index].textRect.left.roundToInt(),
                    y = layoutInfo.textLayoutInfoList[index].textRect.top.roundToInt()
                )
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
private fun TaskToggleButtonTextContent(
    text: String,
    presentationModel: CommandButtonPresentationModel,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    currStateIgnoreSelection: ComponentState,
    style: TextStyle
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors

    // Compute the text color based on the passed model state
    val textColor = getTextColor(
        modelStateInfo = modelStateInfo,
        currState = currState,
        currStateIgnoreSelection = currStateIgnoreSelection,
        skinColors = skinColors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = decorationAreaType,
        containerColorTokensAssociationKind = ContainerColorTokensAssociationKind.Default
    )
    // Pass our text color to the children
    CompositionLocalProvider(
        LocalTextColor provides textColor
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        val textAlign = if (LocalLayoutDirection.current == LayoutDirection.Ltr) TextAlign.Left else TextAlign.Right
        AuroraText(
            text = text,
            color = textColor,
            style = style.copy(textAlign = textAlign),
            maxLines = 1,
            overflow = presentationModel.textOverflow,
            textAlign = textAlign
        )
    }
}

@OptIn(AuroraInternalApi::class)
private fun getTextColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    currStateIgnoreSelection: ComponentState,
    skinColors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    decorationAreaType: DecorationAreaType,
    containerColorTokensAssociationKind: ContainerColorTokensAssociationKind
): Color {
    val activeStates: Map<ComponentState, StateContributionInfo> = modelStateInfo.stateContributionMap

    val buttonColorTokens = getContainerTokens(
        colors = skinColors,
        tokensOverlayProvider = tokensOverlayProvider,
        decorationAreaType = decorationAreaType,
        componentState = currStateIgnoreSelection,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Active
    )

    val parentDecorationAreaType = DecorationAreaType.Header
    val parentColorTokens = skinColors.getNeutralContainerTokens(decorationAreaType = parentDecorationAreaType)

    if (currState.isDisabled || (activeStates.size == 1)) {
        // In enabled state the task toggle button does not show any background. Take the foreground
        // color from the tokens of the parent
        val tokensForCurrState = if (currState == ComponentState.Enabled) parentColorTokens else buttonColorTokens
        return tokensForCurrState.onContainer
    }

    // Get the combined foreground color from all states
    var aggrRed = 0f
    var aggrGreen = 0f
    var aggrBlue = 0f
    for ((activeState, value) in activeStates) {
        val contribution = value.contribution
        val correspondsToParentFill = (activeState == ComponentState.Enabled) &&
                !currState.isFacetActive(ComponentStateFacet.Selection)

        val activeColorTokens = getContainerTokens(
            colors = skinColors,
            tokensOverlayProvider = tokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = containerColorTokensAssociationKind,
            componentState = activeState,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            inactiveContainerType = ContainerType.Active
        )

        val activeForeground = if (correspondsToParentFill) parentColorTokens.onContainer else
            activeColorTokens.onContainer
        aggrRed += contribution * activeForeground.red
        aggrGreen += contribution * activeForeground.green
        aggrBlue += contribution * activeForeground.blue
    }
    val foreground = Color(red = aggrRed, blue = aggrBlue, green = aggrGreen, alpha = 1.0f)
    return foreground
}

@OptIn(AuroraInternalApi::class)
private class RibbonTaskToggleButtonLocator(
    val projection: BaseCommandButtonProjection<*, *, *>,
    val topLeftOffset: AuroraOffset,
    val size: MutableState<IntSize>,
    val trackBounds: Boolean,
    val trackKeyTips: Boolean,
    val keyTipChainRoot: Any?,
    val keyTipChainRootKeyTip: String?,
    val keyTipTraversal: Any?,
) : OnGloballyPositionedModifier {
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
            BoundsTracker.trackBounds(projection, bounds)
        }

        if (trackKeyTips) {
            if (projection.presentationModel.actionKeyTip != null) {
                KeyTipTracker.trackKeyTipBase(
                    projection,
                    projection.presentationModel.actionKeyTip!!,
                    projection.contentModel.isActionEnabled,
                    false,
                    bounds,
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    keyTipTraversal
                )
            }
            if (projection.presentationModel.popupKeyTip != null) {
                KeyTipTracker.trackKeyTipBase(
                    projection,
                    projection.presentationModel.popupKeyTip!!,
                    projection.contentModel.isSecondaryEnabled,
                    false,
                    bounds,
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    keyTipTraversal
                )
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun Modifier.ribbonTaskToggleButtonLocator(
    projection: BaseCommandButtonProjection<*, *, *>,
    topLeftOffset: AuroraOffset,
    size: MutableState<IntSize>,
    trackBounds: Boolean,
    trackKeyTips: Boolean,
    keyTipChainRoot: Any?,
    keyTipChainRootKeyTip: String?,
    keyTipTraversal: Any?
) = this.then(
    RibbonTaskToggleButtonLocator(
        projection,
        topLeftOffset,
        size,
        trackBounds,
        trackKeyTips,
        keyTipChainRoot,
        keyTipChainRootKeyTip,
        keyTipTraversal
    )
)
