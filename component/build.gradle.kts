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
                implementation(project(":common"))
                implementation(project(":theming"))
                implementation(libs.ephemeral.chroma)
            }
        }
        named("desktopMain") {
            dependencies {
                api(libs.compose.desktop)
                implementation(project(":common"))
                implementation(project(":theming"))
                implementation(libs.ephemeral.chroma)
            }
        }
    }
}
