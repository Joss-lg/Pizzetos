plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")

}

android {
    namespace = "com.tuusuario.pizzetos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tuusuario.pizzetos"
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
}

dependencies {
    annotationProcessor ("androidx.room:room-compiler:2.5.1") // Para Java
    implementation ("com.google.firebase:firebase-firestore:24.7.1")
    implementation ("androidx.room:room-runtime:2.5.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.firebase:firebase-auth:22.1.0")
    implementation ("androidx.sqlite:sqlite:2.3.1")
    implementation ("com.google.firebase:firebase-auth")
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation ("com.google.android.material:material:1.9.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("com.google.android.gms:play-services-maps:19.0.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}