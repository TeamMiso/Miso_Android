package com.example.miso.ui.main.component.search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SearchHistoryText() {
    MisoTheme { colors, typography ->
        Text(
            text = "최근 검색어",
            color = colors.BLACK,
            style = typography.content1,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchHistoryTextPreView() {
    SearchHistoryText()
}