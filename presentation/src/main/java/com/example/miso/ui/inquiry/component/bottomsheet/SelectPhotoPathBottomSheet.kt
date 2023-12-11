package com.example.miso.ui.inquiry.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
    onGalleryLaunchButtonClick: () -> Unit,
    onCameraLaunchButtonClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    MisoTheme { colors, typography ->
        Column {
            Spacer(modifier = Modifier.size(24.dp))
            BaseBottomSheetComponent(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState,
                leftIcon = {
                    GalleryIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                },
                text = "앨범에서 가져오기",
                textStyle = typography.content1,
                textColor = Color(0xFF313235),
                fontWeight = FontWeight.Normal,
                onClick = onGalleryLaunchButtonClick
            )
            Spacer(modifier = Modifier.size(8.dp))
            BaseBottomSheetComponent(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState,
                leftIcon = {
                    CameraIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                },
                text = "카메라에서 촬영",
                textStyle = typography.content1,
                textColor = Color(0xFF313235),
                fontWeight = FontWeight.Normal,
                onClick = onCameraLaunchButtonClick
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}