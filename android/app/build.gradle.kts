import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

// Release signing comes from <repo root>/keystore.properties and only when that file exists
// (storeFile, storePassword, keyAlias, keyPassword). Never commit it or the key it names.
val keystoreProps = Properties().apply {
    val f = rootProject.file("../keystore.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}

android {
    namespace = "com.pixelicious.pxlscan"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pixelicious.pxlscan"
        minSdk = 26
        targetSdk = 35
        versionCode = 3
        versionName = "0.2.0"
        ndk { abiFilters += listOf("arm64-v8a", "x86_64") }
    }

    signingConfigs {
        if (keystoreProps.containsKey("storeFile")) {
            create("release") {
                storeFile = rootProject.file("..").resolve(keystoreProps.getProperty("storeFile"))
                storePassword = keystoreProps.getProperty("storePassword")
                keyAlias = keystoreProps.getProperty("keyAlias")
                keyPassword = keystoreProps.getProperty("keyPassword")
            }
        }
    }
    buildTypes {
        // debug: the default Android debug key (no signingConfig set).
        getByName("release") {
            isMinifyEnabled = false
            signingConfigs.findByName("release")?.let { signingConfig = it }
        }
    }
    buildFeatures { compose = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    // The .so files are produced by cargo-ndk (task below), not by an NDK/CMake build.
    sourceSets["main"].jniLibs.srcDirs("src/main/jniLibs")
}

kotlin { compilerOptions { jvmTarget.set(JvmTarget.JVM_17) } }

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.09.03")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}

// Build the Rust core (android/rust -> libpxlscan_core.so) for the APK's ABIs before packaging.
// Skip with -PskipCargo when the jniLibs are already fresh.
val cargoBuild by tasks.registering(Exec::class) {
    group = "build"
    description = "cargo ndk build of android/rust -> app/src/main/jniLibs"
    workingDir = rootProject.file("rust")
    commandLine(
        "cargo", "ndk", "-t", "arm64-v8a", "-t", "x86_64", "-P", "26",
        "-o", "../app/src/main/jniLibs", "build", "--release",
    )
    if (file("F:/").exists()) environment("CARGO_TARGET_DIR", "F:/cargo-target/pxlscan")
}
if (!project.hasProperty("skipCargo")) {
    tasks.named("preBuild") { dependsOn(cargoBuild) }
}
