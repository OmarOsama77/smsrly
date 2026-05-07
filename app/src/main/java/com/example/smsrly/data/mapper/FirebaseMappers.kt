package com.example.smsrly.data.mapper

import com.example.smsrly.data.local.db.entities.UserEntity
import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.data.remote.dto.firebasedtos.MessageDto
import com.example.smsrly.domain.models.Conversation
import com.example.smsrly.domain.models.Message
import com.example.smsrly.domain.models.User
import com.mapbox.maps.extension.style.image.image
import kotlin.String

fun MessageDto.toDomain(): Message {
    return Message(
        senderId = senderId!!,
        text = text!!
    )
}

fun ConversationDto.toDomain(): Conversation {
    return Conversation(
        conversationId = this.conversationId,
        lastMessage = this.lastMessage,
        lastMessageTimeStamp = this.lastMessageTimestamp,
        receiverInfo = null
    )
}

fun UserEntity.toDomain(): User {
    return User(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        latitude = this.latitude,
        longitude = this.longitude,
        userId = this.userId,
        phoneNumber = this.phoneNumber,
        imageUrl = null
    )
}