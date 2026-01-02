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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.*

@Immutable
@OptIn(AuroraInternalApi::class)
private class BoxWithHighlightsDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

private object BoxWithHighlightsOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = 0.0f,
            sides = Sides(),
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

/**
 * A composable that wraps its content with top-level highlights. Notes:
 *
 * <ul>
 *     <li>[LabelProjection] content should have [LabelPresentationModel.inheritStateFromParent]
 *     set to true.</li>
 *     <li>By design, this box does not support [ComponentStateFacet.Press] transitions.</li>
 * </ul>
 */
@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraBoxWithHighlights(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    sides: Sides = Sides(),
    content: @Composable (colorTokens: ContainerColorTokens) -> Unit
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

    val density = LocalDensity.current

    val decorationAreaType = AuroraSkin.decorationAreaType

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
    @Suppress("UNUSED_VARIABLE")
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
            colors = AuroraSkin.colors,
            tokensOverlayProvider = null,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Highlight,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted,
            isTextInFilledArea = true
        )

        // Populate the cached color tokens for filling the highlight box
        // based on the current model state info
        populateColorTokensForHighlights(
            colorTokens = drawingCache.colorTokens,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = null,
            decorationAreaType = decorationAreaType,
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            associationKind = ContainerColorTokensAssociationKind.Highlight,
            inactiveContainerType = ContainerType.Neutral)

        val highlightSurfacePainter = AuroraSkin.painters.highlightSurfacePainter
        val highlightOutlinePainter = AuroraSkin.painters.highlightOutlinePainter

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
                val outlineInset = highlightOutlinePainter.getOutlineInset(InsetKind.Surface)
                val outlineFill = BoxWithHighlightsOutlineSuppler.getOutline(
                    layoutDirection = layoutDirection,
                    density = density,
                    size = this.size,
                    insets = outlineInset,
                    radiusAdjustment = 0.0f,
                    outlineKind = OutlineKind.Surface)
                val outlineBoundingRect = outlineFill.bounds
                if (outlineBoundingRect.isEmpty) {
                    return@withTransform
                }

                paintSurface(
                    drawScope = this,
                    componentState = currentState.value,
                    surfacePainter = highlightSurfacePainter,
                    surfacePainterOverlay = null,
                    size = this.size,
                    alpha = alpha,
                    outline = outlineFill,
                    colorTokens = drawingCache.colorTokens)

                paintOutline(
                    drawScope = this,
                    componentState = currentState.value,
                    outlinePainter = highlightOutlinePainter,
                    outlinePainterOverlay = null,
                    size = this.size,
                    alpha = alpha,
                    outlineSupplier = BoxWithHighlightsOutlineSuppler,
                    colorTokens = drawingCache.colorTokens)
            }
        }

        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            content(drawingCache.colorTokens)
        }
    }
}
