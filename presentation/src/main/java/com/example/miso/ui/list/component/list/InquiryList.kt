package com.example.miso.ui.list.component.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InquiryList(items: Int) {
    MisoTheme { colors, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            stickyHeader {
                InquiryStickyHeader()
            }

            items(items) { index ->
                InquiryListItem(
                    "23.01.23",
                    "유색 페트병이 등록되어있지 않습니다",
                    "-"
                ) {

                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(color = colors.GRAY3)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchListPreView() {
    InquiryList(4)
}