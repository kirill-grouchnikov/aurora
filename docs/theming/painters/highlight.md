## Aurora theming - fill painters

The only painting method in `AuroraFillPainter` is

```kotlin
fun paintContourBackground(
    drawScope: DrawScope,
    size: Size,
    outline: Outline,
    fillScheme: AuroraColorScheme,
    alpha: Float
)
```

The `outline` parameter specifies the actual shape to fill and the `fillScheme` specifies the Aurora color scheme to be used to compute the colors.

Important thing to note - a fill painter **should not** paint the focus ring or the border; these are painted by separate painters.

Applications that wish to provide a custom (branding) fill painter may utilize the existing `StandardFillPainter` base class. The subclass can override any one of the four base functions that compute fill colors at four vertical locations (see `get*FillColor` functions). Most core Aurora fill painters extend this class.

### Management API

If you wish to use the fill painter of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.fillPainter` to retrieve the fill painter of the current skin.
* `AuroraFillPainter.paintContourBackground()` to paint the background on the specific draw scope.
