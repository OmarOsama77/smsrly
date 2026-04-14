package com.example.smsrly.utility
import com.auth0.android.jwt.JWT
import java.util.Date

object JwtTokenValidator {
    fun isTokenExpired(token: String): Boolean {
        return try {
            val jwt = JWT(token)
            jwt.expiresAt?.before(Date()) ?: true
        } catch (e: Exception) {
            true
        }
    }

    fun isTokenValid(token: String?): Boolean {
        return token != null && !isTokenExpired(token)
    }
}