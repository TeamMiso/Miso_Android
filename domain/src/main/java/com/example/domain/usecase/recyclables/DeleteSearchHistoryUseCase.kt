package com.example.domain.usecase.recyclables

import com.example.domain.repository.RecyclablesRepository
import javax.inject.Inject

class DeleteSearchHistoryUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(index: Int) = kotlin.runCatching {
        recyclablesRepository.deleteSearchHistory(index = index)
    }
}