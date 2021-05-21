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
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel

abstract class BaseProjection<C : ContentModel, P : PresentationModel>(
    val contentModel: C,
    val presentationModel: P
) {
    // TODO - add Modifier as a parameter
    @Composable
    abstract fun project()
}

abstract class CommandBasedProjection<C : ContentModel, P : PresentationModel>(
    contentModel: C,
    presentationModel: P,
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : BaseProjection<C, P>(contentModel, presentationModel)
