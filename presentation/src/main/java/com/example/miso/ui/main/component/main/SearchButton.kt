package com.example.miso.ui.main.component.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
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
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.size(width = 24.dp, height = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchButtonPreView() {
    SearchButton({})
}