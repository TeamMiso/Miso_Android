package com.example.miso.ui.component.icon

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.miso.R

@Composable
fun MisoLogoWhiteIcon(
    modifier: Modifier = Modifier.size(114.dp),
    isClick: Boolean = false
) {
    when (isClick) {
        false -> {
            Image(
                painter = painterResource(id = R.drawable.ic_miso_logo_white),
                contentDescription = "Miso Logo White Icon",
                modifier = modifier
            )
        }

        true -> {}
    }
}