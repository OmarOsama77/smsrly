package com.example.smsrly.data.remote.dto.firebasedtos

data class ConversationDto(

    val conversationId:String="",
    val lastMessage:String="",
    val lastMessageTimestamp : Long = System.currentTimeMillis()
)