package com.example.miso.ui.inquiry.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun InquiryContentTitleText() {
    MisoTheme { colors, typography ->
        Text(
            text = "문의 제목",
            color = colors.GRAY6,
            style = typography.content1,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryContentTitleTextPreView() {
    InquiryContentTitleText()
}