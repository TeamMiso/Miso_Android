package com.example.domain.usecase.inquiry

import com.example.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.example.domain.repository.InquiryRepository
import javax.inject.Inject

class GetInquiryListDetailUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        inquiryRepository.getInquiryListDetail(id = id)
    }
}