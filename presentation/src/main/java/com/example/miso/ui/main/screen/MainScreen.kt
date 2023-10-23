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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miso.ui.main.component.LogoutButton
import com.example.miso.ui.main.component.LogoutDialog
import com.example.miso.ui.main.component.MoveCameraButton
import com.example.miso.ui.main.component.MoveInquireButton
import com.example.miso.ui.main.component.MoveInquireCheckButton
import com.example.miso.ui.main.component.MovePurchaseButton
import com.example.miso.ui.main.component.SearchButton

@Composable
fun MainScreen(
    context: Context
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
            LogoutButton {
                openDialog = true
            }
            SearchButton {

            }
        }
        Column {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            MoveCameraButton {

            }
            Spacer(modifier = Modifier.height(8.dp))
            MovePurchaseButton {

            }
            Spacer(modifier = Modifier.height(8.dp))
            MoveInquireButton {

            }
            Spacer(modifier = Modifier.fillMaxHeight(0.3f))
            MoveInquireCheckButton {

            }
        }
    }
}