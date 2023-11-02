package com.example.data.remote.dto.auth.request

data class AuthSignUpRequest(
    val email: String,
    val password: String,
    val passwordCheck: String
)