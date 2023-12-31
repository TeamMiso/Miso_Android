package com.example.miso.ui.sign_up.component.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun SignUpButton(onClick: () -> Unit) {
    MisoButton(text = "인증번호 받기") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpButtonPreView() {
    SignUpButton(onClick = {})
}