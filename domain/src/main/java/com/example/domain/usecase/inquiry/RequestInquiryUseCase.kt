package com.example.domain.usecase.inquiry

import com.example.domain.model.inquiry.request.InquiryRequestModel
import com.example.domain.repository.InquiryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RequestInquiryUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(filePart: MultipartBody.Part?, inquiryPart: RequestBody) = kotlin.runCatching {
        inquiryRepository.requestInquiry(filePart = filePart, inquiryPart = inquiryPart)
    }
}