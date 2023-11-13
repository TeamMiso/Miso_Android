package com.example.miso.ui.main.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.component.snackbar.MisoSnackber
import com.example.miso.ui.main.component.logout.LogoutDialog
import com.example.miso.ui.main.component.main.LogoutButton
import com.example.miso.ui.main.component.main.MainContentPager
import com.example.miso.ui.main.component.main.MainContentText
import com.example.miso.ui.main.component.main.MainTitleText
import com.example.miso.ui.main.component.main.SearchButton
import com.example.miso.viewmodel.InquiryViewModel
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    context: Context,
    lifecycleScope: CoroutineScope,
    viewModel: InquiryViewModel,
    onCameraClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onListClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    val snackBarVisibility = remember { mutableStateOf(false) }

    if (openDialog) {
        LogoutDialog(
            openDialog = openDialog,
            onStateChange = {
                openDialog = it
            },
            onLogoutClick = {
                onLogoutClick()
            }
        )
    }

    val requestInquiryResponse = viewModel.requestInquiryResponse.collectAsState()
    if (requestInquiryResponse.value is Event.Success) {
        LaunchedEffect(requestInquiryResponse.value) {
            lifecycleScope.launch {
                snackBarVisibility.value = true
                delay(3000)
                snackBarVisibility.value = false
                viewModel.changeRequestInquiry()
            }
        }
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
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            MainContentText()
        }
        Column {
            Spacer(modifier = Modifier.fillMaxHeight(0.25f))
            MainContentPager(
                onInquiryClick = { onInquiryClick() },
                onListClick = { onListClick() },
                onCameraClick = { onCameraClick() }
            )
        }
        MisoSnackber(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding(),
            text = "문의글이 성공적으로 등록되었습니다!",
            visible = snackBarVisibility.value,
            icon = R.drawable.ic_check
        ) {
            snackBarVisibility.value = false
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreView() {
    // MainScreen(LocalContext.current, {}, {}, {}, onSearchClick = {}, {})
}