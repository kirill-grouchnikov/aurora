## Aurora theming - decoration painters

Menu bars, tool bars, status bars - these are common examples of special containers found in application windows. These containers create functional grouping of application controls and bring order to complex screens. Aurora skinning layer provides a flexible and powerful set of APIs to define the visual appearance of these control groups - called **decoration areas**. At the same time, Aurora decoration painters enforce visual consistency and connections across the components in related areas and states. In addition, Aurora provides a set of published APIs for applications that wish to skin custom composables and preserve visual consistency with the core Aurora composables.

### Decoration areas

The `DecorationAreaType` class contains the available core decoration area types. As a picture is worth a thousand words, the following screenshots illustrate the different decoration area types.

The following screenshot is the main Aurora test application under the Gemini skin (click to see full size version):

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/full.png" width="766"/></a>

The next screenshot shows the `title` decoration area, which in this example includes the title pane of the main window:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-title.png" width="766"/>

The next screenshot shows the `header` decoration area, which in this example includes the menu bar of the main window:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-header.png" width="766"/>

The next screenshot shows the `toolbar` decoration area, which in this example includes the tool bar of the main window:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-toolbar.png" width="766"/>

The next screenshot shows the `footer` decoration area, which in this example includes the status bar component:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-footer.png" width="766"/>

The next screenshot shows the `control pane` decoration area, which in this example includes the task pane container component:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-control-pane.png" width="766"/>

Let's go back to the original screenshot:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/full.png" width="766"/>

It illustrates that the specific skin assigns different color tokens to different decoration areas. The controls in those areas get the background and foreground colors based on their assigned color tokens (without any custom application code), thus creating visual distinction between different application areas.

## Inlay painters
Inlay painters provide the functionality of watermarks - a layer that is drawn above surfaces (background fills) but below content (texts, icons, etc).

This is how a custom inlay looks like under the Blueprint skin:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/inlays.png" />

This skin emulates the visuals of construction blueprints that are drawn across all surfaces. The custom inlay painter is set on the skin's decoration painter:

```kotlin
val decorationPainter = BlueprintDecorationPainter()
decorationPainter.inlayPainter = BlueprintDecorationInlayPainter()
```

## Overlay painters

Overlay painters add the final polish that usually affects relatively small areas at the edges of the relevant decoration areas.

