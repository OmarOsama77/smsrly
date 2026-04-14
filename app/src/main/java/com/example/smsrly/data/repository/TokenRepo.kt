package com.example.smsrly.data.repository

import com.example.smsrly.data.local.datasource.tokendatasource.ITokenLocalDataSource
import com.example.smsrly.domain.repository.ITokenRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepo @Inject constructor(
    private val tokenLocalDataSource: ITokenLocalDataSource
): ITokenRepo{
    override fun saveTokens(accessToken: String, refreshToken: String) {
        tokenLocalDataSource.saveTokens(accessToken,refreshToken)
    }

    override fun getAccessToken(): String? {
        return tokenLocalDataSource.getAccessToken()
    }

    override fun getRefreshToken(): String? {
       return tokenLocalDataSource.getRefreshToken()
    }

    override fun saveAccessToken(newToken:String) {
        tokenLocalDataSource.saveAccessToken(newToken)
    }

    override fun resetTokens() :Boolean{
       return tokenLocalDataSource.resetTokens()
    }


}