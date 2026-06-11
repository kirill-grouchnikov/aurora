## Aurora theming - window decorators

The base interface for Aurora window decorators is `AuroraWindowDecorator`. The window decorator from the current skin is responsible for the visuals of "chrome" part of decorated windows.

This method is used to compute the window insets:

```kotlin
fun getWindowBorderInsets(): Dp
```

This method is used to paint the visuals of the decorated root pane border:

```kotlin
fun paintWindowBorder(
    drawScope: DrawScope,
    size: Size,
    colorTokens: ContainerColorTokens,
)
```

The `size` parameter defines the size of the window, while the `colorTokens` specifies the color tokens to be used to paint the border visuals.

Aurora provides a default implementation of this interface in `DefaultWindowDecorator`. Skins that target more custom visuals can either implement the `AuroraWindowDecorator` interface from scratch, or extend the `DefaultWindowDecorator` class.

The core Blueprint skin is an example of a custom window decorator that paints grid hashmarks along the border edges:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/decorators/window.png" />

### Management API

If you wish to use the window decorator of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.decorators` to retrieve the decorators associated with the current skin.
* `AuroraDecorators.windowDecorator` to retrieve the window decorator of the current skin.
* Call the relevant method(s) on the `AuroraWindowDecorator`.
