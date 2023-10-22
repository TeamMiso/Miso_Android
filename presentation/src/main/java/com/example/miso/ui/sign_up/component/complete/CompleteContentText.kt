package com.example.miso.ui.sign_up.component.complete

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun CompleteContentText() {
    MisoTheme { colors, typography ->
        Text(
            text = "회원가입이\n" +
                    "완료 되었습니다.",
            textAlign = TextAlign.Center,
            color = colors.GRAY6,
            style = typography.title2,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CompleteContentTextPreView() {
    CompleteContentText()
}