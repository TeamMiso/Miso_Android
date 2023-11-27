package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.camera.request.CameraRequestModel
import com.example.domain.model.camera.response.CameraResponseModel
import com.example.domain.usecase.camera.AiResponseUseCase
import com.example.miso.ui.camera.state.CameraState
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.io.encoding.ExperimentalEncodingApi

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val aiResponseUseCase: AiResponseUseCase
) : ViewModel() {
    private val _capturedImgBitmapState = MutableStateFlow(CameraState())
    val captureImgBitmapState = _capturedImgBitmapState.asStateFlow()

    private val _getAiAnswer = MutableStateFlow<Event<CameraResponseModel>>(Event.Loading)
    val getAiAnswer = _getAiAnswer.asStateFlow()

    private val imgData = MutableStateFlow(CameraRequestModel(""))

    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _capturedImgBitmapState.value.capturedImage?.recycle()
            _capturedImgBitmapState.value = _capturedImgBitmapState.value.copy(capturedImage = bitmap)
        }
    }
    fun getAiAnswer() = viewModelScope.launch {
        imgData.value = imgData.value.copy(image_url = swapBitmapFormat())
        Log.d("cameraAi-imgData",imgData.value.toString())

        aiResponseUseCase(imgData.value)
            .onSuccess {
                it.catch {remoteError ->
                    Log.d("cameraAi-vm",remoteError.toString())
                    _getAiAnswer.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("cameraAi-vm",response.toString())
                    _getAiAnswer.value = Event.Success(data = response)
                }
            }.onFailure {
                Log.d("cameraAi-vm","fail")
                _getAiAnswer.value = it.errorHandling()
            }
    }

    private fun swapBitmapFormat(): String{
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage

        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)

        val swapBitmapData = byteArrayOutputStream.toByteArray()

        val base64String = Base64.encodeToString(swapBitmapData, Base64.NO_WRAP)
        Log.d("cameraAi-base64", base64String)

        return base64String
    }

}
