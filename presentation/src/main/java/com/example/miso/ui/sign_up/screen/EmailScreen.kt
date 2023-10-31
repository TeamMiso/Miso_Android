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
import androidx.navigation.NavController
import com.example.miso.ui.component.util.keyboardAsState
import com.example.miso.ui.sign_up.component.sign_up.EmailTextField
import com.example.miso.ui.sign_up.component.sign_up.MoveLogInText
import com.example.miso.ui.sign_up.component.sign_up.PasswordTextField
import com.example.miso.ui.sign_up.component.sign_up.RePasswordTextField
import com.example.miso.ui.sign_up.component.SignUpBackground
import com.example.miso.ui.sign_up.component.SignUpBackground2
import com.example.miso.ui.sign_up.component.sign_up.SignUpButton
import com.example.miso.ui.sign_up.component.SignUpTitleText
import com.example.miso.ui.sign_up.component.email.EmailButton
import com.example.miso.ui.sign_up.component.email.EmailContentText
import com.example.miso.ui.sign_up.component.email.EmailIcon
import com.example.miso.ui.sign_up.component.email.MoveBackText
import com.example.miso.ui.sign_up.component.email.NumberTextField

@Composable
fun EmailScreen(
    context: Context,
    onCompleteClick: () -> Unit,
    navController: NavController
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
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            EmailIcon()
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            EmailContentText()
            Spacer(modifier = Modifier.fillMaxHeight(0.035f))
            NumberTextField(
                text = number,
                isError = isError,
                onValueChange = {
                    number = it
                }
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.195f))
            EmailButton {
                if (number.isNotEmpty()) {
                    onCompleteClick()
                }
                else {
                    isError = true
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.01f))
            MoveBackText {
                navController.popBackStack()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmailScreenPreView() {
    EmailScreen(LocalContext.current, onCompleteClick = {}, navController = NavController(LocalContext.current))
}