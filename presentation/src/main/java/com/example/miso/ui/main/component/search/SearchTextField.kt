package com.example.miso.ui.main.component.search

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.main.component.main.SearchButton
import com.example.miso.ui.theme.MisoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.job

@Composable
fun SearchTextField(
    setChangeText: String,
    onFocusChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    text = setChangeText

    LaunchedEffect(key1 = text) {
        delay(300L)
        onSearchTextChange(text)
    }

    MisoTheme { colors, typography ->
        Row {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .onFocusEvent { state ->
                        onFocusChange(state.isFocused)
                    },
                textStyle = typography.content1,
                singleLine = true,
                decorationBox = { innerTextField ->
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .drawWithContent {
                                drawContent()
                                drawLine(
                                    color = colors.BLACK,
                                    start = Offset(
                                        x = 0f,
                                        y = size.height - 1.dp.toPx(),
                                    ),
                                    end = Offset(
                                        x = size.width,
                                        y = size.height - 1.dp.toPx(),
                                    ),
                                    strokeWidth = 1.dp.toPx(),
                                )
                            },
                    ) {
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Spacer(modifier = Modifier.width(16.dp))
            SearchButton {

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchTextFieldPreView() {
    SearchTextField("", {}, {}, {})
}