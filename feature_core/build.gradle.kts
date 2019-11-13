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
    api(Dependency.dagger)
    api(Dependency.appCompat)
    api(Dependency.retrofit)
    api(Dependency.rxJava)
    api(Dependency.rxAndroid)
    api(Dependency.okHttp)
    api(Dependency.okHttpLoggingInterceptor)
    api(Dependency.lifecycleExtensions)
    implementation(Dependency.constraintLayout)
    implementation(Dependency.androidMaterial)
    implementation(Dependency.kotlinStdLib)
    implementation(Dependency.retrofitConverterGson)
    implementation(Dependency.retrofitAdapterRxJava)
}