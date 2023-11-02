package com.example.di.module

import com.example.data.remote.datasource.auth.AuthDatasource
import com.example.data.remote.datasource.auth.AuthDatasourceImpl
import com.example.data.remote.datasource.email.EmailDatasource
import com.example.data.remote.datasource.email.EmailDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun provideAuthDatasource(
        authDatasourceImpl: AuthDatasourceImpl
    ): AuthDatasource

    @Binds
    abstract fun provideEmailDatasource(
        emailDatasourceImpl: EmailDatasourceImpl
    ): EmailDatasource
}