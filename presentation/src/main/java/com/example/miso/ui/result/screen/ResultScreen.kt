package com.example.miso.ui.result.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.result.component.ResultButton
import com.example.miso.ui.result.component.ResultContentText
import com.example.miso.ui.result.component.ResultImage
import com.example.miso.ui.result.component.ResultRecyclablesTypeText
import com.example.miso.ui.result.component.ResultRecycleMarkText
import com.example.miso.ui.result.component.ResultSubTitleText
import com.example.miso.ui.result.component.ResultTitleText
import com.example.miso.viewmodel.RecyclablesViewModel

@Composable
fun ResultScreen(
    context: Context,
    viewModel: RecyclablesViewModel,
    onResultClick: () -> Unit,
) {
    val result = viewModel.result.value

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(55.dp))
            Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                ResultImage(url = result.imageUrl)
                Spacer(modifier = Modifier.height(24.dp))
                ResultTitleText(text = result.title)
                Spacer(modifier = Modifier.height(12.dp))
                ResultSubTitleText(text = result.subTitle)
                Spacer(modifier = Modifier.height(12.dp))
                ResultRecyclablesTypeText(text = result.recyclablesType)
                Spacer(modifier = Modifier.height(12.dp))
                ResultRecycleMarkText(url = result.recycleMark)
                Spacer(modifier = Modifier.height(30.dp))
                ResultContentText(markdown = result.content.trimIndent())
            }
            Spacer(modifier = Modifier.height(24.dp))
            ResultButton(onClick = { onResultClick() }, isAiResult = viewModel.isAiResult.value)
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    // ResultScreen(LocalContext.current)
}