package com.example.domain.usecase.recyclables

import com.example.domain.repository.RecyclablesRepository
import javax.inject.Inject

class GetAllRecyclablesListUseCase @Inject constructor(
    private val recyclablesRepository: RecyclablesRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        recyclablesRepository.getAllRecyclablesList()
    }
}