package com.example.data.remote.datasource.inquiry

import com.example.data.remote.dto.inquiry.request.InquiryRequest
import com.example.data.remote.dto.inquiry.response.InquiryListDetailResponse
import com.example.data.remote.dto.inquiry.response.InquiryListResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryDatasource {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>

    suspend fun getInquiryList(): Flow<InquiryListResponse>

    suspend fun getInquiryListAll(): Flow<InquiryListResponse>

    suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponse>

}