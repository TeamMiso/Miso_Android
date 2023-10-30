package com.example.miso.ui.list.component.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun DetailImageText() {
    MisoTheme { colors, typography ->
        Text(
            text = "이미지",
            color = colors.GRAY6,
            style = typography.content1,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryImageTextPreView() {
    DetailImageText()
}