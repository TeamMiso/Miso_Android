package com.example.domain.usecase.camera

import com.example.domain.model.camera.request.CameraRequestModel
import com.example.domain.repository.CameraRepository
import javax.inject.Inject

class AiResponseUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
){
    suspend operator fun invoke(body: CameraRequestModel) = kotlin.runCatching {
        cameraRepository.getAiResponse(body = body)
    }
}