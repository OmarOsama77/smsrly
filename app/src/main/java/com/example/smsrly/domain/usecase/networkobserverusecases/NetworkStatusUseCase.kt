package com.example.smsrly.domain.usecase.networkobserverusecases

import com.example.smsrly.domain.observer.IConnectivityObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkObserverUseCase @Inject constructor(
    private val networkObserver: IConnectivityObserver
) {
    fun invoke(): Flow<IConnectivityObserver.Status>{
        return networkObserver.observe()
    }
}