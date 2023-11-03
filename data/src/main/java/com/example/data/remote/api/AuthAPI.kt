package com.example.data.remote.api

import com.example.data.remote.dto.auth.request.AuthLogInRequest
import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import com.example.data.remote.dto.auth.response.AuthLogInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth")
    suspend fun authSignUp(
        @Body body: AuthSignUpRequest
    )

    @POST("auth/signIn")
    suspend fun authLogIn(
        @Body body: AuthLogInRequest
    ): AuthLogInResponse
}