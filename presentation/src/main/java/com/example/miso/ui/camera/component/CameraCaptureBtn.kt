package com.example.miso.ui.camera.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R

@Composable
fun CameraCaptureBtn(onClick: () -> Unit, context: Context){
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = ),
        onClick = { /*TODO*/ }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_camera_btn),
            contentDescription = "Camera Btn",
        )
    }
}
@Composable
fun CameraFlashBtn(onClick: () -> Unit, context: Context){

}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    Box(modifier = Modifier.fillMaxSize()){
        CameraCaptureBtn(onClick = {}, context = LocalContext.current)
    }
}