package com.example.miso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.auth.request.AuthLogInRequestModel
import com.example.domain.model.auth.response.AuthLogInResponseModel
import com.example.domain.model.user.response.UserInfoResponseModel
import com.example.domain.usecase.user.GetRoleUseCase
import com.example.domain.usecase.user.GetUserInfoUseCase
import com.example.domain.usecase.user.GivePointUseCase
import com.example.domain.usecase.user.SaveUserInfoUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val getRoleUseCase: GetRoleUseCase,
    private val givePointUseCase: GivePointUseCase
) : ViewModel() {

    private val _getUserInfoResponse = MutableStateFlow<Event<UserInfoResponseModel>>(Event.Loading)
    val getUserInfoResponse = _getUserInfoResponse.asStateFlow()

    private val _saveUserInfoResponse = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val saveUserInfoResponse = _saveUserInfoResponse.asStateFlow()

    private val _getRoleResponse = MutableStateFlow<Event<String>>(Event.Loading)
    val getRoleResponse = _getRoleResponse.asStateFlow()

    private val _givePointResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val givePointResponse = _givePointResponse.asStateFlow()

    fun getUserInfo() = viewModelScope.launch {
        getUserInfoUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getUserInfoResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getUserInfoResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getUserInfoResponse.value = it.errorHandling()
            }
    }

    fun saveUserInfo(userInfo: UserInfoResponseModel) = viewModelScope.launch {
        saveUserInfoUseCase(
            userInfo = userInfo
        ).onSuccess {
            _saveUserInfoResponse.value = Event.Success()
        }.onFailure {
            _saveUserInfoResponse.value = it.errorHandling()
        }
    }

    fun getRole() = viewModelScope.launch {
        getRoleUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _getRoleResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getRoleResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _getRoleResponse.value = error.errorHandling()
            }
    }

    fun givePoint() = viewModelScope.launch {
        givePointUseCase()
            .onSuccess {
                it.catch { remoteError ->
                    _givePointResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _givePointResponse.value = Event.Success(data = response)
                }
            }.onFailure { error ->
                _givePointResponse.value = error.errorHandling()
            }
    }
}