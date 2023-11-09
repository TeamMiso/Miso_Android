package com.example.data.remote.dto.inquiry.response

import com.example.domain.model.inquiry.response.InquiryListDetailResponseModel

data class InquiryListDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val inquiryStatus: String
)

fun InquiryListDetailResponse.toInquiryModel() =
    InquiryListDetailResponseModel(
        id = this.id,
        title = this.title,
        content = this.content,
        imageUrl = this.imageUrl,
        inquiryStatus = this.inquiryStatus
    )
