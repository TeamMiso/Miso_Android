package com.example.miso.ui.shop.screen

import android.content.Context
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miso.R
import com.example.miso.ui.shop.component.ShopDetailButton
import com.example.miso.ui.shop.component.ShopTopBar

@Composable
fun ShopScreen(context: Context){
    Column(modifier = Modifier.fillMaxSize()) {
        ShopTopBar()
        Spacer(modifier = Modifier.fillMaxHeight(0.03f))
        Image(
            painter = painterResource(id = R.drawable.ic_list_example),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Text(text = "막대사탕", fontSize = 20.sp)
            Text(text = "150 point", fontSize = 18.sp)
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Text(
                text = "새콤달콤한 츄파츕스로 오늘 하루는 달콤하게 보내 보는게 어떨까요?",
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.45f))
            ShopDetailButton(onClick = {})
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShopScreenPreView() {
    ShopScreen(LocalContext.current)
}

