package com.example.miso.ui.result.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.ui.util.isUrl

@Composable
fun ResultRecycleMarkText(url: String) {
    MisoTheme { colors, typography ->
        when {
            isUrl(url) -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "분류배출 표시: ",
                        color = colors.BLACK,
                        style = typography.content4,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Image(
                        painter = rememberAsyncImagePainter(model = url),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }

            else -> {
                Text(
                    text = "분류배출 표시: $url",
                    color = colors.BLACK,
                    style = typography.content4,
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}