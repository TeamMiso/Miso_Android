package com.example.miso.ui.camera.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.domain.model.camera.response.CameraResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraConfirmBtn
import com.example.miso.ui.camera.component.CameraReCaptureBtn
import com.example.miso.ui.component.progressbar.MisoProgressbar
import com.example.miso.ui.main.MainPage
import com.example.miso.ui.shop.screen.getShopDetailList
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.viewmodel.CameraViewModel
import com.example.miso.viewmodel.RecyclablesViewModel
import com.example.miso.viewmodel.ShopViewModel
import com.example.miso.viewmodel.util.Event

@Composable
fun CameraResultScreen(context: Context,navController: NavController,viewModel: CameraViewModel,viewModelResult: RecyclablesViewModel) {

    val launchAi = remember { mutableStateOf(false) }
    var progressState = remember { mutableStateOf(false) }

    LaunchedEffect(launchAi.value){
        if(launchAi.value){
            viewModel.getAiAnswer()
            getAiResponse(
                viewModel = viewModel,
                progressState = { progressState.value = it },
                onSuccess = { response ->
                    viewModelResult.result(response.best_class)
                    navController.navigate(MainPage.Result.value)
                }

            )
        }
    }

    getBitmap(viewModel = viewModel)
    MisoTheme { colors, typography ->
        Box(modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()) {
            CameraBackground()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.87f))
                Text(text = "정말 이 사진을 등록 하시겠습니까?", color = colors.M3)
                Spacer(modifier = Modifier.fillMaxHeight(0.12f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth(0.07f))
                    CameraReCaptureBtn { navController.popBackStack() }
                    Spacer(modifier = Modifier.fillMaxWidth(0.06f))
                    CameraConfirmBtn { launchAi.value = true }
                }
            }
            if (progressState.value) {
                MisoProgressbar(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .statusBarsPadding()
                )
            }
        }
    }
}
@Composable
private fun getBitmap(viewModel: CameraViewModel){
    val captureImgBitmapState by viewModel.captureImgBitmapState.collectAsState()
    LaunchedEffect(captureImgBitmapState){ Log.d("testt",captureImgBitmapState.toString()) }
    Box(modifier = Modifier.fillMaxSize()){
        (captureImgBitmapState.capturedImage?.asImageBitmap() ?: null)?.let {
            Image(
                bitmap = it,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "camera result preview")
        }
    }
}

suspend fun getAiResponse(
    viewModel: CameraViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (aiAnswer: CameraResponseModel) -> Unit,
) {
    viewModel.getAiAnswer.collect { response ->
        Log.d("testt", "작동")
        when (response) {
            is Event.Success -> {
                Log.d("testt","이벤트 성공${response.data!!}")
                progressState(false)
                onSuccess(response.data!!)
            }

            is Event.Loading -> {
                Log.d("testt","이벤트 중")
                progressState(true)
            }

            else -> {
                Log.d("testt","이벤트 실패")
                progressState(false)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun CameraResultScreenPreView() {

}
