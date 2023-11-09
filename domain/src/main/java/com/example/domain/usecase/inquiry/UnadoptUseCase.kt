package com.example.domain.usecase.inquiry

import com.example.domain.repository.InquiryRepository
import javax.inject.Inject

class UnadoptUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        inquiryRepository.unadopt(id = id)
    }
}