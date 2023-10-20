package com.example.miso.ui.splash

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.splash.screen.SplashScreen
import com.example.miso.ui.theme.MisoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun init() {
        setContent {
            MisoTheme { _, _ ->
                SplashScreen()
            }
        }
        lifecycleScope.launch {
            delay(1000)
            pageLogIn()
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
}