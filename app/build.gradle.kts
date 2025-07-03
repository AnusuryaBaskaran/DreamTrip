plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.dreamtrip"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dreamtrip"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // UI
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Retrofit for API calls
    implementation(libs.retrofit2.retrofit)
    implementation(libs.junit.androidx)
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    implementation(libs.okhttp)
    implementation(libs.okhttp.v4100)
    implementation(libs.json)
    implementation(libs.google.gson)
    implementation(libs.firebase.database.v2030)
    implementation(libs.google.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.lottie.v610)


    implementation(libs.lottie)
    // Unit Testing
    testImplementation(libs.junit)

    // Instrumentation Testing
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}
