plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies{

    // Inject
    implementation("javax.inject:javax.inject:1")

    // Coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Domain
    api(project(":common"))
}