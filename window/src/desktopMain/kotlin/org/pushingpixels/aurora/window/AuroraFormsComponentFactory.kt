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
package org.pushingpixels.aurora.window

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.layout.ComponentLambda
import org.pushingpixels.aurora.layout.factories.ComponentFactory
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy

class AuroraFormsComponentFactory: ComponentFactory {
    override fun createButton(
        text: String,
        icon: Painter?,
        action: (() -> Unit)?,
        isEnabled: Boolean
    ): ComponentLambda {
        return { builderModifier: Modifier ->
            CommandButtonProjection(
                contentModel = Command(
                    text = text,
                    icon = icon,
                    action = action
                ),
                presentationModel = CommandButtonPresentationModel(
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                    presentationState = CommandButtonPresentationState.Medium
                )
            ).project(builderModifier)
        }
    }
}