package com.example.data.remote.datasource.shop

import com.example.data.remote.api.ShopAPI
import com.example.data.remote.dto.shop.response.ShopListDetailResponse
import com.example.data.remote.dto.shop.response.ShopListResponse
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ShopDatasourceImpl @Inject constructor(
    private val api: ShopAPI
): ShopDatasource{
    override suspend fun getShopList(): Flow<ShopListResponse> = flow {
        emit(
            MisoApiHandler<ShopListResponse>()
                .httpRequest { api.getShopList() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getShopListDetail(id: Long): Flow<ShopListDetailResponse> = flow {
        emit(
            MisoApiHandler<ShopListDetailResponse>()
                .httpRequest { api.getShopListDetail(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

}