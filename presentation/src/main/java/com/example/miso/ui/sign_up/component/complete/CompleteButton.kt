package com.example.miso.ui.sign_up.component.complete

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun CompleteButton(onClick: () -> Unit) {
    MisoButton(text = "로그인 하러가기") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun CompleteButtonPreView() {
    CompleteButton(onClick = {})
}