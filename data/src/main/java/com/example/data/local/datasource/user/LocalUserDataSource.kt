package com.example.data.local.datasource.user

import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {

    suspend fun getEmail(): Flow<String>

    suspend fun setEmail(email: String)

    suspend fun removeEmail()

    suspend fun getPassword(): Flow<String>

    suspend fun setPassword(password: String)

    suspend fun removePassword()

    suspend fun getPoint(): Flow<Int>

    suspend fun setPoint(point: Int)

    suspend fun removePoint()

    suspend fun getRole(): Flow<String>

    suspend fun setRole(role: String)

    suspend fun removeRole()
}