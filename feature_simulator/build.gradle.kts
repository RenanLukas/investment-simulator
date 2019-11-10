plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Android.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
    }
}

dependencies {
    kapt(Dependency.daggerCompiler)
    implementation(project(Module.featureCore))
    implementation(Dependency.constraintLayout)
}