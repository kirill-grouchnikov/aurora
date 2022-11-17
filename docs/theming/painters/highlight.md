## Aurora theming - highlight painters

The highlight painter is used to paint special highlightable areas of application content. In Aurora, the highlight painter is using the same `AuroraFillPainter` interface as the [fill painter](fill.md). The choice of when to use the highlight painter vs the fill painter is left for the application side to decide. Some applications may decide to use different visuals for selected items in lists or grids, in which case they may opt to use the highlight painter.

Aurora uses the highlight painter on its [box with highlights](../component/BoxWithHighlights.md) in the `AuroraBoxWithHighlights` composable container:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/box-with-highlights.png" width="300" border=0/>

This allows the application code to provide different highlight visuals for elements in repeatable collection-based containers such as lists and grids.

Here is another example:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/highlight/highlights.png" border=0/>

The yellow highlights in the leftmost pane and the blue highlights in the middle pane are provided by the combination of the `AuroraBoxWithHighlights` composable and a custom application skin. The custom application skin uses:

* `AuroraColorSchemeBundle.registerHighlightColorScheme` for registering yellow and blue color schemes for painting the highlight fill.
* `AuroraColorSchemeBundle.registerColorScheme` with `ColorSchemeAssociationKind.HighlightBorder` for registering a brown color scheme for painting the highlight borders in the leftmost pane.
* `AuroraPainters.highlightFillPainter` configured with a fill painter that draws a flat (no gradient) highlight appearance.


### Management API

If you wish to use the fill painter of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.highlightFillPainter` to retrieve the highlight painter of the current skin.
* `AuroraFillPainter.paintContourBackground()` to paint the background on the specific draw scope.
