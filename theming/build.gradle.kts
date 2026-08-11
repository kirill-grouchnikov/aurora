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
                api(libs.compose.desktop)
                api(libs.compose.foundation)
                api(libs.compose.runtime)
                implementation(project(":common"))
                implementation(libs.ephemeral.chroma)
            }
        }
    }
}
