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
package org.pushingpixels.aurora.demo.ribbon

import org.pushingpixels.aurora.component.ribbon.RibbonGalleryInlineState

enum class Task {
    PageLayout, Write, Animations, Wrapped, Contextual11, Contextual12, Contextual21
}

enum class DocumentStyle {
    Style1, Style2, Style3, Style4, Style5, Style6, Style7, Style8, Style9, Style10,
    Style11, Style12, Style13, Style14, Style15, Style16, Style17, Style18, Style19, Style20,
    Style21, Style22, Style23, Style24, Style25, Style26, Style27, Style28, Style29, Style30
}

enum class FontFamily {
    Calibri, Columbus, Consolas, Cornelius,
    Cleopatra, Cornucopia, California, Calendula,
    Coriander, Callisto, Cajun, Congola,
    Candella, Cambria
}

enum class FontSize(val fontSize: Int) {
    Size11(11), Size12(12), Size13(13), Size14(14), Size16(16)
}

enum class DocumentSaveLocation {
    None, Local, Remote, Saved
}

enum class ApplicationGame {
    Tetris, Minesweeper, Doom
}

enum class ApplicationBrowser {
    Firefox, Opera, Konqueror
}

enum class ApplicationMultimedia(val resourceKey: String) {
    Pictures("Pictures.text"), Video("Video.text"), Audio("Audio.text")
}

enum class Presentation {
    Comfortable, Cozy, Compact
}

data class RibbonState(
    val selectedTask: Task,
    val documentStyle: DocumentStyle,
    val fontFamily: FontFamily,
    val fontSize: FontSize,
    val documentSaveLocation: DocumentSaveLocation,
    val applicationGame: ApplicationGame,
    val applicationBrowser: ApplicationBrowser,
    val applicationMultimedia: ApplicationMultimedia,
    val visibilityRulerOn: Boolean,
    val visibilityGridlinesOn: Boolean,
    val visibilityMessageBarOn: Boolean,
    val visibilityDocumentMapOn: Boolean,
    val visibilityThumbnailsOn: Boolean,
    val presentation: Presentation
) {
    lateinit var documentStyleGalleryInlineState: RibbonGalleryInlineState
}
