plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    groovy
    id("com.vanniktech.maven.publish")
    `maven-publish`
}

dependencies {
    implementation(gradleApi())
    implementation(project(":tools:svg-transcoder"))
}

gradlePlugin {
    plugins.register("org.pushing-pixels.aurora.tools.svgtranscoder.gradle") {
        id = "org.pushing-pixels.aurora.tools.svgtranscoder.gradle"
        implementationClass = "org.pushingpixels.aurora.tools.svgtranscoder.gradle.AuroraSvgTranscoderGradlePlugin"
    }
}
