plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
    `maven-publish`
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                implementation(project(":common"))
                implementation(project(":theming"))
            }
        }
        named("desktopMain") {
            dependencies {
                api(compose.desktop.common)
                implementation(project(":common"))
                implementation(project(":theming"))
            }
        }
    }
}
