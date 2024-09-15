plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id ("kotlin-kapt")
    // Parcelize
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.vkclientcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vkclientcompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Dagger2
    implementation ("com.google.dagger:dagger:2.51")
    kapt ("com.google.dagger:dagger-compiler:2.51")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Logging-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // VK
    implementation ("com.vk:android-sdk-core:4.1.0")
    implementation ("com.vk:android-sdk-api:4.1.0")
    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")
    // GSON
    implementation ("com.google.code.gson:gson:2.11.0")
    // Viewmodel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
    val nav_version = "2.7.7"
    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")
    // SwipeToDismiss
    implementation("androidx.compose.material3:material3:1.3.0")
    // Compose LiveData
    implementation ("androidx.compose.runtime:runtime-livedata:1.7.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(kotlin("script-runtime"))
}