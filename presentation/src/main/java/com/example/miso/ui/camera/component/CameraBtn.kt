package com.example.miso.ui.camera.component

import android.content.Context
import android.opengl.Visibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R

@Composable
fun CameraCaptureBtn(onClick: () -> Unit, context: Context){
    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_camera_btn),
            contentDescription = "Camera Btn",
        )
    }
}
@Composable
fun CameraFlashBtn(onClick: () -> Unit, context: Context){
    var flashOn by remember { mutableStateOf(false) }
    IconButton(
        onClick = { flashOn = !flashOn}
    ) {
        if(!flashOn) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera_flash_off_btn),
                contentDescription = "Flash Btn",
            )
        }else {
            Image(
                painter = painterResource(id = R.drawable.ic_camera_flash_on_btn),
                contentDescription = "Flash Btn",
            )
        }
    }
}
@Composable
fun CameraBackBtn(onClick: () -> Unit, context: Context){
    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_camera_backbutton),
            contentDescription = "Flash Off Btn",
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    Column(modifier = Modifier.fillMaxSize()){
        CameraCaptureBtn(onClick = {}, context = LocalContext.current)
        CameraFlashBtn(onClick = { /*TODO*/ }, context = LocalContext.current)
        CameraBackBtn(onClick = { /*TODO*/ }, context = LocalContext.current)
    }
}