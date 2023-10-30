package com.example.miso.ui.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MisoButton(
    text: String,
    modifier: Modifier = Modifier.padding(start = 38.dp, end = 38.dp),
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Row(modifier = modifier) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(colors.M1),
                onClick = { onClick() }
            ) {
                Text(
                    text = text,
                    color = colors.M3,
                    style = typography.title3,
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}

@Composable
fun MisoBackBlackButton(
    modifier: Modifier = Modifier.size(9.dp, 16.dp),
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = R.drawable.ic_back_black),
        contentDescription = "Back Black Icon",
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
    )
}
