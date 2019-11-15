plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(App.compileSdkVersion)
    defaultConfig {
        minSdkVersion(App.minSdkVersion)
        targetSdkVersion(App.targetSdkVersion)
    }
}

dependencies {
    kapt(Dependency.daggerCompiler)
    api(project(Module.featureCore))
    implementation(Dependency.constraintLayout)
    implementation(Dependency.recyclerView)

    testImplementation(TestDependency.junit)
    testImplementation(TestDependency.mockk)
    testImplementation(TestDependency.archCoreTesting)
}