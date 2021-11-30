## Window - application APIs

Every Aurora-powered application needs an `auroraApplication` block that wraps it.

This block creates an `AuroraApplicationScope` that extends the base Compose `ApplicationScope`, as well as Aurora's own `AuroraLocaleScope`.

The definition of `AuroraLocaleScope` is:

```kotlin
interface AuroraLocaleScope {
    var applicationLocale: Locale
}
```

### Setting application locale

The default value for application locale is set to `Locale.getDefault()`. Applications that expose locale switching can persist their currently selected locale on application exit and use that saved value on next application startup:

```kotlin
fun main() = auroraApplication {
    // Hard coded here, but can be read from persisted
    // value and constructed dynamically
    applicationLocale = Locale("iw", "IL")

    AuroraWindow(...) {
        // Content
    }
}
```

### Working with resource bundles

Use the combination of `applicationLocale` and Compose's `derivedStateOf` to load your localized strings.

First, create your derived `ResourceBundle`:

```kotlin
val resourceBundle = derivedStateOf {
    ResourceBundle.getBundle(
      "org.pushingpixels.aurora.demo.Resources",
      applicationLocale)
}
```

Now you can use this resource bundle to configure strings in your UI:

```kotlin
CommandButtonProjection(
    contentModel = Command(
        text = resourceBundle.value.getString("Control.button.toggle"),
        icon = computer(),
        ...
    ),
    presentationModel = CommandButtonPresentationModel(
      ...
    )
).project()
```

### Switching locale at runtime

The `applicationLocale` in `AuroraLocaleScope` is a `var`, which means that you can wire your application logic not only to retrieve localized strings for your UI, but also to change locale at runtime and have all relevant pieces recompose to reflect the newly set locale.

Here is sample code on how this can be achieved. We first start with two [commands](../component/Command.md), one to set locale to `en_US` (English) and one to set locale to `iw_IL` (Hebrew):

```kotlin
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
```

Now we create a command with a [secondary content model](../component/CommandPopupMenu.md) and [project it](../component/CommandProjections.md), which ends up emitting what is otherwise known as a popup button:

```kotlin
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
```

Here is how this popup looks like:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/window/locale-switcher.png" width="132" border=0>

If your resource bundle is constructed as a `derivedStateOf` using the `applicationLocale` from the `AuroraApplicationScope`, the `action` lambda in each one of these two commands that changes the value of `applicationLocale` will also end up recomposing your UI with strings fetched from the new resource bundle. In addition, Aurora will also apply the matching layout direction (left-to-right or right-to-left) based on the new application locale.
