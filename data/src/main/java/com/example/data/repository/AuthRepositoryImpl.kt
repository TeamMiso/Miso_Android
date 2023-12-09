package com.example.data.repository

import com.example.data.local.datasource.auth.LocalAuthDataSource
import com.example.data.remote.datasource.auth.AuthDatasource
import com.example.data.remote.dto.auth.request.AuthLogInRequest
import com.example.data.remote.dto.auth.request.AuthSignUpRequest
import com.example.data.remote.dto.auth.response.toLogInModel
import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.domain.model.auth.response.AuthLogInResponseModel
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localAuthDataSource: LocalAuthDataSource,
    private val remoteAuthDatasource: AuthDatasource
): AuthRepository {
    override suspend fun authSignUp(body: AuthSignUpRequestModel): Flow<Unit> {
        return remoteAuthDatasource.authSignUp(
            body = AuthSignUpRequest(
                email = body.email,
                password = body.password,
                passwordCheck = body.passwordCheck
            )
        )
    }

    override suspend fun authLogIn(body: AuthLogInRequestModel): Flow<AuthLogInResponseModel> {
        return remoteAuthDatasource.authLogIn(
            body = AuthLogInRequest(
                email = body.email,
                password = body.password,
                deviceToken = body.deviceToken
            )
        ).map { it.toLogInModel() }
    }

    override suspend fun saveToken(token: AuthLogInResponseModel) {
        token.let {
            localAuthDataSource.setAccessToken(it.accessToken)
            localAuthDataSource.setAccessTime(it.accessExp)
            localAuthDataSource.setRefreshToken(it.refreshToken)
            localAuthDataSource.setRefreshTime(it.refreshExp)
        }
    }

    override suspend fun getAccessToken(): Flow<String> {
        return localAuthDataSource.getAccessToken()
    }

    override suspend fun logout(): Flow<Unit> {
        return remoteAuthDatasource.logout()
    }

    override suspend fun deleteToken() {
        localAuthDataSource.removeAccessToken()
        localAuthDataSource.removeRefreshToken()
        localAuthDataSource.removeAccessTime()
        localAuthDataSource.removeRefreshTime()
    }
}