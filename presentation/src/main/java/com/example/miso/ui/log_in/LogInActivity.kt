package com.example.miso.ui.log_in

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.screen.LogInScreen
import com.example.miso.ui.main.MainActivity
import com.example.miso.ui.sign_up.SignUpActivity
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.util.Event
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    private lateinit var token: String

    override fun init() {
        lifecycleScope.launch {
            authViewModel.saveTokenResponse.collect {
                if (it is Event.Success) {
                    val deviceTokenSF = getSharedPreferences("deviceToken", MODE_PRIVATE)
                    deviceTokenSF.edit().putString("device", token).apply()
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
                    initNotification(body)
                }
            )
        }
    }

    private fun initNotification(body: AuthLogInRequestModel) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val deviceTokenSF = getSharedPreferences("deviceToken", MODE_PRIVATE)
                token = task.result
                if (deviceTokenSF.getString("device", "") == token) {
                    body.deviceToken = token
                    authViewModel.authLogIn(body)
                }
            }
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