package com.example.domain.usecase.recyclables

import com.example.domain.repository.RecyclablesRepository
import javax.inject.Inject

class ResultUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(recyclablesType: String) = kotlin.runCatching {
        recyclablesRepository.result(recyclablesType = recyclablesType)
    }
}