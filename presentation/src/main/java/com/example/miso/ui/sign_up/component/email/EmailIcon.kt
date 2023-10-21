package com.example.miso.ui.sign_up.component.email

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.miso.R

@Composable
fun EmailIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_email_color),
        contentDescription = "Email Icon Color",
        modifier = Modifier.size(74.dp, 47.dp)
    )
}
