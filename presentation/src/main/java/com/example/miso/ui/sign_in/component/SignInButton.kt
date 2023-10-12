package com.example.miso.ui.sign_in.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun SignInButton(onClick: () -> Unit) {
    MisoButton(text = "로그인") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun SignInButtonPreView() {
    SignInButton(onClick = {})
}