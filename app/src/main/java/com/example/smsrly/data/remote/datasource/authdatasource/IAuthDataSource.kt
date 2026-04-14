package com.example.smsrly.data.remote.datasource.authdatasource

import com.example.smsrly.data.remote.dto.login.LoginSuccessDto
import com.example.smsrly.data.remote.dto.signup.SignupSuccessDto
import com.example.smsrly.domain.models.User
import java.io.File

interface IAuthDataSource {

    suspend fun sendOtp(firstName:String,lastName:String,email:String): Result<String>
    suspend fun signup(user: User,password:String,otpCode:String): Result<SignupSuccessDto>

     suspend fun login(email:String,pass:String):Result<LoginSuccessDto>
    suspend fun uploadUserImage(imageFile: File,accessToken:String):Result<String>
}