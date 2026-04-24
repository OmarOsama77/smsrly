package com.example.smsrly.domain.models

data class Conversation(
    val conversationId:String,
    val lastMessage:String,
    val lastMessageTimeStamp:Long,
    var receiverInfo: UserInfo?
)