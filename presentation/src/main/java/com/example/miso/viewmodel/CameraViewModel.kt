package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.camera.LoadImgUrlUseCase
import com.example.miso.ui.camera.state.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val loadImgUrlUseCase: LoadImgUrlUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun loadImgUrl(bitmap: Bitmap){
        viewModelScope.launch {
            Log.d("testt",bitmap.toString())
        }
    }
}
