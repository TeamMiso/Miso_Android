package com.example.miso.ui.list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun InquiryListItem(
    data: String,
    title: String,
    state: String,
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data,
                        color = colors.GRAY5,
                        style = typography.content4,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = title,
                        color = colors.GRAY5,
                        style = typography.content4,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = state,
                        color = colors.GRAY5,
                        style = typography.content4,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryListItemPreView() {
    InquiryListItem("23.01.23", "유색 페트병이 등록되어있지 않습니다", "-" ,{})
}