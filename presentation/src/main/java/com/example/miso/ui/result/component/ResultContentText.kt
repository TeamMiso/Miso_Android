package com.example.miso.ui.result.component

import androidx.compose.runtime.Composable
import com.example.miso.ui.theme.MisoTheme
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ResultContentText(markdown: String) {
    MisoTheme { colors, typography ->
        MarkdownText(
            markdown = markdown,
            style = typography.content4,
            color = colors.BLACK
        )
    }
}