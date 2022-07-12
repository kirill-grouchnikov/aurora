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
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.2.0-alpha01-dev745")
        classpath(kotlin("gradle-plugin", version = "1.7.0"))
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.20.0")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.6.21")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    tasks.withType<Jar>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        // Force class file format for Java 11
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
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
            from("${project.buildDir}/libs")
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
