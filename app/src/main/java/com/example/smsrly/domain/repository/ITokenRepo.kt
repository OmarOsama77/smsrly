package com.example.smsrly.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface ITokenRepo{
    fun saveTokens(accessToken:String,refreshToken:String)

    fun getAccessToken():String?

    fun getRefreshToken():String?

    fun saveAccessToken(newToken:String)
    fun resetTokens():Boolean
}