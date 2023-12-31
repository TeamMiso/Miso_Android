package com.example.domain.model.user.response

import java.util.UUID

data class UserInfoResponseModel(
    val id: UUID,
    val email: String,
    val password: String,
    val point: Int,
    val role: String
)
