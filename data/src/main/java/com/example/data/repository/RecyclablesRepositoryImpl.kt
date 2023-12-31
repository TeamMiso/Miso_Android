package com.example.data.repository

import com.example.data.local.datasource.recyclables.LocalRecyclablesDataSource
import com.example.data.remote.datasource.recyclables.RecyclablesDatasource
import com.example.data.remote.dto.recyclables.response.toRecyclablesModel
import com.example.data.remote.dto.recyclables.response.toResultModel
import com.example.data.remote.dto.recyclables.response.toSearchModel
import com.example.domain.model.recyclables.response.RecyclablesListResponseModel
import com.example.domain.model.recyclables.response.ResultResponseModel
import com.example.domain.model.recyclables.response.SearchResponseModel
import com.example.domain.repository.RecyclablesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecyclablesRepositoryImpl @Inject constructor(
    private val localRecyclablesDataSource: LocalRecyclablesDataSource,
    private val remoteRecyclablesDatasource: RecyclablesDatasource
): RecyclablesRepository {
    override suspend fun search(search: String): Flow<SearchResponseModel> {
        return remoteRecyclablesDatasource.search(search = search).map { it.toSearchModel() }
    }

    override suspend fun result(recyclablesType: String): Flow<ResultResponseModel> {
        return remoteRecyclablesDatasource.result(recyclablesType = recyclablesType).map { it.toResultModel() }
    }

    override suspend fun saveSearchHistory(searchHistory: String) {
        searchHistory.let {
            localRecyclablesDataSource.setSearchHistory(it)
        }
    }

    override suspend fun getSearchHistory(): Flow<List<String>> {
        return localRecyclablesDataSource.getSearchHistory()
    }

    override suspend fun deleteSearchHistory(index: Int) {
        index.let {
            localRecyclablesDataSource.removeSearchHistory(it)
        }
    }

    override suspend fun deleteAllSearchHistory() {
        localRecyclablesDataSource.removeAllSearchHistory()
    }

    override suspend fun getAllRecyclablesList(): Flow<RecyclablesListResponseModel> {
        return remoteRecyclablesDatasource.getAllRecyclablesList().map { it.toRecyclablesModel() }
    }
}