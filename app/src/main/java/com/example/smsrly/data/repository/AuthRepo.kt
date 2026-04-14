package com.example.smsrly.data.repository

import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.remote.datasource.authdatasource.IAuthDataSource
import com.example.smsrly.domain.models.AuthTokens
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.repository.IAuthRepo
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepo @Inject constructor(
    private val authDataSource: IAuthDataSource
) :
    IAuthRepo {
    override suspend fun sendOtp(
        firstName: String,
        lastName: String,
        email: String
    ): Result<String> {
        return authDataSource.sendOtp(firstName, lastName, email)
    }

    override suspend fun signup(
        user: User,
        password: String,
        otpCode: String
    ): Result<AuthTokens> {
        return authDataSource.signup(user, password, otpCode)
            .map { it.toDomain() }

    }

    override suspend fun login(
        email: String,
        pass: String
    ): Result<AuthTokens> {
        return authDataSource.login(email, pass)
            .map { it.toDomain() }
    }

    override suspend fun uploadUserImage(
        imageFile: File,
        accessToken: String
    ): Result<String> {
        return authDataSource.uploadUserImage(imageFile, accessToken)
    }


}