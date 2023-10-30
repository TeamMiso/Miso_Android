package com.example.miso.ui.camera.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.camera.component.CameraBackBtn
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraCaptureBtn
import com.example.miso.ui.camera.component.CameraFlashBtn
import com.example.miso.ui.camera.component.CameraPreview

@Composable
fun CameraScreen(context: Context){
    Box(){
        CameraPreview()
        CameraBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.03f))
                CameraBackBtn(onClick = { /*TODO*/ }, context = context)
                Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                CameraFlashBtn(
                    onClick = {},
                    context = context
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.87f))
            CameraCaptureBtn(onClick = {}, context = context)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    CameraScreen(LocalContext.current)
}