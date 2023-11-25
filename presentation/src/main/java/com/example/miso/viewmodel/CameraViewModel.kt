package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.camera.request.CameraRequestModel
import com.example.domain.model.camera.response.CameraResponseModel
import com.example.domain.model.shop.response.ShopListResponseModel
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
            Log.d("testt-vm",bitmap.toString())
        }
    }
    fun getAiAnswer() = viewModelScope.launch {
        imgData.value = imgData.value.copy(image_url = swapBitmapFormat())

        aiResponseUseCase(imgData.value)
            .onSuccess {
                it.catch {remoteError ->
                    Log.d("shopList-vm",remoteError.toString())
                    _getAiAnswer.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("shopList-vm",response.toString())
                    _getAiAnswer.value = Event.Success(data = response)
                }
            }.onFailure {
                Log.d("shopList-vm","fail")
                _getAiAnswer.value = it.errorHandling()
            }
    }

    private fun swapBitmapFormat(): String{
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage
        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val bitmapData = byteArrayOutputStream.toByteArray().toString()
        Log.d("testt-vm", bitmapData)

        return bitmapData
    }

}
