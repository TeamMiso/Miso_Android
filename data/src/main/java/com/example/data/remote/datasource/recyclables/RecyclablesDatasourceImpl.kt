package com.example.data.remote.datasource.recyclables

import com.example.data.remote.api.RecyclablesAPI
import com.example.data.remote.dto.recyclables.response.SearchResponse
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecyclablesDatasourceImpl @Inject constructor(
    private val api: RecyclablesAPI
): RecyclablesDatasource {
    override suspend fun search(search: String): Flow<SearchResponse> = flow {
        emit(
            MisoApiHandler<SearchResponse>()
                .httpRequest { api.search(search = search) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}