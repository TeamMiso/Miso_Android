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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.miso.R
import com.example.miso.ui.component.progressbar.MisoProgressbar
import com.example.miso.ui.component.snackbar.MisoSnackbar
import com.example.miso.ui.sign_up.SignUpPage
import com.example.miso.ui.util.keyboardAsState
import com.example.miso.ui.sign_up.component.sign_up.EmailTextField
import com.example.miso.ui.sign_up.component.sign_up.MoveLogInText
import com.example.miso.ui.sign_up.component.sign_up.PasswordTextField
import com.example.miso.ui.sign_up.component.sign_up.RePasswordTextField
import com.example.miso.ui.sign_up.component.SignUpBackground
import com.example.miso.ui.sign_up.component.SignUpBackground2
import com.example.miso.ui.sign_up.component.sign_up.SignUpButton
import com.example.miso.ui.sign_up.component.SignUpTitleText
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun SignUpScreen(
    context: Context,
    lifecycleScope: CoroutineScope,
    navController: NavController,
    viewModel: AuthViewModel,
    onLogInClick: () -> Unit,
    onSignUpClick: (body: AuthSignUpRequestModel) -> Unit
) {
    var isClick by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState()
    var isState by remember { mutableStateOf("Normal") }

    val snackBarVisibility = remember { mutableStateOf(false) }
    val errorText = remember { mutableStateOf("") }
    val progressState = remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(isKeyboardOpen) {
        isClick = isKeyboardOpen
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(snackBarVisibility.value) {
        if (snackBarVisibility.value) {
            delay(1.5.seconds)
            snackBarVisibility.value = false
            viewModel.changeSignUp()
            progressState.value = false
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
                    lifecycleScope.launch {
                        signUp(
                            viewModel = viewModel,
                            navController = navController,
                            errorText = { text ->
                                errorText.value = text
                                snackBarVisibility.value = true
                            },
                            progressState = { state ->
                                progressState.value = state
                            }
                        )
                    }
                    onSignUpClick(body)
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.01f))
            MoveLogInText {
                onLogInClick()
            }
        }
        MisoSnackbar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding(),
            text = errorText.value,
            visible = snackBarVisibility.value,
            icon = R.drawable.ic_cancel
        ) {
            snackBarVisibility.value = false
        }
        if (progressState.value) {
            MisoProgressbar(
                modifier = Modifier
                    .align(Alignment.Center)
                    .statusBarsPadding()
            )
        }
    }
}

suspend fun signUp(
    viewModel: AuthViewModel,
    navController: NavController,
    errorText: (errorText: String) -> Unit,
    progressState: (progressState: Boolean) -> Unit
) {
    viewModel.authSignUpResponse.collect {
        when (it) {
            is Event.Loading -> {
                progressState(true)
            }

            is Event.Success -> {
                navController.navigate(SignUpPage.Email.name)
                progressState(false)
            }

            is Event.BadRequest -> {
                errorText("비밀번호가 재확인 비밀번호와 일치하지 않습니다!")
                progressState(false)
            }

            is Event.Conflict -> {
                errorText("이미 사용중인 이메일입니다!")
                progressState(false)
            }

            else -> {
                errorText("알 수 없는 에러가 발생했습니다!")
                progressState(false)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreView() {
    // SignUpScreen(LocalContext.current, onLogInClick = {}, onSignUpClick = {})
}