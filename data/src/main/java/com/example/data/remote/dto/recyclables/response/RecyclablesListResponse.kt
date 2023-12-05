package com.example.data.remote.dto.recyclables.response

import com.example.domain.model.recyclables.response.RecyclablesListModel
import com.example.domain.model.recyclables.response.RecyclablesListResponseModel

data class RecyclablesListResponse(
    val recyclablesList: List<RecyclablesList>
)

data class RecyclablesList(
    val title: String,
    val imageUrl: String,
    val recyclablesType: String
)

fun RecyclablesListResponse.toRecyclablesModel() =
    RecyclablesListResponseModel(
        recyclablesList = this.recyclablesList.map { it.toRecyclablesModel() }
    )

fun RecyclablesList.toRecyclablesModel() =
    RecyclablesListModel(
        title = this.title,
        imageUrl = this.imageUrl,
        recyclablesType = this.recyclablesType
    )
