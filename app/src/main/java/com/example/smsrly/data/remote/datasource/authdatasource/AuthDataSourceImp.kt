package com.example.smsrly.data.remote.datasource.authdatasource

import android.util.Log
import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.remote.apiservice.ApiService
import com.example.smsrly.data.remote.dto.login.LoginFailedDto
import com.example.smsrly.data.remote.dto.login.LoginRequestDto
import com.example.smsrly.data.remote.dto.login.LoginSuccessDto
import com.example.smsrly.data.remote.dto.otp.OtpFailedDto
import com.example.smsrly.data.remote.dto.otp.OtpRequestDto
import com.example.smsrly.data.remote.dto.signup.SignupFailedDto
import com.example.smsrly.data.remote.dto.signup.SignupRequestDto
import com.example.smsrly.data.remote.dto.signup.SignupSuccessDto
import com.example.smsrly.domain.models.AuthTokens
import com.example.smsrly.domain.models.User
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi
) : IAuthDataSource {
    override suspend fun sendOtp(
        firstName: String,
        lastName: String,
        email: String
    ): Result<String> {
        try {

            val requestModel = OtpRequestDto(firstName, lastName, email)
            val response = apiService.sendOtp(requestModel)

            if (response.isSuccessful) {
                val body = response.body()
                return Result.success(body?.message ?: "Account created successfully")

            } else {
                val errorJson = response.errorBody()?.string()
                errorJson?.let {
                    val adapter = moshi.adapter(OtpFailedDto::class.java)
                    val errorResponse = adapter.fromJson(it)
                    return Result.failure(Exception(errorResponse?.error?.message))
                }
                return Result.failure(Exception("Failed to do request"))
            }
        } catch (e: Exception) {
            Log.d("error ya omar ",e.toString())
            e.cause?.let { Log.d("error ya omar cause", it.stackTraceToString()) }
            Log.d("error ya omar trace", e.stackTraceToString())
            return Result.failure(Exception("Network issue"))
        }

    }



    override suspend fun signup(
        user: User,
        password: String,
        otpCode: String
    ): Result<SignupSuccessDto> {
        try {
            val requestModel =
                SignupRequestDto(
                    user.firstName,
                    user.lastName,
                    user.email,
                    password,
                    "+2${user.phoneNumber}",
                    user.latitude,
                    user.longitude
                )

            val response = apiService.signup(otpCode, requestModel)

            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            } else {
                val errorJson = response.errorBody()?.string()
                errorJson?.let {
                    val adapter = moshi.adapter(SignupFailedDto::class.java)
                    val errorResponse = adapter.fromJson(it)
                    return Result.failure(Exception(errorResponse?.error?.message))
                }
                return Result.failure(Exception("unknown error"))
            }
        } catch (e: Exception) {
            return Result.failure(Exception("Network issue"))
        }

    }
    override suspend fun login(
        email: String,
        pass: String
    ): Result<LoginSuccessDto> {
        try {
            val requestModel = LoginRequestDto(
                email, pass
            )
            val response = apiService.login(requestModel)

            if (response.isSuccessful) {

                return Result.success(response.body()!!)

            } else {
                val errorJson = response.errorBody()?.string()
                errorJson?.let {
                    val adapter = moshi.adapter(LoginFailedDto::class.java)
                    val errorResponse = adapter.fromJson(it)
                    return Result.failure(Exception(errorResponse?.error?.message))
                }
                return Result.failure(Exception("Login Failed"))
            }

        } catch (e: Exception) {
            return Result.failure(Exception("Network issue"))
        }
    }
    override suspend fun uploadUserImage(imageFile: File, accessToken: String): Result<String> {
        try {

            val requestFile = imageFile.asRequestBody("image/*".toMediaType())

            val imagePart = MultipartBody.Part.createFormData(
                name = "image",
                filename = imageFile.name,
                body = requestFile
            )
            val response = apiService.uploadUserImage(
                "Bearer ${accessToken}",
                imagePart
            )
            Log.d("the issue ",response.toString())
            if (response.isSuccessful) {
                Log.d("the issue s",response.body().toString())
                return Result.success(response.body()!!.message)
            } else {
                Log.d("the issue d",response.errorBody().toString())
                return Result.failure(Exception("Failed to upload picture,try again later"))
            }
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to upload picture,try again later"))
        }

    }
}