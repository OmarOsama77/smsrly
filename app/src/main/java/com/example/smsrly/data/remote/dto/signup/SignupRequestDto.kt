package com.example.smsrly.data.remote.dto.signup

data class SignupRequestDto(
    val firstname:String,
    val lastname:String,
    val email:String,
    val password:String,
    val phone_number:String,
    val latitude: Double?,
    val longitude: Double?
)