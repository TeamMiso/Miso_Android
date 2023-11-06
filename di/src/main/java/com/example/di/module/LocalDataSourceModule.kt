package com.example.di.module

import com.example.data.local.datasource.auth.LocalAuthDataSource
import com.example.data.local.datasource.auth.LocalAuthDataSourceImpl
import com.example.data.local.datasource.user.LocalUserDataSource
import com.example.data.local.datasource.user.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun provideLocalAuthDataSource(
        localAuthDataSourceImpl: LocalAuthDataSourceImpl
    ): LocalAuthDataSource

    @Binds
    abstract fun provideLocalUserDataSource(
        localUserDataSourceImpl: LocalUserDataSourceImpl
    ): LocalUserDataSource
}