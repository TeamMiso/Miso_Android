package com.example.miso.ui.result.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ResultContentText(markdown: String) {
    MisoTheme { colors, _ ->
        MarkdownText(
            markdown = markdown,
            fontResource = R.font.suitv1_extralight,
            fontSize = 10.sp,
            color = colors.BLACK
        )
    }
}