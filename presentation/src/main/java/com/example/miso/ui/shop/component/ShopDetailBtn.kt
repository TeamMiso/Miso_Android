package com.example.miso.ui.shop.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.button.MisoButton

@Composable
fun ShopDetailButton(onClick: () -> Unit) {
    MisoButton(text = "구매하기") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun ShopDetailButtonPreview() {
    ShopDetailButton(onClick = {})
}