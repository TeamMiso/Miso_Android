package com.example.domain.repository

import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.domain.model.auth.response.AuthLogInResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit>

    suspend fun authLogIn(body: AuthLogInRequestModel): Flow<AuthLogInResponseModel>

    suspend fun saveToken(token: AuthLogInResponseModel)
}