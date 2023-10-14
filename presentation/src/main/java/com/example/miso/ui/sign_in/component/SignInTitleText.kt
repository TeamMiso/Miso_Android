package com.example.miso.ui.sign_in.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SignInTitleText(isClick: Boolean = false) {
    when (isClick) {
        false -> {
            MisoTheme { colors, typography ->
                Text(
                    text = "SIGN IN",
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
fun SignInTitleTextPreView() {
    SignInTitleText()
}