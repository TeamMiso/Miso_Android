package com.example.miso.ui.camera.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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
fun CameraScreen(context: Context, navController: NavController,){
    ExampleCameraScreen()
    Box(){
        //CameraPreview()
        CameraBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.03f))
                CameraBackBtn(onClick = { /*TODO*/ }, context = context)
                Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                CameraFlashBtn(
                    onClick = {},
                    context = context
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.87f))
            CameraCaptureBtn(onClick = {}, context = context)
        }
    }
    //checkPermission(context = context,navController = navController)
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun checkPermission(context: Context,navController: NavController){

    /*val showPermissionDialog = remember { mutableStateOf(false) }

    val permissionList = listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val permissionState = rememberMultiplePermissionsState(permissions = permissionList)

    HandlePermissionActions(
        permissionState = permissionState,
        showPermissionDialog = showPermissionDialog
    )*/
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExampleCameraScreen() {

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(key1 = Unit) {
        if (!cameraPermissionState.status.isGranted && !cameraPermissionState.status.shouldShowRationale) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (cameraPermissionState.status.isGranted) {
        // Permission is granted, we can show the camera preview
        CameraPreview()
    } else {

    }
}

@Composable
@Preview(showBackground = true)
fun CameraBtnPreView() {
    CameraScreen(LocalContext.current, NavController(LocalContext.current))
}