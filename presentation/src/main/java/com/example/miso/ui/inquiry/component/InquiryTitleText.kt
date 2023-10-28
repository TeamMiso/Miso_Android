package com.example.miso.ui.inquiry.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun InquiryTitleText() {
    MisoTheme { colors, typography ->
        Text(
            text = "문의하기",
            color = colors.BLACK,
            style = typography.title3,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryTitleTextPreView() {
    InquiryTitleText()
}