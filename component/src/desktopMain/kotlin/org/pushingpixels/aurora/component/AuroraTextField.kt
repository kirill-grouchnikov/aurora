/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.model.TextFieldPresentationModel
import org.pushingpixels.aurora.component.model.TextFieldSizingConstants
import org.pushingpixels.aurora.component.model.TextFieldStringContentModel
import org.pushingpixels.aurora.component.model.TextFieldValueContentModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.utils.getBaseOutline
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
private class TextFieldDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false,
        ultraLight = Color.White,
        extraLight = Color.White,
        light = Color.White,
        mid = Color.White,
        dark = Color.White,
        ultraDark = Color.White,
        foreground = Color.Black
    )
)

@Composable
fun AuroraTextField(
    modifier: Modifier = Modifier,
    contentModel: TextFieldStringContentModel,
    presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) {

    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = contentModel.value)) }
    val textFieldValue = textFieldValueState.copy(text = contentModel.value)

    AuroraTextField(
        modifier = modifier,
        contentModel = TextFieldValueContentModel(
            value = textFieldValue,
            onValueChange = {
                textFieldValueState = it
                if (contentModel.value != it.text) {
                    contentModel.onValueChange(it.text)
                }
            },
            enabled = contentModel.enabled,
            readOnly = contentModel.readOnly
        ),
        presentationModel = presentationModel
    )
}

