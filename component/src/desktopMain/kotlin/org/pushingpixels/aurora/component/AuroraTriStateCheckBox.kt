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
package org.pushingpixels.aurora.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.SelectorPresentationModel
import org.pushingpixels.aurora.component.model.SelectorSizingConstants
import org.pushingpixels.aurora.component.model.TriStateSelectorContentModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import org.pushingpixels.aurora.theming.utils.getBaseOutline

@Immutable
private class TriStateCheckBoxDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    ),
    val markPath: Path = Path()
)

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraTriStateCheckBox(
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    contentModel: TriStateSelectorContentModel,
    presentationModel: SelectorPresentationModel
) {
    val drawingCache = remember { TriStateCheckBoxDrawingCache() }
    val rollover by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = contentModel.enabled,
                isRollover = rollover,
                isSelected = (contentModel.state == ToggleableState.On),
                isMixed = (contentModel.state == ToggleableState.Indeterminate),
                isPressed = isPressed
            )
        )
    }

    //println("State is ${contentModel.state} and ${currentState.value}")
    //val markAlpha = remember { mutableStateOf(if (contentModel.state == ToggleableState.Off) 0.0f else 1.0f) }
    //val indeterminateStrength = remember { mutableStateOf(if (contentModel.state == ToggleableState.Indeterminate) 1.0f else 0.0f) }

    // Transition for the selection state
    val selectionTransition = updateTransition(contentModel.state == ToggleableState.On)
    val selectedFraction by selectionTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the selection state
    val indeterminateTransition = updateTransition(contentModel.state == ToggleableState.Indeterminate)
    val indeterminateFraction by indeterminateTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the rollover state
    val rolloverTransition = updateTransition(rollover)
    val rolloverFraction by rolloverTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the pressed state
    val pressedTransition = updateTransition(isPressed)
    val pressedFraction by pressedTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the enabled state
    val enabledTransition = updateTransition(contentModel.enabled)
    val enabledFraction by enabledTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // TODO - figure out why the animations are not running without looking
    //  at the result (and how it looks like in the new animation APIs)
    @Suppress("UNUSED_VARIABLE")
    val totalFraction = selectedFraction + indeterminateFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = contentModel.enabled,
        selected = (contentModel.state == ToggleableState.On),
        mixed = (contentModel.state == ToggleableState.Indeterminate),
        rollover = rollover,
        pressed = isPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (transitionInfo.value != null) {
        //val tweakedDuration = AuroraSkin.animationConfig.regular
        LaunchedEffect(currentState.value) {
            // println("In launch effect!")
            val transitionFloat = Animatable(transitionInfo.value!!.from)
//            stateTransitionFloat.value = Animatable(transitionInfo.from)
//            println("******** Animating from ${transitionInfo.value!!.from} to 1.0f over ${transitionInfo.value!!.duration} ********")
//            println("******** Is running ${transitionFloat.isRunning} ********")
            val result = transitionFloat.animateTo(
                targetValue = transitionInfo.value!!.to,
                animationSpec = tween(durationMillis = transitionInfo.value!!.duration)
            ) {
               // println("During animation $value towards $targetValue")
                modelStateInfo.updateActiveStates(value)
            }

            //println("&&&&&&& Ended with reason ${result.endReason} at ${transitionFloat.value}")
            if (result.endReason == AnimationEndReason.Finished) {
                modelStateInfo.updateActiveStates(1.0f)
                modelStateInfo.clear(currentState.value)
                //println("******** After clear (target reached) ********")
                //modelStateInfo.dumpState(stateTransitionFloat.value)
            }
        }
    }

    // The tri-state toggleable modifier is set on the checkbox mark, as well as on the
    // content so that the whole thing is clickable to toggle the control.
    val decorationAreaType = AuroraSkin.decorationAreaType
    Row(
        modifier = modifier
            .padding(presentationModel.contentPadding)
            .auroraRichTooltip(
                richTooltip = contentModel.richTooltip,
                presentationModel = presentationModel.richTooltipPresentationModel
            )
            .triStateToggleable(
                state = contentModel.state,
                onClick = { contentModel.onClick.invoke() },
                enabled = contentModel.enabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = presentationModel.horizontalAlignment.arrangement
    ) {
        // Populate the cached color scheme for filling the markbox
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.MarkBox
        )

        // And retrieve the mark box colors
        val fillUltraLight = drawingCache.colorScheme.ultraLightColor
        val fillExtraLight = drawingCache.colorScheme.extraLightColor
        val fillLight = drawingCache.colorScheme.lightColor
        val fillMid = drawingCache.colorScheme.midColor
        val fillDark = drawingCache.colorScheme.darkColor
        val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
        val fillIsDark = drawingCache.colorScheme.isDark

        // Populate the cached color scheme for drawing the markbox border
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.Border
        )
        // And retrieve the mark box border colors
        val borderUltraLight = drawingCache.colorScheme.ultraLightColor
        val borderExtraLight = drawingCache.colorScheme.extraLightColor
        val borderLight = drawingCache.colorScheme.lightColor
        val borderMid = drawingCache.colorScheme.midColor
        val borderDark = drawingCache.colorScheme.darkColor
        val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
        val borderIsDark = drawingCache.colorScheme.isDark

        // Mark color
        val markColor = getStateAwareColor(
            modelStateInfo, currentState.value,
            decorationAreaType, ColorSchemeAssociationKind.Mark
        ) { it.markColor }

        // Checkmark alpha is the combined strength of all the
        // states that have the selection bit turned on or determinate bit turned off
        val markAlpha =
            modelStateInfo.stateContributionMap
                .filter {
                    it.key.isFacetActive(ComponentStateFacet.Selection) || it.key.isFacetActive(
                        ComponentStateFacet.Mix
                    )
                }
                .map { it.value }
                .sumOf { it.contribution.toDouble() }
                .toFloat()

        // Indeterminate strength ("flatness" of the checkmark) is the combined strength of all the
        // states that have the determinate bit turned off
        val selectedStrength = modelStateInfo.stateContributionMap
            .filter { it.key.isFacetActive(ComponentStateFacet.Selection) }
            .map { it.value }
            .sumOf { it.contribution.toDouble() }
            .toFloat()
        val mixStrength = modelStateInfo.stateContributionMap
            .filter { it.key.isFacetActive(ComponentStateFacet.Mix) }
            .map { it.value }
            .sumOf { it.contribution.toDouble() }
            .toFloat()

        val checkmarkFlatness = when {
            ((selectedStrength > 0.0f) && (mixStrength > 0.0f)) -> {
                // Going between On and Indeterminate states
                mixStrength
            }

            ((selectedStrength == 0.0f) && (mixStrength > 0.0f)) -> {
                // Going between Off and Indeterminate states
                1.0f
            }

            ((selectedStrength > 0.0f) && (mixStrength == 0.0f)) -> {
                // Going between On and Off states
                0.0f
            }

            else -> 0.0f
        }

