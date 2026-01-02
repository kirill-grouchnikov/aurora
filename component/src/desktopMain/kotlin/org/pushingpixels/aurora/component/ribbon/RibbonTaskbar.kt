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
package org.pushingpixels.aurora.component.ribbon

import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection

sealed interface RibbonTaskbarElement

data class RibbonTaskbarCommand(val commandProjection: BaseCommandButtonProjection<*, *, *>) :
    RibbonTaskbarElement

data class RibbonTaskbarComponent(val componentProjection: Projection<ContentModel, PresentationModel>) :
    RibbonTaskbarElement

data class RibbonTaskbarGallery(val galleryProjection: RibbonGalleryProjection):
    RibbonTaskbarElement

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

object DefaultRibbonTaskbarKeyTipPolicy : RibbonTaskbarKeyTipPolicy {
    override val overflowButtonKeyTip = "00"

    override fun getContentKeyTip(contentIndex: Int): String {
        var contentIndexForLookup = contentIndex
        if (contentIndexForLookup < 10) {
            return contentIndexForLookup.toString()
        }
        contentIndexForLookup -= 10

        // Creates a sequence of 01, 02, 03, ..., 09, 0A, 0B, 0C, ..., 0Z, 11, 12, ...
        return (contentIndexForLookup / LETTERS.length).toString() +
                LETTERS[contentIndex % LETTERS.length]
    }

    private const val LETTERS = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
}
