package com.example.miso.ui.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MisoButton(
    text: String,
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colors.M1),
                onClick = { onClick() }
            ) {
                Text(
                    text = text,
                    color = colors.WHITE,
                    style = typography.content1,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}