buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.3.0-build154")
        classpath(kotlin("gradle-plugin", version = "1.4.30"))
        classpath("com.github.ben-manes:gradle-versions-plugin:0.36.0")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.14.2")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.20")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        // TODO - this is still needed for org.jetbrains.kotlinx:kotlinx-collections-immutable
        maven("https://kotlin.bintray.com/kotlinx")
    }

    tasks.withType<Jar>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
        archiveBaseName.set("${rootProject.name}-${project.name}")
    }
}


// To generate report about available dependency updates, run
// ./gradlew dependencyUpdates
apply(plugin = "com.github.ben-manes.versions")

