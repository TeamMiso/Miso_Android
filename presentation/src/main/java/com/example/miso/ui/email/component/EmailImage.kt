package com.example.miso.ui.email.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun EmailImage(
) {
    MisoTheme { colors, typography ->
        Image(
            painter = painterResource(id = R.drawable.ic_miso_email),
            contentDescription = "Email Icon",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(width = 74.dp, height = 47.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EmailImagePreView() {
    EmailImage()
}