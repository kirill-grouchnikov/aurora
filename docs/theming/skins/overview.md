## Aurora theming - skins

### Introduction

A **skin** is a set of visual settings that gives your application a polished and consistent look. Aurora Theming module bundles a number of predefined skins that can be broadly categorized as light and dark.

[Light skins](toneddown.md) use predominantly light colors for painting the UI controls and containers. [Business skin](toneddown.md#business) is an example of a light skin:

<p>
<img alt="Business" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/business.png" width="340" height="280">
</p>

[Dark skins](dark.md) use predominantly dark colors for painting the UI controls and containers. [Graphite Chalk](dark.md#graphite-chalk) skin is an example of a dark skin:

<p>
<img alt="GraphiteChalk" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/graphitechalk.png" width="340" height="280">
</p>

The core Aurora skins are in the `org.pushingpixels.aurora.theming` package, and the `AuroraSkinDefinition` class contains all the APIs officially supported by Aurora skins. It is possible to use different Aurora skins on different `AuroraWindow`s in the same application.

### Skin definition

The skin definition consists of the following:

* Decoration areas and color scheme bundles:
  * List of supported [decoration areas](../painters/decoration.md).
  * [Color scheme bundles](colorschemebundles.md) for the supported decoration areas.
  * Optional background [color schemes](colorschemes.md) for the supported decoration areas.
* Painters:
  * [Fill painter](../painters/fill.md).
  * [Border painter](../painters/border.md).
  * [Decoration painter](../painters/decoration.md).
* Miscellaneous:
  * Button shaper.
  * Optional [overlay painters](../painters/overlay.md) for some decoration areas.

In order to define a valid skin, you need to specify all its mandatory parameters. A valid skin must have a color scheme bundle for `DecorationAreaType.None`, a button shaper, a fill painter, a decoration painter, a highlight painter and a border painter. All other parts are optional.

### Decoration areas

The documentation on [decoration painters](../painters/decoration.md) explains the notion of a decoration area type. While a valid skin must define a color scheme bundle for `DecorationAreaType.None`, all other decoration area types are optional. Different skins have different sets of decoration areas that are painted. For example, the [Moderate skin](toneddown.md#moderate) decorates `DecorationAreaType.TitlePane` and `DecorationAreaType.Header`, while the [Nebula Amethyst skin](toneddown.md#nebula-amethyst) also decorates `DecorationAreaType.Toolbar`:

<p>
<img alt="Moderate" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/moderate.png" width="340" height="280">
<img alt="Nebula Amethyst" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebulaamethyst.png" width="340" height="280">
</p>

In order to register a custom color scheme bundle and an optional background color scheme on the specific decoration area type(s), use the following APIs:

```kotlin
  /**
   * Registers the specified color scheme bundle to be used on controls in
   * decoration areas.
   *
   * @param bundle
   *     The color scheme bundle to use on controls in decoration areas.
   * @param areaTypes
   *     Enumerates the area types that are affected by the parameters.
   */
   fun registerDecorationAreaSchemeBundle(
         bundle: AuroraColorSchemeBundle,
         vararg areaTypes: DecorationAreaType
   )

  /**
   * Registers the specified color scheme bundle and background color scheme
   * to be used on controls in decoration areas.
   *
   * @param bundle
   *     The color scheme bundle to use on controls in decoration areas.
   * @param backgroundColorScheme
   *     The color scheme to use for background of controls in decoration
   *     areas.
   * @param areaTypes
   *     Enumerates the area types that are affected by the parameters.
   */
   fun registerDecorationAreaSchemeBundle(
         bundle: AuroraColorSchemeBundle,
         backgroundColorScheme: AuroraColorScheme,
         vararg areaTypes: DecorationAreaType
   )
```

Decoration areas registered with these APIs will have their background painted by the skin's [decoration painter](../painters/decoration.md) based on the default color scheme of the registered color scheme bundle. You can also use the following API to use a custom default color scheme on the specified decoration area types (in this case the controls in those decoration areas will use the default color scheme bundle):

```kotlin
  /**
   * Registers the specified background color scheme to be used on controls in
   * decoration areas.
   *
   * @param backgroundColorScheme
   *     The color scheme to use for background of controls in decoration
   *     areas.
   * @param areaTypes
   *     Enumerates the area types that are affected by the parameters. Each
   *     decoration area type will be painted by [AuroraDecorationPainter.paintDecorationArea].
   */
   fun registerAsDecorationArea(
         backgroundColorScheme: AuroraColorScheme,
         vararg areaTypes: DecorationAreaType
   )
```

Here is an example of specifying the default color scheme bundle for the [Mariner skin](toneddown.md#mariner):

```kotlin
val result = AuroraSkinColors()
val schemes = getColorSchemes(
    AuroraSkin::class.java.getResourceAsStream(
        "/org/pushingpixels/aurora/theming/mariner.colorschemes"
    )
)

val activeScheme = schemes["Mariner Active"]
val enabledScheme = schemes["Mariner Enabled"]
val disabledScheme = schemes["Mariner Disabled"]

val disabledSelectedScheme = schemes["Mariner Disabled Selected"]

val defaultSchemeBundle = AuroraColorSchemeBundle(
    activeScheme, enabledScheme, disabledScheme
)

defaultSchemeBundle.registerAlpha(0.8f, ComponentState.DisabledSelected)
defaultSchemeBundle.registerAlpha(0.8f, ComponentState.DisabledUnselected)
defaultSchemeBundle.registerColorScheme(
    disabledSelectedScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.DisabledSelected
)
defaultSchemeBundle.registerColorScheme(
    disabledScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.DisabledUnselected
)

...

result.registerDecorationAreaSchemeBundle(defaultSchemeBundle, DecorationAreaType.None)
```
and a custom color scheme bundle for the `header`-type decoration areas:

```java
val headerColorScheme = schemes["Mariner Header"]
val headerBorderColorScheme = schemes["Mariner Header Border"]
val headerSchemeBundle = AuroraColorSchemeBundle(
    headerColorScheme, headerColorScheme, headerColorScheme
)
headerSchemeBundle.registerAlpha(0.4f, ComponentState.DisabledSelected, ComponentState.DisabledUnselected)
headerSchemeBundle.registerColorScheme(
    headerColorScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.DisabledSelected, ComponentState.DisabledUnselected
)

...

result.registerDecorationAreaSchemeBundle(
        headerSchemeBundle, headerColorScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header
)
```

And here is an example of specifying a number of decoration area types to have their background painted by the decoration painter and the specific color scheme, without registering a custom color scheme bundle for those areas:

```kotlin
result.registerAsDecorationArea(
        enabledScheme,
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header,
        DecorationAreaType.Footer,
        DecorationAreaType.Toolbar
)
```

### Overlays        
To add polishing touches to the specific decoration areas, use [overlay painters](../painters/overlay.md) with the following API on `AuroraPainters`:

```kotlin
/**
 * Adds the specified overlay painter to the end of the list of overlay
 * painters associated with the specified decoration area types.
 *
 * @param overlayPainter
 *            Overlay painter to add to the end of the list of overlay
 *            painters associated with the specified decoration area types.
 * @param areaTypes
 *            Decoration area types.
 */
 fun addOverlayPainter(
       overlayPainter: AuroraOverlayPainter,
       vararg areaTypes: DecorationAreaType
 )
```

Here is how the [Nebula skin](toneddown.md#nebula) is configured to paint drop shadows on the toolbars and separators on title panes and headers:

```kotlin
// add an overlay painter to paint a drop shadow along the top edge of toolbars
painters.addOverlayPainter(
    TopShadowOverlayPainter.getInstance(60),
    DecorationAreaType.Toolbar
)

// add an overlay painter to paint separator lines along the bottom
// edges of title panes and menu bars
painters.addOverlayPainter(
    BottomLineOverlayPainter(
        composite({ it.darkColor }, ColorTransforms.alpha(0.625f))
    ),
    DecorationAreaType.TitlePane, DecorationAreaType.Header
)
```

and here is how it looks like:

<img alt="Nebula" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebula.png" width="340" height="280">

### Additional settings

`AuroraSkinDefinition` groups its structure into three major categories: colors, painters and button shaper. Here is sample code from [Mariner skin](toneddown.md#mariner):

```kotlin
fun marinerSkin(): AuroraSkinDefinition {
    val painters = AuroraPainters(
        fillPainter = FractionBasedFillPainter(
            0.0f to { it.extraLightColor },
            0.5f to { it.lightColor },
            1.0f to { it.midColor },
            displayName = "Mariner"
        ),
        borderPainter = FractionBasedBorderPainter(
            0.0f to { it.ultraDarkColor },
            0.5f to { it.darkColor },
            1.0f to { it.midColor },
            displayName = "Mariner"
        ),
        decorationPainter = MatteDecorationPainter()
    )

    // add an overlay painter to paint a bezel line along the top
    // edge of footer
    painters.addOverlayPainter(
        TopBezelOverlayPainter(
            colorSchemeQueryTop = { it.ultraDarkColor },
            colorSchemeQueryBottom = { it.lightColor }
        ),
        DecorationAreaType.Footer
    )

    // add two overlay painters to create a bezel line between
    // menu bar and toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(
            composite({ it.ultraDarkColor }, ColorTransforms.brightness(0.5f))
        ),
        DecorationAreaType.Header
    )

    // add overlay painter to paint drop shadows along the bottom
    // edges of toolbars
    painters.addOverlayPainter(
        BottomShadowOverlayPainter.getInstance(100),
        DecorationAreaType.Toolbar
    )

    // add overlay painter to paint a dark line along the bottom
    // edge of toolbars
    painters.addOverlayPainter(
        BottomLineOverlayPainter(colorSchemeQuery = { it.ultraDarkColor }),
        DecorationAreaType.Toolbar
    )

    return AuroraSkinDefinition(
        displayName = "Mariner",
        colors = marinerSkinColors(),
        painters = painters,
        buttonShaper = ClassicButtonShaper()
    )
}
```

### Accented skins

Aurora provides a fine-grained mechanism for creating related skin variations by using **accented skins**. This can be done by using the `AccentBuilder` APIs.

Such skins "declare" themselves to support one particular, narrowly scoped kind of derivation - providing up to five [color schemes](colorschemes.md) as accents. It is up to a skin that declares itself as accented to "decide" how to apply those accent colors.

For example, here are two `Creme` skins that extend the core `CremeAccentedSkin` class:

<p>
<img alt="Creme" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/creme.png" width="340" height="280">
<img alt="Creme Coffee" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/cremecoffee.png" width="340" height="280">
</p>

The first passes a light blue color scheme as the accent for active controls and cell highlights, while the second passes a light brown scheme as the accent for the same parts of the UI. This particular accented skin family uses these two accent types for selected tabs, checkboxes, radio buttons, default buttons, scroll bars and active cells in tables, trees, and lists.

As another example, here are two `Nebula` skins that extend the core `NebulaAccentedSkin` class:

<p>
<img alt="Nebula" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebula.png" width="340" height="280">
<img alt="Nebula Brick Wall" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebulabrickwall.png" width="340" height="280">
</p>

The first passes a light silver scheme as the window chrome accent, while the second passes an orange scheme as the window chrome accent. This particular accented skin family uses the window chrome accent on the root pane border, the title pane and the menu bar - while maintaining the overall consistency of its visual "language", such as decoration painter, fill painter, color scheme for active controls in the main UI area, etc.

### Sample code to work with Aurora skins

The following class implements a custom combobox that lists all available Aurora skins and allows changing the current Aurora skin based on the user selection.

```kotlin
@Composable
fun AuroraSkinSwitcher(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    val currentSkinDisplayName = AuroraSkin.displayName
    val auroraSkins = getAuroraSkins()
    val selectedSkinItem =
        remember { mutableStateOf(auroraSkins.first { it.first == currentSkinDisplayName }) }

    ComboBoxProjection(
        contentModel = ComboBoxContentModel(
            items = auroraSkins,
            selectedItem = selectedSkinItem.value,
            onTriggerItemSelectedChange = {
                selectedSkinItem.value = it
                auroraSkinDefinition.value = it.second.invoke()
            }
        ),
        presentationModel = ComboBoxPresentationModel(
            displayConverter = { it.first }
        )
    ).project()
}
```

* First, it uses the `getAuroraSkins()` API to populate the combobox with the list of all available Aurora skins.
* Then, it uses the current `AuroraSkinDefinition` to select the combobox entry that matches the current Aurora skin.
* Since the model entries behind the combobox are `Pair<String, () -> AuroraSkinDefinition` objects, we pass a `displayConverter` that uses the display name of the skin.
* Finally, our `onTriggerItemSelectedChange` updates the mutable state that was passed into our composable function, and if that mutable state is also used at the higher level of the app stack to configure one or more `AuroraWindow`s, those will get recomposed with the newly selected Aurora skin.

The same approach can be used to create a menu selection of available Aurora skins.
