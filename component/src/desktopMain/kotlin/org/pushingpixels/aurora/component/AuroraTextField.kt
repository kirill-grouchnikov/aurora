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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.LocalTextStyle
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.SunfireRedColorScheme
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.painter.border.AuroraBorderPainter
import org.pushingpixels.aurora.utils.getBaseOutline
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun AuroraTextField(
    modifier: Modifier = Modifier,
    contentModel: TextFieldStringContentModel,
    presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()) {

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
    // TODO - provide correct text color
    val textColor = Color.Black
    val mergedTextStyle = LocalTextStyle.current.merge(TextStyle(color = textColor))

    // TODO - provide correct border color scheme
    val borderScheme = SunfireRedColorScheme()

    // TODO - provide correct cursor color
    val cursorColor = Color.Blue

    // TODO - provide correct text selection colors
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = Color.Green,
            backgroundColor = Color.Green.copy(alpha = 0.4f)
        )
    ) {
        AuroraTextFieldLayout(
            modifier = modifier,
            contentModel = contentModel,
            presentationModel = presentationModel,
            textStyle = mergedTextStyle,
            interactionSource = remember { MutableInteractionSource() },
            borderScheme = borderScheme,
            cursorColor = cursorColor
        )
    }
}

@Composable
internal fun AuroraTextFieldLayout(
    modifier: Modifier,
    contentModel: TextFieldValueContentModel,
    presentationModel: TextFieldPresentationModel,
    textStyle: TextStyle,
    interactionSource: MutableInteractionSource,
    borderScheme: AuroraColorScheme,
    cursorColor: Color
) {
    val borderPainter = AuroraSkin.painters.borderPainter
    BasicTextField(
        value = contentModel.value,
        modifier = modifier
            .defaultMinSize(
                minWidth = TextFieldSizingConstants.MinWidth,
                minHeight = TextFieldSizingConstants.MinHeight,
            )
            .drawTextFieldBorder(borderPainter, borderScheme),
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

private fun Modifier.drawTextFieldBorder(
    borderPainter: AuroraBorderPainter,
    borderScheme: AuroraColorScheme
): Modifier = drawBehind {
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
        borderScheme = borderScheme,
        alpha = 1.0f
    )
}
