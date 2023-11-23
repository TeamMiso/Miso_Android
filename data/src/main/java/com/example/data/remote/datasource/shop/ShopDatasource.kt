package com.example.data.remote.datasource.shop

import com.example.data.remote.dto.shop.response.ShopList
import com.example.data.remote.dto.shop.response.ShopListDetailResponse
import com.example.data.remote.dto.shop.response.ShopListResponse
import kotlinx.coroutines.flow.Flow

interface ShopDatasource {
    suspend fun getShopList(): Flow<ShopListResponse>
    suspend fun getShopListDetail(id: Long): Flow<ShopListDetailResponse>
}