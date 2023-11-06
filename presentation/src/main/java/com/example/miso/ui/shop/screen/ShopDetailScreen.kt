package com.example.miso.ui.shop.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.shop.component.ShopTopBar

@Composable
fun ShopDetailScreen(context: Context){
    Column(modifier = Modifier.fillMaxSize()) {
        ShopTopBar()

    }
}

@Composable
@Preview(showBackground = true)
fun ShopDetailScreenPreView() {
    ShopDetailScreen(LocalContext.current)
}