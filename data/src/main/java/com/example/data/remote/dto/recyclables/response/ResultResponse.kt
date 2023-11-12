package com.example.data.remote.dto.recyclables.response

import com.example.domain.model.recyclables.response.ResultResponseModel

data class ResultResponse(
    val id: Long,
    val title: String,
    val subTitle: String,
    val content: String,
    val imageUrl: String,
    val recyclablesType: String,
    val recycleMark: String
)

fun ResultResponse.toResultModel() =
    ResultResponseModel(
        id = this.id,
        title = this.title,
        subTitle = this.subTitle,
        content = this.content,
        imageUrl = this.imageUrl,
        recyclablesType = this.recyclablesType,
        recycleMark = this.recycleMark
    )
