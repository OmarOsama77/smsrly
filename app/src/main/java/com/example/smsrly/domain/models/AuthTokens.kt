package com.example.smsrly.domain.models

data class AuthTokens(
    val id : Int,
    val accessToken:String,
    val refreshToken:String
)