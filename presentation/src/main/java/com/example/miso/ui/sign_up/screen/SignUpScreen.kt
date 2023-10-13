package com.example.miso.ui.sign_up.screen

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.util.keyboardAsState
import com.example.miso.ui.sign_up.component.SignUpBackground
import com.example.miso.ui.sign_up.component.SignUpButton
import com.example.miso.ui.sign_up.component.SignUpErrorTextField
import com.example.miso.ui.sign_up.component.SignUpSimpleTextField
import com.example.miso.ui.sign_up.component.SignUpTextField

@Composable
fun SignUpScreen(
    context: Context,
    onEmailClick: () -> Unit,
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

    val targetOffset = if (!isClick) 0.dp else (-290).dp
    val offset by animateDpAsState(targetValue = targetOffset, label = "")

    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var repw by remember { mutableStateOf("") }

    if (pw == repw) isError = false
    else isError = true

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
        SignUpBackground(isClick = isClick)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = offset),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.55f))
            SignUpSimpleTextField(
                isError = false,
                placeHolder = "이메일을 입력해주세요",
                readOnly = false,
                setChangeText = email,
                onFocusChange = { isTextFieldFocused ->
                    isClick = isTextFieldFocused
                },
                onValueChange = { text ->
                    email = text
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SignUpTextField(
                isError = isError,
                placeHolder = "비밀번호를 입력해주세요",
                readOnly = false,
                setChangeText = pw,
                onFocusChange = { isTextFieldFocused ->
                    isClick = isTextFieldFocused
                },
                onValueChange = { text ->
                    pw = text
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SignUpErrorTextField(
                isError = isError,
                placeHolder = "비밀번호를 다시 입력해 주세요.",
                readOnly = false,
                errorText = if (!isError) "비밀번호가 일치합니다." else "비밀번호가 일치하지 않습니다.",
                setChangeText = repw,
                onFocusChange = { isTextFieldFocused ->
                    isClick = isTextFieldFocused
                },
                onValueChange = { text ->
                    repw = text
                }
            )
            if (!isClick) {
                Spacer(modifier = Modifier.weight(1f))
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }
            SignUpButton {
                if (email.isNotEmpty() && pw.isNotEmpty() && repw.isNotEmpty()) {
                    onEmailClick()
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreView() {
    SignUpScreen(LocalContext.current, onEmailClick = {})
}