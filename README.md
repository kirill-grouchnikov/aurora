
# Aurora libraries

Aurora is a collection of libraries for writing modern, elegant and fast [desktop Compose](https://github.com/JetBrains/compose-jb) applications.

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebula.png" width="340" height="258" border=0>
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/gemini.png" width="340" height="258" border=0>
</p>

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/graphitechalk.png" width="340" height="258" border=0>
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nightshade.png" width="340" height="258" border=0>
</p>

## Current status

Most of the ideas and building blocks in Aurora come from [Radiance](https://github.com/kirill-grouchnikov/radiance). Aurora is currently in beta phase of its development. 

The goal is to provide a collection of high-quality components for building modern desktop Compose applications. Aurora brings the full power of the skinning layer from Radiance, including all the skins and complete support for state-based transitions for all the components. In addition, Aurora provides a powerful painter API layer to create custom composable components with consistent visual appearance and state transitions.

Going a bit further into 2022, Aurora will bring a fully-fledged ribbon container into the desktop Compose world.

## Playing with builds

Add the latest Kotlin and Compose Desktop dependencies:
```kotlin
plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-beta6-dev464"
}
```

Add Aurora dependencies:

```
dependencies {
    implementation("org.pushing-pixels:aurora-theming:1.0.0-beta2")
    implementation("org.pushing-pixels:aurora-component:1.0.0-beta2")
    implementation("org.pushing-pixels:aurora-window:1.0.0-beta2")
    implementation(compose.desktop.currentOs)
}
```

Now you are ready for your first Aurora demo:

```kotlin
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
        undecorated = true,
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

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/helloworld.png" width="266" height="196" border=0>
</p>

## Aurora artifacts

* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-theming/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-theming)
  `aurora-theming`
* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-component/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-component)
  `aurora-component`
* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-window/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-window)
  `aurora-window`

