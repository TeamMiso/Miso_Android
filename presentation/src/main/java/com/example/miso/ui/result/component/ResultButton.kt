package com.example.miso.ui.result.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.button.MisoButton
import com.example.miso.ui.inquiry.component.InquiryButton
import com.example.miso.ui.theme.MisoTheme

@Composable
fun ResultButton(onClick: () -> Unit) {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.padding(start = 17.dp, end = 17.dp)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(colors.M1),
                onClick = { onClick() }
            ) {
                Text(
                    text = "+10 POINT",
                    color = colors.M3,
                    style = typography.title3,
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultButtonPreview() {
    ResultButton(onClick = {})
}