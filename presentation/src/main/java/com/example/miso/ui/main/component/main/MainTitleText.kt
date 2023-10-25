package com.example.miso.ui.main.component.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MainTitleText() {
    MisoTheme { colors, typography ->
        Text(
            text = "“미소”",
            color = colors.BLACK,
            style = typography.title3,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainTitleTextPreView() {
    MainTitleText()
}