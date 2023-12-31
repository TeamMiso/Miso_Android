package com.example.data.remote.datasource.inquiry

import com.example.data.remote.api.InquiryAPI
import com.example.data.remote.dto.inquiry.response.InquiryListDetailResponse
import com.example.data.remote.dto.inquiry.response.InquiryListResponse
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

    override suspend fun getInquiryList(): Flow<InquiryListResponse> = flow {
        emit(
            MisoApiHandler<InquiryListResponse>()
                .httpRequest { api.getInquiryList() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getInquiryListAll(): Flow<InquiryListResponse> = flow {
        emit(
            MisoApiHandler<InquiryListResponse>()
                .httpRequest { api.getInquiryListAll() }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponse> = flow {
        emit(
            MisoApiHandler<InquiryListDetailResponse>()
                .httpRequest { api.getInquiryListDetail(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun adopt(id: Long): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.adopt(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun unadopt(id: Long): Flow<Unit> = flow {
        emit(
            MisoApiHandler<Unit>()
                .httpRequest { api.unadopt(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}