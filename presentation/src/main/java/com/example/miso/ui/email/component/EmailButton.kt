package com.example.miso.ui.email.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun EmailButton(
    isError: Boolean,
    onClick: () -> Unit
) {
    MisoButton(
        text = if (!isError) "확인" else "재발송"
    ) {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun EmailButtonPreView() {
    EmailButton(isError = false, onClick = {})
}