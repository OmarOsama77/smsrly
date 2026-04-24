package com.example.smsrly.data.repository

import android.util.Log
import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.remote.datasource.firebasedatasource.IFirebaseDataSource
import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.domain.models.Conversation
import com.example.smsrly.domain.models.Message
import com.example.smsrly.domain.repository.IFirebaseRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepo @Inject constructor(
    private val firebaseDataSource: IFirebaseDataSource) : IFirebaseRepo {
    override suspend fun uploadUserId(id:Int) {
         firebaseDataSource.uploadUserId(id)
    }

    override suspend fun sendAMessage(
        senderId: Int,
        receiverId: Int,
        text: String
    ) {
        firebaseDataSource.sendAMessage(senderId,receiverId,text)
    }

    override fun readAConversation(
        senderId: Int,
        receiverId: Int
    ) :Flow<List<Message>>{

         return firebaseDataSource.readAConversation(senderId,receiverId).map {
             it.map {
                 it.toDomain()
             }
         }
    }

    override  fun getConversations(userId: Int): Flow<Map<String, Conversation>>{
         return firebaseDataSource.getConversations(userId).map {it->
            it.mapValues {
                it.value.toDomain()
            }
         }
    }


}