package com.example.miso.ui.sign_up

import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.sign_up.screen.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    override fun init() {
        setContent {
            SignUpScreen(context = this)
        }
    }
}