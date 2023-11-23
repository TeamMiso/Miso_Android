package com.example.domain.usecase.shop

import com.example.domain.repository.ShopRepository
import javax.inject.Inject

class GetShopListDetailUseCase @Inject constructor(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        shopRepository.getShopListDetail(id = id)
    }
}