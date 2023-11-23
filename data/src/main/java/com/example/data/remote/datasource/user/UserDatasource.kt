package com.example.data.remote.datasource.user

import com.example.data.remote.dto.user.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow

interface UserDatasource {
    suspend fun getUserInfo(): Flow<UserInfoResponse>

    suspend fun givePoint(): Flow<Unit>
}