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
    kapt(Dependency.daggerCompiler)
    api(Dependency.dagger)
    api(Dependency.appCompat)
    api(Dependency.retrofit)
    api(Dependency.rxJava)
    api(Dependency.rxAndroid)
    implementation(Dependency.kotlinStdLib)
    implementation(Dependency.okHttp)
    implementation(Dependency.okHttpLoggingInterceptor)
    implementation(Dependency.retrofitConverterGson)
    implementation(Dependency.retrofitAdapterRxJava)
}