package com.example.smsrly.data.remote.dto.realestate.searchrealestate

data class SearchDto(
    val title:String,
    val price: Any? = null,
    val area: Any? = null,
    val floor_number: Any? = null,
    val bathroom_number: Any? = null,
    val room_number: Any? = null,
    val is_sale: Boolean = false
)