package com.example.miso.ui.sign_in.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.textfield.MisoTextField
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SignInTextField(
    isError: Boolean = false,
    placeHolder: String = "이메일을 입력해주세요",
    readOnly: Boolean = false,
    setChangeText: String,
    singleLine: Boolean = true,
    onFocusChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    MisoTextField(
        isError = isError,
        placeHolder = placeHolder,
        readOnly = readOnly,
        setChangeText = setChangeText,
        singleLine = singleLine,
        onFocusChange = onFocusChange,
        onValueChange = onValueChange
    )
}

@Composable
@Preview(showBackground = true)
fun SignInTextFieldPreView() {
    SignInTextField(
        isError = false,
        placeHolder = "이메일을 입력해주세요",
        readOnly = false,
        setChangeText = "s22020@gsm.hs.kr",
        onValueChange = { }
    )
}