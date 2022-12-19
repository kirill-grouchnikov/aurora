/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.component.ribbon

import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandPopupMenuPresentationModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.theming.IconFilterStrategy

data class RibbonTaskbarCommandButtonPresentationModel(
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val popupMenuPresentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel()
) : PresentationModel

interface RibbonTaskbarKeyTipPolicy {
    /**
     * Returns the keytip for the task bar content (command, component, gallery, menu link)
     * at the specified index.
     *
     * @param contentIndex Index of the task bar content. Content index starts at 1.
     * @return Keytip for the specified content.
     */
    fun getContentKeyTip(contentIndex: Int): String

    /**
     * Returns the keytip for the overflow button of the task bar.
     *
     * @return Keytip for the overflow button of the task bar.
     */
    val overflowButtonKeyTip: String
}

class DefaultRibbonTaskbarKeyTipPolicy : RibbonTaskbarKeyTipPolicy {
    override val overflowButtonKeyTip = "00"

    override fun getContentKeyTip(contentIndex: Int): String {
        var contentIndex = contentIndex
        if (contentIndex < 10) {
            return contentIndex.toString()
        }
        contentIndex -= 10

        // Creates a sequence of 01, 02, 03, ..., 09, 0A, 0B, 0C, ..., 0Z, 11, 12, ...
        return (contentIndex / LETTERS.length).toString() +
                LETTERS[contentIndex % LETTERS.length]
    }

    companion object {
        private const val LETTERS = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }
}

class RibbonTaskbarCommandButtonProjection(
    val contentModel: Command,
    val presentationModel: RibbonTaskbarCommandButtonPresentationModel
) : Projection<Command, RibbonTaskbarCommandButtonPresentationModel>()

