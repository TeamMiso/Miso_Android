package com.example.domain.usecase.inquiry

import com.example.domain.repository.InquiryRepository
import javax.inject.Inject

class GetInquiryListAllUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        inquiryRepository.getInquiryListAll()
    }
}