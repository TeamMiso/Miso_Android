package com.example.domain.repository

import com.example.domain.model.auth.request.AuthSignUpRequestModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit>
}