/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.skin.*
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.TextFieldPresentationModel
import org.pushingpixels.aurora.component.model.TextFieldSizingConstants
import org.pushingpixels.aurora.component.model.TextFieldStringContentModel
import org.pushingpixels.aurora.component.model.TextFieldValueContentModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.skin.utils.MutableColorScheme
import org.pushingpixels.aurora.skin.utils.getBaseOutline
import kotlin.math.max
import kotlin.math.roundToInt

@Immutable
private class TextFieldDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

@Composable
internal fun AuroraTextField(
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
internal fun AuroraTextField(
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

    // Populate the cached color scheme for drawing the text field border
    // based on the current model state info
    populateColorScheme(
        drawingCache.colorScheme,
        modelStateInfo,
        currentState.value,
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

    val alpha = if (currentState.value.isDisabled)
        AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f

    val textColor = getTextColor(
        modelStateInfo = modelStateInfo,
        currState = currentState.value,
        skinColors = AuroraSkin.colors,
        decorationAreaType = decorationAreaType,
        colorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
        isTextInFilledArea = false
    )
    val textStyle = LocalTextStyle.current.merge(TextStyle(color = textColor))

    val cursorColor = textColor

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            val borderStrokeWidth = 1.0f

            // Read-only text fields use the regular background fill. Editable text fields
            // use text background fill (with rollover and focused transitions)
            val backgroundFillColor = if (contentModel.readOnly)
                skinColors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.Fill,
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
                    insets = 1.0f
                ),
                outlineInner = null,
                borderScheme = drawingCache.colorScheme,
                alpha = alpha
            )

            if (!contentModel.readOnly) {
                // Get the base border color
                val baseBorderScheme = skinColors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.Border,
                    componentState = currentState.value
                )
                var borderColor = borderPainter.getRepresentativeColor(baseBorderScheme)

                if (!currentState.value.isDisabled && (modelStateInfo.stateContributionMap.size > 1)) {
                    // If we have more than one active state, compute the composite color from all
                    // the contributions
                    for (activeEntry in modelStateInfo.stateContributionMap.entries) {
                        val activeState = activeEntry.key
                        if (activeState === currentState.value) {
                            continue
                        }
                        val contribution = activeEntry.value.contribution
                        if (contribution == 0.0f) {
                            continue
                        }
                        val activeStateAlpha = skinColors.getAlpha(decorationAreaType, activeState)
                        if (activeStateAlpha == 0.0f) {
                            continue
                        }
                        val activeBorderScheme =
                            skinColors.getColorScheme(
                                decorationAreaType = decorationAreaType,
                                associationKind = ColorSchemeAssociationKind.Border,
                                componentState = activeState
                            )
                        val activeBorderColor =
                            borderPainter.getRepresentativeColor(activeBorderScheme)
                        borderColor = borderColor.interpolateTowards(
                            activeBorderColor,
                            1.0f - contribution * activeStateAlpha
                        )
                    }
                }

                // Paint a translucent drop shadow along the top edge of this editable text field
                val shadowHeight = 6.dp
                val topAlpha = if (currentState.value.isDisabled) 16 else 32
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            borderColor.withAlpha(topAlpha / 256.0f),
                            borderColor.withAlpha(0.0f)
                        ),
                        startY = 0.0f,
                        endY = shadowHeight.toPx(),
                        tileMode = TileMode.Clamp
                    ),
                    topLeft = Offset(borderStrokeWidth, borderStrokeWidth),
                    size = Size(size.width - 2 * borderStrokeWidth, shadowHeight.toPx()),
                    style = Fill
                )
            }
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
                modifier = Modifier.padding(TextFieldSizingConstants.DefaultTextFieldContentPadding),
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
                (TextFieldSizingConstants.DefaultTextFieldContentPadding.calculateTopPadding().value * density).roundToInt()
            textFieldPlaceable.placeRelative(0, textVerticalPosition)
        }
    }
}
