package com.example.miso.ui.sign_up.component.email

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun EmailButton(onClick: () -> Unit) {
    MisoButton(text = "확인") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun EmailButtonPreView() {
    EmailButton(onClick = {})
}