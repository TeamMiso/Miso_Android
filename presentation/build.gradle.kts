plugins {
    id (Dependency.Gradle.APPLICATION)
    id (Dependency.Gradle.KOTLIN)
    id (Dependency.Gradle.KAPT)
    id (Dependency.Google.HILT_PLUGIN)
    id (Dependency.Google.GMS_GOOGLE_SERVICE_PLUGIN)
}

android {
    namespace = Dependency.Gradle.MISO
    compileSdk = Dependency.Version.TARGET_SDK

    defaultConfig {
        applicationId = Dependency.Gradle.MISO
        minSdk = Dependency.Version.MIN_SDK
        targetSdk = Dependency.Version.TARGET_SDK
        versionCode = Dependency.Version.VERSION_CODE
        versionName = Dependency.Version.VERSION_NAME

        testInstrumentationRunner = Dependency.TestProperties.TEST_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Dependency.Files.DEFAULT_PROGUARDFILES),
                Dependency.Files.PROGUARDFILES
            )
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVA_VERSION
        targetCompatibility = Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Versions.JAVA_VERSION.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    buildFeatures {
        compose = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":di"))

    //hilt
    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    //CameraX
    implementation(Dependency.AndroidX.CAMERA_CORE)
    implementation(Dependency.AndroidX.CAMERA_CAMERA2)
    implementation(Dependency.AndroidX.CAMERA_LIFECYCLE)
    implementation(Dependency.AndroidX.CAMERA_VIEW)
    implementation(Dependency.AndroidX.CAMERA_EXTENSIONS)

    //fireBase
    implementation(platform(Dependency.Google.FIREBASE_BOM))
    implementation(Dependency.Google.FIREBASE_ANALYTICS)
    implementation(Dependency.Google.FIREBASE_DATABASE)
    implementation(Dependency.Google.FIREBASE_STORAGE)

    //coil
    implementation(Dependency.Image.COIL)

    //aac
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.LIFECYCLE_RUNTIME)
    implementation(Dependency.Navigation.NAVIGATION)

    //compose
    implementation(Dependency.Compose.ACTIVITY_COMPOSE)
    implementation(Dependency.Compose.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_TOOLING)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_MATERIAL3)
    implementation(Dependency.Compose.COMPOSE_PREVIEW)

    //junit
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)
    androidTestImplementation(Dependency.Test.COMPOSE_JUNIT)
    debugImplementation(Dependency.Compose.COMPOSE_TOOLING)
    debugImplementation(Dependency.Test.COMPOSE_MANIFEST)

    //accompanist
    implementation(Dependency.Google.ACCOMPANIST)
    implementation(Dependency.Google.ACCOMPANIST_PERMISSION)

    //gson
    implementation(Dependency.Google.GSON)

    //markdown
    implementation(Dependency.Github.MARKDOWN)

}