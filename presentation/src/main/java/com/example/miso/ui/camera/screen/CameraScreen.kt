package com.example.miso.ui.camera.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraBtn
import com.example.miso.ui.camera.component.CameraPreview

@Composable
fun CameraScreen(context: Context){
    CameraPreview()
    CameraBackground()
    CameraBtn(onClick = {}, context = context)
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    CameraScreen(LocalContext.current)
}

