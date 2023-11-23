package com.example.data.repository

import android.util.Log
import com.example.data.remote.datasource.shop.ShopDatasource
import com.example.data.remote.dto.shop.response.ShopListResponse
import com.example.data.remote.dto.shop.response.toShopModel
import com.example.domain.model.shop.response.ShopListDetailResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.model.shop.response.ShopListResponseModel
import com.example.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val shopDatasource: ShopDatasource
): ShopRepository {
    override suspend fun getShopList(): Flow<ShopListResponseModel> {
        Log.d("testt-impl",shopDatasource.getShopList().toString())
        return shopDatasource.getShopList().map { it.toShopModel() }
    }

    override suspend fun getShopListDetail(id: Long): Flow<ShopListDetailResponseModel> {
        return shopDatasource.getShopListDetail(id = id).map { it.toShopModel() }
    }

}