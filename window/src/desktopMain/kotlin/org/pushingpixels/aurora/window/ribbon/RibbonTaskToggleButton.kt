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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.auroraRichTooltip
import org.pushingpixels.aurora.component.model.BaseCommand
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.impl.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.MutableContainerColorTokens
import org.pushingpixels.aurora.theming.utils.getContainerTokens
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Immutable
@OptIn(AuroraInternalApi::class)
private class RibbonTaskToggleButtonDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

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

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val componentShaper = AuroraSkin.componentShaper

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

    val textColor = getTextColor(
        modelStateInfo = actionModelStateInfo,
        currState = currentActionState.value,
        skinColors = skinColors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = decorationAreaType,
    )

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
                        colors = AuroraSkin.colors,
                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                        decorationAreaType = decorationAreaType,
                        modelStateInfo = actionModelStateInfo,
                        currState = currentActionState.value,
                        associationKind = ContainerColorTokensAssociationKind.Tab,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        treatEnabledAsActive = true,
                        skipFlatCheck = false,
                        inactiveContainerType = ContainerType.Muted)

                    val outlinePainter = AuroraSkin.painters.outlinePainter
                    val decorationPainter = AuroraSkin.painters.decorationPainter
                    val componentShaper = AuroraSkin.componentShaper

                    val actionAlpha = max(actionRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            // For flat buttons, compute the combined contribution of all
                            // non-disabled states - ignoring ComponentState.ENABLED
                            actionModelStateInfo.stateContributionMap
                                .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else 1.0f
                    )

                    val neutralSurfaceTokens = getContainerTokens(
                        colors = skinColors,
                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                        decorationAreaType = AuroraSkin.decorationAreaType,
                        componentState = ComponentState.Enabled,
                        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                        inactiveContainerType = ContainerType.Neutral
                    )
                    val outlineColorTokens = TabUtils.getTabOutlineColorTokens(
                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider)

                    val outlineSupplier = componentShaper.getTabOutlineSupplier(presentationModel.sides)

                    Canvas(modifier = Modifier.matchParentSize().graphicsLayer(alpha = actionAlpha)) {
                        val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
                        val sizeForSurface = size.copy(height = size.height + 1)
                        val outlineFill = outlineSupplier.getOutline(
                            layoutDirection = layoutDirection,
                            density = density,
                            size = sizeForSurface,
                            insets = outlineInset,
                            radiusAdjustment = 0.0f,
                            outlineKind = OutlineKind.Surface)

                        TabUtils.paintTabSurface(
                            drawScope = this,
                            skinColors = skinColors,
                            decorationAreaType = decorationAreaType,
                            decorationPainter = decorationPainter,
                            outlineFill = outlineFill,
                            density = density,
                            rootSize = rootSize,
                            offsetFromRoot = buttonTopLeftOffset.asOffset(density = density),
                            size = sizeForSurface,
                            surfaceColorTokens = neutralSurfaceTokens,
                            alpha = 1.0f
                        )

                        TabUtils.paintTabSurfaceHighlight(
                            drawScope = this,
                            outlineSupplier = outlineSupplier,
                            density = density,
                            size = size,
                            surfaceHighlightColorTokens = drawingCache.colorTokens,
                            alpha = if (currentActionState.value.isDisabled) {
                                outlineColorTokens.containerSurfaceDisabledAlpha
                            } else {
                                outlineColorTokens.containerSurfaceEnabledAlpha
                            }
                        )

                        TabUtils.paintTabOutline(
                            drawScope = this,
                            outlineSupplier = outlineSupplier,
                            density = density,
                            size = size,
                            outlineColorTokens = outlineColorTokens,
                            alpha = if (currentActionState.value.isDisabled) {
                                outlineColorTokens.containerOutlineDisabledAlpha
                            } else {
                                outlineColorTokens.containerOutlineEnabledAlpha
                            }
                        )
                    }
                }
            }

            for (text in preLayoutInfo.texts) {
                TaskToggleButtonTextContent(text, presentationModel, textColor, resolvedTextStyle)
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
            componentShaper = componentShaper
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
    textColor: Color,
    style: TextStyle
) {
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
    skinColors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    decorationAreaType: DecorationAreaType,
): Color {
    val activeStates: Map<ComponentState, StateContributionInfo> = modelStateInfo.stateContributionMap

    val parentSurfaceTokens = getContainerTokens(
        colors = skinColors,
        tokensOverlayProvider = tokensOverlayProvider,
        decorationAreaType = DecorationAreaType.Header,
        componentState = ComponentState.Enabled,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Neutral
    )

    var activeStateTotalContribution = if (currState.isActive) 1.0f else 0.0f
    if (activeStates.size > 1) {
        for ((activeState, value) in activeStates) {
            if (activeState != currState) {
                if (activeState != ComponentState.Enabled) {
                    activeStateTotalContribution += value.contribution
                }
            }
        }
    }
    activeStateTotalContribution = min(1.0f, activeStateTotalContribution)

    if (activeStateTotalContribution == 0.0f) {
        return parentSurfaceTokens.onContainer
    }

    val surfaceTokens = getContainerTokens(
        colors = skinColors,
        tokensOverlayProvider = tokensOverlayProvider,
        decorationAreaType = decorationAreaType,
        componentState = ComponentState.Enabled,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Neutral
    )

    return parentSurfaceTokens.onContainer.interpolateTowards(
        surfaceTokens.onContainer, 1.0f - activeStateTotalContribution)
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
