buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.3.0-build152")
        classpath(kotlin("gradle-plugin", version = "1.4.30"))
        classpath("com.github.ben-manes:gradle-versions-plugin:0.36.0")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.14.0")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.20")
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
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

