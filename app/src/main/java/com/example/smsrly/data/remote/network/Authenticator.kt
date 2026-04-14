package com.example.smsrly.data.remote.network

import com.example.smsrly.data.remote.apiservice.ApiService
import com.example.smsrly.domain.repository.ITokenRepo
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class Authenticator @Inject constructor(
    private val tokenRepo: ITokenRepo,
    private val apiService: ApiService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = tokenRepo.getRefreshToken()

        val  newToken = runBlocking {
            apiService.refreshAccessToken("Bearer $refreshToken")
        }

        tokenRepo.saveAccessToken(newToken.body()!!.access_token)
        return response.request.newBuilder()
            .header("Authorization", "Bearer ${newToken.body()!!.access_token}")
            .build()

    }

}