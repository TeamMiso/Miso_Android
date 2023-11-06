package com.example.data.remote.api

import com.example.data.remote.dto.user.response.UserInfoResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("user")
    suspend fun getUserInfo(): UserInfoResponse
}