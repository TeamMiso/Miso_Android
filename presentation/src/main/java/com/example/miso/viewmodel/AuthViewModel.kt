package com.example.miso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.domain.model.auth.request.AuthSignUpRequestModel
import com.example.domain.model.auth.response.AuthLogInResponseModel
import com.example.domain.usecase.auth.AuthLogInUseCase
import com.example.domain.usecase.auth.AuthSignUpUseCase
import com.example.domain.usecase.auth.DeleteTokenUseCase
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.auth.SaveTokenUseCase
import com.example.domain.usecase.recyclables.DeleteAllSearchHistoryUseCase
import com.example.domain.usecase.user.DeleteUserInfoUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authSignUpUseCase: AuthSignUpUseCase,
    private val authLogInUseCase: AuthLogInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val deleteUserInfoUseCase: DeleteUserInfoUseCase,
    private val deleteAllSearchHistoryUseCase: DeleteAllSearchHistoryUseCase
) : ViewModel() {

    private val _authSignUpResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val authSignUpResponse = _authSignUpResponse.asStateFlow()

    private val _authLogInResponse = MutableStateFlow<Event<AuthLogInResponseModel>>(Event.Loading)
    val authLogInResponse = _authLogInResponse.asStateFlow()

    private val _saveTokenResponse = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val saveTokenResponse = _saveTokenResponse.asStateFlow()

    private val _getAccessTokenResponse = MutableStateFlow<Event<String>>(Event.Loading)
    val getAccessTokenResponse = _getAccessTokenResponse.asStateFlow()

    private val _logoutResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val logoutResponse = _logoutResponse.asStateFlow()

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

    fun authLogIn(body: AuthLogInRequestModel) = viewModelScope.launch {
        authLogInUseCase(
            body = body
        ).onSuccess {
            it.catch { remoteError ->
                _authLogInResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _authLogInResponse.value = Event.Success(data = response)
            }
        }.onFailure {
            _authLogInResponse.value = it.errorHandling()
        }
    }

    fun changeLogIn() {
        _authLogInResponse.value = Event.Loading
    }

    fun saveToken(token: AuthLogInResponseModel) = viewModelScope.launch {
        saveTokenUseCase(
            token = token
        ).onSuccess {
            _saveTokenResponse.value = Event.Success()
        }.onFailure {
            _saveTokenResponse.value = it.errorHandling()
        }
    }

    fun getAccessToken() = viewModelScope.launch {
        getAccessTokenUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getAccessTokenResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getAccessTokenResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _getAccessTokenResponse.value = error.errorHandling()
            }
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _logoutResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _logoutResponse.value = Event.Success(data = response)
                    deleteTokenUseCase()
                    deleteUserInfoUseCase()
                    deleteAllSearchHistoryUseCase()
                }
            }.onFailure {
                _logoutResponse.value = it.errorHandling()
            }
    }
}