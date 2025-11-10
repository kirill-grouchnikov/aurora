### Window - Radiance interoperability

In addition to [core Swing interoperability](https://github.com/JetBrains/compose-multiplatform/tree/master/tutorials/Swing_Integration), Aurora supports integrating with [Radiance](https://github.com/kirill-grouchnikov/radiance).

To start, add a dependency on the latest Radiance binaries:

```kotlin
dependencies {
  ...
  implementation("org.pushing-pixels:radiance-theming:8.0.0")
}
```

Then, before creating your first `AuroraWindow`, set Radiance skin in your `auroraApplication` function:

```kotlin
RadianceThemingCortex.GlobalScope.setSkin(MarinerSkin())
```

Now, Swing content created within `SwingPanel` scope will be themed by Radiance:

```kotlin
SwingPanel(
    modifier = Modifier.fillMaxWidth().height(90.dp),
    factory = {
        JPanel().apply {
            layout = FlowLayout(FlowLayout.CENTER)
            add(swingButton("Hello, Swing!") { println("I come from Swing") })
        }
    }
)
```
