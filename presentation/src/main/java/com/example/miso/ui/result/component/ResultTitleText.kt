package com.example.miso.ui.result.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.miso.ui.theme.MisoTheme

@Composable
fun ResultTitleText(text: String) {
    MisoTheme { colors, typography ->
        Text(
            text = text,
            color = colors.BLACK,
            style = typography.title1,
            fontWeight = FontWeight.ExtraLight
        )
    }
}