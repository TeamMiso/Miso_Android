package com.example.data.repository

import com.example.data.remote.datasource.camera.CameraDatasource
import com.example.data.remote.dto.camera.request.CameraRequest
import com.example.data.remote.dto.camera.response.toAiResponseModel
import com.example.domain.model.camera.request.CameraRequestModel
import com.example.domain.model.camera.response.CameraResponseModel
import com.example.domain.repository.CameraRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val cameraDatasource: CameraDatasource
): CameraRepository{
    override suspend fun getAiResponse(body: CameraRequestModel): Flow<CameraResponseModel> {
        return cameraDatasource.getAiResponse(
            body = CameraRequest(
                image_url = body.image_url
            )
        ).map { it.toAiResponseModel()}
    }
}