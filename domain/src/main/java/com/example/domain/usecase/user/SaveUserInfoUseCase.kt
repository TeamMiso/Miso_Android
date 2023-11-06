package com.example.domain.usecase.user

import com.example.domain.model.user.response.UserInfoResponseModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userInfo: UserInfoResponseModel) = kotlin.runCatching {
        userRepository.saveUserInfo(userInfo = userInfo)
    }
}