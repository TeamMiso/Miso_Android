package com.example.miso.ui.camera.screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraCaptureBtn
import com.example.miso.ui.camera.component.CameraPreview

@Composable
fun CameraScreen(context: Context){
    CameraBackground()
    CameraCaptureBtn(onClick = {}, context = context)
    CameraPreview()
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    CameraScreen(LocalContext.current)
}

