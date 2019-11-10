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
    api(Dependency.dagger)
    kapt(Dependency.daggerCompiler)
    implementation(Dependency.kotlinStdLib)
    implementation(Dependency.appCompat)
    implementation(Dependency.okHttp)
    implementation(Dependency.okHttpLoggingInterceptor)
    implementation(Dependency.retrofit)
    implementation(Dependency.retrofitConverterGson)
    implementation(Dependency.retrofitAdapterRxJava)
    implementation(Dependency.rxJava)
    implementation(Dependency.rxAndroid)
}