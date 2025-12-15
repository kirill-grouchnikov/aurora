plugins {
    `kotlin-dsl`
    id("com.vanniktech.maven.publish")
    `maven-publish`
}

dependencies {
    implementation(gradleApi())
    implementation(project(":tools:svg-transcoder"))
}

kotlin {
    jvmToolchain(11)
}

gradlePlugin {
    plugins {
        create("org.pushing-pixels.aurora.tools.svgtranscoder.gradle") {
            id = "org.pushing-pixels.aurora.tools.svgtranscoder.gradle"
            implementationClass = "org.pushingpixels.aurora.tools.svgtranscoder.gradle.AuroraSvgTranscoderGradlePlugin"
        }
    }
}
