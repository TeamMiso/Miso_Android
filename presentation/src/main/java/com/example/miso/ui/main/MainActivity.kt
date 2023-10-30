package com.example.miso.ui.main

import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.miso.ui.base.BaseActivity
import com.example.miso.ui.inquiry.screen.InquiryScreen
import com.example.miso.ui.list.screen.DetailScreen
import com.example.miso.ui.list.screen.ListScreen
import com.example.miso.ui.main.screen.MainScreen
import com.example.miso.ui.main.screen.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

enum class MainPage(val value: String) {
    Main("Main"),
    Search("Search"),
    Inquiry("Inquiry"),
    List("List"),
    Detail("Detail")
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun init() {
        setContent {
            val navController = rememberNavController()
            var content = ""

            NavHost(
                navController = navController,
                startDestination = "Main"
            ) {
                composable(MainPage.Main.name) {
                    MainScreen(
                        context = this@MainActivity,
                        onInquiryClick = { navController.navigate(MainPage.Inquiry.value) },
                        onListClick = { navController.navigate(MainPage.List.value) },
                        onSearchClick = { navController.navigate(MainPage.Search.value) }
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
