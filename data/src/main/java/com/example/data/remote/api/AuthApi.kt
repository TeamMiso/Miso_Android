package com.example.data.remote.api

import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth")
    suspend fun authSignUp(
        @Body body: AuthSignUpRequest
    )
}