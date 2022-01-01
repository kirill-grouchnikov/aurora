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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.drawscope.withTransform
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.MutableColorScheme

@Immutable
private class BoxWithHighlightsDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

/**
 * A composable that wraps its content with top-level highlights. Notes:
 *
 * <ul>
 *     <li>[LabelProjection] content should have [LabelPresentationModel.inheritStateFromParent]
 *     set to true.</li>
 *     <li>By design, this box does not support [ComponentStateFacet.PRESSED] transitions.</li>
 * </ul>
 */
@OptIn(ExperimentalComposeUiApi::class, AuroraInternalApi::class)
@Composable
fun AuroraBoxWithHighlights(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    sides: Sides = Sides(),
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { BoxWithHighlightsDrawingCache() }
    val rollover by interactionSource.collectIsHoveredAsState()

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = enabled,
                isRollover = rollover,
                isSelected = selected,
                isPressed = false
            )
        )
    }

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = AuroraSkin.buttonShaper

    // Transition for the selection state
    val selectionTransition = updateTransition(selected)
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
    val pressedTransition = updateTransition(false)
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
    val enabledTransition = updateTransition(enabled)
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
    val totalFraction = selectedFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = enabled,
        selected = selected,
        rollover = rollover,
        pressed = false,
        duration = AuroraSkin.animationConfig.regular
    )

    if (transitionInfo.value != null) {
        //val tweakedDuration = AuroraSkin.animationConfig.regular
        LaunchedEffect(currentState.value) {
            //println("In launch effect!")
            val transitionFloat = Animatable(transitionInfo.value!!.from)
//            stateTransitionFloat.value = Animatable(transitionInfo.from)
//            println("******** Animating from ${transitionInfo.value!!.from} to 1.0f over ${transitionInfo.value!!.duration} ********")
//            println("******** Is running ${transitionFloat.isRunning} ********")
            val result = transitionFloat.animateTo(
                targetValue = transitionInfo.value!!.to,
                animationSpec = tween(durationMillis = transitionInfo.value!!.duration)
            ) {
//                println("During animation $value towards $targetValue")
                modelStateInfo.updateActiveStates(value)
            }

//            println("&&&&&&& Ended with reason ${result.endReason} at ${transitionFloat.value}")
            if (result.endReason == AnimationEndReason.Finished) {
                modelStateInfo.updateActiveStates(1.0f)
                modelStateInfo.clear(currentState.value)
                //println("******** After clear (target reached) ********")
                //modelStateInfo.dumpState(stateTransitionFloat.value)
            }
        }
    }

    var boxModifier = modifier
    if (enabled && (onClick != null)) {
        boxModifier = boxModifier.clickable(
            onClick = onClick,
            interactionSource = interactionSource,
            indication = null
        )
    }
    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.CenterStart
    ) {
        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            colorSchemeAssociationKind = ColorSchemeAssociationKind.Highlight,
            isTextInFilledArea = true
        )

        // Populate the cached color scheme for filling the combobox
        // based on the current model state info
        populateColorSchemeWithHighlightAlpha(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.Highlight
        )
        // And retrieve the container fill colors
        val fillUltraLight = drawingCache.colorScheme.ultraLightColor
        val fillExtraLight = drawingCache.colorScheme.extraLightColor
        val fillLight = drawingCache.colorScheme.lightColor
        val fillMid = drawingCache.colorScheme.midColor
        val fillDark = drawingCache.colorScheme.darkColor
        val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
        val fillIsDark = drawingCache.colorScheme.isDark

        // Populate the cached color scheme for drawing the border
        // based on the current model state info
        populateColorSchemeWithHighlightAlpha(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.HighlightBorder
        )
        // And retrieve the border colors
        val borderUltraLight = drawingCache.colorScheme.ultraLightColor
        val borderExtraLight = drawingCache.colorScheme.extraLightColor
        val borderLight = drawingCache.colorScheme.lightColor
        val borderMid = drawingCache.colorScheme.midColor
        val borderDark = drawingCache.colorScheme.darkColor
        val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
        val borderIsDark = drawingCache.colorScheme.isDark

        val fillPainter = AuroraSkin.painters.fillPainter
        val borderPainter = AuroraSkin.painters.borderPainter

        val alpha = 1.0f

        Canvas(Modifier.matchParentSize()) {
            val width = this.size.width
            val height = this.size.height

            withTransform({
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = width,
                    bottom = height,
                    clipOp = ClipOp.Intersect
                )
            }) {
                val outline = buttonShaper.getButtonOutline(
                    width = width,
                    height = height,
                    extraInsets = 0.5f,
                    isInner = false,
                    sides = sides,
                    drawScope = this
                )

                val outlineBoundingRect = outline.bounds
                if (outlineBoundingRect.isEmpty) {
                    return@withTransform
                }

                // Populate the cached color scheme for filling the combobox
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

                // Populate the cached color scheme for drawing the border
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
                        width = width,
                        height = height,
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

        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            content()
        }
    }
}
