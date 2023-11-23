package com.example.miso.ui.log_in

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.screen.LogInScreen
import com.example.miso.ui.main.MainActivity
import com.example.miso.ui.sign_up.SignUpActivity
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun init() {
        lifecycleScope.launch {
            authViewModel.saveTokenResponse.collect {
                if (it is Event.Success) {
                    pageMain()
                    finish()
                }
            }
        }
        setContent {
            LogInScreen(
                context = this,
                lifecycleScope = lifecycleScope,
                viewModel = viewModel(LocalContext.current as LogInActivity),
                onSignUpClick = {
                    pageSignUp()
                    finish()
                },
                onMainClick = { body ->
                    authViewModel.authLogIn(body = body)
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