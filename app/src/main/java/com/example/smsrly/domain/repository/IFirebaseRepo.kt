package com.example.smsrly.domain.repository

interface IFirebaseRepo{
    suspend fun uploadUserId(id:Int)

    suspend fun sendAMessage(senderId:Int,receiverId:Int,text:String)
}