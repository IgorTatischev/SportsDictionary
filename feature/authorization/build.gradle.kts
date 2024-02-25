plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.serizliation)
}

android {
    namespace = libs.versions.base.id.get() + libs.versions.authorization.get()
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = libs.versions.release.minify.get().toBoolean()

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isMinifyEnabled = libs.versions.debug.minify.get().toBoolean()

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.versions.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.get()
    }
}

dependencies {
    implementation(libs.bundles.voyager)

    implementation(libs.kotlinx.json)
    implementation(libs.supabasegoTrue)
    implementation(libs.supabaseAuth)
    implementation(libs.supabaseAuthUi)
    implementation(libs.ktor.client.cio)
    implementation(libs.play.services.auth)
    implementation(libs.ktor.client.android)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.ui.tooling)
    implementation(libs.material3)
    implementation(libs.extended.icons)

    implementation(libs.androidx.window)
    implementation(libs.bundles.koin)

    implementation(projects.core.resources)
    implementation(projects.core.common)
    implementation(projects.core.ui)
}