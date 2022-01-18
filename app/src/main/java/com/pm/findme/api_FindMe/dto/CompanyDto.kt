package com.pm.findme.api_FindMe.dto

import com.pm.findme.api_FindMe.models.Company

data class CompanyDto(
    val status : String,
    val message : String,
    val company : Company
)

