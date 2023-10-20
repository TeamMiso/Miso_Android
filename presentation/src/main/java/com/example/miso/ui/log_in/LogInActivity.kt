package com.example.miso.ui.log_in

import android.content.Intent
import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.screen.LogInScreen
import com.example.miso.ui.main.MainActivity
import com.example.miso.ui.sign_up.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : BaseActivity() {
    override fun init() {
        setContent {
            LogInScreen(
                context = this,
                onSignUpClick = {
                    pageSignUp()
                },
                onMainClick = {
                    pageMain()
                }
            )
        }
    }

    private fun pageSignUp() {
        startActivity(
            Intent(
                this,
                SignUpActivity::class.java
            )
        )
    }

    private fun pageMain() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
    }
}