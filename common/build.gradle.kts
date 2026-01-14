plugins {
    kotlin("multiplatform")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
    `maven-publish`
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("commonMain") {
            dependencies {
                api(libs.compose.runtime)
                api(libs.compose.foundation)
            }
        }
        named("desktopMain") {
            dependencies {
                api(libs.compose.desktop)
            }
        }
    }
}
