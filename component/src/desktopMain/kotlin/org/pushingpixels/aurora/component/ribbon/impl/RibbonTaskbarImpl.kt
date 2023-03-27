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
package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.component.model.BaseCommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarCommandProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarComponentProjection
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarElement
import org.pushingpixels.aurora.component.ribbon.RibbonTaskbarGalleryProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy

@Composable
fun RibbonTaskbar(
    modifier: Modifier,
    elements: List<RibbonTaskbarElement>
) {
    Row(modifier = modifier) {
        for (element in elements) {
            when (element) {
                is RibbonTaskbarCommandProjection -> {
                    element.commandProjection.reproject(
                        modifier = Modifier,
                        primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                            presentationState = CommandButtonPresentationState.Small,
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                        ),
                        actionInteractionSource = remember { MutableInteractionSource() },
                        popupInteractionSource = remember { MutableInteractionSource() }
                    )
                }

                is RibbonTaskbarGalleryProjection -> {}

                is RibbonTaskbarComponentProjection -> {}
            }
        }
    }
}