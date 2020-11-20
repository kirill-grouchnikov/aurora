buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.2.0-build127")
        classpath(kotlin("gradle-plugin", version = "1.4.10"))
        classpath("com.github.ben-manes:gradle-versions-plugin:0.36.0")
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

// To generate report about available dependency updates, run
// ./gradlew dependencyUpdates
apply(plugin = "com.github.ben-manes.versions")

