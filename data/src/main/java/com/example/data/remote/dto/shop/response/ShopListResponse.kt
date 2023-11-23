package com.example.data.remote.dto.shop.response

import com.example.domain.model.shop.response.ShopListModel
import com.example.domain.model.shop.response.ShopListResponseModel

data class ShopListResponse(
    val itemList: List<ShopList>
)
data class ShopList(
    val id: Long,
    val price: Int,
    val name: String,
    val imageUrl: String
)
fun ShopListResponse.toShopModel() =
    ShopListResponseModel(
        itemList = this.itemList.map { it.toShopModel() }
    )
fun ShopList.toShopModel() =
    ShopListModel(
        id = this.id,
        price = this.price,
        name = this.name,
        imageUrl = this.imageUrl
    )