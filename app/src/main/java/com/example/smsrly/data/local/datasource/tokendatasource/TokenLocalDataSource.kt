package com.example.smsrly.data.local.datasource.tokendatasource

import com.example.smsrly.data.local.cache.EncryptedSharedPref
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenLocalDataSource @Inject constructor(
    private val sharedPref: EncryptedSharedPref
) : ITokenLocalDataSource {
    override fun saveTokens(accessToken: String, refreshToken: String) {
        sharedPref.saveTokens(accessToken, refreshToken)
    }

    override fun getAccessToken(): String? {
        return sharedPref.getAccessToken()
    }

    override fun getRefreshToken(): String? {
        return sharedPref.getRefreshToken()
    }

    override fun saveAccessToken(newToken: String) {
        sharedPref.saveAccessToken(newToken)
    }

    override fun resetTokens(): Boolean {
        return sharedPref.clearTokens()
    }

}