package com.example.miso.ui.main.component.search

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

@Composable
fun SearchList(items: Int) {
    MisoTheme { colors, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { index ->
                SearchListItem(text = "text") {

                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(color = colors.GRAY4)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchListPreView() {
    SearchList(3)
}