package com.example.smsrly.data.connectivity


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkConnectivityObserver(
    private val context : Context
) : IConnectivityObserver {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    override fun observe(): Flow<IConnectivityObserver.Status> {
        return callbackFlow {
            val callBack = object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(IConnectivityObserver.Status.Available)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(IConnectivityObserver.Status.UnAvailable)
                }
            }

            awaitClose {
                 connectivityManager.unregisterNetworkCallback(callBack   )
            }
        }
    }
}