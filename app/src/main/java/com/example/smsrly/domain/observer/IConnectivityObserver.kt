package com.example.smsrly.domain.observer

import kotlinx.coroutines.flow.Flow

interface IConnectivityObserver {
    fun observe(): Flow<Status>
    fun isOnline():Boolean
    enum class Status{
        Available,UnAvailable
    }
}