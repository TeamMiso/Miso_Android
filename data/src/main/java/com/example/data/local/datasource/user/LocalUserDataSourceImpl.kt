package com.example.data.local.datasource.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.data.local.key.UserPreferenceKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): LocalUserDataSource {
    override suspend fun getEmail(): Flow<String> = dataStore.data.map {
        it[UserPreferenceKey.EMAIL] ?: ""
    }

    override suspend fun setEmail(email: String) {
        dataStore.edit {
            it[UserPreferenceKey.EMAIL] = email
        }
    }

    override suspend fun removeEmail() {
        dataStore.edit {
            it.remove(UserPreferenceKey.EMAIL)
        }
    }

    override suspend fun getPassword(): Flow<String> = dataStore.data.map {
        it[UserPreferenceKey.PASSWORD] ?: ""
    }

    override suspend fun setPassword(password: String) {
        dataStore.edit {
            it[UserPreferenceKey.PASSWORD] = password
        }
    }

    override suspend fun removePassword() {
        dataStore.edit {
            it.remove(UserPreferenceKey.PASSWORD)
        }
    }

    override suspend fun getPoint(): Flow<Int> = dataStore.data.map {
        it[UserPreferenceKey.POINT] ?: 0
    }

    override suspend fun setPoint(point: Int) {
        dataStore.edit {
            it[UserPreferenceKey.POINT] = point
        }
    }

    override suspend fun removePoint() {
        dataStore.edit {
            it.remove(UserPreferenceKey.POINT)
        }
    }

    override suspend fun getRole(): Flow<String> = dataStore.data.map {
        it[UserPreferenceKey.ROLE] ?: ""
    }

    override suspend fun setRole(role: String) {
        dataStore.edit {
            it[UserPreferenceKey.ROLE] = role
        }
    }

    override suspend fun removeRole() {
        dataStore.edit {
            it.remove(UserPreferenceKey.ROLE)
        }
    }
}