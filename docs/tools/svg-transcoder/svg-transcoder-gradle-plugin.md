## Gradle plugin for transcoding SVG content

The goal of this project is to allow build-time transcoding of SVG content into Kotlin classes by wrapping [SVG transcoder APIs](svg-transcoder.md) and making them available for Gradle builds.

### Using the plugin in your Gradle script

Add the plugin to the `buildscript` part of your Gradle build file and apply the plugin:

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:X.Y.Z'
    }
}

apply plugin: 'org.pushing-pixels.aurora.tools.svgtranscoder.gradle'
```

In case you want to use the latest snapshot version of the plugin, use the Sonatype repository:

```groovy
buildscript {
    repositories {
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots'
        }
    }
    dependencies {
        classpath 'org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:X.Y.Z-SNAPSHOT'
    }
}
```

### Transcoding SVG files from a single folder

For a Kotlin project, generate Kotlin classes with the plugin (add multiple `transcode` lambdas if you have more than one SVG content folder):

```groovy
compileKotlin.doFirst {
    transcode {
        inputDirectory = file('src/main/resources')
        outputDirectory = file('src/main/java/org/aurora/demo/svg')
        outputPackageName = 'org.aurora.demo.svg'
        transcode()
    }
}
```

### Recursively transcoding SVG files under a folder

For a Kotlin project, generate Kotlin classes with the plugin (add multiple `transcodeDeep` lambdas if you have more than one SVG content root folder):

```groovy
compileKotlin.doFirst {
    transcodeDeep {
        inputRootDirectory = file('src/main/resources')
        outputRootDirectory = file('src/main/java/org/aurora/demo/svg')
        outputRootPackageName = 'org.aurora.demo.svg'
        transcode()
    }
}
```
### Additional notes

Note that using `compileKotlin` assumes that you have at least one "real" source file in your project so that these tasks are executed by Gradle. If you are planning to use the plugin in a module that will have only SVG content and the transcoded classes, you will need to use the `transcode` / `transcodeDeep` tasks in a different way (perhaps as a default task).
