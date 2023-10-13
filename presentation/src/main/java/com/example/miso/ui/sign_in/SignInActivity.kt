package com.example.miso.ui.sign_in

import android.content.Intent
import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.sign_in.screen.SignInScreen
import com.example.miso.ui.sign_up.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity() {
    override fun init() {
        setContent {
            SignInScreen(
                context = this,
                onSignUpClick = {
                    pageSignUp()
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
}