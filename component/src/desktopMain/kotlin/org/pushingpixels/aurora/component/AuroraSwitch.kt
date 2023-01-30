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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.SwitchContentModel
import org.pushingpixels.aurora.component.model.SwitchPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.fill.FractionBasedFillPainter
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import org.pushingpixels.aurora.theming.utils.getBaseOutline

@Immutable
private class SwitchDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

private val trackFillPainter = FractionBasedFillPainter(
    0.0f to { it.lightColor },
    1.0f to { it.lightColor },
    displayName = "Track fill (internal)"
)

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
        // Get the thumb fill color (flat)
        val thumbColor = getStateAwareColor(
            modelStateInfo, currentState.value,
            decorationAreaType, ColorSchemeAssociationKind.Mark
        ) { it.markColor }

        // Populate the cached color scheme for filling the track
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.MarkBox
        )

        // And retrieve the track fill colors
        val trackUltraLight = drawingCache.colorScheme.ultraLightColor
        val trackExtraLight = drawingCache.colorScheme.extraLightColor
        val trackLight = drawingCache.colorScheme.lightColor
        val trackMid = drawingCache.colorScheme.midColor
        val trackDark = drawingCache.colorScheme.darkColor
        val trackUltraDark = drawingCache.colorScheme.ultraDarkColor
        val trackIsDark = drawingCache.colorScheme.isDark

        // Populate the cached color scheme for drawing the track border
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
            skinColors = AuroraSkin.colors,
            decorationAreaType = decorationAreaType,
            colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
            isTextInFilledArea = false
        )
        val alpha = if (currentState.value.isDisabled)
            AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f

        val borderPainter = AuroraSkin.painters.borderPainter

        Canvas(Modifier.wrapContentSize(Alignment.Center).size(presentationModel.trackSize)) {
            val trackOutline = getBaseOutline(
                layoutDirection = layoutDirection,
                width = this.size.width,
                height = this.size.height,
                radius = this.size.height / 2.0f,
                sides = null,
                insets = 0.5f
            )

            // Populate the cached color scheme for filling the track
            drawingCache.colorScheme.ultraLight = trackUltraLight
            drawingCache.colorScheme.extraLight = trackExtraLight
            drawingCache.colorScheme.light = trackLight
            drawingCache.colorScheme.mid = trackMid
            drawingCache.colorScheme.dark = trackDark
            drawingCache.colorScheme.ultraDark = trackUltraDark
            drawingCache.colorScheme.isDark = trackIsDark
            drawingCache.colorScheme.foreground = textColor
            trackFillPainter.paintContourBackground(
                this, this.size, trackOutline, drawingCache.colorScheme, alpha
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

            val trackOutlineInner = if (borderPainter.isPaintingInnerOutline) getBaseOutline(
                layoutDirection = layoutDirection,
                width = this.size.width,
                height = this.size.height,
                radius = this.size.height / 2.0f - 1.0f,
                sides = null,
                insets = 2.0f
            ) else null

            borderPainter.paintBorder(
                this, this.size, trackOutline, trackOutlineInner, drawingCache.colorScheme, alpha
            )

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
                alpha = alpha
            )
        }
    }
}
