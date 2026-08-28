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
package org.pushingpixels.aurora.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import org.pushingpixels.aurora.layout.factories.ComponentFactory

@Composable
public fun FormCortex(
    textStyle: TextStyle,
    componentFactory: ComponentFactory,
    content: @Composable () -> Unit
) {
    val textMeasurer = rememberTextMeasurer()

    CompositionLocalProvider(
        LocalFormLayoutInitialized provides true,
        LocalTextMeasurer provides textMeasurer,
        LocalTextStyle provides textStyle,
        LocalComponentFactory provides componentFactory,
    ) {
        content()
    }
}

@Composable
public fun getFormCortexCompositionLocals(
    textStyle: TextStyle,
    componentFactory: ComponentFactory
): Array<ProvidedValue<out Any>> {
    val textMeasurer = rememberTextMeasurer()

    return arrayOf(
        LocalFormLayoutInitialized provides true,
        LocalTextMeasurer provides textMeasurer,
        LocalTextStyle provides textStyle,
        LocalComponentFactory provides componentFactory,
    )
}
