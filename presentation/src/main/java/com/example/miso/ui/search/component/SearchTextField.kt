package com.example.miso.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.main.component.main.SearchButton
import com.example.miso.ui.theme.MisoTheme

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
) {
    MisoTheme { colors, typography ->
        BasicTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            textStyle = typography.content1,
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier.drawWithContent {
                        drawContent()
                        drawLine(
                            color = Color(0xFFEFEFEF),
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        innerTextField()
                        SearchButton {

                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchTextFieldPreView() {
    SearchTextField("", {})
}