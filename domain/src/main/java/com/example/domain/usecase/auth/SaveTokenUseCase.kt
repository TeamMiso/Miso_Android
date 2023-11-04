package com.example.domain.usecase.auth

import com.example.domain.model.auth.response.AuthLogInResponseModel
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(token: AuthLogInResponseModel) = kotlin.runCatching {
        authRepository.saveToken(token = token)
    }
}