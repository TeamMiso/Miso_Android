package com.example.miso.ui.inquiry.component

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme
import coil.compose.rememberImagePainter

@Composable
fun MoveGalleryButton(
    modifier: Modifier = Modifier,
    selectedImageUri: (uri: Uri) -> Unit
) {
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
        if (isGranted) {
            galleryLauncher.launch("image/*")
        }
    }
    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    MisoTheme { colors, typography ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(colors.GRAY3)
                .clickable { permissionLauncher.launch(permission) }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (selectedImageUri == null) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_gallery),
                        contentDescription = "Gallery Logo Icon",
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "이미지 업로드",
                        color = colors.GRAY6,
                        style = typography.content3,
                        fontWeight = FontWeight.Normal
                    )
                }
                else {
                    Image(
                        painter = rememberAsyncImagePainter(model = selectedImageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MoveGalleryButtonPreView() {
    MoveGalleryButton(selectedImageUri = {})
}