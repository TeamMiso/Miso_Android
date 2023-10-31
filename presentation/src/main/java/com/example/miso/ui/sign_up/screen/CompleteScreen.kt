package com.example.miso.ui.sign_up.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.sign_up.component.SignUpTitleText
import com.example.miso.ui.sign_up.component.complete.CompleteBackground
import com.example.miso.ui.sign_up.component.complete.CompleteBackground2
import com.example.miso.ui.sign_up.component.complete.CompleteButton
import com.example.miso.ui.sign_up.component.complete.CompleteContentText

@Composable
fun CompleteScreen(
    context: Context,
    onLogInClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CompleteBackground()
        Column {
            Spacer(modifier = Modifier.weight(1f))
            CompleteBackground2()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.28f))
            SignUpTitleText()
            Spacer(modifier = Modifier.fillMaxHeight(0.168f))
            CompleteContentText()
            Spacer(modifier = Modifier.fillMaxHeight(0.195f))
            CompleteButton {
                onLogInClick()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CompleteScreenPreView() {
    CompleteScreen(LocalContext.current, onLogInClick = {})
}