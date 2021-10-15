package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.window.WindowScope
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.flags.il
import org.pushingpixels.aurora.demo.svg.flags.us
import org.pushingpixels.aurora.demo.svg.tango.preferences_desktop_locale_2
import java.awt.ComponentOrientation
import java.util.*

@Composable
fun WindowScope.AuroraLocaleSwitcher(
    locale: MutableState<Locale>,
    resourceBundle: State<ResourceBundle>
) {
    val englishLocale = Command(
        text = resourceBundle.value.getString("Language.english"),
        icon = us(),
        action = {
            locale.value = Locale("en", "US")
            Locale.setDefault(locale.value)
            window.applyComponentOrientation(ComponentOrientation.getOrientation(locale.value))
        }
    )
    val hebrewLocale = Command(
        text = resourceBundle.value.getString("Language.hebrew"),
        icon = il(),
        action = {
            locale.value = Locale("iw", "IL")
            Locale.setDefault(locale.value)
            window.applyComponentOrientation(ComponentOrientation.getOrientation(locale.value))
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