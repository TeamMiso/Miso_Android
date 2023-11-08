package com.example.miso.ui.main

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.inquiry.screen.InquiryScreen
import com.example.miso.ui.list.screen.DetailScreen
import com.example.miso.ui.list.screen.ListScreen
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.main.screen.MainScreen
import com.example.miso.ui.main.screen.SearchScreen
import com.example.miso.ui.sign_up.SignUpPage
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.InquiryViewModel
import com.example.miso.viewmodel.UserViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class MainPage(val value: String) {
    Main("Main"),
    Search("Search"),
    Inquiry("Inquiry"),
    List("List"),
    Detail("Detail")
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val inquiryViewModel by viewModels<InquiryViewModel>()

    private lateinit var navController: NavController

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
            inquiryViewModel.requestInquiryResponse.collect {
                if (it is Event.Success) {
                    navController.navigate(MainPage.Main.value)
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.getRoleResponse.collect { response ->
                if (response is Event.Success) {
                    setContent {
                        navController = rememberNavController()
                        NavHost(
                            navController = navController as NavHostController,
                            startDestination = "Main"
                        ) {
                            composable(MainPage.Main.name) {
                                MainScreen(
                                    context = this@MainActivity,
                                    onInquiryClick = { navController.navigate(MainPage.Inquiry.value) },
                                    onListClick = { navController.navigate(MainPage.List.value) },
                                    onSearchClick = { navController.navigate(MainPage.Search.value) },
                                    onLogoutClick = { authViewModel.logout() }
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
                                    navController = navController,
                                    onInquiryClick = { filePart, inquiryPart ->
                                        inquiryViewModel.requestInquiry(filePart = filePart, inquiryPart = inquiryPart)
                                    }
                                )
                            }
                            composable(MainPage.List.name) {
                                ListScreen(
                                    context = this@MainActivity,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    navController = navController,
                                    role = response.data ?: "",
                                    onBackClick = {
                                        navController.popBackStack()
                                        inquiryViewModel.clearInquiryList()
                                        inquiryViewModel.clearInquiryListAll()
                                    }
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
