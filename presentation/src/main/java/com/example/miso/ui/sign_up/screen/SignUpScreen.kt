package com.example.miso.ui.sign_up.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.miso.ui.component.util.keyboardAsState
import com.example.miso.ui.sign_up.component.sign_up.EmailTextField
import com.example.miso.ui.sign_up.component.sign_up.MoveLogInText
import com.example.miso.ui.sign_up.component.sign_up.PasswordTextField
import com.example.miso.ui.sign_up.component.sign_up.RePasswordTextField
import com.example.miso.ui.sign_up.component.SignUpBackground
import com.example.miso.ui.sign_up.component.SignUpBackground2
import com.example.miso.ui.sign_up.component.sign_up.SignUpButton
import com.example.miso.ui.sign_up.component.SignUpTitleText

@Composable
fun SignUpScreen(
    context: Context,
    onLogInClick: () -> Unit,
    onEmailClick: () -> Unit,
    onSignUpClick: (body: AuthSignUpRequestModel) -> Unit
) {
    var isClick by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState()
    var isState by remember { mutableStateOf("Normal") }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(isKeyboardOpen) {
        isClick = isKeyboardOpen
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var repw by remember { mutableStateOf("") }

    if (pw.isEmpty() || repw.isEmpty()) isState = "Normal"
    else if (pw == repw) isState = "Success"
    else isState = "Error"

    val body = AuthSignUpRequestModel(
        email = email,
        password = pw,
        passwordCheck = repw
    )

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
        SignUpBackground()
        Column {
            Spacer(modifier = Modifier.weight(1f))
            SignUpBackground2()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.17f))
            SignUpTitleText()
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            EmailTextField(
                isError = false,
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
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            PasswordTextField(
                isState = isState,
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
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
            RePasswordTextField(
                isState = isState,
                placeHolder = "Re Password",
                readOnly = false,
                setChangeText = repw,
                onFocusChange = { isTextFieldFocused ->
                    isClick = isTextFieldFocused
                },
                onValueChange = { text ->
                    repw = text
                }
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.145f))
            SignUpButton {
                if (email.isNotEmpty() && pw.isNotEmpty() && repw.isNotEmpty()) {
                    onSignUpClick(body)
                    onEmailClick()
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.01f))
            MoveLogInText {
                onLogInClick()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreView() {
    SignUpScreen(LocalContext.current, onLogInClick = {}, onEmailClick = {}, onSignUpClick = {})
}