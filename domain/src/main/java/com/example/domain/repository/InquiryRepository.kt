package com.example.domain.repository

import com.example.domain.model.inquiry.request.InquiryRequestModel
import com.example.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.example.domain.model.inquiry.response.InquiryListResponseModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryRepository {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>

    suspend fun getInquiryList(): Flow<InquiryListResponseModel>

    suspend fun getInquiryListAll(): Flow<InquiryListResponseModel>

    suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponseModel>

    suspend fun adopt(id: Long): Flow<Unit>

    suspend fun unadopt(id: Long): Flow<Unit>
}