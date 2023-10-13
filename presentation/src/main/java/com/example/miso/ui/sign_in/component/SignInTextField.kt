package com.example.miso.ui.sign_in.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.textfield.MisoTextField

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