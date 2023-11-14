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
import com.example.domain.model.email.request.EmailRequestModel
import com.example.miso.R
import com.example.miso.ui.component.progressbar.MisoProgressbar
import com.example.miso.ui.component.snackbar.MisoSnackbar
import com.example.miso.ui.sign_up.SignUpPage
import com.example.miso.ui.util.keyboardAsState
import com.example.miso.ui.sign_up.component.SignUpBackground
import com.example.miso.ui.sign_up.component.SignUpBackground2
import com.example.miso.ui.sign_up.component.SignUpTitleText
import com.example.miso.ui.sign_up.component.email.EmailButton
import com.example.miso.ui.sign_up.component.email.EmailContentText
import com.example.miso.ui.sign_up.component.email.EmailIcon
import com.example.miso.ui.sign_up.component.email.MoveBackText
import com.example.miso.ui.sign_up.component.email.NumberTextField
import com.example.miso.viewmodel.EmailViewModel
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun EmailScreen(
    context: Context,
    lifecycleScope: CoroutineScope,
    viewModel: EmailViewModel,
    navController: NavController,
    onEmailClick: (body: EmailRequestModel) -> Unit
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
            viewModel.changeEmail()
        }
    }

    var number by remember { mutableStateOf("") }

    val body = EmailRequestModel(
        randomKey = number
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
                    lifecycleScope.launch {
                        email(
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
                    onEmailClick(body)
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

suspend fun email(
    viewModel: EmailViewModel,
    navController: NavController,
    errorText: (errorText: String) -> Unit,
    progressState: (progressState: Boolean) -> Unit
) {
    viewModel.emailResponse.collect {
        when (it) {
            is Event.Loading -> {
                progressState(true)
            }

            is Event.Success -> {
                navController.navigate(SignUpPage.Complete.name)
                progressState(false)
            }

            is Event.Unauthorized -> {
                errorText("인증번호가 일치하지 않습니다!")
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
fun EmailScreenPreView() {
    // EmailScreen(LocalContext.current, navController = NavController(LocalContext.current), onEmailClick = {})
}