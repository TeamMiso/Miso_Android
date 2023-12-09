package com.example.miso.ui.log_in.screen

import android.annotation.SuppressLint
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
import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.miso.R
import com.example.miso.ui.component.progressbar.MisoProgressbar
import com.example.miso.ui.component.snackbar.MisoSnackbar
import com.example.miso.ui.util.keyboardAsState
import com.example.miso.ui.log_in.component.EmailTextField
import com.example.miso.ui.log_in.component.LogInBackground
import com.example.miso.ui.log_in.component.LogInBackground2
import com.example.miso.ui.log_in.component.LogInButton
import com.example.miso.ui.log_in.component.LogInTitleText
import com.example.miso.ui.log_in.component.MoveSignUpText
import com.example.miso.ui.log_in.component.PasswordTextField
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LogInScreen(
    context: Context,
    lifecycleScope: CoroutineScope,
    viewModel: AuthViewModel,
    onSignUpClick: () -> Unit,
    onMainClick: (body: AuthLogInRequestModel) -> Unit
) {
    var isClick by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState()
    var isError by remember { mutableStateOf(false) }

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
            viewModel.changeLogIn()
            progressState.value = false
        }
    }

    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val body = AuthLogInRequestModel(
        email = email,
        password = pw,
        deviceToken = ""
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
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
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
            Spacer(modifier = Modifier.fillMaxHeight(0.03f))
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
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))
            LogInButton {
                if (email.isNotEmpty() && pw.isNotEmpty()) {
                    isError = false
                    lifecycleScope.launch {
                        login(
                            viewModel = viewModel,
                            errorText = { text ->
                                errorText.value = text
                                snackBarVisibility.value = true
                            },
                            progressState = { state ->
                                progressState.value = state
                            }
                        )
                    }
                    onMainClick(body)
                } else {
                    isError = true
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.01f))
            MoveSignUpText {
                onSignUpClick()
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

suspend fun login(
    viewModel: AuthViewModel,
    errorText: (errorText: String) -> Unit,
    progressState: (progressState: Boolean) -> Unit
) {
    viewModel.authLogInResponse.collect {
        when (it) {
            is Event.Loading -> {
                progressState(true)
            }

            is Event.Success -> {
                viewModel.saveToken(token = it.data!!)
                progressState(false)
            }

            is Event.BadRequest -> {
                errorText("비밀번호가 일치하지 않습니다!")
                progressState(false)
            }

            is Event.ForBidden -> {
                errorText("이메일이 인증되지 않았습니다!")
                progressState(false)
            }

            is Event.NotFound -> {
                errorText("사용자를 찾을 수 없습니다!")
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
fun LogInScreenPreView() {
    // LogInScreen(LocalContext.current, false, "", onSignUpClick = {}, onMainClick = {})
}