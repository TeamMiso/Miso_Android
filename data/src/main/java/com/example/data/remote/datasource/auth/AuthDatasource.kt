package com.example.data.remote.datasource.auth

import com.example.data.remote.dto.auth.request.AuthLogInRequest
import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import com.example.data.remote.dto.auth.response.AuthLogInResponse
import kotlinx.coroutines.flow.Flow

interface AuthDatasource {
    suspend fun authSignUp(body: AuthSignUpRequest): Flow<Unit>

    suspend fun authLogIn(body: AuthLogInRequest): Flow<AuthLogInResponse>

    suspend fun logout(): Flow<Unit>

}