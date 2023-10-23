package com.example.miso.ui.sign_up.component.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun PasswordTextField(
    isState: String = "Normal",
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
        var lineColor = colors.TRANSPARENT
        var errorText = ""
        when (isState) {
            "Normal" -> {
                lineColor = colors.TRANSPARENT
                errorText = ""
            }

            "Error" -> {
                lineColor = colors.ERROR
                errorText = "비밀번호가 일치하지 않습니다."
            }

            "Success" -> {
                lineColor = colors.BLUE1
                errorText = "비밀번호가 일치합니다."
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(start = 38.dp, end = 38.dp)) {
                Row {
                    Text(
                        text = "Password",
                        color = colors.BLACK2,
                        style = typography.content2,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = errorText,
                        color = lineColor,
                        style = typography.content4,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = colors.WHITE2)
                        .border(
                            width = 1.dp,
                            color = if (!isFocused) lineColor else colors.M1,
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier.background(color = colors.WHITE2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!isFocused) {
                            Spacer(modifier = Modifier.width(12.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "Password Icon",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.size(16.dp, 19.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Divider(
                                modifier = Modifier
                                    .width(0.5.dp)
                                    .height(30.dp)
                                    .background(color = colors.GRAY2)
                            )
                        }
                        TextField(
                            value = text,
                            onValueChange = {
                                text = it
                                onValueChange(it)
                            },
                            singleLine = singleLine,
                            placeholder = {
                                Text(
                                    text = placeHolder,
                                    style = typography.content2,
                                    color = colors.GRAY5,
                                    fontWeight = FontWeight.ExtraLight
                                )
                            },
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .height(54.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .onFocusEvent { state ->
                                    isFocused = state.isFocused
                                    onFocusChange(state.isFocused)
                                }
                                .background(color = colors.WHITE2)
                                .fillMaxWidth(),
                            textStyle = typography.content1,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = colors.WHITE2,
                                placeholderColor = colors.GRAY5,
                                cursorColor = colors.BLACK,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            readOnly = readOnly,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordTextFieldTextPreView() {
    PasswordTextField(
        isState = "Normal",
        placeHolder = "Password",
        readOnly = false,
        setChangeText = "",
        onValueChange = { }
    )
}