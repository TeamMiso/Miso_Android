import java.io.FileInputStream
import java.util.Properties

plugins {
    id (Dependency.Gradle.APPLICATION)
    id (Dependency.Gradle.KOTLIN)
    id (Dependency.Gradle.KAPT)
    id (Dependency.Google.HILT_PLUGIN)
}

android {
    namespace = Dependency.Gradle.DATA
    compileSdk = Dependency.Version.COMPILE_SDK

    defaultConfig {
        minSdk = Dependency.Version.MIN_SDK

        testInstrumentationRunner = Dependency.TestProperties.TEST_RUNNER
        consumerProguardFiles(Dependency.Files.CONSUMER_PROGUARDFILES)

        buildConfigField(
            "String",
            "BASE_URL",
            getApiKey("BASE_URL")
        )
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
    implementation(project(":domain"))
    //unitTest
    testImplementation(Dependency.Test.JUNIT)

    //androidTest
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)

    //hilt
    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    //retrofit
    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)

    //okhttp
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.DataStore.PREFERENCES)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}