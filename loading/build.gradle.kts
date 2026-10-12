import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
    `maven-publish`
}

kotlin {
    //explicitApi()
    jvm("desktop")
    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }
    sourceSets {
        named("commonMain") {
            dependencies {
                api(libs.compose.runtime)
                api(libs.compose.foundation)
                api(libs.graphics.shapes)
            }
        }
    }
}
