package com.example.data.repository

import com.example.data.remote.datasource.purchase.PurchaseDatasource
import com.example.data.remote.dto.purchase.response.toPurchaseModel
import com.example.domain.model.purchase.response.PurchaseListResponseModel
import com.example.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val purchaseDatasource: PurchaseDatasource
): PurchaseRepository {
    override suspend fun getPurchase(): Flow<PurchaseListResponseModel> {
        return purchaseDatasource.getPurchaseList().map { it.toPurchaseModel() }
    }

    override suspend fun buyItem(id: Long): Flow<Unit> {
        return purchaseDatasource.buyItem(id = id)
    }

}