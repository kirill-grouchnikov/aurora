import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("desktopMain") {
            dependencies {
                api(compose.desktop.common)
                implementation("org.apache.xmlgraphics:batik-all:1.13")
            }
        }
    }
}
