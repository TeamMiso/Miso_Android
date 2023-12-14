package com.example.miso.ui.camera.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.miso.ui.camera.component.CameraBackBtn
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraFlashBtn
import com.example.miso.ui.camera.component.CameraPreview
import com.example.miso.ui.main.MainPage
import com.example.miso.viewmodel.CameraViewModel
import com.example.miso.viewmodel.InquiryViewModel_Factory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.flow.callbackFlow

@Composable
fun CameraScreen(
    context: Context,
    navController: NavController,
    viewModel: CameraViewModel,
    isInquiry: () -> Unit
) {
    val flashOn = remember { mutableStateOf(false) }
    LaunchedEffect(flashOn) {
        Log.d("testt_flash", flashOn.value.toString())
    }
    CheckPermission(context = context, navController = navController, viewModel = viewModel)
    CameraPreview(
        onPhotoCapturedData = viewModel::loadImgBitmap,
        onPhotoCaptured = { captured ->
            if ( captured && !viewModel.isInquiry.value) {
                navController.navigate(MainPage.CameraResult.value)
            } else {
                viewModel.isInquiry.value = false
                isInquiry()
            }
        },
        getFlashOn = flashOn.value
    )
    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(0.03f))
                CameraBackBtn { navController.popBackStack() }
                Spacer(modifier = Modifier.fillMaxWidth(0.38f))
                CameraFlashBtn(
                    onClick = { flashOn.value = !flashOn.value }
                )
            }
            CameraBackground()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermission(
    context: Context,
    navController: NavController,
    viewModel: CameraViewModel
) {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(key1 = Unit) {
        if (!cameraPermissionState.status.isGranted && !cameraPermissionState.status.shouldShowRationale) run {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (!cameraPermissionState.status.isGranted) {
        navController.popBackStack()
    }
}