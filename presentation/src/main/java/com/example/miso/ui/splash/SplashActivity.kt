package com.example.miso.ui.splash

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
import com.example.miso.viewmodel.UserViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

    override fun init() {
        if (userViewModel.getUserInfoResponse.value is Event.Loading) {
            setContent {
                MisoTheme { _, _ ->
                    SplashScreen()
                }
            }
        }
        lifecycleScope.launch {
            delay(800)
            authViewModel.getAccessToken()
            userViewModel.getUserInfo()
        }
        lifecycleScope.launch {
            authViewModel.getAccessTokenResponse.collect {
                if (it is Event.Success) {
                    if (it.data!!.isBlank()) {
                        pageLogIn()
                        finish()
                    }
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.getUserInfoResponse.collect {
                when (it) {
                    is Event.Success -> {
                        userViewModel.saveUserInfo(it.data!!)
                    }

                    is Event.Loading -> {}

                    else -> {
                        pageLogIn()
                        finish()
                    }
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.saveUserInfoResponse.collect {
                if (it is Event.Success) {
                    pageMain()
                    finish()
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