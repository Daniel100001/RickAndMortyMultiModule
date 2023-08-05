plugins {

    // Application
    id("com.android.application")

    //
    kotlin("android")

    // safeargs
    id("androidx.navigation.safeargs")

    // kapt
    id("kotlin-kapt")

    // hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.rickandmortyapicleanarchitecture"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.rickandmortyapicleanarchitecture"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug{
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")

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
        viewBinding = true
    }
}

dependencies {

    //core
    implementation("androidx.core:core-ktx:1.10.1")

    //app_compact
    implementation("androidx.appcompat:appcompat:1.6.1")

    //material_design
    implementation("com.google.android.material:material:1.9.0")

    // UI components
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Activity
    val activity_version = "1.7.2"
    implementation("androidx.activity:activity-ktx:$activity_version")

    // Fragment
    val fragment_version = "1.5.5"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    // Lifecycle
    val lifecycle_version = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")


    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")

    //paging 3
    val paging_version = "3.1.1"
    implementation("androidx.paging:paging-common-ktx:$paging_version")

    // nav_graph
    val navVersion = "2.6.0"

    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0")

    //Retrofit 2
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // ViewBindingPropertyDelegate
    val view_binding_property_delegate = "1.5.8"
    implementation("com.github.kirich1409:viewbindingpropertydelegate:$view_binding_property_delegate")

    //hilt
    val hilt_version = "2.45"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    // View Pager
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}