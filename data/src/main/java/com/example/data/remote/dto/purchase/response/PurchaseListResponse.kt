package com.example.data.remote.dto.purchase.response

import com.example.data.remote.dto.shop.response.ShopList
import com.example.data.remote.dto.shop.response.ShopListResponse
import com.example.domain.model.purchase.response.PurchaseListModel
import com.example.domain.model.purchase.response.PurchaseListResponseModel
import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.model.shop.response.ShopListResponseModel

data class PurchaseListResponse(
    val itemList: List<PurchaseList>
)

data class PurchaseList(
    val id: Long,
    val price: Int,
    val name: String,
    val imageUrl: String
)
fun PurchaseListResponse.toPurchaseModel() =
    PurchaseListResponseModel(
        itemList = this.itemList.map { it.toPurchaseModel() }
    )
fun PurchaseList.toPurchaseModel() =
    PurchaseListModel(
        id = this.id,
        price = this.price,
        name = this.name,
        imageUrl = this.imageUrl
    )