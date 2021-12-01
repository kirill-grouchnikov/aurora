## Building Aurora

Aurora libraries are built with [Gradle](https://docs.gradle.org/). Each library has its own `build.gradle.kts` file, and the top-level `build.gradle.kts` has tasks to build, package and deploy Aurora artifacts.

### Java requirements

Aurora is built with Java 11. Search for "Java 11 download" in your favorite search engine, or browse [this page](https://www.oracle.com/technetwork/java/javase/downloads/index.html) (and perhaps the archives that it points to) to get the JDK distribution for your environment.

### Local artifacts

To build library artifacts, run `./gradlew build` in your terminal. After this command completes, the packaged jar files will be under the matching library folders. To copy all the jars under one location, run `./gradlew copyJars` command. For version `X.Y` of Aurora (see `version` in `build.gradle`), the artifacts will be copied into the `drop/X.Y` folder.

### Local third-party dependencies

Some Aurora libraries have third-party dependencies. To print the full dependency tree, run `./gradlew printRuntimeDependencies`. Here is a snippet that shows the dependencies for [SVG Transcoder](tools/svg-transcoder/svg-transcoder.md):

```
-------- svg-transcoder --------
org.jetbrains.compose.desktop:desktop:1.0.0
org.apache.xmlgraphics:batik-all:1.14
```

To locally copy these dependencies (in case you do not want to pull them at build time with Gradle, Maven or other similar build tools in your own environment), use `./gradlew getDependencies`. Once that command completes, all dependencies willl be in the `build/libs` folder.

For a more detailed tree of dependencies, run `./gradlew tools:svg-transcoder:dependencies` - substituting the relevant module name.
