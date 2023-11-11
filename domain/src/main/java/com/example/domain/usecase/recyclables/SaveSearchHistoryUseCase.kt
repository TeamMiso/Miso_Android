package com.example.domain.usecase.recyclables

import com.example.domain.repository.RecyclablesRepository
import javax.inject.Inject

class SaveSearchHistoryUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(searchHistory: String) = kotlin.runCatching {
        recyclablesRepository.saveSearchHistory(searchHistory = searchHistory)
    }
}