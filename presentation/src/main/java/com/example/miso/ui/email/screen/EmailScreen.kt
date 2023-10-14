package com.example.miso.ui.email.screen

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.util.keyboardAsState
import com.example.miso.ui.email.component.EmailButton
import com.example.miso.ui.email.component.EmailImage
import com.example.miso.ui.email.component.EmailText
import com.example.miso.ui.email.component.EmailTextField
import com.example.miso.ui.sign_up.component.SignUpBackground
import com.example.miso.ui.sign_up.component.SignUpButton
import com.example.miso.ui.sign_up.component.SignUpErrorTextField
import com.example.miso.ui.sign_up.component.SignUpSimpleTextField
import com.example.miso.ui.sign_up.component.SignUpTextField

@Composable
fun EmailScreen(
    context: Context,
    onClick: () -> Unit,
) {
    var isClick by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState()
    var isError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(isKeyboardOpen) {
        isClick = isKeyboardOpen
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    val targetOffset = if (!isClick) 0.dp else (-140).dp
    val offset by animateDpAsState(targetValue = targetOffset, label = "")

    var number by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = offset),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.4f))
            EmailImage()
            Spacer(modifier = Modifier.height(16.dp))
            EmailText()
            Spacer(modifier = Modifier.height(32.dp))
            EmailTextField(
                text = number,
                isError = isError,
                onValueChange = {
                    number = it
                }
            )
            if (!isClick) {
                Spacer(modifier = Modifier.weight(1f))
            } else {
                Spacer(modifier = Modifier.height(60.dp))
            }
            EmailButton(isError = isError) {
                if (number.isEmpty()) {
                    isError = true
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}