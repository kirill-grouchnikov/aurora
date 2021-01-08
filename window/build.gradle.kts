import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                implementation(project(":component"))
                implementation(project(":skin"))
            }
        }
        named("desktopMain") {
            dependencies {
                api(compose.desktop.common)
                implementation(project(":common"))
                implementation(project(":bitmapfilter"))
                implementation(project(":icon:icon"))
                implementation(project(":component"))
                implementation(project(":skin"))
            }
        }
    }
}