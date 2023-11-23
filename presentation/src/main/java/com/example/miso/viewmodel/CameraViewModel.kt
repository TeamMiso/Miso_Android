package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.camera.response.CameraResponseModel
import com.example.domain.model.shop.response.ShopListResponseModel
import com.example.domain.usecase.camera.AiResponseUseCase
import com.example.miso.ui.camera.state.CameraState
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    aiResponseUseCase: AiResponseUseCase
) : ViewModel() {
    private val _capturedImgBitmapState = MutableStateFlow(CameraState())
    val captureImgBitmapState = _capturedImgBitmapState.asStateFlow()

    private val _getAiAnswer = MutableStateFlow<Event<CameraResponseModel>>(Event.Loading)
    val getAiAnswer = _getAiAnswer.asStateFlow()


    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _capturedImgBitmapState.value.capturedImage?.recycle()
            _capturedImgBitmapState.value = _capturedImgBitmapState.value.copy(capturedImage = bitmap)
            Log.d("testt-vm",bitmap.toString())
        }
    }
    fun getAiAnswer() {
        val imageData = swapBitmapFormat()

    }

    private fun swapBitmapFormat(): ByteArray{
        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage
        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val bitmapData = byteArrayOutputStream.toByteArray()
        Log.d("testt-vm", bitmapData.toString())

        return bitmapData
    }

}
