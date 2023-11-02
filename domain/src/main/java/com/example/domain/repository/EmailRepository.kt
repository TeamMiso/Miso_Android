package com.example.domain.repository

import com.example.domain.model.email.request.EmailRequestModel
import kotlinx.coroutines.flow.Flow

interface EmailRepository {
    suspend fun email(body: EmailRequestModel): Flow<Unit>
}