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

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.layout.ComponentLambda
import org.pushingpixels.aurora.layout.Sizes
import org.pushingpixels.aurora.layout.factories.ComponentFactory
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.ContainerColorTokens

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

    override fun createLabel(text: String): ComponentLambda {
        return { builderModifier: Modifier ->
            LabelProjection(
                contentModel = LabelContentModel(text = text),
                presentationModel = LabelPresentationModel(textMaxLines = 1)
            ).project(builderModifier)
        }
    }

    override fun createReadOnlyLabel(text: String): ComponentLambda {
        return { builderModifier: Modifier ->
            LabelProjection(
                contentModel = LabelContentModel(text = text),
                presentationModel = LabelPresentationModel(
                    textMaxLines = 1,
                    colorTokenQuery = ContainerColorTokens::onContainerLow)
            ).project(builderModifier)
        }
    }

    override fun createTitle(text: String): ComponentLambda {
        return { builderModifier: Modifier ->
            LabelProjection(
                contentModel = LabelContentModel(text = text),
                presentationModel = LabelPresentationModel(
                    textMaxLines = 1,
                    textStyle = TextStyle(fontWeight = FontWeight.Bold)
                )
            ).project(builderModifier)
        }
    }

    override fun createHeaderLabel(text: String): ComponentLambda {
        return { builderModifier: Modifier ->
            LabelProjection(
                contentModel = LabelContentModel(text = text),
                presentationModel = LabelPresentationModel(
                    textMaxLines = 1,
                    colorTokenQuery = ContainerColorTokens::accentOnContainer,
                    textStyle = TextStyle(fontWeight = FontWeight.Bold)
                )
            ).project(builderModifier)
        }
    }

    override fun createSeparator(text: String, arrangement: Arrangement.Horizontal): ComponentLambda {
        require((arrangement == Arrangement.Start) || (arrangement == Arrangement.End) || (arrangement == Arrangement.Center)) {
            "Only Arrangement.Start, Arrangement.End and Arrangement.Center are supported"
        }
        return { builderModifier: Modifier ->
            when (arrangement) {
                Arrangement.Start ->
                    Row(modifier = builderModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        LabelProjection(
                            contentModel = LabelContentModel(text = text),
                            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontWeight = FontWeight.Bold))
                        ).project()

                        Spacer(modifier = Modifier.width(Sizes.DluX1.toDp()))

                        HorizontalSeparatorProjection().project(
                            modifier = Modifier.weight(1.0f, fill = true).padding(top = 2.dp))
                    }

                Arrangement.End ->
                    Row(modifier = builderModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        HorizontalSeparatorProjection().project(
                            modifier = Modifier.weight(1.0f, fill = true).padding(top = 2.dp))

                        Spacer(modifier = Modifier.width(Sizes.DluX1.toDp()))

                        LabelProjection(
                            contentModel = LabelContentModel(text = text),
                            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontWeight = FontWeight.Bold))
                        ).project()
                    }

                Arrangement.Center ->
                    Row(modifier = builderModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        HorizontalSeparatorProjection().project(
                            modifier = Modifier.weight(1.0f, fill = true).padding(top = 2.dp))

                        Spacer(modifier = Modifier.width(Sizes.DluX3.toDp()))

                        LabelProjection(
                            contentModel = LabelContentModel(text = text),
                            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontWeight = FontWeight.Bold))
                        ).project()

                        Spacer(modifier = Modifier.width(Sizes.DluX3.toDp()))

                        HorizontalSeparatorProjection().project(
                            modifier = Modifier.weight(1.0f, fill = true).padding(top = 2.dp))
                    }
            }
        }
    }
}