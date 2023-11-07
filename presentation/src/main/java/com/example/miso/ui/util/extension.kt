package com.example.miso.ui.util

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun Uri.toMultipartBody(context: Context): MultipartBody.Part? {
    val file: File? = getFileFromUri(context, this)
    file?.let {
        val requestFile: RequestBody =
            it.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val part: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", it.name, requestFile)
        Log.d("Multipart", file.name)
        return part
    }
    return null
}