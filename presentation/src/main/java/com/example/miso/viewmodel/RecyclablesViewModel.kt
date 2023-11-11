package com.example.miso.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.recyclables.response.SearchResponseModel
import com.example.domain.usecase.recyclables.DeleteSearchHistoryUseCase
import com.example.domain.usecase.recyclables.GetSearchHistoryUseCase
import com.example.domain.usecase.recyclables.SaveSearchHistoryUseCase
import com.example.domain.usecase.recyclables.SearchUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclablesViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val saveSearchHistoryUseCase: SaveSearchHistoryUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase
) : ViewModel() {
    private val _searchResponse = MutableStateFlow<Event<SearchResponseModel>>(Event.Loading)
    val searchResponse = _searchResponse.asStateFlow()

    private val _saveSearchHistoryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val saveSearchHistoryResponse = _saveSearchHistoryResponse.asStateFlow()

    private val _getSearchHistoryResponse = MutableStateFlow<Event<List<String>>>(Event.Loading)
    val getSearchHistoryResponse = _getSearchHistoryResponse.asStateFlow()

    private val _deleteSearchHistoryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deleteSearchHistoryResponse = _deleteSearchHistoryResponse.asStateFlow()

    var imageUrl = mutableStateOf("")
        private set
    var title = mutableStateOf("")
        private set
    var recyclablesType = mutableStateOf("")
        private set

    fun search(search: String) = viewModelScope.launch {
        searchUseCase(search = search)
            .onSuccess {
                it.catch { remoteError ->
                    _searchResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _searchResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _searchResponse.value = it.errorHandling()
            }
    }

    fun addSearch(data: SearchResponseModel) {
        imageUrl.value = data.imageUrl
        title.value = data.title
        recyclablesType.value = data.recyclablesType
    }

    fun saveSearchHistory(searchHistory: String) = viewModelScope.launch {
        saveSearchHistoryUseCase(
            searchHistory = searchHistory
        ).onSuccess {
            _saveSearchHistoryResponse.value = Event.Success()
        }.onFailure {
            _saveSearchHistoryResponse.value = it.errorHandling()
        }
    }

    fun getSearchHistory() = viewModelScope.launch {
        getSearchHistoryUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getSearchHistoryResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getSearchHistoryResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getSearchHistoryResponse.value = it.errorHandling()
            }
    }

    fun deleteHistory(index: Int) = viewModelScope.launch {
        deleteSearchHistoryUseCase(index = index)
            .onSuccess {
                _deleteSearchHistoryResponse.value = Event.Success()
            }.onFailure {
                _deleteSearchHistoryResponse.value = it.errorHandling()
            }
    }
}