enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SportsDictionary"
include(":app")
include(":core:ui")
include(":core:resources")
include(":core:common")
include(":feature:dictionary")
include(":feature:authorization")
include(":feature:settings")
include(":core:supabase")
