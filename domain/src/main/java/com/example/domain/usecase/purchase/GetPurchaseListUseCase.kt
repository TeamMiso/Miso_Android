package com.example.domain.usecase.purchase

import com.example.domain.repository.PurchaseRepository
import javax.inject.Inject

class GetPurchaseListUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        purchaseRepository.getPurchase()
    }
}