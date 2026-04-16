package com.example.smsrly.domain.usecase.authusecases


import com.example.smsrly.domain.models.AuthTokens
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.repository.IAuthRepo
import com.example.smsrly.domain.repository.IFirebaseRepo
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepo: IAuthRepo,
    private val firebaseRepo: IFirebaseRepo
) {


    suspend fun signup(
        user: User,
        password: String,
        confirmPass: String,
        otpCode: String
    ): Result<AuthTokens> {
        val res = validateData(password, confirmPass, user.phoneNumber)
        if (res == "Success") {
            return authRepo.signup(user, password, otpCode).onSuccess { it ->
                firebaseRepo.uploadUserId(it.id)
            }
        }
        return Result.failure(Exception(res))
    }

    fun validateData(
        pass: String,
        confirmPass: String,
        phoneNum: String,
    ): String {
        if (!isValidPassword(pass)) {
            return "password should have at least 8 characters and contains at least one uppercase letter, one lowercase letter, one digit, and one special character";
        } else if (confirmPass.length < 6) {
            return "invalid confirm password";
        } else if (!pass.equals(confirmPass)) {
            return "passwords are not equal";
        } else if (!isValidPhone(phoneNum)) {
            return "invalid phone number"
        }
        return "Success"
    }

    fun isValidPhone(phone: String): Boolean {
        val regex = Regex("^(?:\\+20|0)?1[0125][0-9]{8}$")
        return regex.matches(phone)
    }


    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        )
        return passwordRegex.matches(password)
    }

}