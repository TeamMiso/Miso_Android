package com.example.miso.ui.component.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.miso.R

@Composable
fun MisoLogoWhiteIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_miso_logo_white),
        contentDescription = "Miso Logo White Icon",
        modifier = modifier
    )
}