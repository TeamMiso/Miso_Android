package com.example.miso.ui.shop.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Colors
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R

@Composable
fun ShopBackBtn(onClick: () -> Unit, context: Context){
    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_camera_backbutton),
            colorFilter = ColorFilter.tint(Color.Black),
            contentDescription = "Shop bak btn",
        )
    }
}
@Composable
fun ShopPointState(onClick: () -> Unit, context: Context){
    var point by remember { mutableStateOf(99999) }
    Text(
        text = "MY POINT : ${point.toString()}",
        fontWeight = FontWeight.Bold
        )
}
@Composable
fun ShopPurchaseHistory(onClick: () -> Unit, context: Context){
    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_shop_purchase_history_btn),
            contentDescription = "Shop bak btn",
        )
    }
}
@Composable
fun ShopTopBar(){
    Spacer(modifier = Modifier.fillMaxHeight(0.02f))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(0.03f))
        ShopBackBtn(onClick = { /*TODO*/ }, context = LocalContext.current)
        Spacer(modifier = Modifier.fillMaxWidth(0.5f))
        ShopPointState(onClick = { /*TODO*/ }, context = LocalContext.current)
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        ShopPurchaseHistory(onClick = { /*TODO*/ }, context = LocalContext.current)
    }

}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    Box(modifier = Modifier.fillMaxSize()){
        ShopTopBar()
    }
}