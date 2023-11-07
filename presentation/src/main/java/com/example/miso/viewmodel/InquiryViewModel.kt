package com.example.miso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.inquiry.request.InquiryRequestModel
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
    private val requestInquiryUseCase: RequestInquiryUseCase
): ViewModel() {

    private val _requestInquiryResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val requestInquiryResponse = _requestInquiryResponse.asStateFlow()

    fun requestInquiry(filePart: MultipartBody.Part?, inquiryPart: RequestBody) = viewModelScope.launch {
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
}