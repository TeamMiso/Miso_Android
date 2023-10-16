package com.example.miso.ui.splash.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SplashText(
) {
    MisoTheme { colors, typography ->
        Text(
            text = "“미소”\n" +
                    "환경을 웃음으로 바꾸다.",
            color = colors.M1,
            style = typography.title2,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SplashTextPreView() {
    SplashText()
}