package com.example.miso.ui.camera.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun CameraBtn(onClick: () -> Unit,context: Context){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.ic_camera_btn),
            contentDescription = "Camera Btn",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clickable {
                    Toast
                        .makeText(context, "hi", Toast.LENGTH_SHORT)
                        .show()
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    CameraBtn(onClick = {}, context = LocalContext.current)
}