package com.example.miso.ui.util

fun isUrl(text: String): Boolean {
    return text.startsWith("http://") || text.startsWith("https://")
}