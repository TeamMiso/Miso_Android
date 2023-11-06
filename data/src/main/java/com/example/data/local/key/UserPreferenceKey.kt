package com.example.data.local.key

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferenceKey {

    val EMAIL = stringPreferencesKey("email")

    val PASSWORD = stringPreferencesKey("password")

    val POINT = intPreferencesKey("point")

    val ROLE = stringPreferencesKey("role")

}