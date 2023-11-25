package com.example.miso.ui.purchase.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.purchase.response.PurchaseListModel
import com.example.miso.ui.component.button.MisoBackBlackButton
import com.example.miso.ui.list.component.list.ListTitleText
import com.example.miso.ui.purchase.component.PurchaseList
import com.example.miso.viewmodel.PurchaseViewModel
import com.example.miso.viewmodel.util.Event


@Composable
fun PurchaseScreen(
    viewModel: PurchaseViewModel,
    onBackClick: () -> Unit,
){
    val progressState = remember { mutableStateOf(false) }

    LaunchedEffect("GetPurchaseList") {
        viewModel.getPurchaseList()
        getPurchaseList(
            viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list ->
                viewModel.addPurchaseList(list)
            }
        )
        Log.d("LaunchedEffect:List", "작동22")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MisoBackBlackButton {
                onBackClick()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ListTitleText()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(144.dp))
            PurchaseList(
                purchaseList = viewModel.purchaseList,
                progressState = progressState.value,
            )
        }
    }
}
suspend fun getPurchaseList(
    viewModel: PurchaseViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (purchaseList: List<PurchaseListModel>) -> Unit,
) {
    viewModel.getPurchaseListResponse.collect { response ->
        Log.d("purchase", "작동")
        when (response) {
            is Event.Success -> {
                Log.d("purchase", "성공${response.data!!.itemList}")
                progressState(false)
                onSuccess(response.data!!.itemList)
            }

            is Event.Loading -> {
                Log.d("purchase", "작동중")
                progressState(true)
            }

            else -> {
                Log.d("purchase", "실패")
                progressState(false)
            }
        }
    }
}