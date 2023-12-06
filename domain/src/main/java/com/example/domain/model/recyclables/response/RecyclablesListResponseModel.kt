package com.example.domain.model.recyclables.response

data class RecyclablesListResponseModel(
    val recyclablesList: List<RecyclablesListModel>
)

data class RecyclablesListModel(
    val title: String,
    val imageUrl: String,
    val recyclablesType: String
)