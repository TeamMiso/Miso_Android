package com.example.data.remote.datasource.auth

import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import kotlinx.coroutines.flow.Flow

interface AuthDatasource {
    suspend fun authSignUp(body: AuthSignUpRequest): Flow<Unit>

}