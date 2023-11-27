package com.example.miso.ui.util

import java.text.NumberFormat
import java.util.Locale

fun formatNumberToCommas(number: Int): String {
    return NumberFormat.getNumberInstance(Locale.KOREA).format(number)
}