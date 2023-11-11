package com.example.data.local.datasource.recyclables

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.data.local.key.RecyclablesPreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRecyclablesDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalRecyclablesDataSource {
    override suspend fun getSearchHistory(): Flow<List<String>> = dataStore.data.map {
        it[RecyclablesPreferenceKey.SEARCH_HISTORY]?.let { searchHistoryJson ->
            val type = object : TypeToken<List<String>>() {}.type
            val searchHistoryList = Gson().fromJson<List<String>>(searchHistoryJson, type)
            searchHistoryList ?: emptyList()
        } ?: run {
            emptyList()
        }
    }

    override suspend fun setSearchHistory(searchHistory: String) {
        dataStore.edit {
            val currentSearchHistory =
                it[RecyclablesPreferenceKey.SEARCH_HISTORY]?.let { searchHistoryJson ->
                    Gson().fromJson(searchHistoryJson, List::class.java) as? MutableList<String>
                } ?: mutableListOf()

            currentSearchHistory.add(searchHistory)
            it[RecyclablesPreferenceKey.SEARCH_HISTORY] = Gson().toJson(currentSearchHistory)
        }
    }

    override suspend fun removeSearchHistory(index: Int) {
        dataStore.edit { preferences ->
            val currentSearchHistory = preferences[RecyclablesPreferenceKey.SEARCH_HISTORY]
                ?.let { searchHistoryJson ->
                    Gson().fromJson(searchHistoryJson, List::class.java) as? MutableList<String>
                } ?: mutableListOf()

            val updatedSearchHistory = currentSearchHistory.filterIndexed { i, _ -> i != index }

            preferences[RecyclablesPreferenceKey.SEARCH_HISTORY] = Gson().toJson(updatedSearchHistory)
        }
    }

    override suspend fun removeAllSearchHistory() {
        dataStore.edit {
            it.remove(RecyclablesPreferenceKey.SEARCH_HISTORY)
        }
    }
}