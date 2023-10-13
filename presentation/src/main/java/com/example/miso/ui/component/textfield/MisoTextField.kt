package com.example.miso.ui.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MisoTextField(
    isError: Boolean = false,
    placeHolder: String,
    readOnly: Boolean = false,
    setChangeText: String,
    singleLine: Boolean = true,
    onFocusChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    text = setChangeText
    MisoTheme { colors, typography ->
        var color = if (isFocused) colors.BLACK
        else if (setChangeText.isEmpty()) colors.GRAY1
        else if (!isError) colors.BLUE
        else colors.ERROR
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
                    .height(54.dp)
                    .border(
                        width = 1.dp,
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .onFocusEvent { state ->
                        isFocused = state.isFocused
                        onFocusChange(state.isFocused)
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
                readOnly = readOnly,
            )
        }
    }
}

@Composable
fun MisoErrorTextField(
    isError: Boolean = false,
    placeHolder: String = "비밀번호를 입력해주세요",
    readOnly: Boolean = false,
    errorText: String = "",
    setChangeText: String,
    singleLine: Boolean = true,
    onFocusChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    text = setChangeText

    MisoTheme { colors, typography ->
        var color = if (isFocused) colors.BLACK
        else if (setChangeText.isEmpty()) colors.GRAY1
        else if (!isError) colors.BLUE
        else colors.ERROR
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
                    .height(54.dp)
                    .border(
                        width = 1.dp,
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .onFocusEvent { state ->
                        isFocused = state.isFocused
                        onFocusChange(state.isFocused)
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
            if (setChangeText.isNotEmpty()) {
                Text(
                    text = errorText,
                    color = if (isError) colors.ERROR else colors.BLUE,
                    style = typography.content3
                )
            }

        }
    }
}