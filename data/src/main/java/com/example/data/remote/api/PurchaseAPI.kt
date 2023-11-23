package com.example.data.remote.api

import com.example.data.remote.dto.purchase.response.PurchaseListResponse
import retrofit2.http.GET

interface PurchaseAPI {
    @GET
    suspend fun getPurchaseList(): PurchaseListResponse
}