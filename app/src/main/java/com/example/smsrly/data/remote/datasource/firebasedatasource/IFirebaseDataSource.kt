package com.example.smsrly.data.remote.datasource.firebasedatasource

import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.data.remote.dto.firebasedtos.MessageDto
import com.example.smsrly.domain.models.Conversation
import kotlinx.coroutines.flow.Flow

interface IFirebaseDataSource{
    suspend fun uploadUserId(id:Int)

    suspend fun sendAMessage(senderId:Int,receiverId:Int,text:String)
    suspend fun updateUser(senderId: Int,receiverId: Int,lastMessage:String,conversationId:String)
    fun readAConversation(senderId: Int,receiverId: Int):Flow<List<MessageDto>>
     fun getConversations(userId:Int):Flow<Map<String, ConversationDto>>
}