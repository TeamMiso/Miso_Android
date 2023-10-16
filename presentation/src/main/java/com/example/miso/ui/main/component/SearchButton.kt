package com.example.miso.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R

@Composable
fun SearchButton(
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Logout Icon",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(width = 24.dp, height = 24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick }
    )
}

@Composable
@Preview(showBackground = true)
fun SearchButtonPreView() {
    SearchButton({})
}