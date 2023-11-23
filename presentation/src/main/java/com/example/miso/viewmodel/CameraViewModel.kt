package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.camera.AiResponseUseCase
import com.example.miso.ui.camera.state.AiAnswerState
import com.example.miso.ui.camera.state.CameraState
import com.example.miso.ui.camera.state.BitmapState
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
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

    private val _uploadFirebaseState = MutableStateFlow(BitmapState())
    val uploadFirebaseState = _uploadFirebaseState.asStateFlow()

    private val _aiAnswer = MutableStateFlow(AiAnswerState(aiAnswerData = null))
    val aiAnswer = _aiAnswer.asStateFlow()

    private val imgNum = MutableStateFlow(0)


    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _aiAnswer.value = _aiAnswer.value.copy(aiAnswerUploaded = null)
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
