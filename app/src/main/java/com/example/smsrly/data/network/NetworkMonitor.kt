package com.example.smsrly.data.network

import android.net.ConnectivityManager
import android.net.Network
import javax.inject.Inject

class NetworkMonitor @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    fun isConnected(){
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                // ✅ This is a callback function
                println("Internet is available")
            }

            override fun onLost(network: Network) {
                // ✅ This is also a callback function
                println("Internet is lost")
            }
        }
    }
}