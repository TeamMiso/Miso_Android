package com.example.domain.model.auth.response

import java.time.ZonedDateTime

data class AuthLogInResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: ZonedDateTime,
    val refreshExp: ZonedDateTime
)
