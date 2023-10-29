package com.example.miso.ui.inquiry.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun InquiryTextField(
    isManager: Boolean,
    modifier: Modifier = Modifier,
    setChangeText: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    text = setChangeText
    MisoTheme { colors, typography ->
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            singleLine = singleLine,
            textStyle = typography.content2,
            modifier = modifier
                .focusRequester(focusRequester),
            decorationBox = { innerTextField ->
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (isManager) colors.TRANSPARENT else colors.GRAY3,
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        innerTextField()
                    }
                }
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryTextFieldPreView() {
    InquiryTextField(
        isManager = false,
        modifier = Modifier.height(40.dp),
        setChangeText = "유색 페트병",
        onValueChange = { }
    )
}