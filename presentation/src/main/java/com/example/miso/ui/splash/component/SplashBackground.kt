package com.example.miso.ui.splash.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SplashBackground(
) {
    MisoTheme { colors, typography ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = colors.M1)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_leaf_1),
                        contentDescription = "Leaf Icon 1",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(188.dp, 247.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_leaf_2),
                        contentDescription = "Leaf Icon 2",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(191.dp, 247.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SplashBackgroundPreView() {
    SplashBackground()
}