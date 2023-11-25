package com.example.miso.ui.list.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.example.miso.ui.component.button.MisoBackBlackButton
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.inquiry.response.PurchaseListModel
import com.example.miso.ui.list.component.list.InquiryList
import com.example.miso.ui.list.component.list.ListTitleText
import com.example.miso.viewmodel.InquiryViewModel
import com.example.miso.viewmodel.util.Event

@Composable
fun ListScreen(
    context: Context,
    viewModel: InquiryViewModel,
    role: String,
    onBackClick: () -> Unit,
    onItemClick: (id: Long) -> Unit
) {
    val progressState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect("Start") {
        when (role) {
            "ROLE_USER" -> viewModel.getInquiryList()
            "ROLE_ADMIN" -> viewModel.getInquiryAllList()
            else -> {}
        }
        Log.d("LaunchedEffect:Start", "작동")
    }

    LaunchedEffect("GetInquiryList") {
        getInquiryList(
            viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list ->
                viewModel.clearInquiryList()
                viewModel.addInquiryList(list)
            }
        )
        Log.d("LaunchedEffect:List", "작동22")
    }

    LaunchedEffect("GetInquiryListAll") {
        getInquiryListAll(
            viewModel = viewModel,
            progressState = { progressState.value = it },
            onSuccess = { list ->
                viewModel.clearInquiryListAll()
                viewModel.addInquiryListAll(list)
            }
        )
        Log.d("LaunchedEffect:List", "작동33")
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
            InquiryList(
                inquiryList = if (role == "ROLE_USER") viewModel.inquiryList else viewModel.inquiryListAll,
                progressState = progressState.value,
            ) {
                onItemClick(it)
            }
        }
    }
}

suspend fun getInquiryList(
    viewModel: InquiryViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (inquiryList: List<PurchaseListModel>) -> Unit,
) {
    viewModel.getInquiryListResponse.collect { response ->
        Log.d("LaunchedEffect:GetInquiryList", "작동")
        when (response) {
            is Event.Success -> {
                progressState(false)
                onSuccess(response.data!!.inquiryList)
            }

            is Event.Loading -> {
                progressState(true)
            }

            else -> {
                progressState(false)
            }
        }
    }
}

suspend fun getInquiryListAll(
    viewModel: InquiryViewModel,
    progressState: (Boolean) -> Unit,
    onSuccess: (inquiryList: List<PurchaseListModel>) -> Unit
) {
    viewModel.getInquiryListAllResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                progressState(false)
                onSuccess(response.data!!.inquiryList)
            }

            is Event.Loading -> {
                progressState(true)
            }

            else -> {
                progressState(false)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ListScreenPreView() {
    // ListScreen(LocalContext.current, NavController(LocalContext.current), "ROLE_USER")
}