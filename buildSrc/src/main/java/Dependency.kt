object Dependency {
    object Gradle {
        const val APPLICATION = "com.android.application"
        const val LIBRARY = "com.android.library"
        const val KOTLIN = "org.jetbrains.kotlin.android"
        const val KAPT = "kapt"

        const val MISO = "com.example.miso"
        const val DATA = "com.example.data"
        const val DI = "com.example.di"
        const val DOMAIN = "com.example.domain"
    }
    object Version {
        const val COMPILE_SDK = 33
        const val MIN_SDK = 26
        const val TARGET_SDK = 33
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
    }
    object Files {
        const val CONSUMER_PROGUARDFILES = "consumer-rules.pro"
        const val DEFAULT_PROGUARDFILES = "proguard-android-optimize.txt"
        const val PROGUARDFILES = "proguard-rules.pro"
    }
    object TestProperties {
        const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    }
    object AndroidX {
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val PREFERENCE_KTX = "androidx.preference:preference-ktx:${Versions.PREFERENCE_KTX}"

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

        //CameraX
        const val CAMERA_CORE =  "androidx.camera:camera-core:${Versions.CAMERA}"
        const val CAMERA_VIEW = "androidx.camera:camera-view:${Versions.CAMERA}"
        const val CAMERA_CAMERA2 = "androidx.camera:camera-camera2:${Versions.CAMERA}-rc01@aar"
        const val CAMERA_LIFECYCLE = "androidx.camera:camera-lifecycle:${Versions.CAMERA}"
        const val CAMERA_EXTENSIONS = "androidx.camera:camera-extensions:${Versions.CAMERA}"
    }
    object Compose {
        const val ACTIVITY_COMPOSE =
            "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
        const val COMPOSE = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val COMPOSE_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val COMPOSE_MATERIAL =
            "androidx.compose.material:material:${Versions.COMPOSE_MATERIAL}"
        const val COMPOSE_MATERIAL3 =
            "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL3}"
        const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
        const val COMPOSE_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
    }

    object Libraries {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    }
    object Google {
        const val FIREBASE_STORAGE = "com.google.firebase:firebase-storage"
        const val FIREBASE_DATABASE = "com.google.firebase:firebase-database-ktx:${Versions.FIREBASE_DATABASE}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging-ktx:${Versions.FIREBASE_MESSAGING}"

        const val GMS_PLAY_SERVICE_AUTH =
            "com.google.android.gms:play-services-auth:${Versions.GMS_PLAY_SERVICE_AUTH}"
        const val GMS_GOOGLE_SERVICE =
            "com.google.gms:google-services:${Versions.GMS_GOOGLE_SERVICE}"
        const val GMS_GOOGLE_SERVICE_PLUGIN =
            "com.google.gms.google-services"

        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val HILT_PLUGIN = "com.google.dagger.hilt.android"

        const val ACCOMPANIST = "com.google.accompanist:accompanist-systemuicontroller:${Versions.ACCOMPANIST}"
        const val ACCOMPANIST_PERMISSION = "com.google.accompanist:accompanist-permissions:${Versions.ACCOMPANIST}"

        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    }

    object Navigation {
        const val NAVIGATION = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"
    }

    object Image {
        const val COIL = "io.coil-kt:coil-compose:${Versions.COIL_VERSION}"
    }

    object DataStore {
        const val PREFERENCES = "androidx.datastore:datastore-preferences:${Versions.PREFERENCES}"
    }

    object Github {
        const val MARKDOWN = "com.github.jeziellago:compose-markdown:${Versions.MARKDOWN}"
    }
}