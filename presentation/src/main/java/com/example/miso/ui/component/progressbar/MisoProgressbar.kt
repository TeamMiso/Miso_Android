package com.example.miso.ui.component.progressbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MisoProgressbar(modifier: Modifier) {
    MisoTheme { colors, _ ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.Center),
                color = colors.GRAY4,
            )
        }
    }
}