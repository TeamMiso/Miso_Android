package com.example.domain.usecase.shop

import android.util.Log
import com.example.domain.repository.ShopRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        Log.d("testt-inv",shopRepository.getShopList().toString())
        shopRepository.getShopList()
    }
}