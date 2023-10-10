object Dependency {

    object AndroidX {
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"

        const val LIFECYCLE_VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
        const val LIFECYCLE_LIVEDATA_KTX =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"
        const val LIFECYCLE_RUNTIME =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

        const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.LEGACY}"

        const val PREFERENCE_KTX = "androidx.preference:preference-ktx:${Versions.PREFERENCE_KTX}"

        const val VIEWPAGER_2 = "androidx.viewpager2:viewpager2:${Versions.VIEWPAGER_2}"

        //CameraX
        const val CAMERA_VIEW = "androidx.camera:camera-view:${Versions.CAMERA}"
        const val CAMERA_CAMERA2 = "androidx.camera:camera-camera2:${Versions.CAMERA}-rc01"
        const val CAMERA_LIFECYCLE = "androidx.camera:camera-lifecycle:${Versions.CAMERA}"
    }

    object UnitTest {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
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
        const val FIREBASE = "com.google.firebase:firebase-auth-ktx:${Versions.FIREBASE}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"

        const val GMS_PLAY_SERVICE_AUTH =
            "com.google.android.gms:play-services-auth:${Versions.GMS_PLAY_SERVICE_AUTH}"
        const val GMS_GOOGLE_SERVICE =
            "com.google.gms:google-services:${Versions.GMS_GOOGLE_SERVICE}"

        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

        const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIVEDATA}"
    }

}