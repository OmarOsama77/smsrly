package com.example.smsrly.domain.usecase.authusecases

import com.example.smsrly.domain.repository.IAuthRepo
import javax.inject.Inject


class OtpUseCase @Inject constructor(private val authRepo: IAuthRepo) {


    suspend fun sendOtp(
        firstName: String,
        lastName: String,
        email: String,

    ): Result<String> {
        val validationRes = validateData(firstName, lastName, email)
        if (validationRes == "success") {
            return authRepo.sendOtp(firstName, lastName, email)
        }
        return Result.failure(Exception(validationRes))
    }


    fun validateData(
        firstName: String,
        lastName: String,
        email: String,

    ): String {
         if (firstName.isEmpty() || firstName.length < 2) {
            return "invalid first name";
        } else if (lastName.isEmpty() || lastName.length < 2) {
            return "invalid last name";
        } else if (email.isEmpty() || !isValidEmail(email)) {
            return "invalid email";
        }


        return "success"
    }


    fun isValidEmail(email: String): Boolean {
        return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matches(email)
    }



}