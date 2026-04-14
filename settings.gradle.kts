pluginManagement {
    repositories {

        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()


        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password ="sk.eyJ1Ijoia2hhbGVkMTEzIiwiYSI6ImNtbGkwYWN2MTA3azYzZXM4ZW9vMGM4eWIifQ.Bhz8QSKHEx7sI5_vrQdN_Q"
            }
        }

    }

}

rootProject.name = "smsrly"
include(":app")
