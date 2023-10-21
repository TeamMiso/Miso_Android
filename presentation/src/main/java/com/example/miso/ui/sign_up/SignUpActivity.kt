package com.example.miso.ui.sign_up

import android.content.Intent
import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.email.EmailActivity
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.sign_up.screen.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    override fun init() {
        setContent {
            SignUpScreen(
                context = this,
                onEmailClick = {
                    pageEmail()
                },
                onLogInClick = {
                    pageLogIn()
                }
            )
        }
    }

    private fun pageEmail() {
        startActivity(
            Intent(
                this,
                EmailActivity::class.java
            )
        )
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