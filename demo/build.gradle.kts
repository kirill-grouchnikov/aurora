import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":component"))
                implementation(project(":icon:icon"))
                implementation(project(":skin"))
                implementation(project(":window"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.pushingpixels.aurora.demo.AuroraDemoKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "AuroraDemo"
            packageVersion = "1.0.0"
            modules("jdk.crypto.ec")
        }
    }
}
