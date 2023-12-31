package com.example.data.remote.api

import com.example.data.remote.dto.inquiry.response.InquiryListDetailResponse
import com.example.data.remote.dto.inquiry.response.InquiryListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface InquiryAPI {

    @Multipart
    @POST("inquiry")
    suspend fun requestInquiry(
        @Part filePart: MultipartBody.Part?,
        @Part("inquiry") inquiryPart: RequestBody
    )

    @GET("inquiry")
    suspend fun getInquiryList(): InquiryListResponse

    @GET("inquiry/all")
    suspend fun getInquiryListAll(): InquiryListResponse

    @GET("inquiry/{id}")
    suspend fun getInquiryListDetail(
        @Path("id") id: Long
    ): InquiryListDetailResponse

    @PATCH("inquiry/adopt/{id}")
    suspend fun adopt(
        @Path("id") id: Long
    )

    @PATCH("inquiry/unadopt/{id}")
    suspend fun unadopt(
        @Path("id") id: Long
    )
}