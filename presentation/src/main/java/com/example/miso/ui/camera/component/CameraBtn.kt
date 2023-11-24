package com.example.miso.ui.camera.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R
import com.example.miso.ui.component.button.MisoBackWhiteButton

@Composable
fun CameraCaptureBtn(onClick: () -> Unit){
    IconButton(
        onClick = {}
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_camera_btn),
            contentDescription = "Camera Btn",
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() }
        )
    }
}
@Composable
fun CameraFlashBtn(onClick: () -> Unit){
    var flashOn by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            onClick()
            flashOn = !flashOn
        },
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
fun CameraBackBtn(onClick: () -> Unit){
    MisoBackWhiteButton {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    Column(modifier = Modifier.fillMaxSize()){
        CameraCaptureBtn(onClick = {})
        CameraFlashBtn(onClick = { /*TODO*/ })
        CameraBackBtn(onClick = { /*TODO*/ })
    }
}