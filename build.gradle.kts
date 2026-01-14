import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    dependencies {
        classpath(libs.compose.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.dokka.gradlePlugin)
        classpath(libs.versionchecker.gradlePlugin)
        classpath(libs.mavenpublish.gradlePlugin)
    }
}

plugins {
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    tasks.withType<Jar>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
        version = "${project.property("VERSION_NAME")}"
        archiveBaseName.set("${rootProject.name}-${project.name}")

        manifest {
            // add Aurora version and automatic module name to each MANIFEST.MF
            attributes["Aurora-Version"] =
                "${project.property("VERSION_NAME")} ${project.property("VERSION_CODENAME")}"
            if (project.hasProperty("POM_ARTIFACT_ID")) {
                attributes["Automatic-Module-Name"] =
                    "org.pushingpixels." + "${project.property("POM_ARTIFACT_ID")}".replace("-", ".")
            }
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            // Force class file format for Java 11
            jvmTarget.set(JvmTarget.JVM_11)
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        }
    }

    configurations {
        all {
            exclude(group = "org.jetbrains.compose.material", module = "material")
            exclude(group = "org.jetbrains.compose.material3", module = "material3")
        }
    }
}

// To generate report about available dependency updates, run
// ./gradlew dependencyUpdates
apply(plugin = "com.github.ben-manes.versions")

// Copies the compiled jars from all the modules into the drop folder.
tasks.register("copyJars") {
    delete("drop/${project.property("VERSION_NAME")}")
    mkdir("drop/${project.property("VERSION_NAME")}")
    subprojects {
        copy {
            from(project.layout.buildDirectory.dir("libs").get().asFile)
            include("${rootProject.name}-${project.name}-*-${project.property("VERSION_NAME")}.jar")
            into("${rootProject.projectDir}/drop/${project.property("VERSION_NAME")}")
        }
    }
}

tasks.register("getDependencies") {
    subprojects {
        val runtimeClasspath =
            project.configurations.matching { it.name == "desktopRuntimeClasspath" }
        runtimeClasspath.all {
            for (dep in map { file: File -> file.absoluteFile }) {
                project.copy {
                    from(dep)
                    into("${rootProject.projectDir}/build/libs")
                }
            }
        }
    }
}

tasks.register("printRuntimeDependencies") {
    println("Project runtime dependencies:")
    allprojects {
        println()
        println("-------- ${project.name} --------")
        project.configurations.matching { it.name == "desktopRuntimeClasspath" }
            .matching { !it.allDependencies.isEmpty() }
            .forEach {
                it.allDependencies.forEach { dep ->
                    if (dep.group != null) {
                        println("${dep.group}:${dep.name}:${dep.version}")
                    }
                }
            }
    }
}
