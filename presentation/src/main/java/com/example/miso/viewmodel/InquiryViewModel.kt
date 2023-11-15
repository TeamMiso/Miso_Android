package com.example.miso.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.inquiry.response.InquiryListDetailResponseModel
import com.example.domain.model.inquiry.response.InquiryListModel
import com.example.domain.model.inquiry.response.InquiryListResponseModel
import com.example.domain.usecase.inquiry.AdoptUseCase
import com.example.domain.usecase.inquiry.GetInquiryListAllUseCase
import com.example.domain.usecase.inquiry.GetInquiryListDetailUseCase
import com.example.domain.usecase.inquiry.GetInquiryListUseCase
import com.example.domain.usecase.inquiry.RequestInquiryUseCase
import com.example.domain.usecase.inquiry.UnadoptUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class InquiryViewModel @Inject constructor(
    private val requestInquiryUseCase: RequestInquiryUseCase,
    private val getInquiryListUseCase: GetInquiryListUseCase,
    private val getInquiryListAllUseCase: GetInquiryListAllUseCase,
    private val getInquiryListDetailUseCase: GetInquiryListDetailUseCase,
    private val adoptUseCase: AdoptUseCase,
    private val unadoptUseCase: UnadoptUseCase
) : ViewModel() {

    private val _requestInquiryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val requestInquiryResponse = _requestInquiryResponse.asStateFlow()

    private val _getInquiryListResponse = MutableStateFlow<Event<InquiryListResponseModel>>(Event.Loading)
    val getInquiryListResponse = _getInquiryListResponse.asStateFlow()

    private val _getInquiryListAllResponse = MutableStateFlow<Event<InquiryListResponseModel>>(Event.Loading)
    val getInquiryListAllResponse = _getInquiryListAllResponse.asStateFlow()

    private val _getInquiryListDetailResponse = MutableStateFlow<Event<InquiryListDetailResponseModel>>(Event.Loading)
    val getInquiryListDetailResponse = _getInquiryListDetailResponse.asStateFlow()

    private val _adoptResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val adoptResponse = _adoptResponse.asStateFlow()

    private val _unadoptResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val unadoptResponse = _unadoptResponse.asStateFlow()

    var inquiryList = mutableStateListOf<InquiryListModel>()
        private set

    var inquiryListAll = mutableStateListOf<InquiryListModel>()
        private set

    var id = mutableStateOf(0L)
        private set
    var title = mutableStateOf("")
        private set
    var content = mutableStateOf("")
        private set
    var imageUrl = mutableStateOf<String?>(null)
        private set
    var inquiryStatus = mutableStateOf("")
        private set

    fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody) =
        viewModelScope.launch {
            requestInquiryUseCase(
                filePart = filePart,
                inquiryPart = inquiryPart
            ).onSuccess {
                it.catch { remoteError ->
                    _requestInquiryResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _requestInquiryResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _requestInquiryResponse.value = it.errorHandling()
            }
        }

    fun changeRequestInquiry() {
        _requestInquiryResponse.value = Event.Loading
    }

    fun getInquiryList() = viewModelScope.launch {
        getInquiryListUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getInquiryListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getInquiryListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getInquiryListResponse.value = it.errorHandling()
            }
    }

    fun getInquiryAllList() = viewModelScope.launch {
        getInquiryListAllUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getInquiryListAllResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getInquiryListAllResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getInquiryListAllResponse.value = it.errorHandling()
            }
    }

    fun getInquiryListDetail(id: Long) = viewModelScope.launch {
        getInquiryListDetailUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    _getInquiryListDetailResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getInquiryListDetailResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getInquiryListDetailResponse.value = it.errorHandling()
            }
    }

    fun adopt(id: Long) = viewModelScope.launch {
        adoptUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    _adoptResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _adoptResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _adoptResponse.value = it.errorHandling()
            }
    }

    fun unadopt(id: Long) = viewModelScope.launch {
        unadoptUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    _unadoptResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _unadoptResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _unadoptResponse.value = it.errorHandling()
            }
    }

    fun addInquiryList(list: List<InquiryListModel>) {
        inquiryList.addAll(list)
        Log.d("Add", inquiryList.toList().toString())
    }

    fun addInquiryListAll(list: List<InquiryListModel>) {
        inquiryListAll.addAll(list)
    }

    fun addInquiryListDetail(data: InquiryListDetailResponseModel) {
        id.value = data.id
        title.value = data.title
        content.value = data.content
        imageUrl.value = data.imageUrl
        inquiryStatus.value = data.inquiryStatus
    }

    fun clearInquiryList() {
        inquiryList.clear()
    }

    fun clearInquiryListAll() {
        inquiryListAll.clear()
    }
}