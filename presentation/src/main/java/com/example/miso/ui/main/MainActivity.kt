package com.example.miso.ui.main

import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.main.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

enum class MainPage(val value: String) {
    Main("Main"),
    Search("Search")
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun init() {
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "Main"
            ) {
                composable(MainPage.Main.name) {
                    MainScreen(
                        context = this@MainActivity,
                        onSearchClick = { navController.navigate(MainPage.Search.value) }
                    )
                }
                composable(MainPage.Search.name) {

                }
            }
        }
    }
}
