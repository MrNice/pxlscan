plugins {
    id("com.android.application") version "8.11.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0" apply false
}

// Dev-machine only: C: is nearly full, so build output goes to F: when that drive exists.
// On any other machine the guard is false and the default build directories are used.
if (file("F:/").exists()) {
    allprojects {
        layout.buildDirectory.set(file("F:/pxlscan-build/${project.name}"))
    }
}
