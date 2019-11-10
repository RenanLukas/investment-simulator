plugins {
    id("com.android.library")
    kotlin("android")
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
    implementation(Dependency.kotlinStdLib)
    implementation(Dependency.appCompat)
    api(Dependency.dagger)
    kapt(Dependency.daggerCompiler)
}