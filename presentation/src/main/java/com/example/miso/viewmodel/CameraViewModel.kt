package com.example.miso.viewmodel


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {
    private val _capturedImgBitmapState = MutableStateFlow(CameraState())
    val captureImgBitmapState = _capturedImgBitmapState.asStateFlow()

    private val _uploadFirebaseState = MutableStateFlow(BitmapState(uploadedBitmap = null))
    val uploadFirebaseState = _uploadFirebaseState.asStateFlow()

    private val _aiAnswer = MutableStateFlow(AiAnswerState(aiAnswerData = null, aiAnswerUploaded = null))
    val aiAnswer = _aiAnswer.asStateFlow()

    private val imgNum = MutableStateFlow(0)


    fun loadImgBitmap(bitmap: Bitmap){
        viewModelScope.launch {
            _capturedImgBitmapState.value.capturedImage?.recycle()
            _capturedImgBitmapState.value = _capturedImgBitmapState.value.copy(capturedImage = bitmap)
            Log.d("testt-vm",bitmap.toString())
        }
    }
    fun sendImgBitmap(){
        val (databaseRef, storageRef, bitmapData) = setReference()

        val uploadImg = storageRef.putBytes(bitmapData)
        Log.d("testt-vm",uploadImg.toString())

        uploadImg
            .addOnSuccessListener {
                Log.d("testt-vm","success")
                _uploadFirebaseState.value = _uploadFirebaseState.value.copy(uploadedBitmap = true)

                databaseRef
                    .child("img${imgNum.value}")
                    .setValue(imgNum.value)

                getAiAnswer()

            }.addOnFailureListener {
                Log.d("testt-vm","failure")
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
        Log.d("testt-vm", bitmapData.toString())

        return Triple(databaseRef, storageRef, bitmapData)
    }
    private fun getAiAnswer() {
        val query: Query = getMessageQuery()
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null) {
                    _aiAnswer.value = _aiAnswer.value.copy(
                        aiAnswerData = snapshot.value.toString(),
                        aiAnswerUploaded = true
                    )
                    Log.d("testt-vm", snapshot.value.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        }
        query.addValueEventListener(valueEventListener)
    }

    private fun getMessageQuery(): Query {
        return FirebaseDatabase.getInstance().getReference()
            .child("ai")
            .child("answer${imgNum.value}")
    }

}
