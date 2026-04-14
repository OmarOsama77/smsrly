package com.example.smsrly.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptedSharedPref @Inject constructor( @ApplicationContext private val context: Context) {

    private val sharedPref = EncryptedSharedPreferences.create(
        "user_tokens",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun getAccessToken():String?{
        return  sharedPref.getString("access_token",null)
    }
    fun getRefreshToken(): String?{
        return  sharedPref.getString("refresh_token",null)
    }
    fun saveTokens(access: String, refresh: String) {
        sharedPref.edit().apply {
            putString("access_token", access)
            putString("refresh_token", refresh)
        }.apply()
    }
    fun saveAccessToken(newToken:String){
        sharedPref.edit().apply(){
            putString("access_token",newToken)
        }
    }

    fun clearTokens(): Boolean{
        return sharedPref.edit().clear().commit()
    }
}