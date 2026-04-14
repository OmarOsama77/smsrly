package com.example.smsrly.di

import android.content.Context
import com.example.smsrly.utility.ImageUtils
import com.example.smsrly.utility.LocationHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideLocationHelper(
        @ApplicationContext context: Context
    ): LocationHelper = LocationHelper(context)

    @Provides
    @Singleton
    fun provideImageUtils(
        @ApplicationContext context: Context
    ): ImageUtils = ImageUtils(context)
}