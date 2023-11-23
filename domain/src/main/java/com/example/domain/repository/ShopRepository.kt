package com.example.domain.repository

import com.example.domain.model.shop.response.ShopListDetailResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.model.shop.response.ShopListResponseModel
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    suspend fun getShopList(): Flow<ShopListResponseModel>
    suspend fun getShopListDetail(id: Long): Flow<ShopListDetailResponseModel>
}