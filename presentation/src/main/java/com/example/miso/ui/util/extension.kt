package com.example.miso.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.text.SimpleDateFormat

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

@SuppressLint("SimpleDateFormat")
fun String.toDateString(): String {
    return kotlin.runCatching {
        val originalDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS").parse(this)
        if (originalDate != null) {
            val outputFormatter = SimpleDateFormat("yy.MM.dd")
            outputFormatter.format(originalDate)
        } else {
            throw Exception()
        }
    }.onSuccess {
        it
    }.getOrElse {
        throw Exception()
    }
}