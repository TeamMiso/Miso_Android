package com.example.miso.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.domain.usecase.auth.AuthSignUpUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authSignUpUseCase: AuthSignUpUseCase
): ViewModel() {

    private val _authSignUpResponse = MutableLiveData<Event<Unit>>()
    val authSignUpResponse: LiveData<Event<Unit>> = _authSignUpResponse

    fun authSignUp(body: AuthSignUpRequestModel) = viewModelScope.launch {
        authSignUpUseCase(
            body = body
        ).onSuccess {
            it.catch { remoteError ->
                _authSignUpResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _authSignUpResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _authSignUpResponse.value = it.errorHandling()
        }
    }
}