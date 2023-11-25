package com.example.miso.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.inquiry.response.InquiryListResponseModel
import com.example.domain.model.purchase.response.PurchaseListModel
import com.example.domain.model.purchase.response.PurchaseListResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.usecase.purchase.GetPurchaseListUseCase
import com.example.miso.viewmodel.util.Event
import com.example.miso.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val getPurchaseListUseCase: GetPurchaseListUseCase
): ViewModel() {
    private val _getPurchaseListResponse = MutableStateFlow<Event<PurchaseListResponseModel>>(Event.Loading)
    val getPurchaseListResponse = _getPurchaseListResponse.asStateFlow()

    var purchaseList = mutableStateListOf<PurchaseListModel>()
    fun getPurchaseList() = viewModelScope.launch {
        getPurchaseListUseCase()
            .onSuccess {
                it.catch {remoteError ->
                    Log.d("purchase-vm-remoteFail",remoteError.toString())
                    _getPurchaseListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    Log.d("purchase-vm",response.toString())
                    _getPurchaseListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                Log.d("purchase-vm-faill","fail")
                _getPurchaseListResponse.value = it.errorHandling()
            }
    }

    fun addPurchaseList(list: List<PurchaseListModel>) {
        purchaseList.clear()
        purchaseList.addAll(list)
    }
}