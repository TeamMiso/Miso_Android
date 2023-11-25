package com.example.domain.usecase.purchase

import com.example.domain.repository.PurchaseRepository
import javax.inject.Inject

class BuyItemUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        purchaseRepository.buyItem(id = id)
    }
}