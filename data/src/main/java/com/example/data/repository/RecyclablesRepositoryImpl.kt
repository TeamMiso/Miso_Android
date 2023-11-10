package com.example.data.repository

import com.example.data.local.datasource.recyclables.LocalRecyclablesDataSource
import com.example.data.remote.datasource.recyclables.RecyclablesDatasource
import com.example.data.remote.dto.recyclables.response.toSearchModel
import com.example.domain.model.recyclables.response.SearchResponseModel
import com.example.domain.repository.RecyclablesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecyclablesRepositoryImpl @Inject constructor(
    private val remoteRecyclablesDatasource: RecyclablesDatasource
): RecyclablesRepository {
    override suspend fun search(search: String): Flow<SearchResponseModel> {
        return remoteRecyclablesDatasource.search(search = search).map { it.toSearchModel() }
    }
}