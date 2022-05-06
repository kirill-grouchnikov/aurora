/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.ImageFilter
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
private class TabButtonDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    ),
    val markPath: Path = Path()
)

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
    val skinColors = AuroraSkin.colors
    val buttonShaper = AuroraSkin.buttonShaper
    val painters = AuroraSkin.painters
    val tabDefinition = AuroraSkin.tabDefinition

    val buttonTopLeftOffset = remember { AuroraOffset(0.0f, 0.0f) }
    val buttonSize = remember { mutableStateOf(IntSize(0, 0)) }
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

    val hasAction = (command.action != null)
    val isActionEnabled = command.isActionEnabled

    // TODO - do we need more keys? Maybe from the presentation model
    val preLayoutInfo = remember(
        command.text, command.extraText,
        command.action == null, command.secondaryContentModel == null
    ) {
        layoutManager.getPreLayoutInfo(command, presentationModel)
    }

    val hasIcon = preLayoutInfo.showIcon

    @Language("GLSL")
    val alphaSksl = """
        uniform shader content;
        uniform float height;
        uniform float transitionStartFraction;
        uniform float transitionEndFraction;

        vec4 main(vec2 coord) {
            float fraction = coord.y / height;
            float alpha = 1.0;
            if (fraction > transitionEndFraction) {
                alpha = 0.0;
            } else if (fraction > transitionStartFraction) {
                alpha = 1.0 - (fraction - transitionStartFraction) /
                    (transitionEndFraction - transitionStartFraction);
            }
            vec4 c = content.eval(coord);
            return vec4(c.r * alpha, c.g * alpha, c.b * alpha, c.a * alpha);
        }
    """

    val alphaRuntimeEffect = RuntimeEffect.makeForShader(alphaSksl)
    val alphaShaderBuilder = RuntimeShaderBuilder(alphaRuntimeEffect)
    alphaShaderBuilder.uniform("height", buttonSize.value.height.toFloat())
    alphaShaderBuilder.uniform("transitionStartFraction", tabDefinition.tabStartFade)
    alphaShaderBuilder.uniform("transitionEndFraction", tabDefinition.tabEndFade)

    Layout(
        modifier = modifier.tabButtonLocator(buttonTopLeftOffset, buttonSize),
        content = {
            val modifierAction: Modifier = Modifier.toggleable(
                value = command.isActionToggleSelected,
                enabled = isActionEnabled,
                role = Role.Button,
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
                    // Populate the cached color scheme for filling the action area
                    // based on the current model state info
                    populateColorScheme(
                        drawingCache.colorScheme,
                        actionModelStateInfo,
                        currentActionState.value,
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
                        actionModelStateInfo,
                        currentActionState.value,
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

                    Canvas(
                        modifier = Modifier.matchParentSize().graphicsLayer(
                            renderEffect = ImageFilter.makeRuntimeShader(
                                runtimeShaderBuilder = alphaShaderBuilder,
                                shaderName = "content",
                                input = null
                            ).asComposeRenderEffect(),
                            clip = true
                        )
                    ) {
                        val width = buttonSize.value.width.toFloat()
                        val height = buttonSize.value.height.toFloat()

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
                            val fillOutline = buttonShaper.getButtonOutline(
                                width = width,
                                height = height,
                                extraInsets = 0.5f,
                                isInner = false,
                                sides = presentationModel.sides,
                                outlineKind = OutlineKind.Fill,
                                density = this
                            )

                            val outlineBoundingRect = fillOutline.bounds
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
                                Size(buttonSize.value.width.toFloat(), buttonSize.value.height.toFloat()),
                                fillOutline,
                                drawingCache.colorScheme,
                                actionAlpha
                            )
                        }
                    }

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val width = buttonSize.value.width.toFloat()
                        val height = buttonSize.value.height.toFloat()

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
                        }) {                            // Populate the cached color scheme for drawing the button border
                            drawingCache.colorScheme.ultraLight = borderUltraLight
                            drawingCache.colorScheme.extraLight = borderExtraLight
                            drawingCache.colorScheme.light = borderLight
                            drawingCache.colorScheme.mid = borderMid
                            drawingCache.colorScheme.dark = borderDark
                            drawingCache.colorScheme.ultraDark = borderUltraDark
                            drawingCache.colorScheme.isDark = borderIsDark
                            drawingCache.colorScheme.foreground = Color.Black

                            val borderOutline = buttonShaper.getButtonOutline(
                                width = width,
                                height = height,
                                extraInsets = 0.5f,
                                isInner = false,
                                sides = presentationModel.sides,
                                outlineKind = OutlineKind.Border,
                                density = this
                            )
                            val innerBorderOutline =
                                if (borderPainter.isPaintingInnerOutline) buttonShaper.getButtonOutline(
                                    width = width,
                                    height = height,
                                    extraInsets = 1.0f,
                                    isInner = true,
                                    sides = presentationModel.sides,
                                    outlineKind = OutlineKind.Border,
                                    density = this
                                ) else null

                            borderPainter.paintBorder(
                                this,
                                Size(buttonSize.value.width.toFloat(), buttonSize.value.height.toFloat()),
                                borderOutline,
                                innerBorderOutline,
                                drawingCache.colorScheme,
                                actionAlpha
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
                    currentActionState.value,
                    drawingCache
                )
            }

            // Special handling of tabs under skins that show partial visuals
            val isTextOnParentBackground = (tabDefinition.tabEndFade <= 0.5f)
            var currentStateForText = currentActionState.value
            if (isTextOnParentBackground) {
                // Ignore all other "aspects" of tab's state
                currentStateForText =
                    if (command.isActionEnabled) ComponentState.Enabled else ComponentState.DisabledUnselected
            }
            val modelStateInfoForText = if (isTextOnParentBackground) null else actionModelStateInfo
            for (text in preLayoutInfo.texts) {
                TabButtonTextContent(
                    text, modelStateInfoForText, currentStateForText, resolvedTextStyle,
                    presentationModel.textOverflow
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

        // Measure the action and popup boxes
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
    text: String, modelStateInfo: ModelStateInfo?, currState: ComponentState,
    style: TextStyle, overflow: TextOverflow
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

    // Pass our text color to the children
    CompositionLocalProvider(
        LocalTextColor provides textColor
    ) {
        // Since we're passing the resolved style that has the default color,
        // also explicitly pass our text color to override the one set in the style
        AuroraText(text = text, color = textColor, style = style, maxLines = 1, overflow = overflow)
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun TabButtonIconContent(
    command: Command, presentationModel: CommandButtonPresentationModel,
    iconSize: Dp, modelStateInfo: ModelStateInfo, currState: ComponentState,
    drawingCache: TabButtonDrawingCache
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
                val stateForBackground =
                    if (currState.isDisabled) ComponentState.DisabledSelected
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
        if (command.icon == null) {
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
            val icon = if (command.icon is TransitionAwarePainterDelegate)
                command.icon.createNewIcon(modelStateInfo.getSnapshot(currState))
            else
                command.icon

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
                    size = iconSize,
                    disabledFilterStrategy = presentationModel.iconDisabledFilterStrategy,
                    enabledFilterStrategy = presentationModel.iconEnabledFilterStrategy,
                    activeFilterStrategy = presentationModel.iconActiveFilterStrategy
                )
            }
        }
    }
}

private class TabButtonBoxLocator(val topLeftOffset: AuroraOffset, val size: MutableState<IntSize>) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.value = coordinates.size
    }
}

@Composable
private fun Modifier.tabButtonLocator(topLeftOffset: AuroraOffset, size: MutableState<IntSize>) =
    this.then(TabButtonBoxLocator(topLeftOffset, size))
