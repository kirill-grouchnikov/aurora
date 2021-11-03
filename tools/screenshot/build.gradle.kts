import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("desktopMain") {
            dependencies {
                api(compose.desktop.currentOs)
                implementation(project(":common"))
                implementation(project(":component"))
                implementation(project(":theming"))
                implementation(project(":window"))
            }
        }
    }
}
