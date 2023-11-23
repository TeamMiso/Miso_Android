package com.example.data.remote.datasource.purchase

import com.example.data.remote.api.PurchaseAPI
import com.example.data.remote.dto.purchase.response.PurchaseListResponse
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PurchaseDatasourceImpl @Inject constructor(
    private val api: PurchaseAPI
): PurchaseDatasource {
    override suspend fun getPurchaseList(): Flow<PurchaseListResponse> = flow {
        emit(
            MisoApiHandler<PurchaseListResponse>()
                .httpRequest { api.getPurchaseList() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

}