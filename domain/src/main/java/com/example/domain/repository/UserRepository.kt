package com.example.domain.repository

import com.example.domain.model.user.response.UserInfoResponseModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserInfo(): Flow<UserInfoResponseModel>

    suspend fun saveUserInfo(userInfo: UserInfoResponseModel)

    suspend fun getRole(): Flow<String>

    suspend fun deleteUserInfo()

    suspend fun givePoint(): Flow<Unit>
}