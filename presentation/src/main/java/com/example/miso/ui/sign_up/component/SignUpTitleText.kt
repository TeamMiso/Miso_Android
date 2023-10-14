package com.example.miso.ui.sign_up.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SignUpTitleText(isClick: Boolean = false) {
    when (isClick) {
        false -> {
            MisoTheme { colors, typography ->
                Text(
                    text = "SIGN UP",
                    color = colors.WHITE,
                    style = typography.title1,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        true -> {}
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpTitleTextPreView() {
    SignUpTitleText()
}