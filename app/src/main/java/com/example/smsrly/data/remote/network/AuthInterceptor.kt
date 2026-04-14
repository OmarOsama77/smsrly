package com.example.smsrly.data.remote.network


import android.util.Log
import com.example.smsrly.domain.repository.ITokenRepo

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    val tokenRepo: ITokenRepo
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()



        val token = tokenRepo.getAccessToken()
        val requestBuilder = originalRequest.newBuilder()
        if (!token.isNullOrBlank()) {
            Log.d("The sent token is ",token)
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }


        return chain.proceed(requestBuilder.build())
    }
}
