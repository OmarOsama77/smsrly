package com.example.smsrly.data.remote.datasource.firebasedatasource

import android.util.Log
import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.data.remote.dto.firebasedtos.MessageDto
import com.example.smsrly.domain.models.Conversation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
        val messageModel = MessageDto(senderId, text, System.currentTimeMillis())
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
            "lastMessage" to lastMessage,
            "lastMessageTimeStamp" to System.currentTimeMillis()
        )
        db.reference.child("users").child(senderId.toString()).child(receiverId.toString())
            .setValue(data)
    }

    override fun readAConversation(senderId: Int, receiverId: Int): Flow<List<MessageDto>> {
        return callbackFlow {

            val conversationId =
                "${Math.min(senderId, receiverId)}_${Math.max(senderId, receiverId)}"
            val ref = db.reference.child("conversations").child(conversationId)
            val listener = ref.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val newList = mutableListOf<MessageDto>()
                        for (i in snapshot.children) {
                            val item = i.getValue(MessageDto()::class.java)
                            newList.add(item!!)
                        }
                        trySend(newList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
            ref.addValueEventListener(listener)
            awaitClose {
                ref.removeEventListener(listener)
            }
        }
    }

    override fun getConversations(userId: Int): Flow<Map<String, ConversationDto>> {
        return callbackFlow {
            val ref = db.reference.child("users").child(userId.toString())
            val listener = ref.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val newMap = mutableMapOf<String, ConversationDto>()
                        for (i in snapshot.children) {
                            if (i.key.toString() != "created at") {
                                val conversation = i.getValue(ConversationDto()::class.java)
                                newMap.put(i.key.toString(), conversation!!)
                            }
                        }
                        trySend(newMap)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
            ref.addValueEventListener(listener)
            awaitClose {
                ref.removeEventListener(listener)
            }
        }
    }

}