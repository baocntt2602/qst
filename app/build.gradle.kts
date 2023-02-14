plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 33

    buildTypes {
        create("develop") {
            initWith(getByName("debug"))
        }
    }

    defaultConfig {
        applicationId = "com.example.qstmovieapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.googleMaterial)

    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)

    implementation(Libraries.navFragmentKtx)
    implementation(Libraries.navUIKtx)

    implementation(Libraries.gson)

    implementation(Libraries.junit)
    implementation(Libraries.junitKtx)
    implementation(Libraries.mockk)
    implementation(Libraries.mockkAndroid)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}