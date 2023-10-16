package com.example.miso.ui.splash

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.sign_in.SignInActivity
import com.example.miso.ui.splash.screen.SplashScreen
import com.example.miso.ui.theme.MisoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun init() {
        installSplashScreen()
        setContent {
            MisoTheme { colors, _ ->
                SplashScreen()
            }
        }
        lifecycleScope.launch {
            delay(1000)
            pageSignIn()
        }
    }

    private fun pageSignIn() {
        startActivity(
            Intent(
                this,
                SignInActivity::class.java
            )
        )
    }
}