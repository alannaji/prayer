plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.islam.prayer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.islam.prayer"
        minSdk = 26
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

    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.foundation)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.lifecycle.process4)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.animation)

    //serialization
    implementation(libs.kotlinx.serialization.json)
    //coil
    implementation(libs.coil.gif)
    implementation(libs.coil.compose)
    implementation (libs.coil.kt.coil.video)

    //retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)

    //Launch Darkly
    implementation (libs.launchdarkly.launchdarkly.android.client.sdk)

    //gms
    implementation (libs.play.services.location)

    //permission
    implementation (libs.accompanist.permissions)

    //gson
    implementation (libs.retrofit.gson.converter)

    //room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    //hilt
    implementation(libs.hilt.common)
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //datastore
    implementation(libs.datastore.preferences)
    implementation(libs.datastore.core)

    //New Shapes
    implementation(libs.graphics.shapes)

    //permission
    implementation(libs.accompanist.permissions)

    //extended Icons
    implementation(libs.androidx.material.icons.extended.android)


    /*testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)*/

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}