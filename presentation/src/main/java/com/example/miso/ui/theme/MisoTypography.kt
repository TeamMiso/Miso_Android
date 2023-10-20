package com.example.miso.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.miso.R

object MisoTypography {

    internal val inter = FontFamily(
        Font(R.font.inter_black, FontWeight.Black),
        Font(R.font.inter_bold, FontWeight.Bold),
        Font(R.font.inter_extrabold, FontWeight.ExtraBold),
        Font(R.font.inter_extralight, FontWeight.ExtraLight),
        Font(R.font.inter_light, FontWeight.Light),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_thin, FontWeight.Thin),
    )

    @Stable
    val title1 = TextStyle(
        fontFamily = inter,
        fontSize = 30.sp,
        lineHeight = 16.sp
    )

    @Stable
    val title2 = TextStyle(
        fontFamily = inter,
        fontSize = 24.sp,
        lineHeight = 26.sp
    )

    @Stable
    val title3 = TextStyle(
        fontFamily = inter,
        fontSize = 20.sp,
        lineHeight = 22.sp
    )

    @Stable
    val number = TextStyle(
        fontFamily = inter,
        fontSize = 30.sp,
        lineHeight = 16.sp
    )

    @Stable
    val content1 = TextStyle(
        fontFamily = inter,
        fontSize = 15.sp,
        lineHeight = 16.sp
    )

    @Stable
    val content2 = TextStyle(
        fontFamily = inter,
        fontSize = 13.sp,
        lineHeight = 15.sp
    )

    @Stable
    val content3 = TextStyle(
        fontFamily = inter,
        fontSize = 12.sp,
        lineHeight = 15.sp
    )
}