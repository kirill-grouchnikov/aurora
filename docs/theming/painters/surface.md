## Aurora theming - surface painters

The only painting method in `AuroraSurfacePainter` is

```kotlin
fun paintSurface(
    drawScope: DrawScope,
    size: Size,
    outline: Outline,
    colorTokens: ContainerColorTokens,
    alpha: Float,
)
```

The `outline` parameter specifies the outline to fill and the `colorTokens` specifies the color tokens to be used to compute the colors.

Important thing to note - a surface painter **should not** paint the focus ring or the outline; these are painted by separate painters.

Applications that wish to provide a custom (branding) surface painter may utilize the existing `FractionBasedSurfacePainter` base class. Most core Aurora surface painters extend this class.

### Management API

If you wish to use the surface painter of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.surfacePainter` to retrieve the surface painter of the current skin.
* `AuroraSurfacePainter.paintSurface()` to paint the background on the specific draw scope.
