package com.example.data.local.datasource.recyclables

import kotlinx.coroutines.flow.Flow

interface LocalRecyclablesDataSource {
    suspend fun getSearchHistory(): Flow<List<String>>

    suspend fun setSearchHistory(searchHistory: String)

    suspend fun removeSearchHistory(index: Int)

    suspend fun removeAllSearchHistory()
}