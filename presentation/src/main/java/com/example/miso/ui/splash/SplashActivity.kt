package com.example.miso.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.main.MainActivity
import com.example.miso.ui.splash.screen.SplashScreen
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun init() {
        setContent {
            if (authViewModel.getAccessTokenResponse.value is Event.Loading) {
                MisoTheme { _, _ ->
                    SplashScreen()
                }
            }
        }
        authViewModel.getAccessToken()
        lifecycleScope.launch {
            authViewModel.getAccessTokenResponse.collect {
                if (it is Event.Success) {
                    if (it.data!!.isNotBlank()) pageMain()
                    else pageLogIn()
                }
                else {
                    pageLogIn()
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

    private fun pageMain() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
    }
}