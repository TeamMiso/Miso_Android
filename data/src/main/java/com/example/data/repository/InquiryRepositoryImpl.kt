package com.example.data.repository

import com.example.data.remote.datasource.inquiry.InquiryDatasource
import com.example.domain.repository.InquiryRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class InquiryRepositoryImpl @Inject constructor(
    private val inquiryDatasource: InquiryDatasource
): InquiryRepository {
    override suspend fun requestInquiry(
        filePart: MultipartBody.Part?,
        inquiryPart: RequestBody
    ): Flow<Unit> {
        return inquiryDatasource.requestInquiry(
            filePart = filePart,
            inquiryPart = inquiryPart
        )
    }
}