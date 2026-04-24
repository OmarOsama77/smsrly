package com.example.smsrly.data.mapper

import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.data.remote.dto.firebasedtos.MessageDto
import com.example.smsrly.domain.models.Conversation
import com.example.smsrly.domain.models.Message

fun MessageDto.toDomain(): Message{
    return Message(
        senderId =senderId!!,
        text = text!!
    )
}
fun ConversationDto.toDomain(): Conversation{
    return Conversation(
        conversationId = this.conversationId,
        lastMessage = this.lastMessage,
        lastMessageTimeStamp= this.lastMessageTimestamp,
        receiverInfo = null
    )
}