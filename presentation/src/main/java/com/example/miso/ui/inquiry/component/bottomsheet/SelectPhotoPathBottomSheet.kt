package com.example.miso.ui.inquiry.component.bottomsheet

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.component.bottomsheet.BaseBottomSheetComponent
import com.example.miso.ui.component.icon.CameraIcon
import com.example.miso.ui.component.icon.GalleryIcon
import com.example.miso.ui.inquiry.component.InquiryButton
import com.example.miso.ui.theme.MisoTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectPhotoPathBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    onProfileImageUriChanged: () -> Unit,
    selectedImageUri: (uri: Uri) -> Unit
) {
    val isCamera = remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                selectedImageUri(uri)
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when {
            isGranted && isCamera.value -> {

            }
            isGranted && !isCamera.value -> {
                galleryLauncher.launch("image/*")
            }
        }
    }
    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    SelectPhotoPathBottomSheetComponent(
        bottomSheetState = bottomSheetState,
        onGalleryLaunchButtonClick = {
            isCamera.value = false
            permissionLauncher.launch(permission)
        },
        onCameraLaunchButtonClick = {
            isCamera.value = true
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    )
}