package com.example.miso.ui.inquiry.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.button.MisoButton

@Composable
fun InquiryButton(onClick: () -> Unit) {
    MisoButton(
        text = "제출하기",
        Modifier
    ) {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryButtonPreView() {
    InquiryButton(onClick = {})
}