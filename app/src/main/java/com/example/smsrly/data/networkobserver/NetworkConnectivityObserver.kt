package com.example.smsrly.data.networkobserver


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.example.smsrly.domain.observer.IConnectivityObserver
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkConnectivityObserver @Inject constructor(
    @ApplicationContext private val context: Context
) : IConnectivityObserver {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val _status = MutableStateFlow(IConnectivityObserver.Status.UnAvailable)
    val status: StateFlow<IConnectivityObserver.Status> = _status
    override fun observe(): Flow<IConnectivityObserver.Status> {
        return callbackFlow {
            Log.d("im running ","now")
            val callBack = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    _status.value = IConnectivityObserver.Status.Available

                    trySend(IConnectivityObserver.Status.Available)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    _status.value = IConnectivityObserver.Status.UnAvailable
                    trySend(IConnectivityObserver.Status.UnAvailable)
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callBack)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callBack)
            }
        }
    }

    override fun isOnline(): Boolean {
        if(_status.value == IConnectivityObserver.Status.Available){
            return true
        }
        return false
    }

}