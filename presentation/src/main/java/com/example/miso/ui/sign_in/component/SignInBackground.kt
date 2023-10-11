package com.example.miso.ui.sign_in.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.R

@Composable
fun SignInBackground(
    isClick: Boolean = false
) {
    Crossfade(targetState = isClick, label = "Sign In Background") {
        when (it) {
            false -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_miso_background_big),
                    contentDescription = "Sign In Background Big",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                )
            }
            true -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_miso_background_small),
                    contentDescription = "Sign In Background Small",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignInBackgroundPreView() {
    SignInBackground()
}