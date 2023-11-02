package com.example.data.remote.dto.auth.response

import java.time.ZonedDateTime

data class AuthLogInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: ZonedDateTime,
    val refreshExp: ZonedDateTime
)
