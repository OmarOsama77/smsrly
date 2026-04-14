package com.example.smsrly.data.remote.dto.realestate.getuseruploads

import com.example.smsrly.data.remote.dto.realestate.getrealestates.UserInfoDto

data class UserUploadedRealEstateDto(
    val floor_number:Int,
    val bathroom_number:Int,
    val room_number:Int,
    val is_sale:Boolean,
    val images:List<String>,
    val id:Int,
    val title: String,
    val description:String,
    val area : Double,
    val price: Double,
    val latitude: Double,
    val longitude: Double,
    val city:String,
    val country: String,
    val requests:List<UserInfoDto>
)