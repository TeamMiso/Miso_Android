package com.example.miso.ui.camera.progressbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.component.icon.MisoLogoIcon
import com.example.miso.ui.component.progressbar.MisoProgressbar
import com.example.miso.ui.theme.MisoTheme

@Composable
fun AiProgressbar(modifier: Modifier){
    MisoTheme { colors, typography ->
        Box (
            modifier = modifier
                .size(250.dp)
                .padding(15.dp)
        ){
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ){
                Box() {
                    Box (
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.Center)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_miso_clearbackground),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                    CircularProgressIndicator(
                        modifier = modifier
                            .size(150.dp)
                            .align(Alignment.Center),
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                Text(
                    text = "Miso가 분리수거 방법을 찾고 있어요!",
                    color = Color.White
                    )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AiProgressbarPreview() {
    Box(modifier = Modifier.fillMaxSize()){
        AiProgressbar(
            modifier = Modifier
                .align(Alignment.Center)
                .statusBarsPadding()
        )
    }
}