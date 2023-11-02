package com.example.data.repository

import android.util.Log
import com.example.data.remote.datasource.auth.AuthDatasource
import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDatasource: AuthDatasource
): AuthRepository {
    override suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit> {
        return authDatasource.authSignUp(
            body = AuthSignUpRequest(
                email = body.email,
                password = body.password,
                passwordCheck = body.passwordCheck
            )
        )
    }

}