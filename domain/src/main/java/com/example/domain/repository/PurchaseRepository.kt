package com.example.domain.repository

import com.example.domain.model.purchase.response.PurchaseListResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PurchaseRepository {
    suspend fun getPurchase(): Flow<PurchaseListResponseModel>

    suspend fun buyItem(id: Long): Flow<Unit>
}