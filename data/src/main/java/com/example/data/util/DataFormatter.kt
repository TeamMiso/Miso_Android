package com.example.data.util

import android.annotation.SuppressLint
import com.example.domain.exception.TokenExpiration
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    kotlin.runCatching {
        SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").parse(this)!!
    }.onSuccess {
        return it
    }
    throw TokenExpiration()
}

@SuppressLint("SimpleDateFormat")
fun Long.toMisoTimeDate(): Date {
    return SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").format(this).toDate()
}