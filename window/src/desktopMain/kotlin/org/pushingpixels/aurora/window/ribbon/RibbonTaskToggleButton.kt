/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawOutline
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
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.auroraRichTooltip
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
private class RibbonTaskToggleButtonDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonTaskToggleButton(
    modifier: Modifier,
    command: Command,
    presentationModel: CommandButtonPresentationModel
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
    val painters = AuroraSkin.painters

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
    var buttonTopLeftOffset by remember { mutableStateOf(Offset(0.0f, 0.0f)) }
    Layout(
        modifier = modifier.onGloballyPositioned {
            buttonTopLeftOffset = it.localToRoot(Offset.Zero)
        },
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
            Box(
                modifier = modifierAction.auroraRichTooltip(
                    richTooltip = command.actionRichTooltip,
                    presentationModel = presentationModel.actionRichTooltipPresentationModel
                )
            ) {
                if (presentationModel.backgroundAppearanceStrategy != BackgroundAppearanceStrategy.Never) {
                    // Populate the cached color scheme for filling the action area
                    // based on the current model state info
                    populateColorScheme(
                        colorScheme = drawingCache.colorScheme,
                        modelStateInfo = actionModelNoSelectionStateInfo,
                        currState = currentActionNoSelectionState.value,
                        colorSchemeDelegate = object : ColorSchemeDelegate {
                            override fun getColorSchemeForCurrentState(state: ComponentState): AuroraColorScheme {
                                if (state == ComponentState.Enabled) {
                                    return skinColors.getBackgroundColorScheme(decorationAreaType = decorationAreaType)
                                }
                                return presentationModel.colorSchemeBundle?.getColorScheme(
                                    associationKind = ColorSchemeAssociationKind.Fill,
                                    componentState = state,
                                    allowFallback = true
                                ) ?: skinColors.getColorScheme(
                                    decorationAreaType = decorationAreaType,
                                    associationKind = ColorSchemeAssociationKind.Fill,
                                    componentState = state
                                )
                            }

                            override fun getColorSchemeForActiveState(state: ComponentState): AuroraColorScheme {
                                if (state == ComponentState.Enabled) {
                                    return skinColors.getBackgroundColorScheme(decorationAreaType = decorationAreaType)
                                }
                                return presentationModel.colorSchemeBundle?.getColorScheme(
                                    associationKind = ColorSchemeAssociationKind.Fill,
                                    componentState = state,
                                    allowFallback = true
                                ) ?: skinColors.getColorScheme(
                                    decorationAreaType = decorationAreaType,
                                    associationKind = ColorSchemeAssociationKind.Fill,
                                    componentState = state
                                )
                            }
                        },
                    )
                    // And retrieve the container fill colors
                    val fillUltraLight = drawingCache.colorScheme.ultraLightColor
                    val fillExtraLight = drawingCache.colorScheme.extraLightColor
                    val fillLight = drawingCache.colorScheme.lightColor
                    val fillMid = drawingCache.colorScheme.midColor
                    val fillDark = drawingCache.colorScheme.darkColor
                    val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
                    val fillBackgroundFill = drawingCache.colorScheme.backgroundFillColor
                    val fillIsDark = drawingCache.colorScheme.isDark

                    // Populate the cached color scheme for drawing the button border
                    // based on the current model state info
                    populateColorScheme(
                        colorScheme = drawingCache.colorScheme,
                        modelStateInfo = actionModelStateInfo,
                        currState = currentActionState.value,
                        colorSchemeDelegate = object : ColorSchemeDelegate {
                            override fun getColorSchemeForCurrentState(state: ComponentState): AuroraColorScheme {
                                return skinColors.getColorScheme(
                                    decorationAreaType = decorationAreaType,
                                    associationKind = ColorSchemeAssociationKind.Border,
                                    componentState = state
                                )
                            }

                            override fun getColorSchemeForActiveState(state: ComponentState): AuroraColorScheme {
                                return skinColors.getColorScheme(
                                    decorationAreaType = decorationAreaType,
                                    associationKind = ColorSchemeAssociationKind.Border,
                                    componentState = state
                                )
                            }
                        }
                    )
                    // And retrieve the border colors
                    val borderUltraLight = drawingCache.colorScheme.ultraLightColor
                    val borderExtraLight = drawingCache.colorScheme.extraLightColor
                    val borderLight = drawingCache.colorScheme.lightColor
                    val borderMid = drawingCache.colorScheme.midColor
                    val borderDark = drawingCache.colorScheme.darkColor
                    val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
                    val borderIsDark = drawingCache.colorScheme.isDark

                    val decorationPainter = painters.decorationPainter
                    val borderPainter = painters.borderPainter

                    val actionAlpha = max(actionRolloverFraction,
                        if (presentationModel.backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat) {
                            if (currentActionState.value == ComponentState.DisabledSelected) {
                                // Respect the alpha in disabled+selected state
                                skinColors.getAlpha(
                                    decorationAreaType,
                                    currentActionState.value
                                )
                            } else {
                                // For flat buttons, compute the combined contribution of all
                                // non-disabled states - ignoring ComponentState.ENABLED
                                actionModelStateInfo.stateContributionMap
                                    .filter { !it.key.isDisabled && (it.key != ComponentState.Enabled) }
                                    .values.sumOf { it.contribution.toDouble() }.toFloat()
                            }
                        } else {
                            if (currentActionState.value.isDisabled)
                                skinColors.getAlpha(
                                    decorationAreaType,
                                    currentActionState.value
                                ) else 1.0f
                        }
                    )

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = size.width
                        val height = size.height

                        val fillOutline = buttonShaper.getButtonOutline(
                            layoutDirection = layoutDirection,
                            width = width,
                            height = height + 1,
                            extraInsets = 0.5f,
                            isInner = false,
                            sides = presentationModel.sides,
                            outlineKind = OutlineKind.Fill,
                            density = this
                        )

                        // Populate the cached color scheme for filling the task toggle button
                        drawingCache.colorScheme.ultraLight = fillUltraLight.withAlpha(actionAlpha)
                        drawingCache.colorScheme.extraLight = fillExtraLight.withAlpha(actionAlpha)
                        drawingCache.colorScheme.light = fillLight.withAlpha(actionAlpha)
                        drawingCache.colorScheme.mid = fillMid.withAlpha(actionAlpha)
                        drawingCache.colorScheme.dark = fillDark.withAlpha(actionAlpha)
                        drawingCache.colorScheme.ultraDark = fillUltraDark.withAlpha(actionAlpha)
                        drawingCache.colorScheme.isDark = fillIsDark
                        drawingCache.colorScheme.foreground = Color.Black

                        if (actionAlpha > 0.0f) {
                            if (skinColors.isRegisteredAsDecorationArea(decorationAreaType)) {
                                // If the current skin has a decoration painter that provides custom visuals
                                // for this decoration area, use it
                                decorationPainter.paintDecorationArea(
                                    drawScope = this,
                                    decorationAreaType = decorationAreaType,
                                    componentSize = size,
                                    outline = fillOutline,
                                    rootSize = rootSize,
                                    offsetFromRoot = buttonTopLeftOffset,
                                    colorScheme = drawingCache.colorScheme
                                )
                            } else {
                                // Otherwise use flat color fill
                                drawOutline(
                                    color = fillBackgroundFill,
                                    outline = fillOutline,
                                    alpha = actionAlpha
                                )
                            }
                        }

                        // Populate the cached color scheme for drawing the button border
                        drawingCache.colorScheme.ultraLight = borderUltraLight
                        drawingCache.colorScheme.extraLight = borderExtraLight
                        drawingCache.colorScheme.light = borderLight
                        drawingCache.colorScheme.mid = borderMid
                        drawingCache.colorScheme.dark = borderDark
                        drawingCache.colorScheme.ultraDark = borderUltraDark
                        drawingCache.colorScheme.isDark = borderIsDark
                        drawingCache.colorScheme.foreground = Color.Black

                        val borderOutline = buttonShaper.getButtonOutline(
                            layoutDirection = layoutDirection,
                            width = width,
                            height = height + 1,
                            extraInsets = 0.5f,
                            isInner = false,
                            sides = presentationModel.sides,
                            outlineKind = OutlineKind.Border,
                            density = this
                        )
                        val innerBorderOutline =
                            if (borderPainter.isPaintingInnerOutline) buttonShaper.getButtonOutline(
                                layoutDirection = layoutDirection,
                                width = width,
                                height = height + 1,
                                extraInsets = 1.0f,
                                isInner = true,
                                sides = presentationModel.sides,
                                outlineKind = OutlineKind.Border,
                                density = this
                            ) else null

                        borderPainter.paintBorder(
                            this,
                            Size(width, height + 1),
                            borderOutline,
                            innerBorderOutline,
                            drawingCache.colorScheme,
                            actionAlpha
                        )
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
            preLayoutInfo = preLayoutInfo
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
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun TaskToggleButtonTextContent(
    text: String, presentationModel: CommandButtonPresentationModel,
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
        colorSchemeBundle = presentationModel.colorSchemeBundle,
        decorationAreaType = decorationAreaType,
        colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill
    )
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
private fun getTextColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    currStateIgnoreSelection: ComponentState,
    skinColors: AuroraSkinColors,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType,
    colorSchemeAssociationKind: ColorSchemeAssociationKind
): Color {
    var activeStates: Map<ComponentState, StateContributionInfo> = modelStateInfo.stateContributionMap

    val buttonFillScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Fill,
        componentState = currStateIgnoreSelection
    )
    val parentDecorationAreaType = DecorationAreaType.Header
    val parentFillScheme = skinColors.getBackgroundColorScheme(decorationAreaType = parentDecorationAreaType)

    if (currState.isDisabled || (activeStates.size == 1)) {
        // In enabled state the task toggle button does not show any background. Take the foreground
        // color from the fill scheme of the parent
        val schemeForCurrState = if (currState == ComponentState.Enabled) parentFillScheme else buttonFillScheme
        return schemeForCurrState.foregroundColor
    }

    // Get the combined foreground color from all states
    var aggrRed = 0f
    var aggrGreen = 0f
    var aggrBlue = 0f
    for ((activeState, value) in activeStates) {
        val contribution = value.contribution
        val correspondsToParentFill = (activeState == ComponentState.Enabled) &&
                !currState.isFacetActive(ComponentStateFacet.Selection)

        val activeColorScheme = colorSchemeBundle?.getColorScheme(
            associationKind = colorSchemeAssociationKind,
            componentState = activeState,
            allowFallback = true
        ) ?: skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = colorSchemeAssociationKind,
            componentState = activeState
        )
        val activeForeground = if (correspondsToParentFill) parentFillScheme.foregroundColor else
            activeColorScheme.foregroundColor
        aggrRed += contribution * activeForeground.red
        aggrGreen += contribution * activeForeground.green
        aggrBlue += contribution * activeForeground.blue
    }
    var foreground = Color(red = aggrRed, blue = aggrBlue, green = aggrGreen, alpha = 1.0f)

    val baseAlpha = colorSchemeBundle?.getAlpha(currState) ?: skinColors.getAlpha(
        decorationAreaType = decorationAreaType,
        componentState = currState
    )

    if (baseAlpha < 1.0f) {
        // Blend with the background fill
        val backgroundColorScheme =
            colorSchemeBundle?.getColorScheme(ComponentState.Enabled) ?: skinColors.getColorScheme(
                decorationAreaType = decorationAreaType,
                componentState = ComponentState.Enabled
            )
        val bgFillColor = backgroundColorScheme.backgroundFillColor
        foreground = foreground.interpolateTowards(bgFillColor, baseAlpha)
    }
    return foreground
}
