package com.example.miso.ui.log_in.screen

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
import com.example.miso.ui.log_in.component.EmailTextField
import com.example.miso.ui.log_in.component.LogInBackground
import com.example.miso.ui.log_in.component.LogInBackground2
import com.example.miso.ui.log_in.component.LogInButton
import com.example.miso.ui.log_in.component.LogInTitleText
import com.example.miso.ui.log_in.component.MoveSignUpText
import com.example.miso.ui.log_in.component.PasswordTextField

@Composable
fun LogInScreen(
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
        LogInBackground()
        Column {
            Spacer(modifier = Modifier.weight(1f))
            LogInBackground2()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.28f))
            LogInTitleText()
            Spacer(modifier = Modifier.height(12.dp))
            EmailTextField(
                isError = isError,
                placeHolder = "Email",
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
            PasswordTextField(
                isError = isError,
                placeHolder = "Password",
                readOnly = false,
                setChangeText = pw,
                onFocusChange = { isTextFieldFocused ->
                    isClick = isTextFieldFocused
                },
                onValueChange = { text ->
                    pw = text
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            LogInButton {
                if (email.isNotEmpty() && pw.isNotEmpty()) {
                    isError = false
                    onMainClick()
                } else {
                    isError = true
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            MoveSignUpText {
                onSignUpClick()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LogInScreenPreView() {
    LogInScreen(LocalContext.current, onSignUpClick = {}, onMainClick = {})
}