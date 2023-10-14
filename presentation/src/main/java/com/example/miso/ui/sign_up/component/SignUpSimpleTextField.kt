package com.example.miso.ui.sign_up.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.textfield.MisoSimpleTextField

@Composable
fun SignUpSimpleTextField(
    isError: Boolean = false,
    placeHolder: String = "이메일을 입력해주세요",
    readOnly: Boolean = false,
    setChangeText: String,
    singleLine: Boolean = true,
    onFocusChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    MisoSimpleTextField(
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
fun SignUpSimpleTextFieldPreView() {
    SignUpSimpleTextField(
        isError = false,
        placeHolder = "이메일을 입력해주세요",
        readOnly = false,
        setChangeText = "s22020@gsm.hs.kr",
        onValueChange = { }
    )
}