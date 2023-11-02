package com.example.data.remote.dto.auth.response

import com.example.domain.model.auth.response.AuthLogInResponseModel
import java.time.ZonedDateTime

data class AuthLogInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: ZonedDateTime,
    val refreshExp: ZonedDateTime
)

fun AuthLogInResponse.toLogInModel() =
    AuthLogInResponseModel(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        accessExp = this.accessExp,
        refreshExp = this.refreshExp
    )
