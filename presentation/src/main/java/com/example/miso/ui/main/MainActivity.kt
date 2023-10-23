package com.example.miso.ui.main

import androidx.activity.compose.setContent
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.main.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun init() {
        setContent {
            MainScreen(context = this)
        }
    }
}