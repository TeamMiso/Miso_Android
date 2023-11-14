package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miso.ui.camera.state.CameraState
import com.example.miso.ui.camera.state.BitmapState
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
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
) : ViewModel() {
    private val _capturedImgBitmapState = MutableStateFlow(CameraState())
    val captureImgBitmapState = _capturedImgBitmapState.asStateFlow()

    private val _uploadFirebaseState = MutableStateFlow(BitmapState(uploadedBitmap = null))
    val uploadFirebaseState = _uploadFirebaseState.asStateFlow()

    private val _aiAnswer = MutableStateFlow("")
    val aiAnswer = _aiAnswer.asStateFlow()

    private val imgNum = MutableStateFlow(0)


    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _capturedImgBitmapState.value.capturedImage?.recycle()
            _capturedImgBitmapState.value = _capturedImgBitmapState.value.copy(capturedImage = bitmap)
            Log.d("testt",bitmap.toString())
        }
    }
    fun sendImgBitmap(){
        val (databaseRef, storageRef, bitmapData) = setReference()

        val uploadImg = storageRef.putBytes(bitmapData)
        Log.d("testt",uploadImg.toString())

        uploadImg
            .addOnSuccessListener {
                Log.d("testt","success")
                _uploadFirebaseState.value = _uploadFirebaseState.value.copy(uploadedBitmap = true)

                databaseRef
                    .child("img${imgNum.value}")
                    .setValue(imgNum.value)

            }.addOnFailureListener {
                Log.d("testt","failure")
                _uploadFirebaseState.value = _uploadFirebaseState.value.copy(uploadedBitmap = false)
            }
    }
    private fun setReference(): Triple<DatabaseReference, StorageReference, ByteArray> {
        imgNum.value++

        val databaseRef = FirebaseDatabase.getInstance().reference
            .child("user")

        val storageRef = Firebase.storage.reference
            .child(imgNum.value.toString() + ".jpeg")

        val byteArrayOutputStream = ByteArrayOutputStream()

        val swapBitmap = _capturedImgBitmapState.value.capturedImage
        swapBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val bitmapData = byteArrayOutputStream.toByteArray()
        Log.d("testt", bitmapData.toString())

        return Triple(databaseRef, storageRef, bitmapData)
    }


}
