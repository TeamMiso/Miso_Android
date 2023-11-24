package com.example.data.remote.dto.camera.response

import com.example.data.remote.dto.auth.response.AuthLogInResponse
import com.example.domain.model.auth.response.AuthLogInResponseModel
import com.example.domain.model.camera.response.CameraResponseModel

data class CameraResponse(
    val best_class: String
)
fun CameraResponse.toAiResponseModel() =
    CameraResponseModel(
        best_class = this.best_class
    )