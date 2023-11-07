package com.example.miso.ui.main

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.camera.CameraActivity
import com.example.miso.ui.camera.screen.CameraScreen
import com.example.miso.ui.inquiry.screen.InquiryScreen
import com.example.miso.ui.list.screen.DetailScreen
import com.example.miso.ui.list.screen.ListScreen
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.main.screen.MainScreen
import com.example.miso.ui.main.screen.SearchScreen
import com.example.miso.ui.sign_up.SignUpPage
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.UserViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class MainPage(val value: String) {
    Main("Main"),
    Search("Search"),
    Inquiry("Inquiry"),
    List("List"),
    Detail("Detail"),
    Camera("Camera")
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

    override fun init() {
        userViewModel.getRole()
        lifecycleScope.launch {
            authViewModel.logoutResponse.collect {
                if (it is Event.Success) {
                    pageLogIn()
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.getRoleResponse.collect { response ->
                if (response is Event.Success) {
                    setContent {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            composable(MainPage.Main.name) {
                                MainScreen(
                                    context = this@MainActivity,
                                    onCameraClick = { navController.navigate(MainPage.Camera.value)},
                                    onInquiryClick = { navController.navigate(MainPage.Inquiry.value) },
                                    onListClick = { navController.navigate(MainPage.List.value) },
                                    onSearchClick = { navController.navigate(MainPage.Search.value) },
                                    onLogoutClick = { authViewModel.logout() }
                                )
                            }
                            composable(MainPage.Camera.name){
                                CameraScreen(
                                    context = this@MainActivity,
                                    navController = navController
                                )
                            }
                            composable(MainPage.Search.name) {
                                SearchScreen(
                                    context = this@MainActivity,
                                    navController = navController
                                )
                            }
                            composable(MainPage.Inquiry.name) {
                                InquiryScreen(
                                    context = this@MainActivity,
                                    navController = navController
                                )
                            }
                            composable(MainPage.List.name) {
                                ListScreen(
                                    context = this@MainActivity,
                                    navController = navController,
                                    role = response.data ?: "ROLE_USER"
                                )
                            }
                            composable(MainPage.Detail.name + "/{index}", arguments = listOf(navArgument("index") { type = NavType.IntType })) { backStackEntry ->
                                backStackEntry.arguments?.getInt("index")?.let {
                                    DetailScreen(
                                        context = this@MainActivity,
                                        index = it,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
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
}
