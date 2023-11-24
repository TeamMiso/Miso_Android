package com.example.miso.ui.shop.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.miso.R
import com.example.miso.ui.component.button.MisoBackBlackButton
import com.example.miso.ui.theme.MisoTheme

@Composable
fun ShopBackBtn(navController: NavController){
    IconButton(
        onClick = { navController.popBackStack() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_white),
            colorFilter = ColorFilter.tint(Color.Black),
            contentDescription = "Shop bak btn",
        )
    }
}
@Composable
fun ShopPointState(onClick: () -> Unit, context: Context){
    var point by remember { mutableStateOf(99999) }
    MisoTheme { colors, typography ->
        Text(
            text = "MY POINT : ${point.toString()}",
            style = typography.content3,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Composable
fun ShopPurchaseHistory(onClick: () -> Unit, context: Context){
    IconButton(
        onClick = {  }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_shop_purchase_history_btn),
            contentDescription = "Shop bak btn",
        )
    }
}

@Composable
fun ShopTopBar(navController: NavController) {
    MisoTheme { colors, _ ->
        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(0.041f))
                MisoBackBlackButton {
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                ShopPointState(onClick = {}, context = LocalContext.current)
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                ShopPurchaseHistory(onClick = {}, context = LocalContext.current)
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(color = colors.GRAY1)
            )
        }
        Spacer(modifier = Modifier.height(16.5.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    Box(modifier = Modifier.fillMaxSize()){
       // ShopTopBar()
    }
}