package com.example.data.repository

import com.example.data.remote.datasource.email.EmailDatasource
import com.example.data.remote.dto.email.request.EmailRequest
import com.example.domain.model.email.request.EmailRequestModel
import com.example.domain.repository.EmailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val emailDatasource: EmailDatasource
): EmailRepository {
    override suspend fun email(body: EmailRequestModel): Flow<Unit> {
        return emailDatasource.email(
            body = EmailRequest(
                randomKey = body.randomKey
            )
        )
    }
}