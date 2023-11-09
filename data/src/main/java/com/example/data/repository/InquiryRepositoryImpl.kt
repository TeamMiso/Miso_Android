package com.example.data.repository

import android.util.Log
import com.example.data.remote.datasource.inquiry.InquiryDatasource
import com.example.data.remote.dto.inquiry.response.toInquiryModel
import com.example.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.example.domain.model.inquiry.response.InquiryListResponseModel
import com.example.domain.repository.InquiryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getInquiryList(): Flow<InquiryListResponseModel> {
        return inquiryDatasource.getInquiryList().map { it.toInquiryModel() }
    }

    override suspend fun getInquiryListAll(): Flow<InquiryListResponseModel> {
        return inquiryDatasource.getInquiryListAll().map { it.toInquiryModel() }
    }

    override suspend fun getInquiryListDetail(id: Long): Flow<InquiryListDetailResponseModel> {
        return inquiryDatasource.getInquiryListDetail(id = id).map { it.toInquiryModel() }
    }

    override suspend fun adopt(id: Long): Flow<Unit> {
        return inquiryDatasource.adopt(id = id)
    }

    override suspend fun unadopt(id: Long): Flow<Unit> {
        return inquiryDatasource.unadopt(id = id)
    }
}