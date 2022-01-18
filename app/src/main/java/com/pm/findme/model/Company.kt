package com.pm.findme.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Company")
class Company(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val companyName: String,
    val companyEmail: String,
    val companyPhone: Int,
    val companyAddress: String,
    val companyCategory: String,
): Parcelable


