package com.example.miso.ui.log_in.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun LogInButton(onClick: () -> Unit) {
    MisoButton(text = "로그인") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun LogInButtonPreView() {
    LogInButton(onClick = {})
}