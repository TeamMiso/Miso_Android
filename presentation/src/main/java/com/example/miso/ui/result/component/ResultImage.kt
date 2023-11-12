package com.example.miso.ui.result.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun ResultImage(url: String) {
    MisoTheme { colors, _ ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(255.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colors.GRAY3)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (url.isBlank()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_no_image),
                        contentDescription = "No Image",
                        modifier = Modifier.size(100.dp)
                    )
                }
                else {
                    Image(
                        painter = rememberAsyncImagePainter(model = url),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}