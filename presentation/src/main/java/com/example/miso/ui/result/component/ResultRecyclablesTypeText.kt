package com.example.miso.ui.result.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.miso.ui.theme.MisoTheme

@Composable
fun ResultRecyclablesTypeText(text: String) {
    MisoTheme { colors, typography ->
        Text(
            text = "분류: $text",
            color = colors.BLACK,
            style = typography.content2,
            fontWeight = FontWeight.ExtraLight
        )
    }
}