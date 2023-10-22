package com.example.miso.ui.sign_up

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.sign_up.screen.CompleteScreen
import com.example.miso.ui.sign_up.screen.EmailScreen
import com.example.miso.ui.sign_up.screen.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

enum class SignUpPage(val value: String) {
    SignUp("SignUp"),
    Email("Email"),
    Complete("Complete"),
}

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    override fun init() {
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "SignUp"
            ) {
                composable(SignUpPage.SignUp.name) {
                    SignUpScreen(
                        context = this@SignUpActivity,
                        onEmailClick = {
                            navController.navigate(SignUpPage.Email.name)
                        },
                        onLogInClick = {
                            pageLogIn()
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

    private fun pageLogIn() {
        startActivity(
            Intent(
                this,
                LogInActivity::class.java
            )
        )
    }
}