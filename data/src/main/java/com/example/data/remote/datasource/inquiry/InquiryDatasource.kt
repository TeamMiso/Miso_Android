package com.example.data.remote.datasource.inquiry

import com.example.data.remote.dto.inquiry.request.InquiryRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface InquiryDatasource {
    suspend fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody): Flow<Unit>
}