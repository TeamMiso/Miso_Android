package com.example.miso.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.shop.response.ShopListDetailResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.model.shop.response.ShopListResponseModel
import com.example.domain.usecase.purchase.BuyItemUseCase
import com.example.domain.usecase.shop.GetShopListDetailUseCase
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
    private val getShopListUseCase: GetShopListUseCase,
    private val getShopListDetailUseCase: GetShopListDetailUseCase,
    private val buyItemUseCase: BuyItemUseCase
): ViewModel() {

    private val _getShopListResponse = MutableStateFlow<Event<ShopListResponseModel>>(Event.Loading)
    val getShopListResponse = _getShopListResponse.asStateFlow()

    private val _getShopListDetailResponse = MutableStateFlow<Event<ShopListDetailResponseModel>>(Event.Loading)
    val getShopListDetailResponse = _getShopListDetailResponse.asStateFlow()

    private val _buyItemResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val buyItemResponse = _buyItemResponse.asStateFlow()

    var shopList = mutableStateListOf<ShopListModel>()
        private set
    var point = mutableStateOf<Int>(0)
    var id = mutableStateOf<Long?>(null)
        private set
    var price = mutableStateOf<Int?>(null)
        private set
    var amount = mutableStateOf<Int?>(null)
        private set
    var name = mutableStateOf<String?>(null)
        private set
    var content = mutableStateOf<String?>(null)
        private set
    var imageUrl = mutableStateOf<String?>(null)
        private set
    fun getShopList() = viewModelScope.launch {
        getShopListUseCase()
            .onSuccess {
                it.catch {remoteError ->
                    Log.d("shopList-vm",remoteError.toString())
                    _getShopListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("shopList-vm",response.toString())
                    _getShopListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                Log.d("shopList-vm","fail")
                _getShopListResponse.value = it.errorHandling()
            }
    }
    fun getShopDetail(id: Long) = viewModelScope.launch {
        getShopListDetailUseCase(id = id)
            .onSuccess {
                it.catch {remoteError ->
                    Log.d("shopDetail-vm",remoteError.toString())
                    _getShopListDetailResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("shopDetail-vm",response.toString())
                    _getShopListDetailResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                Log.d("shopDetail-vm","fail")
                _getShopListDetailResponse.value = it.errorHandling()
            }
    }
    fun buyItem(id: Long) = viewModelScope.launch {
        buyItemUseCase(id = id)
            .onSuccess {
                it.catch { remoteError ->
                    Log.d("buyItem-vm",remoteError.toString())
                    _buyItemResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("buyItem-vm",response.toString())
                    _buyItemResponse.value = Event.Success()
                }
            }.onFailure {
                Log.d("buyItem-vm","fail")
                _buyItemResponse.value = it.errorHandling()
            }
    }
    fun repurchaseItem() {
        _buyItemResponse.value = Event.Loading
    }
    fun changeDetailList() {
        _getShopListDetailResponse.value = Event.Loading
    }
    fun addShopList(list: List<ShopListModel>) {
        shopList.clear()
        shopList.addAll(list)
    }
    fun addShopDetailList(list: ShopListDetailResponseModel){
        id.value = list.id
        price.value = list.price
        amount.value = list.amount
        name.value = list.name
        content.value = list.content
        imageUrl.value = list.imageUrl
    }
}