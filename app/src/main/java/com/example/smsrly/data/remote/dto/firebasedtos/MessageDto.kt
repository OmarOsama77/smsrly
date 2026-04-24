package com.example.smsrly.data.remote.dto.firebasedtos



data class MessageDto(
    val senderId:Int?=null,
    val text : String?=null,
    val timestamp : Long = System.currentTimeMillis()
)