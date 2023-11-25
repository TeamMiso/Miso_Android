package com.example.data.remote.datasource.purchase

import com.example.data.remote.dto.purchase.response.PurchaseListResponse
import kotlinx.coroutines.flow.Flow

interface PurchaseDatasource {
    suspend fun getPurchaseList(): Flow<PurchaseListResponse>

    suspend fun buyItem(id: Long): Flow<Unit>
}