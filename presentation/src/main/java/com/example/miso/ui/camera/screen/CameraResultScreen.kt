package com.example.miso.ui.camera.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.miso.R
import com.example.miso.ui.camera.component.CameraBackBtn
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraConfirmBtn
import com.example.miso.ui.camera.component.CameraFlashBtn
import com.example.miso.ui.camera.component.CameraReCaptureBtn
import com.example.miso.ui.camera.state.CameraState
import com.example.miso.ui.component.button.MisoButton
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.viewmodel.CameraViewModel

@Composable
fun CameraResultScreen(navController: NavController,viewModel: CameraViewModel) {
    getBitmap(viewModel = viewModel)
    MisoTheme { colors, typography ->
        CameraBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.87f))
            Text(text = "정말 이 사진을 등록 하시겠습니까?", color = colors.M3)
            Spacer(modifier = Modifier.fillMaxHeight(0.12f))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.07f))
                CameraReCaptureBtn {navController.popBackStack()}
                Spacer(modifier = Modifier.fillMaxWidth(0.06f))
                CameraConfirmBtn {}
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
                contentScale = ContentScale.Crop,
                contentDescription = "camera result preview")
        }
    }
}
@Composable
@Preview(showBackground = true)
fun CameraResultScreenPreView() {

}
