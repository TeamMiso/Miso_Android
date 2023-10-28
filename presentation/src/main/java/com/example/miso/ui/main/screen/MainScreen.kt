package com.example.miso.ui.main.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.main.component.logout.LogoutDialog
import com.example.miso.ui.main.component.main.LogoutButton
import com.example.miso.ui.main.component.main.MainContentPager
import com.example.miso.ui.main.component.main.MainContentText
import com.example.miso.ui.main.component.main.MainTitleText
import com.example.miso.ui.main.component.main.SearchButton

@Composable
fun MainScreen(
    context: Context,
    onInquiryClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        LogoutDialog(
            openDialog = openDialog,
            onStateChange = {
                openDialog = it
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MainTitleText()
            Spacer(modifier = Modifier.weight(1f))
            SearchButton {
                onSearchClick()
            }
            Spacer(modifier = Modifier.width(20.dp))
            LogoutButton {
                openDialog = true
            }
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Spacer(modifier = Modifier.height(180.dp))
            MainContentText()
        }
        Column {
            Spacer(modifier = Modifier.height(230.dp))
            MainContentPager(
                onInquiryClick = { onInquiryClick() }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreView() {
    MainScreen(LocalContext.current, {}, onSearchClick = {})
}