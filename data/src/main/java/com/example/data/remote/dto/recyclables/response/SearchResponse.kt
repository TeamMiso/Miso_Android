package com.example.data.remote.dto.recyclables.response

import com.example.domain.model.recyclables.response.SearchResponseModel

data class SearchResponse(
    val title: String,
    val imageUrl: String,
    val recyclablesType: String
)

fun SearchResponse.toSearchModel() =
    SearchResponseModel(
        title = this.title,
        imageUrl = this.imageUrl,
        recyclablesType = this.recyclablesType
    )