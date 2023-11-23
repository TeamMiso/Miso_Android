package com.example.data.remote.datasource.recyclables

import com.example.data.remote.dto.recyclables.response.ResultResponse
import com.example.data.remote.dto.recyclables.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface RecyclablesDatasource {
    suspend fun search(search: String): Flow<SearchResponse>

    suspend fun result(recyclablesType: String): Flow<ResultResponse>
}