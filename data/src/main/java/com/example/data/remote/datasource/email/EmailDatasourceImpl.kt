package com.example.data.remote.datasource.email

import com.example.data.remote.api.EmailAPI
import com.example.data.remote.dto.email.request.EmailRequest
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EmailDatasourceImpl @Inject constructor(
    private val api: EmailAPI
): EmailDatasource {
    override suspend fun email(body: EmailRequest): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.email(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}