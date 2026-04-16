package com.example.smsrly.data.remote.datasource.firebasedatasource

import com.example.smsrly.data.remote.dto.firebasedtos.MessageDto
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataSource @Inject constructor(
    private val db: FirebaseDatabase
) : IFirebaseDataSource {

    override suspend fun uploadUserId(id: Int) {
        db.reference.child("users")
            .child(id.toString())
            .child("created at")
            .setValue(System.currentTimeMillis())
    }

    override suspend fun sendAMessage(
        senderId: Int,
        receiverId: Int,
        text: String
    ) {
        val conversationId = "${Math.min(senderId, receiverId)}_${Math.max(senderId, receiverId)}"
        val messageModel = MessageDto(senderId, text)
        db.reference.child("conversations").child(conversationId).push().setValue(messageModel)
        updateUser(senderId, receiverId, text, conversationId)
        updateUser(receiverId, senderId, text, conversationId)
    }

    override suspend fun updateUser(
        senderId: Int,
        receiverId: Int,
        lastMessage: String,
        conversationId: String
    ) {
        val data = mapOf(
            "conversationId" to conversationId,
            "lastMessage" to lastMessage
        )
        db.reference.child("users").child(senderId.toString()).child(receiverId.toString())
            .setValue(data)
    }


}