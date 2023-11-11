package com.example.domain.model.inquiry.response

data class InquiryListDetailResponseModel(
    val id: Long,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val inquiryStatus: String
)
