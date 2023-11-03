package com.example.domain.usecase.auth

import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthLogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: AuthLogInRequestModel) = kotlin.runCatching {
        authRepository.authLogIn(body = body)
    }
}