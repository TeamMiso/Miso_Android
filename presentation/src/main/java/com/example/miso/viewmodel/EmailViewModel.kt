package com.example.miso.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.email.request.EmailRequestModel
import com.example.domain.usecase.email.EmailUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val emailUseCase: EmailUseCase
): ViewModel() {

    private val _emailResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val emailResponse = _emailResponse.asStateFlow()

    fun email(body: EmailRequestModel) = viewModelScope.launch {
        emailUseCase(
            body = body
        ).onSuccess {
            it.catch { remoteError ->
                _emailResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _emailResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _emailResponse.value = it.errorHandling()
        }
    }

    fun changeEmail() {
        _emailResponse.value = Event.Loading
    }
}