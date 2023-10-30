package com.example.miso.ui.list.component.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun UnselectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Button(
            modifier = modifier.height(48.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(colors.GRAY4),
            onClick = { onClick() }
        ) {
            Text(
                text = "비채택",
                color = colors.M3,
                style = typography.content1,
                fontWeight = FontWeight.ExtraLight
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UnselectButtonPreView() {
    UnselectButton() {}
}