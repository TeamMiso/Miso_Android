package com.example.data.remote.api

import com.example.data.remote.dto.email.request.EmailRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailAPI {
    @POST("email")
    suspend fun email(
        @Body body: EmailRequest
    )
}