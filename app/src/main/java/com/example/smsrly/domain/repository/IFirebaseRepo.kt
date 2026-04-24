package com.example.smsrly.domain.repository

import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.domain.models.Conversation
import com.example.smsrly.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface IFirebaseRepo{
    suspend fun uploadUserId(id:Int)

    suspend fun sendAMessage(senderId:Int,receiverId:Int,text:String)
    fun readAConversation(senderId: Int,receiverId: Int):Flow<List<Message>>
     fun getConversations(userId:Int):Flow<Map<String, Conversation>>
}