package com.example.smsrly.utility

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import android.Manifest
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val fusedClient = LocationServices.getFusedLocationProviderClient(context)

    suspend fun getLocation(): Pair<Double, Double>? {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) return null

        return suspendCancellableCoroutine { cont ->
            fusedClient.lastLocation.addOnSuccessListener { loc ->
                cont.resume(loc?.let { Pair(it.latitude, it.longitude) })
            }.addOnFailureListener {
                cont.resume(null)
            }
        }
    }
}