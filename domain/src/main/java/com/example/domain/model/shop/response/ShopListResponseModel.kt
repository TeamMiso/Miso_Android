package com.example.domain.model.shop.response

data class ShopListResponseModel(
    val itemList: List<ShopListModel>
)
data class ShopListModel(
    val id: Long,
    val price: Int,
    val name: String,
    val imageUrl: String
)
