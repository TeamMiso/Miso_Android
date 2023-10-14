package com.example.miso.ui.email.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun EmailTextField(
    text: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    MisoTheme { colors, typography ->
        Column {
            BasicTextField(
                value = text.take(4),
                onValueChange = {
                    if (it.length <= 4) {
                        onValueChange(it)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        text.forEachIndexed { index, char ->
                            EmailTextFieldCharContainer(
                                text = char,
                                isError = isError,
                                isFocused = index == text.lastIndex,
                            )
                        }
                        repeat(4 - text.length) {
                            EmailTextFieldCharContainer(
                                text = ' ',
                                isError = isError,
                                isFocused = false,
                            )
                        }
                    }
                },
            )
            if (isError) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "인증번호가 일치하지 않습니다.",
                    color = colors.ERROR,
                    style = typography.content3
                )
            }
        }
    }
}

@Composable
private fun EmailTextFieldCharContainer(
    text: Char,
    isError: Boolean,
    isFocused: Boolean,
) {
    val shape = remember { RoundedCornerShape(10.dp) }

    MisoTheme { colors, typography ->
        Box(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(10.dp))
                .size(
                    width = 50.dp,
                    height = 50.dp,
                )
                .background(
                    color = colors.GRAY3,
                    shape = shape,
                )
                .border(
                    width = 1.dp,
                    color = if (!isError) colors.TRANSPARENT else colors.ERROR,
                    shape = shape
                )
                .run {
                    if (isFocused) {
                        border(
                            width = 1.dp,
                            color = colors.GRAY4,
                            shape = shape,
                        )
                    } else {
                        this
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text.toString(),
                color = colors.GRAY5,
                style = typography.number,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmailTextFieldPreView() {
    EmailTextField("", false ,{})
}