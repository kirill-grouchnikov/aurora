package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.flags.il
import org.pushingpixels.aurora.demo.svg.flags.us
import org.pushingpixels.aurora.demo.svg.tango.preferences_desktop_locale_2
import org.pushingpixels.aurora.window.AuroraLocaleScope
import java.awt.ComponentOrientation
import java.awt.Window
import java.util.*

@Composable
fun AuroraLocaleScope.AuroraLocaleSwitcher(
    resourceBundle: State<ResourceBundle>
) {
    val englishLocale = Command(
        text = resourceBundle.value.getString("Language.english"),
        icon = us(),
        action = {
            applicationLocale = Locale("en", "US")
            // Only necessary for embedded Swing content
            for (window in Window.getWindows()) {
                window.applyComponentOrientation(
                    ComponentOrientation.getOrientation(applicationLocale)
                )
            }
        }
    )
    val hebrewLocale = Command(
        text = resourceBundle.value.getString("Language.hebrew"),
        icon = il(),
        action = {
            applicationLocale = Locale("iw", "IL")
            // Only necessary for embedded Swing content
            for (window in Window.getWindows()) {
                window.applyComponentOrientation(
                    ComponentOrientation.getOrientation(applicationLocale)
                )
            }
        }
    )
    val localeCommand = Command(
        text = resourceBundle.value.getString("Language.select"),
        icon = preferences_desktop_locale_2(),
        secondaryContentModel = CommandMenuContentModel(
            group = CommandGroup(
                commands = arrayListOf(englishLocale, hebrewLocale)
            )
        )
    )
    CommandButtonProjection(
        contentModel = localeCommand,
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium
        )
    ).project()
}
