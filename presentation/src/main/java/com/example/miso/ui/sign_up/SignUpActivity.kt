package com.example.miso.ui.sign_up

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    private lateinit var navController: NavController

    override fun init() {
        setContent {
            navController = rememberNavController()

            NavHost(
                navController = navController as NavHostController,
                startDestination = SignUpPage.SignUp.value
            ) {
                composable(SignUpPage.SignUp.name) {
                    SignUpScreen(
                        context = this@SignUpActivity,
                        lifecycleScope = lifecycleScope,
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as SignUpActivity),
                        onLogInClick = {
                            pageLogIn()
                            finish()
                        },
                        onSignUpClick = { body ->
                            authViewModel.authSignUp(body = body)
                        }
                    )
                }
                composable(SignUpPage.Email.name) {
                    EmailScreen(
                        context = this@SignUpActivity,
                        lifecycleScope = lifecycleScope,
                        viewModel = viewModel(LocalContext.current as SignUpActivity),
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
                            finish()
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