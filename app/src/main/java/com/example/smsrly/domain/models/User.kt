package com.example.smsrly.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName:String,
    val lastName: String,
    val email:String,
    val latitude:Double,
    val longitude:Double,
    val userId:Int?=null,
    val phoneNumber:String,
    var imageUrl: String? = null
)