//        println("State is ${contentModel.state}, markAlpha is $markAlpha, flatness is $checkmarkFlatness")
//        println("\tInternal state is $currentState")
//        println("\t\t Selection on? ${currentState.value.isFacetActive(ComponentStateFacet.Selection)}")
//        println("\t\t Mixed on? ${currentState.value.isFacetActive(ComponentStateFacet.Mix)}")
//        modelStateInfo.dumpState()
//        println("\tselected strength $selectedStrength, mixed strength $mixStrength")

        // Text color. Note that the text doesn't "participate" in state changes that
        // involve rollover, selection or pressed bits
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            skinColors = AuroraSkin.colors,
            decorationAreaType = decorationAreaType,
            colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
            isTextInFilledArea = false
        )
        val alpha = if (currentState.value.isDisabled)
            AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f

        val fillPainter = AuroraSkin.painters.fillPainter
        val borderPainter = AuroraSkin.painters.borderPainter

        Canvas(Modifier.wrapContentSize(Alignment.Center).size(presentationModel.markSize)) {
            val width = this.size.width
            val height = this.size.height

            val outline = getBaseOutline(
                layoutDirection = layoutDirection,
                width = this.size.width,
                height = this.size.height,
                radius = 3.0f.dp.toPx(),
                sides = null,
                insets = 0.5f
            )

            // Populate the cached color scheme for filling the markbox
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

            // Populate the cached color scheme for drawing the markbox border
            drawingCache.colorScheme.ultraLight = borderUltraLight
            drawingCache.colorScheme.extraLight = borderExtraLight
            drawingCache.colorScheme.light = borderLight
            drawingCache.colorScheme.mid = borderMid
            drawingCache.colorScheme.dark = borderDark
            drawingCache.colorScheme.ultraDark = borderUltraDark
            drawingCache.colorScheme.isDark = borderIsDark
            drawingCache.colorScheme.foreground = textColor

            val outlineInner = if (borderPainter.isPaintingInnerOutline) getBaseOutline(
                layoutDirection = layoutDirection,
                width = this.size.width,
                height = this.size.height,
                radius = 3.0f.dp.toPx() - 1,
                sides = null,
                insets = 2.0f
            ) else null

            borderPainter.paintBorder(
                this, this.size, outline, outlineInner, drawingCache.colorScheme, alpha
            )

            // Draw the checkbox mark with the alpha that corresponds to the current
            // selection and potential transition
            val markStroke = 0.12f * width

            with(drawingCache) {
                markPath.reset()
                markPath.moveTo(0.25f * width, 0.48f * height + 0.02f * height * checkmarkFlatness)
                markPath.lineTo(0.48f * width, 0.73f * height - 0.23f * height * checkmarkFlatness)
                markPath.lineTo(0.76f * width, 0.28f * height + 0.22f * height * checkmarkFlatness)

                // Note that we apply alpha twice - once for the selected / checked
                // state or transition, and the second time based on the enabled state
                drawPath(
                    path = markPath,
                    color = markColor.withAlpha(markAlpha),
                    style = Stroke(
                        width = markStroke,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    ),
                    alpha = alpha
                )
            }
        }
        Spacer(
            modifier = Modifier.width(
                SelectorSizingConstants.SelectorMarkTextGap *
                        presentationModel.horizontalGapScaleFactor
            )
        )
        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            Box(
                modifier = Modifier.requiredSizeIn(
                    minWidth = 0.dp,
                    minHeight = presentationModel.markSize
                )
            ) {
                AuroraText(text = contentModel.text)
            }
        }
    }
}
