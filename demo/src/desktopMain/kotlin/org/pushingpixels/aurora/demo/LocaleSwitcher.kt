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
package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.flags.il
import org.pushingpixels.aurora.demo.svg.flags.us
import org.pushingpixels.aurora.demo.svg.tango.preferences_desktop_locale
import org.pushingpixels.aurora.window.AuroraLocaleScope
import java.awt.ComponentOrientation
import java.awt.Window
import java.util.*

@Composable
fun AuroraLocaleScope.AuroraLocaleSwitcher(resourceBundle: ResourceBundle) {
    val englishLocale = Command(
        text = resourceBundle.getString("Language.english"),
        icon = us(),
        action = {
            applicationLocale = Locale.US
            // Only necessary for embedded Swing content
            for (window in Window.getWindows()) {
                window.applyComponentOrientation(
                    ComponentOrientation.getOrientation(applicationLocale)
                )
            }
        }
    )
    val hebrewLocale = Command(
        text = resourceBundle.getString("Language.hebrew"),
        icon = il(),
        action = {
            applicationLocale = Locale.forLanguageTag("iw-IL")
            // Only necessary for embedded Swing content
            for (window in Window.getWindows()) {
                window.applyComponentOrientation(
                    ComponentOrientation.getOrientation(applicationLocale)
                )
            }
        }
    )
    val localeCommand = Command(
        text = resourceBundle.getString("Language.select"),
        icon = preferences_desktop_locale(),
        secondaryContentModel = CommandMenuContentModel(
            group = CommandGroup(commands = arrayListOf(englishLocale, hebrewLocale))
        )
    )
    CommandButtonProjection(
        contentModel = localeCommand,
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium
        )
    ).project()
}
