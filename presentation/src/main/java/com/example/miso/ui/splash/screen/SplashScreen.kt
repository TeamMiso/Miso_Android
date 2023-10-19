package com.example.miso.ui.splash.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.icon.MisoLogoIcon
import com.example.miso.ui.component.icon.MisoLogoWhiteIcon
import com.example.miso.ui.splash.component.SplashBackground
import com.example.miso.ui.splash.component.SplashText

@Composable
fun SplashScreen() {
    Box {
        SplashBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MisoLogoWhiteIcon(modifier = Modifier.size(70.dp))
            SplashText()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreView() {
    SplashScreen()
}