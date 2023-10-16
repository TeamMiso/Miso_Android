package com.example.miso.ui.sign_in.screen

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.util.keyboardAsState
import com.example.miso.ui.sign_in.component.SignInBackground
import com.example.miso.ui.sign_in.component.SignInButton
import com.example.miso.ui.sign_in.component.SignInContentText
import com.example.miso.ui.sign_in.component.SignInErrorSimpleTextField
import com.example.miso.ui.sign_in.component.SignInSimpleTextField

@Composable
fun SignInScreen(
    context: Context,
    onSignUpClick: () -> Unit,
    onMainClick: () -> Unit
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

    val targetOffset = if (!isClick) 0.dp else (-280).dp
    val offset by animateDpAsState(targetValue = targetOffset, label = "")

    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

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
        SignInBackground(isClick = isClick)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = offset),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.6f))
            SignInSimpleTextField(
                isError = isError,
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
            SignInErrorSimpleTextField(
                isError = isError,
                placeHolder = "비밀번호를 입력해주세요",
                readOnly = false,
                errorText = "이메일 또는 비밀번호가 일치하지 않습니다.",
                setChangeText = pw,
                onFocusChange = { isTextFieldFocused ->
                    isClick = isTextFieldFocused
                },
                onValueChange = {text ->
                    pw = text
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            SignInContentText(onSignUpClick = { onSignUpClick() })
            if (!isClick) {
                Spacer(modifier = Modifier.weight(1f))
            }
            else {
                Spacer(modifier = Modifier.height(30.dp))
            }
            SignInButton {
                if (email.isNotEmpty() && pw.isNotEmpty()) {
                    isError = false
                    onMainClick()
                }
                else {
                    isError = true
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignInScreenPreView() {
    SignInScreen(LocalContext.current, onSignUpClick = {}, onMainClick = {})
}
