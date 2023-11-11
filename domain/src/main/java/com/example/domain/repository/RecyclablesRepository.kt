package com.example.domain.repository

import com.example.domain.model.recyclables.response.SearchResponseModel
import kotlinx.coroutines.flow.Flow

interface RecyclablesRepository {
    suspend fun search(search: String): Flow<SearchResponseModel>

    suspend fun saveSearchHistory(searchHistory: String)

    suspend fun getSearchHistory(): Flow<List<String>>

    suspend fun deleteSearchHistory(index: Int)

    suspend fun deleteAllSearchHistory()
}