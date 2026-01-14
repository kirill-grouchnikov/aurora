plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-multiplatform/issues/22)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.compose.components.resources)
                implementation(project(":common"))
                implementation(project(":component"))
                implementation(project(":theming"))
                implementation(project(":window"))
                implementation(libs.ephemeral.chroma)
            }
        }
    }
}

compose {
    resources {
        publicResClass = false
        packageOfResClass = "org.pushingpixels.aurora.demo.resources"
        generateResClass = always
    }
}

