package com.example.data.remote.datasource.inquiry

import com.example.data.remote.api.InquiryAPI
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class InquiryDatasourceImpl @Inject constructor(
    private val api: InquiryAPI
): InquiryDatasource {
    override suspend fun requestInquiry(
        filePart: MultipartBody.Part?,
        inquiryPart: RequestBody
    ): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.requestInquiry(
                    filePart = filePart,
                    inquiryPart = inquiryPart
                ) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}