package com.example.miso.ui.shop.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.miso.R


@Composable
fun ShopProductListImg(productImg: String) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth(0.9f)
    ) {
        if (productImg == "url") {
            Log.d("testt-url", productImg)
            Image(
                painter = painterResource(id = R.drawable.ic_no_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(5.dp))
            )
        } else if (productImg.isNotBlank()) {
            Log.d("testt-blank", productImg)
            AsyncImage(
                model = productImg,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_no_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(5.dp))
            )
        }
    }
}

@Composable
fun ShopProductListItem(onClick: () -> Unit,launchDetail: () -> Unit,productName: String, price: Int, productImg: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth(0.5f)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                launchDetail()
                onClick()
            }
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShopProductListImg(productImg = productImg)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Text(text = "${productName}", fontSize = 15.sp)
        Text(text = "${price} point", fontSize = 12.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun ShopProductListItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        ShopProductListItem(onClick = {}, launchDetail = {}, productName = "막대사탕", price = 150, productImg = "https://project-miso.s3.ap-northeast-2.amazonaws.com/file/Rectangle+2083.png")
    }
}