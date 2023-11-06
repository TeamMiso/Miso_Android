package com.example.data.remote.datasource.user

import com.example.data.remote.api.UserAPI
import com.example.data.remote.dto.user.response.UserInfoResponse
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDatasourceImpl @Inject constructor(
    private val api: UserAPI
): UserDatasource {
    override suspend fun getUserInfo(): Flow<UserInfoResponse> = flow {
        emit(
            MisoApiHandler<UserInfoResponse>()
                .httpRequest { api.getUserInfo() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}