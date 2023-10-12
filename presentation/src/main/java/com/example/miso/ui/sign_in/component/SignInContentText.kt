package com.example.miso.ui.sign_in.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SignInContentText(onSignUpClick: () -> Unit) {
    MisoTheme { colors, typography ->
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "계정이 없으신가요?",
                color = colors.GRAY2,
                style = typography.content2,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onSignUpClick()
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "회원가입",
                color = colors.BLACK,
                style = typography.content2,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onSignUpClick()
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignInContentTextPreView() {
    SignInContentText(onSignUpClick = {})
}
