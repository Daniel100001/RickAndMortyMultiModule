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
rootProject.name = "RickAndMortyApiCleanArchitecture"
include (":app")

include(":data")
include(":presentation")
include(":domain")
include(":common")
include(":utils")
