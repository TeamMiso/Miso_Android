package com.example.miso.ui.email.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun EmailText(
) {
    MisoTheme { colors, typography ->
        Text(
            text = "이메일로 인증번호를\n" +
                    "발송하였습니다.",
            color = colors.BLACK,
            style = typography.title2,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EmailTextPreView() {
    EmailText()
}