plugins {
    id(Dependency.Gradle.APPLICATION) version Versions.GRADLE_ANDROID apply false
    id(Dependency.Gradle.LIBRARY) version Versions.GRADLE_ANDROID apply false
    id(Dependency.Gradle.KOTLIN) version Versions.GRADLE_KOTLIN apply false
    id(Dependency.Google.HILT_PLUGIN) version Versions.HILT apply false
    id(Dependency.Google.GMS_GOOGLE_SERVICE_PLUGIN) version Versions.GMS_GOOGLE_SERVICE apply false
}