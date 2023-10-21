package com.example.miso.ui.sign_up.component.email

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun EmailContentText() {
    MisoTheme { colors, typography ->
        Text(
            text = "이메일로 인증번호를\n" +
                    "발송하였습니다.",
            textAlign = TextAlign.Center,
            color = colors.GRAY6,
            style = typography.title3,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EmailContentTextPreView() {
    EmailContentText()
}