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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.*
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
@OptIn(AuroraInternalApi::class)
private class TabButtonDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

private class TabButtonOutlineSuppler(val presentationModel: CommandButtonPresentationModel): OutlineSupplier {
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
internal fun AuroraTabButton(
    modifier: Modifier,
    command: Command,
    presentationModel: CommandButtonPresentationModel
) {
    val actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { TabButtonDrawingCache() }

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

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val mergedTextStyle = LocalTextStyle.current.merge(presentationModel.textStyle)
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val buttonShaper = LocalButtonShaper.current

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

    val hasIcon = preLayoutInfo.showIcon

    Layout(
        modifier = modifier,
        content = {
            val modifierAction: Modifier = Modifier.toggleable(
                value = command.isActionToggleSelected,
                enabled = isActionEnabled,
                role = Role.Tab,
                interactionSource = actionInteractionSource,
                indication = null,
                onValueChange = {
                    command.onTriggerActionToggleSelectedChange?.invoke(it)
                }
            )
            var actionAreaOffset = remember { Offset.Zero }
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

                    val underlineColorTokens = getContainerTokens(
                        colors = AuroraSkin.colors,
                        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                        decorationAreaType = AuroraSkin.decorationAreaType,
                        associationKind = ContainerColorTokensAssociationKind.Tab,
                        componentState = ComponentState.Selected,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
                        inactiveContainerType = ContainerType.Neutral
                    )
                    val underlineColor = if (underlineColorTokens.isDark) {
                        underlineColorTokens.complementaryContainerOutline
                    } else {
                        underlineColorTokens.containerOutline
                    }

                    val outlinePainter = AuroraSkin.painters.outlinePainter

                    val actionAlpha = max(actionRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                                // For flat buttons, compute the combined contribution of all
                                // non-disabled states - ignoring ComponentState.ENABLED
                                actionModelStateInfo.stateContributionMap
                                    .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                    .values.sumOf { it.contribution.toDouble() }.toFloat()
                        } else 1.0f
                    )

                    val outlineSupplier = TabButtonOutlineSuppler(presentationModel)

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = size.width
                        val height = size.height

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = width,
                                bottom = height,
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
                                size = this.size,
                                insets = outlineInset,
                                radiusAdjustment = 0.0f,
                                outlineKind = OutlineKind.Surface)

                            withTransform({
                                clipRect(
                                    left = 0.0f,
                                    top = 0.0f,
                                    right = width,
                                    bottom = height * 0.18f,
                                    clipOp = ClipOp.Intersect
                                )
                            }) {
                                drawOutline(
                                    outline = outlineFill,
                                    style = Fill,
                                    color = drawingCache.colorTokens.containerSurface,
                                    alpha = actionAlpha
                                )
                            }
                        }
                    }

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = size.width
                        val height = size.height

                        withTransform({
                            clipRect(
                                left = 0.0f,
                                top = 0.0f,
                                right = width,
                                bottom = height,
                                clipOp = ClipOp.Intersect
                            )
                            translate(
                                left = -actionAreaOffset.x,
                                top = -actionAreaOffset.y
                            )
                        }) {
                            drawOutline(
                                outline = outlineSupplier.getOutline(
                                    layoutDirection = this.layoutDirection,
                                    density = this,
                                    size = size,
                                    insets = 0.5f,
                                    radiusAdjustment = 0.0f,
                                    outlineKind = OutlineKind.Outline
                                ),
                                style = Stroke(width = 1.0f),
                                color = underlineColor,
                                alpha = if (currentActionState.value.isDisabled) {
                                    actionAlpha * underlineColorTokens.onContainerDisabledAlpha
                                } else actionAlpha
                            )
                        }
                    }
                }
            }

            if (hasIcon) {
                TabButtonIconContent(
                    command,
                    presentationModel,
                    layoutManager.getPreferredIconSize(command, presentationModel),
                    actionModelStateInfo,
                    currentActionState.value
                )
            }

            val currentStateForText = if (command.isActionEnabled) ComponentState.Enabled
            else ComponentState.DisabledUnselected
            for (text in preLayoutInfo.texts) {
                TabButtonTextContent(
                    text, presentationModel, null, currentStateForText, resolvedTextStyle
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

        layout(
            width = layoutInfo.fullSize.width.toInt(),
            height = layoutInfo.fullSize.height.toInt()
        ) {
            actionPlaceable.place(
                x = layoutInfo.actionClickArea.left.roundToInt(),
                y = layoutInfo.actionClickArea.top.roundToInt()
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
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun TabButtonTextContent(
    text: String, presentationModel: CommandButtonPresentationModel,
    modelStateInfo: ModelStateInfo?, currState: ComponentState,
    style: TextStyle
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors

    // Compute the text color based on the passed model state (which can be action
    // or popup)
    val textColor = getTextColor(
        modelStateInfo = modelStateInfo,
        currState = currState,
        colors = skinColors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Tab,
        backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
        skipFlatCheck = false,
        inactiveContainerType = ContainerType.Muted,
        isTextInFilledArea = true)

    // Pass our text color to the children
    CompositionLocalProvider(
        LocalTextColor provides textColor
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        AuroraText(
            text = text,
            color = textColor,
            style = style,
            maxLines = 1,
            overflow = presentationModel.textOverflow
        )
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun TabButtonIconContent(
    command: Command, presentationModel: CommandButtonPresentationModel,
    iconSize: DpSize, modelStateInfo: ModelStateInfo, currState: ComponentState
) {
    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType

    Box {
        if (command.icon != null) {
            val icon = if (command.icon is TransitionAwarePainterDelegate)
                command.icon.createNewIcon(modelStateInfo.getSnapshot(currState))
            else
                command.icon

            // Compute the text color based on the passed model state (which can be action
            // or popup)
            val textColor = getTextColor(
                modelStateInfo = modelStateInfo,
                currState = currState,
                colors = skinColors,
                tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Tab,
                backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
                skipFlatCheck = false,
                inactiveContainerType = ContainerType.Muted,
                isTextInFilledArea = true)

            // Pass our text color and model state snapshot to the children
            CompositionLocalProvider(
                LocalTextColor provides textColor,
                LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currState),
                LocalColorTokensOverlayProvider provides presentationModel.colorTokensOverlayProvider
            ) {
                AuroraThemedIcon(
                    icon = icon,
                    size = iconSize,
                    disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                    enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                    activeFilterStrategy = presentationModel.iconActiveFilterStrategy
                )
            }
        }
    }
}
