## Aurora theming - border painters

The base class for Aurora border painters is `AuroraBorderPainter`. Border painter is used to paint the outer contour of most composables, such as buttons, check boxes, radio buttons, progress bars, tabs, scroll bars and others. This allows providing a consistent and pluggable appearance to those composables. In addition, it provides an external API for applications that wish to skin custom composables in a visually consistent manner.

The `AuroraBorderPainter` interface defines the following painting method:

```kotling
fun paintBorder(
    drawScope: DrawScope,
    size: Size,
    outline: Outline,
    outlineInner: Outline?,
    borderScheme: AuroraColorScheme,
    alpha: Float
)
```

The `outline` and `outlineInner` parameters specify the outer and inner outlines to paint, while the `borderScheme` specifies the Aurora color scheme to be used to compute the border colors. The internal implementation of a specific border painter may decide to ignore the `outlineInner` if this is not relevant.

For optimization purposes, the `AuroraBorderPainter` defines the following attribute that must be implemented by the specific border painter:

```kotlin
val isPaintingInnerOutline: Boolean
```

Note that if this attribute is `false`, the implementation of the `paintBorder()` **must** ignore the `outlineInner` parameter.

Applications that wish to provide a custom (branding) border painter may utilize the existing `FractionBasedBorderPainter` base class. Most core Aurora border painters extend this class. In addition, the `DelegateFractionBasedBorderPainter` and `CompositeBorderPainter` classes can be used to combine existing border painters and tweak the colors selected for the painting.

### Management API

If you wish to use the border painter of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.borderPainter` to retrieve the fill painter of the current skin.
* `AuroraBorderPainter.paintBorder()` to paint the border on the specific draw scope.
