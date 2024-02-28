import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

private val supabaseProperties = Properties()
supabaseProperties.load(FileInputStream("app.properties"))

android {
    namespace = libs.versions.base.id.get() + libs.versions.supabase.get()
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()

        buildConfigField("String", "supabaseUrl", supabaseProperties.getProperty("supabaseUrl"))
        buildConfigField("String", "supabaseKey", supabaseProperties.getProperty("supabaseKey"))
        buildConfigField("String", "googleKey", supabaseProperties.getProperty("googleKey"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.versions.get()
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.android.appcompat)
    implementation(libs.bundles.supabase)
    implementation(libs.bundles.koin)

    implementation(projects.core.common)
}