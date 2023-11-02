package com.example.miso.ui.sign_up

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.sign_up.screen.CompleteScreen
import com.example.miso.ui.sign_up.screen.EmailScreen
import com.example.miso.ui.sign_up.screen.SignUpScreen
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.EmailViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class SignUpPage(val value: String) {
    SignUp("SignUp"),
    Email("Email"),
    Complete("Complete"),
}

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val emailViewModel by viewModels<EmailViewModel>()

    override fun init() {
        lifecycleScope.launch {
            authViewModel.authSignUpResponse.collect {
                if (it is Event.Success) {
                    Log.d("signup", it.toString())
                }
            }
        }
        lifecycleScope.launch {
            emailViewModel.emailResponse.collect {
                if (it is Event.Success) {
                    Log.d("email", it.toString())
                }
            }
        }
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
                            authViewModel.authSignUp(body = body)
                        }
                    )
                }
                composable(SignUpPage.Email.name) {
                    EmailScreen(
                        context = this@SignUpActivity,
                        onCompleteClick = {
                            navController.navigate(SignUpPage.Complete.name)
                        },
                        navController = navController,
                        onEmailClick = { body ->
                            emailViewModel.email(body = body)
                        }
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

    private fun pageLogIn() {
        startActivity(
            Intent(
                this,
                LogInActivity::class.java
            )
        )
    }
}