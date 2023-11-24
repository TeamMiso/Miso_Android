package com.example.domain.repository

import com.example.domain.model.camera.request.CameraRequestModel
import com.example.domain.model.camera.response.CameraResponseModel
import com.example.domain.model.email.request.EmailRequestModel
import kotlinx.coroutines.flow.Flow

interface CameraRepository {
    suspend fun getAiResponse(body: CameraRequestModel): Flow<CameraResponseModel>
}