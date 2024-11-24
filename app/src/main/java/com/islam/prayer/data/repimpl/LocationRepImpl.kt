package com.islam.prayer.data.repimpl

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.islam.prayer.domain.rep.LocationRep
import com.islam.prayer.util.AppConstants.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationRepImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val application: Application
) : LocationRep {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation(): Location? {

        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            application, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isGPSEnabled =
            (application.getSystemService(Context.LOCATION_SERVICE) as? LocationManager)
                ?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

        if (!hasFineLocationPermission && !hasCoarseLocationPermission) {
            Log.d(TAG, "No location permissions granted")
            return null
        }

        // Ensure GPS is enabled for accurate location
        if (!isGPSEnabled) {
            Log.d(TAG, "GPS not enabled")
            return null
        }
        return suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(task.result) {}
                } else {
                    continuation.resume(null) {}
                }
            }.addOnCanceledListener {
                continuation.cancel()
            }
        }
    }
}
