package com.example.miso.ui.list.component.list

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@Composable
fun InquiryStickyHeader() {
    MisoTheme { colors, typography ->
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(colors.GRAY3)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 14.dp, end = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "문의글",
                        color = colors.GRAY6,
                        style = typography.content3,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Spacer(modifier = Modifier.width(55.dp))
                    Text(
                        text = "제목",
                        color = colors.GRAY6,
                        style = typography.content3,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "상태",
                        color = colors.GRAY6,
                        style = typography.content3,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryStickyHeaderPreView() {
    InquiryStickyHeader()
}