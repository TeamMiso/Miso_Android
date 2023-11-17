package com.example.data.remote.api

import com.example.data.remote.dto.inquiry.response.InquiryListDetailResponse
import com.example.data.remote.dto.shop.response.ShopList
import com.example.data.remote.dto.shop.response.ShopListDetailResponse
import com.example.data.remote.dto.shop.response.ShopListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopAPI {
    @GET("item")
    suspend fun getShopList(): ShopListResponse

    @GET("item/{id}")
    suspend fun getShopListDetail(
        @Path("id") id: Long
    ): ShopListDetailResponse
}