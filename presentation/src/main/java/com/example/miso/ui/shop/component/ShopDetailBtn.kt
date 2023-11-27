package com.example.miso.ui.shop.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.button.MisoButton

@Composable
fun ShopDetailButton(
    amount: Int,
    onClick: () -> Unit
) {
    MisoButton(
        text = if (amount != 0) "구매하기" else "품절",
        modifier = Modifier.padding(6.dp)
    ) {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun ShopDetailButtonPreview() {
    ShopDetailButton(0, onClick = {})
}