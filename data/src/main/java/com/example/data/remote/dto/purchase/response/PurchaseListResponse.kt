package com.example.data.remote.dto.purchase.response

import com.example.domain.model.purchase.response.PurchaseListModel
import com.example.domain.model.purchase.response.PurchaseListResponseModel

data class PurchaseListResponse(
    val itemList: List<PurchaseList>
)

data class PurchaseList(
    val id: Long,
    val price: Int,
    val name: String,
    val createdDate: String
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
        createdDate = this.createdDate
    )