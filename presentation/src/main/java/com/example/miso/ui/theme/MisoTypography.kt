package com.example.miso.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.miso.R

object MisoTypography {

    internal val suit = FontFamily(
        Font(R.font.suitv1_bold, FontWeight.Bold),
        Font(R.font.suitv1_extrabold, FontWeight.ExtraBold),
        Font(R.font.suitv1_extralight, FontWeight.ExtraLight),
        Font(R.font.suitv1_light, FontWeight.Light),
        Font(R.font.suitv1_medium, FontWeight.Medium),
        Font(R.font.suitv1_regular, FontWeight.Normal),
        Font(R.font.suitv1_semibold, FontWeight.SemiBold),
        Font(R.font.suitv1_thin, FontWeight.Thin),
    )

    @Stable
    val title1 = TextStyle(
        fontFamily = suit,
        fontSize = 30.sp,
        lineHeight = 16.sp
    )

    @Stable
    val title2 = TextStyle(
        fontFamily = suit,
        fontSize = 25.sp,
        lineHeight = 26.sp
    )

    @Stable
    val title3 = TextStyle(
        fontFamily = suit,
        fontSize = 20.sp,
        lineHeight = 22.sp
    )

    @Stable
    val number = TextStyle(
        fontFamily = suit,
        fontSize = 30.sp,
        lineHeight = 16.sp
    )

    @Stable
    val content1 = TextStyle(
        fontFamily = suit,
        fontSize = 15.sp,
        lineHeight = 16.sp
    )

    @Stable
    val content2 = TextStyle(
        fontFamily = suit,
        fontSize = 13.sp,
        lineHeight = 15.sp,
        fontWeight = FontWeight.ExtraLight
    )

    @Stable
    val content3 = TextStyle(
        fontFamily = suit,
        fontSize = 12.sp,
        lineHeight = 15.sp
    )

    @Stable
    val content4 = TextStyle(
        fontFamily = suit,
        fontSize = 10.sp,
        lineHeight = 15.sp
    )
}