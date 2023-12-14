package com.example.miso.ui.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.miso.R

@Composable
fun MisoLogoIcon(
    modifier: Modifier = Modifier.size(70.dp)
) {
    Image(
        painter = painterResource(id = R.drawable.ic_miso_logo),
        contentDescription = "Miso Logo Icon",
        modifier = modifier
    )
}

@Composable
fun MisoLogoWhiteIcon(
    modifier: Modifier = Modifier.size(70.dp),
) {
    Image(
        painter = painterResource(id = R.drawable.ic_miso_logo_white),
        contentDescription = "Miso Logo White Icon",
        modifier = modifier
    )
}

@Composable
fun GalleryIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_gallery_bottom_sheet),
        contentDescription = "Gallery Icon Button",
        modifier = modifier
    )
}

@Composable
fun CameraIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_camera_bottm_sheet),
        contentDescription = "Camera Icon",
        modifier = modifier
    )
}