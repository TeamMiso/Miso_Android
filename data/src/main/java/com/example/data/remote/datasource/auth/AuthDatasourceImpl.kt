package com.example.data.remote.datasource.auth

import android.util.Log
import com.example.data.remote.api.AuthAPI
import com.example.data.remote.dto.auth.request.AuthLogInRequest
import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import com.example.data.remote.dto.auth.response.AuthLogInResponse
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDatasourceImpl @Inject constructor(
    private val api: AuthAPI
): AuthDatasource {
    override suspend fun authSignUp(body: AuthSignUpRequest): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.authSignUp(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun authLogIn(body: AuthLogInRequest): Flow<AuthLogInResponse> = flow {
        emit(
            MisoApiHandler<AuthLogInResponse>()
                .httpRequest { api.authLogIn(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun logout(): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.logout() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

}