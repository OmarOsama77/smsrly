package com.example.smsrly.domain.repository

import com.example.smsrly.domain.models.AuthTokens
import com.example.smsrly.domain.models.User
import java.io.File

interface IAuthRepo{
   suspend fun sendOtp(firstName:String,lastName:String,email:String): Result<String>
    suspend fun signup(user: User,password : String ,otpCode:String): Result<AuthTokens>

    suspend fun login(email:String,pass:String):Result<AuthTokens>
    suspend fun uploadUserImage(imageFile: File,accessToken:String):Result<String>
}