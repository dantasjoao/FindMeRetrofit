package com.pm.findme.api_FindMe.requests

import com.pm.findme.api_FindMe.dto.CompanyDto
import com.pm.findme.api_FindMe.models.Company
import retrofit2.Call
import retrofit2.http.*

interface CompanyAPI {
    @GET("company/read")
    fun getReports(@Header("Authorization") token: String): Call<List<Company>>
    @FormUrlEncoded
    @POST("company/create")
    fun createReport(
        @Header("Authorization") token: String,
        @Field("users_id") users_id: String?,
        @Field("nameCompany") name: String,
        @Field("emailCompany") email: String,
        @Field("phoneCompany") phone: Int,
        @Field("addressCompany") address: String,
        @Field("categoryCompany") category: String
    ): Call<CompanyDto>

    @FormUrlEncoded
    @POST("company/update")
    fun updateReport(
        @Header("Authorization") token: String,
        @Field("id") id: Int,
        @Field("nameCompany") name: String,
        @Field("emailCompany") email: String,
        @Field("phoneCompany") phone: Int,
        @Field("addressCompany") address: String,
        @Field("categoryCompany") category: String
    ): Call<CompanyDto>

    @FormUrlEncoded
    @POST("company/delete")
    fun deleteReport(@Header("Authorization") token: String, @Field("id") id: Int): Call<CompanyDto>
}