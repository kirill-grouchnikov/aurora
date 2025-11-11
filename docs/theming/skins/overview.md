## Aurora theming - skins

### Introduction

A **skin** is a set of visual settings that gives your application a polished and consistent look. Aurora Theming module bundles a number of predefined skins that can be broadly categorized as light and dark.

[Light skins](toneddown.md) use predominantly light colors for painting the UI controls and containers. [Business skin](toneddown.md#business) is an example of a light skin:

<p>
<img alt="Business" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/business.png" width="350" height="280">
</p>

[Dark skins](dark.md) use predominantly dark colors for painting the UI controls and containers. [Graphite Chalk](dark.md#graphite-chalk) skin is an example of a dark skin:

<p>
<img alt="GraphiteChalk" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/graphitechalk.png" width="350" height="280">
</p>

The core Aurora skins are in the `org.pushingpixels.aurora.theming` package, and the `AuroraSkinDefinition` class contains all the APIs officially supported by Aurora skins. It is possible to use different Aurora skins on different `AuroraWindow`s in the same application.

### Skin definition

The skin definition consists of the following:

* Decoration areas and color token bundles:
  * List of supported [decoration areas](../painters/decoration.md).
  * [Color tokens bundles](colortokensbundles.md) for the supported decoration areas.
  * Optional background [color tokens](colortokens.md) for the supported decoration areas.
* Painters:
  * [Surface painter](../painters/surface.md).
  * [Outline painter](../painters/outline.md).
  * [Highlight painters](../painters/highlight.md)
  * [Decoration painter](../painters/decoration.md).
* Miscellaneous:
  * Button shaper.
  * Optional [overlay painters](../painters/overlay.md) for some decoration areas.

In order to define a valid skin, you need to specify all its mandatory parameters. A valid skin must have a color tokens bundle for `DecorationAreaType.None`, a button shaper, a surface painter, a decoration painter, a highlight painter and an outline painter. All other parts are optional.

### Decoration areas

The documentation on [decoration painters](../painters/decoration.md) explains the notion of a decoration area type. While a valid skin must define a color tokens bundle for `DecorationAreaType.None`, all other decoration area types are optional. Different skins have different sets of decoration areas that are painted. For example, the [Moderate skin](toneddown.md#moderate) decorates `DecorationAreaType.TitlePane` and `DecorationAreaType.Header`, while the [Nebula Amethyst skin](toneddown.md#nebula-amethyst) also decorates `DecorationAreaType.Toolbar`:

<p>
<img alt="Moderate" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/moderate.png" width="350" height="280">
<img alt="Nebula Amethyst" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebulaamethyst.png" width="350" height="280">
</p>

In order to register a custom color tokens bundle on the specific decoration area type(s), use the following API:

```kotlin
/**
 * Registers the specified color tokens bundle to be used on controls in
 * decoration areas.
 *
 * @param bundle    The color tokens bundle to use on controls in decoration
 * areas.
 * @param areaTypes Enumerates the area types that are affected by the parameters.
 */
fun registerDecorationAreaTokensBundle(
    bundle: ContainerColorTokensBundle, vararg areaTypes: DecorationAreaType
) {
```

Decoration areas registered with these APIs will have their background painted by the skin's [decoration painter](../painters/decoration.md) based on the default color tokens of the registered color tokens bundle. You can also use the following API to use custom default color tokens on the specified decoration area types (in this case the controls in those decoration areas will use the default color tokens bundle):

```kotlin
/**
 * Registers the specified neutral color tokens to be used on controls in
 * decoration areas.
 *
 * @param neutralContainerTokens The neutral tokens to use in specified decoration areas.
 * @param areaTypes             Enumerates the area types that are affected by the parameters.
 * Each decoration area type will be painted by
 * [RadianceDecorationPainter.paintDecorationArea]
 */
fun registerAsDecorationArea(
    neutralContainerTokens: ContainerColorTokens,
    vararg areaTypes: DecorationAreaType
)
```

Here is an example of specifying the default color tokens bundle for the [Mariner skin](toneddown.md#mariner):

```kotlin
val result = AuroraSkinColors()

val marinerDefaultBundle = ContainerColorTokensBundle(
    activeContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFF6DD9Du.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
    mutedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFD9D8D5u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
    neutralContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFECF0F3u.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
    isSystemDark = false)

...

result.registerDecorationAreaTokensBundle(marinerDefaultBundle, DecorationAreaType.None)
```
and a custom color tokens bundle for the `header`-type decoration areas:

```java
val marinerHeaderBundle = ContainerColorTokensBundle(
    activeContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFF5D47Au.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ true,
            /* contrastLevel */ 0.8)),
    mutedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF281D1Eu.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ true,
            /* contrastLevel */ 0.8)),
    neutralContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFF261D1Eu.toInt()),
        containerConfiguration = ContainerConfiguration(
            /* isDark */ true,
            /* contrastLevel */ 1.0),
        colorResolver = DefaultPaletteColorResolver.overlayWith(
            TokenPaletteColorResolverOverlay(
                containerOutline = { it.containerOutlineVariant }
            )
        )
    ),
    isSystemDark = true)

...

result.registerDecorationAreaTokensBundle(marinerHeaderBundle,
    DecorationAreaType.TitlePane, DecorationAreaType.Header)
```

And here is an example of specifying a number of decoration area types to have their background painted by the decoration painter and the specific color tokens, without registering a custom color tokens bundle for those areas:

```kotlin
result.registerAsDecorationArea(
    neutralContainerColorTokens,
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
    BottomLineOverlayPainter( { it.containerOutline } ),
    DecorationAreaType.TitlePane, DecorationAreaType.Header
)
```

and here is how it looks like:

<img alt="Nebula" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebula.png" width="350" height="280">

### Additional settings

`AuroraSkinDefinition` groups its structure into three major categories: colors, painters and button shaper. Here is sample code from [Mariner skin](toneddown.md#mariner):

```kotlin
fun marinerSkin(): AuroraSkinDefinition {
  val painters = AuroraPainters(
      decorationPainter = MatteDecorationPainter(),
      surfacePainter = FractionBasedSurfacePainter(
          ColorStop(fraction = 0.0f, colorQuery = {
              if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLowest
          }),
          ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
          ColorStop(fraction = 1.0f, colorQuery = {
              if (it.isDark) it.containerSurfaceLow else it.containerSurfaceHigh
          }),
          displayName = "Mariner"
      ),
      highlightSurfacePainter = FractionBasedSurfacePainter(
          ColorStop(fraction = 0.0f, colorQuery = {
              if (it.isDark) it.containerSurfaceLow else it.containerSurfaceHigh
          }),
          ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerSurface),
          ColorStop(fraction = 1.0f, colorQuery = {
              if (it.isDark) it.containerSurfaceHigh else it.containerSurfaceLow
          }),
          displayName = "Mariner Highlight"
      ),
      outlinePainter = InlayOutlinePainter(
          displayName = "Mariner",
          outer = OutlineSpec(colorQuery = ContainerColorTokens::containerOutline),
          inner = OutlineSpec(
              ColorStop(fraction = 0.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
              ColorStop(fraction = 1.0f, alpha = 0.25f, colorQuery = ContainerColorTokens::complementaryContainerOutline),
          )
      ),
      highlightOutlinePainter = FlatOutlinePainter(),
  )

  // add an overlay painter to paint a bezel line along the top
  // edge of footer
  painters.addOverlayPainter(
      TopBezelOverlayPainter(
          colorTokensQueryTop = { it.containerOutline.withAlpha(0.3125f) },
          colorTokensQueryBottom = { it.inverseContainerOutline.withAlpha(0.1875f) }
      ),
      DecorationAreaType.Footer
  )

  // add two overlay painters to create a bezel line between
  // menu bar and toolbars
  painters.addOverlayPainter(
      BottomLineOverlayPainter( { it.containerSurfaceHighest } ),
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
      BottomLineOverlayPainter(colorTokensQuery = { it.containerOutline.withAlpha(0.5f) }),
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

Aurora provides a fine-grained mechanism for creating related skin variations by using **accented skins**. This can be done by using the `AccentContainerColorTokens` APIs.

Such skins "declare" themselves to support one particular, narrowly scoped kind of derivation - providing up to 9 [color tokens](colortokens.md) as accents. It is up to a skin that declares itself as accented to "decide" how to apply those accent color tokens.

For example, here are two `Creme` skins that extend the core `CremeAccentedSkin` class:

<p>
<img alt="Creme" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/creme.png" width="350" height="280">
<img alt="Creme Coffee" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/cremecoffee.png" width="350" height="280">
</p>

The first passes light blue color tokens as the accent for active controls and cell highlights, while the second passes light brown tokens as the accent for the same parts of the UI. This particular accented skin family uses these two accent types for selected tabs, checkboxes, radio buttons, default buttons, scroll bars and active cells in tables, trees, and lists.

As another example, here are two `Nebula` skins that extend the core `NebulaAccentedSkin` class:

<p>
<img alt="Nebula" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebula.png" width="350" height="280">
<img alt="Nebula Brick Wall" src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebulabrickwall.png" width="350" height="280">
</p>

The first passes light silver color tokens as the window chrome accent, while the second passes orange tokens as the window chrome accent. This particular accented skin family uses the window chrome accent on the root pane border, the title pane and the menu bar - while maintaining the overall consistency of its visual "language", such as decoration painter, surface painter, color tokens for active controls in the main UI area, etc.

### Sample code to work with Aurora skins

The following class implements a custom combobox that lists all available Aurora skins and allows changing the current Aurora skin based on the user selection.

```kotlin
@Composable
fun AuroraSkinSwitcher(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
) {
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
                onSkinChange.invoke(it.second.invoke())
            }
        ),
        presentationModel = ComboBoxPresentationModel(
            displayConverter = { it.first },
            popupPlacementStrategy = popupPlacementStrategy
        )
    ).project()
}
```

* First, it uses the `getAuroraSkins()` API to populate the combobox with the list of all available Aurora skins.
* Then, it uses the current `AuroraSkinDefinition` to select the combobox entry that matches the current Aurora skin.
* Since the model entries behind the combobox are `Pair<String, () -> AuroraSkinDefinition` objects, we pass a `displayConverter` that uses the display name of the skin.
* Finally, our `onTriggerItemSelectedChange` calls the passed lambda. The purpose of the lambda is to allow the higher level configuration to update the current skin. This responsibility rests with the application code - to have a mutable state variable that tracks the currently set skin, and to update that mutable state in this lambda passed to the sample `AuroraSkinSwitcher`.

The same approach can be used to create a menu selection of available Aurora skins.
