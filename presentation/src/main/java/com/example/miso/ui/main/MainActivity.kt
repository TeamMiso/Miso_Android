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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.camera.screen.CameraResultScreen
import com.example.miso.ui.camera.screen.CameraScreen
import com.example.miso.ui.inquiry.screen.InquiryScreen
import com.example.miso.ui.list.screen.DetailScreen
import com.example.miso.ui.list.screen.ListScreen
import com.example.miso.ui.log_in.LogInActivity
import com.example.miso.ui.main.screen.MainScreen
import com.example.miso.ui.main.screen.SearchScreen
import com.example.miso.ui.purchase.screen.PurchaseScreen
import com.example.miso.ui.result.screen.ResultScreen
import com.example.miso.ui.shop.screen.ShopDetailScreen
import com.example.miso.ui.shop.screen.ShopScreen
import com.example.miso.viewmodel.AuthViewModel
import com.example.miso.viewmodel.CameraViewModel
import com.example.miso.viewmodel.InquiryViewModel
import com.example.miso.viewmodel.PurchaseViewModel
import com.example.miso.viewmodel.RecyclablesViewModel
import com.example.miso.viewmodel.ShopViewModel
import com.example.miso.viewmodel.UserViewModel
import com.example.miso.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class MainPage(val value: String) {
    Main("Main"),
    Camera("Camera"),
    CameraResult("CameraResult"),
    Search("Search"),
    Shop("Shop"),
    ShopDetail("ShopDetail"),
    PurChase("Purchase"),
    Inquiry("Inquiry"),
    List("List"),
    Detail("Detail"),
    Result("Result")
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val inquiryViewModel by viewModels<InquiryViewModel>()
    private val cameraViewModel by viewModels<CameraViewModel>()
    private val recyclablesViewModel by viewModels<RecyclablesViewModel>()
    private val shopViewModel by viewModels<ShopViewModel>()
    private val purchaseViewModel by viewModels<PurchaseViewModel>()

    private lateinit var navController: NavController
    override fun init() {
        userViewModel.getRole()
        lifecycleScope.launch {
            authViewModel.logoutResponse.collect {
                if (it is Event.Success) {
                    pageLogIn()
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            inquiryViewModel.getInquiryListDetailResponse.collect {
                if (it is Event.Success) {
                    inquiryViewModel.addInquiryListDetail(it.data!!)
                    navController.navigate(MainPage.Detail.value)
                }
            }
        }
        lifecycleScope.launch {
            inquiryViewModel.adoptResponse.collect {
                if (it is Event.Success) {
                    navController.navigate(MainPage.Main.value)
                }
            }
        }
        lifecycleScope.launch {
            inquiryViewModel.unadoptResponse.collect {
                if (it is Event.Success) {
                    navController.navigate(MainPage.Main.value)
                }
            }
        }
        lifecycleScope.launch {
            recyclablesViewModel.resultResponse.collect {
                if (it is Event.Success) {
                    recyclablesViewModel.result.value = it.data!!
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.givePointResponse.collect {
                if (it is Event.Success) {
                    userViewModel.getUserInfo()
                    navController.navigate(MainPage.Main.value){
                        popUpTo(MainPage.Main.value){
                            inclusive = true
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.getRoleResponse.collect { response ->
                if (response is Event.Success) {
                    setContent {
                        navController = rememberNavController()
                        navController.addOnDestinationChangedListener { controller, destination, arguments ->
                            Log.d("Navigation", "Destination changed: ${destination.route}")
                        }
                        NavHost(
                            navController = navController as NavHostController,
                            startDestination = MainPage.Main.value
                        ) {
                            composable(MainPage.Main.name) {
                                MainScreen(
                                    context = this@MainActivity,
                                    lifecycleScope = lifecycleScope,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    onCameraClick = { navController.navigate(MainPage.Camera.value) },
                                    onShopClick = {navController.navigate(MainPage.Shop.value)},
                                    onInquiryClick = { navController.navigate(MainPage.Inquiry.value) },
                                    onListClick = { navController.navigate(MainPage.List.value) },
                                    onSearchClick = { navController.navigate(MainPage.Search.value) },
                                    onLogoutClick = { authViewModel.logout() }
                                )
                            }
                            composable(MainPage.Camera.name) {
                                CameraScreen(
                                    context = this@MainActivity,
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                )
                            }
                            composable(MainPage.CameraResult.name) {
                                CameraResultScreen(
                                    context = this@MainActivity,
                                    navController = navController,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    viewModelResult = viewModel(LocalContext.current as MainActivity),
                                    onResultCallback = {
                                        recyclablesViewModel.isAiResult.value = true
                                        navController.navigate(MainPage.Result.value)
                                    },
                                    onReCaptureClick = { navController.popBackStack() },
                                    onGoHomeClick = {
                                        navController.popBackStack()
                                        navController.popBackStack()
                                    }
                                )
                            }
                            composable(MainPage.Search.name) {
                                SearchScreen(
                                    context = this@MainActivity,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    onBackClick = { navController.popBackStack() },
                                    onResultClick = {
                                        recyclablesViewModel.isAiResult.value = false
                                        navController.navigate(MainPage.Result.value)
                                    },
                                    onInquiryClick = { navController.navigate(MainPage.Inquiry.value) }
                                )
                            }
                            composable(MainPage.Shop.name){
                                ShopScreen(
                                    context = this@MainActivity,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    pointViewModel = viewModel(LocalContext.current as MainActivity),
                                    navController = navController,
                                    )
                            }
                            composable(MainPage.ShopDetail.name){
                                ShopDetailScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    navController = navController,
                                )
                            }
                            composable(MainPage.PurChase.name){
                                PurchaseScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable(MainPage.Inquiry.name) {
                                InquiryScreen(
                                    context = this@MainActivity,
                                    lifecycleScope = lifecycleScope,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    navController = navController,
                                    onBackClick = { navController.popBackStack() },
                                    onInquiryClick = { filePart, inquiryPart ->
                                        inquiryViewModel.requestInquiry(
                                            filePart = filePart,
                                            inquiryPart = inquiryPart
                                        )
                                    }
                                )
                            }
                            composable(MainPage.List.name) {
                                ListScreen(
                                    context = this@MainActivity,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    role = response.data ?: "",
                                    onBackClick = {
                                        navController.popBackStack()
                                        inquiryViewModel.clearInquiryList()
                                        inquiryViewModel.clearInquiryListAll()
                                    },
                                    onItemClick = { id ->
                                        inquiryViewModel.getInquiryListDetail(id)
                                    }
                                )
                            }
                            composable(MainPage.Detail.name) {
                                DetailScreen(
                                    context = this@MainActivity,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    role = response.data ?: "",
                                    onBackClick = { navController.popBackStack() },
                                )
                            }
                            composable(MainPage.Result.name) {
                                ResultScreen(
                                    context = this@MainActivity,
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    onResultClick = {
                                        if(recyclablesViewModel.isAiResult.value){
                                            userViewModel.givePoint()
                                        }else{
                                            navController.navigate(MainPage.Main.value){
                                                popUpTo(MainPage.Main.value){
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                )
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