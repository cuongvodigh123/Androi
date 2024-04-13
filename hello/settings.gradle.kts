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
<<<<<<< HEAD
=======
        maven { url = uri("https://jitpack.io") }
>>>>>>> nguyencongvan
    }
}

rootProject.name = "Helloworld"
include(":app")
 