package com.example.miso.ui.list.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController
import com.example.miso.ui.component.button.MisoBackBlackButton
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.list.component.list.InquiryList
import com.example.miso.ui.list.component.list.ListTitleText

@Composable
fun ListScreen(
    context: Context,
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MisoBackBlackButton { navController.popBackStack() }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ListTitleText()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(144.dp))
            InquiryList(items = 4, navController = navController)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ListScreenPreView() {
    ListScreen(LocalContext.current, NavController(LocalContext.current))
}