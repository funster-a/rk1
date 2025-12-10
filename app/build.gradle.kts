plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.rk1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.rk1"
        minSdk = 29
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    javacOptions {
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
