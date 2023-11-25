package com.example.miso.ui.list.component.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.inquiry.response.InquiryListModel
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.ui.util.toDateString

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InquiryList(
    inquiryList: List<InquiryListModel>,
    progressState: Boolean,
    onClick: (id: Long) -> Unit
) {
    MisoTheme { colors, _ ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            stickyHeader {
                InquiryStickyHeader()
            }

            items(inquiryList.size) { index ->
                InquiryListItem(
                    inquiryList[index].inquiryDate.toDateString(),
                    inquiryList[index].title,
                    when (inquiryList[index].inquiryStatus) {
                        "WAIT" -> "-"
                        "ADOPT" -> "채택"
                        "UNADOPT" -> "비채택"
                        else -> ""
                    }
                ) {
                    onClick(inquiryList[index].id)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(color = colors.GRAY3)
                )
            }
            if (progressState) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .align(Alignment.Center),
                            color = colors.GRAY3,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchListPreView() {
    // InquiryList(, false, NavController(LocalContext.current))
}