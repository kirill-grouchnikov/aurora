## Window - introduction

Note: Before starting with Aurora window APIs, make yourself comfortable with Desktop Compose [window APIs](https://github.com/JetBrains/compose-multiplatform/tree/master/tutorials/Window_API_new).

At the most basic level, Desktop Compose application skeleton looks like this:

```kotlin
fun main() = application {
    Window(...) {
        // Content
    }
}
```

Aurora application skeleton looks like this:

```kotlin
fun main() = auroraApplication {
    AuroraWindow(...) {
        // Content
    }
}
```

### Hello World in Aurora

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/helloworld.png" width="266" height="196" border=0>

``` kotlin
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(220.dp, 150.dp)
    )

    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication
    ) {
        var text by remember { mutableStateOf("Hello, World!") }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().auroraBackground()
        ) {
            CommandButtonProjection(
                contentModel = Command(
                    text = text,
                    action = { text = "Hello, Desktop!" }
                )
            ).project()
        }
    }
}
```

Continue reading about [Aurora application](Application.md) and [Aurora window](Window.md) APIs.
