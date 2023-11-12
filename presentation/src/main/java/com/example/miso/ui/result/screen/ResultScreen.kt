package com.example.miso.ui.result.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.result.component.ResultButton
import com.example.miso.ui.result.component.ResultContentText
import com.example.miso.ui.result.component.ResultImage
import com.example.miso.ui.result.component.ResultRecyclablesTypeText
import com.example.miso.ui.result.component.ResultRecycleMarkText
import com.example.miso.ui.result.component.ResultSubTitleText
import com.example.miso.ui.result.component.ResultTitleText

@Composable
fun ResultScreen(
    context: Context
) {
    LazyColumn {
        item {
            Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                ResultImage(url = "")
                Spacer(modifier = Modifier.height(24.dp))
                ResultTitleText(text = "페트병")
                Spacer(modifier = Modifier.height(12.dp))
                ResultSubTitleText(text = "#생수병 #맥주병 #음료수병")
                Spacer(modifier = Modifier.height(12.dp))
                ResultRecyclablesTypeText(text = "")
                Spacer(modifier = Modifier.height(12.dp))
                ResultRecycleMarkText(url = "")
                Spacer(modifier = Modifier.height(30.dp))
                ResultContentText(text = "- 투명 페트병(생수병, 콜라, 사이다, 쥬스 등의 음료수병)과\n" +
                        "  유색 페트병(갈색 맥주병, 녹색 소주병, 녹색 사이다병 등)은 분리 배출 방법이 달라요.\n" +
                        " 투명 페트병 (생수, 콜라, 사이다, 쥬스 등)\n" +
                        "- 내용물을 비우고, 물로 내부를 깨끗이 세척해요.\n" +
                        "- 페트병 겉면에 붙어있는 상표 등의 비닐 라벨을 깨끗이 떼어내서 비닐류로\n" +
                        "   분리배출해요.\n" +
                        "- 페트병은 최대한 압축해서 뚜껑을 닫은 후 페트로 분리배출해요.")
            }
            Spacer(modifier = Modifier.height(24.dp))
            ResultButton {

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    ResultScreen(LocalContext.current)
}