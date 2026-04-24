package com.example.smsrly.domain.usecase.chattingusecases

import android.util.Log
import com.example.smsrly.domain.models.Message
import com.example.smsrly.domain.repository.IFirebaseRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadConversationUseCase @Inject constructor(
    private val firebaseRepo: IFirebaseRepo
) {
    fun readAConversation(senderId:Int,receiverId:Int):Flow<List<Message>>{

        return firebaseRepo.readAConversation(senderId,receiverId)
    }

}