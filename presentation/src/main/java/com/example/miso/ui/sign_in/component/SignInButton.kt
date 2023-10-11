package com.example.miso.ui.sign_in.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SignInButton(onClick: () -> Unit) {
    MisoTheme { colors, typography ->
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colors.M1),
                onClick = { onClick() }
            ) {
                Text(
                    text = "로그인",
                    color = colors.WHITE,
                    style = typography.content1,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignInButtonPreView() {
    SignInButton(onClick = {})
}