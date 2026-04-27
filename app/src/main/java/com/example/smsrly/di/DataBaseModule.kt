package com.example.smsrly.di

import com.example.smsrly.data.local.db.SmsrlyDataBase
import com.example.smsrly.data.local.db.SmsrlyDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): SmsrlyDataBase {
        return SmsrlyDataBase.getInstance(context)
    }
    @Provides
    fun provideDao(db: SmsrlyDataBase): SmsrlyDataBaseDao{
        return db.smsrlyDataBaseDao
    }
}