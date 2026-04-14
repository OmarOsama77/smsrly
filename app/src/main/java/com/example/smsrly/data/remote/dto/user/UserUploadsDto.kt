package com.example.smsrly.data.remote.dto.user

import android.net.Uri

data class UserUploadsDto(
    val floor_number : Int,
    val bathroom_number: Int,
    val room_number:Int,
    val is_sale:Boolean,
    val images:List<String> = emptyList(),
    val id :Int,
    val title:String,
    val description:String,
    val area:Double,
    val price:Double,
    val latitude: Double,
    val longitude:Double,
    val city:String,
    val country:String,
    val requests:List<String> = emptyList()
)