package com.example.data.remote.datasource.email

import com.example.data.remote.dto.email.request.EmailRequest
import kotlinx.coroutines.flow.Flow

interface EmailDatasource {
    suspend fun email(body: EmailRequest): Flow<Unit>
}