package com.example.miso.ui.camera.screen

import android.Manifest
import android.content.Context
import android.content.Context.CAMERA_SERVICE
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.miso.ui.camera.component.CameraBackBtn
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraCaptureBtn
import com.example.miso.ui.camera.component.CameraFlashBtn
import com.example.miso.ui.camera.component.CameraPreview
import com.example.miso.viewmodel.util.PermissionHanding
import com.example.miso.viewmodel.util.PermissionHanding.HandlePermissionActions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun CameraScreen(context: Context, navController: NavController,cameraM: CameraManager){
    CheckPermission(context = context, navController = navController)
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
                    onClick = { },
                    cameraM
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.87f))
            //CameraCaptureBtn(onClick = {})
        }
    }
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermission(context: Context,navController: NavController){
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(key1 = Unit) {
        if (!cameraPermissionState.status.isGranted && !cameraPermissionState.status.shouldShowRationale) run {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (cameraPermissionState.status.isGranted) {
        LunchCameraScreen(navController = navController)
    } else {
        navController.popBackStack()
    }
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LunchCameraScreen(navController: NavController) {
    CameraPreview()
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    //CameraScreen(LocalContext.current, NavController(LocalContext.current))
}