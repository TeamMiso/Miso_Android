package com.example.data.remote.api

import com.example.data.remote.dto.purchase.response.PurchaseListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PurchaseAPI {
    @GET("purchase")
    suspend fun getPurchaseList(): PurchaseListResponse

    @POST("purchase/{id}")
    suspend fun buyItem(
        @Path("id") id: Long
    )
}