buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.4.0")
        classpath(kotlin("gradle-plugin", version = "1.5.10"))
        classpath("com.github.ben-manes:gradle-versions-plugin:0.39.0")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.15.1")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.32")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    tasks.withType<Jar>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
        archiveBaseName.set("${rootProject.name}-${project.name}")
    }

    configurations {
        all {
            exclude(group="org.jetbrains.compose.material", module="material")
        }
    }
}


// To generate report about available dependency updates, run
// ./gradlew dependencyUpdates
apply(plugin = "com.github.ben-manes.versions")

