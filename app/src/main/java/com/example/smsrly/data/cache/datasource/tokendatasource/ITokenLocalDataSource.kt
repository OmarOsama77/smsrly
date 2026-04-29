package com.example.smsrly.data.cache.datasource.tokendatasource

interface ITokenLocalDataSource{
    fun saveTokens(accessToken: String, refreshToken: String)

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

    fun saveAccessToken(newToken:String)
    fun resetTokens(): Boolean
}