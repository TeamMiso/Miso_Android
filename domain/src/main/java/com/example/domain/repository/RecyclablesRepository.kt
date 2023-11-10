package com.example.domain.repository

import com.example.domain.model.recyclables.response.SearchResponseModel
import kotlinx.coroutines.flow.Flow

interface RecyclablesRepository {
    suspend fun search(search: String): Flow<SearchResponseModel>
}