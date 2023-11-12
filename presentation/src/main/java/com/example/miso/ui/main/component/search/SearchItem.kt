package com.example.miso.ui.main.component.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.viewmodel.RecyclablesViewModel

@Composable
fun SearchItem(
    search: String,
    saveSearchHistory: (searchHistory: String) -> Unit,
    imageUrl: String,
    title: String,
    recyclablesType: String,
    onItemClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                saveSearchHistory(search)
                onItemClick()
            }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (imageUrl.isNotBlank() && title.isNotBlank() && recyclablesType.isNotBlank()) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .width(50.dp)
                                .fillMaxHeight()
                                .border(0.15.dp, color = colors.BLACK),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(
                                text = title,
                                color = colors.GRAY6,
                                style = typography.content3,
                                fontWeight = FontWeight.ExtraLight
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = recyclablesType,
                                color = colors.BLACK,
                                style = typography.content1,
                                fontWeight = FontWeight.ExtraLight
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .fillMaxHeight()
                                .border(0.15.dp, color = colors.BLACK)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_no_image),
                                contentDescription = "no image",
                                modifier = Modifier
                                    .size(35.dp)
                                    .align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(
                                text = "검색결과 없음",
                                color = colors.BLACK,
                                style = typography.content1,
                                fontWeight = FontWeight.ExtraLight
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "문의하기를 통해 직접 쓰레기를 등록해보세요!",
                                color = colors.GRAY6,
                                style = typography.content3,
                                fontWeight = FontWeight.ExtraLight
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp),
                    color = colors.GRAY6
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ISearchItemPreView() {
    // SearchItem("", "가구", "가구류", {}, {})
}