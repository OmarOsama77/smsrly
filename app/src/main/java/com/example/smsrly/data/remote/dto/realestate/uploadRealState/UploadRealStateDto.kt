package com.example.smsrly.data.remote.dto.realestate.uploadRealState

data class UploadRealStateDto(
    val title: String,
    val description:String,
    val area:Double,
    val floor_number:Int,
    val bathroom_number:Int,
    val room_number:Int,
    val price:Double ,
    val latitude:Double,
    val longitude:Double,
    val city : String,
    val country:String,
    val is_sale : Boolean = false

)