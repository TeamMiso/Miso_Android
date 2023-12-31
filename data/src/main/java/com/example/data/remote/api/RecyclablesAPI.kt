package com.example.data.remote.api

import com.example.data.remote.dto.recyclables.response.RecyclablesListResponse
import com.example.data.remote.dto.recyclables.response.ResultResponse
import com.example.data.remote.dto.recyclables.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecyclablesAPI {

    @GET("recyclables/search")
    suspend fun search(
        @Query("searchValue") search: String
    ): SearchResponse

    @GET("recyclables")
    suspend fun result(
        @Query("recyclablesType") recyclablesType: String
    ): ResultResponse

    @GET("recyclables/all")
    suspend fun getAllRecyclablesList(): RecyclablesListResponse
}