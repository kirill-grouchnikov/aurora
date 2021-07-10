
# Aurora libraries

Aurora is a collection of libraries for writing modern, elegant and fast [desktop Compose](https://github.com/JetBrains/compose-jb) applications.

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/logo/auroraicon-512.png" width="256" height="256" border=0>
</p>

# Current status

Aurora is currently in pre-alpha, active early development. Most of the ideas and building blocks in Aurora come from [Radiance](https://github.com/kirill-grouchnikov/radiance). That project has been around since 2005, and it will take a bit more time to get to the more stable state where it'll be ready to be experimented with. How much more time? Probably around summer 2021.

The goal is to provide a collection of high-quality components for building modern desktop Compose applications. Aurora will bring the full power of the skinning layer from Radiance, including all the skins and complete support for state-based transitions for all the components. In addition, Aurora will provide a powerful painter API layer to create custom composable components with consistent visual appearance and state transitions.

Going a bit further into 2021, Aurora will bring a fully-fledged ribbon container into the desktop Compose world.

# Playing with SNAPSHOT builds

Aurora snapshot builds are made available on Sonatype.

Add the latest Kotlin and Compose Desktop dependencies:
```kotlin
plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.compose") version "0.5.0-build226"
}
```

Add Aurora snapshot repository to your `repositories` block:
```kotlin
maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
```

Add Aurora dependencies:

```
dependencies {
    implementation("org.pushing-pixels:aurora-skin:0.0.41-SNAPSHOT")
    implementation("org.pushing-pixels:aurora-icon-icon:0.0.41-SNAPSHOT")
    implementation("org.pushing-pixels:aurora-component:0.0.41-SNAPSHOT")
    implementation("org.pushing-pixels:aurora-window:0.0.41-SNAPSHOT")
    implementation(compose.desktop.currentOs)
}
```

Now you are ready for your first Aurora demo:

```kotlin
fun main() = AuroraWindow(
    skin = marinerSkin(),
    title = "Aurora Demo",
    size = IntSize(220, 150),
    undecorated = true
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
```

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/helloworld.png" width="266" height="196" border=0>
</p>
