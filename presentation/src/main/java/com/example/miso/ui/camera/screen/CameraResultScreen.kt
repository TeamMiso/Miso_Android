package com.example.miso.ui.camera.screen

import android.graphics.Bitmap
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R
import com.example.miso.ui.camera.component.CameraBackBtn
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraConfirmBtn
import com.example.miso.ui.camera.component.CameraFlashBtn
import com.example.miso.ui.camera.component.CameraReCaptureBtn
import com.example.miso.ui.component.button.MisoButton
import com.example.miso.ui.theme.MisoTheme

@Composable
fun CameraResultScreen(bitmap: Bitmap) {
    MisoTheme { colors, typography ->
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = "camera result preview")
        }
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
                CameraReCaptureBtn {}
                Spacer(modifier = Modifier.fillMaxWidth(0.06f))
                CameraConfirmBtn {}
            }
        }

    }
}
@Composable
@Preview(showBackground = true)
fun CameraResultScreenPreView() {

}
