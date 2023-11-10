package com.example.domain.usecase.recyclables

import com.example.domain.repository.RecyclablesRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke(search: String) = kotlin.runCatching {
        recyclablesRepository.search(search = search)
    }
}