package com.example.miso.ui.camera.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraBtn

@Composable
fun CameraScreen(context: Context){
    CameraBackground()
    CameraBtn()
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    CameraScreen(LocalContext.current)
}