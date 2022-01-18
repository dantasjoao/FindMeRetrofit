package com.pm.findme.api_FindMe.dto

import com.pm.findme.api_FindMe.models.User

data class UserDto (
    val status : String,
    val message : String,
    val user : List<User>,
    val token : String
)
