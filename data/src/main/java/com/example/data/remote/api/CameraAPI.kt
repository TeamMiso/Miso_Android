package com.example.data.remote.api

import com.example.data.remote.dto.camera.request.CameraRequest
import com.example.data.remote.dto.camera.response.CameraResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CameraAPI {
    @POST("process_image")
    suspend fun getAiResponse(
        @Body body: CameraRequest
    ): CameraResponse
}