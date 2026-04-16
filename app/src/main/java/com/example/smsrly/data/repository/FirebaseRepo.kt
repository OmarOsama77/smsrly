package com.example.smsrly.data.repository

import com.example.smsrly.data.remote.datasource.firebasedatasource.IFirebaseDataSource
import com.example.smsrly.domain.repository.IFirebaseRepo
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


}