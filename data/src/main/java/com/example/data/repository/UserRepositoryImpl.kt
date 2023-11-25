package com.example.data.repository

import com.example.data.local.datasource.user.LocalUserDataSource
import com.example.data.remote.datasource.user.UserDatasource
import com.example.data.remote.dto.user.response.toUserModel
import com.example.domain.model.user.response.UserInfoResponseModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDatasource: UserDatasource
): UserRepository {
    override suspend fun getUserInfo(): Flow<UserInfoResponseModel> {
        return remoteUserDatasource.getUserInfo().map { it.toUserModel() }
    }

    override suspend fun saveUserInfo(userInfo: UserInfoResponseModel) {
        userInfo.let {
            localUserDataSource.setEmail(it.email)
            localUserDataSource.setPassword(it.password)
            localUserDataSource.setPoint(it.point)
            localUserDataSource.setRole(it.role)
        }
    }

    override suspend fun getRole(): Flow<String> {
        return localUserDataSource.getRole()
    }

    override suspend fun deleteUserInfo() {
        localUserDataSource.removeEmail()
        localUserDataSource.removePassword()
        localUserDataSource.removePoint()
        localUserDataSource.removeRole()
    }

    override suspend fun givePoint(): Flow<Unit> {
        return remoteUserDatasource.givePoint()
    }

    override suspend fun getPoint(): Flow<Int> {
        return localUserDataSource.getPoint()
    }
}