package com.example.smsrly.domain.usecase.chattingusecases

import com.example.smsrly.domain.repository.IFirebaseRepo
import javax.inject.Inject

class SendAMessageUseCase @Inject constructor(
    private val firebaseRepo: IFirebaseRepo
) {
    suspend fun sendAMessage(senderId:Int,receiverId:Int,text:String){
        firebaseRepo.sendAMessage(senderId,receiverId,text)
    }
}