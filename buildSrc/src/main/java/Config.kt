private const val kotlinVersion = "1.3.50"

object Gradle {
    private const val androidGradleVersion = "3.5.0"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    val android = "com.android.tools.build:gradle:$androidGradleVersion"
}

object Android {
    const val buildToolsVersion = "29.0.0"
    const val minSdkVersion = 16
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
    const val applicationId = "com.btctracker"
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val testRunner = "android.support.test.runner.AndroidJUnitRunner"
}

object Dependency {
    private const val appCompatVersion = "1.0.0"
    private const val constraintLayoutVersion = "1.1.3"
    private const val okHttpVersion = "3.12.1"
    private const val retrofitVersion = "2.5.0"
    private const val rxJavaVersion = "2.2.6"
    private const val rxAndroidVersion = "2.1.0"
    private const val daggerVersion = "2.25.2"

    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
}

object TestDependency {
    private const val junitVersion = "4.12"
    private const val mockitoVersion = "2.7.2"
    private const val mockitoKotlinVersion = "2.1.0"
    private const val archCoreTestingVersion = "2.0.0-alpha1"

    const val junit = "junit:junit:$junitVersion"
    const val mockito = "org.mockito:mockito-core:$mockitoVersion"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
    const val archCoreTesting = "androidx.arch.core:core-testing:$archCoreTestingVersion"
}

object AndroidTestDependency {
    private const val espressoCoreVersion = "3.1.0"
    private const val androidxTestVersion = "1.1.0"

    const val espresso = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    const val testRunnerVersion = "androidx.test:runner:$androidxTestVersion"
    const val testRulesVersion = "androidx.test:rules:$androidxTestVersion"
}

object Module {
    const val featureCore = ":feature_core"
}