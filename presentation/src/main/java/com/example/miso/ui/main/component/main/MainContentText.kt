package com.example.miso.ui.main.component.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MainContentText() {
    MisoTheme { colors, typography ->
        Text(
            text = "메뉴",
            color = colors.BLACK,
            style = typography.title2,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainContentTextPreView() {
    MainContentText()
}