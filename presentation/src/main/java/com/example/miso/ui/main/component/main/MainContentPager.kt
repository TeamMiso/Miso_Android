package com.example.miso.ui.main.component.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContentPager() {
    val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2 - 3)
    val navController = rememberNavController()
    var isFocus by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            pageCount = Int.MAX_VALUE,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 30.dp)
        ) { page ->
            val isPageFocused = isFocus == page

            when (page % 4) {
                0 -> {
                    MoveCameraButton(isPageFocused) {
                        navController.navigate("Camera")
                    }
                }
                1 -> {
                    MoveShopButton(isPageFocused) {
                        navController.navigate("Shop")
                    }
                }
                2 -> {
                    MoveInquiryButton(isPageFocused) {
                        navController.navigate("Inquiry")
                    }
                }
                3 -> {
                    MoveListButton(isPageFocused) {
                        navController.navigate("List")
                    }
                }
            }

            LaunchedEffect(pagerState.currentPage) {
                isFocus = pagerState.currentPage
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        SlidingIndicator(
            pageCount = 4,
            currentPage = pagerState.currentPage % 4,
            targetPage = pagerState.targetPage % 4,
            currentPageOffsetFraction = pagerState.currentPageOffsetFraction
        )
    }

    NavHost(navController, startDestination = "Camera") {
        composable("Camera") {

        }
        composable("Shop") {

        }
        composable("Inquiry") {

        }
        composable("List") {

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainContentPagerPreView() {
    MainContentPager()
}