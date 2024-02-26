import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

private val supabaseProperties = Properties()
supabaseProperties.load(FileInputStream("app.properties"))

android {
    namespace = libs.versions.base.id.get() + libs.versions.common.get()
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
            isMinifyEnabled = libs.versions.release.minify.get().toBoolean()

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.versions.get()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.get()
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.android.appcompat)
    implementation(libs.google.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.retrofit.with.okhttp)
    implementation(libs.bundles.orbit)
    implementation(libs.bundles.voyager)
    implementation(libs.bundles.datastore.full)
    implementation(libs.bundles.settings)
    implementation(libs.bundles.koin)
    implementation(libs.kotlinx.json)
    implementation(libs.supabasegoTrue)
    implementation(libs.supabaseAuth)
    implementation(libs.supabaseAuthUi)
    implementation(libs.supabaseRealtime)
    implementation(libs.ktor.client.cio)
    implementation(libs.play.services.auth)
    implementation(libs.supabasePostgress)
    implementation(libs.ktor.client.android)

    implementation(projects.core.resources)
}