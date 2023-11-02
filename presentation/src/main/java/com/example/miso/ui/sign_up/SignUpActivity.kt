package com.example.miso.ui.sign_up

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.sign_up.screen.CompleteScreen
import com.example.miso.ui.sign_up.screen.EmailScreen
import com.example.miso.ui.sign_up.screen.SignUpScreen
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint

enum class SignUpPage(val value: String) {
    SignUp("SignUp"),
    Email("Email"),
    Complete("Complete"),
}

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    private val viewModel by viewModels<AuthViewModel>()

    override fun init() {
        observeSignUpEvent()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "SignUp"
            ) {
                composable(SignUpPage.SignUp.name) {
                    SignUpScreen(
                        context = this@SignUpActivity,
                        onLogInClick = {
                            pageLogIn()
                        },
                        onEmailClick = {
                            navController.navigate(SignUpPage.Email.name)
                        },
                        onSignUpClick = { body ->
                            viewModel.authSignUp(body = body)
                        }
                    )
                }
                composable(SignUpPage.Email.name) {
                    EmailScreen(
                        context = this@SignUpActivity,
                        onCompleteClick = {
                            navController.navigate(SignUpPage.Complete.name)
                        },
                        navController = navController
                    )
                }
                composable(SignUpPage.Complete.name) {
                    CompleteScreen(
                        context = this@SignUpActivity,
                        onLogInClick = {
                            pageLogIn()
                        }
                    )
                }
            }
        }
    }

    private fun observeSignUpEvent() {
        viewModel.authSignUpResponse.observe(this) { event ->
            when (event) {
                is Event.Success -> {
                    Log.d("signup", event.toString())
                }
                else -> {
                    Log.d("signup", event.toString())
                }
            }
        }
    }

    private fun pageLogIn() {
        startActivity(
            Intent(
                this,
                LogInActivity::class.java
            )
        )
    }
}