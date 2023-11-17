package com.example.miso.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.inquiry.response.InquiryListModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.model.shop.response.ShopListResponseModel
import com.example.domain.usecase.shop.GetShopListUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase
): ViewModel() {

    private val _getShopListResponse = MutableStateFlow<Event<ShopListResponseModel>>(Event.Loading)
    val getShopListResponse = _getShopListResponse.asStateFlow()
    var shopList = mutableStateListOf<ShopListModel>()
        private set
    fun getShopList() = viewModelScope.launch {
        getShopListUseCase()
            .onSuccess {
                it.catch {remoteError ->
                    Log.d("testt-vm",remoteError.toString())
                    _getShopListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("testt-vm",response.toString())
                    _getShopListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                Log.d("testt-vm","fail")
                _getShopListResponse.value = it.errorHandling()
            }
    }
    fun addShopList(list: List<ShopListModel>) {
        shopList.clear()
        shopList.addAll(list)
        Log.d("testt-Add", shopList.toList().toString())
    }
}