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
package org.pushingpixels.aurora.component.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay

data class TextFieldValueContentModel(
    val value: TextFieldValue,
    val placeholder: String = "",
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val onValueChange: (TextFieldValue) -> Unit
) : ContentModel

data class TextFieldStringContentModel(
    val value: String,
    val placeholder: String = "",
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val onValueChange: (String) -> Unit
) : ContentModel

object TextFieldSizingConstants {
    val MinWidth = 200.dp
    val MinHeight = 24.dp
    val DefaultTextFieldContentPadding = PaddingValues(start = 5.dp, top = 4.dp, end = 5.dp, bottom = 4.dp)
}

data class TextFieldPresentationModel(
    val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val keyboardActions: KeyboardActions = KeyboardActions(),
    val contentPadding: PaddingValues = TextFieldSizingConstants.DefaultTextFieldContentPadding,
    val defaultMinSize: DpSize = DpSize(width = TextFieldSizingConstants.MinWidth, TextFieldSizingConstants.MinHeight),
    val singleLine: Boolean = false,
    val showBorder: Boolean = true,
    val maxLines: Int = Int.MAX_VALUE,
    val textStyle: TextStyle? = null,
    val visualTransformation: VisualTransformation = VisualTransformation.None
) : PresentationModel
