package com.example.domain.model.purchase.response

data class PurchaseListResponseModel(
    val purchaseList: List<PurchaseListModel>
)

data class PurchaseListModel(
    val id: Long,
    val price: Int,
    val name: String,
    val createdDate: String
)