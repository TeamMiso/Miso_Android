package com.example.data.remote.datasource.camera

import com.example.data.remote.api.CameraAPI
import com.example.data.remote.dto.camera.request.CameraRequest
import com.example.data.remote.dto.camera.response.CameraResponse
import com.example.data.util.MisoApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class CameraDatasourceImpl @Inject constructor(
    @Named("AI")
    private val api: CameraAPI
): CameraDatasource {
    override suspend fun getAiResponse(body: CameraRequest): Flow<CameraResponse> = flow {
        emit(
            MisoApiHandler<CameraResponse>()
                .httpRequest { api.getAiResponse(body = body) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

}