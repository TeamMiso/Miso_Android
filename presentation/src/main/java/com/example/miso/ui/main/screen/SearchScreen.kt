package com.example.miso.ui.main.screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.domain.model.inquiry.response.InquiryListModel
import com.example.domain.model.recyclables.response.SearchResponseModel
import com.example.miso.ui.component.button.MisoBackBlackButton
import com.example.miso.ui.list.screen.getInquiryListAll
import com.example.miso.ui.main.MainPage
import com.example.miso.ui.util.keyboardAsState
import com.example.miso.ui.main.component.search.SearchHistoryText
import com.example.miso.ui.main.component.search.SearchItem
import com.example.miso.ui.main.component.search.SearchHistoryList
import com.example.miso.ui.main.component.search.SearchTextField
import com.example.miso.viewmodel.InquiryViewModel
import com.example.miso.viewmodel.RecyclablesViewModel
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    context: Context,
    viewModel: RecyclablesViewModel,
    navController: NavController,
    onBackClick: () -> Unit
) {
    var isClick by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState()

    val focusManager = LocalFocusManager.current

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(isKeyboardOpen) {
        isClick = isKeyboardOpen
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var search by remember { mutableStateOf("") }

    LaunchedEffect("Start") {
        viewModel.getSearchHistory()
    }

    LaunchedEffect("Search") {
        search(
            viewModel = viewModel
        )
    }

    var searchHistory by remember { mutableStateOf(mutableListOf<String>()) }

    LaunchedEffect("SaveSearchHistory") {
        saveSearchHistory(
            viewModel = viewModel,
            navController = navController
        )
    }

    LaunchedEffect("GetSearchHistory") {
        getSearchHistory(
            viewModel = viewModel,
            onSuccess = {
                searchHistory = it.toMutableList()
            }
        )
    }

    LaunchedEffect("DeleteSearchHistory") {
        deleteSearchHistory(
            viewModel = viewModel
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MisoBackBlackButton { onBackClick() }
                Spacer(modifier = Modifier.width(20.dp))
                SearchTextField(
                    setChangeText = search,
                    onFocusChange = {
                        isClick = it
                    },
                    onValueChange = {
                        search = it
                    },
                    onSearchTextChange = { viewModel.search(it) },
                    modifier = Modifier.focusRequester(focusRequester)
                )
            }
            if (search.isNotBlank()) {
                Spacer(modifier = Modifier.fillMaxHeight(0.04f))
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    SearchItem(
                        search = search,
                        saveSearchHistory = { searchHistory ->
                            viewModel.saveSearchHistory(searchHistory)
                        },
                        imageUrl = viewModel.imageUrl.value,
                        title = viewModel.title.value,
                        recyclablesType = viewModel.recyclablesType.value,
                        onItemClick = { },
                    )
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.045f))
            Column(modifier = Modifier.padding(start = 16.dp)) {
                SearchHistoryText()
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.04f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp)
            ) {
                SearchHistoryList(
                    searchHistory = searchHistory,
                    onSearchHistoryClick = {
                        search = it
                    },
                    onRemoveClick = { index ->
                        viewModel.deleteHistory(index)
                    }
                )
            }
        }
    }
}

suspend fun search(
    viewModel: RecyclablesViewModel
) {
    viewModel.searchResponse.collect {
        if (it is Event.Success) {
            viewModel.addSearch(it.data!!)
        } else if (it is Event.NotFound) {
            viewModel.addSearch(
                SearchResponseModel(
                    title = "",
                    imageUrl = "",
                    recyclablesType = ""
                )
            )
        }
    }
}

suspend fun saveSearchHistory(
    viewModel: RecyclablesViewModel,
    navController: NavController
) {
    viewModel.saveSearchHistoryResponse.collect {
        if (it is Event.Success) {
            viewModel.getSearchHistory()
            if (viewModel.title.value.isNotBlank() &&
                viewModel.imageUrl.value.isNotBlank() &&
                viewModel.recyclablesType.value.isNotBlank()
            ) {
                navController.navigate(MainPage.Result.value)
            } else {
                navController.navigate(MainPage.Inquiry.value)
            }
        }
    }
}


suspend fun getSearchHistory(
    viewModel: RecyclablesViewModel,
    onSuccess: (searchHistory: List<String>) -> Unit,
) {
    viewModel.getSearchHistoryResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data ?: emptyList())
            }

            else -> {}
        }
    }
}

suspend fun deleteSearchHistory(
    viewModel: RecyclablesViewModel
) {
    viewModel.deleteSearchHistoryResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                viewModel.getSearchHistory()
            }

            else -> {}
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreView() {
    // SearchScreen(LocalContext.current, {})
}