Overlays are best illustrated with screenshots. The following screenshot is a skeleton window under the [Nebula Brick Wall](../skins/toneddown.md#nebula-brick-wall) skin:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/overlay/nebulabrickwall.png" width="646"/>

This skin defines custom visual appearance for the title pane, the menu bar and the status bar - the background of these areas is painted by the matching decoration painter - in this case, the `MarbleNoiseDecorationPainter`. To add the final polishing touch and create a unique visual footprint for this skin, we use a number of overlay painters. The Nebula Brick Wall skin defines two separate overlay painters, each one associated with the relevant decoration areas:

```kotlin
// add an overlay painter to paint a drop shadow along the top edge of toolbars
decorationPainter.addOverlayPainter(
    TopShadowOverlayPainter.getInstance(60),
    DecorationAreaType.Toolbar
)

// add an overlay painter to paint separator lines along the bottom
// edges of title panes and menu bars
decorationPainter.addOverlayPainter(
    BottomLineOverlayPainter( { it.markerOnContainer.withAlpha(0.5f) } ),
    DecorationAreaType.TitlePane, DecorationAreaType.Header
)
```

* The `TopShadowOverlayPainter` is associated with the `Toolbar` decoration area - adding the drop shadow along the top edge of all application toolbars (see the bottom half of the zoomed area in the screenshot above).
* The `BottomLineOverlayPainter` is associated with `TitlePane` and `Header` decoration areas - adding a thin separator line along the bottom edge of the title pane and the menubar (see the top half of the zoomed area in the screenshot above). Note that the application needs to specify what color is used to paint the separator line - using the `(ContainerColorTokens) -> Color` lambda and an optional list of color transformations (in this case, applying alpha of 50%).

Here is the same skeleton window under the [Gemini](../skins/toneddown.md#gemini) skin:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/overlay/gemini.png" width="646"/>

This skin defines custom visual appearance for the title pane, the menu bar, the toolbars and the status bar - the background of these areas is painted by the matching [decoration painter](decoration.md) - in this case, the `MatteDecorationPainter`. To add the final polishing touch and create a unique visual footprint for this skin, we use overlay painters. The Gemini skin defines a number of overlay painters, each one associated with the relevant decoration areas:

```kotlin
// add an overlay painter to paint a bezel line along the top
// edge of footer
decorationPainter.addOverlayPainter(
    TopBezelOverlayPainter(
        colorTokensQueryTop = ContainerColorTokens::complementaryMarkerOnContainer,
        colorTokensQueryBottom = ContainerColorTokens::markerOnContainer,
    ),
    DecorationAreaType.Footer
)

// add two overlay painters to create a bezel line between
// menu bar and toolbars
decorationPainter.addOverlayPainter(
    BottomLineOverlayPainter(ContainerColorTokens::complementaryMarkerOnContainer),
    DecorationAreaType.Header
)
decorationPainter.addOverlayPainter(
    TopLineOverlayPainter( { it.markerOnContainer.withAlpha(0.5f) }),
    DecorationAreaType.Toolbar
)

// add overlay painter to paint drop shadows along the bottom
// edges of toolbars
decorationPainter.addOverlayPainter(
    BottomShadowOverlayPainter.getInstance(100),
    DecorationAreaType.Toolbar
)

// add overlay painter to paint a dark line along the bottom
// edge of toolbars
decorationPainter.addOverlayPainter(
    BottomLineOverlayPainter(colorTokensQuery = ContainerColorTokens::complementaryMarkerOnContainer),
    DecorationAreaType.Toolbar
)
```

This skin shows two different ways to add double-line bezel separators - the first between the menu bar and tool bar, and the second between the main application area and the footer:

* The double separator along the top edge of the footer (status bar) is painted by an instance of `TopBezelOverlayPainter` which is associated with the footer decoration area - see the bottom zoomed area in the screenshot above.
* The double separator between the menu bar and the tool bar is painted by two different overlay painters - see the top zoomed area in the screenshot above:
  * An instance of `BottomLineOverlayPainter` associated with header decoration area - paints the top (darker) separator line along the bottom edge of the menu bar.
  * An instance of `TopLineOverlayPainter` associated with toolbar decoration area - paints the bottom (lighter) separator line along the top edge of the tool bar.

The last example comes from the [Twilight](../skins/dark.md#twilight) skin:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/overlay/twilight.png" width="646"/>

This skin defines custom visual appearance for the title pane, the menu bar, the toolbars and the status bar - the background of these areas is painted by the matching decoration painter - in this case, the `MatteDecorationPainter`. To add the final polishing touch and create a unique visual footprint for this skin, we use overlay painters. The Twilight skin defines a number of overlay painters, each one associated with the relevant decoration areas:

```kotlin
// Add overlay painters to paint drop shadows along the bottom
// edges of toolbars and footers
decorationPainter.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Toolbar)
decorationPainter.addOverlayPainter(BottomShadowOverlayPainter.getInstance(100), DecorationAreaType.Footer)

// add an overlay painter to paint a dark line along the bottom
// edge of toolbars
decorationPainter.addOverlayPainter(
    BottomLineOverlayPainter( { it.containerOutlineVariant } ),
    DecorationAreaType.Toolbar
)

// add an overlay painter to paint a light line along the top
// edge of toolbars
decorationPainter.addOverlayPainter(
    TopLineOverlayPainter( { it.complementaryMarkerOnContainer.withAlpha(0.5f) } ),
    DecorationAreaType.Toolbar
)

// add an overlay painter to paint a bezel line along the top
// edge of footer
decorationPainter.addOverlayPainter(
    TopBezelOverlayPainter(
        colorTokensQueryTop = ContainerColorTokens::markerOnContainer,
        colorTokensQueryBottom = ContainerColorTokens::complementaryMarkerOnContainer,
    ), DecorationAreaType.Footer
)
```

The overlay painters used in the Twilight skin are:

* An instance of `TopLineOverlayPainter` associated with `toolbar` decoration area - paints the lighter top separator line along the top edge of the tool bar - see the top zoomed area in the screenshot above.
* An instance of `BottomLineOverlayPainter` associated with `toolbar` decoration area - paints the darker bottom separator line along the bottom edge of the tool bar - see the top zoomed area in the screenshot above.
* An instance of `TopBezelOverlayPainter` associated with `footer` decoration area - paints the double bezel separator lines along the top edge of the status bar - see the bottom zoomed area in the screenshot above.
* The instance of `BottomShadowOverlayPainter` associated with `toolbar` and `footer` decoration areas - paints the drop shadow along the bottom edge of these areas - see the top zoomed area in the screenshot above.

### Application-facing APIs

To use the current **decoration painter** in custom painting routines of your application, call the following published Aurora APIs:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.decorationPainter` to retrieve the decoration painter of the current skin.
* `AuroraSkin.decorationAreaType` to retrieve the decoration area type of the composable.
* `AuroraDecorationPainter.paintDecorationArea()` to paint the decoration background on the specific draw scope.

The base class for Aurora decoration painters is `AuroraDecorationPainter`. It has the following method:

```kotlin
fun paintDecorationArea(
    drawScope: DrawScope,
    decorationAreaType: DecorationAreaType,
    componentSize: Size,
    outline: Outline,
    rootSize: Size,
    offsetFromRoot: Offset,
    colorTokens: ContainerColorTokens
)
```

The `outline` parameter specifies the outline to paint, the `colorTokens` specifies the color tokens to be used to compute the colors, while `decorationAreaType` indicates the decoration area type.

To use the current **inlay painter** in custom painting routines of your application, call the following published Aurora APIs:

* `AuroraSkin.decorationAreaType` to retrieve the decoration area type of the composable.
* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.decorationPainter` to retrieve the decoration painter of the skin, followed by `AuroraDecorationPainter.inlayPainter` to get the inlay painter.
* If the inlay painter is not null, user the `AuroraDecorationPainter.InlayPainter.paintInlay()` (see below) to paint the overlays on the specific draw scope.

The base class for Aurora inlay painters is `AuroraDecorationPainter.InlayPainter`. The only painting method in this class is:

```kotlin
fun paintOverlay(
    drawScope: DrawScope,
    decorationAreaType: DecorationAreaType,
    offsetFromRoot: Offset,
    width: Float,
    height: Float,
    colorTokens: ContainerColorTokens
)
```

The `offsetFromRoot`, `width` and `height` parameters specify the rectangle for the inlays (the inlay painters can only paint on rectangular areas), the `colorTokens` specifies the tokens to be used to paint the inlay, while `decorationAreaType` indicates the decoration area type.

To use the current **overlay painters** in custom painting routines of your application, call the following published Aurora APIs:

* `AuroraSkin.decorationAreaType` to retrieve the decoration area type of the composable.
* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.decorationPainter` to retrieve the decoration painter of the skin, followed by `AuroraDecorationPainter.getOverlayPainters*()` to get the list of overlay painters.
* Loop over the overlay painters and use the `AuroraDecorationPainter.OverlayPainter.paintOverlay()` (see below) to paint the overlays on the specific draw scope.

The base class for Aurora overlay painters is `AuroraDecorationPainter.OverlayPainter`. The only painting method in this class is:

```kotlin
fun paintOverlay(
    drawScope: DrawScope,
    decorationAreaType: DecorationAreaType,
    width: Float,
    height: Float,
    colorTokens: ContainerColorTokens
)
```

The `width` and `height` parameters specify the rectangle for the overlays (the overlay painters can only paint on rectangular areas), the `colorTokens` specifies the tokens to be used to paint the overlay, while `decorationAreaType` indicates the decoration area type.
