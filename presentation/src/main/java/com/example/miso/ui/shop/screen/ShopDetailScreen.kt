package com.example.miso.ui.shop.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.shop.response.ShopListDetailResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.miso.R
import com.example.miso.ui.shop.component.ShopDetailButton
import com.example.miso.ui.shop.component.ShopTopBar
import com.example.miso.viewmodel.ShopViewModel
import com.example.miso.viewmodel.util.Event

@Composable
fun ShopDetailScreen(viewModel: ShopViewModel,navController: NavController){

    var price by remember { mutableStateOf(viewModel.price.value) }
    var content by remember { mutableStateOf(viewModel.content.value) }
    var name by remember { mutableStateOf(viewModel.name.value) }
    var imageUrl by remember { mutableStateOf(viewModel.imageUrl.value) }

    Column(modifier = Modifier.fillMaxSize()) {
        ShopTopBar(navController = navController)
        Spacer(modifier = Modifier.fillMaxHeight(0.03f))
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
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
            Text(text = name!!, fontSize = 20.sp)
            Text(text = "${price} point", fontSize = 18.sp)
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            Text(
                text = content!!,
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

/*
@Composable
@Preview(showBackground = true)
fun ShopScreenPreView() {
    ShopDetailScreen(LocalContext.current)
}
*/

