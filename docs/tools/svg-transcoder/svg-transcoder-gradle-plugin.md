## Gradle plugin for transcoding SVG content

The goal of this project is to allow build-time transcoding of SVG content into Kotlin classes by wrapping [SVG transcoder APIs](svg-transcoder.md) and making them available for Gradle builds.

### Using the plugin in your Gradle script

Add the plugin to the `buildscript` part of your `build.gradle.kts` file:

```kotlin
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:X.Y.Z'
    }
}
```

Apply the plugin:

```kotlin
plugins {
    id("org.pushing-pixels.aurora.tools.svgtranscoder.gradle")
}
```

In case you want to use the latest snapshot version of the plugin, use the Sonatype repository:

```groovy
buildscript {
    repositories {
        maven {
            url 'https://central.sonatype.com/repository/maven-snapshots/'
        }
    }
    dependencies {
        classpath 'org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:X.Y.Z-SNAPSHOT'
    }
}
```

### Configuring a source directory for transcoded files

Your SVG content is the source of truth for the transcoded icons, and should be a part of your versioned codebase. The transcoded files themselves, on the other hand, are a generated artifact and should be treated as such.

Start by choosing where to place them as a separate "source" folder which will be added to your `.gitignore`, let's say `src/gen`.

Configure your Kotlin source set to include that folder, as well as mark it for IDEA as a generated source:

```groovy
kotlin {
    sourceSets {
        kotlin {
            sourceSets["desktopMain"].apply {
                kotlin.srcDir("$rootDir/src/desktopMain/kotlin")
                kotlin.srcDir("$rootDir/src/gen/kotlin")
            }
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("$rootDir/src/gen/kotlin"))
    }
}
```

### Transcoding SVG files from a single folder

Register a separate task for generating Kotlin classes and add a `dependsOn` clause for your `KotlinCompile` tasks. Add multiple tasks if you have more than one SVG content folder.

```kotlin
tasks.register<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeTask>("transcodeSingle") {
    inputDirectory = file("src/desktopMain/resources")
    outputDirectory = file("src/gen/kotlin/org/aurora/demo/svg")
    outputPackageName = "org.aurora.demo.svg"
    transcode()
}

tasks.withType<KotlinCompile> {
    dependsOn("transcodeSingle")
}
```

Alternatively, use `doFirst` in your `KotlinCompile` tasks. Add multiple tasks if you have more than one SVG content folder.

```kotlin
tasks.withType<KotlinCompile> {
    doFirst {
        task<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeTask>("transcodeSingle") {
            inputDirectory = file("src/desktopMain/resources")
            outputDirectory = file("src/gen/kotlin/org/aurora/demo/svg2")
            outputPackageName = "org.aurora.demo.svg2"
            transcode()
        }
    }
}
```

Note that we're using our separate `src/gen` folder as the `outputDirectory`

### Recursively transcoding SVG files under a folder

Register a separate task for generating Kotlin classes and add a dependsOn clause for your KotlinCompile tasks. Add multiple tasks if you have more than one SVG content folder.

```kotlin
tasks.register<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeDeepTask>("transcodeFolder") {
    inputRootDirectory = file("src/desktopMain/resources/scalable")
    outputRootDirectory = file("src/gen/kotlin/org/aurora/demo/scalable/svg")
    outputRootPackageName = "org.aurora.demo.scalable.svg"
    transcode()
}

tasks.withType<KotlinCompile> {
    dependsOn("transcodeFolder")
}
```

Alternatively, use `doFirst` in your `KotlinCompile` tasks. Add multiple tasks if you have more than one SVG content folder.

```kotlin
tasks.withType<KotlinCompile> {
    doFirst {
        task<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeDeepTask>("transcodeFolder") {
            inputRootDirectory = file("src/desktopMain/resources")
            outputRootDirectory = file("src/gen/kotlin/org/aurora/demo/scalable/svg2")
            outputRootPackageName = "org.aurora.demo.scalable.svg2"
            transcode()
        }
    }
}
```

Note that we're using our separate `src/gen` folder as the `outputDirectory`

### Additional notes

Note that using `tasks.withType<KotlinCompile>` assumes that you have at least one "real" source file in your project so that these tasks are executed by Gradle. If you are planning to use the plugin in a module that will have only SVG content and the transcoded classes, you will need to use the transcode tasks in a different way (perhaps as a default task).

### Full sample

See [full sample project here](https://github.com/kirill-grouchnikov/aurora-svg-transcoder-plugin-test).
