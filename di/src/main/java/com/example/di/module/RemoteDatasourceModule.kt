package com.example.di.module

import com.example.data.remote.datasource.auth.AuthDatasource
import com.example.data.remote.datasource.auth.AuthDatasourceImpl
import com.example.data.remote.datasource.camera.CameraDatasource
import com.example.data.remote.datasource.camera.CameraDatasourceImpl
import com.example.data.remote.datasource.email.EmailDatasource
import com.example.data.remote.datasource.email.EmailDatasourceImpl
import com.example.data.remote.datasource.inquiry.InquiryDatasource
import com.example.data.remote.datasource.inquiry.InquiryDatasourceImpl
import com.example.data.remote.datasource.recyclables.RecyclablesDatasource
import com.example.data.remote.datasource.recyclables.RecyclablesDatasourceImpl
import com.example.data.remote.datasource.shop.ShopDatasource
import com.example.data.remote.datasource.shop.ShopDatasourceImpl
import com.example.data.remote.datasource.user.UserDatasource
import com.example.data.remote.datasource.user.UserDatasourceImpl
import com.example.data.repository.ShopRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDatasourceModule {

    @Binds
    abstract fun provideAuthDatasource(
        authDatasourceImpl: AuthDatasourceImpl
    ): AuthDatasource

    @Binds
    abstract fun provideEmailDatasource(
        emailDatasourceImpl: EmailDatasourceImpl
    ): EmailDatasource

    @Binds
    abstract fun provideUserDatasource(
        userDataSourceImpl: UserDatasourceImpl
    ): UserDatasource

    @Binds
    abstract fun provideInquiryDatasource(
        inquiryDatasourceImpl: InquiryDatasourceImpl
    ): InquiryDatasource

    @Binds
    abstract fun provideRecyclablesDatasource(
        recyclablesDatasourceImpl: RecyclablesDatasourceImpl
    ): RecyclablesDatasource

    @Binds
    abstract fun provideShopDatasource(
        shopDatasourceImpl: ShopDatasourceImpl
    ): ShopDatasource

    @Binds
    abstract fun provideCameraDatasource(
        cameraDatasourceImpl: CameraDatasourceImpl
    ): CameraDatasource
}