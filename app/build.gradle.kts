plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.weeber"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weeber"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    /*converter-name tends to any one from listed conververts*/
    implementation ("com.squareup.retrofit2:adapter-rxjava:2.7.1")
    // HttpRequest and converters
    // This is used for logging (Log) the responses of the WebServices
    implementation ("com.squareup.okhttp3:logging-interceptor:4.3.0")
    // These are HTTP clients
    implementation ("com.squareup.okhttp3:okhttp-urlconnection:4.3.0")
    // This is for receiving plain text from the WebServices
    implementation ("com.squareup.retrofit2:converter-scalars:2.7.1")
    // This is for using Observable with Retrofit, so we don't have to worry about threading
    implementation ("com.squareup.retrofit2:adapter-rxjava:2.7.1")
    // This is the plain JSON to object Parser
    implementation ("com.google.code.gson:gson:2.8.6")
    // This the RXJava specific for Android
    implementation ("io.reactivex:rxandroid:1.2.1")

    implementation ("org.jetbrains.kotlinx:kotlinx-datetime:0.2.0")
}