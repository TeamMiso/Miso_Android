package com.example.di.module

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.CameraRepositoryImpl
import com.example.data.repository.EmailRepositoryImpl
import com.example.data.repository.InquiryRepositoryImpl
import com.example.data.repository.RecyclablesRepositoryImpl
import com.example.data.repository.ShopRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.CameraRepository
import com.example.domain.repository.EmailRepository
import com.example.domain.repository.InquiryRepository
import com.example.domain.repository.RecyclablesRepository
import com.example.domain.repository.ShopRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideEmailRepository(
        emailRepositoryImpl: EmailRepositoryImpl
    ): EmailRepository

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun provideInquiryRepository(
        inquiryRepositoryImpl: InquiryRepositoryImpl
    ): InquiryRepository

    @Binds
    abstract fun provideRecyclablesRepository(
        recyclablesRepositoryImpl: RecyclablesRepositoryImpl
    ): RecyclablesRepository

    @Binds
    abstract fun provideShopRepository(
        shopRepositoryImpl: ShopRepositoryImpl
    ): ShopRepository

    @Binds
    abstract fun provideCameraRepository(
        cameraRepositoryImpl: CameraRepositoryImpl
    ): CameraRepository
}