package com.example.domain.model.recyclables.response

data class ResultResponseModel(
    val id: Long,
    val title: String,
    val subTitle: String,
    val content: String,
    val imageUrl: String,
    val recyclablesType: String,
    val recycleMark: String
)
