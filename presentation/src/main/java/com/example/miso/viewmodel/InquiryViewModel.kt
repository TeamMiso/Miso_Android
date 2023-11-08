package com.example.miso.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.inquiry.request.InquiryRequestModel
import com.example.domain.model.inquiry.response.InquiryListModel
import com.example.domain.model.inquiry.response.InquiryListResponseModel
import com.example.domain.usecase.inquiry.GetInquiryListAllUseCase
import com.example.domain.usecase.inquiry.GetInquiryListUseCase
import com.example.domain.usecase.inquiry.RequestInquiryUseCase
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
    private val getInquiryListAllUseCase: GetInquiryListAllUseCase
) : ViewModel() {

    private val _requestInquiryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val requestInquiryResponse = _requestInquiryResponse.asStateFlow()

    private val _getInquiryListResponse = MutableStateFlow<Event<InquiryListResponseModel>>(Event.Loading)
    val getInquiryListResponse = _getInquiryListResponse.asStateFlow()

    private val _getInquiryListAllResponse = MutableStateFlow<Event<InquiryListResponseModel>>(Event.Loading)
    val getInquiryListAllResponse = _getInquiryListAllResponse.asStateFlow()

    var inquiryList = mutableStateListOf<InquiryListModel>()
        private set

    var inquiryListAll = mutableStateListOf<InquiryListModel>()
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

    fun addInquiryList(list: List<InquiryListModel>) {
        inquiryList.addAll(list)
    }

    fun addInquiryListAll(list: List<InquiryListModel>) {
        inquiryListAll.addAll(list)
    }

    fun clearInquiryList() {
        inquiryList.clear()
    }

    fun clearInquiryListAll() {
        inquiryListAll.clear()
    }
}