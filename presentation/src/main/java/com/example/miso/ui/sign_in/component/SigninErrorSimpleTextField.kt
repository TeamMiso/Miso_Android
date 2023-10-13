package com.example.miso.ui.sign_in.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.miso.ui.component.textfield.MisoErrorSimpleTextField

@Composable
fun SignInErrorSimpleTextField(
    isError: Boolean = false,
    placeHolder: String = "비밀번호를 입력해주세요",
    readOnly: Boolean = false,
    errorText: String = "이메일 또는 비밀번호가 일치하지 않습니다.",
    setChangeText: String,
    singleLine: Boolean = true,
    onFocusChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    MisoErrorSimpleTextField(
        isError = isError,
        placeHolder = placeHolder,
        readOnly = readOnly,
        errorText = errorText,
        setChangeText = setChangeText,
        singleLine = singleLine,
        onFocusChange = onFocusChange,
        onValueChange = onValueChange
    )
}

@Composable
@Preview(showBackground = true)
fun SignInErrorSimpleTextFieldPreView() {
    SignInErrorSimpleTextField(
        isError = false,
        placeHolder = "비밀번호를 입력해주세요",
        readOnly = false,
        setChangeText = "1234",
        onValueChange = { }
    )
}