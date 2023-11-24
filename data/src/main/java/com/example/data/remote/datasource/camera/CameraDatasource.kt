package com.example.data.remote.datasource.camera

import com.example.data.remote.dto.camera.request.CameraRequest
import com.example.data.remote.dto.camera.response.CameraResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body

interface CameraDatasource {
    suspend fun getAiResponse(body: CameraRequest): Flow<CameraResponse>
}