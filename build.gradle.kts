buildscript {
    repositories {
        google()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.2.0-build127")
        classpath(kotlin("gradle-plugin", version = "1.4.10"))
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
