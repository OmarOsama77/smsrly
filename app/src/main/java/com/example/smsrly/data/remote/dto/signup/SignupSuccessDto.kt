package com.example.smsrly.data.remote.dto.signup

data class SignupSuccessDto(
    val id : Int,
    val access_token:String,
    val refresh_token:String
)