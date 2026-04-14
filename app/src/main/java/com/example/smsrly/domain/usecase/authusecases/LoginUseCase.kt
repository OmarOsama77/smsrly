package com.example.smsrly.domain.usecase.authusecases

import com.example.smsrly.domain.models.AuthTokens
import com.example.smsrly.domain.repository.IAuthRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepo: IAuthRepo) {


    suspend fun login(email: String, pass: String): Result<AuthTokens> {
        val validation = validateData(email,pass)
        if(validation=="success"){
            return authRepo.login(email, pass)
        }else{
            return Result.failure(Exception(validation))
        }
    }

    fun validateData(email: String, pass: String): String {
        if (!isValidEmail(email)) {
            return "Invalid email";
        } else if (!isValidPassword(pass)) {
           return "Invalid password"
        }
        return "success";
    }

    fun isValidEmail(email: String): Boolean {
        return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$"
        )
        return passwordRegex.matches(password)
    }
}