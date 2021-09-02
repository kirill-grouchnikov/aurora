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
package org.pushingpixels.aurora.component.projection

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.skin.LocalWindow
import org.pushingpixels.aurora.skin.Side
import org.pushingpixels.aurora.skin.Sides
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*

// TODO - restore when issue
//  https://issuetracker.google.com/issues/165812010 is fixed
//abstract class BaseProjection<C : ContentModel, P : PresentationModel>(
//    val contentModel: C,
//    val presentationModel: P
//) {
//    @Composable
//    abstract fun project(modifier: Modifier? = null)
//}
//
//abstract class CommandBasedProjection<C : ContentModel, P : PresentationModel>(
//    contentModel: C,
//    presentationModel: P,
//    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
//) : BaseProjection<C, P>(contentModel, presentationModel)

class CommandButtonProjection(
    val contentModel: Command,
    val presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        val window = LocalWindow.current
        AuroraCommandButton(
            modifier = modifier,
            command = this.contentModel,
            parentWindow = window,
            extraAction = null,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf(),
            buttonSides = Sides(
                straightSides = if (presentationModel.isMenu) Side.values().toSet() else emptySet()
            )
        )
    }
}

class CommandButtonStripProjection(
    val contentModel: CommandGroup,
    val presentationModel: CommandStripPresentationModel,
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraCommandButtonStrip(
            modifier = modifier,
            commandGroup = this.contentModel,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class CommandButtonPanelProjection(
    val contentModel: CommandPanelContentModel,
    val presentationModel: CommandPanelPresentationModel,
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        require(
            !presentationModel.showGroupLabels ||
                    (presentationModel.layoutFillMode == PanelLayoutFillMode.RowFill)
        ) {
            "Column fill layout is not supported when group labels are shown"
        }
        // TODO - pass the app-side modifier
        AuroraCommandButtonPanel(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class ComboBoxProjection<E>(
    val contentModel: ComboBoxContentModel<E>,
    val presentationModel: ComboBoxPresentationModel<E>
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraComboBox(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class CheckBoxProjection(
    val contentModel: SelectorContentModel,
    val presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraCheckBox(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class RadioButtonProjection(
    val contentModel: SelectorContentModel,
    val presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraRadioButton(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class CircularProgressProjection(
    val contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    val presentationModel: ProgressCircularPresentationModel = ProgressCircularPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraCircularProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class IndeterminateLinearProgressProjection(
    val contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    val presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraIndeterminateLinearProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class DeterminateLinearProgressProjection(
    val contentModel: ProgressDeterminateContentModel,
    val presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraDeterminateLinearProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class LabelProjection(
    val contentModel: LabelContentModel,
    val presentationModel: LabelPresentationModel = LabelPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraLabel(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class VerticalSeparatorProjection(
    val contentModel: SeparatorContentModel = SeparatorContentModel(),
    val presentationModel: SeparatorPresentationModel = SeparatorPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraVerticalSeparator(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class HorizontalSeparatorProjection(
    val contentModel: SeparatorContentModel = SeparatorContentModel(),
    val presentationModel: SeparatorPresentationModel = SeparatorPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraHorizontalSeparator(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class SliderProjection(
    val contentModel: SliderContentModel,
    val presentationModel: SliderPresentationModel = SliderPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraSlider(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TextFieldValueProjection(
    val contentModel: TextFieldValueContentModel,
    val presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraTextField(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TextFieldStringProjection(
    val contentModel: TextFieldStringContentModel,
    val presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        // TODO - pass the app-side modifier
        AuroraTextField(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}
