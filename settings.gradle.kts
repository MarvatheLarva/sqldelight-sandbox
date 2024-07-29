rootProject.name = "sandbox-sqldelight"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

include(
    ":modules:mysql",
    ":modules:postgres",
)
