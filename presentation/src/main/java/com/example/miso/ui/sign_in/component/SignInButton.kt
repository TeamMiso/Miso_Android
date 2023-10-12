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
import com.example.miso.ui.component.button.MisoButton
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SignInButton(onClick: () -> Unit) {
    MisoButton(text = "로그인") {
        onClick()
    }
}

@Composable
@Preview(showBackground = true)
fun SignInButtonPreView() {
    SignInButton(onClick = {})
}