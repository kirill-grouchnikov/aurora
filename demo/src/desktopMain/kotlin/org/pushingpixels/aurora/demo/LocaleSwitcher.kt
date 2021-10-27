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
import kotlin.reflect.KMutableProperty0

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
@Composable
fun WindowScope.AuroraLocaleSwitcher(
    applicationLocaleProperty: KMutableProperty0<Locale>,
    resourceBundle: State<ResourceBundle>
) {
    val englishLocale = Command(
        text = resourceBundle.value.getString("Language.english"),
        icon = us(),
        action = {
            applicationLocaleProperty.set(Locale("en", "US"))
            window.applyComponentOrientation(ComponentOrientation.getOrientation(applicationLocaleProperty.get()))
        }
    )
    val hebrewLocale = Command(
        text = resourceBundle.value.getString("Language.hebrew"),
        icon = il(),
        action = {
            applicationLocaleProperty.set(Locale("iw", "IL"))
            window.applyComponentOrientation(ComponentOrientation.getOrientation(applicationLocaleProperty.get()))
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
