package com.pm.findme.api_FindMe.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Field

@Parcelize
data class Company (
        val id: Int,
        val nameCompany: String,
        val emailCompany: String,
        val phoneCompany : Int,
        val addressCompany: String,
        val categoryCompany: String,
        val users_id : Int,
        val created_at : String,
        val user_name: String
) : Parcelable
