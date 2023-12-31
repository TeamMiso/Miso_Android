package com.example.miso.ui.shop.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.ui.util.formatNumberToCommas


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
fun ShopProductListItem(
    onClick: () -> Unit,
    launchDetail: () -> Unit,
    productName: String,
    price: Int,
    amount: Int,
    productImg: String
) {
    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(0.5f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        launchDetail()
                        onClick()
                    }
                )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ShopProductListImg(productImg = productImg)
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                Row {
                    Text(
                        text = productName,
                        style = typography.content1,
                        fontWeight = FontWeight.ExtraLight,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "수량 : ${formatNumberToCommas(amount)}",
                        style = typography.content3,
                        fontWeight = FontWeight.ExtraLight,
                        textAlign = TextAlign.End,
                        color = colors.GRAY6
                    )
                }
                Text(
                    text = "${formatNumberToCommas(price)} point",
                    style = typography.content3,
                    color = colors.GRAY6,
                    fontWeight = FontWeight.ExtraLight,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShopProductListItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        ShopProductListItem(onClick = {}, launchDetail = {}, productName = "막대사탕", price = 150, amount = 10, productImg = "https://project-miso.s3.ap-northeast-2.amazonaws.com/file/Rectangle+2083.png")
    }
}