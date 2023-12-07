plugins {
    id (Dependency.Gradle.LIBRARY)
    id (Dependency.Gradle.KOTLIN)
    kotlin (Dependency.Gradle.KAPT)
}

android {
    namespace = Dependency.Gradle.DOMAIN
    compileSdk = Dependency.Version.COMPILE_SDK

    defaultConfig {
        minSdk = Dependency.Version.MIN_SDK

        testInstrumentationRunner = Dependency.TestProperties.TEST_RUNNER
        consumerProguardFiles(Dependency.Files.CONSUMER_PROGUARDFILES)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    //unitTest
    testImplementation(Dependency.Test.JUNIT)

    //androidTest
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)

    //hilt
    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    //okhttp
    implementation(Dependency.Libraries.OKHTTP)
}