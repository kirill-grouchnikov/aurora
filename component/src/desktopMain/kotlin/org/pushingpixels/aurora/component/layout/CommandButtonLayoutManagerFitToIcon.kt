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
package org.pushingpixels.aurora.component.layout

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel

internal class CommandButtonLayoutManagerFitToIcon(
    layoutDirection: LayoutDirection,
    _density: Density,
    textStyle: TextStyle,
    resourceLoader: Font.ResourceLoader
) : CommandButtonLayoutManagerBig(layoutDirection, _density, textStyle, resourceLoader) {
    override fun getPreferredIconSize(
        command: Command,
        presentationModel: CommandButtonPresentationModel
    ): Dp {
        return (-1).dp
    }

    override fun getCurrentIconWidth(
        command: Command,
        presentationModel: CommandButtonPresentationModel
    ): Dp {
        return if (command.iconFactory != null)
            getPreferredIconSize(command, presentationModel) else 0.dp
    }

    override fun getCurrentIconHeight(
        command: Command,
        presentationModel: CommandButtonPresentationModel
    ): Dp {
        return if (command.iconFactory != null)
            getPreferredIconSize(command, presentationModel) else 0.dp
    }
}