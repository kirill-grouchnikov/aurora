import org.jetbrains.compose.compose

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
        named("desktopMain") {
            dependencies {
                api(compose.desktop.currentOs)
                implementation(libs.kotlin.coroutines)
                implementation(project(":common"))
                implementation(project(":component"))
                implementation(project(":theming"))
                implementation(project(":window"))
                implementation(libs.ephemeral.chroma)
            }
        }
    }
}
