package com.example.domain.model.auth.request

data class AuthSignUpRequestModel(
    val email: String,
    val password: String,
    val passwordCheck: String
)