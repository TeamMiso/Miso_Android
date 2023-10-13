package com.example.miso.ui.sign_up.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.component.icon.MisoLogoWhiteIcon

@Composable
fun SignUpBackground(
    isClick: Boolean = false
) {
    val targetOffset = if (!isClick) 0.dp else (-50).dp
    val offset by animateDpAsState(targetValue = targetOffset, label = "")
    Crossfade(
        targetState = isClick,
        label = "Sign Up Background",
        animationSpec = tween(300)
    ) {
        when (it) {
            false -> {
                Box(modifier = Modifier.offset(y = offset)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_miso_background_big),
                        contentDescription = "Sign Up Background Big",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                        MisoLogoWhiteIcon(isClick = isClick)
                        SignUpTitleText(isClick = isClick)
                    }
                }
            }

            true -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_miso_background_small),
                    contentDescription = "Sign Up Background Small",
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
fun SignUpBackgroundPreView() {
    SignUpBackground()
}