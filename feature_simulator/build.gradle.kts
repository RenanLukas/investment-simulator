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
    api(project(Module.featureCore))
    implementation(Dependency.constraintLayout)
    implementation(Dependency.lifecycleExtensions)
}