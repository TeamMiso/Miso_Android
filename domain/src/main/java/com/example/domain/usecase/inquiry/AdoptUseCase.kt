package com.example.domain.usecase.inquiry

import com.example.domain.repository.InquiryRepository
import javax.inject.Inject

class AdoptUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository
) {
    suspend operator fun invoke(id: Long) = kotlin.runCatching {
        inquiryRepository.adopt(id = id)
    }
}