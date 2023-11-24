package com.example.miso.ui.shop.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.model.inquiry.response.InquiryListModel
import com.example.domain.model.shop.response.ShopListDetailResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.miso.ui.list.screen.getInquiryList
import com.example.miso.ui.main.MainPage
import com.example.miso.ui.shop.component.ShopProductListItem
import com.example.miso.ui.shop.component.ShopTopBar
import com.example.miso.viewmodel.InquiryViewModel
import com.example.miso.viewmodel.ShopViewModel
import com.example.miso.viewmodel.util.Event


@Composable
fun ShopScreen(
    context: Context,
    viewModel: ShopViewModel,
    navController: NavController,
) {
    val progressState = remember { mutableStateOf(false) }
    val launchDetail = remember { mutableStateOf(false) }

    LaunchedEffect("GetShopList") {
        Log.d("testt", "lunched getshop")
        viewModel.getShopList()
        getShopList(
            viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list ->
                viewModel.addShopList(list)
            }
        )
        Log.d("testt", "작동")
    }
    LaunchedEffect(launchDetail.value) {
        Log.d("lsd", "작동함")
        if (launchDetail.value) {
            Log.d("lsd", "작동")
            getShopDetailList(
                viewModel = viewModel,
                progressState = { progressState.value = it },
                onSuccess = { list ->
                    Log.d("lsd", "nav작동")
                    viewModel.addShopDetailList(list)
                    navController.navigate(MainPage.ShopDetail.value)
                    viewModel.changeDetailList()
                    launchDetail.value = false
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShopTopBar(navController = navController)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center
        ) {
            items(viewModel.shopList.size) { index ->
                Log.d("testt-item", viewModel.shopList[index].toString())
                ShopProductListItem(
                    launchDetail = { viewModel.getShopDetail(viewModel.shopList[index].id) },
                    onClick = { launchDetail.value = true },
                    productName = viewModel.shopList[index].name,
                    price = viewModel.shopList[index].price,
                    productImg = viewModel.shopList[index].imageUrl
                )
            }
        }
    }
}

suspend fun getShopList(
    viewModel: ShopViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (shopList: List<ShopListModel>) -> Unit,
) {
    viewModel.getShopListResponse.collect { response ->
        Log.d("testt", "작동")
        when (response) {
            is Event.Success -> {
                Log.d("testt", "이벤트 성공${response.data!!.itemList}")
                progressState(false)
                onSuccess(response.data!!.itemList)
            }

            is Event.Loading -> {
                Log.d("testt", "이벤트 중")
                progressState(true)
            }

            else -> {
                Log.d("testt", "이벤트 실패")
                progressState(false)
            }
        }
    }
}

suspend fun getShopDetailList(
    viewModel: ShopViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (shopDetailList: ShopListDetailResponseModel) -> Unit,
) {
    viewModel.getShopListDetailResponse.collect { response ->
        Log.d("testt", "작동")
        when (response) {
            is Event.Success -> {
                Log.d("lsd", "이벤트 성공${response.data!!}")
                progressState(false)
                onSuccess(response.data!!)
            }

            is Event.Loading -> {
                Log.d("testt", "이벤트 중")
                progressState(true)
            }

            else -> {
                Log.d("testt", "이벤트 실패")
                progressState(false)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShopDetailScreenPreView() {

}