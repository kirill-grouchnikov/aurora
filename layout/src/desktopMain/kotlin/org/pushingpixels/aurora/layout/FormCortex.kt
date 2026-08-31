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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import org.pushingpixels.aurora.layout.factories.ComponentFactory

/**
 * This composable function can be used at the top level of your UI hierarchy
 * to initialize the configuration needed by [FormLayout] APIs by wrapping your
 * hierarchy.
 *
 * For example:
 * ```kotlin
 * FormCortex(
 *     textStyle = AcmeDesignSystemTextStyle(),
 *     componentFactory = AcmeDesignSystemComponentFactory()
 * ) {
 *     MyAcmeWindowContent()
 * }
 * ```
 *
 * @param textStyle The default text style used by your design system. This will
 *    be used to convert DLU units to pixel and dp values.
 * @param componentFactory Component factory that matches the components used by
 *    your design system. This will be used by APIs in form builders such as
 *    [org.pushingpixels.aurora.layout.builder.PanelScope].
 */
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

/**
 * This composable function can be used at the top level of your UI hierarchy
 * to initialize the configuration needed by [FormLayout] APIs by adding the
 * returned composition locals (via the array of [ProvidedValue]s) to your top
 * level [CompositionLocalProvider].
 *
 * For example:
 * ```kotlin
 * val formCortexCompositionLocals = getFormCortexCompositionLocals(
 *     textStyle = AcmeDesignSystemTextStyle(),
 *     componentFactory = AcmeDesignSystemComponentFactory()
 * )
 *
 * // Combine my design system composition locals with FormLayout composition locals
 * val compositionLocals = arrayOf(
 *     AcmeColorsCompositionLocal provides AcmeColors(),
 *     AcmeTypographyCompositionLocal provides AcmeTypography(),
 *     ...
 * ) + formCortexCompositionLocals
 *
 * // And set them all in one go
 * CompositionLocalProvider(*compositionLocals) {
 *     MyAcmeWindowContent()
 * }
 * ```
 *
 * @param textStyle The default text style used by your design system. This will
 *    be used to convert DLU units to pixel and dp values.
 * @param componentFactory Component factory that matches the components used by
 *    your design system. This will be used by APIs in form builders such as
 *    [org.pushingpixels.aurora.layout.builder.PanelScope].
 */
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
