package com.example.smsrly.di

import com.example.smsrly.data.networkobserver.NetworkConnectivityObserver
import com.example.smsrly.domain.observer.IConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {
    @Binds
    @Singleton
    abstract fun provideConnectivity(connectivityObserver: NetworkConnectivityObserver): IConnectivityObserver
}