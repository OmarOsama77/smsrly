package com.example.smsrly.data.remote.dto.user

data class GetUserResponse (
    val phone_number : String,
    var image_url : String?,
    val firstname : String,
    val lastname:String,
    val email : String,
    val user_id:Int,
    val latitude:Double,
    val longitude : Double,
)