package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.camera.AiResponseUseCase
import com.example.miso.ui.camera.state.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val aiResponseUseCase: AiResponseUseCase
) : ViewModel() {
    private val _capturedImgBitmapState = MutableStateFlow(CameraState())
    val captureImgBitmapState = _capturedImgBitmapState.asStateFlow()

    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _capturedImgBitmapState.value.capturedImage?.recycle()
            _capturedImgBitmapState.value = _capturedImgBitmapState.value.copy(capturedImage = bitmap)
            Log.d("testt",bitmap.toString())
        }
    }
}
