package com.example.miso.ui.sign_in.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SignInErrorTextField(
    isError: Boolean = false,
    placeHolder: String = "비밀번호를 입력해주세요",
    readOnly: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    errorText: String = "이메일 또는 비밀번호가 일치하지 않습니다.",
    setChangeText: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }
    text = setChangeText
    MisoTheme { colors, typography ->
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                singleLine = singleLine,
                placeholder = {
                    Text(
                        text = placeHolder,
                        style = typography.content1,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .border(
                        width = 1.dp,
                        color = if (!isError) colors.GRAY1 else colors.ERROR,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    }
                    .fillMaxWidth(),
                textStyle = typography.content1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = colors.WHITE,
                    placeholderColor = colors.GRAY1,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.BLACK
                ),
                readOnly = readOnly
            )
            if (isError) {
                Text(text = errorText, color = colors.ERROR, style = typography.content3)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignInErrorTextFieldPreView() {
    SignInErrorTextField(
        isError = false,
        placeHolder = "비밀번호를 입력해주세요",
        readOnly = false,
        setChangeText = "1234",
        onValueChange = {  }
    )
}