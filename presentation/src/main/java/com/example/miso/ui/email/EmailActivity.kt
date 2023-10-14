package com.example.miso.ui.email

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.email.screen.EmailScreen
import com.example.miso.ui.sign_up.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailActivity : BaseActivity() {
    override fun init() {
        setContent {
            EmailScreen(
                context = this
            ) {

            }
        }
    }
}