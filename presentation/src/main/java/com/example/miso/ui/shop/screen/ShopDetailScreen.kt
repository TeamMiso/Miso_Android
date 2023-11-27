package com.example.miso.ui.shop.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.miso.R
import com.example.miso.ui.component.snackbar.MisoSnackbar
import com.example.miso.ui.main.MainPage
import com.example.miso.ui.shop.component.ShopDetailButton
import com.example.miso.ui.shop.component.ShopTopBar
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.ui.util.formatNumberToCommas
import com.example.miso.viewmodel.ShopViewModel
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ShopDetailScreen(viewModel: ShopViewModel, navController: NavController) {
    val buyItemState = remember { mutableStateOf(false) }
    val progressState = remember { mutableStateOf(false) }

    val snackBarVisibility = remember { mutableStateOf(false) }
    val errorText = remember { mutableStateOf("") }
    val remoteSuccess = remember { mutableStateOf(false) }

    var point by remember { mutableStateOf(viewModel.point.value) }
    var id by remember { mutableStateOf(viewModel.id.value) }
    var price by remember { mutableStateOf(viewModel.price.value) }
    var amount by remember { mutableStateOf(viewModel.amount.value) }
    var content by remember { mutableStateOf(viewModel.content.value) }
    var name by remember { mutableStateOf(viewModel.name.value) }
    var imageUrl by remember { mutableStateOf(viewModel.imageUrl.value) }

    LaunchedEffect(buyItemState.value) {
        if (buyItemState.value) {
            viewModel.repurchaseItem()
            viewModel.buyItem(id = id!!)
            buyItem(
                viewModel = viewModel,
                errorText = { text ->
                    errorText.value = text
                    snackBarVisibility.value = true
                },
                progressState = { progressState.value = it },
                onSuccess = {
                    Log.d("buyItem-popBackStack", "launched")
                    remoteSuccess.value = true
                }
            )
        }
    }

    LaunchedEffect(snackBarVisibility.value) {
        if (snackBarVisibility.value) {
            delay(1.5.seconds)
            if (remoteSuccess.value) navController.popBackStack()
            snackBarVisibility.value = false
            progressState.value = false
            delay(1.seconds)
            remoteSuccess.value = false
        }
    }

    MisoTheme { colors, typography ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 18.dp)
            ) {
                ShopTopBar(
                    navController = navController,
                    userPoint = point,
                    onClick = { navController.navigate(MainPage.PurChase.value) })
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
                    Row {
                        Text(
                            text = name!!,
                            style = typography.title3,
                            fontWeight = FontWeight.ExtraLight
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "남은 수량 : ${formatNumberToCommas(amount!!)}",
                            style = typography.content3,
                            fontWeight = FontWeight.ExtraLight,
                            color = colors.GRAY6,
                        )
                    }
                    Text(
                        text = "${formatNumberToCommas(price!!)} Point",
                        style = typography.title3,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                    Text(
                        text = content!!,
                        style = typography.content3,
                        fontWeight = FontWeight.ExtraLight,
                        color = colors.GRAY6,
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ShopDetailButton(
                        amount = amount!!,
                        onClick = {
                            if (amount != 0) {
                                buyItemState.value = true
                            } else {
                                errorText.value = "남은 수량이 없습니다!"
                                snackBarVisibility.value = true
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
            MisoSnackbar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
                text = errorText.value,
                visible = snackBarVisibility.value,
                icon = if (remoteSuccess.value) R.drawable.ic_check else R.drawable.ic_cancel
            ) {
                snackBarVisibility.value = false
            }
        }
    }
}

suspend fun buyItem(
    viewModel: ShopViewModel,
    errorText: (errorText: String) -> Unit,
    progressState: (Boolean) -> Unit,
    onSuccess: () -> Unit,
) {
    viewModel.buyItemResponse.collect { response ->
        Log.d("buyItem", "작동")
        when (response) {
            is Event.Success -> {
                Log.d("buyItem", "이벤트 성공")
                onSuccess()
                errorText("구매 성공했습니다!")
                progressState(false)
            }

            is Event.Loading -> {
                Log.d("buyItem", "이벤트 중")
                progressState(true)
            }

            is Event.ForBidden -> {
                errorText("포인트가 부족합니다!")
                progressState(true)
            }

            else -> {
                Log.d("buyItem", "이벤트 실패")
                errorText("알 수 없는 에러가 발생했습니다!")
                progressState(false)
            }
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

