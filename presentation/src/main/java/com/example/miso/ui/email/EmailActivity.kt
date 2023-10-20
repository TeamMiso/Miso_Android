package com.example.miso.ui.email

import android.content.Intent
import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.email.screen.EmailScreen
import com.example.miso.ui.log_in.LogInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailActivity : BaseActivity() {
    override fun init() {
        setContent {
            EmailScreen(
                context = this,
                onSignInClick = {
                    pageSingIn()
                }
            )
        }
    }

    private fun pageSingIn() {
        startActivity(
            Intent(
                this,
                LogInActivity::class.java
            )
        )
    }
}