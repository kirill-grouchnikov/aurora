## Aurora theming - outline painters

The base class for Aurora outline painters is `AuroraOutlinePainter`. Outline painter is used to paint the outer contour of most composables, such as buttons, check boxes, radio buttons, progress bars, tabs, scroll bars and others. This allows providing a consistent and pluggable appearance to those composables. In addition, it provides an external API for applications that wish to skin custom composables in a visually consistent manner.

The `AuroraOutlinePainter` interface defines the following painting method:

```kotlin
fun paintOutline(
    drawScope: DrawScope,
    size: Size,
    outlineSupplier: OutlineSupplier,
    colorTokens: ContainerColorTokens,
    alpha: Float
)
```

The `outlineSupplier` parameter is used to compute the outline to paint, while the `colorTokens` specifies the color tokens to be used to compute the outline colors.

Applications that wish to provide a custom (branding) outline painter may utilize the existing `FractionBasedOutlinePainter` base class. Most core Aurora outline painters extend this class. In addition, the `InlayOutlinePainter` classes can be used for more complex visuals that two nested outlines - outer and inner.

### Management API

If you wish to use the outline painter of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.outlinePainter` to retrieve the outline painter of the current skin.
* `AuroraOutlinePainter.paintOutline()` to paint the outline on the specific draw scope.
