// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id("com.google.dagger.hilt.android") version "2.41" apply false
}




buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        //hilt class path
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
    }
}