@Composable
fun AuroraTextField(
    modifier: Modifier = Modifier,
    contentModel: TextFieldValueContentModel,
    presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) {
    val interactionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { TextFieldDrawingCache() }
    var rollover by remember { mutableStateOf(false) }
    val isPressed by interactionSource.collectIsPressedAsState()
    // Treat focused as selected
    val isFocused by interactionSource.collectIsFocusedAsState()

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = contentModel.enabled,
                isRollover = rollover,
                isSelected = isFocused,
                isPressed = isPressed
            )
        )
    }

    // Transition for the selection state
    val selectionTransition = updateTransition(isFocused)
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
    val totalFraction = selectedFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = contentModel.enabled,
        selected = isFocused,
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
//            println("******** Animating at ${currentState.value} from ${transitionInfo.value!!.from} to 1.0f over ${transitionInfo.value!!.duration} ********")
//            println("******** Is running ${transitionFloat.isRunning} ********")
            val result = transitionFloat.animateTo(
                targetValue = transitionInfo.value!!.to,
                animationSpec = tween(durationMillis = transitionInfo.value!!.duration)
            ) {
//                println("During animation $value towards $targetValue")
                modelStateInfo.updateActiveStates(value)
            }

            //println("&&&&&&& Ended with reason ${result.endReason} at ${transitionFloat.value}")
            if (result.endReason == AnimationEndReason.Finished) {
                modelStateInfo.updateActiveStates(1.0f)
                modelStateInfo.clear(currentState.value)
//                println("******** After clear (target reached) ********")
//                modelStateInfo.dumpState(stateTransitionFloat.value)
            }
        }
    }

    val skinColors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val borderPainter = AuroraSkin.painters.borderPainter

    // Populate the cached color scheme for drawing the button border
    // based on the current model state info
    populateColorScheme(
        drawingCache.colorScheme,
        modelStateInfo,
        currentState.value,
        decorationAreaType,
        ColorSchemeAssociationKind.BORDER
    )
    // And retrieve the border colors
    val borderUltraLight = drawingCache.colorScheme.ultraLightColor
    val borderExtraLight = drawingCache.colorScheme.extraLightColor
    val borderLight = drawingCache.colorScheme.lightColor
    val borderMid = drawingCache.colorScheme.midColor
    val borderDark = drawingCache.colorScheme.darkColor
    val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
    val borderIsDark = drawingCache.colorScheme.isDark

    val alpha = if (currentState.value.isDisabled)
        AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f

    val textColor = getTextColor(
        modelStateInfo = modelStateInfo,
        currState = currentState.value,
        skinColors = AuroraSkin.colors,
        decorationAreaType = decorationAreaType,
        isTextInFilledArea = false
    )
    val textStyle = LocalTextStyle.current.merge(TextStyle(color = textColor))

    val cursorColor = textColor

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            val borderStrokeWidth = TextFieldSizingConstants.BorderWidth.value * density

            val backgroundFillColor = if (contentModel.readOnly)
                skinColors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.FILL,
                    componentState = currentState.value
                ).backgroundFillColor
            else getTextFillBackground(
                modelStateInfo = modelStateInfo,
                currState = currentState.value,
                skinColors = skinColors,
                decorationAreaType = decorationAreaType
            )

            drawRect(
                color = backgroundFillColor,
                topLeft = Offset(borderStrokeWidth / 2.0f, borderStrokeWidth / 2.0f),
                size = Size(size.width - borderStrokeWidth, size.height - borderStrokeWidth)
            )

            val outline = getBaseOutline(
                width = size.width,
                height = size.height,
                radius = 0.0f,
                straightSides = Side.values().toSet(),
                insets = borderStrokeWidth
            )

            val outlineBoundingRect = outline.bounds
            if (outlineBoundingRect.isEmpty) {
                return@Canvas
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

            borderPainter.paintBorder(
                drawScope = this,
                size = size,
                outline = getBaseOutline(
                    width = size.width,
                    height = size.height,
                    radius = 0.0f,
                    straightSides = Side.values().toSet(),
                    insets = TextFieldSizingConstants.BorderWidth.value * density
                ),
                outlineInner = null,
                borderScheme = drawingCache.colorScheme,
                alpha = alpha
            )
        }

        // TODO - Compose does not support specifying foreground color for selected text
        val textSelectionBackgroundColor = getTextSelectionBackground(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType

        )
        CompositionLocalProvider(
            LocalTextSelectionColors provides TextSelectionColors(
                handleColor = textSelectionBackgroundColor,
                backgroundColor = textSelectionBackgroundColor
            )
        ) {
            BasicTextField(
                value = contentModel.value,
                modifier = modifier
                    .defaultMinSize(
                        minWidth = TextFieldSizingConstants.MinWidth,
                        minHeight = TextFieldSizingConstants.MinHeight,
                    )
                    .pointerMoveFilter(
                        onEnter = {
                            rollover = true
                            false
                        },
                        onExit = {
                            rollover = false
                            false
                        },
                        onMove = {
                            false
                        }),
                onValueChange = contentModel.onValueChange,
                enabled = contentModel.enabled,
                readOnly = contentModel.readOnly,
                textStyle = textStyle,
                cursorBrush = SolidColor(cursorColor),
                visualTransformation = presentationModel.visualTransformation,
                keyboardOptions = presentationModel.keyboardOptions,
                keyboardActions = presentationModel.keyboardActions,
                interactionSource = interactionSource,
                singleLine = presentationModel.singleLine,
                maxLines = presentationModel.maxLines,
                decorationBox = @Composable { coreTextField ->
                    TextFieldContentLayout(
                        textField = coreTextField
                    )
                }
            )
        }
    }
}

@Composable
private fun TextFieldContentLayout(
    textField: @Composable () -> Unit
) {
    Layout(
        content = {
            Box(
                modifier = Modifier.padding(TextFieldSizingConstants.ContentPadding),
                propagateMinConstraints = true
            ) {
                textField()
            }
        }
    ) { measurables, incomingConstraints ->
        val textFieldPlaceable = measurables[0].measure(incomingConstraints)

        val width = max(incomingConstraints.minWidth, textFieldPlaceable.width)
        val height = max(incomingConstraints.minHeight, textFieldPlaceable.height)

        layout(width, height) {
            val textVerticalPosition =
                (TextFieldSizingConstants.ContentPadding.calculateTopPadding().value * density).roundToInt()
            textFieldPlaceable.placeRelative(0, textVerticalPosition)
        }
    }
}
