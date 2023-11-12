package com.example.miso.ui.camera.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    viewModel: CameraViewModel
){
    CheckPermission(context = context, navController = navController,viewModel = viewModel)
    Box(){
        CameraBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.03f))
                CameraBackBtn{ navController.popBackStack() }
                Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                CameraFlashBtn(
                    onClick = { }
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.87f))
        }
    }
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermission(
    context: Context,
    navController: NavController,
    viewModel: CameraViewModel
){
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(key1 = Unit) {
        if (!cameraPermissionState.status.isGranted && !cameraPermissionState.status.shouldShowRationale) run {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (cameraPermissionState.status.isGranted) {
        LunchCameraScreen(navController = navController,viewModel = viewModel)
    } else {
        navController.popBackStack()
    }
}
@Composable
fun LunchCameraScreen(navController: NavController,viewModel: CameraViewModel) {
    var capturedPhotoState by remember { mutableStateOf(false) }
    val captureImgBitmapState by viewModel.captureImgBitmapState.collectAsState()
    CameraPreview(
        onPhotoCapturedData = viewModel::loadImgBitmap,
        onPhotoCaptured = {captured ->
            if(captured) navController.navigate(MainPage.CameraResult.value)
        }
    )

}