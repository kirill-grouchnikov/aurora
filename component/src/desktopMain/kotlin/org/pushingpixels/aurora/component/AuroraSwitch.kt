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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.SwitchContentModel
import org.pushingpixels.aurora.component.model.SwitchPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.*

@Immutable
@OptIn(AuroraInternalApi::class)
private class SwitchDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

private object SwitchOutlineSuppler: OutlineSupplier {
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
            radius = size.height / 2.0f - radiusAdjustment,
            sides = Sides(),
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

@Composable
internal fun switchIntrinsicSize(
    contentModel: SwitchContentModel,
    presentationModel: SwitchPresentationModel
): Size {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val width = presentationModel.contentPadding.calculateStartPadding(layoutDirection) +
            presentationModel.trackSize.width +
            presentationModel.contentPadding.calculateEndPadding(layoutDirection)

    val height = presentationModel.contentPadding.calculateTopPadding() +
            presentationModel.trackSize.height +
            presentationModel.contentPadding.calculateBottomPadding()

    return Size(
        width.value * density.density,
        height.value * density.density
    )
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraSwitch(
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    contentModel: SwitchContentModel,
    presentationModel: SwitchPresentationModel
) {
    val drawingCache = remember { SwitchDrawingCache() }
    val rollover by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val ltr = (LocalLayoutDirection.current == LayoutDirection.Ltr)

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = contentModel.enabled,
                isRollover = rollover,
                isSelected = contentModel.selected,
                isPressed = isPressed
            )
        )
    }

    val density = LocalDensity.current

    // Transition for the selection state
    val selectionTransition = updateTransition(contentModel.selected)
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
    val totalFraction = selectedFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = contentModel.enabled,
        selected = contentModel.selected,
        rollover = rollover,
        pressed = isPressed,
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

    // The toggleable modifier is set on the switch pill, as well as on the
    // content so that the whole thing is clickable to toggle the control.
    val decorationAreaType = AuroraSkin.decorationAreaType
    Row(
        modifier = modifier
            .padding(presentationModel.contentPadding)
            .auroraRichTooltip(
                richTooltip = contentModel.richTooltip,
                presentationModel = presentationModel.richTooltipPresentationModel
            )
            .toggleable(
                value = contentModel.selected,
                onValueChange = { contentModel.onClick.invoke() },
                enabled = contentModel.enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = presentationModel.horizontalAlignment.arrangement
    ) {
        populateColorTokens(
            colorTokens = drawingCache.colorTokens,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            associationKind = ContainerColorTokensAssociationKind.Mark,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            treatEnabledAsActive = false,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted)

        // Get the thumb fill color (flat)
        val thumbColor = drawingCache.colorTokens.onContainer

        // Thumb selection factor is the combined strength of all the
        // states that have the selection bit turned on
        val thumbSelectionFactor = modelStateInfo.stateContributionMap
            .filter { it.key.isFacetActive(ComponentStateFacet.Selection) }
            .map { it.value }
            .sumOf { it.contribution.toDouble() }
            .toFloat()

        // Text color. Note that the text doesn't "participate" in state changes that
        // involve rollover, selection or pressed bits
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Neutral,
            isTextInFilledArea = false
        )
        val thumbAlpha = if (currentState.value.isDisabled)
            drawingCache.colorTokens.onContainerDisabledAlpha else 1.0f

        val surfacePainter = AuroraSkin.painters.surfacePainter
        val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
        val outlinePainter = AuroraSkin.painters.outlinePainter
        val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

        Canvas(Modifier.wrapContentSize(Alignment.Center).size(presentationModel.trackSize)) {
            val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
            val outlineFill = SwitchOutlineSuppler.getOutline(
                layoutDirection = layoutDirection,
                density = density,
                size = this.size,
                insets = outlineInset,
                radiusAdjustment = 0.0f,
                outlineKind = OutlineKind.Surface)

            paintSurface(
                drawScope = this,
                componentState = currentState.value,
                surfacePainter = surfacePainter,
                surfacePainterOverlay = surfacePainterOverlay,
                size = this.size,
                alpha = 1.0f,
                outline = outlineFill,
                colorTokens = drawingCache.colorTokens)

            paintOutline(
                drawScope = this,
                componentState = currentState.value,
                outlinePainter = outlinePainter,
                outlinePainterOverlay = outlinePainterOverlay,
                size = this.size,
                alpha = 1.0f,
                outlineSupplier = SwitchOutlineSuppler,
                colorTokens = drawingCache.colorTokens)

            val thumbSize = presentationModel.thumbSizeOff +
                    (presentationModel.thumbSizeOn - presentationModel.thumbSizeOff) * thumbSelectionFactor
            val thumbXStart = if (ltr) {
                val unselectedStartX = (presentationModel.trackSize.height - presentationModel.thumbSizeOff) / 2.0f
                val selectedStartX = presentationModel.trackSize.width -
                        (presentationModel.trackSize.height - presentationModel.thumbSizeOn) / 2.0f -
                        presentationModel.thumbSizeOn
                unselectedStartX + (selectedStartX - unselectedStartX) * thumbSelectionFactor
            } else {
                val unselectedStartX = presentationModel.trackSize.width -
                        (presentationModel.trackSize.height - presentationModel.thumbSizeOff) / 2.0f -
                        presentationModel.thumbSizeOff
                val selectedStartX = (presentationModel.trackSize.height - presentationModel.thumbSizeOn) / 2.0f
                selectedStartX + (unselectedStartX - selectedStartX) * (1.0f - thumbSelectionFactor)
            }

            val thumbRadiusPx = thumbSize.toPx() / 2.0f
            val thumbVerticalCenterPx = presentationModel.trackSize.height.toPx() / 2.0f
            val thumbOutline = Outline.Rounded(
                roundRect = RoundRect(
                    left = thumbXStart.toPx(),
                    top = thumbVerticalCenterPx - thumbRadiusPx,
                    right = thumbXStart.toPx() + 2.0f * thumbRadiusPx,
                    bottom = thumbVerticalCenterPx + thumbRadiusPx,
                    radiusX = thumbRadiusPx, radiusY = thumbRadiusPx
                )
            )

            drawOutline(
                outline = thumbOutline,
                style = Fill,
                color = thumbColor,
                alpha = thumbAlpha
            )
        }
    }
}
